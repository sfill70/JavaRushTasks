package com.javarush.task.task17.task1720;
//-- /В РЕШЕНИИ СИНХРОНИЗИРОВАЛИ МЕТОД deposit в классе BankAccount
public class Bankomat {

    static BankAccount account = new BankAccount("Amigo");

    public static volatile boolean isStopped;

    public static void main(String[] args) throws InterruptedException {
      //  System.out.println(isStopped);
        addMoney.start();
        SpendThread spendThread = new SpendThread();
        SpendThread spendThread1 = new SpendThread();
        SpendThread spendThread2 = new SpendThread();
        spendThread.start();
        spendThread1.start();
        spendThread2.start();
        Thread.sleep(4000);
        isStopped = true;
    }

    private  static  Thread addMoney = new Thread() {
        @Override

            public void run () {
            while (!isStopped) {
                account.deposit("100");            //В РЕШЕНИИ СИНХРОНИЗИРОВАЛИ МЕТОД deposit в классе BankAccount кладем на счет
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

    };


    public static class SpendThread extends Thread {

        @Override
        public void run() {



                while (!isStopped) {
                    try {
                        account.withdraw("1000");//снимаем со счета
                        /*System.out.println(getName());*/
                    } catch (NotEnoughMoneyException e) {
                        System.out.println("Недостаточно денег");
                        /*System.out.println(getName());*/
                    }
                    try {
                        Thread.sleep(1100);
                    } catch (InterruptedException e) {
                        break;
                    }
                }


        }
    }
}
