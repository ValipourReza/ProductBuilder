package ir.valipour.productbuilder.web;

import ir.valipour.productbuilder.domain.Product;
import ir.valipour.productbuilder.domain.ProductStatus;
import ir.valipour.productbuilder.domain.ProductVersion;
import ir.valipour.productbuilder.repository.ProductRepository;
import ir.valipour.productbuilder.repository.ProductVersionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/products/{productId}/versions")
public class ProductLifecycleController {
    private final ProductRepository products;
    private final ProductVersionRepository versions;
    public ProductLifecycleController(ProductRepository products, ProductVersionRepository versions) { this.products=products; this.versions=versions; }

    @GetMapping
    @Transactional(readOnly=true)
    public String versions(@PathVariable Long productId, Model model) {
        Product p=products.findById(productId).orElseThrow();
        model.addAttribute("product",p); model.addAttribute("versions",versions.findByProductIdOrderByVersionDesc(productId));
        return "product-versions";
    }

    @PostMapping
    public String create(@PathVariable Long productId) {
        Product p=products.findById(productId).orElseThrow();
        var list=versions.findByProductIdOrderByVersionDesc(productId);
        ProductVersion v=new ProductVersion(); v.setProduct(p); v.setVersion(list.isEmpty()?1:list.get(0).getVersion()+1); v.setStatus(ProductStatus.DRAFT); versions.save(v);
        return "redirect:/products/"+productId+"/versions";
    }

    @PostMapping("/{versionId}/publish")
    public String publish(@PathVariable Long productId,@PathVariable Long versionId) {
        Product p=products.findById(productId).orElseThrow();
        ProductVersion v=versions.findById(versionId).orElseThrow();
        v.setStatus(ProductStatus.ACTIVE); v.setPublishedAt(LocalDateTime.now()); versions.save(v);
        p.setStatus(ProductStatus.ACTIVE); products.save(p);
        return "redirect:/products/"+productId+"/versions";
    }
}
