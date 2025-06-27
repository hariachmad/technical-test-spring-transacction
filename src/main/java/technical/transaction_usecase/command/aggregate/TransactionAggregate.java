package technical.transaction_usecase.command.aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import technical.transaction_usecase.command.command.transaction.CreateTransactionCommand;
import technical.transaction_usecase.command.common.PaidStatusEnum;
import technical.transaction_usecase.command.event.transaction.TransactionCreatedEvent;

@Aggregate
public class TransactionAggregate {
    @AggregateIdentifier
    private String id;
    List<Map<String,Object>> products;
    PaidStatusEnum paidStatus;

    @CommandHandler
    public TransactionAggregate(CreateTransactionCommand command) {
        AggregateLifecycle.apply(new TransactionCreatedEvent(
            command.getId(), 
            command.getProducts() != null ? command.getProducts() : new ArrayList<>(),
            command.getPaidStatus()
        ));
    }

    @EventSourcingHandler
    public void on(TransactionCreatedEvent event) {
        this.id = event.getId();
        this.products = event.getProducts() != null ? event.getProducts() : new ArrayList<>();
        this.paidStatus = event.getPaidStatus();
    }
}
