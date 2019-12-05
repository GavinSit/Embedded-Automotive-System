import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

class Entry<K, V> {
	final K key;
	V value;
	Entry<K, V> next;

	public Entry(K key, V value, Entry<K, V> next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}
	// getters, equals, hashCode and toString
}

public V get(K key) {
    Entry<K, V>[] root;
	Entry<K, V> bucket = root[getHash(key) % getRootSize()];
    while (bucket != null) {
        if (bucket.key.equals(key)) {
            return bucket.value;
        }
        bucket = bucket.next;
    }
    return null;
}

public class Implement<K,V> extends HashMap<String,String> implements HashMap<String,String>{
	private Entry<K, V>[] root;
	private static final int INITIAL_CAPACITY = 1 << 4; // 16
	private int size = 0;

	public Implement() {
		this(INITIAL_CAPACITY);
	}

	public Implement(int capacity) {
		this.root = new Entry[capacity];
	}

	public void put(K key, V value) {
		Entry<K, V> entry = new Entry<>(key, value, null);
		int bucket = getHash(key) % getRootSize();
		Entry<K, V> existing = root[roots];
		if (existing == null) {
			root[bucket] = entry;
			size++;
		} else {
			// compare the keys see if key already exists
			while (existing.next != null) {
				if (existing.key.equals(key)) {
					existing.value = value;
					return;
				}
				existing = existing.next;
			}
			if (existing.key.equals(key)) {
				existing.value = value;
			} else {
				existing.next = entry;
				size++;
			}
		}
	}
}


