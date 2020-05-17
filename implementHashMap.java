import java.util.*;

public class ExternalChainingHashMap<K, V> {


    public static final int INITIAL_CAPACITY = 13;

    public static final double MAX_LOAD_FACTOR = 0.67;

    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    public ExternalChainingHashMap() {
        table = (ExternalChainingMapEntry<K, V>[])new ExternalChainingMapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    public ExternalChainingHashMap(int initialCapacity) {
        table = (ExternalChainingMapEntry<K, V>[])new ExternalChainingMapEntry[initialCapacity];
        size = 0;
    }


    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("your input contains null value! please re-enter!");
        }

        if ((double) (size + 1) / (double) table.length > MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }


        ExternalChainingMapEntry<K,V> newElement = new ExternalChainingMapEntry<>(key, value);
        int hashcode = key.hashCode();
        int index = Math.abs(hashcode % table.length);
        ExternalChainingMapEntry<K,V> pointer = table[index];
        if (pointer == null) {
            table[index] = newElement;
            size++;
            return null;
        } else {
            while (pointer != null) {
                if (pointer.getKey().equals(newElement.getKey())) {
                    V oldValue = pointer.getValue();
                    pointer.setValue(newElement.getValue());
                    //size do not need to change
                    return oldValue;
                }
                pointer = pointer.getNext();
            }

            newElement.setNext(table[index]);
            table[index] = newElement;
            size++;
            return null;
        }
    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("the input key is null! please re-enter!");
        }
        int hashcode = key.hashCode();
        int index = hashcode % table.length;
        ExternalChainingMapEntry<K, V> pointer = table[index];
        if (pointer == null) {
            throw new NoSuchElementException("There is not such key contains in this map!");
        } else {
            if (pointer.getKey().equals(key)) {
                table[index] = table[index].getNext();
                size--;
                return pointer.getValue();
            } else {
                ExternalChainingMapEntry<K, V> prev = pointer;
                pointer = pointer.getNext();
                while (pointer != null) {
                    if (pointer.getKey().equals(key)) {
                        size--;
                        prev.setNext(pointer.getNext());
                        return pointer.getValue();
                    }
                    pointer = pointer.getNext();
                }
                throw new NoSuchElementException("There is not such key contains in this map!");
            }
        }
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("the input key is null! please re-enter!");
        }
        int hashcode = key.hashCode();
        int index = hashcode % table.length;
        ExternalChainingMapEntry<K, V> pointer = table[index];
        while (pointer != null) {
            if (pointer.getKey().equals(key)) {
                return pointer.getValue();
            }
            pointer = pointer.getNext();
        }
        throw new NoSuchElementException("There is not such key contains in this map!");
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("the input key is null! please re-enter!");
        }

        for (int i = 0; i < table.length; i++) {
            ExternalChainingMapEntry<K, V> pointer = table[i];
            while (pointer != null) {
                if (pointer.getKey().equals(key)) {
                    return true;
                }
                pointer = pointer.getNext();
            }
        }
        return false;
    }

    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            ExternalChainingMapEntry<K, V> pointer = table[i];
            while (pointer != null) {
                set.add(pointer.getKey());
                pointer = pointer.getNext();
            }
        }
        return set;
    }

    public List<V> values() {
        List<V> list = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            ExternalChainingMapEntry<K, V> pointer = table[i];
            while (pointer != null) {
                list.add(pointer.getValue());
                pointer = pointer.getNext();
            }
        }
        return list;
    }


    public void resizeBackingTable(int length) {
        if (length < size) {
            throw new IllegalArgumentException("capacity is not sufficient! please resize your table!");
        }

        ExternalChainingMapEntry<K, V>[] tempTable = (ExternalChainingMapEntry<K, V>[])new ExternalChainingMapEntry[length];
        for (int i = 0; i < table.length; i++) {
            ExternalChainingMapEntry<K, V> pointer = table[i];
            while (pointer != null) {
                int hashcode = pointer.getKey().hashCode();
                int index = Math.abs(hashcode % tempTable.length); //rehash and relocate in tempTable
                ExternalChainingMapEntry<K,V> temp = tempTable[index];//get the right, add it to the tail
                if (temp == null) {
                    tempTable[index] = pointer;
                } else {
                    ExternalChainingMapEntry<K, V> tempPointer = temp;
                    while (tempPointer.getNext() != null) {
                        tempPointer = tempPointer.getNext();
                    }
                    tempPointer.setNext(pointer);
                }
                pointer = pointer.getNext();
            }
        }
        table = tempTable;
    }

    public void clear() {
        table = (ExternalChainingMapEntry<K, V>[])new ExternalChainingMapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
