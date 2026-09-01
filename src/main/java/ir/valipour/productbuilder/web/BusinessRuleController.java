package ir.valipour.productbuilder.web;

import ir.valipour.productbuilder.domain.BusinessRule;
import ir.valipour.productbuilder.repository.BusinessRuleRepository;
import ir.valipour.productbuilder.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products/{productId}/rules")
public class BusinessRuleController {
    private final ProductRepository products; private final BusinessRuleRepository rules;
    public BusinessRuleController(ProductRepository products, BusinessRuleRepository rules){this.products=products;this.rules=rules;}
    @GetMapping public String page(@PathVariable Long productId, Model model){
        var p=products.findById(productId).orElseThrow(); model.addAttribute("product",p); model.addAttribute("rules",rules.findByProductIdOrderByCode(productId)); return "business-rules";
    }
    @PostMapping public String create(@PathVariable Long productId,@RequestParam String code,@RequestParam String name,@RequestParam(required=false) String expression){
        var r=new BusinessRule(); r.setProduct(products.findById(productId).orElseThrow()); r.setCode(code.trim().toUpperCase()); r.setName(name.trim()); r.setExpression(expression); rules.save(r); return "redirect:/products/"+productId+"/rules";
    }
    @PostMapping("/{ruleId}/toggle") public String toggle(@PathVariable Long productId,@PathVariable Long ruleId){var r=rules.findById(ruleId).orElseThrow();r.setActive(!r.isActive());rules.save(r);return "redirect:/products/"+productId+"/rules";}
}
