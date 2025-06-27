package technical.transaction_usecase.query.projection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import technical.transaction_usecase.command.command.product.SellProductCommand;
import technical.transaction_usecase.command.event.transaction.TransactionCreatedEvent;
import technical.transaction_usecase.query.model.ProductSalesView;
import technical.transaction_usecase.query.model.ProductView;
import technical.transaction_usecase.query.model.TransactionView;
import technical.transaction_usecase.query.repository.ProductRepository;
import technical.transaction_usecase.query.repository.ProductSalesRepository;
import technical.transaction_usecase.query.repository.TransactionRepository;

@Transactional
@Component
public class TransactionProjection {
    private final ProductRepository productRepository;
    private final ProductSalesRepository productSalesRepository;
    private final TransactionRepository transactionRepository;
    private final CommandGateway commandGateway;

    public TransactionProjection(ProductRepository productRepository, ProductSalesRepository productSalesRepository,
            TransactionRepository transactionRepository, CommandGateway commandGateway) {
        this.productRepository = productRepository;
        this.productSalesRepository = productSalesRepository;
        this.transactionRepository = transactionRepository;
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(TransactionCreatedEvent event) {
        TransactionView transactionView = new TransactionView();
        transactionView.setId(event.getId());
        transactionView.setLastUpdate(LocalDateTime.now());
        transactionView.setStatus(event.getPaidStatus());
        List<ProductSalesView> productSalesViews = new ArrayList<>();
        transactionView.setProductSalesView(productSalesViews);
        try {
            transactionRepository.save(transactionView);            
        } catch (Exception e) {
            throw new RuntimeException("Failed to save transaction: " + e.getMessage(), e);
        }

        event.getProducts()
                .forEach(product -> {
                    String productSalesId = "product-sales" + System.currentTimeMillis();
                    ProductView productView = productRepository.findById(product.get("id").toString())
                            .orElseThrow(
                                    () -> new RuntimeException("Product not found: " + product.get("id").toString()));
                    if (productView.getStock() < (Integer) product.get("quantity")) {
                        throw new IllegalArgumentException("Insufficient stock for product ID: " + productView.getId());
                    }

                    ProductSalesView productSalesView = new ProductSalesView();
                    productSalesView.setId(productSalesId);
                    productSalesView.setProduct(productView);
                    productSalesView.setQuantity((Integer) product.get("quantity"));
                    productSalesView.setTransactionView(transactionView);
                    productSalesViews.add(productSalesView);
                    try {
                        productSalesRepository.save(productSalesView);
                        commandGateway.send(new SellProductCommand(
                                productView.getId(), 
                                (Integer) product.get("quantity")));
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to save product sales: " + e.getMessage(), e);
                    }
                });
        transactionView.setProductSalesView(productSalesViews);
        try {
            transactionRepository.save(transactionView);            
        } catch (Exception e) {
            throw new RuntimeException("Failed to save transaction: " + e.getMessage(), e);
        }
    }
}
