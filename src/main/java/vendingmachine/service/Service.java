package vendingmachine.service;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.Coin;
import vendingmachine.domain.Machine;
import vendingmachine.domain.Merchandise;
import vendingmachine.domain.User;
import vendingmachine.dto.ChangeDto;
import vendingmachine.dto.MachineDto;
import vendingmachine.dto.MerchandiseDto;
import vendingmachine.dto.UserAmountDto;
import vendingmachine.message.ErrorMessage;
import vendingmachine.repository.MerchandiseRepository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service {

    private static Service service = new Service();
    private static final MerchandiseRepository merchandiseRepository = MerchandiseRepository.getInstance();
    private Machine machine;
    private User user;

    private Service() {
    }

    public static Service getInstance() {
        return service;
    }

    public void createMachine(int amount) {
        machine = new Machine(amount);
    }

    public List<MachineDto> getMachineDto() {
        return machine.getCoins().keySet().stream()
                .map(coin -> new MachineDto(coin.getAmount(), machine.getCoins().get(coin)))
                .collect(Collectors.toList());
    }

    public void createMerchandise(List<MerchandiseDto> merchandiseDtos) {
        validateDuplicated(merchandiseDtos);
        for (MerchandiseDto dto : merchandiseDtos) {
            Merchandise merchandise = new Merchandise(dto.getName(), dto.getPrice(), dto.getQuantity());
            merchandiseRepository.save(merchandise);
        }
    }

    private void validateDuplicated(List<MerchandiseDto> merchandiseDtos) {
        int nonDuplicatedCount = (int) merchandiseDtos.stream()
                .map(dto -> dto.getName())
                .collect(Collectors.toList()).stream()
                .distinct()
                .count();
        if (nonDuplicatedCount != merchandiseDtos.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_MERCHANDISE_NAME.getErrorMessage());
        }
    }

    public void createUser(int userAmount) {
        user = new User(userAmount);
    }

    public UserAmountDto getUserAmountDto() {
        return new UserAmountDto(user.getAmount());
    }

    public boolean isEnd() {
        return user.cannotBuy(merchandiseRepository.getMinPrice()) || merchandiseRepository.isSoldOut();
    }

    public void buy(String merchandiseName) {
        Merchandise merchandise = merchandiseRepository.findByName(merchandiseName);
        merchandise.buy(user.getAmount());
        user.reduceAmount(merchandise.getPrice());
    }

    public ChangeDto getChangesDto() {
        Map<Integer, Integer> changes = new LinkedHashMap<>();
        int totalChanges = 0;

        int amount = user.getAmount();

        outer: for (Coin coin : machine.getCoins().keySet()) {
            Integer coinCount = machine.getCoins().get(coin);

            while(coinCount > 0) {
                if (totalChanges + coin.getAmount() > amount) {
                    break;
                }
                if (totalChanges == amount) {
                    break outer;
                }
                totalChanges += coin.getAmount();
                changes.put(coin.getAmount(), changes.getOrDefault(coin.getAmount(), 0) + 1);
                coinCount--;
            }
        }
        return new ChangeDto(changes);
    }
}
