import java.lang.IllegalArgumentException;
import java.util.*;

public class MyLinkedList implements Iterable<Integer> {
/*
    public static void main(String[] args) {
        MyLinkedList l = new MyLinkedList();
        l.add(0);
        System.out.println(l);
        l.add(1);
        System.out.println(l);
        l.add(2);
        System.out.println(l);
        l.add(4);
        System.out.println(l);
        l.add(3, 3);
        System.out.println(l);
        l.add(0, 100);
        System.out.println(l);
        l.add(l.size(), 100);
        System.out.println(l);
        l.remove(3);
        System.out.println("SHIT WORKS");
        System.out.println(l);
        for (Integer i : l) {
            System.out.println(i);
        }
    }
*/
    private LNode start, end;
    private int size;

    public MyLinkedList() {
        this(0);
    }

    public MyLinkedList(int s) {
        size = s;
        start = new LNode(0);
        end = start;
    }

    private LNode getNode(int index) {
        LNode curr;
        if (index < size/2) {
            curr = start;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else {
            curr = end;
            for (int i = size-1; i > index; i--) {
                curr = curr.previous;
                System.out.print(i-1+",");
                System.out.println(curr.value);
            }
        }
        return curr;
    }

    private void addAfter(LNode location, LNode toBeAdded) {
        LNode after = location.next;
        after.setPrevious(toBeAdded);
        toBeAdded.setNext(after);
        toBeAdded.setPrevious(location);
        location.setNext(toBeAdded);
    }

    public boolean add(int val) {
        add(size, val);
        return true;
    }

    public void add(int index, int val) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("INDEX INVALID");
        }

        LNode n = new LNode(val);
        if (size == 0) {
            start = n;
            end = n;
        } else if (index == 0) {
            n.setNext(start);
            start.setPrevious(n);
            start = n;
        } else if (index == size) {
            end.setNext(n);
            n.setPrevious(end);
            end = n;
        } else {
            addAfter(getNode(index-1), n);
        }
        size++;
    }

    public int indexOf(int value) {
        LNode curr = start;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (curr.value == value) {
                return index;
            }
            curr = curr.next;
            index++;
        }
        return -1;
    }

    private void removeNode(LNode node) {
        LNode after = node.next;
        LNode before = node.previous;
        after.setPrevious(before);
        before.setNext(after);
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("INDEX INVALID");
        }

        LNode node = getNode(index);
        System.out.println(node.value);
        if (index == size-1) {
            end.previous.setNext(null);
            end = end.previous;
        } else if (index == 0) {
            start.next.setPrevious(null);
            start = start.next;
        } else {
            removeNode(node);
        }
        size--;
        return node.value;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("INDEX INVALID");
        }
        return getNode(index).value;
    }

    public int set(int index, int newVal) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("INDEX INVALID");
        }
        LNode n = getNode(index);
        int temp = n.value;
        n.setValue(newVal);
        return temp;
    }

    public int size() {
        return size;
    }

    public Iterator<Integer> iterator() {
        return new LIterator(this);
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        }
        String ret = "[";
        LNode curr = start;
        for (int i = 0; i < size; i++) {
            ret += curr.value + ",";
            curr = curr.next;
        }
        return ret.substring(0, ret.length()-1) + "]";
    }

    private class LIterator implements Iterator<Integer> {
        private MyLinkedList list;
        private LNode current;

        public LIterator(MyLinkedList l) {
            list = l;
            current = l.start;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Integer next() {
            if (hasNext()) {
                int v = current.value;
                current = current.next;
                return v;
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class LNode {
        private LNode next, previous;
        private int value;

        public LNode(int v) {
            this(v, null, null);
        }

        public LNode(int v, LNode n, LNode p) {
            next = n;
            previous = p;
            value = v;
        }

        public void setNext(LNode n) {
            next = n;
        }

        public void setPrevious(LNode p) {
            previous = p;
        }

        public void setValue(int v) {
            value = v;
        }
    }
}
