package vendingmachine.message;

public enum ErrorMessage {

    NOT_NUMBER_AMOUNT("금액은 숫자여야 합니다."),
    INDIVISIBLE_AMOUNT("금액은 10원 단위여야 합니다.")
    ;

    private static final String ERROR_HEADER = "[ERROR]";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return String.format("%s %s", ERROR_HEADER, errorMessage);
    }
}
