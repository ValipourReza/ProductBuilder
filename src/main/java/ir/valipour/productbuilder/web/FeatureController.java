package ir.valipour.productbuilder.web;
import ir.valipour.productbuilder.domain.*;import ir.valipour.productbuilder.repository.FeatureRepository;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/features") public class FeatureController { private final FeatureRepository repo; public FeatureController(FeatureRepository r){repo=r;}
 @GetMapping public String list(Model m){m.addAttribute("features",repo.findAll());return "features";}
 @GetMapping("/new") public String form(Model m){m.addAttribute("types",FeatureType.values());m.addAttribute("feature",new Feature());return "feature-form";}
 @PostMapping public String save(@RequestParam String code,@RequestParam String name,@RequestParam FeatureType type,@RequestParam(required=false) String description){Feature f=new Feature();f.setCode(code);f.setName(name);f.setType(type);f.setDescription(description);repo.save(f);return "redirect:/features";}
}