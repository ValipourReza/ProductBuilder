package ir.valipour.productbuilder.web;

import ir.valipour.productbuilder.service.ProductFeatureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductFeatureController {
 private final ProductFeatureService service;
 public ProductFeatureController(ProductFeatureService service){this.service=service;}
 @PostMapping("/products/features/add") public String add(@RequestParam Long productId,@RequestParam Long featureId,@RequestParam(defaultValue="false") boolean required,@RequestParam(defaultValue="0") int displayOrder,@RequestParam(required=false) String defaultValue,RedirectAttributes ra){service.add(productId,featureId,required,displayOrder,defaultValue);ra.addFlashAttribute("success","Feature با موفقیت به محصول اضافه شد.");return "redirect:/products/"+productId;}
 @PostMapping("/products/features/remove") public String remove(@RequestParam Long productId,@RequestParam Long id,RedirectAttributes ra){service.remove(id);ra.addFlashAttribute("success","Feature حذف شد.");return "redirect:/products/"+productId;}
}
