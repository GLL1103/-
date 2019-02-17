
import java.util.ArrayList;
import java.util.List;

//��Ʒ��
class Goods {
    //��Ʒ����
    private String goodsName;
    //��Ʒ����
    private int count;

    //��������������
    public synchronized void setGoods(String goodsName) {
        while(count == 10) {
            System.out.println("��Ʒ����ʣ�࣬��ȴ� ...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.goodsName = goodsName;
        this.count++;
        System.out.println(Thread.currentThread().getName()+" ���� "+toString());
        //����ȫ���������߳̽������ѣ�������������
        notifyAll();
    }

    //���������ѷ���
    public synchronized void getGoods() {
        while(this.count == 0) {
            System.out.println("��ʱ��û����Ʒ���ۣ���ȴ� ...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.count--;
        System.out.println(Thread.currentThread().getName()+" ���� "+toString());
        //���������������߳̽���������������������
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

//��������
class Produces implements Runnable {
    private Goods goods;
    public Produces(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while(true) {
            this.goods.setGoods(" MAC�ں� ");
        }
    }
}

//��������
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
        //��������������߳�
        for(int i = 0;i<5;++i) {
            Thread thread = new Thread(produces,"������"+i);
            list.add(thread);
        }
        //��������������߳�
        for(int i = 0;i<5;++i) {
            Thread thread = new Thread(customs,"������"+i);
            list.add(thread);
        }
        //���������ߣ��������߳�
        for(Thread thread : list) {
            thread.start();
        }
    }
}
