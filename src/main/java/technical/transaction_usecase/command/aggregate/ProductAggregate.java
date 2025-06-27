package technical.transaction_usecase.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import technical.transaction_usecase.command.command.CreateProductCommand;
import technical.transaction_usecase.command.command.DeleteProductCommand;
import technical.transaction_usecase.command.command.UpdateProductCommand;
import technical.transaction_usecase.command.event.ProductCreatedEvent;
import technical.transaction_usecase.command.event.ProductDeletedEvent;
import technical.transaction_usecase.command.event.ProductUpdatedEvent;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private int price;
    private int stock;

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        AggregateLifecycle.apply(new ProductCreatedEvent(
                command.getId(),
                command.getName(),
                command.getPrice(),
                command.getStock()));
    }

    @CommandHandler
    public void handle(UpdateProductCommand command) {
        AggregateLifecycle.apply(new ProductUpdatedEvent(
                command.getId(),
                command.getName(),
                command.getPrice(),
                command.getStock()));
    }

    @CommandHandler
    public void handle(DeleteProductCommand command) {
        AggregateLifecycle.apply(new ProductDeletedEvent(command.getId()));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        try {
            this.id = event.getId();
            this.name = event.getName();
            this.price = event.getPrice();
            this.stock = event.getStock();
            System.out.println("Successfully created product aggregate with ID: " + this.id);
        } catch (Exception e) {
            System.out.println("Error creating product aggregate: {}" + e.getMessage());
        }

    }

    @EventSourcingHandler
    public void on(ProductUpdatedEvent event) {
        if (event.getName() != null)
            this.name = event.getName();
        if (event.getPrice() != 0)
            this.price = event.getPrice();
        if (event.getStock() != 0)
            this.stock = event.getStock();
    }

    @EventSourcingHandler
    public void on(ProductDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }

    private ProductAggregate() {
    }
}
