import 'package:material_ui/material_ui.dart';

import '../models.dart';

class ShowCard extends StatelessWidget {
  const ShowCard({super.key, required this.post, required this.onTap});
  final BlogPost post;
  final VoidCallback onTap;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Card(
      child: InkWell(
        onTap: onTap,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Expanded(child: ShowImage(url: post.thumbnail)),
            Padding(
              padding: const EdgeInsets.all(12),
              child: Text(
                post.title,
                maxLines: 2,
                overflow: TextOverflow.ellipsis,
                style: theme.textTheme.titleMedium?.copyWith(
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            if (post.tags.isNotEmpty)
              Padding(
                padding: const EdgeInsets.fromLTRB(12, 0, 12, 12),
                child: Text(
                  post.tags.take(3).join(' · '),
                  maxLines: 1,
                  overflow: TextOverflow.ellipsis,
                  style: theme.textTheme.bodySmall?.copyWith(
                    color: theme.colorScheme.primary,
                  ),
                ),
              ),
          ],
        ),
      ),
    );
  }
}

class ShowImage extends StatelessWidget {
  const ShowImage({super.key, required this.url, this.fit = BoxFit.cover});
  final String? url;
  final BoxFit fit;

  @override
  Widget build(BuildContext context) {
    final placeholder = ColoredBox(
      color: Theme.of(context).colorScheme.surfaceContainerHighest,
      child: const Center(child: Icon(Icons.theater_comedy, size: 48)),
    );
    if (url == null || url!.isEmpty) return placeholder;
    return Image.network(
      url!,
      fit: fit,
      errorBuilder: (_, _, _) => placeholder,
      loadingBuilder: (_, child, progress) =>
          progress == null ? child : placeholder,
    );
  }
}
