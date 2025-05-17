package ru.academits.eliseev.tree;

class Node<E> {
    private Node<E> left;
    private Node<E> right;
    private E data;

    public Node(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    @SuppressWarnings("unused")
    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }
}
