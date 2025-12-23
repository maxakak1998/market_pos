# 🔧 Technical Architecture & Dependencies

## Overview

This document provides technical details about the architecture, dependencies, and integration patterns used in the Chợ Số POS application.

---

## 🏗️ Architecture Pattern

### Clean Architecture with Feature-Based Structure

```
┌─────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │   Screens    │  │   Widgets    │  │    Cubit     │       │
│  └──────────────┘  └──────────────┘  └──────────────┘       │
├─────────────────────────────────────────────────────────────┤
│                      DOMAIN LAYER                            │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │    Models    │  │   UseCases   │  │ Repositories │       │
│  │              │  │              │  │ (Interfaces) │       │
│  └──────────────┘  └──────────────┘  └──────────────┘       │
├─────────────────────────────────────────────────────────────┤
│                       DATA LAYER                             │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │ Repositories │  │  Data Sources │  │   Services   │       │
│  │   (Impl)     │  │  (Firestore)  │  │             │       │
│  └──────────────┘  └──────────────┘  └──────────────┘       │
└─────────────────────────────────────────────────────────────┘
```

### Data Flow

```
User Action → Widget → Cubit → UseCase → Repository → Firestore
                  ↑                                      ↓
                  └─────────── State Update ─────────────┘
```

---

## 📦 Dependencies

### Core Flutter Packages

```yaml
dependencies:
  flutter:
    sdk: flutter
  
  # State Management
  flutter_bloc: ^9.1.1
  equatable: ^2.0.7
  
  # Dependency Injection
  get_it: ^8.0.3
  
  # Navigation
  go_router: ^15.1.3
  
  # Localization
  easy_localization: ^3.0.7+1
  
  # Responsive Design
  flutter_screenutil: ^5.9.3
  
  # Firebase Suite
  firebase_core: ^3.11.0
  cloud_firestore: ^5.6.3
  firebase_auth: ^5.4.2
  firebase_storage: ^12.4.2
  firebase_analytics: ^11.4.2
  firebase_crashlytics: ^4.3.2
  firebase_messaging: ^15.2.2
  firebase_remote_config: ^5.4.0
  
  # Network & API
  dio: ^5.8.0+1
  connectivity_plus: ^6.0.5
  
  # Storage
  flutter_secure_storage: ^9.2.4
  shared_preferences: ^2.5.3
  
  # UI Components
  flutter_svg: ^2.1.0
  extended_image: ^10.0.1
  flutter_easyloading: ^3.0.5
  
  # Utilities
  rxdart: ^0.27.7
  crypto: ^3.0.6
  url_launcher: ^6.2.4
  package_info_plus: ^8.3.0
  
  # Media
  image_picker: ^1.1.2
  permission_handler: ^11.3.1
  image: ^4.1.7

dev_dependencies:
  flutter_test:
    sdk: flutter
  
  # Code Generation
  build_runner: ^2.4.15
  go_router_builder: ^3.0.0
  
  # Linting
  flutter_lints: ^5.0.0
```

### Additional Dependencies to Add

```yaml
dependencies:
  # Barcode Scanner
  mobile_scanner: ^5.2.3
  
  # OCR (Choose one)
  google_mlkit_text_recognition: ^0.13.1
  # OR
  # firebase_ml_vision: ^latest
  
  # Charts for Revenue
  fl_chart: ^0.69.0
  
  # Date Formatting
  intl: ^0.19.0
  
  # Currency Formatting
  money_formatter: ^0.0.5
  
  # UUID for IDs
  uuid: ^4.5.1
  
  # Caching
  cached_network_image: ^3.4.1
  
  # JSON Serialization
  json_annotation: ^4.9.0
  freezed_annotation: ^2.4.4

dev_dependencies:
  # JSON Code Generation
  json_serializable: ^6.8.0
  freezed: ^2.5.7
  
  # Mocking
  mocktail: ^1.0.4
  
  # Testing
  bloc_test: ^9.1.7
```

---

## 🔥 Firebase Configuration

### Firestore Indexes

Create `firestore.indexes.json`:

```json
{
  "indexes": [
    {
      "collectionGroup": "products",
      "queryScope": "COLLECTION",
      "fields": [
        { "fieldPath": "storeId", "order": "ASCENDING" },
        { "fieldPath": "isActive", "order": "ASCENDING" },
        { "fieldPath": "name", "order": "ASCENDING" }
      ]
    },
    {
      "collectionGroup": "products",
      "queryScope": "COLLECTION",
      "fields": [
        { "fieldPath": "storeId", "order": "ASCENDING" },
        { "fieldPath": "categoryId", "order": "ASCENDING" },
        { "fieldPath": "isActive", "order": "ASCENDING" }
      ]
    },
    {
      "collectionGroup": "orders",
      "queryScope": "COLLECTION",
      "fields": [
        { "fieldPath": "storeId", "order": "ASCENDING" },
        { "fieldPath": "createdAt", "order": "DESCENDING" }
      ]
    },
    {
      "collectionGroup": "orders",
      "queryScope": "COLLECTION",
      "fields": [
        { "fieldPath": "storeId", "order": "ASCENDING" },
        { "fieldPath": "status", "order": "ASCENDING" },
        { "fieldPath": "createdAt", "order": "DESCENDING" }
      ]
    },
    {
      "collectionGroup": "daily_revenue",
      "queryScope": "COLLECTION",
      "fields": [
        { "fieldPath": "storeId", "order": "ASCENDING" },
        { "fieldPath": "date", "order": "DESCENDING" }
      ]
    }
  ]
}
```

### Firestore Offline Persistence

```dart
// lib/core/services/firestore_service.dart
class FirestoreService {
  static Future<void> initialize() async {
    FirebaseFirestore.instance.settings = const Settings(
      persistenceEnabled: true,
      cacheSizeBytes: Settings.CACHE_SIZE_UNLIMITED,
    );
  }
  
  static FirebaseFirestore get instance => FirebaseFirestore.instance;
}
```

---

## 🗂️ Dependency Injection Setup

### Main Injection Configuration

```dart
// lib/core/di/injection.dart
import 'package:get_it/get_it.dart';

final sl = GetIt.instance;

Future<void> initializeDependencies() async {
  // Core Services
  sl.registerLazySingleton<FirebaseFirestore>(
    () => FirebaseFirestore.instance,
  );
  
  sl.registerLazySingleton<FirebaseAuth>(
    () => FirebaseAuth.instance,
  );
  
  sl.registerLazySingleton<FirebaseStorage>(
    () => FirebaseStorage.instance,
  );
  
  // Feature Injections
  injectInventoryModule();
  injectPOSModule();
  injectRevenueModule();
  injectTaxCalculatorModule();
  injectBillScannerModule();
  injectSettingsModule();
}
```

### Feature Injection Example

```dart
// lib/features/inventory/inventory_inject.dart
void injectInventoryModule() {
  // Repository
  sl.registerLazySingleton<IInventoryRepository>(
    () => InventoryRepositoryImpl(
      firestore: sl<FirebaseFirestore>(),
    ),
  );
  
  // UseCases
  sl.registerFactory(() => GetProductsUseCase(sl()));
  sl.registerFactory(() => AddProductUseCase(sl()));
  sl.registerFactory(() => UpdateProductUseCase(sl()));
  sl.registerFactory(() => DeleteProductUseCase(sl()));
  sl.registerFactory(() => AdjustStockUseCase(sl()));
  
  // Cubit
  sl.registerFactory(() => InventoryCubit(
    getProductsUseCase: sl(),
    addProductUseCase: sl(),
    updateProductUseCase: sl(),
    deleteProductUseCase: sl(),
    adjustStockUseCase: sl(),
  ));
}
```

---

## 🧭 Navigation Setup

### Route Configuration

```dart
// lib/core/routers/app_router.dart
import 'package:go_router/go_router.dart';

final appRouter = GoRouter(
  initialLocation: '/pos',
  routes: [
    ShellRoute(
      builder: (context, state, child) {
        return MainShell(child: child);
      },
      routes: [
        GoRoute(
          path: '/pos',
          name: 'pos',
          builder: (context, state) => const POSScreen(),
        ),
        GoRoute(
          path: '/inventory',
          name: 'inventory',
          builder: (context, state) => const InventoryScreen(),
        ),
        GoRoute(
          path: '/bill-scanner',
          name: 'billScanner',
          builder: (context, state) => const BillScannerScreen(),
        ),
        GoRoute(
          path: '/revenue',
          name: 'revenue',
          builder: (context, state) => const RevenueScreen(),
        ),
        GoRoute(
          path: '/tax-calculator',
          name: 'taxCalculator',
          builder: (context, state) => const TaxCalculatorScreen(),
        ),
        GoRoute(
          path: '/settings',
          name: 'settings',
          builder: (context, state) => const SettingsScreen(),
        ),
      ],
    ),
  ],
);
```

### Main Shell (Sidebar Layout)

```dart
// lib/commons/widgets/main_shell.dart
class MainShell extends StatelessWidget {
  final Widget child;
  
  const MainShell({required this.child});
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Row(
        children: [
          const POSSidebar(),
          Expanded(child: child),
        ],
      ),
    );
  }
}
```

---

## 🔄 State Management Patterns

### Cubit Structure

```dart
// lib/features/inventory/presentation/cubit/inventory_cubit.dart
import 'package:flutter_bloc/flutter_bloc.dart';

class InventoryCubit extends Cubit<InventoryState> {
  final GetProductsUseCase getProductsUseCase;
  final AddProductUseCase addProductUseCase;
  final UpdateProductUseCase updateProductUseCase;
  final DeleteProductUseCase deleteProductUseCase;
  final AdjustStockUseCase adjustStockUseCase;
  
  InventoryCubit({
    required this.getProductsUseCase,
    required this.addProductUseCase,
    required this.updateProductUseCase,
    required this.deleteProductUseCase,
    required this.adjustStockUseCase,
  }) : super(const InventoryState.initial());
  
  Future<void> loadProducts({String? categoryId, String? searchQuery}) async {
    emit(state.copyWith(status: InventoryStatus.loading));
    
    try {
      final products = await getProductsUseCase.call(
        categoryId: categoryId,
        searchQuery: searchQuery,
      );
      
      emit(state.copyWith(
        status: InventoryStatus.loaded,
        products: products,
      ));
    } catch (e) {
      emit(state.copyWith(
        status: InventoryStatus.error,
        errorMessage: e.toString(),
      ));
    }
  }
  
  Future<void> addProduct(Product product) async {
    emit(state.copyWith(status: InventoryStatus.loading));
    
    try {
      await addProductUseCase.call(product);
      await loadProducts();
    } catch (e) {
      emit(state.copyWith(
        status: InventoryStatus.error,
        errorMessage: e.toString(),
      ));
    }
  }
  
  // ... other methods
}
```

### State Class with Freezed

```dart
// lib/features/inventory/presentation/cubit/inventory_state.dart
import 'package:freezed_annotation/freezed_annotation.dart';

part 'inventory_state.freezed.dart';

enum InventoryStatus { initial, loading, loaded, error }

@freezed
class InventoryState with _$InventoryState {
  const factory InventoryState({
    @Default(InventoryStatus.initial) InventoryStatus status,
    @Default([]) List<Product> products,
    @Default([]) List<Category> categories,
    String? selectedCategoryId,
    String? searchQuery,
    String? errorMessage,
  }) = _InventoryState;
  
  const factory InventoryState.initial() = _Initial;
}
```

---

## 📝 Model Definitions

### Product Model with Freezed

```dart
// lib/features/inventory/domain/models/product.dart
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:freezed_annotation/freezed_annotation.dart';

part 'product.freezed.dart';
part 'product.g.dart';

@freezed
class Product with _$Product {
  const factory Product({
    String? id,
    required String storeId,
    required String name,
    String? description,
    String? barcode,
    String? imageUrl,
    required int price,
    required String unit,
    required int quantity,
    @Default(10) int lowStockThreshold,
    required String categoryId,
    required String categoryName,
    @Default(true) bool isActive,
    DateTime? createdAt,
    DateTime? updatedAt,
    String? createdBy,
  }) = _Product;
  
  factory Product.fromJson(Map<String, dynamic> json) => 
      _$ProductFromJson(json);
  
  factory Product.fromFirestore(DocumentSnapshot doc) {
    final data = doc.data() as Map<String, dynamic>;
    return Product.fromJson({
      'id': doc.id,
      ...data,
      'createdAt': (data['createdAt'] as Timestamp?)?.toDate().toIso8601String(),
      'updatedAt': (data['updatedAt'] as Timestamp?)?.toDate().toIso8601String(),
    });
  }
}

extension ProductX on Product {
  Map<String, dynamic> toFirestore() {
    return {
      'storeId': storeId,
      'name': name,
      'description': description,
      'barcode': barcode,
      'imageUrl': imageUrl,
      'price': price,
      'unit': unit,
      'quantity': quantity,
      'lowStockThreshold': lowStockThreshold,
      'categoryId': categoryId,
      'categoryName': categoryName,
      'isActive': isActive,
      'createdAt': createdAt != null ? Timestamp.fromDate(createdAt!) : FieldValue.serverTimestamp(),
      'updatedAt': FieldValue.serverTimestamp(),
      'createdBy': createdBy,
    };
  }
  
  bool get isLowStock => quantity <= lowStockThreshold;
  bool get isOutOfStock => quantity <= 0;
}
```

---

## 🔐 Security & Authentication

### Anonymous Authentication Flow

```dart
// lib/core/services/auth_service.dart
class AuthService {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  
  Future<User?> signInAnonymously() async {
    try {
      final result = await _auth.signInAnonymously();
      return result.user;
    } catch (e) {
      throw AuthException('Failed to sign in: $e');
    }
  }
  
  User? get currentUser => _auth.currentUser;
  
  Stream<User?> get authStateChanges => _auth.authStateChanges();
}
```

### Store Initialization

```dart
// lib/core/services/store_service.dart
class StoreService {
  final FirebaseFirestore _firestore;
  
  Future<Store> initializeStore(String userId, String storeName) async {
    final storeRef = _firestore.collection('stores').doc();
    
    final store = Store(
      id: storeRef.id,
      ownerId: userId,
      name: storeName,
      currentOrderNumber: 1000,
      createdAt: DateTime.now(),
      updatedAt: DateTime.now(),
    );
    
    await storeRef.set(store.toFirestore());
    
    // Update user with storeId
    await _firestore.collection('users').doc(userId).update({
      'storeId': store.id,
      'storeName': storeName,
    });
    
    return store;
  }
}
```

---

## 📱 Platform Configuration

### Android Configuration

```kotlin
// android/app/build.gradle.kts
android {
    compileSdk = 35
    
    defaultConfig {
        minSdk = 24
        targetSdk = 35
        
        // Tablet optimization
        resConfigs("vi", "en")
    }
    
    buildFeatures {
        viewBinding = true
    }
}
```

### iOS Configuration

```xml
<!-- ios/Runner/Info.plist -->
<dict>
    <!-- Camera Permission (for bill scanner) -->
    <key>NSCameraUsageDescription</key>
    <string>Ứng dụng cần quyền truy cập camera để quét hóa đơn</string>
    
    <!-- Photo Library Permission -->
    <key>NSPhotoLibraryUsageDescription</key>
    <string>Ứng dụng cần quyền truy cập thư viện ảnh để chọn hình sản phẩm</string>
    
    <!-- iPad Multitasking -->
    <key>UISupportsFullScreen</key>
    <true/>
</dict>
```

---

## 🧪 Testing Strategy

### Unit Tests Example

```dart
// test/features/inventory/domain/usecases/add_product_usecase_test.dart
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';

class MockInventoryRepository extends Mock implements IInventoryRepository {}

void main() {
  late AddProductUseCase useCase;
  late MockInventoryRepository mockRepository;
  
  setUp(() {
    mockRepository = MockInventoryRepository();
    useCase = AddProductUseCase(mockRepository);
  });
  
  test('should add product to repository', () async {
    // Arrange
    final product = Product(
      storeId: 'store1',
      name: 'Test Product',
      price: 10000,
      unit: 'kg',
      quantity: 50,
      categoryId: 'cat1',
      categoryName: 'Test',
    );
    
    when(() => mockRepository.addProduct(any()))
        .thenAnswer((_) async => product.copyWith(id: 'new_id'));
    
    // Act
    final result = await useCase.call(product);
    
    // Assert
    expect(result.id, 'new_id');
    verify(() => mockRepository.addProduct(product)).called(1);
  });
}
```

### Cubit Tests Example

```dart
// test/features/inventory/presentation/cubit/inventory_cubit_test.dart
import 'package:bloc_test/bloc_test.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';

void main() {
  late InventoryCubit cubit;
  late MockGetProductsUseCase mockGetProducts;
  
  setUp(() {
    mockGetProducts = MockGetProductsUseCase();
    cubit = InventoryCubit(getProductsUseCase: mockGetProducts);
  });
  
  blocTest<InventoryCubit, InventoryState>(
    'emits [loading, loaded] when loadProducts succeeds',
    build: () {
      when(() => mockGetProducts.call())
          .thenAnswer((_) async => [testProduct]);
      return cubit;
    },
    act: (cubit) => cubit.loadProducts(),
    expect: () => [
      const InventoryState(status: InventoryStatus.loading),
      InventoryState(
        status: InventoryStatus.loaded,
        products: [testProduct],
      ),
    ],
  );
}
```

---

## 📊 Monitoring & Analytics

### Firebase Analytics Events

```dart
// lib/core/services/analytics_service.dart
class AnalyticsService {
  final FirebaseAnalytics _analytics = FirebaseAnalytics.instance;
  
  Future<void> logProductAdded(Product product) async {
    await _analytics.logEvent(
      name: 'product_added',
      parameters: {
        'product_id': product.id,
        'product_name': product.name,
        'category': product.categoryName,
        'price': product.price,
      },
    );
  }
  
  Future<void> logOrderCompleted(Order order) async {
    await _analytics.logEvent(
      name: 'order_completed',
      parameters: {
        'order_id': order.id,
        'order_number': order.orderNumber,
        'total': order.total,
        'item_count': order.itemCount,
        'payment_method': order.paymentMethod,
      },
    );
  }
  
  Future<void> logScreenView(String screenName) async {
    await _analytics.logScreenView(screenName: screenName);
  }
}
```

### Crashlytics Error Logging

```dart
// lib/core/services/error_service.dart
class ErrorService {
  static Future<void> recordError(
    dynamic error,
    StackTrace? stackTrace, {
    String? reason,
    bool fatal = false,
  }) async {
    await FirebaseCrashlytics.instance.recordError(
      error,
      stackTrace,
      reason: reason,
      fatal: fatal,
    );
  }
  
  static Future<void> setUserContext(String userId, String storeId) async {
    await FirebaseCrashlytics.instance.setUserIdentifier(userId);
    await FirebaseCrashlytics.instance.setCustomKey('store_id', storeId);
  }
}
```

---

## 🔄 Offline Sync Strategy

### Sync Service

```dart
// lib/core/services/sync_service.dart
class SyncService {
  final FirebaseFirestore _firestore;
  final ConnectivityResult _connectivity;
  
  StreamSubscription? _connectivitySubscription;
  
  void startSyncMonitoring() {
    _connectivitySubscription = Connectivity()
        .onConnectivityChanged
        .listen(_handleConnectivityChange);
  }
  
  void _handleConnectivityChange(List<ConnectivityResult> result) {
    if (result.contains(ConnectivityResult.wifi) || 
        result.contains(ConnectivityResult.mobile)) {
      _syncPendingChanges();
    }
  }
  
  Future<void> _syncPendingChanges() async {
    // Firestore handles this automatically with offline persistence
    // This is for custom sync logic if needed
  }
  
  void dispose() {
    _connectivitySubscription?.cancel();
  }
}
```

---

## 📂 Complete Project Structure

```
lib/
├── main.dart
├── app_export.dart
├── core/
│   ├── di/
│   │   └── injection.dart
│   ├── services/
│   │   ├── firestore_service.dart
│   │   ├── auth_service.dart
│   │   ├── store_service.dart
│   │   ├── analytics_service.dart
│   │   ├── error_service.dart
│   │   └── sync_service.dart
│   ├── routers/
│   │   ├── app_router.dart
│   │   └── routes.dart
│   ├── theme/
│   │   ├── app_colors.dart
│   │   ├── app_text_styles.dart
│   │   ├── app_spacing.dart
│   │   └── app_theme.dart
│   └── utils/
│       ├── currency_formatter.dart
│       ├── date_formatter.dart
│       └── validators.dart
├── commons/
│   ├── widgets/
│   │   ├── main_shell.dart
│   │   ├── pos_sidebar.dart
│   │   ├── pos_header.dart
│   │   ├── pos_primary_button.dart
│   │   ├── pos_text_field.dart
│   │   ├── category_chip.dart
│   │   ├── pos_dialog.dart
│   │   ├── loading_overlay.dart
│   │   ├── error_widget.dart
│   │   └── empty_state.dart
│   └── extensions/
│       ├── context_extensions.dart
│       └── string_extensions.dart
├── features/
│   ├── inventory/
│   │   ├── data/
│   │   ├── domain/
│   │   ├── presentation/
│   │   └── inventory_inject.dart
│   ├── pos/
│   │   ├── data/
│   │   ├── domain/
│   │   ├── presentation/
│   │   └── pos_inject.dart
│   ├── revenue/
│   │   ├── data/
│   │   ├── domain/
│   │   ├── presentation/
│   │   └── revenue_inject.dart
│   ├── tax_calculator/
│   │   ├── data/
│   │   ├── domain/
│   │   ├── presentation/
│   │   └── tax_calculator_inject.dart
│   ├── bill_scanner/
│   │   ├── data/
│   │   ├── domain/
│   │   ├── presentation/
│   │   └── bill_scanner_inject.dart
│   ├── settings/
│   │   ├── data/
│   │   ├── domain/
│   │   ├── presentation/
│   │   └── settings_inject.dart
│   └── main_app/
│       ├── data/
│       ├── domain/
│       ├── presentation/
│       └── main_app_inject.dart
└── configs/
    └── app_config.dart
```
