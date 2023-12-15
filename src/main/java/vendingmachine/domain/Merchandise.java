package vendingmachine.domain;

import vendingmachine.message.ErrorMessage;

public class Merchandise {

    private String name;
    private int price;
    private int quantity;

    public Merchandise(String name, int price, int quantity) {
        validatePrice(price);
        validateQuantity(quantity);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    private void validatePrice(int price) {
        if (price < 100 || price % 10 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MERCHANDISE_PRICE.getErrorMessage());
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MERCHANDISE_QUANTIRY.getErrorMessage());
        }
    }
}
