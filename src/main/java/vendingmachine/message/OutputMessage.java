package vendingmachine.message;

public enum OutputMessage {

    START_MACHINE_INFO("자판기가 보유한 동전"),
    MACHINE_INFO("%d원 - %d개")
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
