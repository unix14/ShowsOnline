import 'package:firebase_core/firebase_core.dart';

/// Web SDK config for Firebase project "Shows Online" (shows-online-d3274).
/// These values are public identifiers (not secrets). apiKey and appId come
/// from the Web app's config in the Firebase console and are injected at build
/// time with --dart-define (CI does this automatically, see
/// .github/workflows/deploy.yml and scripts/gen_dart_defines.sh).
class DefaultFirebaseOptions {
  static const String projectId = 'shows-online-d3274';

  static const FirebaseOptions web = FirebaseOptions(
    apiKey: String.fromEnvironment('FIREBASE_API_KEY'),
    appId: String.fromEnvironment('FIREBASE_APP_ID'),
    messagingSenderId: '16261396358',
    projectId: projectId,
    authDomain: 'shows-online-d3274.firebaseapp.com',
    storageBucket: String.fromEnvironment(
      'FIREBASE_STORAGE_BUCKET',
      defaultValue: 'shows-online-d3274.firebasestorage.app',
    ),
    measurementId: String.fromEnvironment('FIREBASE_MEASUREMENT_ID'),
  );
}
