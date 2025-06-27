package technical.transaction_usecase.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
    @Id
    protected String id;
    protected String name;
    protected int price;
    protected int stock;
    protected boolean deleted;
}
