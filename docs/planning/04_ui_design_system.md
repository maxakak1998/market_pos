# 🎨 UI/UX Design System

## Overview

This document defines the tablet-optimized UI/UX design system for Chợ Số POS application, based on Material 3 guidelines with custom theming.

---

## 🎨 Color System

### Primary Palette

```dart
// Primary Orange - Main brand color
static const Color primary = Color(0xFFF48525);
static const Color primaryLight = Color(0xFFFF9D4D);
static const Color primaryDark = Color(0xFFD96E10);
static const Color primaryContent = Color(0xFFFFFFFF);

// Orange shadows
static const Color primaryShadow = Color(0x4DF48525); // 30% opacity
```

### Neutral Palette

```dart
// Light Theme Neutrals
static const Color backgroundLight = Color(0xFFF8F7F5);
static const Color surfaceLight = Color(0xFFFFFFFF);
static const Color itemLight = Color(0xFFF4EDE7);
static const Color borderLight = Color(0xFFE6DBD0);

// Dark Theme Neutrals
static const Color backgroundDark = Color(0xFF221810);
static const Color surfaceDark = Color(0xFF2A2018);
static const Color itemDark = Color(0xFF3C2F25);
static const Color borderDark = Color(0xFF4A3A2F);
```

### Text Colors

```dart
// Light Theme Text
static const Color textMain = Color(0xFF1C140D);
static const Color textSecondary = Color(0xFF9C7049);
static const Color textHint = Color(0xFFB8956A);

// Dark Theme Text
static const Color textMainDark = Color(0xFFEDE0D4);
static const Color textSecondaryDark = Color(0xFFC4A488);
```

### Semantic Colors

```dart
// Success
static const Color success = Color(0xFF22C55E);
static const Color successLight = Color(0xFFDCFCE7);
static const Color successDark = Color(0xFF166534);

// Warning
static const Color warning = Color(0xFFFBBF24);
static const Color warningLight = Color(0xFFFEF3C7);
static const Color warningDark = Color(0xFF92400E);

// Error
static const Color error = Color(0xFFEF4444);
static const Color errorLight = Color(0xFFFEE2E2);
static const Color errorDark = Color(0xFFB91C1C);

// Info
static const Color info = Color(0xFF3B82F6);
static const Color infoLight = Color(0xFFDBEAFE);
static const Color infoDark = Color(0xFF1D4ED8);
```

### Color Usage in Flutter

```dart
// lib/core/theme/app_colors.dart
class AppColors {
  // Light Theme
  static const lightColorScheme = ColorScheme(
    brightness: Brightness.light,
    primary: Color(0xFFF48525),
    onPrimary: Colors.white,
    secondary: Color(0xFF9C7049),
    onSecondary: Colors.white,
    background: Color(0xFFF8F7F5),
    onBackground: Color(0xFF1C140D),
    surface: Colors.white,
    onSurface: Color(0xFF1C140D),
    error: Color(0xFFEF4444),
    onError: Colors.white,
  );
  
  // Dark Theme
  static const darkColorScheme = ColorScheme(
    brightness: Brightness.dark,
    primary: Color(0xFFF48525),
    onPrimary: Colors.white,
    secondary: Color(0xFFC4A488),
    onSecondary: Color(0xFF1C140D),
    background: Color(0xFF221810),
    onBackground: Color(0xFFEDE0D4),
    surface: Color(0xFF2A2018),
    onSurface: Color(0xFFEDE0D4),
    error: Color(0xFFF87171),
    onError: Color(0xFF1C140D),
  );
}
```

---

## 📝 Typography

### Font Family

```dart
// Primary: Inter (for UI elements)
// Secondary: Roboto (for body text)

static const String fontFamily = 'Inter';
static const String fontFamilyBody = 'Roboto';
```

### Type Scale

| Style | Size | Weight | Line Height | Usage |
|-------|------|--------|-------------|-------|
| Display Large | 32sp | Black (900) | 1.2 | Main headers |
| Display Medium | 28sp | Bold (700) | 1.25 | Section titles |
| Headline Large | 24sp | Bold (700) | 1.3 | Screen titles |
| Headline Medium | 20sp | Bold (700) | 1.35 | Card titles |
| Title Large | 18sp | SemiBold (600) | 1.4 | Sub-headers |
| Title Medium | 16sp | SemiBold (600) | 1.4 | List items |
| Body Large | 16sp | Regular (400) | 1.5 | Primary body |
| Body Medium | 14sp | Regular (400) | 1.5 | Secondary body |
| Label Large | 14sp | Bold (700) | 1.4 | Button labels |
| Label Medium | 12sp | Bold (700) | 1.4 | Badges, chips |
| Caption | 11sp | Medium (500) | 1.3 | Helper text |

### Text Styles in Flutter

```dart
// lib/core/theme/app_text_styles.dart
class AppTextStyles {
  static const TextStyle displayLarge = TextStyle(
    fontFamily: 'Inter',
    fontSize: 32,
    fontWeight: FontWeight.w900,
    height: 1.2,
    letterSpacing: -0.5,
  );
  
  static const TextStyle headlineLarge = TextStyle(
    fontFamily: 'Inter',
    fontSize: 24,
    fontWeight: FontWeight.w700,
    height: 1.3,
  );
  
  static const TextStyle titleMedium = TextStyle(
    fontFamily: 'Inter',
    fontSize: 16,
    fontWeight: FontWeight.w600,
    height: 1.4,
  );
  
  static const TextStyle bodyLarge = TextStyle(
    fontFamily: 'Roboto',
    fontSize: 16,
    fontWeight: FontWeight.w400,
    height: 1.5,
  );
  
  static const TextStyle labelLarge = TextStyle(
    fontFamily: 'Inter',
    fontSize: 14,
    fontWeight: FontWeight.w700,
    height: 1.4,
  );
  
  static const TextStyle priceText = TextStyle(
    fontFamily: 'Inter',
    fontSize: 20,
    fontWeight: FontWeight.w700,
    height: 1.2,
    color: Color(0xFFF48525),
  );
}
```

---

## 📐 Spacing & Layout

### Spacing Scale

```dart
class AppSpacing {
  static const double xs = 4.0;
  static const double sm = 8.0;
  static const double md = 12.0;
  static const double lg = 16.0;
  static const double xl = 24.0;
  static const double xxl = 32.0;
  static const double xxxl = 48.0;
}
```

### Tablet Layout Breakpoints

```dart
class AppBreakpoints {
  static const double tablet = 768.0;      // Min tablet width
  static const double tabletLarge = 1024.0; // Large tablet
  static const double desktop = 1280.0;     // Desktop/Large tablet
}
```

### Standard Dimensions

```dart
class AppDimensions {
  // Sidebar
  static const double sidebarWidth = 280.0;
  static const double sidebarCollapsed = 80.0;
  
  // Cart Panel
  static const double cartPanelWidth = 420.0;
  static const double cartPanelWidthLarge = 480.0;
  
  // Header
  static const double headerHeight = 88.0;
  static const double headerHeightCompact = 64.0;
  
  // Navigation Item
  static const double navItemHeight = 56.0;
  
  // Cards
  static const double productCardMinWidth = 200.0;
  static const double productCardMaxWidth = 280.0;
  
  // Touch Targets
  static const double minTouchTarget = 44.0;
  static const double touchTargetLarge = 56.0;
  
  // Icons
  static const double iconSm = 20.0;
  static const double iconMd = 24.0;
  static const double iconLg = 28.0;
  static const double iconXl = 32.0;
}
```

### Grid System for Products

```dart
// Responsive grid columns for product display
int getProductGridColumns(double screenWidth) {
  if (screenWidth >= 1536) return 5;      // 2xl
  if (screenWidth >= 1280) return 4;      // xl
  if (screenWidth >= 1024) return 3;      // lg
  if (screenWidth >= 768) return 3;       // md
  return 2;                                // sm
}

// Grid item aspect ratio
static const double productCardAspectRatio = 0.75; // width/height
```

---

## 🔲 Border Radius

```dart
class AppRadius {
  static const double xs = 8.0;
  static const double sm = 12.0;
  static const double md = 16.0;    // Default
  static const double lg = 24.0;
  static const double xl = 32.0;
  static const double full = 9999.0; // Circular
  
  // Standard border radius
  static const BorderRadius small = BorderRadius.all(Radius.circular(12));
  static const BorderRadius medium = BorderRadius.all(Radius.circular(16));
  static const BorderRadius large = BorderRadius.all(Radius.circular(24));
  static const BorderRadius pill = BorderRadius.all(Radius.circular(9999));
}
```

---

## 🌓 Shadows & Elevation

```dart
class AppShadows {
  // Light shadows
  static const BoxShadow small = BoxShadow(
    color: Color(0x0D000000),
    blurRadius: 4,
    offset: Offset(0, 2),
  );
  
  static const BoxShadow medium = BoxShadow(
    color: Color(0x14000000),
    blurRadius: 8,
    offset: Offset(0, 4),
  );
  
  static const BoxShadow large = BoxShadow(
    color: Color(0x1F000000),
    blurRadius: 16,
    offset: Offset(0, 8),
  );
  
  // Primary button shadow
  static const BoxShadow primaryButton = BoxShadow(
    color: Color(0x4DF48525), // 30% orange
    blurRadius: 12,
    offset: Offset(0, 4),
  );
  
  // Cart panel shadow
  static const BoxShadow cartPanel = BoxShadow(
    color: Color(0x29000000),
    blurRadius: 24,
    offset: Offset(-4, 0),
  );
}
```

---

## 🔘 Components

### 1. Buttons

#### Primary Button (Large)
```dart
class POSPrimaryButton extends StatelessWidget {
  final String label;
  final IconData? icon;
  final VoidCallback? onPressed;
  final bool isLoading;
  
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 56,
      decoration: BoxDecoration(
        color: AppColors.primary,
        borderRadius: AppRadius.large,
        boxShadow: [AppShadows.primaryButton],
      ),
      child: Material(
        color: Colors.transparent,
        child: InkWell(
          onTap: onPressed,
          borderRadius: AppRadius.large,
          child: Padding(
            padding: EdgeInsets.symmetric(horizontal: 24),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                if (icon != null) ...[
                  Icon(icon, color: Colors.white, size: 24),
                  SizedBox(width: 8),
                ],
                Text(
                  label,
                  style: AppTextStyles.labelLarge.copyWith(
                    color: Colors.white,
                    fontSize: 16,
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
```

#### Secondary Button
```dart
// Outlined style with border
Container(
  height: 48,
  decoration: BoxDecoration(
    color: AppColors.surfaceLight,
    borderRadius: AppRadius.medium,
    border: Border.all(color: AppColors.borderLight),
  ),
  // ...
)
```

#### Icon Button (Round)
```dart
// Circular touch target
Container(
  width: 44,
  height: 44,
  decoration: BoxDecoration(
    color: AppColors.itemLight,
    shape: BoxShape.circle,
  ),
  child: Icon(Icons.add, size: 24),
)
```

### 2. Category Chips

```dart
class CategoryChip extends StatelessWidget {
  final String label;
  final String emoji;
  final bool isSelected;
  final VoidCallback onTap;
  
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 48,
      padding: EdgeInsets.symmetric(horizontal: 20),
      decoration: BoxDecoration(
        color: isSelected ? AppColors.primary : AppColors.surfaceLight,
        borderRadius: AppRadius.pill,
        border: isSelected 
          ? null 
          : Border.all(color: AppColors.borderLight),
        boxShadow: isSelected 
          ? [AppShadows.primaryButton] 
          : [AppShadows.small],
      ),
      child: Row(
        mainAxisSize: MainAxisSize.min,
        children: [
          Text(emoji, style: TextStyle(fontSize: 22)),
          SizedBox(width: 8),
          Text(
            label,
            style: AppTextStyles.labelLarge.copyWith(
              color: isSelected ? Colors.white : AppColors.textMain,
            ),
          ),
        ],
      ),
    );
  }
}
```

### 3. Product Card (POS)

```dart
class ProductCardPOS extends StatelessWidget {
  final Product product;
  final VoidCallback onAdd;
  
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        color: AppColors.surfaceLight,
        borderRadius: AppRadius.large,
        boxShadow: [AppShadows.small],
        border: Border.all(color: Colors.transparent),
      ),
      child: Material(
        color: Colors.transparent,
        borderRadius: AppRadius.large,
        child: InkWell(
          onTap: onAdd,
          borderRadius: AppRadius.large,
          child: Padding(
            padding: EdgeInsets.all(16),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                // Product Image
                AspectRatio(
                  aspectRatio: 1,
                  child: Container(
                    decoration: BoxDecoration(
                      color: AppColors.itemLight,
                      borderRadius: AppRadius.medium,
                    ),
                    child: product.imageUrl != null
                      ? Image.network(product.imageUrl!)
                      : Icon(Icons.inventory_2, size: 48),
                  ),
                ),
                SizedBox(height: 12),
                
                // Product Name
                Text(
                  product.name,
                  style: AppTextStyles.titleMedium,
                  maxLines: 1,
                  overflow: TextOverflow.ellipsis,
                ),
                
                Spacer(),
                
                // Price and Add Button
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  crossAxisAlignment: CrossAxisAlignment.end,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          formatCurrency(product.price),
                          style: AppTextStyles.priceText,
                        ),
                        Text(
                          '/${product.unit}',
                          style: AppTextStyles.caption.copyWith(
                            color: AppColors.textSecondary,
                          ),
                        ),
                      ],
                    ),
                    
                    // Add Button
                    Container(
                      width: 44,
                      height: 44,
                      decoration: BoxDecoration(
                        color: AppColors.itemLight,
                        borderRadius: AppRadius.medium,
                      ),
                      child: Icon(Icons.add, color: AppColors.textMain),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
```

### 4. Cart Item

```dart
class CartItemTile extends StatelessWidget {
  final OrderItem item;
  final Function(int) onQuantityChanged;
  final VoidCallback onRemove;
  
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(12),
      decoration: BoxDecoration(
        color: AppColors.surfaceLight,
        borderRadius: AppRadius.large,
        boxShadow: [AppShadows.small],
      ),
      child: Row(
        children: [
          // Product Image
          Container(
            width: 80,
            height: 80,
            decoration: BoxDecoration(
              color: AppColors.itemLight,
              borderRadius: AppRadius.medium,
            ),
          ),
          SizedBox(width: 12),
          
          // Item Info
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(item.productName, style: AppTextStyles.titleMedium),
                    Text(
                      formatCurrency(item.subtotal),
                      style: AppTextStyles.titleMedium.copyWith(
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
                SizedBox(height: 4),
                Text(
                  '${formatCurrency(item.unitPrice)} / ${item.productUnit}',
                  style: AppTextStyles.bodyMedium.copyWith(
                    color: AppColors.textSecondary,
                  ),
                ),
                SizedBox(height: 8),
                
                // Quantity Controls
                Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    _QuantityButton(
                      icon: Icons.remove,
                      onTap: () => onQuantityChanged(item.quantity - 1),
                    ),
                    SizedBox(width: 8),
                    Text(
                      '${item.quantity}',
                      style: AppTextStyles.labelLarge,
                    ),
                    SizedBox(width: 8),
                    _QuantityButton(
                      icon: Icons.add,
                      isPrimary: true,
                      onTap: () => onQuantityChanged(item.quantity + 1),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
```

### 5. Sidebar Navigation

```dart
class POSSidebar extends StatelessWidget {
  final int selectedIndex;
  final Function(int) onItemSelected;
  
  static const navItems = [
    NavItem(icon: Icons.point_of_sale, label: 'Bán hàng (POS)'),
    NavItem(icon: Icons.inventory_2, label: 'Kho sản phẩm'),
    NavItem(icon: Icons.receipt_long, label: 'Nhập từ hóa đơn'),
    NavItem(icon: Icons.monitoring, label: 'Doanh thu'),
    NavItem(icon: Icons.calculate, label: 'Tính thuế'),
  ];
  
  @override
  Widget build(BuildContext context) {
    return Container(
      width: AppDimensions.sidebarWidth,
      color: AppColors.surfaceLight,
      child: Column(
        children: [
          // Logo Header
          Container(
            height: 88,
            padding: EdgeInsets.symmetric(horizontal: 24),
            child: Row(
              children: [
                Container(
                  width: 48,
                  height: 48,
                  decoration: BoxDecoration(
                    color: AppColors.primary,
                    borderRadius: AppRadius.medium,
                    boxShadow: [AppShadows.primaryButton],
                  ),
                  child: Icon(Icons.storefront, color: Colors.white, size: 28),
                ),
                SizedBox(width: 12),
                Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text('Chợ Số', style: AppTextStyles.displayMedium),
                    Text(
                      'Trợ lý bán hàng tiểu thương',
                      style: AppTextStyles.caption.copyWith(
                        color: AppColors.textSecondary,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
          
          // Navigation Items
          Expanded(
            child: ListView.builder(
              padding: EdgeInsets.all(16),
              itemCount: navItems.length,
              itemBuilder: (context, index) {
                final item = navItems[index];
                final isSelected = index == selectedIndex;
                
                return _NavItemWidget(
                  icon: item.icon,
                  label: item.label,
                  isSelected: isSelected,
                  onTap: () => onItemSelected(index),
                );
              },
            ),
          ),
          
          // User Profile
          Padding(
            padding: EdgeInsets.all(16),
            child: UserProfileCard(),
          ),
        ],
      ),
    );
  }
}
```

---

## 📱 Responsive Layouts

### POS Screen Layout

```dart
class POSScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Row(
        children: [
          // Sidebar (fixed)
          POSSidebar(),
          
          // Main Content (flexible)
          Expanded(
            flex: 3,
            child: Column(
              children: [
                POSHeader(),
                CategoryFilter(),
                Expanded(child: ProductGrid()),
              ],
            ),
          ),
          
          // Cart Panel (fixed width)
          Container(
            width: AppDimensions.cartPanelWidth,
            child: CartPanel(),
          ),
        ],
      ),
    );
  }
}
```

### Inventory Screen Layout

```dart
class InventoryScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Row(
        children: [
          POSSidebar(),
          
          Expanded(
            child: Column(
              children: [
                InventoryHeader(),
                CategoryFilter(),
                Expanded(child: ProductInventoryGrid()),
                InventoryStatsBar(),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
```

---

## ♿ Accessibility

### Touch Targets
- Minimum touch target size: 44x44dp
- Recommended for primary actions: 56x56dp
- Spacing between touch targets: minimum 8dp

### Contrast Ratios
- Text on light background: minimum 4.5:1
- Large text (18sp+): minimum 3:1
- Interactive elements: minimum 3:1

### Font Sizes
- Minimum body text: 14sp
- Minimum label text: 12sp
- Never use text smaller than 11sp

### Color Blind Support
- Never rely solely on color to convey information
- Use icons and text labels alongside color indicators
- Low stock: Red color + "Hết hàng" text + warning icon

---

## 🌙 Dark Mode

All components support dark mode by using the defined color tokens:

```dart
// Example: Get current theme color
Color backgroundColor = Theme.of(context).colorScheme.background;
Color textColor = Theme.of(context).colorScheme.onBackground;

// Or use extension method
Color backgroundColor = context.colors.background;
```

### Dark Mode Color Mapping

| Light | Dark |
|-------|------|
| `#F8F7F5` | `#221810` |
| `#FFFFFF` | `#2A2018` |
| `#F4EDE7` | `#3C2F25` |
| `#E6DBD0` | `#4A3A2F` |
| `#1C140D` | `#EDE0D4` |
| `#9C7049` | `#C4A488` |

---

## 📋 Component Checklist

- [x] Primary Button
- [x] Secondary Button
- [x] Icon Button
- [x] Category Chip
- [x] Product Card (POS)
- [x] Product Card (Inventory)
- [x] Cart Item Tile
- [x] Sidebar Navigation
- [x] Header
- [x] Search Field
- [x] Dialog/Modal
- [x] Bottom Sheet
- [x] Snackbar/Toast
- [x] Loading Indicator
- [x] Empty State
- [x] Error State
- [x] Number Input
- [x] Dropdown Select
- [x] Date Picker
- [x] Charts (Revenue)
