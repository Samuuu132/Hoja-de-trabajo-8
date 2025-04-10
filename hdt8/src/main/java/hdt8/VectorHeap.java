package hdt8;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    protected ArrayList<E> data;


    public VectorHeap() {
        data = new ArrayList<>();
    }


    public VectorHeap(ArrayList<E> v) {
        data = new ArrayList<>(v);
        for (int i = parent(data.size() - 1); i >= 0; i--) {
            downHeap(i);
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * index + 2;
    }

    @Override
    public void add(E element) {
        data.add(element);
        upHeap(data.size() - 1);
    }

    private void upHeap(int index) {
        int parentIndex = parent(index);
        if (index > 0 && data.get(index).compareTo(data.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            upHeap(parentIndex);
        }
    }


    @Override
    public E remove() {
        if (data.isEmpty()) {
            throw new NoSuchElementException("El heap está vacío");
        }
        E result = data.get(0);
        E last = data.remove(data.size() - 1);
        if (!data.isEmpty()) {
            data.set(0, last);
            downHeap(0);
        }
        return result;
    }


    private void downHeap(int index) {
        int leftIndex = left(index);
        int rightIndex = right(index);
        int smallest = index;

        if (leftIndex < data.size() && data.get(leftIndex).compareTo(data.get(smallest)) < 0) {
            smallest = leftIndex;
        }
        if (rightIndex < data.size() && data.get(rightIndex).compareTo(data.get(smallest)) < 0) {
            smallest = rightIndex;
        }

        if (smallest != index) {
            swap(index, smallest);
            downHeap(smallest);
        }
    }

    private void swap(int i, int j) {
        E temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }


    @Override
    public E peek() {
        if (data.isEmpty()) {
            throw new NoSuchElementException("El heap está vacío");
        }
        return data.get(0);
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
