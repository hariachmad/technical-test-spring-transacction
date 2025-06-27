package technical.transaction_usecase.query.projection;

import java.time.LocalDateTime;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import technical.transaction_usecase.command.event.product.ProductAppendStockEvent;
import technical.transaction_usecase.command.event.product.ProductCreatedEvent;
import technical.transaction_usecase.command.event.product.ProductDeletedEvent;
import technical.transaction_usecase.command.event.product.ProductSoldEvent;
import technical.transaction_usecase.command.event.product.ProductUpdatedEvent;
import technical.transaction_usecase.query.common.StatusEnum;
import technical.transaction_usecase.query.model.ProductView;
import technical.transaction_usecase.query.repository.ProductRepository;

@Component
public class ProductProjection {
    private final ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductView productView = new ProductView();
        productView.setId(event.getId());
        productView.setName(event.getName());
        productView.setPrice(event.getPrice());
        productView.setStock(event.getStock());
        productView.setStatus(StatusEnum.CREATED);
        productView.setCreatedAt(LocalDateTime.now());
        productView.setUpdatedAt(LocalDateTime.now());
        productRepository.save(productView);
    }

    @EventHandler
    public void on(ProductSoldEvent event) {
        ProductView productView = productRepository.findById(event.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (productView.getStock() < event.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock for product ID: " + productView.getId());
        }
        productView.setStock(productView.getStock() - event.getQuantity());
        productView.setUpdatedAt(LocalDateTime.now());
        productRepository.save(productView);
    }

    @EventHandler
    public void on(ProductAppendStockEvent event) {
        ProductView productView = productRepository.findById(event.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productView.setStock(productView.getStock() + event.getQuantity());
        productView.setUpdatedAt(LocalDateTime.now());
        productRepository.save(productView);
    }

    @EventHandler
    public void on(ProductUpdatedEvent event) {
        ProductView productView = productRepository.findById(event.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (event.getName() != null)
            productView.setName(event.getName());
        if (event.getPrice() != 0)
            productView.setPrice(event.getPrice());
        if (event.getStock() != 0)
            productView.setStock(event.getStock());
        productView.setStatus(StatusEnum.UPDATED);
        productView.setUpdatedAt(LocalDateTime.now());
        productRepository.save(productView);
    }

    @EventHandler
    public void on(ProductDeletedEvent event) {
        ProductView productView = productRepository.findById(event.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productView.setStatus(StatusEnum.DELETED);
        productView.setUpdatedAt(LocalDateTime.now());
        productRepository.save(productView);
    }
}