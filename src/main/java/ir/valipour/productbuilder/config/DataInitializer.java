package ir.valipour.productbuilder.config;

import ir.valipour.productbuilder.domain.*;
import ir.valipour.productbuilder.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean CommandLineRunner seed(FeatureRepository fr, ProductRepository pr, ProductFeatureRepository pfr) {
        return args -> {
            if (fr.count() == 0) {
                fr.save(feature("INTEREST_RATE", "نرخ سود", FeatureType.PERCENTAGE, "نرخ سود سالانه"));
                fr.save(feature("MAX_AMOUNT", "سقف مبلغ", FeatureType.MONEY, "حداکثر مبلغ قابل پرداخت"));
                fr.save(feature("TERM", "مدت", FeatureType.NUMBER, "مدت محصول به ماه"));
                fr.save(feature("COLLATERAL_REQUIRED", "نیاز به وثیقه", FeatureType.BOOLEAN, "آیا محصول نیازمند وثیقه است؟"));
                fr.save(feature("REPAYMENT_TYPE", "نوع بازپرداخت", FeatureType.SELECT, "نوع برنامه بازپرداخت"));
                fr.save(feature("MIN_INCOME", "حداقل درآمد", FeatureType.MONEY, "حداقل درآمد مورد نیاز متقاضی"));
            }
            if (pr.count() == 0) {
                Product loan = product("LOAN_MURABAHA", "تسهیلات مرابحه", ProductCategory.LOAN, "نمونه محصول تسهیلات با پارامترهای قابل پیکربندی.");
                pr.save(loan);
                int order = 1;
                for (Feature f : fr.findAll()) {
                    ProductFeature pf = new ProductFeature(); pf.setProduct(loan); pf.setFeature(f); pf.setRequiredField(order <= 4); pf.setDisplayOrder(order++); pfr.save(pf);
                }
                Product deposit = product("DEPOSIT_QARD", "سپرده قرض الحسنه", ProductCategory.DEPOSIT, "نمونه محصول سپرده قرض الحسنه.");
                pr.save(deposit);
            }
        };
    }
    private Feature feature(String code,String name,FeatureType type,String description){Feature f=new Feature();f.setCode(code);f.setName(name);f.setType(type);f.setDescription(description);return f;}
    private Product product(String code,String name,ProductCategory category,String description){Product p=new Product();p.setCode(code);p.setName(name);p.setCategory(category);p.setDescription(description);p.setStatus(ProductStatus.ACTIVE);return p;}
}
