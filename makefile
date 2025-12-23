
# Generate route.g.dart files and update all_routes.dart files
route:
	fvm dart run build_runner build --delete-conflicting-outputs && fvm dart "flutter_tools/tools/gen_routes.dart" pos_manager

# Generate feature files
# make feature name=loginWithFacebook
feature:
	mason make create_feature --feature=${name} -o "lib/features"

# Generate usecase files
# make usecase name=${name} path=${path}
usecase:
	mason make create_usecase --feature=${name} -o  ${path}
gen_api:
	fvm dart "flutter_tools/tools/gen_api.dart" pos_manager
get_tools:
	rm -rf flutter_tools && git clone https://github.com/maxakak1998/flutter_tools.git