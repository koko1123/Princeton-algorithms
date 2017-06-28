package queues;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;

    public RandomizedQueue() {

    }                // construct an empty randomized queue
    public boolean isEmpty() {
        return true;
    }                // is the queue empty?
    public int size() {
        return 0;
    }                       // return the number of items on the queue
    public void enqueue(Item item) {

    }          // add the item
    public Item dequeue() {
        return null;
    }                    // remove and return a random item
    public Item sample() {
        return null;
    }                    // return (but do not remove) a random item
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Item next() {
                return null;
            }
        };
    }         // return an independent iterator over items in random order
    public static void main(String[] args) {

    }  // unit testing (optional)
}
