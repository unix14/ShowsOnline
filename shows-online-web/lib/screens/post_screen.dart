import 'package:material_ui/material_ui.dart';
import 'package:flutter_widget_from_html_core/flutter_widget_from_html_core.dart';
import 'package:go_router/go_router.dart';
import 'package:intl/intl.dart';
import 'package:share_plus/share_plus.dart';
import 'package:url_launcher/url_launcher.dart';

import '../config.dart';
import '../models.dart';
import '../repository.dart';
import '../widgets/show_card.dart';

Future<void> openUrl(String url) =>
    launchUrl(Uri.parse(url), mode: LaunchMode.externalApplication);

class PostScreen extends StatefulWidget {
  const PostScreen({super.key, required this.repository, required this.id});
  final ShowsRepository repository;
  final String id;

  @override
  State<PostScreen> createState() => _PostScreenState();
}

class _PostScreenState extends State<PostScreen> {
  late Future<BlogPost?> _future;

  @override
  void initState() {
    super.initState();
    _future = widget.repository.fetchShow(widget.id);
  }

  @override
  void didUpdateWidget(covariant PostScreen old) {
    super.didUpdateWidget(old);
    if (old.id != widget.id) _future = widget.repository.fetchShow(widget.id);
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<BlogPost?>(
      future: _future,
      builder: (context, snap) {
        if (snap.connectionState != ConnectionState.done) {
          return const Center(child: CircularProgressIndicator());
        }
        final post = snap.data;
        if (snap.hasError || post == null) {
          return Center(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                const Text('ההצגה לא נמצאה'),
                const SizedBox(height: 8),
                FilledButton(
                  onPressed: () => context.go('/'),
                  child: const Text('לכל ההצגות'),
                ),
              ],
            ),
          );
        }
        return _PostBody(post: post, repository: widget.repository);
      },
    );
  }
}

class _PostBody extends StatefulWidget {
  const _PostBody({required this.post, required this.repository});
  final BlogPost post;
  final ShowsRepository repository;

  @override
  State<_PostBody> createState() => _PostBodyState();
}

class _PostBodyState extends State<_PostBody> {
  BlogPost get post => widget.post;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final date = post.date == null
        ? null
        : DateFormat('dd/MM/yyyy').format(post.date!);
    return SelectionArea(
      child: ListView(
        padding: const EdgeInsets.only(bottom: 48),
        children: [
          Center(
            child: ConstrainedBox(
              constraints: const BoxConstraints(maxWidth: 820),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  Padding(
                    padding: const EdgeInsets.all(8),
                    child: Align(
                      alignment: AlignmentDirectional.centerStart,
                      child: TextButton.icon(
                        onPressed: () => context.go('/'),
                        icon: const Icon(Icons.arrow_forward),
                        label: const Text('כל ההצגות'),
                      ),
                    ),
                  ),
                  AspectRatio(
                    aspectRatio: 16 / 9,
                    child: ShowImage(url: post.thumbnail),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(16),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          post.title,
                          style: theme.textTheme.headlineMedium?.copyWith(
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        if (date != null)
                          Text(date, style: theme.textTheme.bodySmall),
                        if (post.tags.isNotEmpty) ...[
                          const SizedBox(height: 8),
                          Wrap(
                            spacing: 8,
                            children: [
                              for (final t in post.tags) Chip(label: Text(t)),
                            ],
                          ),
                        ],
                        const SizedBox(height: 12),
                        if (post.shortDescription.isNotEmpty)
                          HtmlWidget(
                            post.shortDescription,
                            onTapUrl: (u) async {
                              await openUrl(u);
                              return true;
                            },
                          ),
                        if (post.description.isNotEmpty) ...[
                          const SizedBox(height: 12),
                          HtmlWidget(
                            post.description,
                            onTapUrl: (u) async {
                              await openUrl(u);
                              return true;
                            },
                          ),
                        ],
                        const SizedBox(height: 16),
                        Wrap(
                          spacing: 8,
                          runSpacing: 8,
                          children: [
                            for (final l in post.links)
                              FilledButton.icon(
                                onPressed: () => openUrl(l.url),
                                icon: const Icon(Icons.play_circle_outline),
                                label: Text(l.text.isEmpty ? 'לצפייה' : l.text),
                              ),
                            OutlinedButton.icon(
                              onPressed: () => SharePlus.instance.share(
                                ShareParams(
                                  text:
                                      '${post.links.isNotEmpty ? '${post.links.first.url}\n' : ''}'
                                      'מצאתי הצגה שווה! "${post.title}"\n$siteUrl/show/${post.id}',
                                  subject: 'שיתוף הצגה: ${post.title}',
                                ),
                              ),
                              icon: const Icon(Icons.share),
                              label: const Text('שיתוף'),
                            ),
                          ],
                        ),
                        if (post.author.showAuthor) ...[
                          const Divider(height: 40),
                          _AuthorView(author: post.author),
                        ],
                        if (post.comments.enabled) ...[
                          const Divider(height: 40),
                          _CommentsSection(
                            post: post,
                            repository: widget.repository,
                          ),
                        ],
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class _AuthorView extends StatelessWidget {
  const _AuthorView({required this.author});
  final Author author;

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: author.link.isEmpty ? null : () => openUrl(author.link),
      child: Row(
        children: [
          if (author.image.isNotEmpty)
            ClipRRect(
              borderRadius: BorderRadius.circular(author.circleImage ? 28 : 8),
              child: SizedBox(
                width: 56,
                height: 56,
                child: ShowImage(url: author.image),
              ),
            ),
          const SizedBox(width: 12),
          Expanded(
            child: Text(
              author.name,
              style: Theme.of(context).textTheme.titleMedium,
            ),
          ),
        ],
      ),
    );
  }
}

class _CommentsSection extends StatefulWidget {
  const _CommentsSection({required this.post, required this.repository});
  final BlogPost post;
  final ShowsRepository repository;

  @override
  State<_CommentsSection> createState() => _CommentsSectionState();
}

class _CommentsSectionState extends State<_CommentsSection> {
  final _form = GlobalKey<FormState>();
  final _author = TextEditingController();
  final _title = TextEditingController();
  final _text = TextEditingController();
  bool _sending = false;

  @override
  void dispose() {
    _author.dispose();
    _title.dispose();
    _text.dispose();
    super.dispose();
  }

  Future<void> _submit() async {
    if (!_form.currentState!.validate()) return;
    setState(() => _sending = true);
    final messenger = ScaffoldMessenger.of(context);
    try {
      await widget.repository.addComment(
        widget.post,
        author: _author.text,
        title: _title.text,
        text: _text.text,
      );
      _title.clear();
      _text.clear();
      messenger.showSnackBar(const SnackBar(content: Text('התגובה נשלחה')));
    } catch (_) {
      messenger.showSnackBar(
        const SnackBar(content: Text('שליחת התגובה נכשלה')),
      );
    } finally {
      if (mounted) setState(() => _sending = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final comments = widget.post.comments.comments;
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text('תגובות (${comments.length})', style: theme.textTheme.titleLarge),
        const SizedBox(height: 8),
        for (final c in comments) _CommentTile(comment: c),
        const SizedBox(height: 16),
        Form(
          key: _form,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              TextFormField(
                controller: _author,
                decoration: const InputDecoration(
                  labelText: 'שם (לא חובה)',
                  border: OutlineInputBorder(),
                ),
              ),
              const SizedBox(height: 8),
              TextFormField(
                controller: _title,
                decoration: const InputDecoration(
                  labelText: 'כותרת',
                  border: OutlineInputBorder(),
                ),
              ),
              const SizedBox(height: 8),
              TextFormField(
                controller: _text,
                minLines: 3,
                maxLines: 6,
                decoration: const InputDecoration(
                  labelText: 'תגובה',
                  border: OutlineInputBorder(),
                ),
                validator: (v) =>
                    (v == null || v.trim().isEmpty) ? 'יש לכתוב תגובה' : null,
              ),
              const SizedBox(height: 8),
              Align(
                alignment: AlignmentDirectional.centerStart,
                child: FilledButton(
                  onPressed: _sending ? null : _submit,
                  child: _sending
                      ? const SizedBox(
                          width: 18,
                          height: 18,
                          child: CircularProgressIndicator(strokeWidth: 2),
                        )
                      : const Text('שליחה'),
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}

class _CommentTile extends StatelessWidget {
  const _CommentTile({required this.comment, this.depth = 0});
  final PostComment comment;
  final int depth;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final date = comment.date == null
        ? ''
        : DateFormat('dd/MM/yyyy HH:mm').format(comment.date!);
    return Padding(
      padding: EdgeInsetsDirectional.only(start: depth * 24.0, bottom: 8),
      child: Card(
        child: Padding(
          padding: const EdgeInsets.all(12),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                '${comment.author}  ·  $date',
                style: theme.textTheme.bodySmall,
              ),
              if (comment.title.isNotEmpty)
                Text(
                  comment.title,
                  style: theme.textTheme.titleSmall?.copyWith(
                    fontWeight: FontWeight.bold,
                  ),
                ),
              Text(comment.text),
              for (final r in comment.replies)
                _CommentTile(comment: r, depth: depth + 1),
            ],
          ),
        ),
      ),
    );
  }
}
