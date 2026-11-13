import 'dart:math';

import 'package:cloud_firestore/cloud_firestore.dart';

import 'config.dart';
import 'models.dart';

class ShowsRepository {
  ShowsRepository([FirebaseFirestore? firestore])
    : _db = firestore ?? FirebaseFirestore.instance;

  final FirebaseFirestore _db;
  List<BlogPost>? _cache;

  Future<List<BlogPost>> fetchShows({bool refresh = false}) async {
    if (!refresh && _cache != null) return _cache!;
    final snap = await _db.collection(showsCollection).get();
    final posts =
        snap.docs.map((d) => BlogPost.fromDoc(d, showsCollection)).toList()
          ..sort(
            (a, b) => (b.date ?? DateTime(0)).compareTo(a.date ?? DateTime(0)),
          );
    return _cache = posts;
  }

  Future<BlogPost?> fetchShow(String id) async {
    final cached = _cache?.where((p) => p.id == id);
    if (cached != null && cached.isNotEmpty) return cached.first;
    final doc = await _db.collection(showsCollection).doc(id).get();
    if (!doc.exists) return null;
    return BlogPost.fromDoc(doc, showsCollection);
  }

  /// Appends a comment inside a transaction (same document layout as the
  /// Android app: the whole `comments` map is rewritten).
  Future<PostComment> addComment(
    BlogPost post, {
    required String author,
    required String title,
    required String text,
  }) async {
    final comment = PostComment(
      id: (Random().nextInt(900000) + 100000).toString(),
      author: author.trim().isEmpty ? emptyUserName : author.trim(),
      date: DateTime.now(),
      title: title.trim(),
      text: text.trim(),
    );
    final ref = _db.collection(post.category).doc(post.id);
    await _db.runTransaction((tx) async {
      final snap = await tx.get(ref);
      final section = CommentSection.fromMap(
        (snap.data()?['comments'] as Map?)?.cast<String, dynamic>() ?? {},
      );
      section.comments.add(comment);
      tx.update(ref, {'comments': section.toMap()});
    });
    post.comments.comments.add(comment);
    return comment;
  }
}
