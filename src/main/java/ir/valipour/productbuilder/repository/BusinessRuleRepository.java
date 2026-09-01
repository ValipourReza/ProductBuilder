package ir.valipour.productbuilder.repository;

import ir.valipour.productbuilder.domain.BusinessRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BusinessRuleRepository extends JpaRepository<BusinessRule,Long> { List<BusinessRule> findByProductIdOrderByCode(Long productId); }
