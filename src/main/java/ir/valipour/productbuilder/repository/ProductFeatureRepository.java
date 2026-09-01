package ir.valipour.productbuilder.repository;
import ir.valipour.productbuilder.domain.ProductFeature;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductFeatureRepository extends JpaRepository<ProductFeature,Long> { boolean existsByProductIdAndFeatureId(Long productId,Long featureId); }
