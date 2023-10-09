package step.learning.async;

import java.util.Locale;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TaskDemo {
    private final ExecutorService threadPool = Executors.newSingleThreadExecutor(); //Executors.newFixedThreadPool(12) ;

    public void run() {
        long t1 = System.nanoTime();
        Future<String> task1 = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println( "Task 1 start" ) ;
                Thread.sleep( 1000 ) ;
                return "Task 1 finish";
            }
        });

        for(int i = 0; i < 10; i++){
            printNumber(10 + i);
        }
        Future<String> supplyTask = CompletableFuture.supplyAsync(supplier, threadPool).thenApply(continuation).thenApply(continuation2);
        Future<?> task2 = CompletableFuture.supplyAsync(supplier2).thenApply(continuation2).thenAccept(accepter);
        CompletableFuture.supplyAsync(() -> {
            System.out.println( "Task supply start" ) ;
            try {
                Thread.sleep( 1000 ) ;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Task supply finish";
        });
        try {
            String res = task1.get() ;
            System.out.println( res );
            System.out.println(supplyTask.get());
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }


        try {
            threadPool.shutdown();
            threadPool.awaitTermination(50000, TimeUnit.MILLISECONDS);
            threadPool.shutdownNow();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long t2 = System.nanoTime();

        System.out.println( "Main finish " + (t2 - t1) / 1e9) ;
    }

    private Future<?> printNumber( int num ) {
        return threadPool.submit(
            () -> {
                try {
                    System.out.println("Task start from mumber: " + num);
                    Thread.sleep(1000);
                    System.out.println("Number: " + num);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        );
    }

    private final Supplier<String> supplier = () ->{
        System.out.println();
        System.out.println( "Task supply start" ) ;
        try {
            Thread.sleep( 1000 ) ;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Task supply finish";
    };

    private final Supplier<String> supplier2 = new Supplier<String>() {
        @Override
        public String get() {
            try {
                Thread.sleep( 1000 ) ;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Supplier 2";
        }
    };

    private Function<String, String> continuation = str -> str + "continued";

    private Function<String, String> continuation2 = new Function<String, String>() {
        @Override
        public String apply(String s) {
            return s + " continuation2";
        }
    };

    private final Consumer<String> accepter = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println(s + " accepted");
        }
    };
}
/*
Багатозадачність - використання об'єктів рівня мови/платформи (Future, Task, Promise)
 */