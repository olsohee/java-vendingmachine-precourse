package vendingmachine.service;

import vendingmachine.domain.Machine;
import vendingmachine.domain.Merchandise;
import vendingmachine.domain.User;
import vendingmachine.dto.MachineDto;
import vendingmachine.dto.MerchandiseDto;
import vendingmachine.dto.UserAmountDto;
import vendingmachine.message.ErrorMessage;
import vendingmachine.repository.MerchandiseRepository;

import java.util.List;
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
        user.reduceAmount();
    }
}
