import 'package:flutter_test/flutter_test.dart';
import 'package:shows_online_web/models.dart';

void main() {
  test('PostComment round-trips through its map form', () {
    final c = PostComment(
      id: '1',
      author: 'דנה',
      title: 'כותרת',
      text: 'תגובה',
    );
    final back = PostComment.fromMap(c.toMap());
    expect(back.author, 'דנה');
    expect(back.text, 'תגובה');
  });

  test('CommentSection defaults to enabled', () {
    expect(CommentSection.fromMap({}).enabled, isTrue);
  });
}
