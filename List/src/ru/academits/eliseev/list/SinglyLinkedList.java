package ru.academits.eliseev.list;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class SinglyLinkedList<E> {
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

        return getItem(index).getData();
    }

    public E set(int index, E element) {
        checkIndex(index);

        ListItem<E> currentItem = getItem(index);

        E oldData = currentItem.getData();

        currentItem.setData(element);

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

        ListItem<E> previousItem = getItem(index - 1);

        E removedData = previousItem.getNext().getData();

        previousItem.setNext(previousItem.getNext().getNext());
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
                if (previousItem == null) {
                    removeFirst();
                    return true;
                }

                previousItem.setNext(currentItem.getNext());

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
        add(count, element);
    }

    public void add(int index, E element) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона. Допускается от 0 до " + (count) + ".");
        }

        if (index == 0) {
            addFirst(element);
            return;
        }

        ListItem<E> previousItem = getItem(index - 1);

        previousItem.setNext(new ListItem<>(element, previousItem.getNext()));
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
        SinglyLinkedList<E> copiedList = new SinglyLinkedList<>();

        if (head == null) {
            return copiedList;
        }

        ListItem<E> copiedHead = new ListItem<>(head.getData());
        copiedList.head = copiedHead;

        ListItem<E> currentItem = head.getNext();
        ListItem<E> copiedCurrentItem = copiedHead;

        while (currentItem != null) {
            ListItem<E> copiedItem = new ListItem<>(currentItem.getData());

            copiedCurrentItem.setNext(copiedItem);
            copiedCurrentItem = copiedItem;

            currentItem = currentItem.getNext();
        }

        copiedList.count = count;

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
            hash = prime * hash + (currentItem.getData() == null ? 0 : currentItem.getData().hashCode());
            currentItem = currentItem.getNext();
        }

        return hash;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        ListItem<E> currentItem = head;

        while (currentItem != null) {
            joiner.add(String.valueOf(currentItem.getData()));
            currentItem = currentItem.getNext();
        }

        return joiner.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона. Допускается от 0 до " + (count - 1) + ". Текущее значение: " + index + ".");
        }
    }

    private ListItem<E> getItem(int index) {
        ListItem<E> currentItem = head;

        for (int i = 0; i < index; i++) {
            currentItem = currentItem.getNext();
        }

        return currentItem;
    }
}
