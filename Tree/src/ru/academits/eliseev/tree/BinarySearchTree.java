package ru.academits.eliseev.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private static class Node<E> {
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

    private Node<E> root;
    private int size;
    private final Comparator<? super E> comparator;

    public BinarySearchTree() {
        this.comparator = null;
    }

    public BinarySearchTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }

        if (e1 == null && e2 == null) {
            return 0;
        }

        if (e1 == null) {
            return -1;
        }

        if (e2 == null) {
            return 1;
        }

        try {
            return ((Comparable<E>) e1).compareTo(e2);
        } catch (ClassCastException e) {
            throw new ClassCastException("Элементы должны быть сравнимы");
        }
    }

    public int size() {
        return size;
    }

    public void add(E element) {
        if (root == null) {
            root = new Node<>(element);
            size++;
            return;
        }

        Node<E> currentNode = root;
        Node<E> parentNode = null;

        while (currentNode != null) {
            parentNode = currentNode;

            int comparisonResult = compare(element, currentNode.getData());

            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
            } else if (comparisonResult > 0) {
                currentNode = currentNode.getRight();
            } else {
                return;
            }
        }

        Node<E> newNode = new Node<>(element);

        if (compare(element, parentNode.getData()) < 0) {
            parentNode.setLeft(newNode);
        } else {
            parentNode.setRight(newNode);
        }

        size++;
    }

    public void remove(E element) {
        Node<E> currentNode = root;
        Node<E> parentNode = null;

        boolean isLeftChild = false;

        while (currentNode != null) {
            int comparisonResult = compare(element, currentNode.getData());

            if (comparisonResult == 0) {
                break;
            }

            parentNode = currentNode;
            isLeftChild = comparisonResult < 0;
            currentNode = comparisonResult < 0 ? currentNode.getLeft() : currentNode.getRight();
        }

        if (currentNode == null) {
            return;
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            updateParentLink(parentNode, null, isLeftChild);
        } else if (currentNode.getRight() == null) {
            updateParentLink(parentNode, currentNode.getLeft(), isLeftChild);
        } else if (currentNode.getLeft() == null) {
            updateParentLink(parentNode, currentNode.getRight(), isLeftChild);
        } else {
            Node<E> successor = getSuccessor(currentNode);

            updateParentLink(parentNode, successor, isLeftChild);
        }

        size--;
    }

    private void updateParentLink(Node<E> parent, Node<E> child, boolean isLeftChild) {
        if (parent == null) {
            root = child;
        } else if (isLeftChild) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }
    }

    private static <T> Node<T> getSuccessor(Node<T> currentNode) {
        Node<T> successor = currentNode.getRight();
        Node<T> successorParent = currentNode;

        while (successor.getLeft() != null) {
            successorParent = successor;
            successor = successor.getLeft();
        }

        Node<T> successorRight = successor.getRight();

        if (successorParent != currentNode) {
            successorParent.setLeft(successorRight);

            successor.setRight(currentNode.getRight());
            successor.setLeft(currentNode.getLeft());
        } else {
            successor.setLeft(currentNode.getLeft());
        }

        return successor;
    }

    public boolean search(E element) {
        Node<E> currentNode = root;

        while (currentNode != null) {
            int comparisonResult = compare(element, currentNode.getData());

            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
            } else if (comparisonResult > 0) {
                currentNode = currentNode.getRight();
            } else {
                return true;
            }
        }

        return false;
    }

    public void traverseDepthFirstRecursive(Consumer<E> consumer) {
        if (consumer == null) {
            throw new NullPointerException("Consumer не может быть null");
        }

        traverseDepthFirstRecursive(root, consumer);
    }

    private void traverseDepthFirstRecursive(Node<E> currentNode, Consumer<E> consumer) {
        if (currentNode == null) {
            return;
        }

        consumer.accept(currentNode.getData());

        traverseDepthFirstRecursive(currentNode.getLeft(), consumer);
        traverseDepthFirstRecursive(currentNode.getRight(), consumer);
    }

    public void traverseDepthFirstIterative(Consumer<E> consumer) {
        if (consumer == null) {
            throw new NullPointerException("Consumer не может быть null");
        }

        if (root == null) {
            return;
        }

        Deque<Node<E>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<E> currentNode = stack.pop();
            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }

    public void traverseBreadthFirst(Consumer<E> consumer) {
        if (consumer == null) {
            throw new NullPointerException("Consumer не может быть null");
        }

        if (root == null) {
            return;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<E> currentNode = queue.poll();
            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");

        traverseDepthFirstRecursive(e -> stringJoiner.add(e == null ? "null" : e.toString()));

        return stringJoiner.toString();
    }
}
