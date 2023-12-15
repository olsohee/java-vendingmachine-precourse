package vendingmachine.dto;

public class UserAmountDto {

    private final int amount;

    public UserAmountDto(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
