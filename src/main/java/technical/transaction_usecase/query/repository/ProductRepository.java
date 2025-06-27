package technical.transaction_usecase.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import technical.transaction_usecase.query.model.ProductView;

public interface ProductRepository extends JpaRepository<ProductView, String> {
    
}
