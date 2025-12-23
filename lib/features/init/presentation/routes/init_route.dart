import 'package:pos_manager/app_export.dart';
import 'package:pos_manager/features/init/presentation/screen/init_screen.dart';
part 'init_route.g.dart';

@TypedGoRoute<InitRoute>(path: '/')
class InitRoute extends GoRouteData with _$InitRoute {
  @override
  Widget build(BuildContext context, GoRouterState state) => InitScreen();
}
