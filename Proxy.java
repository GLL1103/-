interface ISubject {
    void buyLipstick();
}
class realSubject implements ISubject {
    public void buyLipstick() {
        System.out.println("���mac�ں�");
    }
}
class proxySubject implements ISubject {
    //������Ҫ֪����ʵ�Ŀͻ�
    private ISubject realSubject;
    public proxySubject(ISubject subject){
        //����ʵ�û�������ϵ
        this.realSubject = subject;
    }
    public void before(){
        System.out.println("��Ǯ���Ŷ�");
    }
    public void after(){
        System.out.println("����������");
    }
    public void buyLipstick() {
        //��������
        this.before();
        //��ʵҵ��Ĳ���
        this.realSubject.buyLipstick();
        //��������
        this.after();
    }
}

public class Proxy {
    public static void main(String[] args) {
        //����һ�������࣬��������ʵ�������ϵ
        ISubject subject = new proxySubject(new realSubject());
        subject.buyLipstick();
    }
}
