import 'package:material_ui/material_ui.dart';

import '../config.dart';
import 'post_screen.dart' show openUrl;

class AboutScreen extends StatelessWidget {
  const AboutScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: ConstrainedBox(
        constraints: const BoxConstraints(maxWidth: 720),
        child: ListView(
          padding: const EdgeInsets.all(24),
          children: [
            Text(aboutText, style: Theme.of(context).textTheme.bodyLarge),
            const SizedBox(height: 24),
            Wrap(
              spacing: 12,
              runSpacing: 12,
              children: [
                OutlinedButton(
                  onPressed: () => openUrl(designerWebsite),
                  child: const Text('עיצוב ע"י: סיגל אברמוביץ'),
                ),
                FilledButton(
                  onPressed: () => openUrl(appStoreUrl),
                  child: const Text('לאפליקציה בחנות'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
