package lesson5;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

public class OpenAddressingSet<T> extends AbstractSet<T> {

    private enum  Condition { DELETED }

    private final int bits;

    private final int capacity;

    private final Object[] storage;

    private int size = 0;

    private int startingIndex(Object element) {
        return element.hashCode() & (0x7FFFFFFF >> (31 - bits));
    }

    public OpenAddressingSet(int bits) {
        if (bits < 2 || bits > 31) {
            throw new IllegalArgumentException();
        }
        this.bits = bits;
        capacity = 1 << bits;
        storage = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Проверка, входит ли данный элемент в таблицу
     */
    @Override
    public boolean contains(Object o) {
        int index = startingIndex(o);
        Object current = storage[index];
        while (current != null) {
            if (current.equals(o)) {
                return true;
            }
            index = (index + 1) % capacity;
            current = storage[index];
        }
        return false;
    }

    /**
     * Добавление элемента в таблицу.
     *
     * Не делает ничего и возвращает false, если такой же элемент уже есть в таблице.
     * В противном случае вставляет элемент в таблицу и возвращает true.
     *
     * Бросает исключение (IllegalStateException) в случае переполнения таблицы.
     * Обычно Set не предполагает ограничения на размер и подобных контрактов,
     * но в данном случае это было введено для упрощения кода.
     */
    @Override
    public boolean add(T t) {
        int startingIndex = startingIndex(t);
        int index = startingIndex;
        Object current = storage[index];
        while (current != null && current != Condition.DELETED) {
            if (current.equals(t)) {
                return false;
            }
            index = (index + 1) % capacity;
            if (index == startingIndex) {
                throw new IllegalStateException("Table is full");
            }
            current = storage[index];
        }
        storage[index] = t;
        size++;
        return true;
    }

    /**
     * Удаление элемента из таблицы
     *
     * Если элемент есть в таблица, функция удаляет его из дерева и возвращает true.
     * В ином случае функция оставляет множество нетронутым и возвращает false.
     * Высота дерева не должна увеличиться в результате удаления.
     *
     * Спецификация: {@link Set#remove(Object)} (Ctrl+Click по remove)
     *
     * Средняя
     */
    @Override
    //Трудоёмкость O(N)
    //Ресурсоёмкость O(1)
    public boolean remove(Object o) {
        int index = startingIndex(o);
        while (storage[index] != null && storage[index] != Condition.DELETED) {
            if (storage[index].equals(o)) {
                storage[index] = Condition.DELETED;
                size--;
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * Создание итератора для обхода таблицы
     *
     * Не забываем, что итератор должен поддерживать функции next(), hasNext(),
     * и опционально функцию remove()
     *
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     *
     * Средняя (сложная, если поддержан и remove тоже)
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new OpenAddressingSetIterator();
    }

    public class OpenAddressingSetIterator implements Iterator<T>{

        int currentIndex = 0;
        Object current = null;
        int count = 0;

        @Override
        //Трудоёмкость O(1)
        //Ресурсоёмкость O(1)
        public boolean hasNext() {
            return count < size;
        }

        @Override
        //Трудоёмкость O(N)
        //Ресурсоёмкость O(1)
        public T next() {
            T result = null;
            while (result == null && hasNext()) {
                current = storage[currentIndex];
                    if (current != null && current != Condition.DELETED) {
                        result = (T) current;
                    }
                    currentIndex++;
                }
            if (result == null) throw new IllegalStateException();
            count++;
            return result;
        }

        @Override
        //Трудоёмкость O(1)
        //Ресурсоёмкость O(1)
        public void remove() {
            if (current == null || current == Condition.DELETED) throw new IllegalStateException();
            storage[currentIndex - 1] = Condition.DELETED;
            current = null;
            size--;
            count--;
        }
    }
}
