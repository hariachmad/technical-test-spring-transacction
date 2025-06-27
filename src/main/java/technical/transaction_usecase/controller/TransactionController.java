package technical.transaction_usecase.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import technical.transaction_usecase.command.command.transaction.CreateTransactionCommand;
import technical.transaction_usecase.command.common.PaidStatusEnum;
import technical.transaction_usecase.controller.dto.transaction.CreateTransactionRequest;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final CommandGateway commandGateway;

    public TransactionController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createTransaction(@RequestBody CreateTransactionRequest request) {
        String id = "transaction-" + System.currentTimeMillis();
        commandGateway.send(new CreateTransactionCommand(id,request.products(), PaidStatusEnum.UNPAID));
        return "Transaction created: " + id;
    }
}
