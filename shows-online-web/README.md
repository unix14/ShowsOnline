# Shows Online (Flutter web)

Flutter web port of the **ShowsOnline** Android app (הצגות אונליין): a Hebrew, right-to-left
catalog of shows backed by Firestore. Public reader experience only: browse/search/filter shows,
read a show (HTML description, links, tags, author), read and post comments, share, plus
About / Contact / More-apps pages. Admin editing and the invitation system are intentionally not ported.

- Live site: https://showz-online.web.app
- Firebase project: `shows-online-d3274` (Hosting site `showz-online`)
- Firestore collection: `showsOnline` (same document layout as the Android app)

## Develop

```bash
flutter pub get
flutter test
# apiKey / appId come from the Web app config (Firebase console > Project settings > Your apps)
flutter run -d chrome \
  --dart-define=FIREBASE_API_KEY=... \
  --dart-define=FIREBASE_APP_ID=1:16261396358:web:...
```

`lib/firebase_options.dart` holds the rest of the config. These are public web identifiers, not secrets.

## Deploy (CI/CD)

Pushing a tag that starts with `v` builds and deploys to Firebase Hosting (`.github/workflows/deploy.yml`):

```bash
git tag v1.0.0 && git push origin v1.0.0
```

Required GitHub secret: `FIREBASE_SERVICE_ACCOUNT` (service account JSON for `shows-online-d3274`).
The workflow reads the Web app's `apiKey`/`appId` with the Firebase CLI using that service account
(needs a role that can read Firebase SDK config, e.g. Firebase Viewer/Admin). If you'd rather not
depend on that, set the repo **variables** `FIREBASE_API_KEY` and `FIREBASE_APP_ID` and the CLI step is skipped.
If the project has several Web apps, also set `FIREBASE_WEB_APP_ID`.

`.github/workflows/ci.yml` runs `flutter analyze` and `flutter test` on pushes to `main` and on pull requests.

## Firestore rules

The site reads posts and appends comments with the client SDK, exactly like the Android app, so the
project's Firestore rules must allow public read of `showsOnline` and updates to the `comments` field.
Rules are not managed by this repo.
