import java.util.Dictionary;

/**
 * Week1 (Day4) - Car class of Car Demo
 */
class Car {

    private int speed;
    private double regularPrice;
    private String color;

    public Car(int speed, double regularPrice, String color){
        this.speed = speed;
        this.regularPrice = regularPrice;
        this.color = color;
    }

    public double getSalePrice(){
        return regularPrice;
    }

    public String getColor() {
        return color;
    }
    
}

/**
 * Truck class of Car Demo
 */
class Truck extends Car{

    private int weight;

    public Truck(int speed, double regularPrice, String color,int weight){
        super(speed,regularPrice,color);
        this.weight = weight;
    }

    public double getSalePrice(){
        double discount;
        if(weight > 2000){
            discount = 0.10 * super.getSalePrice();
        }else{
            discount = 0.20 * super.getSalePrice();
        }
        return super.getSalePrice()  - discount;
    }

}

/**
 * Ford class of Car Demo
 */
class Ford extends Car{

    private int year;
    private int manufacturerDiscount;

    public Ford(int speed, double regularPrice, String color,int year,int md){
        super(speed,regularPrice,color);
        this.year = year;
        this.manufacturerDiscount = md;
    }

    public double getSalePrice(){
        double discount = (manufacturerDiscount/100d) * super.getSalePrice();
        return super.getSalePrice() - discount;
    }

}

/**
 * Sedan class of Car Demo
 */
class Sedan extends Car{

    private int length;

    public Sedan(int speed, double regularPrice, String color,int length){
        super(speed,regularPrice,color);
        this.length = length;
    }

    public double getSalePrice(){
        double discount;
        if(length > 20){
            discount = 0.05 * super.getSalePrice();
        }else{
            discount = 0.10 * super.getSalePrice();
        }
        return super.getSalePrice()  - discount;
    }

}

/**
 * MyOwnAutoShop class of Car Demo, Main class.
 */
public class MyOwnAutoShop {

    public static void main(String[] args) {
        
        Sedan sedan = new Sedan(90, 1500000, "Black", 20);

        Ford redFord = new Ford(100, 2000000, "Red", 2015, 15);
        Ford greyFord = new Ford(120, 2200000, "Grey", 2017, 17);

        Car car = new Car(70, 100000, "White");

        System.out.printf("%s Sedan price: %.2f\n",sedan.getColor(),sedan.getSalePrice());

        System.out.printf("%s Ford price: %.2f\n",redFord.getColor(),redFord.getSalePrice());
        System.out.printf("%s Ford price: %.2f\n",greyFord.getColor(),greyFord.getSalePrice());

        System.out.printf("%s Car price: %.2f\n",car.getColor(),car.getSalePrice());

    }
    
}