
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

class NewBeeLock implements Lock {
    private Sync sync = new Sync();

    static class Sync extends AbstractQueuedSynchronizer {
        @Override
        //规定同步状态为1
        protected boolean tryAcquire(int arg) {
            if(arg != 1){
                throw new RuntimeException("arg不为1");
            }
            if(compareAndSetState(0,1)){
                //当前线程成功获取到锁，将当前线程ID写入对象头
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
    //阻塞机制
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
            //通过sleep()方法在debug模式下简单查看线程状态
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
