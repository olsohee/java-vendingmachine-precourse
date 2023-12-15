package vendingmachine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static List<Integer> getCoinsAmount() {
        return Arrays.stream(Coin.values())
                .map(coin -> coin.getAmount())
                .collect(Collectors.toList());
    }

    public static Coin findCoinByAmount(int amount) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.getAmount() == amount)
                .findAny().get();
    }

    public int getAmount() {
        return amount;
    }
}
