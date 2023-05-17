//Author : Younes Bouhaba
//Date submitted : 30/03/2023
//Comp 352

public class Main {
    public static void main(String[] args) {
        AFPQ minHeap = new AFPQ(true);

        // 1. Insert values into min-heap
        AFPQ.Entry entry1 = minHeap.insert(10, "A");
        AFPQ.Entry entry2 = minHeap.insert(5, "B");
        AFPQ.Entry entry3 = minHeap.insert(15, "C");
        AFPQ.Entry entry4 = minHeap.insert(20, "D");
        AFPQ.Entry entry5 = minHeap.insert(3, "E");

        // 2. Check size and state of min-heap
        System.out.println("Size: " + minHeap.size()); // Size: 5
        System.out.println("State: " + minHeap.state()); // State: Min

        // 3. Check top of min-heap
        System.out.println("Top: " + minHeap.top().getValue()); // Top: E

        // 4. Remove top from min-heap
        System.out.println("Removed Top: " + minHeap.removeTop().getValue()); // Removed Top: E

        // 5. Check size after removing top
        System.out.println("Size: " + minHeap.size()); // Size: 4

        // 6. Toggle min-heap to max-heap
        minHeap.toggle();

        // 7. Check state of max-heap
        System.out.println("State: " + minHeap.state()); // State: Max

        // 8. Check top of max-heap
        System.out.println("Top: " + minHeap.top().getValue()); // Top: D

        // 9. Insert values into max-heap
        AFPQ.Entry entry6 = minHeap.insert(1, "F");
        AFPQ.Entry entry7 = minHeap.insert(25, "G");
        AFPQ.Entry entry8 = minHeap.insert(30, "H");

        // 10. Check size of max-heap
        System.out.println("Size: " + minHeap.size()); // Size: 7

        // 11. Remove an entry from the middle of max-heap
        System.out.println("Removed Entry: " + minHeap.remove(entry2).getValue()); // Removed Entry: B

        // 12. Replace key of an entry
        System.out.println("Old Key: " + minHeap.replaceKey(entry6, 2)); // Old Key: 1

        // 13. Replace value of an entry
        System.out.println("Old Value: " + minHeap.replaceValue(entry7, "New G")); // Old Value: G

        // 14. Check top of max-heap after changes
        System.out.println("Top: " + minHeap.top().getValue()); // Top: H

        // 15. Toggle max-heap back to min-heap
        minHeap.toggle();

        // 16. Check state of min-heap
        System.out.println("State: " + minHeap.state()); // State: Min

        // 17. Check top of min-heap after changes
        System.out.println("Top: " + minHeap.top().getValue()); // Top: F

        // 18. Remove all remaining entries
        minHeap.removeTop();
        minHeap.removeTop();
        minHeap.removeTop();
        minHeap.removeTop();
        minHeap.removeTop();
        minHeap.removeTop();

        // 19. Check size of min-heap after removing entries
        System.out.println("Size: " + minHeap.size()); // Size: 0

        // 20. Check if the min-heap is empty
        System.out.println("Is Empty: " + minHeap.isEmpty()); // Is Empty: true
    }
}