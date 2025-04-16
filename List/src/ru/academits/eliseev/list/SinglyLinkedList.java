package ru.academits.eliseev.list;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class SinglyLinkedList<E> {
    // TODO: null элементы поддерживаются
    // TODO: вспомогательный метод для прохода до индекса
    private ListItem<E> head;
    private int count;

    public SinglyLinkedList() {}

    public SinglyLinkedList(E[] elements) {
        if (elements == null) {
            throw new NullPointerException("Массив не может быть null");
        }

        count = elements.length;

        if (count != 0) {
            head = new ListItem<>(elements[0]);
        }

        ListItem<E> currentItem = head;

        for (int i = 1; i < count; i++) {
            currentItem.setNext(new ListItem<>(elements[i]));
            currentItem = currentItem.getNext();
        }
    }

    public int getLength() {
        return count;
    }

    public E getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.getData();
    }

    public E get(int index) {
        checkIndex(index);

        ListItem<E> currentComponent = head;

        for (int currentIndex = 0; currentIndex != index; currentIndex++) {
            currentComponent = currentComponent.getNext();
        }

        return currentComponent.getData();
    }

    public E set(int index, E element) {
        checkIndex(index);

        ListItem<E> currentComponent = head;

        for (int currentIndex = 0; currentIndex != index; currentIndex++) {
            currentComponent = currentComponent.getNext();
        }

        E oldData = currentComponent.getData();

        currentComponent.setData(element);

        return oldData;
    }

    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        E removedData = head.getData();

        head = head.getNext();
        count--;

        return removedData;
    }

    public E remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<E> currentItem = head;

        for (int currentIndex = 0; currentIndex != index - 1; currentIndex++) {
            currentItem = currentItem.getNext();
        }

        E removedData = currentItem.getNext().getData();

        currentItem.setNext(currentItem.getNext().getNext());
        count--;

        return removedData;
    }

    public boolean remove(E element) {
        if (head == null) {
            return false;
        }

        for (ListItem<E> currentItem = head, previousItem = null;
             currentItem != null;
             previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (Objects.equals(element, currentItem.getData())) {
                if (previousItem != null) {
                    previousItem.setNext(currentItem.getNext());
                } else {
                    removeFirst();
                    return true;
                }

                count--;

                return true;
            }
        }

        return false;
    }

    public void addFirst(E element) {
        head = new ListItem<>(element, head);
        count++;
    }

    public void add(E element) {
        ListItem<E> newItem = new ListItem<>(element);

        if (head == null) {
            head = newItem;
        } else {
            ListItem<E> currentItem = head;

            while (currentItem.getNext() != null) {
                currentItem = currentItem.getNext();
            }

            currentItem.setNext(newItem);
        }

        count++;
    }

    public void add(int index, E element) {
        if (index == count) {
            add(element);
            return;
        }

        checkIndex(index);

        ListItem<E> currentItem = head;

        for (int currentIndex = 0; currentIndex != index - 1; currentIndex++) {
            currentItem = currentItem.getNext();
        }

        currentItem.setNext(new ListItem<>(element, currentItem.getNext()));
        count++;
    }

    public void reverse() {
        ListItem<E> previousItem = null;
        ListItem<E> currentItem = head;

        while (currentItem != null) {
            ListItem<E> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);

            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<E> copy() {
        // TODO: реализовать за O(n)
        SinglyLinkedList<E> copiedList = new SinglyLinkedList<>();

        for (ListItem<E> currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
            copiedList.add(currentItem.getData());
        }

        return copiedList;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        SinglyLinkedList<?> linkedList = (SinglyLinkedList<?>) object;

        if (count != linkedList.count) {
            return false;
        }

        ListItem<E> currentItem = head;
        ListItem<?> otherItem = linkedList.head;

        while (currentItem != null && otherItem != null) {
            if (!Objects.equals(currentItem.getData(), otherItem.getData())) {
                return false;
            }

            currentItem = currentItem.getNext();
            otherItem = otherItem.getNext();
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        ListItem<E> currentItem = head;

        while (currentItem != null) {
            hash = prime * hash + currentItem.getData().hashCode();
            currentItem = currentItem.getNext();
        }

        return hash;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");

        ListItem<E> currentItem = head;

        while (currentItem != null) {
            sj.add(String.valueOf(currentItem.getData()));
            currentItem = currentItem.getNext();
        }

        return sj.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона. Допускается от 0 до " + (count - 1) + ".");
        }
    }
}
