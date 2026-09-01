package ir.valipour.productbuilder.domain;

import jakarta.persistence.*;

@Entity
@Table(name="product_features", uniqueConstraints=@UniqueConstraint(columnNames={"product_id","feature_id"}))
public class ProductFeature {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="product_id") private Product product;
    @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="feature_id") private Feature feature;
    @Column(name="required_field",nullable=false) private boolean requiredField;
    @Column(nullable=false) private int displayOrder;
    @Column(length=500) private String defaultValue;
    @Column(length=100) private String minValue;
    @Column(length=100) private String maxValue;
    @Column(length=50) private String unit;
    public Long getId(){return id;} public Product getProduct(){return product;} public void setProduct(Product v){product=v;}
    public Feature getFeature(){return feature;} public void setFeature(Feature v){feature=v;}
    public boolean isRequiredField(){return requiredField;} public void setRequiredField(boolean v){requiredField=v;}
    public int getDisplayOrder(){return displayOrder;} public void setDisplayOrder(int v){displayOrder=v;}
    public String getDefaultValue(){return defaultValue;} public void setDefaultValue(String v){defaultValue=v;}
    public String getMinValue(){return minValue;} public void setMinValue(String v){minValue=v;}
    public String getMaxValue(){return maxValue;} public void setMaxValue(String v){maxValue=v;}
    public String getUnit(){return unit;} public void setUnit(String v){unit=v;}
}
