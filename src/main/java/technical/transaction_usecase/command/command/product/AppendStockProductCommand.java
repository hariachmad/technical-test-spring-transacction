package technical.transaction_usecase.command.command.product;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppendStockProductCommand {
    @TargetAggregateIdentifier
    private String id;
    private int quantity;
}
