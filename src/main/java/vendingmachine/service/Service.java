package vendingmachine.service;

import vendingmachine.domain.Machine;

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
}
