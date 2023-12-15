package vendingmachine.view;

import vendingmachine.dto.ChangeDto;
import vendingmachine.dto.MachineDto;
import vendingmachine.dto.UserAmountDto;
import vendingmachine.message.OutputMessage;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static OutputView outputView = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return outputView;
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printMachine(List<MachineDto> machineDtos) {
        System.out.println(OutputMessage.START_MACHINE_INFO.getMessage());
        machineDtos.stream()
                .forEach(dto -> System.out.println(String.format
                        (OutputMessage.MACHINE_INFO.getMessage(), dto.getCoin(), dto.getQuantity()))
                );
    }

    public void printUserAmount(UserAmountDto userAmountDto) {
        System.out.println(String.format(OutputMessage.USER_AMOUNT.getMessage(), userAmountDto.getAmount()));
    }

    public void printChanges(ChangeDto changesDto) {
        System.out.println(OutputMessage.CHANGES.getMessage());
        Map<Integer, Integer> changes = changesDto.getChanges();
        changes.keySet().stream()
                .forEach(coin -> System.out.println(String.format(OutputMessage.MACHINE_INFO.getMessage(),
                        coin, changes.get(coin))));
    }
}
