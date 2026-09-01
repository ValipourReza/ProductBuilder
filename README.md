# ProductBuilder

سامانه تعریف و پیکربندی محصولات بانکی با Spring Boot.

## ایده کسب‌وکار
در معماری بانکی، یک فرایند/خدمت مانند **تسهیلات**، **سپرده** یا سایر خدمات، به صورت یک **Product** تعریف می‌شود. هر Product از مجموعه‌ای از **Feature**ها تشکیل می‌شود. Featureها قابلیت‌ها و پارامترهای قابل استفاده مجدد هستند؛ مانند نرخ سود، سقف مبلغ، مدت و نیاز به وثیقه.

ProductBuilder برای ساخت همین مدل است: ابتدا Featureهای استاندارد را در کاتالوگ تعریف می‌کنیم، سپس یک Product ساخته و Featureهای موردنیاز آن را با تنظیماتی مانند اجباری بودن، ترتیب و مقدار پیش‌فرض به محصول متصل می‌کنیم.

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
سپس `http://localhost:8080/products` را باز کنید.

## UI
- `/products` کاتالوگ محصولات
- `/products/new` ایجاد محصول
- `/products/{id}` Product Builder و اتصال Featureها
- `/features` کاتالوگ Featureها
- `/features/new` تعریف Feature جدید
- `/h2-console` کنسول H2

## معماری اولیه
`domain -> repository -> web -> Thymeleaf UI`

این نسخه، هسته MVP است و در گام‌های بعدی می‌توان lifecycle محصول، نسخه‌بندی، انواع Feature، Rule/Constraint، وابستگی Featureها، workflow و API را به آن اضافه کرد.
