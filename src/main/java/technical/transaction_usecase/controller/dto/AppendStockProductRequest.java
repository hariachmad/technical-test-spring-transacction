package technical.transaction_usecase.controller.dto;

public record AppendStockProductRequest(
    String id,
    int quantity
) {
    public AppendStockProductRequest {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Product ID cannot be null or blank");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
}
