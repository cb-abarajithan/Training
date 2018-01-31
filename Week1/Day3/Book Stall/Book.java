import java.util.Arrays;
import java.util.Scanner;
/**
 * Week1 (Day3) - Book for BookStall program
 */
public class Book {

    private String name;
    private Author[] authors;
    private double price;
    private int qtyInStock;

    public Book(String name, Author[] authors, double price,int qtyInStock){
        this.name = name;
        this.authors = authors;
        this.price = price;
        this.qtyInStock = qtyInStock;
    }

    public Book(String name, Author author, double price,int qtyInStock){
        this.name = name;
        this.authors = new Author[1];
        this.authors[0] = author;
        this.price = price;
        this.qtyInStock = qtyInStock;
    }

    public String getName() {
        return name;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public double getPrice() {
        return price;
    }

    public int getQtyInStock() {
        return qtyInStock;
    }

    public void setQtyInStock(int qtyInStock) {
        this.qtyInStock = qtyInStock;
    }

    public String toDisplay(){
        StringBuilder builder = new StringBuilder();
        for (Author author : authors){
            builder.append(String.format("%s by %s (%c) at %s\n", name, author.getName(), author.getGender(), author.getEmail()));
        }
        builder.append("Price: "+price+"\n");
        builder.append("No of books available: "+qtyInStock+"\n");
        return builder.toString();
    }

    public void printAuthors(){
        for (Author author : authors){
            System.out.println(author.getName());
        }
    }

    public void addAuthor(Author author){
        int currSize = authors.length;
        authors = Arrays.copyOf(authors, currSize+1);
        authors[currSize] = author;
    }

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter book name, price and quantity in stock:");
        String bName = scan.nextLine();
        double price = scan.nextDouble();
        int qty = scan.nextInt();

        Book book = new Book(bName, new Author[0], price, qty);

        System.out.println("Enter authors (name, email, gender): ");
        char choice = 'y';
        do{

            scan.nextLine();
            String aName = scan.nextLine();
            String email = scan.nextLine();
            char gender = scan.next(".").charAt(0);

            book.addAuthor(new Author(aName, email, gender));

            System.out.print("Continue adding? (y/n): ");
            choice = scan.next(".").charAt(0);

        }while(choice != 'n');

        System.out.println("\nAuthors: ");
        book.printAuthors();

        System.out.println("\nBook details: ");
        System.out.println(book.toDisplay());

    }
    
}