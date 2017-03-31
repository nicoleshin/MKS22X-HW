import java.lang.IllegalArgumentException;

public class MyLinkedList {

    public static void main(String[] args) {
        MyLinkedList l = new MyLinkedList();
        l.add(0);
        l.add(1);
        l.add(2);
        l.add(4);
        l.add(3, 3);
        l.add(0, 100);
        l.add(l.size(), 100);
        System.out.println(l);
        l.remove(3);
        System.out.println(l);
    }

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

    public int indexOf(int value) {
        LNode curr = start;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (curr.getValue() == value) {
                return index;
            }
            curr = curr.getNext();
            index++;
        }
        return -1;
    }

    public void add(int val) {
        if (size == 0) {
            start.setValue(val);
        } else {
            LNode n = new LNode(val);
            end.setNext(n);
            end = n;
        }
        size++;
    }

    public void add(int index, int val) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("INDEX INVALID");
        }

        LNode n = new LNode(val);
        if (index == 0) {
            n.setNext(start);
            start = n;
            size++;
        } else if (index == size) {
            add(val);
        } else {
            LNode curr = start;
            LNode prev = null;
            for (int i = 0; i < index; i++) {
                prev = curr;
                curr = curr.getNext();
            }
            prev.setNext(n);
            n.setNext(curr);
            size++;
        }
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("INDEX INVALID");
        }

        LNode curr = start;
        if (index == 0) {
            start = start.getNext();
        } else {
            LNode prev = null;
            for (int i = 0; i < index; i++) {
                prev = curr;
                curr = curr.getNext();
            }
            if (index == size-1) {
                prev.setNext(null);
                end = prev;
            } else {
                prev.setNext(curr.getNext());
            }
        }
        size--;
        return curr.getValue();
    }

    public int get(int index) {
        LNode curr = start;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr.getValue();
    }

    public int set(int index, int newVal) {
        LNode curr = start;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        int temp = curr.getValue();
        curr.setValue(newVal);
        return temp;
    }

    public int size() {
        return size;
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        }
        String ret = "[";
        LNode curr = start;
        for (int i = 0; i < size; i++) {
            System.out.println(curr.getValue());
            ret += curr.getValue() + ",";
            curr = curr.getNext();
        }
        return ret.substring(0, ret.length()-1) + "]";
    }

    private class LNode {
        LNode next, previous;
        int value;
        public LNode(int v) {
            this(v, null);
        }

        public LNode(int v, LNode n) {
            next = n;
            value = v;
        }

        public void setNext(LNode n) {
            next = n;
        }

        public void setValue(int v) {
            value = v;
        }

        public LNode getNext() {
            return next;
        }

        public int getValue() {
            return value;
        }
    }
}
