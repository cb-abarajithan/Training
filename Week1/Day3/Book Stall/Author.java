/**
 * Weel1 (Day3) - Author for BookStall program
 */
public class Author {

    private String name;
    private String email;
    private char gender;

    public Author(String name, String email, char gender){
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender(){
        return gender;
    }

    public String toDisplay(){
        return name+ " ("+gender+") at "+email;
    }

    public static void main(String[] args) {
        
        Author author = new Author("R.K.Narayan","rknarayan@gmail.com",'M');
        System.out.println(author.toDisplay());

        author.setEmail("rkn@gmail.com");
        System.out.println(author.toDisplay());

    }
    
}