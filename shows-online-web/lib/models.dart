import 'package:cloud_firestore/cloud_firestore.dart';

const String emptyUserName = 'ללא שם';

DateTime? _date(dynamic v) {
  if (v is Timestamp) return v.toDate();
  if (v is DateTime) return v;
  return null;
}

Map<String, dynamic> _map(dynamic v) =>
    v is Map ? Map<String, dynamic>.from(v) : <String, dynamic>{};

List<Map<String, dynamic>> _list(dynamic v) =>
    v is List ? v.map(_map).toList() : <Map<String, dynamic>>[];

class HyperLink {
  const HyperLink({required this.url, required this.text});
  final String url;
  final String text;

  factory HyperLink.fromMap(Map<String, dynamic> m) => HyperLink(
    url: (m['url'] ?? '') as String,
    text: (m['text'] ?? '') as String,
  );
}

class Author {
  const Author({
    this.name = '3P Cups',
    this.desc = '',
    this.image = '',
    this.link = '',
    this.showAuthor = true,
    this.circleImage = false,
  });
  final String name;
  final String desc;
  final String image;
  final String link;
  final bool showAuthor;
  final bool circleImage;

  factory Author.fromMap(Map<String, dynamic> m) => Author(
    name: (m['name'] ?? '3P Cups') as String,
    desc: (m['desc'] ?? '') as String,
    image: (m['image'] ?? '') as String,
    link: (m['link'] ?? '') as String,
    showAuthor: (m['showAuthor'] ?? true) as bool,
    circleImage: (m['circleImageAuthor'] ?? false) as bool,
  );
}

class PostComment {
  PostComment({
    required this.id,
    this.author = emptyUserName,
    this.date,
    this.title = '',
    this.text = '',
    this.replies = const [],
  });
  final String? id;
  final String author;
  final DateTime? date;
  final String title;
  final String text;
  final List<PostComment> replies;

  factory PostComment.fromMap(Map<String, dynamic> m) => PostComment(
    id: m['id']?.toString(),
    author: (m['author'] ?? emptyUserName) as String,
    date: _date(m['date']),
    title: (m['title'] ?? '') as String,
    text: (m['text'] ?? '') as String,
    replies: _list(m['commentReplies']).map(PostComment.fromMap).toList(),
  );

  Map<String, dynamic> toMap() => {
    'id': id,
    'author': author,
    'date': date == null ? null : Timestamp.fromDate(date!),
    'title': title,
    'text': text,
    'commentReplies': replies.map((r) => r.toMap()).toList(),
  };
}

class CommentSection {
  CommentSection({this.enabled = true, List<PostComment>? comments})
    : comments = comments ?? [];
  bool enabled;
  List<PostComment> comments;

  factory CommentSection.fromMap(Map<String, dynamic> m) => CommentSection(
    enabled: (m['commentsEnabled'] ?? true) as bool,
    comments: _list(m['comments']).map(PostComment.fromMap).toList(),
  );

  Map<String, dynamic> toMap() => {
    'commentsEnabled': enabled,
    'comments': comments.map((c) => c.toMap()).toList(),
  };
}

class BlogPost {
  BlogPost({
    required this.id,
    required this.category,
    this.title = '',
    this.thumbnail,
    this.shortDescription = '',
    this.description = '',
    this.links = const [],
    this.date,
    this.tags = const [],
    this.author = const Author(),
    CommentSection? comments,
  }) : comments = comments ?? CommentSection();

  final String id;
  final String category;
  final String title;
  final String? thumbnail;
  final String shortDescription;
  final String description;
  final List<HyperLink> links;
  final DateTime? date;
  final List<String> tags;
  final Author author;
  final CommentSection comments;

  factory BlogPost.fromDoc(
    DocumentSnapshot<Map<String, dynamic>> doc,
    String category,
  ) {
    final d = doc.data() ?? {};
    return BlogPost(
      id: doc.id,
      category: category,
      title: (d['title'] ?? '') as String,
      thumbnail: d['thumbnail'] as String?,
      shortDescription: (d['shortDescription'] ?? '') as String,
      description: (d['description'] ?? '') as String,
      links: _list(d['links']).map(HyperLink.fromMap).toList(),
      date: _date(d['date']),
      tags: _list(d['tags'])
          .map((t) => (t['tag'] ?? '') as String)
          .where((t) => t.isNotEmpty)
          .toList(),
      author: Author.fromMap(_map(d['author'])),
      comments: CommentSection.fromMap(_map(d['comments'])),
    );
  }
}
