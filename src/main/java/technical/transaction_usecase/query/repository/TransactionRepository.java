package technical.transaction_usecase.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import technical.transaction_usecase.query.model.TransactionView;

public interface TransactionRepository extends JpaRepository<TransactionView, String> {
}
