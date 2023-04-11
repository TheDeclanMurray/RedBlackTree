package src;

public class Holder implements Comparable<Holder> {
    
    int value;

    public Holder(int val){
        this.value = val;
    }

    public int compareTo(Holder val){
        if (this.value == val.value){
            return 0;
        }
        if (this.value < val.value){
            return 1;
        }
        return -1;
    }

    public String toString(){
        String rtn = String.format("%2d", this.value);
        return rtn;
    }
}
