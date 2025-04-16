package ru.academits.eliseev.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount = 0;

    public ArrayList(){
        //noinspection unchecked
        items = (E[]) new Object[10];
        size = 0;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Некорректное значение размерности массива: " + initialCapacity);
        }

        //noinspection unchecked
        items = (E[]) new Object[initialCapacity];
        size = 0;
    }

    public ArrayList(Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        size = collection.size();
        //noinspection unchecked
        items = (E[]) collection.toArray();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        if (size == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (Objects.equals(object, items[i])) {
                return true;
            }
        }

        return false;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        public boolean hasNext() {
            return currentIndex < size - 1;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Элемент не найден");
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }

            ++currentIndex;

            return items[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E element) {
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = element;
        size++;

        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        if (size == 0) {
            return false;
        }

        int indexOfElement = indexOf(object);

        if (indexOfElement == -1) {
            return false;
        } else {
            remove(indexOfElement);

            return true;
        }
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        for (Object item : collection) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(@SuppressWarnings("DataFlowIssue") Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        if (collection.isEmpty()) {
            return false;
        }

        ensureCapacity(size + collection.size());

        for (E item : collection) {
             add(item);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }

        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        if (collection.isEmpty()) {
            return false;
        }

        ensureCapacity(size + collection.size());
        System.arraycopy(items, index, items, index + collection.size(), size - index);
        size += collection.size();

        int i = index;

        for (E item : collection) {
            items[i++] = item;
        }

        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        if (collection.isEmpty()) {
            return false;
        }

        boolean isRemoved = false;

        for (Object item : collection) {
            if (remove(item)) {
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        if (collection.isEmpty()) {
            return false;
        }

        boolean isModified = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!collection.contains(items[i])) {
                remove(i);

                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;

        modCount++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }

        E previousElement = items[index];

        items[index] = element;

        return previousElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }

        if (index == size) {
            add(element);
            return;
        }

        if (size >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = element;
        size++;

        modCount++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }

        E removedElement = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;
        size--;

        modCount++;

        return removedElement;
    }

    @Override
    public int indexOf(Object object) {
        if (size == 0) {
            return -1;
        }

        for (int i = 0; i < size; i++) {
            if (Objects.equals(object, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        if (size == 0) {
            return -1;
        }

        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(object, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        for (int i = 0; i < size; i++) {
            joiner.add(items[i].toString());
        }

        return joiner.toString();
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            int newCapacity = Math.max(items.length * 2, minCapacity);

            items = Arrays.copyOf(items, newCapacity);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }
}
