import java.util.Comparator;


public class MaxArrayDeque<T>  extends ArrayDeque<T> {


    private Comparator<T> comp;

    public MaxArrayDeque() {}
    public MaxArrayDeque(Comparator<T> c) {
        comp = c;
    }

    public T max(){
        if(size == 0){
            return null;
        }
        T max = get(first + 1);

        for(T i: this){
            if(comp.compare(i, max) > 0){
                max = i;
            }
        }
        return max;
    }

    public T max(Comparator<T> c){
        T max = get(first+1);
        for(int i = 0; i< size(); i++){
            if(c.compare(get(i),max)>0){
                max = get(i);
            }
        }
        return max;
    }

}
