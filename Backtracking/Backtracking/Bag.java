package Backtracking;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Solution> implements Iterable<Solution> {
    private Node<Solution> first;    // beginning of bag
    private int n;                   // number of elements in bag

    // helper linked list class
    private static class Node<Solution> {
        private Solution item;
        private Node<Solution> next;
    }
    public Bag() {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void add(Solution solution) {
        Node<Solution> oldFirst = first;
        first = new Node<>();
        first.item = solution;
        first.next = oldFirst;
        n++;
    }
    public Iterator<Solution> iterator() {
        return new ListIterator<>(first);
    }

    private class ListIterator<Solution> implements Iterator<Solution> {
        private Node<Solution> current;

        public ListIterator(Node<Solution> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Solution next() {
            if (!hasNext()) throw new NoSuchElementException();
            Solution item = current.item;
            current = current.next;
            return item;
        }
    }


}
