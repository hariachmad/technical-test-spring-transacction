package technical.transaction_usecase.command.command.transaction;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technical.transaction_usecase.command.common.PaidStatusEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaidTransactionCommand {
    @TargetAggregateIdentifier
    private String id;
    private PaidStatusEnum paidStatus;
}
