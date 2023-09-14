package be.vinci.ipl.amazing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Product {
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
