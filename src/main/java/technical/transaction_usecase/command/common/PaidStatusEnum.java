package technical.transaction_usecase.command.common;

public enum PaidStatusEnum {
    PAID("PAID"),
    UNPAID("UNPAID");

    private final String status;

    PaidStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
