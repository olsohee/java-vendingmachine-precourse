package vendingmachine.dto;

public class MachineDto {

    private final int coin;
    private final int quantity;

    public MachineDto(int coin, int quantity) {
        this.coin = coin;
        this.quantity = quantity;
    }

    public int getCoin() {
        return coin;
    }

    public int getQuantity() {
        return quantity;
    }
}
