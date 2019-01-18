
//����ʽ����
class SingLeton{
    //������ʵ���������Ψһ����
    private static final SingLeton SINGLETON = new SingLeton();
    //���췽��˽�л���ʹ�����ⲻ��ʵ��������
    private SingLeton(){};
    //ͨ���þ�̬����ʹ���ⲿ������и����Ψһһ������
    public static SingLeton getSingLeton(){
        return SINGLETON;
    }
}

//����ʽ����
class Sing_Leton{
    private static Sing_Leton SING_LETON = null;
    private Sing_Leton(){};
    public static Sing_Leton getSing_Leton(){
        //�ڵ�һ�ε���ʱ����Ψһʵ��������
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