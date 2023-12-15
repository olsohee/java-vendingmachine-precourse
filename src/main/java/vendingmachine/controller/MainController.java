package vendingmachine.controller;

import vendingmachine.dto.MerchandiseDto;
import vendingmachine.service.Service;
import vendingmachine.utils.InputConvertor;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

import java.util.List;

public class MainController {

    private final InputView inputView = InputView.getInstance();
    private final InputConvertor inputConvertor = InputConvertor.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private final Service service = Service.getInstance();

    public void run() {
        createMachine();
        createMerchandise();
        createUserAmount();

        while (!service.isEnd()) {
            buy();
        }

        printResult();
    }

    private void createMachine() {
        try {
            int amount = inputConvertor.convertStringToInt(inputView.readMachineAmount());
            service.createMachine(amount);
            outputView.printMachine(service.getMachineDto());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            createMachine();
        }
    }

    private void createMerchandise() {
        try {
            List<MerchandiseDto> merchandiseDtos = inputConvertor.convertStringToMerchandiseDtos(inputView.readMerchandise());
            service.createMerchandise(merchandiseDtos);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            createMerchandise();
        }
    }

    private void createUserAmount() {
        try {
            int userAmount = inputConvertor.convertStringToInt(inputView.readUserAmount());
            service.createUser(userAmount);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            createUserAmount();
        }
    }

    private void buy() {
        try {
            outputView.printUserAmount(service.getUserAmountDto());
            service.buy(inputView.readBuyMerchandise());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            buy();
        }
    }

    private void printResult() {
        outputView.printUserAmount(service.getUserAmountDto());
        outputView.printChanges(service.getChangesDto());
    }
}
