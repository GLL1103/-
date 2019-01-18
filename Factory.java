import java.util.Scanner;

//简单工厂
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

//工厂类，将所有产生Computer对象实例的操作解耦到工厂类中
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
        System.out.println("请输入你要的电脑型号：");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Computer computer = Factory.getNewComputer(str);
        computer.printComputer();
    }

}



//工厂方法
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
//抽象工厂类
interface Factory {
    Computer createComputer();
}
//各产品族
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