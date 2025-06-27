package technical.transaction_usecase.command.command.transaction;

import java.util.List;
import java.util.Map;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technical.transaction_usecase.command.common.PaidStatusEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionCommand {
    @TargetAggregateIdentifier
    private String id;
    private List<Map<String,Object>> products;
    private PaidStatusEnum paidStatus;    
}
