package banking;

 public class Account extends Transaction {
    int accountNumber;
    String ownerName;
    static double balance;

     @Override
     public double execute() {
         return balance;
     }
 }
