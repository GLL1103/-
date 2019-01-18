
//饿汉式单例
class SingLeton{
    //在类中实例化该类的唯一对象
    private static final SingLeton SINGLETON = new SingLeton();
    //构造方法私有化，使得类外不能实例化对象
    private SingLeton(){};
    //通过该静态方法使得外部类可以有该类的唯一一个对象
    public static SingLeton getSingLeton(){
        return SINGLETON;
    }
}

//懒汉式单例
class Sing_Leton{
    private static Sing_Leton SING_LETON = null;
    private Sing_Leton(){};
    public static Sing_Leton getSing_Leton(){
        //在第一次调用时产生唯一实例化对象
        if(null == SING_LETON){
            SING_LETON = new Sing_Leton();
        }
        return SING_LETON;
    }
}

public class SingLeton_two {
    public static void main(String[] args) {
        SingLeton singLeton1 = SingLeton.getSingLeton();
        System.out.println(singLeton1);
        SingLeton singLeton2 = SingLeton.getSingLeton();
        System.out.println(singLeton2);

        Sing_Leton singLeton3 = Sing_Leton.getSing_Leton();
        System.out.println(singLeton3);
        Sing_Leton singLeton4 = Sing_Leton.getSing_Leton();
        System.out.println(singLeton4);
    }
}