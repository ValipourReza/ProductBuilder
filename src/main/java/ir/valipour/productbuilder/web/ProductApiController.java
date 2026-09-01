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
 @GetMapping public List<Product> all(){return service.findAll();}
 @GetMapping("/{id}") public Product get(@PathVariable Long id){return service.get(id);}
 @PostMapping public ResponseEntity<Product> create(@RequestBody CreateProductRequest r){return ResponseEntity.ok(service.create(r.code(),r.name(),r.description()));}
 public record CreateProductRequest(String code,String name,String description){}
}
