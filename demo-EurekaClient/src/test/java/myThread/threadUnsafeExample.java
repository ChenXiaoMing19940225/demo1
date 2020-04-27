package myThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadUnsafeExample{
    private int count=0;
    public void add(){
        count++;
    }
    public int get(){
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        final int threadSize=1000;
        threadUnsafeExample thread = new threadUnsafeExample();

        final CountDownLatch latch = new CountDownLatch(threadSize);
                ExecutorService execuror = Executors.newCachedThreadPool();
        for (int i=0;i<threadSize;i++){
            execuror.execute(()->{
                synchronized (o){
                thread.add();
                latch.countDown();
                }
            });
        }
        latch.await();
        execuror.shutdown();



        System.out.println(thread.get());
    }
}
