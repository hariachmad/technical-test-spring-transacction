package technical.transaction_usecase.controller;

import java.util.List;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import technical.transaction_usecase.command.command.product.AppendStockProductCommand;
import technical.transaction_usecase.command.command.product.CreateProductCommand;
import technical.transaction_usecase.controller.dto.AppendStockProductRequest;
import technical.transaction_usecase.controller.dto.CreateProductRequest;
import technical.transaction_usecase.controller.dto.product.GetProductResponse;
import technical.transaction_usecase.query.model.ProductView;
import technical.transaction_usecase.query.service.ProductService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final CommandGateway commandGateway;
    private final ProductService productService;

    public ProductController(CommandGateway commandGateway, ProductService productService) {
        this.commandGateway = commandGateway;
        this.productService = productService;
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

    // @PostMapping("/sell")
    // public String sellProduct(@RequestBody SellProductRequest request) {
    //     commandGateway.send(new SellProductCommand(request.id(), request.quantity()));
    //     return "Product sold: " + request.id();
    // }

    @GetMapping
    public List<GetProductResponse> getAllProducts() {
        List<ProductView> products = productService.getAllProducts();
        return products.stream()
                .map(product -> new GetProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock()))
                .toList();
    }

    @PostMapping("/append-stock-product")
    public String appendStockProduct(@RequestBody AppendStockProductRequest request) {
        commandGateway.send(new AppendStockProductCommand(request.id(), request.quantity()));
        return "Product stock appended: " + request.id();
    }

}
