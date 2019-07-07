import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
    private class Node{
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        private int size; /* The number of key-value pairs in the tree */

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
            size = 1;
        }
    }
    private Node root; /* Root node of the tree. */
    /* Creates an empty BSTMap. */
//    public BSTMap() {
//        this.clear();
//    }
    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
    }
    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (root == null) return false;
        return get(key) != null;
    }
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(root, key);
    }
    private V getHelper(Node root, K key) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.key) > 0) {
            return getHelper(root.right, key);
        } else if (key.compareTo(root.key) < 0) {
            return getHelper(root.left, key);
        } else {
            return root.value;
        }
    }
    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return sizeHelper(root);
    }
    private int sizeHelper(Node root) {
        if (root == null) return 0;
        return root.size;
    }
    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }
    private Node put(Node root, K key, V value) {
        if (root == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(root.key);
        if(cmp > 0) {
            return put(root.right, key, value);
        } else if (cmp < 0) {
            return put(root.left, key, value);
        } else {
            root.value = value;
        }
        root.size = sizeHelper(root.left) + sizeHelper(root.right) + 1;
        return root;
    }
    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        printInOrder(root);
    }
    private void printInOrder(Node root) {
        if (root.left == null && root.right == null) {
            printNode(root);
        } else if (root.right == null) {
            printInOrder(root.left);
            printNode(root);
        } else if (root.left == null) {
            printNode(root);
            printInOrder(root.right);
        } else {
            printInOrder(root.left);
            printNode(root);
            printInOrder(root.right);
        }

    }
    private void printNode(Node x) {
        System.out.print(x.key);
        System.out.println(" " + x.value);
    }
}
