package vendingmachine;

public class VendingMachineClient {

    public static void main(String[] args) throws Exception{
        VendingMachineService vendingMachineService = new VendingMachineService();
        vendingMachineService.process();
    }
}
