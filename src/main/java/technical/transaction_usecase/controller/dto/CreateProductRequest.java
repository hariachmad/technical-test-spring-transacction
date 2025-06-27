package technical.transaction_usecase.controller.dto;

public record CreateProductRequest(
    String name,
    int price
) {
    public CreateProductRequest {
        if (price <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
}