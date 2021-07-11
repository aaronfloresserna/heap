package Heap;

import java.util.Arrays;

public class MinHeap {
    HeapNode[] heap;
    HeapNode root = null;
    int capacity; 
    int size = 0;

    public class HeapNode{
        private int id;

        public HeapNode(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "ID: " + this.id;
        }
    }

    public MinHeap(int capacity) {
        this.heap = new HeapNode[capacity];
        this.capacity = capacity;
    }

    public int getLeftChildIndex (int parentIndex) { return parentIndex * 2 + 1; }
    public int getRightChildIndex (int parentIndex) { return parentIndex * 2 + 2; }
    public int getParentIndex (int childIndex) {return (childIndex - 1) / 2; }

    public boolean hasLeftChild (int parentIndex) { return getLeftChildIndex(parentIndex) > this.size; }
    public boolean hasRightChild (int parentIndex) { return getRightChildIndex(parentIndex) < this.size; }
    public boolean hasParent (int childIndex) { return getParentIndex(childIndex) >= 0; }

    public HeapNode leftChild (int parentIndex) { return heap[getLeftChildIndex(parentIndex)]; }
    public HeapNode rightChild (int parentIndex) {return heap[getRightChildIndex(parentIndex)]; }
    public HeapNode parent (int childIndex) { return heap[getParentIndex(childIndex)]; }

    private void swap(int indexOne ,int indexTwo ){
        HeapNode temp = heap[indexOne];
        heap[indexOne] = heap[indexTwo];
        heap[indexTwo] = temp;
    }

    private void ensureExtraCapacity() {
        if (size == capacity){
            heap = Arrays.copyOf(heap, capacity * 2);
            capacity *= 2;
        }
    }

    public HeapNode peek() {
        if (size == 0) throw new IllegalStateException();
        return root;
    }

    public HeapNode poll() {
        if (size == 0) throw new IllegalStateException();
        HeapNode node = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return node;
    }

    public void add(int id) {
        ensureExtraCapacity();
        HeapNode node = new HeapNode(id);
        heap[size] = node;
        size++;
        heapifyUp();
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && smallerChildIndex > getLeftChildIndex(index))
                smallerChildIndex = getRightChildIndex(index);

            if (heap[smallerChildIndex].id < heap[index].id)
                swap(index, smallerChildIndex);
            else
                break;
        }
    }

    private void heapifyUp() { 
        int index = size - 1;
        HeapNode node = heap[index];
        while (hasParent(index) && parent(index).id > node.id){
            swap(getParentIndex(index), index );
            index = getParentIndex(index);
            node = heap[index];
        }
            
    }

    public void iterate(){
        if (size != 0) {
            for (HeapNode hp : this.heap){
                System.out.println(hp);
            }
        }
    }

}
