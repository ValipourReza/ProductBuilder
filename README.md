# ProductBuilder

سامانه تعریف و پیکربندی محصولات بانکی با Spring Boot.

## هدف
در بانک، یک خدمت یا فرآیند مانند تسهیلات، سپرده، کارت یا پرداخت می‌تواند به صورت یک **Product** مدل شود. هر Product از مجموعه‌ای از **Feature**های قابل استفاده مجدد تشکیل می‌شود. ProductBuilder برای این است که Product را بدون تغییر کد برنامه، از طریق Builder تعریف و پیکربندی کنیم.

مثال:

```text
تسهیلات مرابحه
  ├─ مبلغ تسهیلات        MONEY
  ├─ نرخ سود             DECIMAL
  ├─ مدت                 INTEGER
  ├─ دوره بازپرداخت      INTEGER
  ├─ نیاز به ضامن        BOOLEAN
  └─ نوع وثیقه           ENUM
```

## معماری

```text
UI (Thymeleaf)
      │
Web / REST Controllers
      │
Service Layer
      │
Domain / JPA Entities
      │
Repository Layer
      │
H2 Database
```

دامنه فعلی شامل Product، Feature، ProductFeature، ProductVersion و BusinessRule است و lifecycle اولیه محصول نیز با DRAFT/ACTIVE/SUSPENDED/RETIRED مدل شده است.

## فناوری
- Java 21
- Spring Boot 3.5.14
- Spring Data JPA
- H2
- Thymeleaf
- Maven

## اجرا

```bash
mvn spring-boot:run
```

سپس:
- Dashboard: `http://localhost:8080/`
- Products: `http://localhost:8080/products`
- Features: `http://localhost:8080/features`
- H2 Console: `http://localhost:8080/h2-console`

## جریان Product Builder

1. ایجاد Product
2. انتخاب/ایجاد Featureهای استاندارد
3. اتصال Feature به Product
4. تعیین Required، ترتیب و مقدار پیش‌فرض
5. آماده‌سازی نسخه محصول
6. انتشار محصول

## نکته طراحی
Feature یک مفهوم reusable است؛ ProductFeature تنظیمات استفاده از آن Feature در یک Product را نگهداری می‌کند. به این ترتیب یک Feature مثل `INTEREST_RATE` می‌تواند در چندین محصول بانکی استفاده شود ولی تنظیمات هر محصول مستقل باشد.
