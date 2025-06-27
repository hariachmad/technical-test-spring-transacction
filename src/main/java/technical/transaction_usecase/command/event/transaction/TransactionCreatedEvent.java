package technical.transaction_usecase.command.event.transaction;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technical.transaction_usecase.command.common.PaidStatusEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreatedEvent implements Serializable {
    private String id;
    private List<Map<String,Object>> products;
    private PaidStatusEnum paidStatus;
}
