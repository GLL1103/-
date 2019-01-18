import java.util.Scanner;

//�򵥹���
interface Computer {
    void printComputer();
}
class Mac implements Computer {
    public void printComputer() {
        System.out.println("This is a mac");
    }
}
class AlienWare implements Computer {
    public void printComputer() {
        System.out.println("This is a AlienWare");
    }
}

//�����࣬�����в���Computer����ʵ���Ĳ��������������
class Factory {
    private Factory(){};
    public static Computer getNewComputer(String str) {
        Computer computer = null;
        if(str.equals("mac")){
            computer = new Mac();
        }
        else if(str.equals("alienWare")){
            computer = new AlienWare();
        }
        return computer;
    }
}

public class Easy_factory {
    public static void main(String[] args) {
        System.out.println("��������Ҫ�ĵ����ͺţ�");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Computer computer = Factory.getNewComputer(str);
        computer.printComputer();
    }

}



//��������
interface Computer {
    void printComputer ();
}
class Mac implements Computer {
    public void printComputer (){
        System.out.println("This is a mac");
    }
}
class AlienWare implements Computer {
    public void printComputer (){
        System.out.println("This is a AlienWare");
    }
}
//���󹤳���
interface Factory {
    Computer createComputer();
}
//����Ʒ��
class msFactory implements Factory {
    public Computer createComputer(){
        return new AlienWare();
    }
}
class macFactory implements Factory{
    public Computer createComputer() {
        return new Mac();
    }
}

public class Factory_Test {
    public static void main(String[] args) {
        Factory factory = new msFactory();
        Computer computer = factory.createComputer();
        computer.printComputer();
    }
}