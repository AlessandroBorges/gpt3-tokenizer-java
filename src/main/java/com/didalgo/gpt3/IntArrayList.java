package com.didalgo.gpt3;

import java.util.*;

/**
 * A implementation of a dynamic array of integers.
 * It uses a int[] array to store the elements.
 */
public class IntArrayList implements Iterable<Integer>
// implements List<Integer>
{
    private int[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 512;

    public IntArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public IntArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        this.array = new int[initialCapacity];
        this.size = 0;
    }

    /**
     * Add a new integer value to the list.
     * @param e - new Integer element to add
     * @return
     */
    public boolean add(int e) {
        if (size == array.length) {
            ensureCapacity();
        }
        array[size++] = e;
        return true;
    }

    /**
     * Add a new element to the list.
     * It is equivalent to add(int e), but return false if the element is null.
     * @param e the element to add
     * @return true if the element was added, false otherwise
     */
    public boolean add(Integer e) {
        if(e==null)
            return false;
        return add(e.intValue());
    }

    public void add(int index, int element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (size == array.length) {
            ensureCapacity();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    private void ensureCapacity() {
        int newCapacity = array.length * 2;
        array = Arrays.copyOf(array, newCapacity);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(int o) {
        int value = ((Integer) o).intValue();
        for (int i = 0; i < this.size; i++) {
            if (this.array[i] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Implements Iterable<Integer> interface.
     */
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int index = 0;

            public boolean hasNext() {
                return index < size;
            }

            public Integer next() {
                if (index >= size) {
                    throw new NoSuchElementException();
                }
                return Integer.valueOf(array[index++]);
            }
        };
    }

    public int[] toArray() {
        return Arrays.copyOf(array, size);
    }

    public int[] toArray(int[] a) {
        if (a.length < size) {
            return (int[]) Arrays.copyOf(array, size);
        }
        return a;
    }

    public int get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return array[index];
    }

    public int set(int index, int element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int oldValue = array[index];
        array[index] = element;
        return oldValue;
    }

    public int indexOf(int o) {
        int value = ((Integer) o).intValue();
        for (int i = 0; i < this.size; i++) {
            if (this.array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(int o) {
        int value = ((Integer) o).intValue();
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    
   public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i == size - 1) {
                sb.append(']');
            } else {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
