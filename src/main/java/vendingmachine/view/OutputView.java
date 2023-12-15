package vendingmachine.view;

import vendingmachine.dto.MachineDto;
import vendingmachine.message.OutputMessage;

import java.util.List;

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
}
