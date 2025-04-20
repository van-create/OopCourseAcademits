package ru.academits.eliseev.hashtable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
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
        buckets = new ArrayList[capacity];
    }

    @SuppressWarnings("unchecked")
    public HashTable(Collection<? extends E> collection) {
        validateCollection(collection);

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
        if (isEmpty()) {
            return false;
        }

        int index = Objects.equals(null, object) ? 0 : Math.abs(object.hashCode() % buckets.length);

        if (buckets[index] == null) {
            return false;
        }

        for (E item : buckets[index]) {
            if (Objects.equals(item, object)) {
                return true;
            }
        }

        return false;
    }

    private class HashTableIterator implements Iterator<E> {
        private int currentBucketIndex = 0;
        private int currentElementIndex = -1;
        private int elementsReturned = 0;

        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return elementsReturned < size;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Таблица была изменена");
            }
            if (!hasNext()) {
                throw new NoSuchElementException("Элемент не найден");
            }

            while (currentBucketIndex < buckets.length && buckets[currentBucketIndex] == null) {
                currentBucketIndex++;
                currentElementIndex = -1;
            }

            ArrayList<E> currentBucket = buckets[currentBucketIndex];

            currentElementIndex++;
            elementsReturned++;

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

        for (ArrayList<E> bucket : buckets) {
            if (bucket != null) {
                for (int i = 0; i < bucket.size(); i++) {
                    elements[i] = bucket.get(i);
                }
            }
        }

        return elements;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        T[] elements = (T[]) toArray();

        if (array.length < size) {
            return elements;
        }

        System.arraycopy(elements, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E element) {
        int index = Objects.equals(null, element) ? 0 : Math.abs(element.hashCode() % buckets.length);

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
        if (isEmpty()) {
            return false;
        }

        int index = Objects.equals(null, object) ? 0 : Math.abs(object.hashCode() % buckets.length);

        if (buckets[index] == null) {
            return false;
        }

        boolean isRemoved = buckets[index].remove(object);

        if (isRemoved) {
            if (buckets[index].isEmpty()) {
                buckets[index] = null;
            }

            size--;
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        validateCollection(collection);

        if (collection.isEmpty() || isEmpty()) {
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
        validateCollection(collection);

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
        validateCollection(collection);

        if (collection.isEmpty() || isEmpty()) {
            return false;
        }

        boolean isRemoved = false;

        for (Object item : collection) {
            isRemoved |= remove(item);
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        validateCollection(collection);

        if (isEmpty()) {
            return false;
        }

        boolean isModified = false;

        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                Iterator<E> iterator = buckets[i].iterator();

                while (iterator.hasNext()) {
                    E item = iterator.next();

                    if (!collection.contains(item)) {
                        iterator.remove();

                        size--;
                        modCount++;

                        isModified = true;
                    }
                }

                if (buckets[i].isEmpty()) {
                    buckets[i] = null;
                }
            }
        }

        return isModified;
    }

    @Override
    public void clear() {
        Arrays.fill(buckets, null);
        size = 0;
        modCount++;
    }

    private void validateCollection(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }
    }
}
