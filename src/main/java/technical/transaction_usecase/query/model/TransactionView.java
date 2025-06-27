package technical.transaction_usecase.query.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technical.transaction_usecase.command.common.PaidStatusEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions_view")
public class TransactionView {
    @Id
    private String id;
    private LocalDateTime lastUpdate;
    private PaidStatusEnum status;

    @OneToMany(mappedBy = "transactionView", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSalesView> productSalesView = new ArrayList<>();
}
