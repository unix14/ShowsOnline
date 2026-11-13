import 'package:material_ui/material_ui.dart';
import 'package:go_router/go_router.dart';

import '../models.dart';
import '../repository.dart';
import '../widgets/show_card.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key, required this.repository});
  final ShowsRepository repository;

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  late Future<List<BlogPost>> _future;
  String _query = '';
  String? _tag;

  @override
  void initState() {
    super.initState();
    _future = widget.repository.fetchShows();
  }

  void _reload() => setState(() {
    _future = widget.repository.fetchShows(refresh: true);
  });

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<List<BlogPost>>(
      future: _future,
      builder: (context, snap) {
        if (snap.connectionState != ConnectionState.done) {
          return const Center(child: CircularProgressIndicator());
        }
        if (snap.hasError) {
          return Center(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                const Text('טעינת ההצגות נכשלה'),
                const SizedBox(height: 8),
                FilledButton(onPressed: _reload, child: const Text('נסו שוב')),
              ],
            ),
          );
        }
        final all = snap.data ?? [];
        final tags = {for (final p in all) ...p.tags}.toList()..sort();
        final shows = all.where((p) {
          final q = _query.trim().toLowerCase();
          final matchesQuery =
              q.isEmpty ||
              p.title.toLowerCase().contains(q) ||
              p.shortDescription.toLowerCase().contains(q);
          final matchesTag = _tag == null || p.tags.contains(_tag);
          return matchesQuery && matchesTag;
        }).toList();

        return RefreshIndicator(
          onRefresh: () async => _reload(),
          child: CustomScrollView(
            slivers: [
              SliverToBoxAdapter(
                child: Padding(
                  padding: const EdgeInsets.fromLTRB(16, 16, 16, 8),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.stretch,
                    children: [
                      SearchBar(
                        hintText: 'הקלידו שם של הצגה',
                        leading: const Icon(Icons.search),
                        onChanged: (v) => setState(() => _query = v),
                      ),
                      if (tags.isNotEmpty) ...[
                        const SizedBox(height: 12),
                        Wrap(
                          spacing: 8,
                          runSpacing: 4,
                          children: [
                            for (final t in tags)
                              FilterChip(
                                label: Text(t),
                                selected: _tag == t,
                                onSelected: (sel) =>
                                    setState(() => _tag = sel ? t : null),
                              ),
                          ],
                        ),
                      ],
                    ],
                  ),
                ),
              ),
              if (shows.isEmpty)
                const SliverFillRemaining(
                  hasScrollBody: false,
                  child: Center(child: Text('לא נמצאו הצגות')),
                )
              else
                SliverPadding(
                  padding: const EdgeInsets.all(16),
                  sliver: SliverGrid.builder(
                    gridDelegate:
                        const SliverGridDelegateWithMaxCrossAxisExtent(
                          maxCrossAxisExtent: 320,
                          mainAxisSpacing: 16,
                          crossAxisSpacing: 16,
                          childAspectRatio: 0.85,
                        ),
                    itemCount: shows.length,
                    itemBuilder: (_, i) => ShowCard(
                      post: shows[i],
                      onTap: () => context.go('/show/${shows[i].id}'),
                    ),
                  ),
                ),
            ],
          ),
        );
      },
    );
  }
}
