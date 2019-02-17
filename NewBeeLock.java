
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

class NewBeeLock implements Lock {
    private Sync sync = new Sync();

    static class Sync extends AbstractQueuedSynchronizer {
        @Override
        //�涨ͬ��״̬Ϊ1
        protected boolean tryAcquire(int arg) {
            if(arg != 1){
                throw new RuntimeException("arg��Ϊ1");
            }
            if(compareAndSetState(0,1)){
                //��ǰ�̳߳ɹ���ȡ����������ǰ�߳�IDд�����ͷ
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        @Override
        protected boolean tryRelease(int arg) {
            if(getState() == 0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }
    }
    @Override
    public void lock() {
        sync.acquire(1);
    }
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,time);
    }
    @Override
    public void unlock() {
        sync.release(1);
    }
    @Override
    //��������
    public Condition newCondition() {
        return null;
    }
}

class MyThread implements Runnable {
    Lock lock = new NewBeeLock();
    @Override
    public void run() {
        try {
            lock.lock();
            //ͨ��sleep()������debugģʽ�¼򵥲鿴�߳�״̬
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}

public class TestNewBeeLock {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        for(int i = 0;i<10;++i) {
            Thread thread = new Thread(myThread);
            thread.start();
        }
    }
}
