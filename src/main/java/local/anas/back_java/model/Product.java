package local.anas.back_java.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PRODUCTS")
@Data
@JsonIgnoreProperties(allowGetters = true, value = {"createdAt", "updatedAt"}, ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String code;
  private String name;
  private String description;
  private String image;
  private String category;
  private Double price;
  private Integer quantity;
  private String internalReference;
  private Long shellId;

  @Enumerated(EnumType.STRING)
  private InventoryStatus inventoryStatus;

  private Float rating;

  @Column(updatable = false)
  @CreatedDate
  private Long createdAt;
  @LastModifiedDate
  private Long updatedAt;

}

