package technical.transaction_usecase.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import technical.transaction_usecase.command.command.product.AppendStockProductCommand;
import technical.transaction_usecase.command.command.product.CreateProductCommand;
import technical.transaction_usecase.command.command.product.DeleteProductCommand;
import technical.transaction_usecase.command.command.product.SellProductCommand;
import technical.transaction_usecase.command.command.product.UpdateProductCommand;
import technical.transaction_usecase.command.event.product.ProductAppendStockEvent;
import technical.transaction_usecase.command.event.product.ProductCreatedEvent;
import technical.transaction_usecase.command.event.product.ProductDeletedEvent;
import technical.transaction_usecase.command.event.product.ProductSoldEvent;
import technical.transaction_usecase.command.event.product.ProductUpdatedEvent;

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
    public void handle(AppendStockProductCommand command){
        if (command.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero for product ID: " + this.id);
        }
        AggregateLifecycle.apply(new ProductAppendStockEvent(
            command.getId(),
            command.getQuantity()
        ));
    }

    @CommandHandler
    public void handle(SellProductCommand command){
        if (this.stock < command.getQty()) {
            throw new IllegalArgumentException("Insufficient stock for product ID: " + this.id);
        }
        AggregateLifecycle.apply(new ProductSoldEvent(
            command.getId(),
            command.getQty()
        ));
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
    public void on(ProductSoldEvent event) {
        if (this.stock < event.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock for product ID: " + this.id);
        }
        this.stock -= event.getQuantity();
        System.out.println("Product sold successfully. Remaining stock: " + this.stock);
    }

    @EventSourcingHandler
    public void on(ProductAppendStockEvent event) {
        if (event.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero for product ID: " + this.id);
        }
        this.stock += event.getQuantity();
        System.out.println("Stock appended successfully. New stock: " + this.stock);
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
