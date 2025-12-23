# 🗄️ Firestore Database Schema

## Overview

This document defines the Firestore database structure for the Chợ Số POS application. The schema is designed for optimal performance with tablet devices and offline-first capabilities.

---

## 📊 Collections Structure

```
firestore/
├── users/                    # User accounts
│   └── {userId}/
│       ├── profile          # User profile data
│       └── settings         # User preferences
│
├── stores/                   # Store/Shop information
│   └── {storeId}/
│       ├── info             # Store details
│       └── stats            # Store statistics
│
├── products/                 # Product inventory
│   └── {productId}/
│       └── (product data)
│
├── categories/              # Product categories
│   └── {categoryId}/
│       └── (category data)
│
├── orders/                  # Sales orders
│   └── {orderId}/
│       ├── (order data)
│       └── items/           # Subcollection
│           └── {itemId}
│
├── daily_revenue/           # Daily aggregated revenue
│   └── {date_storeId}/
│       └── (revenue data)
│
└── tax_records/             # Tax calculation history
    └── {recordId}/
        └── (tax data)
```

---

## 📦 Products Collection

### Document: `/products/{productId}`

```typescript
interface Product {
  id: string;                    // Auto-generated Firestore ID
  storeId: string;               // Reference to store
  
  // Basic Info
  name: string;                  // "Cà chua", "Rau muống"
  description?: string;          // Optional description
  barcode?: string;              // Barcode/SKU if available
  imageUrl?: string;             // Firebase Storage URL
  
  // Pricing
  price: number;                 // Price in VND (integer, no decimals)
  unit: ProductUnit;             // "kg", "bo", "chai", "trai", "chuc", etc.
  
  // Inventory
  quantity: number;              // Current stock quantity
  lowStockThreshold: number;     // Alert when below this
  
  // Categorization
  categoryId: string;            // Reference to category
  categoryName: string;          // Denormalized for quick display
  
  // Metadata
  isActive: boolean;             // Soft delete flag
  createdAt: Timestamp;
  updatedAt: Timestamp;
  createdBy: string;             // userId
}

// Unit types enum
type ProductUnit = 
  | 'kg'      // Kilogram
  | 'g'       // Gram
  | 'bo'      // Bundle (bó)
  | 'chai'    // Bottle
  | 'trai'    // Fruit/piece (trái)
  | 'chuc'    // Dozen-ish (chục = 10)
  | 'hop'     // Box (hộp)
  | 'goi'     // Pack (gói)
  | 'lon'     // Can (lon)
  | 'cai';    // Piece (cái)
```

### Indexes for Products
```
- storeId + isActive + name (ASC)
- storeId + categoryId + isActive
- storeId + barcode
- storeId + updatedAt (DESC)
```

---

## 🏷️ Categories Collection

### Document: `/categories/{categoryId}`

```typescript
interface Category {
  id: string;
  storeId: string;               // null for default categories
  
  name: string;                  // "Rau củ", "Thịt cá"
  nameEn?: string;               // "Vegetables", "Meat & Fish"
  emoji: string;                 // "🥬", "🥩"
  
  order: number;                 // Display order
  productCount: number;          // Denormalized count
  
  isDefault: boolean;            // System default category
  isActive: boolean;
  
  createdAt: Timestamp;
  updatedAt: Timestamp;
}
```

### Default Categories
```typescript
const defaultCategories = [
  { name: 'Tất cả', emoji: '📦', order: 0 },
  { name: 'Rau củ', emoji: '🥬', order: 1 },
  { name: 'Thịt cá', emoji: '🥩', order: 2 },
  { name: 'Đồ khô', emoji: '🥚', order: 3 },
  { name: 'Gia vị', emoji: '🧂', order: 4 },
  { name: 'Đồ uống', emoji: '🍹', order: 5 },
  { name: 'Trái cây', emoji: '🍎', order: 6 },
];
```

---

## 🛒 Orders Collection

### Document: `/orders/{orderId}`

```typescript
interface Order {
  id: string;
  storeId: string;
  orderNumber: number;           // Sequential: 1023, 1024, etc.
  
  // Customer Info (optional)
  customer?: {
    name?: string;               // "Khách lẻ" by default
    phone?: string;
    notes?: string;
  };
  
  // Order Summary
  itemCount: number;             // Total items
  subtotal: number;              // Before discount
  discount: number;              // Discount amount
  total: number;                 // Final amount in VND
  
  // Payment
  paymentMethod: PaymentMethod;  // 'cash', 'transfer', 'momo', etc.
  paymentStatus: PaymentStatus;  // 'pending', 'paid', 'refunded'
  amountPaid: number;
  change: number;                // Change returned
  
  // Status
  status: OrderStatus;           // 'new', 'completed', 'cancelled'
  
  // Metadata
  createdAt: Timestamp;
  completedAt?: Timestamp;
  createdBy: string;             // userId
}

type PaymentMethod = 'cash' | 'bank_transfer' | 'momo' | 'zalopay' | 'other';
type PaymentStatus = 'pending' | 'paid' | 'partial' | 'refunded';
type OrderStatus = 'new' | 'processing' | 'completed' | 'cancelled';
```

### Subcollection: `/orders/{orderId}/items/{itemId}`

```typescript
interface OrderItem {
  id: string;
  productId: string;
  
  // Snapshot of product at time of sale
  productName: string;
  productUnit: ProductUnit;
  unitPrice: number;
  
  quantity: number;
  subtotal: number;              // quantity * unitPrice
  
  notes?: string;                // Special instructions
}
```

### Indexes for Orders
```
- storeId + createdAt (DESC)
- storeId + status + createdAt
- storeId + paymentStatus
```

---

## 📊 Daily Revenue Collection

### Document: `/daily_revenue/{date_storeId}`

Document ID format: `YYYY-MM-DD_storeId`

```typescript
interface DailyRevenue {
  id: string;                    // "2024-12-23_store123"
  storeId: string;
  date: string;                  // "2024-12-23"
  
  // Aggregated Data
  totalRevenue: number;          // Total sales
  orderCount: number;            // Number of orders
  itemsSold: number;             // Total items sold
  
  // Breakdown by payment method
  cashRevenue: number;
  transferRevenue: number;
  otherRevenue: number;
  
  // Top products (denormalized for quick display)
  topProducts: Array<{
    productId: string;
    productName: string;
    quantitySold: number;
    revenue: number;
  }>;
  
  // Hourly breakdown (for charts)
  hourlyRevenue: {
    [hour: string]: number;      // "09": 150000, "10": 280000
  };
  
  // Metadata
  lastUpdated: Timestamp;
}
```

---

## 👤 Users Collection

### Document: `/users/{userId}`

```typescript
interface User {
  id: string;
  
  // Profile
  displayName: string;           // "Cô Lan"
  phone?: string;
  email?: string;
  avatarUrl?: string;
  role: UserRole;                // "owner", "staff"
  
  // Store Association
  storeId: string;
  storeName?: string;            // Denormalized
  
  // Preferences
  settings: UserSettings;
  
  // Metadata
  createdAt: Timestamp;
  lastLoginAt: Timestamp;
}

interface UserSettings {
  language: 'vi' | 'en';
  theme: 'light' | 'dark' | 'system';
  notifications: boolean;
  autoSync: boolean;
}

type UserRole = 'owner' | 'manager' | 'staff';
```

---

## 🏪 Stores Collection

### Document: `/stores/{storeId}`

```typescript
interface Store {
  id: string;
  ownerId: string;               // Primary owner userId
  
  // Store Info
  name: string;                  // "Cửa hàng Cô Ba"
  description?: string;
  address?: string;
  phone?: string;
  
  // Business Info
  businessType: string;          // "Rau củ quả", "Tạp hóa"
  taxId?: string;                // Mã số thuế
  
  // Settings
  orderNumberPrefix?: string;    // "CB" -> CB1023
  currentOrderNumber: number;    // For generating order numbers
  
  // Statistics (updated periodically)
  stats: {
    totalProducts: number;
    totalOrders: number;
    totalRevenue: number;
    lastOrderAt?: Timestamp;
  };
  
  // Metadata
  createdAt: Timestamp;
  updatedAt: Timestamp;
}
```

---

## 🧮 Tax Records Collection

### Document: `/tax_records/{recordId}`

```typescript
interface TaxRecord {
  id: string;
  storeId: string;
  userId: string;
  
  // Period
  period: 'monthly' | 'quarterly' | 'yearly';
  year: number;
  month?: number;                // 1-12
  quarter?: number;              // 1-4
  
  // Revenue Data
  totalRevenue: number;
  deductions: number;
  taxableIncome: number;
  
  // Tax Calculation
  taxType: TaxType;
  taxRate: number;               // Percentage
  taxAmount: number;             // Calculated tax
  
  // Status
  status: 'draft' | 'calculated' | 'submitted';
  
  // Metadata
  calculatedAt: Timestamp;
  notes?: string;
}

type TaxType = 
  | 'personal_income'     // Thuế thu nhập cá nhân
  | 'business_license'    // Thuế môn bài
  | 'vat';                // Thuế VAT
```

---

## 📷 Bill Imports Collection

### Document: `/bill_imports/{importId}`

```typescript
interface BillImport {
  id: string;
  storeId: string;
  userId: string;
  
  // Source
  imageUrl: string;              // Scanned bill image
  
  // Extracted Data
  supplierName?: string;
  billDate?: Timestamp;
  billNumber?: string;
  
  // Items extracted
  items: Array<{
    rawText: string;             // Original OCR text
    productName?: string;        // Parsed name
    quantity?: number;
    unit?: string;
    price?: number;
    matched?: boolean;           // Matched to existing product
    productId?: string;          // If matched
  }>;
  
  // Status
  status: 'pending' | 'reviewed' | 'imported' | 'cancelled';
  importedCount: number;         // Products actually imported
  
  // Metadata
  createdAt: Timestamp;
  processedAt?: Timestamp;
}
```

---

## 🔒 Security Rules

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    
    // Helper functions
    function isAuthenticated() {
      return request.auth != null;
    }
    
    function isStoreOwner(storeId) {
      return get(/databases/$(database)/documents/stores/$(storeId)).data.ownerId == request.auth.uid;
    }
    
    function belongsToStore(storeId) {
      return get(/databases/$(database)/documents/users/$(request.auth.uid)).data.storeId == storeId;
    }
    
    // Products - Only store members can read/write
    match /products/{productId} {
      allow read, write: if isAuthenticated() 
        && belongsToStore(resource.data.storeId);
    }
    
    // Categories - Public read for defaults, write for store members
    match /categories/{categoryId} {
      allow read: if true;
      allow write: if isAuthenticated() 
        && (resource.data.isDefault == false)
        && belongsToStore(resource.data.storeId);
    }
    
    // Orders - Store members only
    match /orders/{orderId} {
      allow read, write: if isAuthenticated() 
        && belongsToStore(resource.data.storeId);
      
      match /items/{itemId} {
        allow read, write: if isAuthenticated()
          && belongsToStore(get(/databases/$(database)/documents/orders/$(orderId)).data.storeId);
      }
    }
    
    // Revenue - Store members only
    match /daily_revenue/{docId} {
      allow read, write: if isAuthenticated() 
        && belongsToStore(resource.data.storeId);
    }
    
    // Users - Self access only
    match /users/{userId} {
      allow read, write: if isAuthenticated() 
        && request.auth.uid == userId;
    }
    
    // Stores - Owner full access, members read
    match /stores/{storeId} {
      allow read: if isAuthenticated() && belongsToStore(storeId);
      allow write: if isAuthenticated() && isStoreOwner(storeId);
    }
  }
}
```

---

## 📈 Data Migration & Seeding

### Initial Setup Script

```dart
// Seed default categories
Future<void> seedDefaultCategories() async {
  final categories = [
    Category(name: 'Rau củ', emoji: '🥬', order: 1),
    Category(name: 'Thịt cá', emoji: '🥩', order: 2),
    Category(name: 'Đồ khô', emoji: '🥚', order: 3),
    Category(name: 'Gia vị', emoji: '🧂', order: 4),
    Category(name: 'Đồ uống', emoji: '🍹', order: 5),
    Category(name: 'Trái cây', emoji: '🍎', order: 6),
  ];
  
  for (final category in categories) {
    await firestore.collection('categories').add({
      ...category.toJson(),
      'isDefault': true,
      'isActive': true,
    });
  }
}
```

---

## 🔄 Offline Sync Strategy

1. **Products**: Cache all products locally, sync on app start
2. **Orders**: Create locally, sync when online
3. **Revenue**: Calculate locally, aggregate on server
4. **Categories**: Cache on first load, rarely changes

### Local Storage with Firestore Offline Persistence
```dart
FirebaseFirestore.instance.settings = const Settings(
  persistenceEnabled: true,
  cacheSizeBytes: Settings.CACHE_SIZE_UNLIMITED,
);
```
