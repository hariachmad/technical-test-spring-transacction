package technical.transaction_usecase.query.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import technical.transaction_usecase.query.repository.TransactionRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    
    public List<Map<String,Object>> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }


}
