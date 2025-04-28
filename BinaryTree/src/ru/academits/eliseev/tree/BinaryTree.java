package ru.academits.eliseev.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@SuppressWarnings("UnusedReturnValue")
public class BinaryTree<E extends Comparable<E>> {
    private Node<E> root;

    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(Node<E> current) {
        if (current == null) {
            return 0;
        }

        return 1 + sizeRecursive(current.getLeft()) + sizeRecursive(current.getRight());
    }

    public Node<E> add(E element) {
        checkNotNull(element);

        return root == null ? (root = new Node<>(element)) : add(element, root);
    }

    private Node<E> add(E element, Node<E> node) {
        if (element.compareTo(node.getData()) < 0) {
            if (node.getLeft() != null) {
                return add(element, node.getLeft());
            }

            node.setLeft(new Node<>(element));
        }

        if (element.compareTo(node.getData()) >= 0) {
            if (node.getRight() != null) {
                return add(element, node.getRight());
            }

            node.setRight(new Node<>(element));
        }

        return node;
    }

    public Node<E> remove(E element) {
        checkNotNull(element);

        return remove(element, root);
    }

    private Node<E> remove(E element, Node<E> node) {
        if (node == null) {
            return null;
        }

        if (element.compareTo(node.getData()) < 0) {
            node.setLeft(remove(element, node.getLeft()));
        } else if (element.compareTo(node.getData()) > 0) {
            node.setRight(remove(element, node.getRight()));
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }

            if (node.getLeft() == null) {
                return node.getRight();
            }

            if (node.getRight() == null) {
                return node.getLeft();
            }

            Node<E> smallestNode = findSmallestNode(node.getRight());
            node.setData(smallestNode.getData());
            node.setRight(remove(smallestNode.getData(), node.getRight()));
        }

        return node;
    }

    private Node<E> findSmallestNode(Node<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }

        return node;
    }

    public Node<E> search(E element) {
        checkNotNull(element);

        return root == null ? null : search(element, root);
    }

    private Node<E> search(E element, Node<E> node) {
        if (element.compareTo(node.getData()) < 0) {
            if (node.getLeft() != null) {
                return search(element, node.getLeft());
            }

            return null;
        }

        if (element.compareTo(node.getData()) > 0) {
            if (node.getRight() != null) {
                return search(element, node.getRight());
            }

            return null;
        }

        return node;
    }

    public void preorderTraversalRecursive() {
        preorderTraversalRecursive(root);
    }

    private void preorderTraversalRecursive(Node<E> current) {
        if (current != null) {
            System.out.print(current.getData() + " ");

            preorderTraversalRecursive(current.getLeft());
            preorderTraversalRecursive(current.getRight());
        }
    }

    public void preorderTraversalIterative() {
        if (root != null) {
            Stack<Node<E>> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                Node<E> current = stack.pop();
                System.out.print(current.getData() + " ");

                if (current.getRight() != null) {
                    stack.push(current.getRight());
                }

                if (current.getLeft() != null) {
                    stack.push(current.getLeft());
                }
            }

            System.out.println();
        }

    }

    public void breadthFirstTraversal() {
        if (root != null) {
            Queue<Node<E>> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                Node<E> current = queue.poll();
                System.out.print(current.getData() + " ");

                if (current.getLeft() != null) {
                    queue.add(current.getLeft());
                }

                if (current.getRight() != null) {
                    queue.add(current.getRight());
                }
            }

            System.out.println();
        }
    }
    private void checkNotNull(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Элемент не может быть null");
        }
    }
}
