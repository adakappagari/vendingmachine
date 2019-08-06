package vendingmachine;

public class VendingMachineFactory {
    public static VendingMachine getInstance(){
        return new VendingMachineImpl();
    }
}
