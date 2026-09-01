package ir.valipour.productbuilder.repository;

import ir.valipour.productbuilder.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByCode(String code);

    /**
     * Loads products together with their features for read-only screens such as the dashboard.
     * This avoids LazyInitializationException after the repository transaction has ended.
     */
    @Query("select distinct p from Product p left join fetch p.features")
    List<Product> findAllWithFeatures();
}
