 
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

    //�ڵ�  ˽���ڲ��ֻ࣬�ܱ��ⲿ��LinkImplʹ��
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
    //�����½ڵ㣨β�壩
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
    //ɾ��ָ��λ�õĽڵ㣨index��1��ʼ����ɾ���ɹ�����true�����򷵻�false
    public boolean delete(int index) {
        //ɾ��λ�ò��Ϸ�
        if(this.size == 0 || index > this.size || index <= 0)
            return false;
        //ɾ��ͷ���
        if(index == 1) {
            this.first = this.first.next;
            this.first.pre = null;
        }
        //ɾ��β�ڵ�
        else if(index == this.size) {
            this.last = this.last.pre;
            this.last.next = null;
        }
        //ɾ���м�ڵ�
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
    //��λ��Ϊindex�Ľڵ��ֵ����Ϊobj�����ĳɹ�����true�����򷵻�false
    public boolean setData(Object obj, int index) {
        if(!isLinkElement(index))
            return false;
        Node node = node(index);
        node.data = obj;
        return true;
    }

    @Override
    //��ȡλ��Ϊindex�ڵ��ֵ����λ�ò��Ϸ�����null
    public Object getData(int index) {
        if(!isLinkElement(index))
            return null;
        Node ret = node(index);
        return ret.data;
    }

    @Override
    //����������Ƿ����ֵΪobj�Ľڵ㣬�����ڣ����ؽڵ�λ�ã���1��ʼ�����������ڣ�����0
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
    //�������
    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    //��ӡ������ͷ��β��
    public void printLink() {
        Node node = this.first;
        while(null != node){
            System.out.println(node.data);
            node = node.next;
        }
    }

    @Override
    //��ȡ������
    public int length() {
        return this.size;
    }

    @Override
    //������ת��Ϊ����
    public Object[] toArray() {
        Object[] temp = new Object[this.size];
        Node node = this.first;
        for(int i = 0;i<this.size;++i){
            temp[i] = node.data;
            node = node.next;
        }
        return temp;
    }

    //������LinkImpl���ڲ�ʹ�õķ���
    //����λ��Ϊindex�Ľڵ㣬��λ�ò��Ϸ�������null
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
    //�ж�ָ��λ���Ƿ�Ϸ�
    private boolean isLinkElement(int index){
        return index>0 && index <= this.size;
    }
}

public class List {
    public static void main(String[] args) {
        ILink link = new LinkImpl();
        link.add("�ڵ�1");
        link.add("�ڵ�2");
        link.add("�ڵ�3");
        link.add("�ڵ�4");
        link.add("�ڵ�5");
        link.add("�ڵ�6");
        link.printLink();
        System.out.println(link.length());
        System.out.println("**********************");

        System.out.println(link.getData(0));
        System.out.println(link.getData(3));
        System.out.println("**********************");

        System.out.println(link.checkData("�ڵ�100"));
        System.out.println(link.checkData("�ڵ�3"));
        System.out.println("**********************");

        System.out.println(link.setData("�ڵ�three",3));
        System.out.println(link.checkData("�ڵ�3"));
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
