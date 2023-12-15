package vendingmachine.service;

import vendingmachine.domain.Machine;
import vendingmachine.domain.Merchandise;
import vendingmachine.dto.MachineDto;
import vendingmachine.dto.MerchandiseDto;
import vendingmachine.repository.MerchandiseRepository;

import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private static Service service = new Service();
    private static final MerchandiseRepository merchandiseRepository = MerchandiseRepository.getInstance();
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

    public void createMerchandise(List<MerchandiseDto> merchandiseDtos) {
        for (MerchandiseDto dto : merchandiseDtos) {
            Merchandise merchandise = new Merchandise(dto.getName(), dto.getPrice(), dto.getQuantity());
            merchandiseRepository.save(merchandise);
        }
    }
}
