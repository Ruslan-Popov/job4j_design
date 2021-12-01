package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Класс описывает простейшую очередь по принципу FIFO, основанную на двух стеках LIFO
 * @param <T> тип добавляемых ссылочных переменных
 * @author Ruslan Popov (ruslan_popov@mail.ru)
 * @version 1.0
 */
public class SimpleQueue<T> {
    /**
     * основной стек
     */
    private final SimpleStack<T> in = new SimpleStack<>();
    /**
     * вспомогательный стек
     */
    private final SimpleStack<T> out = new SimpleStack<>();
    /**
     * размер очереди
     */
    private int size = 0;

    /**
     * Метод возвращает первое значение и удаляет его
     * @return возвращает удаленное первое значение
     */
    public T poll() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("The queue is empty");
        }
        size--;
        return in.pop();
    }

    /**
     * Метод помещает значение в конец очереди.
     * @param value элемент, который добавляется в конец очереди
     */
    public void push(T value) {
        int tmpSize = size;
        while (tmpSize > 0) {
            out.push(in.pop());
            tmpSize--;
        }
        in.push(value);
        tmpSize = size;
        while (tmpSize != 0) {
            in.push(out.pop());
            tmpSize--;
        }
        size++;
    }
}
