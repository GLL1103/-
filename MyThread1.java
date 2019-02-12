import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//�̳�Thread��
class MyThread1 extends Thread {
    private int ticket = 10;
    public void run() {
        for(int i = 0;i<10;++i) {
            System.out.println("��ʣ��"+ticket--+"Ʊ");
        }
    }
}
//ʵ��Runnable�ӿ�
class MyThread2 implements Runnable {
    private int ticket = 10;
    public void run() {
        for(int i = 0;i<10;++i) {
            System.out.println("��ʣ��"+ticket-- +"Ʊ");
        }
    }
}
//ʵ��Callable<>�ӿ�
class MyThread3 implements Callable<String> {
    private int ticket = 10;
    @Override
    public String call() throws Exception {
        for(int i = 0;i<10;++i){
            System.out.println("��ʣ��"+ticket--+"Ʊ");
        }
        return "Ʊ������";
    }
}
public class MyManyThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //ֱ��ͨ��Thread���start���������߳�
        MyThread1 mt1 = new MyThread1();
        Thread threadA = new Thread(mt1);
        Thread threadB = new Thread(mt1);
        threadA.start();
        threadB.start();

        //ͨ��Thread��Ĺ��췽����Runnable�ӿڶ���ת��ΪThread����󣬵���start���������߳�
        MyThread2 mt2 = new MyThread2();
        Thread threadC = new Thread(mt2);
        Thread threadD = new Thread(mt2);
        threadC.start();
        threadD.start();

        /**
         * 1.ʵ����Callable����
         * 2.ͨ��FutureTask�Ĺ��췽����Callable����ת��ΪFutureTask����(FutureTaskͬʱʵ����Runnable�ӿں�Future�ӿ�)
         * 3.ͨ��futureTask��Runnable���󣩵���start���������߳�
         */
        Callable<String> callable = new MyThread3();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        //ͨ��Future�ӿ��е�get������ȡcall�����ķ���ֵ
        System.out.println(futureTask.get());
    }
}
