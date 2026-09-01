package ir.valipour.productbuilder.web;

import ir.valipour.productbuilder.domain.*;
import ir.valipour.productbuilder.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private final ProductRepository products; private final FeatureRepository features; private final ProductFeatureRepository productFeatures;
    public ProductController(ProductRepository p, FeatureRepository f, ProductFeatureRepository pf){products=p;features=f;productFeatures=pf;}
    @GetMapping("/products") public String products(Model m){m.addAttribute("products",products.findAll());return "products";}
    @GetMapping("/products/new") public String form(Model m){m.addAttribute("product",new Product());m.addAttribute("categories",ProductCategory.values());return "product-form";}
    @PostMapping("/products") public String save(@RequestParam String code,@RequestParam String name,@RequestParam ProductCategory category,@RequestParam(required=false) String description){Product p=new Product();p.setCode(code.trim());p.setName(name.trim());p.setCategory(category);p.setDescription(description);p.setStatus(ProductStatus.DRAFT);products.save(p);return "redirect:/products/"+p.getId();}
    @GetMapping("/products/{id}") @Transactional(readOnly=true) public String detail(@PathVariable Long id,Model m){Product p=products.findById(id).orElseThrow();m.addAttribute("product",p);m.addAttribute("features",features.findByActiveTrueOrderByNameAsc());m.addAttribute("statuses",ProductStatus.values());return "product-detail";}
    @PostMapping("/products/{id}/status") public String status(@PathVariable Long id,@RequestParam ProductStatus status){Product p=products.findById(id).orElseThrow();p.setStatus(status);products.save(p);return "redirect:/products/"+id;}
    @PostMapping("/products/{id}/features") public String addFeature(@PathVariable Long id,@RequestParam Long featureId,@RequestParam(defaultValue="false") boolean required,@RequestParam(defaultValue="0") int displayOrder,@RequestParam(required=false) String defaultValue,@RequestParam(required=false) String minValue,@RequestParam(required=false) String maxValue,@RequestParam(required=false) String unit){Product p=products.findById(id).orElseThrow();Feature f=features.findById(featureId).orElseThrow();if(!productFeatures.existsByProductIdAndFeatureId(id,featureId)){ProductFeature pf=new ProductFeature();pf.setProduct(p);pf.setFeature(f);pf.setRequiredField(required);pf.setDisplayOrder(displayOrder);pf.setDefaultValue(defaultValue);pf.setMinValue(minValue);pf.setMaxValue(maxValue);pf.setUnit(unit);productFeatures.save(pf);}return "redirect:/products/"+id;}
    @PostMapping("/products/{productId}/features/{featureId}/delete") public String remove(@PathVariable Long productId,@PathVariable Long featureId){productFeatures.deleteById(featureId);return "redirect:/products/"+productId;}
}
