interface ISubject {
    void buyLipstick();
}
class realSubject implements ISubject {
    public void buyLipstick() {
        System.out.println("买个mac口红");
    }
}
class proxySubject implements ISubject {
    //代理需要知道真实的客户
    private ISubject realSubject;
    public proxySubject(ISubject subject){
        //与真实用户建立联系
        this.realSubject = subject;
    }
    public void before(){
        System.out.println("拿钱，排队");
    }
    public void after(){
        System.out.println("发货，验收");
    }
    public void buyLipstick() {
        //辅助操作
        this.before();
        //真实业务的操作
        this.realSubject.buyLipstick();
        //辅助操作
        this.after();
    }
}

public class Proxy {
    public static void main(String[] args) {
        //产生一个代理类，并且与真实类产生联系
        ISubject subject = new proxySubject(new realSubject());
        subject.buyLipstick();
    }
}
