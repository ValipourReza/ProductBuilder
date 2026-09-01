package ir.valipour.productbuilder.repository;
import ir.valipour.productbuilder.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product,Long> { boolean existsByCode(String code); }
