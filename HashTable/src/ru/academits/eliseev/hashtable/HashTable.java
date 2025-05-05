package ru.academits.eliseev.hashtable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] buckets;
    private int size;
    private int modCount;

    private static final int DEFAULT_CAPACITY = 30;

    @SuppressWarnings("unchecked")
    public HashTable() {
        buckets = new ArrayList[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Неверное значение capacity: " + capacity + ". Должно быть больше или равно 0");
        }

        buckets = new ArrayList[capacity];
    }

    @SuppressWarnings("unchecked")
    public HashTable(Collection<? extends E> collection) {
        checkCollectionIsNotNull(collection);

        buckets = new ArrayList[DEFAULT_CAPACITY];

        addAll(collection);
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
        int index = getIndex(object);

        return buckets[index] != null && buckets[index].contains(object);
    }

    private class HashTableIterator implements Iterator<E> {
        private int currentBucketIndex;
        private int currentElementIndex = -1;
        private int returnedElementsCount;

        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return returnedElementsCount < size;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Хэш-таблица была изменена");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Элемент не найден");
            }

            while (currentBucketIndex < buckets.length && (buckets[currentBucketIndex] == null || buckets[currentBucketIndex].isEmpty())) {
                currentBucketIndex++;
                currentElementIndex = -1;
            }

            ArrayList<E> currentBucket = buckets[currentBucketIndex];

            currentElementIndex++;
            returnedElementsCount++;

            E element = currentBucket.get(currentElementIndex);

            if (currentElementIndex + 1 >= currentBucket.size()) {
                currentBucketIndex++;
                currentElementIndex = -1;
            }

            return element;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        if (isEmpty()) {
            return new Object[0];
        }

        Object[] elements = new Object[size];

        int i = 0;

        for (E element : this) {
            elements[i] = element;
            i++;
        }

        return elements;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        T[] elements = (T[]) toArray();

        if (array.length < size) {
            return (T[]) Arrays.copyOf(elements, size, array.getClass());
        }

        System.arraycopy(elements, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E element) {
        int index = getIndex(element);

        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }

        buckets[index].add(element);
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = getIndex(object);

        if (buckets[index] == null) {
            return false;
        }

        boolean isRemoved = buckets[index].remove(object);

        if (isRemoved) {
            size--;
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        checkCollectionIsNotNull(collection);

        if (collection.isEmpty()) {
            return true;
        }

        if (isEmpty()) {
            return false;
        }

        for (Object item : collection) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        checkCollectionIsNotNull(collection);

        if (collection.isEmpty()) {
            return false;
        }

        for (E item : collection) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        checkCollectionIsNotNull(collection);

        if (collection.isEmpty() || isEmpty()) {
            return false;
        }

        boolean isRemoved = false;

        for (Object item : collection) {
            while (remove(item)) {
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        checkCollectionIsNotNull(collection);

        if (isEmpty()) {
            return false;
        }

        boolean isModified = false;

        for (ArrayList<E> bucket : buckets) {
            if (bucket != null) {
                int oldSize = bucket.size();

                isModified |= bucket.retainAll(collection);

                size -= oldSize - bucket.size();
            }
        }

        if (isModified) {
            modCount++;
        }

        return isModified;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        for (ArrayList<E> bucket : buckets) {
            if (bucket != null) {
                bucket.clear();
            }
        }

        modCount++;
        size = 0;
    }

    private static void checkCollectionIsNotNull(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }
    }

    private int getIndex(Object object) {
        return object == null ? 0 : Math.abs(object.hashCode() % buckets.length);
    }
}
