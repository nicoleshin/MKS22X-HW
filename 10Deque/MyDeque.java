public class MyDeque {

    private String[] data;
    private int front, end, size;

    public MyDeque() {
        data = new String[10];
        end = 4;
        front = 4;
        size = 0;
    }

    public void addFirst(String s) {
        if (s == null) {
            throw new NullPointerException();
        }
        if (size == data.length) {
            grow();
        }
        if (front == 0) {
            front = data.length - 1;
        } else {
            front--;
        }
        data[front] = s;
        size++;
        if (size == 1) {
            end = front;
        }
    }

    public void addLast(String s) {
        if (s == null) {
            throw new NullPointerException();
        }
        if (size == data.length) {
            grow();
        }
        if (end == data.length-1) {
            end = 0;
        } else {
            end++;
        }
        data[end] = s;
        size++;
        if (size == 1) {
            front = end;
        }
    }

    private void grow() {
        String[] newList = new String[size*2];
        for (int i = 0; i < size; i++) {
            newList[i]= data[(front+i)%size];
        }
        front = 0;
        end = size-1;
        data = newList;
    }

    public String removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        String ret;
        if (front == data.length-1) {
            front = 0;
            size--;
            return data[data.length-1];
        } else {
            front++;
            size--;
            return data[front-1];
        }
    }

    public String removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (end == 0) {
            end = data.length-1;
            size--;
            return data[0];
        } else {
            end--;
            size--;
            return data[front+1];
        }
    }

    public String getFirst(String s) {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return data[front];
    }

    public String getLast(String s) {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return data[end];
    }
}
