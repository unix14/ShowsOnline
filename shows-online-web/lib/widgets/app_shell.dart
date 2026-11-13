import 'package:material_ui/material_ui.dart';
import 'package:go_router/go_router.dart';
import 'package:share_plus/share_plus.dart';

import '../config.dart';

class AppShell extends StatelessWidget {
  const AppShell({super.key, required this.location, required this.child});

  final String location;
  final Widget child;

  static const _items = [
    (path: '/', label: 'כל ההצגות', icon: Icons.home_outlined),
    (path: '/apps', label: 'אפליקציות נוספות', icon: Icons.android),
    (path: '/contact', label: 'דברו איתנו', icon: Icons.mail_outline),
    (path: '/about', label: 'אודות', icon: Icons.info_outline),
  ];

  @override
  Widget build(BuildContext context) {
    final wide = MediaQuery.sizeOf(context).width >= 900;
    final selected = _items.indexWhere(
      (i) => i.path == '/'
          ? location == '/' || location.startsWith('/show')
          : location.startsWith(i.path),
    );

    final nav = NavigationDrawer(
      selectedIndex: selected < 0 ? null : selected,
      onDestinationSelected: (i) {
        context.go(_items[i].path);
        if (!wide) Navigator.of(context).maybePop();
      },
      children: [
        const Padding(
          padding: EdgeInsets.fromLTRB(28, 24, 16, 12),
          child: Text(
            appName,
            style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
          ),
        ),
        for (final i in _items)
          NavigationDrawerDestination(icon: Icon(i.icon), label: Text(i.label)),
      ],
    );

    return Scaffold(
      appBar: AppBar(
        title: const Text(appName),
        actions: [
          IconButton(
            tooltip: 'שיתוף',
            icon: const Icon(Icons.share),
            onPressed: () => SharePlus.instance.share(
              ShareParams(
                text:
                    'שמעת על האתר הצגות אונליין? כל ההצגות במקום אחד: $siteUrl',
                subject: 'הצגות אונליין',
              ),
            ),
          ),
        ],
      ),
      drawer: wide ? null : nav,
      body: wide
          ? Row(
              children: [
                SizedBox(width: 300, child: nav),
                const VerticalDivider(width: 1),
                Expanded(child: child),
              ],
            )
          : child,
    );
  }
}
