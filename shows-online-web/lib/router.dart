import 'package:material_ui/material_ui.dart';
import 'package:go_router/go_router.dart';

import 'repository.dart';
import 'screens/about_screen.dart';
import 'screens/contact_screen.dart';
import 'screens/home_screen.dart';
import 'screens/more_apps_screen.dart';
import 'screens/post_screen.dart';
import 'widgets/app_shell.dart';

GoRouter buildRouter(ShowsRepository repo) {
  return GoRouter(
    routes: [
      ShellRoute(
        builder: (context, state, child) =>
            AppShell(location: state.uri.path, child: child),
        routes: [
          GoRoute(
            path: '/',
            builder: (_, _) => HomeScreen(repository: repo),
          ),
          GoRoute(
            path: '/show/:id',
            builder: (_, state) =>
                PostScreen(repository: repo, id: state.pathParameters['id']!),
          ),
          GoRoute(path: '/apps', builder: (_, _) => const MoreAppsScreen()),
          GoRoute(path: '/contact', builder: (_, _) => const ContactScreen()),
          GoRoute(path: '/about', builder: (_, _) => const AboutScreen()),
        ],
      ),
    ],
    errorBuilder: (context, state) => Scaffold(
      appBar: AppBar(title: const Text('העמוד לא נמצא')),
      body: Center(
        child: FilledButton(
          onPressed: () => context.go('/'),
          child: const Text('לכל ההצגות'),
        ),
      ),
    ),
  );
}
