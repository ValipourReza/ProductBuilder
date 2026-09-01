package ir.valipour.productbuilder.service;

import ir.valipour.productbuilder.domain.Feature;
import ir.valipour.productbuilder.domain.Product;
import ir.valipour.productbuilder.domain.ProductFeature;
import ir.valipour.productbuilder.repository.FeatureRepository;
import ir.valipour.productbuilder.repository.ProductFeatureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductFeatureService {
 private final ProductFeatureRepository productFeatures; private final ProductService products; private final FeatureRepository features;
 public ProductFeatureService(ProductFeatureRepository productFeatures, ProductService products, FeatureRepository features){this.productFeatures=productFeatures;this.products=products;this.features=features;}
 public ProductFeature add(Long productId, Long featureId, boolean required, int order, String defaultValue){
  Product p=products.get(productId); Feature f=features.findById(featureId).orElseThrow(()->new IllegalArgumentException("Feature not found: "+featureId));
  ProductFeature pf=new ProductFeature();pf.setProduct(p);pf.setFeature(f);pf.setRequiredField(required);pf.setDisplayOrder(order);pf.setDefaultValue(defaultValue);return productFeatures.save(pf);
 }
 public void remove(Long id){productFeatures.deleteById(id);}
}
