import java.util.Iterator;

/**
 * Week1 (Day5) -  Factorial class for Factorial demo
 */
public class Factorial implements Iterable {

    private int lowerLimit;
    private int upperLimit;

    private int[] elements;
    private int size;

    Factorial(int l, int u){
        this.lowerLimit = l;
        this.upperLimit = u;
        this.size = upperLimit - lowerLimit + 1;
        this.elements = new int[size];
        populate();
    }

    private void populate(){
        for(int i=lowerLimit;i<=upperLimit;i++){
            elements[i - lowerLimit] = i;
        }
    }

    private int calc(){
        int f = 1;
        for(int i=0;i<size;i++){
            f *= elements[i];
        }
        return f;
    }

    public FactorialIterator iterator(){
        return new FactorialIterator();
    }

    public String toString(){
        return lowerLimit + ", " + upperLimit +", "+ calc();
    }

    class FactorialIterator implements Iterator<Integer> {

        private int current;

        public boolean hasNext(){
            return current < size;
        }

        public Integer next(){
            return elements[current++];
        }

        public void remove(){
            throw new UnsupportedOperationException("Removal is not suppported for factorial.");
        }

    }

    public static void main(String[] args) {
        
        if(args.length < 2){
            System.out.print("Usage : ");
            System.out.println("java Factorial <lower> <upper>");
            System.exit(-1);
        }

        int l = Integer.parseInt(args[0]);
        int u = Integer.parseInt(args[1]);
        Factorial factorial = new Factorial(l, u);

        FactorialIterator fi = factorial.iterator();

        int f = 1;
        while(fi.hasNext()){
            f *= fi.next();
        }
        System.out.println("Factorial is: " + f);
        System.out.println("Result: " + factorial);

    }

}