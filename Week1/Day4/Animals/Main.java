import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Week1 (Day4) - Characteristics of an animal interface.
 */
interface Characteristics{
    boolean isHerbivores();
    boolean canFly();
    boolean layEggs();
}

/**
 * Animal abstract class.
 */
abstract class Animal implements Characteristics{
    String name;
    public Animal(String name){
        this.name = name;
    }
    public abstract void movement();

    public boolean isHerbivores(){
        return false;
    }

    public boolean canFly(){
        return false;
    }

    public boolean layEggs(){
        return false;
    }

}

/**
 * Mammal class.
 */
class Mammal extends Animal{

    public Mammal(String name){
        super(name);
    }

    public void movement(){
        System.out.println("Walk, Swim or fly.");
    }

}

/**
 * Bird class.
 */
class Bird extends Animal{

    public Bird(String name){
        super(name);
    }

    public void movement(){
        System.out.println("Fly");
    }

    public boolean canFly(){
        return true;
    }

}

/**
 * A Bat class.
 */
class Bat extends Mammal implements Characteristics{

    public Bat(){
        super("Bat");
    }

    public boolean canFly(){
        return true;
    }

}

/**
 * A Dog class.
 */
class Dog extends Mammal implements Characteristics{

    public Dog(){
        super("Dog");
    }

}

/**
 * A Cow class.
 */
class Cow extends Mammal implements Characteristics{

    public Cow(){
        super("Cow");
    }

    public boolean isHerbivores(){
        return true;
    }

}

/**
 * An Ostrich class.
 */
class Ostrich extends Bird implements Characteristics{

    public Ostrich(){
        super("Ostrich");
    }

    public boolean isHerbivores(){
        return true;
    }
    public boolean canFly(){
        return true;
    }
    public boolean layEggs(){
        return true;
    }

}

/**
 * A Parrot class.
 */
class Parrot extends Bird implements Characteristics{

    public Parrot(){
        super("Parrot");
    }

    public boolean isHerbivores(){
        return true;
    }
    public boolean canFly(){
        return true;
    }
    public boolean layEggs(){
        return true;
    }

}

/**
 * The main class.
 */
public abstract class Main {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        System.out.println("Bat-1, Dog-2, Cow-3, Ostrich-4, Parrot-5, Exit - 0");

        int choice;
        do{

            System.out.print("Option: ");
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    animals.add(new Bat());
                    break;
                case 2:
                    animals.add(new Dog());
                    break;
                case 3:
                    animals.add(new Cow());
                    break;
                case 4:
                    animals.add(new Ostrich());
                    break;
                case 5:
                    animals.add(new Parrot());
                    break;
                default:
                    choice = 0;
            }

        }while(choice!=0);

        int n = animals.size();
        System.out.println("\nAnimals that can fly are...");
        for(int i=0;i<n;i++){
            if(animals.get(i).canFly()){
                System.out.println(animals.get(i).name);
            }
        }

        System.out.println("\nAnimals that are Herbivores...");
        for(int i=0;i<n;i++){
            if(animals.get(i).isHerbivores()){
                System.out.println(animals.get(i).name);
            }
        }

        scan.close();

    }
    
}