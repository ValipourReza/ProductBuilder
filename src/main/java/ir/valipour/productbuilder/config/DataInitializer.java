package ir.valipour.productbuilder.config;

import ir.valipour.productbuilder.domain.*;import ir.valipour.productbuilder.repository.*;import org.springframework.boot.CommandLineRunner;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;
@Configuration public class DataInitializer {
 @Bean CommandLineRunner seed(FeatureRepository fr,ProductRepository pr,ProductFeatureRepository pfr){return args->{
  if(fr.count()==0){Feature rate=new Feature();rate.setCode("INTEREST_RATE");rate.setName("نرخ سود");rate.setType(FeatureType.PERCENTAGE);rate.setDescription("نرخ سود سالانه");fr.save(rate);
   Feature amount=new Feature();amount.setCode("MAX_AMOUNT");amount.setName("سقف مبلغ");amount.setType(FeatureType.MONEY);amount.setDescription("حداکثر مبلغ قابل پرداخت");fr.save(amount);
   Feature term=new Feature();term.setCode("TERM");term.setName("مدت");term.setType(FeatureType.NUMBER);term.setDescription("مدت محصول به ماه");fr.save(term);
   Feature collateral=new Feature();collateral.setCode("COLLATERAL_REQUIRED");collateral.setName("نیاز به وثیقه");collateral.setType(FeatureType.BOOLEAN);collateral.setDescription("آیا محصول نیازمند وثیقه است؟");fr.save(collateral);
  }
  if(pr.count()==0){Product loan=new Product();loan.setCode("LOAN_STANDARD");loan.setName("تسهیلات عادی");loan.setDescription("نمونه محصول تسهیلات که ویژگی‌های کسب‌وکاری آن از طریق Featureها تعریف می‌شود.");loan.setActive(true);pr.save(loan);for(Feature f:fr.findAll()){ProductFeature pf=new ProductFeature();pf.setProduct(loan);pf.setFeature(f);pf.setRequiredField(true);pf.setDisplayOrder(f.getId().intValue());pfr.save(pf);}
   Product deposit=new Product();deposit.setCode("DEPOSIT_QARD");deposit.setName("سپرده قرض الحسنه");deposit.setDescription("نمونه محصول سپرده قرض الحسنه");deposit.setActive(true);pr.save(deposit);
  }
 };}
}
