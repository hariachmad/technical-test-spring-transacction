package technical.transaction_usecase.controller.dto.product;

public record GetProductResponse(
    String id,
    String name,
    int price,
    int stock
){}