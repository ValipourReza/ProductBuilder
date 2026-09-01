package ir.valipour.productbuilder.repository;

import ir.valipour.productbuilder.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByCode(String code);

    /**
     * Loads products together with their features for read-only screens such as the dashboard.
     * This avoids LazyInitializationException after the repository transaction has ended.
     */
    @Query("select distinct p from Product p left join fetch p.features")
    List<Product> findAllWithFeatures();

    /**
     * Loads one product together with its features before the transaction ends.
     * The detail Thymeleaf page is rendered after the controller method returns,
     * so relying on the lazy collection there causes LazyInitializationException.
     */
    @Query("select distinct p from Product p left join fetch p.features where p.id = :id")
    Optional<Product> findByIdWithFeatures(@Param("id") Long id);
}
