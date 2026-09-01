package ir.valipour.productbuilder.web;

import ir.valipour.productbuilder.service.FeatureService;
import ir.valipour.productbuilder.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
 private final ProductService products; private final FeatureService features;
 public DashboardController(ProductService products, FeatureService features){this.products=products;this.features=features;}
 @GetMapping({"/","/dashboard"}) public String dashboard(Model model){model.addAttribute("products",products.findAll());model.addAttribute("features",features.findAll());return "dashboard";}
}
