import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//继承Thread类
class MyThread1 extends Thread {
    private int ticket = 10;
    public void run() {
        for(int i = 0;i<10;++i) {
            System.out.println("还剩下"+ticket--+"票");
        }
    }
}
//实现Runnable接口
class MyThread2 implements Runnable {
    private int ticket = 10;
    public void run() {
        for(int i = 0;i<10;++i) {
            System.out.println("还剩下"+ticket-- +"票");
        }
    }
}
//实现Callable<>接口
class MyThread3 implements Callable<String> {
    private int ticket = 10;
    @Override
    public String call() throws Exception {
        for(int i = 0;i<10;++i){
            System.out.println("还剩下"+ticket--+"票");
        }
        return "票卖完了";
    }
}
public class MyManyThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //直接通过Thread类的start方法启动线程
        MyThread1 mt1 = new MyThread1();
        Thread threadA = new Thread(mt1);
        Thread threadB = new Thread(mt1);
        threadA.start();
        threadB.start();

        //通过Thread类的构造方法将Runnable接口对象转换为Thread类对象，调用start方法启动线程
        MyThread2 mt2 = new MyThread2();
        Thread threadC = new Thread(mt2);
        Thread threadD = new Thread(mt2);
        threadC.start();
        threadD.start();

        /**
         * 1.实例化Callable对象
         * 2.通过FutureTask的构造方法将Callable对象转换为FutureTask对象(FutureTask同时实现了Runnable接口和Future接口)
         * 3.通过futureTask（Runnable对象）调用start方法启动线程
         */
        Callable<String> callable = new MyThread3();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        //通过Future接口中的get方法获取call方法的返回值
        System.out.println(futureTask.get());
    }
}
