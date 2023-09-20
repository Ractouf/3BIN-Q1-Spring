package be.vinci.ipl.amazing;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "products")
public class Product {
    @Id
    private int id;
    private String name;
    private String category;
    private int price;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

      return id == product.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
