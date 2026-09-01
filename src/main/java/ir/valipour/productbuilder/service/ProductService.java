package ir.valipour.productbuilder.service;

import ir.valipour.productbuilder.domain.Product;
import ir.valipour.productbuilder.domain.ProductStatus;
import ir.valipour.productbuilder.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {
 private final ProductRepository repository;
 public ProductService(ProductRepository repository){this.repository=repository;}
 @Transactional(readOnly=true) public List<Product> findAll(){return repository.findAll();}
 @Transactional(readOnly=true) public Product get(Long id){return repository.findById(id).orElseThrow(()->new IllegalArgumentException("Product not found: "+id));}
 public Product save(Product product){return repository.save(product);}
 public Product create(String code,String name,String description){Product p=new Product();p.setCode(code);p.setName(name);p.setDescription(description);p.setActive(true);return save(p);}
 public void activate(Long id){Product p=get(id);p.setActive(true);}
 public void deactivate(Long id){Product p=get(id);p.setActive(false);}
}
