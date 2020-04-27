package myExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class myExecutor {
    public static void main(String[] args){
        ExecutorService service = Executors.newSingleThreadExecutor();
       service.submit(new Runnable() {
           @Override
           public void run() {
               System.out.println("任务1");
           }
       });
        service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务3");
            }
        });
        service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务2");
            }
        });
        service.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        service.shutdown();
    }
}
