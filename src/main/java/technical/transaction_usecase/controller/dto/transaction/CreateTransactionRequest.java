package technical.transaction_usecase.controller.dto.transaction;

import java.util.List;
import java.util.Map;

public record CreateTransactionRequest(List<Map<String,Object>> products) {
    public CreateTransactionRequest {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Products cannot be null or empty");
        }
        for (Map<String, Object> product : products) {
            if (!product.containsKey("id") || !(product.get("id") instanceof String) || ((String) product.get("id")).isBlank()) {
                throw new IllegalArgumentException("Product ID cannot be null or blank");
            }
            if (!product.containsKey("quantity") || !(product.get("quantity") instanceof Integer) || (Integer) product.get("quantity") <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            }
        }
    }
}
