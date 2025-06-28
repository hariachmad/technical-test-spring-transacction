package technical.transaction_usecase.query.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import technical.transaction_usecase.query.model.TransactionView;

public interface TransactionRepository extends JpaRepository<TransactionView, String> {

    @Query(value = "select trx.status, trx.date,trx.trxId,trx.PS,p.name from  (select t.status,t.last_update as date,t.id as trxId, ps.id as PS, ps.product_id from transactions_view t left join products_sales_view ps on t.id = ps.transaction_id) as trx left join products_view p on trx.product_id = p.id", nativeQuery = true)
    List<TransactionView> getAllTransactions();
}
