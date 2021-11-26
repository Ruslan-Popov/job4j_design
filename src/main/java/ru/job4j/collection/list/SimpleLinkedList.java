package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private int modCount;
    private int size = 0;
    Node<E> first;
    Node<E> last;

    @Override
    public void add(E value) {
        Node<E> tmp = last;
        Node<E> newNode = new Node(tmp, value, null);
        last = newNode;
        if (tmp == null) {
            first = newNode;
        } else {
            tmp.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> tmp = first;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                break;
            }
            tmp = tmp.next;
        }
        return tmp.element;
    }

    @Override
    public Iterator<E> iterator() throws ConcurrentModificationException, NoSuchElementException {
        return new Iterator<E>() {
            int i = 0;
            Node<E> node = first;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("The collection has been changed");
                }
                return i < size;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException("End of elements");
                }
                E rst = node.element;
                node = node.next;
                i++;
                return rst;
            }
        };
    }

    private static class Node<E> {
        private E element;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }
}
