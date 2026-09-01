package ir.valipour.productbuilder.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="products")
public class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false, unique=true, length=100) private String code;
 @Column(nullable=false, length=200) private String name;
 @Column(length=1000) private String description;
 @Column(nullable=false) private boolean active=true;
 @OneToMany(mappedBy="product", cascade=CascadeType.ALL, orphanRemoval=true)
 @OrderBy("displayOrder asc") private List<ProductFeature> features=new ArrayList<>();
 public Long getId(){return id;} public String getCode(){return code;} public void setCode(String v){code=v;}
 public String getName(){return name;} public void setName(String v){name=v;} public String getDescription(){return description;} public void setDescription(String v){description=v;}
 public boolean isActive(){return active;} public void setActive(boolean v){active=v;} public List<ProductFeature> getFeatures(){return features;}
 public void addFeature(ProductFeature f){features.add(f);f.setProduct(this);} public void removeFeature(ProductFeature f){features.remove(f);f.setProduct(null);}
}