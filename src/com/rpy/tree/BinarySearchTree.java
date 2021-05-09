package com.rpy.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: myDemo
 * @description: 二叉搜索树
 * @author: 任鹏宇
 * @create: 2021-05-09 16:57
 **/
public class BinarySearchTree<K extends Comparable<K>,V extends Comparable<V>> {

    /**
     * 根结点
     */
    private Node<K,V> root;

    /**
     * 记录元素个数
     */
    private int n;


    /**
     * 添加元素
     * @param key
     * @param value
     */
    public void put(K key,V value){
        root = put(root,key,value);
    }

    private Node<K,V> put(Node<K,V> root,K key,V value){
        if(null == root){
            this.n++;
            return new Node<>(key,value);
        }
        /**
         * b ==0 key = root.key
         * b < 0 key < root.key
         * b > 0 key > root.key
         */
        int b = key.compareTo(root.key);
        if(b < 0){
            //新节点key小于当前结点key，找当前结点的左子结点
            root.left=put(root.left,key,value);
        }else if(b > 0){
            //新节点key大于当前结点key，找当前结点的右子结点
            root.right = put(root.right,key,value);
        }else {
            //等于直接赋值
            root.value =value;
        }
        return root;
    }

    /**
     * 删除结点
     * @param key
     */
    public void delete(K key){
        root=delete(root,key);
    }

    private Node<K,V> delete(Node<K,V> root,K key){
        if(null == root)return null;
         /**
         * b ==0 key = root.key
         * b < 0 key < root.key
         * b > 0 key > root.key
         */
        int b = key.compareTo(root.key);
        if(b < 0){
            //新节点key小于当前结点key，找当前结点的左子结点
            root.left=delete(root.left,key);
        }else if(b > 0){
            //新节点key大于当前结点key，找当前结点的右子结点
            root.right = delete(root.right,key);
        }else {
            //找到要删除的结点
            //1.如果当前结点没有右子结点 ，那么直接返回左结点
            if(null == root.right ) {
                n--;
                return root.left;
            }
            //2.如果当前结点没有左结点 ，那么直接返回右结点
            if(null == root.left){
                n--;
                return root.right;
            }
            //3.左右结点都存在
            //用找右子树的最小结点 替代当前删除结点

            //找右子树最小结点
            Node<K,V> rightMin = root.right;
            while (null != rightMin.left) rightMin=rightMin.left;

            //删删除最小结点
            Node<K,V> r = root.right;
            while (null != r.left){
                if(r.left.left == null){
                    r.left = null;
                }else {
                    r = r.left;
                }
            }
            // 最小结点替换删除结点
            rightMin.left = root.left;
            rightMin.right = root.right;
            root = rightMin;
            n--;
        }
        return root;
    }


    /**
     * 查询元素
     * @param key
     * @return
     */
    public V get(K key){
        return get(root,key);
    }

    private V get(Node<K,V> root,K key){
        if(null == root)return null;
        int b = key.compareTo(root.key);
        if(b < 0){
            //新节点key小于当前结点key，找当前结点的左子结点
            return get(root.left,key);
        }else if(b > 0){
            //新节点key大于当前结点key，找当前结点的右子结点
            return get(root.right,key);
        }else {
            return root.value;
        }
    }
    /**
     * 返回元素个数
     * @return
     */
    public int size(){
        return this.n;
    }


    /**
     * 层级遍历
     */
    public void levelOrder() {
        levelOrder(root);
    }

    private void levelOrder(Node<K,V> root) {
        //用队列放入每层的结点
        Queue<Node<K,V> > q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();//每个层结点个数
            while (size-- > 0) {
                Node<K,V> node = q.poll(); //出队列
                System.out.print(node.value+" "); //读取
                if (null != node.left) q.add(node.left);
                if (null != node.right) q.add(node.right);
            }
            System.out.println();
        }
    }

    /**
     * 结点
     * @param <K>
     * @param <V>
     */
    class Node<K extends Comparable,V extends Comparable>  {
        K key; //键
        V value; //值
        Node<K,V> left; //左指针
        Node<K,V> right; //右指针

        public Node(){}

        public Node(K key, V value) {
            this(key,value,null,null);
        }

        public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        tree.put(50,"小红");
        tree.put(33,"小刚");
        tree.put(77,"小黄");
        tree.put(34,"小白");
        tree.put(100,"小明");
        tree.put(45,"小黑");
        tree.put(55,"小蓝");
        System.out.println("层级遍历");
        tree.levelOrder();

        System.out.println("100的值为:"+tree.get(100));
        tree.delete(100);
        System.out.println("100的值为:"+tree.get(100));
    }
}
