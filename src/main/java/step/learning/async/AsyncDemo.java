package step.learning.async;

import java.util.concurrent.CountDownLatch;

public class AsyncDemo {
    public void run() {
        System.out.println( "Async demo" ) ;
        //multiThreadDemo();
        Hw();
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
