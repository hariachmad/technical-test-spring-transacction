package technical.transaction_usecase.query.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    String id;
    String name;
    int price;
    int stock;
    boolean deleted;
}
