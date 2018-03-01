/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phone.book.db;

/**
 *
 * @author cb-abarajithan
 */
public class PhoneNumber {
    
    private final long number;
    private final Type type;

    public PhoneNumber(long number, Type type) {
        this.number = number;
        this.type = type;
    }

    public long getNumber() {
        return number;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return number+"("+type.toString()+")";
    }
    
    public enum Type{
        MOBILE(0), WORK(1), HOME(2);
        private final int index;
        Type(int index){
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
        
    }
    
}
