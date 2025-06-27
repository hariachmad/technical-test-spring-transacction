package technical.transaction_usecase.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import technical.transaction_usecase.command.command.CreateProductCommand;
import technical.transaction_usecase.controller.dto.CreateOrderRequest;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final CommandGateway commandGateway;

    public ProductController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@RequestBody CreateOrderRequest request) {
         String orderId = "product-" + System.currentTimeMillis();
        commandGateway.send(new CreateProductCommand(
            orderId,
            request.name(),
            request.price(),
            0
        ));
        return "Order created: " + orderId;
    }

}
