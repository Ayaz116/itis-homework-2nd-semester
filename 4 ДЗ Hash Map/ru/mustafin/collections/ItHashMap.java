package Iterator.ru.mustafin.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class ItHashMap<K, V> implements ItMap<K, V>{

    private int size = 0;
    private int capacity = 20;
    private List<List<Node<K, V>>> buckets = new LinkedList<>();

    public ItHashMap(){
        for (int i = 0; i < capacity; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int index = Math.abs(hash % capacity);
        List<Node<K, V>> bucket = buckets.get(index);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key.equals(key)) {
                return bucket.get(i).value;
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = Math.abs(hash % capacity);
        List<Node<K, V>> bucket = buckets.get(index);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key.equals(key)) {
                bucket.get(i).value = value;
                return;
            }
        }
        bucket.add(new Node<>(key, value));
        size++;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<Node<K, V>> {

        private int bucketIndex = 0;
        private int nodeIndex = 0;

        @Override
        public boolean hasNext() {
            while (bucketIndex < capacity) {
                if (nodeIndex < buckets.get(bucketIndex).size()) {
                    return true;
                } else {
                    bucketIndex++;
                    nodeIndex = 0;
                }
            }
            return false;
        }

        @Override
        public Node<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<K, V> node = buckets.get(bucketIndex).get(nodeIndex);
            nodeIndex++;
            return node;
        }
    }

}
