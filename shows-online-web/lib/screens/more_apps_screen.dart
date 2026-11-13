import 'package:material_ui/material_ui.dart';

import '../config.dart';
import 'post_screen.dart' show openUrl;

class MoreAppsScreen extends StatelessWidget {
  const MoreAppsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Center(
      child: ConstrainedBox(
        constraints: const BoxConstraints(maxWidth: 420),
        child: Padding(
          padding: const EdgeInsets.all(24),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              Text(
                'אהבתם את הצגות אונליין?\nעוד אפליקציות מבית 3P Cups',
                textAlign: TextAlign.center,
                style: theme.textTheme.titleLarge?.copyWith(
                  color: theme.colorScheme.primary,
                  fontWeight: FontWeight.bold,
                ),
              ),
              const SizedBox(height: 24),
              FilledButton.icon(
                onPressed: () => openUrl(devStoreUrl),
                icon: const Icon(Icons.android),
                label: const Text('לחנות האפליקציות'),
              ),
              const SizedBox(height: 12),
              OutlinedButton.icon(
                onPressed: () => openUrl(tripCupsWebsite),
                icon: const Icon(Icons.public),
                label: const Text('3P Cups'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
