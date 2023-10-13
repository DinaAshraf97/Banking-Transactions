package banking;

public class BalanceInquiry extends Transaction {
    String currencyType;
    Account acc=new Account();

    @Override
    public double execute() {
        System.out.println(acc.balance+" "+currencyType);
        return 0;
    }
}
