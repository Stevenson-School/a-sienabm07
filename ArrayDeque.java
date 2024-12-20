import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;
//import java.util.ArrayDeque;
import java.util.Iterator;
//import java.util.HashSet;
//import java.util.Set;
public class ArrayDeque<T> implements Deque<T>, Iterable<T>, Comparator<T>{


    protected T[] arr;
    protected int size;
    public ArrayDeque() {
        arr = (T[]) new Object[8];
        size = 0;
    }
    protected int first=3;
    protected int last=4;
    private int refractor = 2; // needed? I don't think so... will revise later

    public ArrayDeque(int length) {
        arr = (T[]) new Object[length];
        last= 5;
        first =4;
    }

    public void addFirst(T item) {
        resize(1);

        if(first < 0){
            first = arr.length-1;
            arr[first] = item;
            first--;
        }
        else{
            arr[first] = item;
            first--;
        }
        size++;
    }

    protected void resize(int index) {
        if( isEmpty()) {
            return;
        }
        refractor = 1;
        T[] newArr = (T[]) new Object[arr.length * refractor];
        T[] newArr2 = (T[]) new Object[arr.length];
        T[] newArr3 = (T[]) new Object[arr.length * refractor];
        if(size == arr.length) {
            refractor = 2;
            newArr3 = (T[]) new Object[arr.length * refractor];
            int indTrack = first +1;
            if(indTrack == arr.length) {
                indTrack = 0;
            }
            int wierdLastTrack = last -1 ;
            if(wierdLastTrack == -1) {
                wierdLastTrack = arr.length;
            }

            for(int i = 0; i < arr.length; i++) {
                if (indTrack == size) {
                    indTrack = 0;
                }
                newArr2[i] = arr[indTrack];
                indTrack++;
            }

            for (int i = 0; i < newArr3.length; i++) {
                if (i < 4) { // 3?
                    newArr3[i] = null;
                }
                else if(i>= 4 + arr.length) {
                    newArr3[i] = null;
                }
                else {
                    newArr3[i] = newArr2[i-4];
                }
            }
        }
        if(refractor == 2){
            arr = newArr3;
            first = 3;
            last = 4 + newArr2.length;
        }

    }



    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

      @Override
    public int compare(T o1, T o2) {
        return 0;
    }


    public class ArrayDequeIterator implements Iterator<T>{
        int pos;

        public ArrayDequeIterator(){
            pos=0;
        }

        public boolean hasNext(){
            return (pos<size());
        }

        public T next(){
            return arr[pos++];
        }
    }



    @Override
    public boolean equal(Object other){

        if(other == null){
            return false;
        }
        if(other == this){
            return true;
        }
        if(this.getClass() != other.getClass()){
            return false;
        }
        if(other instanceof java.util.ArrayDeque o){
            // [7, 4, 9, 1, 3, 1, 0]
            // [7, 4, 9, 2, 3, 5, 0]
            if(this.size() != o.size()){ return false; }
            for(int i = 0; i < size(); i++){
                if(!o.contains(arr[i])){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString(){
        //curly braces + commas
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < size()-1; i++){
            ret.append(arr[i]+ ",");
        }
        ret.append(arr[size()-1] + "}");
        return ret.toString();
    }


    public void addLast(T item) {
        resize(1);
        if(last == arr.length) {
            last = 0;
            arr[last] = item;
            last++;
        }
        else{
            arr[last] = item;
            last++;
        }
        size++;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int size(){
        return size;
    }

    public T removeFirst() {
        /*
            need work
            currently 3am so I will
            work on this tomorrow
         */
        T removed = null;
        if(first - 1 == 0){
            first = size;
            removed = arr[first];
            arr[first]=null;
        }
        else{
            removed = arr[first+1];
            first++;
            arr[first]=null;
        }
        size--;
        return removed;
    }

    public T removeLast() {
        /*
            need work
            currently 3am so I will
            work on this tomorrow
         */
        T removed = null;
        //int tempLast = last;
        if(last  >= arr.length){
            last=0;
            removed = arr[last];
            arr[last]=null;
        }
        else{
            removed = arr[last-1];
            last--;
            arr[last]=null;
        }
        size--;
        return removed;
    }

    public T get(int index) {
        if( index >= arr.length){
            return null;
        }
        else{
            return arr[index];

        }
    }

    public void printDeque(){

        for(T index: arr){
            System.out.print(index + " ");
        }
    }

    public List<T> toList(){
        List<T> list = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            list.add(get(i));
        }
        return list;
    }


    public static void main(String[] args) {



        Comparator<Double> dc = new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {

                return ((int) (o1-o2));
            }
        };

        MaxArrayDeque<Double> deck = new MaxArrayDeque<>(dc);
        deck.addLast(2.3);
        deck.addLast(0.0);
        deck.addLast(4.7);
        System.out.println(deck.max(dc));



        ArrayDeque<Integer> deque = new ArrayDeque<>();
        System.out.println(deque.isEmpty());
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.printDeque();
        System.out.println();
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        deque.addFirst(10);

        //System.out.println(deque.removeFirst());
        //System.out.println(deque.removeLast());
        deque.printDeque();
    }
}

