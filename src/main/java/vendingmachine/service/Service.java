package vendingmachine.service;

import vendingmachine.domain.Machine;
import vendingmachine.dto.MachineDto;

import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private static Service service = new Service();
    private Machine machine;

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
}
