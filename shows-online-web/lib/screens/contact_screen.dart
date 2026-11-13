import 'package:material_ui/material_ui.dart';

import '../config.dart';
import 'post_screen.dart' show openUrl;

class ContactScreen extends StatelessWidget {
  const ContactScreen({super.key});

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
                'יש לכם הצעות לשיפור?\nנשמח לשמוע ממכם',
                textAlign: TextAlign.center,
                style: theme.textTheme.titleLarge?.copyWith(
                  color: theme.colorScheme.primary,
                  fontWeight: FontWeight.bold,
                ),
              ),
              const SizedBox(height: 24),
              FilledButton.icon(
                onPressed: () => openUrl(
                  'mailto:$supportEmail?subject=${Uri.encodeComponent('הצעה חדשה - $appName')}',
                ),
                icon: const Icon(Icons.mail),
                label: const Text('שליחת מייל'),
              ),
              const SizedBox(height: 12),
              FilledButton.icon(
                onPressed: () => openUrl(whatsappGroupUrl),
                icon: const Icon(Icons.chat),
                label: const Text('לקבוצת הוואטסאפ'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
