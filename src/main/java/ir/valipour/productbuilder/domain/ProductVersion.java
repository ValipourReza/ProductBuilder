package ir.valipour.productbuilder.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="product_versions", uniqueConstraints=@UniqueConstraint(columnNames={"product_id","version"}))
public class ProductVersion {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) private Product product;
 @Column(nullable=false) private Integer version;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private ProductStatus status=ProductStatus.DRAFT;
 @Column(nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
 private LocalDateTime publishedAt;
 public Long getId(){return id;} public Product getProduct(){return product;} public void setProduct(Product v){product=v;}
 public Integer getVersion(){return version;} public void setVersion(Integer v){version=v;} public ProductStatus getStatus(){return status;} public void setStatus(ProductStatus v){status=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;}
}
