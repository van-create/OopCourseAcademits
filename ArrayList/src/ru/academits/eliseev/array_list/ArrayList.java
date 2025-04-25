package ru.academits.eliseev.array_list;

import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.ListIterator;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[10];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Некорректное значение вместимости списка: " + initialCapacity + ". Корректное значение: 0 и больше");
        }

        //noinspection unchecked
        items = (E[]) new Object[initialCapacity];
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
        return indexOf(object) != -1;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex < size - 1;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Элемент не найден");
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
        int elementIndex = indexOf(object);

        if (elementIndex == -1) {
            return false;
        }

        remove(elementIndex);

        return true;
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
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона. Допустимый диапазон от 0 до " + size + ". Полученный индекс: " + index);
        }

        if (collection.isEmpty()) {
            return false;
        }

        ensureCapacity(size + collection.size());
        System.arraycopy(items, index, items, index + collection.size(), size - index);
        size += collection.size();

        int i = index;

        for (E item : collection) {
            items[i] = item;
            i++;
        }

        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

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
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        if (isEmpty()) {
            return false;
        }

        if (collection.isEmpty()) {
            clear();

            return true;
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
        if (isEmpty()) {
            return;
        }

        Arrays.fill(items, null);
        size = 0;

        modCount++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона. Допустимый диапазон от 0 до " + (size - 1) + ". Полученный индекс: " + index);
        }

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона. Допустимый диапазон от 0 до " + (size - 1) + ". Полученный индекс: " + index);
        }

        E oldElement = items[index];

        items[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона. Допустимый диапазон от 0 до " + size + ". Полученный индекс: " + index);
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
            throw new IndexOutOfBoundsException("Индекс вне диапазона. Допустимый диапазон от 0 до " + (size - 1) + ". Полученный индекс: " + index);
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
        for (int i = 0; i < size; i++) {
            if (Objects.equals(object, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
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
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public String toString() {
        if (items == null) {
            return "[]";
        }

        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        for (int i = 0; i < size; i++) {
            joiner.add(items[i].toString());
        }

        return joiner.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        ArrayList<?> arrayList = (ArrayList<?>) object;

        if (size() != arrayList.size()) {
            return false;
        }

        Iterator<E> thisIterator = iterator();
        Iterator<?> otherIterator = arrayList.iterator();

        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            E thisItem = thisIterator.next();
            Object otherItem = otherIterator.next();

            if (!(Objects.equals(thisItem, otherItem))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (E item : this) {
            hash = prime * hash + (item == null ? 0 : item.hashCode());
        }

        return hash;
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            items = Arrays.copyOf(items, minCapacity);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    private void increaseCapacity() {
        int newCapacity = items.length == 0 ? 10 : items.length * 2;

        items = Arrays.copyOf(items, newCapacity);
    }
}
