package technical.transaction_usecase.command.event.transaction;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technical.transaction_usecase.command.common.PaidStatusEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPaidEvent implements Serializable{
    private String id;
    private PaidStatusEnum paidStatus;
}
