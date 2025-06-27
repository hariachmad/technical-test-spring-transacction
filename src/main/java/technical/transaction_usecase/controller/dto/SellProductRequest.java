package technical.transaction_usecase.controller.dto;

public record SellProductRequest(
    String id,
    int quantity
) {
    public SellProductRequest {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Product ID cannot be null or blank");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
}