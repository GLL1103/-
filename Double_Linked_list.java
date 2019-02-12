 
interface ILink {
    void add(Object obj);
    boolean delete(int index);
    boolean setData(Object obj,int index);
    Object getData(int index);
    int checkData(Object obj);
    void clear();
    void printLink();
    int length();
    Object[] toArray();
}

class LinkImpl implements ILink {
    private Node last;
    private Node first;
    private int size = 0;

    //节点  私有内部类，只能被外部类LinkImpl使用
    private class Node {
        private Node next;
        private Node pre;
        private Object data;
         public Node(Node pre,Object data,Node next){
             this.pre = pre;
             this.next = next;
             this.data = data;
         }
    }

    @Override
    //插入新节点（尾插）
    public void add(Object obj) {
        Node temp = new Node(this.last,obj,null);
        if(null == this.first){
            this.first = temp;
        }
        else{
            this.last.next = temp;
        }
        this.last = temp;
        this.size++;
    }

    @Override
    //删除指定位置的节点（index从1开始），删除成功返回true，否则返回false
    public boolean delete(int index) {
        //删除位置不合法
        if(this.size == 0 || index > this.size || index <= 0)
            return false;
        //删除头结点
        if(index == 1) {
            this.first = this.first.next;
            this.first.pre = null;
        }
        //删除尾节点
        else if(index == this.size) {
            this.last = this.last.pre;
            this.last.next = null;
        }
        //删除中间节点
        else{
            Node node = this.first;
            while((--index) >0){
                node = node.next;
            }
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        --this.size;
        return true;
    }

    @Override
    //将位置为index的节点的值更改为obj，更改成功返回true，否则返回false
    public boolean setData(Object obj, int index) {
        if(!isLinkElement(index))
            return false;
        Node node = node(index);
        node.data = obj;
        return true;
    }

    @Override
    //获取位置为index节点的值，若位置不合法返回null
    public Object getData(int index) {
        if(!isLinkElement(index))
            return null;
        Node ret = node(index);
        return ret.data;
    }

    @Override
    //检查链表中是否存在值为obj的节点，若存在，返回节点位置（从1开始），若不存在，返回0
    public int checkData(Object obj) {
        int index = 0;
        Node node = this.first;
        while(null != node){
            ++index;
            if(obj.equals(node.data))
                return index;
            node = node.next;
        }
        return 0;
    }

    @Override
    //清除链表
    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    //打印链表（从头到尾）
    public void printLink() {
        Node node = this.first;
        while(null != node){
            System.out.println(node.data);
            node = node.next;
        }
    }

    @Override
    //获取链表长度
    public int length() {
        return this.size;
    }

    @Override
    //将链表转换为数组
    public Object[] toArray() {
        Object[] temp = new Object[this.size];
        Node node = this.first;
        for(int i = 0;i<this.size;++i){
            temp[i] = node.data;
            node = node.next;
        }
        return temp;
    }

    //仅用于LinkImpl类内部使用的方法
    //返回位置为index的节点，若位置不合法，返回null
    private Node node(int index){
        if(index<=0 || index>size)
            return null;

        if(index < (this.size>>1)){
            Node node = this.first;
            while((--index)>0){
                node = node.next;
            }
            return node;
        }

        Node node = this.last;
        index = this.size-index+1;
        while((--index)>0){
            node = node.pre;
        }
        return node;
    }
    //判断指定位置是否合法
    private boolean isLinkElement(int index){
        return index>0 && index <= this.size;
    }
}

public class List {
    public static void main(String[] args) {
        ILink link = new LinkImpl();
        link.add("节点1");
        link.add("节点2");
        link.add("节点3");
        link.add("节点4");
        link.add("节点5");
        link.add("节点6");
        link.printLink();
        System.out.println(link.length());
        System.out.println("**********************");

        System.out.println(link.getData(0));
        System.out.println(link.getData(3));
        System.out.println("**********************");

        System.out.println(link.checkData("节点100"));
        System.out.println(link.checkData("节点3"));
        System.out.println("**********************");

        System.out.println(link.setData("节点three",3));
        System.out.println(link.checkData("节点3"));
        link.printLink();
        System.out.println("**********************");

        System.out.println(link.delete(3));
        System.out.println(link.delete(1));
        System.out.println(link.delete(link.length()));
        link.printLink();
        System.out.println("**********************");

        Object[] array = link.toArray();
        for(int i = 0;i<array.length;++i) {
            System.out.println(array[i]);
        }

        link.clear();
        System.out.println(link.length());
    }
}
