package technical.transaction_usecase.controller.dto;

public record CreateOrderRequest(
    String name,
    int price
) {
    public CreateOrderRequest {
        if (price <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
}