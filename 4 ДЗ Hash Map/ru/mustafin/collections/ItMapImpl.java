package Iterator.ru.mustafin.collections;

import java.util.Iterator;

public class ItMapImpl<K,V> implements ItMap<K,V> {

    int size = 0;
    Object[] keys = new Object[1000];
    Object[] values = new Object[1000];

    public ItMapImpl(){}


    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return (V) values[i];
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        for (int i = 0; i < size; i++) {
            if (key.equals(keys[i])) {
                values[i] = value;
                return;
            }
        }
        keys[size] = key;
        values[size] = value;
        size++;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<Node<K, V>>{

        int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }


        @Override
        public Node<K, V> next() {
            Node<K, V> node= new Node<K, V>((K) keys[index],(V) values[index]);
            index++;
            return node;
        }
    }
}
