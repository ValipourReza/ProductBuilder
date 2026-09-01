package ir.valipour.productbuilder.service;

import ir.valipour.productbuilder.domain.Feature;
import ir.valipour.productbuilder.repository.FeatureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class FeatureService {
 private final FeatureRepository repository;
 public FeatureService(FeatureRepository repository){this.repository=repository;}
 @Transactional(readOnly=true) public List<Feature> findAll(){return repository.findAll();}
 @Transactional(readOnly=true) public Feature get(Long id){return repository.findById(id).orElseThrow(()->new IllegalArgumentException("Feature not found: "+id));}
 public Feature save(Feature f){return repository.save(f);}
}
