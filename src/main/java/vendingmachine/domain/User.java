package vendingmachine.domain;

import vendingmachine.message.ErrorMessage;

public class User {

    private int amount;

    public User(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount % 10 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INDIVISIBLE_AMOUNT.getErrorMessage());
        }
    }

    public void reduceAmount(int amountToReduce) {
        amount -= amountToReduce;
    }
}
