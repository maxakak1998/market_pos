# 📋 Chợ Số - POS Manager Project Overview

## 📌 Project Summary

**Chợ Số** (Digital Market) is a tablet-optimized Point of Sale (POS) application designed specifically for small market vendors (tiểu thương) in Vietnam. The app helps vendors manage their inventory, process sales, track revenue, and calculate taxes efficiently.

---

## 🎯 Target Users

- **Primary Users**: Small market vendors (tiểu thương) at traditional Vietnamese markets
- **Device**: Tablet (iPad, Android Tablets) - NOT optimized for mobile phones
- **Technical Level**: Low to Medium - UI must be simple and intuitive

---

## 🌟 Core Features

### 1. 📦 Inventory Management (Kho sản phẩm)
- Add products to inventory with:
  - Product name
  - Unit type (kg, bó, chai, trái, chục, etc.)
  - Quantity in stock
  - Price per unit
  - Category
  - Product image (optional)
- Edit existing products
- Delete products
- Quick stock adjustment (+/-)
- Low stock warnings
- Category filtering

### 2. 🛒 Point of Sale (Bán hàng POS)
- Search products by name or barcode
- Browse products by category with emoji icons
- Add products to cart with quantity
- View cart summary with item count and total
- Customer information (optional)
- Order number tracking
- Quick checkout process
- QR/Barcode scanner support

### 3. 📊 Revenue Tracking (Doanh thu)
- Daily/Weekly/Monthly revenue reports
- Sales history
- Top selling products
- Revenue charts and graphs
- Export reports

### 4. 🧮 Tax Calculator (Tính thuế)
- Vietnamese tax calculation for small businesses
- Personal income tax estimation
- VAT calculation (if applicable)
- Tax reporting assistance

### 5. 📷 Scan to Import (Nhập từ hóa đơn)
- Camera/Scanner to capture bills
- OCR for extracting product information
- Bulk import products from supplier invoices
- Edit and confirm before importing

---

## 🎨 Design System

### Colors (Material 3)
```
Primary:           #F48525 (Orange)
Primary Content:   #FFFFFF
Background Light:  #F8F7F5 / #FCFAF8
Background Dark:   #221810 / #1E1611
Surface Light:     #FFFFFF
Surface Dark:      #2A2018 / #2F2219
Item Light:        #F4EDE7
Item Dark:         #3C2F25
Text Main:         #1C140D
Text Secondary:    #9C7049
```

### Typography
- **Font Family**: Inter (Display), Roboto (Body)
- **Large touch targets**: Minimum 44x44dp for buttons
- **High contrast**: For outdoor/bright light usage

### UI Principles
- **Tablet-first**: Optimized for 10-12" screens
- **Side Navigation**: Fixed 280px sidebar
- **Split View**: Product grid + Cart panel for POS
- **Large Cards**: Easy to tap product cards
- **Category Chips**: Quick filtering with emoji icons

---

## 🗄️ Technology Stack

### Frontend
- **Framework**: Flutter 3.7+
- **State Management**: flutter_bloc (Cubit)
- **Navigation**: go_router
- **Localization**: easy_localization (Vietnamese + English)
- **UI**: Material 3 with custom theming

### Backend & Database
- **Database**: Cloud Firestore
- **Authentication**: Firebase Auth (Anonymous or Phone)
- **Storage**: Firebase Storage (for product images)
- **Analytics**: Firebase Analytics
- **Crash Reporting**: Firebase Crashlytics

### Additional
- **OCR**: Google ML Kit or Firebase ML
- **Barcode Scanning**: Mobile Scanner package
- **Local Storage**: SharedPreferences, flutter_secure_storage

---

## 📁 Project Architecture

Following Clean Architecture with Feature-based structure:

```
lib/
├── main.dart
├── app_export.dart
├── core/
│   ├── api/
│   ├── services/
│   │   ├── firestore/
│   │   ├── storage/
│   │   └── connectivity/
│   ├── routers/
│   └── theme/
│       ├── app_colors.dart
│       ├── app_text_styles.dart
│       └── app_theme.dart
├── commons/
│   ├── widgets/
│   │   ├── pos_sidebar.dart
│   │   ├── pos_header.dart
│   │   ├── product_card.dart
│   │   ├── category_chip.dart
│   │   └── cart_item.dart
│   └── utils/
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
│   └── settings/
│       ├── data/
│       ├── domain/
│       ├── presentation/
│       └── settings_inject.dart
```

---

## 🚀 Development Phases

### Phase 1: Foundation (Week 1-2)
- Project setup and theming
- Firebase integration
- Common widgets (Sidebar, Header, Cards)
- Basic navigation structure

### Phase 2: Inventory Management (Week 3-4)
- Product CRUD operations
- Firestore integration for products
- Category management
- Search functionality

### Phase 3: POS Screen (Week 5-6)
- Product browsing and search
- Cart management
- Order creation and saving
- Basic checkout flow

### Phase 4: Revenue & Reports (Week 7-8)
- Sales data aggregation
- Revenue charts
- Date range filtering
- Export functionality

### Phase 5: Tax Calculator (Week 9)
- Vietnamese tax rules implementation
- Tax calculation UI
- Report generation

### Phase 6: Bill Scanner (Week 10)
- Camera integration
- OCR implementation
- Bulk import flow
- Data validation

### Phase 7: Polish & Testing (Week 11-12)
- UI refinements
- Performance optimization
- User testing
- Bug fixes

---

## 📋 Success Metrics

1. **User Adoption**: Market vendors can set up and use the app independently
2. **Transaction Speed**: Complete a sale in under 30 seconds
3. **Accuracy**: Zero calculation errors in orders and taxes
4. **Reliability**: 99.9% uptime with offline capability
5. **User Satisfaction**: Positive feedback from target users

---

## 📝 Notes

- App should work in outdoor lighting conditions (high contrast)
- Support both left-handed and right-handed users
- Vietnamese language primary, English secondary
- Offline mode for basic POS operations
- Sync when connectivity is restored
