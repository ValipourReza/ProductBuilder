package ir.valipour.productbuilder.repository;
import ir.valipour.productbuilder.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FeatureRepository extends JpaRepository<Feature,Long> { boolean existsByCode(String code); List<Feature> findByActiveTrueOrderByNameAsc(); }
