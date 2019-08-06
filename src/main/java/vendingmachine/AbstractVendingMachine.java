package vendingmachine;

import java.io.IOException;

public abstract class AbstractVendingMachine  {
    public void processRequest() throws Exception{
        readCode();
        collectMoney();
        tenderChange();
        tenderProduct();
    }
    protected abstract void readCode() throws IOException;
    protected abstract void collectMoney() throws IOException;
    protected abstract void tenderChange();
    protected abstract void tenderProduct();
}
