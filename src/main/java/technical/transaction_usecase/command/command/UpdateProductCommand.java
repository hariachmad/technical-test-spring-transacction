package technical.transaction_usecase.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductCommand {
    @TargetAggregateIdentifier
    private String id;
    private String name;
    private int price;
    private int stock;
}
