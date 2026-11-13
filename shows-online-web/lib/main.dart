import 'package:firebase_core/firebase_core.dart';
import 'package:material_ui/material_ui.dart';
import 'package:flutter_localizations/flutter_localizations.dart'
    show GlobalWidgetsLocalizations;
import 'package:go_router/go_router.dart';

import 'config.dart';
import 'firebase_options.dart';
import 'repository.dart';
import 'router.dart';
import 'theme.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(options: DefaultFirebaseOptions.web);
  runApp(ShowsOnlineApp(repository: ShowsRepository()));
}

class ShowsOnlineApp extends StatelessWidget {
  ShowsOnlineApp({super.key, required this.repository})
    : _router = buildRouter(repository);

  final ShowsRepository repository;
  final GoRouter _router;

  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      title: appName,
      debugShowCheckedModeBanner: false,
      theme: buildTheme(),
      locale: const Locale('he'),
      supportedLocales: const [Locale('he')],
      localizationsDelegates: const [
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
      ],
      routerConfig: _router,
    );
  }
}
