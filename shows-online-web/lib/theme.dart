import 'package:material_ui/material_ui.dart';

const Color brandBlue = Color(0xFF29B6F6);
const Color brandBlueDark = Color(0xFF039BE5);

ThemeData buildTheme() {
  final scheme = ColorScheme.fromSeed(
    seedColor: brandBlue,
    primary: brandBlueDark,
    brightness: Brightness.light,
  );
  return ThemeData(
    colorScheme: scheme,
    useMaterial3: true,
    appBarTheme: const AppBarTheme(
      backgroundColor: brandBlueDark,
      foregroundColor: Colors.white,
    ),
    cardTheme: const CardThemeData(clipBehavior: Clip.antiAlias),
  );
}
