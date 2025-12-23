# 🗺️ Implementation Roadmap

## Overview

This document outlines the phased implementation plan for the Chợ Số POS application, with detailed milestones, deliverables, and acceptance criteria.

---

## 📅 Project Timeline

| Phase | Duration | Focus Area |
|-------|----------|------------|
| Phase 1 | Week 1-2 | Foundation & Core Setup |
| Phase 2 | Week 3-4 | Inventory Management |
| Phase 3 | Week 5-6 | Point of Sale |
| Phase 4 | Week 7-8 | Revenue & Reports |
| Phase 5 | Week 9 | Tax Calculator |
| Phase 6 | Week 10 | Bill Scanner |
| Phase 7 | Week 11-12 | Polish & Testing |

**Total Duration**: 12 Weeks

---

## 🏗️ Phase 1: Foundation & Core Setup (Week 1-2)

### Goals
- Set up project architecture
- Configure Firebase services
- Implement core UI components
- Establish theming system

### Deliverables

#### Week 1: Project Setup

| Task | Priority | Effort | Status |
|------|----------|--------|--------|
| Configure Firebase (Firestore, Auth, Analytics) | High | 4h | ⬜ |
| Set up project structure following Clean Architecture | High | 4h | ⬜ |
| Create AppColors, AppTextStyles, AppTheme | High | 4h | ⬜ |
| Implement light/dark theme switching | Medium | 2h | ⬜ |
| Set up go_router with base routes | High | 3h | ⬜ |
| Configure flutter_bloc with GetIt | High | 3h | ⬜ |
| Add flutter_screenutil for tablet scaling | Medium | 2h | ⬜ |

#### Week 2: Core Components

| Task | Priority | Effort | Status |
|------|----------|--------|--------|
| Create POSSidebar widget | High | 4h | ⬜ |
| Create POSHeader widget | High | 3h | ⬜ |
| Create POSPrimaryButton widget | High | 2h | ⬜ |
| Create CategoryChip widget | High | 2h | ⬜ |
| Create POSDialog/Modal component | Medium | 3h | ⬜ |
| Create POSTextField component | Medium | 2h | ⬜ |
| Create base screen layout template | High | 3h | ⬜ |
| Implement main navigation flow | High | 4h | ⬜ |

### Acceptance Criteria
- [ ] App launches without errors on iPad/Android tablet
- [ ] Navigation between 5 main sections works
- [ ] Theme switching works correctly
- [ ] All base components render properly
- [ ] Firebase connection is established

### File Structure After Phase 1
```
lib/
├── main.dart
├── app_export.dart
├── core/
│   ├── services/
│   │   └── firestore_service.dart
│   ├── routers/
│   │   ├── app_router.dart
│   │   └── routes.dart
│   └── theme/
│       ├── app_colors.dart
│       ├── app_text_styles.dart
│       ├── app_spacing.dart
│       └── app_theme.dart
├── commons/
│   └── widgets/
│       ├── pos_sidebar.dart
│       ├── pos_header.dart
│       ├── pos_primary_button.dart
│       ├── pos_text_field.dart
│       ├── category_chip.dart
│       └── pos_dialog.dart
└── features/
    └── main_app/
```

---

## 📦 Phase 2: Inventory Management (Week 3-4)

### Goals
- Complete product CRUD operations
- Implement category management
- Build inventory UI with search & filter

### Deliverables

#### Week 3: Backend & Domain

| Task | Priority | Effort | Status |
|------|----------|--------|--------|
| Create Product domain model | High | 2h | ⬜ |
| Create Category domain model | High | 1h | ⬜ |
| Implement IInventoryRepository interface | High | 2h | ⬜ |
| Create InventoryRepository (Firestore) | High | 4h | ⬜ |
| Implement GetProductsUseCase | High | 2h | ⬜ |
| Implement AddProductUseCase | High | 2h | ⬜ |
| Implement UpdateProductUseCase | High | 2h | ⬜ |
| Implement DeleteProductUseCase | High | 2h | ⬜ |
| Implement AdjustStockUseCase | High | 2h | ⬜ |
| Create InventoryCubit with states | High | 4h | ⬜ |
| Set up dependency injection | High | 2h | ⬜ |

#### Week 4: UI Implementation

| Task | Priority | Effort | Status |
|------|----------|--------|--------|
| Create ProductCardInventory widget | High | 3h | ⬜ |
| Create ProductGrid widget | High | 3h | ⬜ |
| Create InventoryStatsBar widget | High | 2h | ⬜ |
| Create AddProductDialog | High | 4h | ⬜ |
| Create EditProductDialog | High | 3h | ⬜ |
| Create DeleteConfirmDialog | Medium | 1h | ⬜ |
| Create CategoryFilter widget | High | 2h | ⬜ |
| Implement search functionality | High | 3h | ⬜ |
| Implement quick stock adjustment | High | 2h | ⬜ |
| Build complete InventoryScreen | High | 4h | ⬜ |
| Seed default categories | Medium | 1h | ⬜ |

### Acceptance Criteria
- [ ] Can add new products with all fields
- [ ] Can edit existing products
- [ ] Can delete products (with confirmation)
- [ ] Can adjust stock with +/- buttons
- [ ] Search filters products in real-time
- [ ] Category filter works correctly
- [ ] Low stock warning shows when quantity < threshold
- [ ] Total inventory value displays correctly
- [ ] Data persists to Firestore

### File Structure After Phase 2
```
lib/features/inventory/
├── data/
│   └── repositories/
│       └── inventory_repository_impl.dart
├── domain/
│   ├── models/
│   │   ├── product.dart
│   │   ├── product.g.dart
│   │   └── category.dart
│   ├── repositories/
│   │   └── i_inventory_repository.dart
│   └── usecases/
│       ├── get_products_usecase.dart
│       ├── add_product_usecase.dart
│       ├── update_product_usecase.dart
│       ├── delete_product_usecase.dart
│       └── adjust_stock_usecase.dart
├── presentation/
│   ├── cubit/
│   │   ├── inventory_cubit.dart
│   │   └── inventory_state.dart
│   ├── screens/
│   │   └── inventory_screen.dart
│   └── widgets/
│       ├── product_card_inventory.dart
│       ├── add_product_dialog.dart
│       ├── category_filter.dart
│       └── inventory_stats_bar.dart
└── inventory_inject.dart
```

---

## 🛒 Phase 3: Point of Sale (Week 5-6)

### Goals
- Build complete POS selling interface
- Implement cart management
- Create checkout flow

### Deliverables

#### Week 5: Cart & Order Logic

| Task | Priority | Effort | Status |
|------|----------|--------|--------|
| Create Order domain model | High | 2h | ⬜ |
| Create OrderItem domain model | High | 1h | ⬜ |
| Create Cart state management | High | 3h | ⬜ |
| Implement IOrderRepository interface | High | 2h | ⬜ |
| Create OrderRepository (Firestore) | High | 4h | ⬜ |
| Implement AddToCartUseCase | High | 2h | ⬜ |
| Implement UpdateCartItemUseCase | High | 2h | ⬜ |
| Implement RemoveFromCartUseCase | High | 1h | ⬜ |
| Implement CreateOrderUseCase | High | 3h | ⬜ |
| Create POSCubit with states | High | 4h | ⬜ |
| Create CartCubit with states | High | 3h | ⬜ |

#### Week 6: POS UI

| Task | Priority | Effort | Status |
|------|----------|--------|--------|
| Create ProductCardPOS widget | High | 3h | ⬜ |
| Create ProductGridPOS widget | High | 3h | ⬜ |
| Create CartPanel widget | High | 4h | ⬜ |
| Create CartItemTile widget | High | 3h | ⬜ |
| Create OrderSummary widget | High | 2h | ⬜ |
| Create PaymentDialog | High | 4h | ⬜ |
| Create QuantitySelector widget | Medium | 2h | ⬜ |
| Implement quick amount buttons | Medium | 2h | ⬜ |
| Implement change calculation | High | 1h | ⬜ |
| Build complete POSScreen | High | 4h | ⬜ |
| Add barcode scanner integration | Medium | 3h | ⬜ |

### Acceptance Criteria
- [ ] Can browse products in grid view
- [ ] Can add products to cart by tapping
- [ ] Cart updates in real-time
- [ ] Can adjust quantity in cart
- [ ] Can remove items from cart
- [ ] Order total calculates correctly
- [ ] Can complete payment with cash
- [ ] Change is calculated correctly
- [ ] Order saves to Firestore
- [ ] Product stock decreases after sale
- [ ] New order number generates correctly

---

## 📊 Phase 4: Revenue & Reports (Week 7-8)

### Goals
- Implement revenue tracking
- Build analytics dashboard
- Create data aggregation

### Deliverables

#### Week 7: Revenue Backend

| Task | Priority | Effort | Status |
|------|----------|--------|--------|
| Create DailyRevenue domain model | High | 2h | ⬜ |
| Create RevenueSummary model | High | 1h | ⬜ |
| Create TopProduct model | High | 1h | ⬜ |
| Implement IRevenueRepository interface | High | 2h | ⬜ |
| Create RevenueRepository (Firestore) | High | 4h | ⬜ |
| Create revenue aggregation logic | High | 4h | ⬜ |
| Implement GetDailyRevenueUseCase | High | 2h | ⬜ |
| Implement GetRevenueRangeUseCase | High | 3h | ⬜ |
| Implement GetTopProductsUseCase | High | 2h | ⬜ |
| Create RevenueCubit with states | High | 4h | ⬜ |

#### Week 8: Revenue UI

| Task | Priority | Effort | Status |
|------|----------|--------|--------|
| Create RevenueSummaryCards widget | High | 3h | ⬜ |
| Create RevenueChart widget (line chart) | High | 4h | ⬜ |
| Create TopProductsList widget | High | 3h | ⬜ |
| Create RecentOrdersList widget | High | 3h | ⬜ |
| Create DateRangePicker widget | High | 3h | ⬜ |
| Create PeriodSelector widget | Medium | 2h | ⬜ |
| Build complete RevenueScreen | High | 4h | ⬜ |
| Implement comparison feature | Medium | 3h | ⬜ |
| Add export functionality | Low | 2h | ⬜ |

### Acceptance Criteria
- [ ] Shows today's revenue summary
- [ ] Can select date range
- [ ] Chart displays revenue over time
- [ ] Top selling products display correctly
- [ ] Recent orders are listed
- [ ] Comparison to previous period works
- [ ] Data updates in real-time

---

## 🧮 Phase 5: Tax Calculator (Week 9)

### Goals
- Implement Vietnamese tax rules
- Build tax calculation interface
- Create tax history

### Deliverables

| Task | Priority | Effort | Status |
|------|----------|--------|--------|
| Create TaxCalculation model | High | 2h | ⬜ |
| Create TaxBracket model | High | 1h | ⬜ |
| Create TaxRecord model | High | 1h | ⬜ |
| Implement Vietnamese tax rules | High | 4h | ⬜ |
| Create ITaxRepository interface | High | 1h | ⬜ |
| Create TaxRepository (Firestore) | High | 3h | ⬜ |
| Implement CalculateBusinessLicenseTaxUseCase | High | 2h | ⬜ |
| Implement CalculateIncomeTaxUseCase | High | 3h | ⬜ |
| Create TaxCalculatorCubit | High | 3h | ⬜ |
| Create RevenueInput widget | High | 2h | ⬜ |
| Create TaxBreakdown widget | High | 3h | ⬜ |
| Create TaxInfoCard widget | Medium | 2h | ⬜ |
| Build complete TaxCalculatorScreen | High | 4h | ⬜ |
| Add "fetch from revenue" feature | Medium | 2h | ⬜ |
| Add save/export functionality | Medium | 2h | ⬜ |

### Acceptance Criteria
- [ ] Can input monthly revenue
- [ ] Can fetch revenue from actual sales
- [ ] Business license tax calculates correctly
- [ ] Personal income tax calculates correctly
- [ ] Tax breakdown displays clearly
- [ ] Can save tax records
- [ ] Historical records are viewable

---

## 📷 Phase 6: Bill Scanner (Week 10)

### Goals
- Implement camera capture
- Add OCR functionality
- Create bulk import flow

### Deliverables

| Task | Priority | Effort | Status |
|------|----------|--------|--------|
| Create BillImport model | High | 2h | ⬜ |
| Create ExtractedItem model | High | 1h | ⬜ |
| Set up camera permissions | High | 1h | ⬜ |
| Integrate Google ML Kit OCR | High | 4h | ⬜ |
| Implement text parsing logic | High | 4h | ⬜ |
| Create product matching algorithm | High | 3h | ⬜ |
| Create IBillScannerRepository interface | High | 1h | ⬜ |
| Create BillScannerRepository | High | 3h | ⬜ |
| Create BillScannerCubit | High | 3h | ⬜ |
| Create CameraPreview widget | High | 3h | ⬜ |
| Create ExtractedItemTile widget | High | 2h | ⬜ |
| Create ReviewImportScreen | High | 4h | ⬜ |
| Build complete BillScannerScreen | High | 4h | ⬜ |
| Add image enhancement | Low | 2h | ⬜ |

### Acceptance Criteria
- [ ] Camera opens and displays preview
- [ ] Can capture image of bill
- [ ] OCR extracts text from image
- [ ] Parser identifies products, quantities, prices
- [ ] Matches to existing products when possible
- [ ] Can edit extracted data
- [ ] Can import products to inventory
- [ ] Stock updates correctly

---

## ✨ Phase 7: Polish & Testing (Week 11-12)

### Goals
- UI/UX refinements
- Performance optimization
- Bug fixes and testing
- Final preparation for release

### Deliverables

#### Week 11: Polish

| Task | Priority | Effort | Status |
|------|----------|--------|--------|
| Add loading states/skeletons | High | 4h | ⬜ |
| Add error handling UI | High | 3h | ⬜ |
| Add empty states | High | 3h | ⬜ |
| Implement offline mode | High | 6h | ⬜ |
| Add pull-to-refresh | Medium | 2h | ⬜ |
| Add haptic feedback | Low | 1h | ⬜ |
| Optimize Firestore queries | High | 4h | ⬜ |
| Add image caching | Medium | 2h | ⬜ |
| Implement data sync indicator | Medium | 2h | ⬜ |
| Add success/confirmation animations | Low | 3h | ⬜ |

#### Week 12: Testing & Release

| Task | Priority | Effort | Status |
|------|----------|--------|--------|
| Write unit tests for UseCases | High | 6h | ⬜ |
| Write widget tests for key components | High | 6h | ⬜ |
| Manual testing on iPad | High | 4h | ⬜ |
| Manual testing on Android tablet | High | 4h | ⬜ |
| Fix identified bugs | High | 8h | ⬜ |
| Performance profiling | Medium | 3h | ⬜ |
| Create user documentation | Medium | 4h | ⬜ |
| Prepare app store assets | Medium | 3h | ⬜ |
| Final build and submission | High | 2h | ⬜ |

### Acceptance Criteria
- [ ] No critical bugs
- [ ] App performs smoothly on target devices
- [ ] Offline mode works correctly
- [ ] All features tested and working
- [ ] User feedback incorporated
- [ ] Ready for app store submission

---

## 📋 Risk Management

### Identified Risks

| Risk | Impact | Probability | Mitigation |
|------|--------|-------------|------------|
| OCR accuracy issues | High | Medium | Provide manual edit option, improve preprocessing |
| Firestore costs | Medium | Medium | Optimize queries, implement local caching |
| Tablet compatibility | High | Low | Test on multiple devices early |
| Offline sync conflicts | High | Medium | Use timestamps, last-write-wins strategy |
| Performance with large inventory | Medium | Medium | Pagination, lazy loading |

---

## 📊 Progress Tracking

### Weekly Standup Template
- What was completed last week?
- What's planned for this week?
- Any blockers or risks?

### Definition of Done
- Code is complete and reviewed
- Unit tests pass
- Manual testing on tablet complete
- No critical bugs
- Documentation updated

---

## 🚀 Post-Launch Roadmap

### Version 1.1 (Future)
- Multi-language support expansion
- Print receipt integration
- Customer loyalty program
- Multiple payment methods

### Version 1.2 (Future)
- Multi-store support
- Staff management
- Detailed analytics
- Integration with accounting software

### Version 2.0 (Future)
- Online ordering integration
- Delivery management
- Supplier management
- AI-powered inventory predictions
