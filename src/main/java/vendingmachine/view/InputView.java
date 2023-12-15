package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static InputView inputView = new InputView();
    private static final String READ_MACHINE_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String READ_MERCHANDISE = "상품명과 가격, 수량을 입력해 주세요.";
    private static final String READ_USER_AMOUNT = "투입 금액을 입력해 주세요.";

    private InputView() {
    }

    public static InputView getInstance() {
        return inputView;
    }

    public String readMachineAmount() {
        System.out.println(READ_MACHINE_AMOUNT);
        return Console.readLine();
    }

    public String readMerchandise() {
        System.out.println(READ_MERCHANDISE);
        return Console.readLine();
    }

    public String readUserAmount() {
        System.out.println(READ_USER_AMOUNT);
        return Console.readLine();
    }
}
