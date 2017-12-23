package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {

        int firstHash = System.identityHashCode(o1);
        int secondHash = System.identityHashCode(o2);

        Object firstMonitor = firstHash > secondHash ? o1 : o2;
        Object secondMonitor = firstHash > secondHash ? o2 : o1;


        Thread thread = new Thread() {
            @Override
            public void run() {
                solution.someMethodWithSynchronizedBlocks(o1, o2);
            }
        };
        thread.start();
        Thread.sleep(1);
        if(thread.getState().equals(Thread.State.RUNNABLE))
        //do something here
        {return true;}
        else
        return false;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
