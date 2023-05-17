//Author : Younes Bouhaba

import java.util.Comparator;

public class AFPQ {
    // Constants and instance variables
    private static final int DEFAULT_CAPACITY = 10;
    private Entry[] heap;
    private int size;
    private Comparator<Integer> comparator;


    // Constructor: Initializes the heap, size, and comparator based on isMinHeap
    public AFPQ(boolean isMinHeap) {
        heap = new Entry[DEFAULT_CAPACITY];
        size = 0;
        comparator = isMinHeap ? Comparator.naturalOrder() : Comparator.reverseOrder();
    }

    // Entry class representing a key-value pair
    public static class Entry {
        int key;
        Object value;

        // Entry constructor: Initializes the key and value
        public Entry(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        // Get the key of the entry
        public int getKey() {
            return key;
        }

        // Get the value of the entry
        public Object getValue() {
            return value;
        }
    }

    // Ensure the heap has enough capacity by doubling its size if needed
    private void ensureCapacity() {
        if (size == heap.length) {
            Entry[] newHeap = new Entry[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, size);
            heap = newHeap;
        }
    }

    // Remove the top entry of the heap, adjust the heap, and return the removed entry
    public Entry removeTop() {
        if (isEmpty()) {
            return null;
        }

        Entry top = heap[0];
        swap(0, --size);
        downheap(0);

        return top;
    }

    // Insert a key-value pair into the heap, adjust the heap, and return the new entry
    public Entry insert(int key, Object value) {
        ensureCapacity();

        Entry newEntry = new Entry(key, value);
        heap[size] = newEntry;
        upheap(size++);
        return newEntry;
    }

    // Get the top entry of the heap without removing it
    public Entry top() {
        return isEmpty() ? null : heap[0];
    }

    // Remove an entry from the heap, adjust the heap, and return the removed entry
    public Entry remove(Entry e) {
        int index = indexOf(e);
        if (index == -1) {
            return null;
        }

        swap(index, --size);
        downheap(index);
        upheap(index);

        return e;
    }

    // Replace the key of an entry, adjust the heap, and return the old key
    public int replaceKey(Entry e, int newKey) {
        int index = indexOf(e);
        if (index == -1) {
            return -1;
        }

        int oldKey = e.key;
        e.key = newKey;
        downheap(index);
        upheap(index);

        return oldKey;
    }

    // Replace the value of an entry and return the old value
    public Object replaceValue(Entry e, Object newValue) {
        int index = indexOf(e);
        if (index == -1) {
            return null;
        }

        Object oldValue = e.value;
        e.value = newValue;

        return oldValue;
    }

    // Toggle between min-heap and max-heap and rebuild the heap
    public void toggle() {
        comparator = comparator == Comparator.naturalOrder() ? Comparator.reverseOrder() : Comparator.naturalOrder();
        buildHeap();
    }

    // Return the current state of the heap (Min or Max)
    public String state() {
        return comparator == Comparator.naturalOrder() ? "Min" : "Max";
    }

    // Check if the heap is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the current size of the heap
    public int size() {
        return size;
    }

    // Move the entry at the specified index up the heap until it is correctly positioned
    private void upheap(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (comparator.compare(heap[parentIndex].key, heap[index].key) <= 0) {
                break;
            }
            swap(parentIndex, index);
            index = parentIndex;
        }
    }

    // Move the entry at the specified index down the heap until it is correctly positioned
    private void downheap(int index) {
        while (2 * index + 1 < size) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int minChildIndex = leftChildIndex;

            if (rightChildIndex < size && comparator.compare(heap[rightChildIndex].key, heap[leftChildIndex].key) < 0) {
                minChildIndex = rightChildIndex;
            }

            if (comparator.compare(heap[minChildIndex].key, heap[index].key) >= 0) {
                break;
            }

            swap(minChildIndex, index);
            index = minChildIndex;
        }
    }

    // Swap the entries at the specified indices
    private void swap(int i, int j) {
        Entry temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Get the index of a given entry in the heap or return -1 if not found
    private int indexOf(Entry e) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == e) {
                return i;
            }
        }
        return -1;
    }

    // Rebuild the heap from the bottom up to maintain the correct heap structure
    private void buildHeap() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            downheap(i);
        }
    }
}

