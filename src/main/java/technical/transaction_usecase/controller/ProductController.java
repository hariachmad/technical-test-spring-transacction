package technical.transaction_usecase.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import technical.transaction_usecase.command.command.product.AppendStockProductCommand;
import technical.transaction_usecase.command.command.product.CreateProductCommand;
import technical.transaction_usecase.command.command.product.SellProductCommand;
import technical.transaction_usecase.controller.dto.AppendStockProductRequest;
import technical.transaction_usecase.controller.dto.CreateProductRequest;
import technical.transaction_usecase.controller.dto.SellProductRequest;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final CommandGateway commandGateway;

    public ProductController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRequest request) {
         String id = "product-" + System.currentTimeMillis();
        commandGateway.send(new CreateProductCommand(
            id,
            request.name(),
            request.price(),
            0
        ));
        return "Product created: " + id;
    }

    @PostMapping("/sell")
    public String sellProduct(@RequestBody SellProductRequest request) {
        commandGateway.send(new SellProductCommand(request.id(), request.quantity()));
        return "Product sold: " + request.id();
    }

    @PostMapping("/append-stock-product")
    public String appendStockProduct(@RequestBody AppendStockProductRequest request) {
        commandGateway.send(new AppendStockProductCommand(request.id(), request.quantity()));
        return "Product stock appended: " + request.id();
    }

}
