package technical.transaction_usecase.command.event.product;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSoldEvent implements Serializable {
    private String id;
    private int quantity;
}
