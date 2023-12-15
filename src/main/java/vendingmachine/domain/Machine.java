package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.message.ErrorMessage;

import java.util.Arrays;
import java.util.EnumMap;

public class Machine {

    private EnumMap<Coin, Integer> coins = new EnumMap<>(Coin.class);

    public Machine(int amount) {
        validate(amount);
        initCoins();

        int totalAmount = 0;
        while (totalAmount != amount) {
            int randomCoinAmount = Randoms.pickNumberInList(Coin.getCoinsAmount());
            if (amount < totalAmount + randomCoinAmount) {
                continue;
            }
            totalAmount += randomCoinAmount;
            coins.put(Coin.findCoinByAmount(randomCoinAmount), coins.get(Coin.findCoinByAmount(randomCoinAmount)) + 1);
        }
    }

    private void validate(int amount) {
        if (amount % 10 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INDIVISIBLE_AMOUNT.getErrorMessage());
        }
    }

    private void initCoins() {
        Arrays.stream(Coin.values())
                .forEach(coin -> coins.put(coin, 0));
    }

    public EnumMap<Coin, Integer> getCoins() {
        return coins;
    }
}
