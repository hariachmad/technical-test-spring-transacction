package technical.transaction_usecase.query.service;

import java.util.List;

import org.springframework.stereotype.Service;

import technical.transaction_usecase.query.model.TransactionView;
import technical.transaction_usecase.query.repository.TransactionRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    
    public List<TransactionView> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }


}
