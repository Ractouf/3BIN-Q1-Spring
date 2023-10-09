package be.vinci.ipl.wishlist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @JsonIgnore
  private int id;
  private String name;
  private String category;
  private int price;
}
