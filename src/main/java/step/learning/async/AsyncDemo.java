package step.learning.async;

import java.util.Locale;
import java.util.concurrent.CountDownLatch;

public class AsyncDemo {
    public void run() {
        System.out.println( "Async demo" ) ;
        //multiThreadDemo();
        Hw2();
        /*
        int months = 12;
        Thread[] threads = new Thread[months];
        sum = 100.0;
        activeThreadCount = months;
        for (int i = 0; i < 12; i++){

            threads[i] = new Thread(new MonthRate(i + 1));
            threads[i].start();
        }*/
        /*
        try {
            for (int i = 0; i < months; i++){
                threads[i].join();
            }
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        System.out.printf(Locale.US,"----------------%ntotal: %.2f%n", sum);
         */
    }
    private double sum;
    private Object sumLocker = new Object();
    private int activeThreadCount; // количество потоков которые еще не закончились
    private Object atcLocker = new Object();
    class MonthRate implements Runnable{
        public MonthRate(int month) {
            this.month = month;
        }

        private int month;
        @Override
        public void run() {

            try {
                Thread.sleep(1000); // иммитация запроса
            } catch (InterruptedException ignored) {}
            double localSum; // локальные переменные - не являются общими для разных потоков
            double p = 0.1; // иммитация результата запроса
            // "добавляем свой результат к общей сумме
            synchronized (sumLocker) { // "закрити" SumLocker
                localSum = // сохраняем локальную копию результата
                sum = sum * (1 + p);
            }
            System.out.printf(Locale.US,"month: %02d, percent: %.2f, sum: %.2f%n", month, p, localSum);
            synchronized (atcLocker){
                activeThreadCount--;
                if(activeThreadCount == 0){
                    System.out.printf(Locale.US,"----------------%ntotal: %.2f%n", sum);
                }
            }
        }
    }
    private void multiThreadDemo(){
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("Thread starts");
                            Thread.sleep(2000);
                            System.out.println("Thread finishes");
                        }
                        catch (InterruptedException ex){
                            System.err.println("Sleeping broken" + ex.getMessage());
                        }
                    }
                }
        );
        try {
            thread.join();
        }
        catch (InterruptedException ex){
            System.err.println("Thread joining interrupted" + ex.getMessage());
        }
        //      thread.start();
        System.out.println("multiThreadDemo() finishes");
    }

    private static final Object lock = new Object();
    private static final StringBuilder result = new StringBuilder();
    private static int digitCount = 0;
    private void Hw2(){
        for (int i = 0; i <= 9; i++) {
            final int digit = i;
            Thread thread = new Thread(() -> {
                synchronized (lock) {
                    result.append(digit);
                    digitCount++;
                    if (digitCount == 10) {
                        System.out.println(result.toString());
                    }
                }
            });
            thread.start();
        }
    }

    private void Hw(){
        CountDownLatch latch = new CountDownLatch(3);

        Thread thread1 = new Thread(() -> {
            System.out.println("1 start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1 finish");
            latch.countDown();
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("2 start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2 finish");
            latch.countDown();
        });

        Thread thread3 = new Thread(() -> {
            System.out.println("3 start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("3 finish");
            latch.countDown();
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("final");
    }
}
