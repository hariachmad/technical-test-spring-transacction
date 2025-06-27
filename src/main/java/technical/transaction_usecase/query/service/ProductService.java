package technical.transaction_usecase.query.service;

import java.util.List;

import org.springframework.stereotype.Service;

import technical.transaction_usecase.query.model.ProductView;
import technical.transaction_usecase.query.repository.ProductRepository;

@Service
public class ProductService{
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }   

    public List<ProductView> getAllProducts(){
        return productRepository.findAll();
    }

    public ProductView getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }
}