package technical.transaction_usecase.controller.dto.transaction;

public record PaidTransactionRequest(
    String id
) {
    public PaidTransactionRequest {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Transaction ID cannot be null or blank");
        }
    }   
}
