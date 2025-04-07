package ru.academits.eliseev.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
        head = null;
        count = 0;
    }

    public SinglyLinkedList(T component) {
        if (component == null) {
            throw new NullPointerException("Компонент не может быть null");
        }

        head = new ListItem<>(component);
        count = 1;
    }

    public SinglyLinkedList(T[] components) {
        if (components == null) {
            throw new NullPointerException("Массив не может быть null");
        }

        for (T component : components) {
            if (component == null) {
                throw new NullPointerException("Массив не может содержать null элементы");
            }
        }

        count = components.length;

        head = new ListItem<>(count == 0 ? null : components[0]);
        ListItem<T> currentItem = head;

        for (int i = 1; i < count; i++) {
            currentItem.setNext(new ListItem<>(components[i]));
            currentItem = currentItem.getNext();
        }
    }

    public int getLength() {
        return count;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.getData();
    }

    public T get(int index) {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }

        ListItem<T> currentComponent = head;

        for (int currentIndex = 0; currentIndex != index; currentIndex++) {
            currentComponent = currentComponent.getNext();
        }

        return currentComponent.getData();
    }

    public T set(int index, T component) {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }

        ListItem<T> currentComponent = head;

        for (int currentIndex = 0; currentIndex != index; currentIndex++) {
            currentComponent = currentComponent.getNext();
        }

        T oldData = currentComponent.getData();

        currentComponent.setData(component);

        return oldData;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        T removedData = head.getData();

        head = head.getNext();
        count--;

        return removedData;
    }

    public T remove(int index) {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }

        ListItem<T> currentItem = head;

        for (int currentIndex = 0; currentIndex != index - 1; currentIndex++) {
            currentItem = currentItem.getNext();
        }

        T removedData = currentItem.getNext().getData();

        currentItem.setNext(currentItem.getNext().getNext());
        count--;

        return removedData;
    }

    public boolean remove(T component) {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        for (ListItem<T> currentItem = head, previousItem = null;
             currentItem != null;
             previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (currentItem.getData() == component) {
                if (previousItem != null) {
                    previousItem.setNext(currentItem.getNext());
                } else {
                    removeFirst();
                }

                count--;

                return true;
            }
        }

        return false;
    }

    public void addFirst(T component) {
        head = new ListItem<>(component, head);
        count++;
    }

    public void add(T component) {
        ListItem<T> newItem = new ListItem<>(component);

        if (head == null) {
            head = newItem;
        } else {
            ListItem<T> currentItem = head;

            while (currentItem.getNext() != null) {
                currentItem = currentItem.getNext();
            }

            currentItem.setNext(newItem);
        }

        count++;
    }

    public void add(int index, T component) {
        if (index < 0 || index > count + 1) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }

        ListItem<T> currentItem = head;

        for (int currentIndex = 0; currentIndex != index - 1; currentIndex++) {
            currentItem = currentItem.getNext();
        }

        currentItem.setNext(new ListItem<>(component, currentItem.getNext()));
        count++;
    }

    public void reverse() {
        ListItem<T> previousItem = null;
        ListItem<T> currentItem = head;

        while (currentItem != null) {
            ListItem<T> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);

            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copiedList = new SinglyLinkedList<>();

        for (ListItem<T> currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
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

        if (this.count != linkedList.count) {
            return false;
        }

        ListItem<T> currentItem = this.head;
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

        ListItem<T> currentItem = head;

        while (currentItem != null) {
            hash = prime * hash + currentItem.getData().hashCode();
            currentItem = currentItem.getNext();
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        ListItem<T> currentItem = head;

        while (currentItem != null) {
            sb.append(currentItem.getData());

            if (currentItem.getNext() != null) {
                sb.append(", ");
            }

            currentItem = currentItem.getNext();
        }

        sb.append("]");

        return sb.toString();
    }
}
