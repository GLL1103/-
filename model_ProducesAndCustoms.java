
import java.util.ArrayList;
import java.util.List;

//商品类
class Goods {
    //商品名称
    private String goodsName;
    //商品个数
    private int count;

    //生产者生产方法
    public synchronized void setGoods(String goodsName) {
        while(count == 10) {
            System.out.println("商品还有剩余，请等待 ...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.goodsName = goodsName;
        this.count++;
        System.out.println(Thread.currentThread().getName()+" 生产 "+toString());
        //唤醒全部消费者线程进行消费，否则会造成死锁
        notifyAll();
    }

    //消费者消费方法
    public synchronized void getGoods() {
        while(this.count == 0) {
            System.out.println("暂时还没有商品出售，请等待 ...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.count--;
        System.out.println(Thread.currentThread().getName()+" 消费 "+toString());
        //唤醒所有生产者线程进行生产，否则会造成死锁
        notifyAll();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsName='" + goodsName + '\'' +
                ", count=" + count +
                '}';
    }
}

//生产者类
class Produces implements Runnable {
    private Goods goods;
    public Produces(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while(true) {
            this.goods.setGoods(" MAC口红 ");
        }
    }
}

//消费者类
class Customs implements Runnable {
    private Goods goods;
    public Customs(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while(true) {
            this.goods.getGoods();
        }
    }
}
public class Model_ProducesAndCustoms {
    public static void main(String[] args) {
        Goods goods = new Goods();
        Produces produces = new Produces(goods);
        Customs customs = new Customs(goods);

        List<Thread> list = new ArrayList<>();
        //创建五个生产者线程
        for(int i = 0;i<5;++i) {
            Thread thread = new Thread(produces,"生产者"+i);
            list.add(thread);
        }
        //创建五个消费者线程
        for(int i = 0;i<5;++i) {
            Thread thread = new Thread(customs,"消费者"+i);
            list.add(thread);
        }
        //启动生产者，消费者线程
        for(Thread thread : list) {
            thread.start();
        }
    }
}
