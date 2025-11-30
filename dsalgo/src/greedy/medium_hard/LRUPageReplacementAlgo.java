package greedy.medium_hard;

import java.util.HashMap;

/**
 * Implement the LRUCache class:
 * <p>
 * LRUCache(int capacity): Initialize the LRU cache with positive size capacity.
 * int get(int key): Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value): Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * <p>
 * The functions get and put must each run in O(1) average time complexity.
 */
// use of Hashmap and DLL
public class LRUPageReplacementAlgo {
    public static void main(String[] args) {
        // Create cache with capacity 2
        LRUCache cache = new LRUCache(2);

        // Put values in cache
        cache.put(1, 1);
        cache.put(2, 2);

        // Get value for key 1
        System.out.println(cache.get(1));

        // Insert another key (evicts key 2)
        cache.put(3, 3);

        // Key 2 should be evicted
        System.out.println(cache.get(2));

        // Insert another key (evicts key 1)
        cache.put(4, 4);

        // Key 1 should be evicted
        System.out.println(cache.get(1));

        // Key 3 should be present
        System.out.println(cache.get(3));

        // Key 4 should be present
        System.out.println(cache.get(4));
    }
}

// class representing the LRU cache
class LRUCache {
    // Doubly Linked List node class
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Head and tail dummy node
    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);

    // Capacity of cache
    int cap;

    // Hashmap to store key-node(address pf node in DLL) mapping
    HashMap<Integer, Node> map = new HashMap<>();

    // Constructor to initialize LRU Cache
    public LRUCache(int capacity) {
        cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    // Function to add a node right after head
    void addNode(Node newNode) {
        Node tmp = head.next;
        newNode.next = tmp;
        newNode.prev = head;
        head.next = newNode;
        tmp.prev = newNode;
    }

    // Function to remove a given node from list
    void deleteNode(Node delNode) {
        Node delPrev = delNode.prev;
        Node delNext = delNode.next;
        delPrev.next = delNext;
        delNext.prev = delPrev;
    }

    // Function to get value from cache
    public int get(int key) {
        // if key exist
        if (map.containsKey(key)) {
            Node resNode = map.get(key);
            int res = resNode.value;
            // remove old mapping from hashmap
            map.remove(key);
            // move the accessed node to front
            deleteNode(resNode);
            addNode(resNode);
            // update hashmap
            map.put(key, head.next);
            return res;
        }
        // if not found
        return -1;
    }

    // Function to put key-value into cache
    public void put(int key, int value) {
        // if key already exists
        if (map.containsKey(key)) {
            Node existingNode = map.get(key);
            map.remove(key);
            deleteNode(existingNode);
        }
        // if capacity reached, remove LRU node
        if (map.size() == cap) {
            map.remove(tail.prev.key);
            deleteNode(tail.prev);
        }
        // Insert new node at front
        addNode(new Node(key, value));
        map.put(key, head.next);
    }
}
