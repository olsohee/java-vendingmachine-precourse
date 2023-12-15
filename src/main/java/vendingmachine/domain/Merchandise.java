package vendingmachine.domain;

import vendingmachine.message.ErrorMessage;

public class Merchandise implements Comparable<Merchandise> {

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
            throw new IllegalArgumentException(ErrorMessage.INVALID_MERCHANDISE_QUANTITY.getErrorMessage());
        }
    }

    public void buy(int userAmount) {
        if (quantity < 1) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_STOCK.getErrorMessage());
        }
        if (price > userAmount) {
            throw new IllegalArgumentException(ErrorMessage.INSUFFICIENT_AMOUNT.getErrorMessage());
        }

        quantity--;
    }

    @Override
    public int compareTo(Merchandise other) {
        return this.price - other.price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
