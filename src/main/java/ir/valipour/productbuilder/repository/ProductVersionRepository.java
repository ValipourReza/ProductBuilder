package ir.valipour.productbuilder.repository;

import ir.valipour.productbuilder.domain.ProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductVersionRepository extends JpaRepository<ProductVersion,Long> {
 List<ProductVersion> findByProductIdOrderByVersionDesc(Long productId);
}
