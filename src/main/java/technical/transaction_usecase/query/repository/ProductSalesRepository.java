package technical.transaction_usecase.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import technical.transaction_usecase.query.model.ProductSalesView;

public interface ProductSalesRepository extends JpaRepository<ProductSalesView, String> {
    
}
