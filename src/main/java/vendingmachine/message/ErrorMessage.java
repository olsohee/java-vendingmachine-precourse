package vendingmachine.message;

public enum ErrorMessage {

    NOT_NUMBER_AMOUNT("금액은 숫자여야 합니다."),
    INDIVISIBLE_AMOUNT("금액은 10원 단위여야 합니다."),
    DUPLICATED_MERCHANDISE_NAME("상품명은 중복될 수 없습니다."),
    INVALID_MERCHANDISE_PRICE("상품은 100원 이상이며 10원 단위여야 합니다."),
    INVALID_MERCHANDISE_QUANTITY("상품은 1개 이상이어야 합니다."),
    NOT_FOUND_MERCHANDISE("존재하지 않는 상품입니다."),
    OUT_OF_STOCK("재고가 없는 상품입니다."),
    INSUFFICIENT_AMOUNT("금액이 부족하여 해당 상품은 구매할 수 없습니다.")
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
