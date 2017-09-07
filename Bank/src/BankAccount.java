import java.util.ArrayList;
import java.util.Random;


public class BankAccount {
    //instance variables
    private int accountNumber;
    private String name;
    private double balance;


    //static properties
    private static double interest = 0.3;
    private static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

    public static int objectCounter=0;



    //The first constructor receives a name and creates bank account with balance = 0
    public  BankAccount (String name) {

        Random rand=new Random();
        objectCounter++;

        this.name=name;
        this.accountNumber= rand.nextInt(900000)+100000;
        this.balance=0;
    }

    //The second constructor receives name and balance, creates a bank amount and updates balance
    public BankAccount (String name, double balance) {
        objectCounter++;

        this.name=name;
        Random rand=new Random();
        this.accountNumber= rand.nextInt(900000)+100000;
        this.balance=balance;
    }




    //This method performs deposit operation
    public double deposit(double money) {

        if(money <0){return -1;}

        this.balance += money;

        return money;
    }

    //This method performs withdraw operation
    public double withdrawMoney(double money) {
        double resultMoney=0;
        resultMoney = money;

        if(money < 0){return -1;}

        if(money>this.balance){return -1;}

        if((this.balance-money) <0){return -1;}
        else{
            this.balance -= money;
            money=this.balance;
        }

        return resultMoney;
    }

    //This method returns account number
    public int getAccountNumber() {
        return accountNumber;
    }

    //This method returns interest rate
    public static double getInterestRate() {
        double interestRate=0;

        interestRate=(interest - (objectCounter/5)*.02);


        return interestRate;
    }

    //This method performs a transfer operation to a single bank account
    public double transfer(BankAccount destinationBankAccount, double amount) {

        if(destinationBankAccount==null){
            return -1;
        }

        if(amount<0){
            return -1;
        }

        if(this.balance<amount){
            return -1;
        }



        destinationBankAccount.deposit(amount);
        this.withdrawMoney(amount);

        return amount;
    }



    //This method performs a transfer operation to multiple accounts
    public double transfer(BankAccount[] destinationBankAccount, double amount) {

        int counter=0;




            if(destinationBankAccount != null) //case 2
            {
                for(BankAccount a : destinationBankAccount)
                    if(a != null);
            }
            else{
                return -1;
            }


        if(this.balance<destinationBankAccount.length*amount){return -1;}//return at begining| case 1



        if(amount<0){return -1;}//case 3

        if(destinationBankAccount.length==0){return -1;}//case 4


        for(int i =0;i<destinationBankAccount.length;i++){
            destinationBankAccount[i].deposit(amount);
            counter++;
        }

        double finalResult=amount*counter;

        this.withdrawMoney(amount*counter);

        return finalResult;

    }

    public static void main(String args[]) {

        BankAccount q;
        q = new BankAccount("q", 100);

        BankAccount w;
        w = new BankAccount("w", 20);

        BankAccount e;
        e = new BankAccount("e", 40);

        BankAccount[] accounts = {w, e};

        System.out.println(q.transfer(accounts, 50));
    }
}