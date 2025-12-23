import 'package:pos_manager/features/main_app/main_app_inject.dart';
import 'package:pos_manager/features/init/init_inject.dart';

import 'app_export.dart';

Future<void> main() async {
  injectMainAppModule();
  injectInitModule();
  EasyLocalization.logger.enableBuildModes = [];
  WidgetsFlutterBinding.ensureInitialized();
  await EasyLocalization.ensureInitialized();

  // final firebaseApp = await Firebase.initializeApp();
  GetIt.instance.enableRegisteringMultipleInstancesOfOneType();
  runApp(const MainAppScreen());
}
