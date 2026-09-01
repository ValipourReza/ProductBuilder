package ir.valipour.productbuilder.domain;

import jakarta.persistence.*;

@Entity
@Table(name="business_rules")
public class BusinessRule {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) private Product product;
 @Column(nullable=false,length=100) private String code;
 @Column(nullable=false,length=200) private String name;
 @Column(length=2000) private String expression;
 private boolean active=true;
 public Long getId(){return id;} public Product getProduct(){return product;} public void setProduct(Product v){product=v;}
 public String getCode(){return code;} public void setCode(String v){code=v;} public String getName(){return name;} public void setName(String v){name=v;}
 public String getExpression(){return expression;} public void setExpression(String v){expression=v;} public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
}
