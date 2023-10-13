package banking;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    static int x;
    static double lastAmount=0;
    static int flag=0;
    static int selectNum(){
        System.out.println(
                "-\tPress 1 to enter account details\n" +
                        "-\tPress 2 to deposit\n" +
                        "-\tPress 3 to withdraw\n" +
                        "-\tPress 4 to show current balance\n" +
                        "-\tPress 5 to cancel last transaction\n" +
                        "-\tPress 6 to exit\n");
        Scanner scan = new Scanner(System.in);
            x = scan.nextInt();
            return x;
    }
    public static void main(String[] args) {
        int conditionTrue=0;
        while (true){
            Scanner sc = new Scanner(System.in);
            try{
            switch (selectNum()) {
                case 1:
                    System.out.println("enter account details:");
                    Account acc = new Account();
                    System.out.print("Account Number: ");
                    acc.accountNumber = sc.nextInt();
                    System.out.print("Owner Name: ");
                    acc.ownerName = sc.next();
                    System.out.print("Balance: ");
                    acc.balance = sc.nextDouble();
                    System.out.println(acc.execute());
                    conditionTrue = 1;
                    break;

                case 2:
                    if (conditionTrue == 0) {
                        System.out.println("please enter you account details first");
                    } else {
                        System.out.println("enter deposit:");
                        Deposit dp = new Deposit();
                        dp.amount = sc.nextDouble();
                        System.out.println(dp.execute());
                        flag = 1;
                        lastAmount = dp.amount;
                    }
                    break;

                case 3:
                    if (conditionTrue == 0) {
                        System.out.println("please enter you account details first");
                    } else {
                        System.out.println("enter Withdraw:");
                        Withdraw w = new Withdraw();
                        w.amount = sc.nextDouble();
                        Account acc1 = new Account();
                        if (w.amount > acc1.balance)
                            System.out.println("not enough amount");
                        else {
                            System.out.println(w.execute());
                            flag = 2;
                            lastAmount = w.amount;
                        }
                    }
                    break;

                case 4:
                    if (conditionTrue == 0) {
                        System.out.println("please enter you account details first");
                    } else {
                        System.out.println("enter BalanceInquiry E for Euro or U USD:");
                        BalanceInquiry bi = new BalanceInquiry();
                        bi.currencyType = sc.next();
                        if ((bi.currencyType).equals("U") || (bi.currencyType).equals("E"))
                            bi.execute();
                        else
                            System.out.println("invalid currency");
                    }
                    break;

                case 5:
                    if (conditionTrue == 0) {
                        System.out.println("please enter you account details first");
                    }
                    else {
                        if (flag==0)
                            System.out.println("there is no last transactions");
                        else{
                            System.out.println("you are about to cancel last transaction:");
                            new Rollback() {
                                @Override
                                public double cancelTransaction() {
                                    Account a = new Account();
                                    if (flag == 1) {
                                        a.balance = a.balance - lastAmount;
                                    } else if (flag == 2) {
                                        a.balance = a.balance + lastAmount;
                                    }
                                    return a.balance;
                                }
                            }.cancelTransaction();
                            flag=0;
                        }
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using our service");
                    System.exit(0);
                    break;

                default:
                    System.out.println("please enter valid number");
                    break;

            }
        }catch (NoSuchElementException e){
                System.err.println("not valid input");
            }
        }
    }

}
