package queues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    class Element<Item> {
        Element<Item> next;
        Element<Item> previous;
        Item value;

        Element(Item value) {
            this.value = value;
            this.next = null;
            this.previous = null;
        }
    }

    private Element<Item> firstElement;
    private Element<Item> lastElement;
    private int size;

    public Deque() {
        this.firstElement = null;
        this.lastElement = null;
        this.size = 0;
    }                           // construct an empty deque

    public boolean isEmpty() {
        return this.size == 0;
    }               // is the deque empty?

    public int size() {
        return this.size;
    }                       // return the number of items on the deque

    public void addFirst(Item item) {
        Element<Item> newElement = new Element<>(item);
        if (!isEmpty()) {
            this.firstElement.previous = newElement;
            newElement.next = this.firstElement;
        } else {
            this.lastElement = newElement;
        }
        this.firstElement = newElement;
        this.size += 1;
    }         // add the item to the front


    public void addLast(Item item) {
        Element<Item> newElement = new Element<>(item);
        if (!isEmpty()) {
            this.lastElement.next = newElement;
            newElement.previous = this.lastElement;
        } else {
            this.firstElement = newElement;
        }
        this.lastElement = newElement;
        this.size += 1;
    }          // add the item to the end

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty queues.Deque");
        }
        this.size -= 1;
        return null;
    }                // remove and return the item from the front

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty queues.Deque");
        }
        this.size -= 1;
        return null;
    }                // remove and return the item from the end

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
    }        // return an iterator over items in order from front to end

    public static void main(String[] args) {

    }  // unit testing (optional)
}