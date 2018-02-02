import java.util.Random;

/**
 * Week1 (Day5) - Main class
 */
public class Main {

    public static void main(String[] args) {
        
        Random r = new Random();

        Mobile pixel = new Mobile("Google Pixel"){
            public void remainingCharge(){
                System.out.println(r.nextInt(101)+"%");
            }
        };
        pixel.name();
        pixel.remainingCharge();

        Mobile motog = new Mobile("Motorola Moto G"){
            public void remainingCharge(){
                System.out.println(r.nextInt(101)+"%");
            }
        };
        motog.name();
        motog.remainingCharge();

        Mobile samsung = new Mobile("Samsung"){
            public void remainingCharge(){
                System.out.println(r.nextInt(101)+"%");
            }
        };
        samsung.name();
        samsung.remainingCharge();

    }

}

/**
 * Mobile abstract class
 */
class Mobile {

    private String name;
    private int remainingCharge;

    Mobile(String name){
        this.name = name;
    }

    public void name(){
        System.out.println(name);
    }
    public void remainingCharge(){
        System.out.println(remainingCharge);
    }

}