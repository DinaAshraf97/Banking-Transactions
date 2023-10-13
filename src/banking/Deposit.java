package banking;

public class Deposit extends Transaction{
    double amount;
Account acc = new Account();
    @Override
    public double execute() {
        acc.balance=acc.balance+amount;
        return acc.balance;
    }
}
