package ir.valipour.productbuilder.web;

import ir.valipour.productbuilder.domain.Feature;
import ir.valipour.productbuilder.domain.Product;
import ir.valipour.productbuilder.domain.ProductFeature;
import ir.valipour.productbuilder.repository.FeatureRepository;
import ir.valipour.productbuilder.repository.ProductFeatureRepository;
import ir.valipour.productbuilder.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private final ProductRepository products;
    private final FeatureRepository features;
    private final ProductFeatureRepository productFeatures;

    public ProductController(ProductRepository products, FeatureRepository features, ProductFeatureRepository productFeatures) {
        this.products = products;
        this.features = features;
        this.productFeatures = productFeatures;
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", products.findAll());
        return "products";
    }

    @GetMapping("/products/new")
    public String form(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/products")
    public String save(@RequestParam String code,
                       @RequestParam String name,
                       @RequestParam(required = false) String description) {
        Product p = new Product();
        p.setCode(code.trim());
        p.setName(name.trim());
        p.setDescription(description);
        p.setActive(true);
        products.save(p);
        return "redirect:/products/" + p.getId();
    }

    @GetMapping("/products/{id}")
    @Transactional(readOnly = true)
    public String detail(@PathVariable Long id, Model model) {
        Product p = products.findById(id).orElseThrow();
        model.addAttribute("product", p);
        model.addAttribute("features", features.findByActiveTrueOrderByNameAsc());
        return "product-detail";
    }

    @PostMapping("/products/{id}/activate")
    public String activate(@PathVariable Long id) {
        Product p = products.findById(id).orElseThrow();
        p.setActive(true);
        products.save(p);
        return "redirect:/products/" + id;
    }

    @PostMapping("/products/{id}/deactivate")
    public String deactivate(@PathVariable Long id) {
        Product p = products.findById(id).orElseThrow();
        p.setActive(false);
        products.save(p);
        return "redirect:/products/" + id;
    }

    @PostMapping("/products/{id}/features")
    public String addFeature(@PathVariable Long id,
                             @RequestParam Long featureId,
                             @RequestParam(defaultValue = "false") boolean required,
                             @RequestParam(defaultValue = "0") int displayOrder,
                             @RequestParam(required = false) String defaultValue) {
        Product p = products.findById(id).orElseThrow();
        Feature f = features.findById(featureId).orElseThrow();
        if (!productFeatures.existsByProductIdAndFeatureId(id, featureId)) {
            ProductFeature pf = new ProductFeature();
            pf.setProduct(p);
            pf.setFeature(f);
            pf.setRequiredField(required);
            pf.setDisplayOrder(displayOrder);
            pf.setDefaultValue(defaultValue);
            productFeatures.save(pf);
        }
        return "redirect:/products/" + id;
    }

    @PostMapping("/products/{productId}/features/{featureId}/delete")
    public String remove(@PathVariable Long productId, @PathVariable Long featureId) {
        productFeatures.deleteById(featureId);
        return "redirect:/products/" + productId;
    }
}
