package Heap;

public class Main {
    public static void main(String args[]){
        MinHeap heap = new MinHeap(7);

        heap.add(10);
        heap.add(9);
        heap.add(8);
        heap.add(1);
        heap.iterate();
    }
}
