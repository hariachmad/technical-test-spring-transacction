package technical.transaction_usecase.controller;

import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import technical.transaction_usecase.command.command.transaction.CreateTransactionCommand;
import technical.transaction_usecase.command.command.transaction.PaidTransactionCommand;
import technical.transaction_usecase.command.common.PaidStatusEnum;
import technical.transaction_usecase.controller.dto.transaction.CreateTransactionRequest;
import technical.transaction_usecase.controller.dto.transaction.PaidTransactionRequest;
import technical.transaction_usecase.query.service.TransactionService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final CommandGateway commandGateway;
    private final TransactionService transactionService;

    public TransactionController(CommandGateway commandGateway,TransactionService transactionService) {
        this.commandGateway = commandGateway;
        this.transactionService = transactionService;
    }

    @PostMapping
    public String createTransaction(@RequestBody CreateTransactionRequest request) {
        String id = "transaction-" + System.currentTimeMillis();
        commandGateway.send(new CreateTransactionCommand(id,request.products(), PaidStatusEnum.UNPAID));
        return "Transaction created: " + id;
    }

    @PostMapping("/paid")
    public String paidTransaction(@RequestBody PaidTransactionRequest request) {
        System.out.println("TransactionAggregate.handle: " + request.id());
        commandGateway.send(new PaidTransactionCommand(request.id(),PaidStatusEnum.PAID));
        return "Transaction paid: " + request.id();
    }

    @GetMapping
    public List<Map<String,Object>> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
    
}
