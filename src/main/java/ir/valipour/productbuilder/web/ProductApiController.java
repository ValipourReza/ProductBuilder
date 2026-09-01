package ir.valipour.productbuilder.web;

import ir.valipour.productbuilder.domain.Product;
import ir.valipour.productbuilder.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {
 private final ProductService service;
 public ProductApiController(ProductService service){this.service=service;}
 @GetMapping public List<ProductResponse> all(){return service.findAll().stream().map(ProductResponse::from).toList();}
 @GetMapping("/{id}") public ProductResponse get(@PathVariable Long id){return ProductResponse.from(service.get(id));}
 @PostMapping public ResponseEntity<ProductResponse> create(@RequestBody CreateProductRequest r){return ResponseEntity.ok(ProductResponse.from(service.create(r.code(),r.name(),r.description())));}
 public record CreateProductRequest(String code,String name,String description){}
 public record ProductResponse(Long id,String code,String name,String description,boolean active,int featureCount){static ProductResponse from(Product p){return new ProductResponse(p.getId(),p.getCode(),p.getName(),p.getDescription(),p.isActive(),p.getFeatures().size());}}
}
