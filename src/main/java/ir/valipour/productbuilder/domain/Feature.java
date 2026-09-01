package ir.valipour.productbuilder.domain;

import jakarta.persistence.*;

@Entity @Table(name="features")
public class Feature {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=100) private String code;
 @Column(nullable=false,length=200) private String name;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private FeatureType type;
 @Column(length=1000) private String description;
 private boolean active=true;
 public Long getId(){return id;} public String getCode(){return code;} public void setCode(String v){code=v;} public String getName(){return name;} public void setName(String v){name=v;}
 public FeatureType getType(){return type;} public void setType(FeatureType v){type=v;} public String getDescription(){return description;} public void setDescription(String v){description=v;} public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
}