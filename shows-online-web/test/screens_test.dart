import 'package:flutter_test/flutter_test.dart';
import 'package:material_ui/material_ui.dart';
import 'package:shows_online_web/screens/about_screen.dart';
import 'package:shows_online_web/screens/contact_screen.dart';
import 'package:shows_online_web/screens/more_apps_screen.dart';

Widget _host(Widget child) => MaterialApp(
  locale: const Locale('he'),
  home: Directionality(
    textDirection: TextDirection.rtl,
    child: Scaffold(body: child),
  ),
);

void main() {
  testWidgets('About screen shows the welcome text', (tester) async {
    await tester.pumpWidget(_host(const AboutScreen()));
    expect(find.textContaining('ברוכים הבאים'), findsOneWidget);
  });

  testWidgets('Contact screen offers email and WhatsApp', (tester) async {
    await tester.pumpWidget(_host(const ContactScreen()));
    expect(find.text('שליחת מייל'), findsOneWidget);
    expect(find.text('לקבוצת הוואטסאפ'), findsOneWidget);
  });

  testWidgets('More apps screen links to the store', (tester) async {
    await tester.pumpWidget(_host(const MoreAppsScreen()));
    expect(find.text('לחנות האפליקציות'), findsOneWidget);
  });
}
