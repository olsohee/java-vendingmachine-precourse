package vendingmachine.controller;

import vendingmachine.service.Service;
import vendingmachine.utils.InputConvertor;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MainController {

    private final InputView inputView = InputView.getInstance();
    private final InputConvertor inputConvertor = InputConvertor.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private final Service service = Service.getInstance();

    public void run() {
        createMachine();
    }

    private void createMachine() {
        try {
            int amount = inputConvertor.convertStringToInt(inputView.readMachineAmount());
            service.createMachine(amount);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            createMachine();
        }
    }
}
