package com.rpy.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: myDemo
 * @description: 二叉平衡树
 * @author: 任鹏宇
 * @create: 2021-05-09 16:57
 **/
public class BalancedBinarySearchTree<K extends Comparable<K>,V extends Comparable<V>> {

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
            // 判断平衡因子
            // 左()型
            if(height(root.left) - height(root.right) == 2){
                int b1= key.compareTo(root.right.key);
                if(b1 < 0){
                    // 左左型
                    root = rightRotate(root);
                }else {
                    // 左右型
                    root =leftRightRotate(root);
                }
            }
        }else if(b > 0){
            //新节点key大于当前结点key，找当前结点的右子结点
            root.right = put(root.right,key,value);
            // 判断平衡因子
            // 右()型
            if(height(root.left) - height(root.right) == -2){
                int b1= key.compareTo(root.right.key);
                if(b1 > 0){
                    // 右右型
                    root = leftRotate(root);
                }else {
                    // 右左型
                    root =rightLeftRotate(root);
                }
            }
        }else {
            //等于直接赋值
            root.value =value;
        }
        //更新深度
        root.height = Math.max(height(root.left),height(root.right))+1;
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
     * 得到高度
     * @param root
     * @return
     */
    private int height(Node<K,V> root){
        if(null == root){
            return 0;
        }
        return root.height;
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
     * 左旋
     * @param node
     * @return
     */
    private Node<K,V> leftRotate(Node<K,V> node){
        Node<K,V> rchild = node.right; //右子结点
        node.right = rchild.left;
        rchild.left = node;

        //更新结点深度
        node.height= Math.max(height(node.left),height(node.right))+1;
        rchild.height= Math.max(height(rchild.left),height(rchild.right))+1;
        return rchild; //右子结点取代成为双钱
    }

    /**
     * 右旋
     * @param node
     * @return
     */
    private Node<K,V> rightRotate(Node<K,V> node){
        Node<K,V> lchild = node.left; //左子结点
        node.left = lchild.right;
        lchild.right = node;
        //更新结点深度
        node.height= Math.max(height(node.left),height(node.right))+1;
        lchild.height= Math.max(height(lchild.left),height(lchild.right))+1;
        return lchild;//左子节点取代成为双亲
    }

    /**
     * 左旋+右旋
     * @param node
     * @return
     */
    private Node<K,V> leftRightRotate(Node<K,V> node){
        Node<K,V> lchild = node.left; //左子结点 需要左旋
        node.right = leftRotate(lchild);
        //右旋
        return rightRotate(node);
    }

    /**
     * 右旋+左旋
     * @param node
     * @return
     */
    private Node<K,V> rightLeftRotate(Node<K,V> node){
        Node<K,V> rchild = node.right; //右子结点 需要右旋
        node.right = rightRotate(rchild);

        //左旋
        return leftRotate(node);
    }

    /**
     * 结点
     * @param <K>
     * @param <V>
     */
    class Node<K extends Comparable,V extends Comparable>  {
        K key; //键
        V value; //值
        int height; //结点深度
        Node<K,V> left; //左指针
        Node<K,V> right; //右指针

        public Node(){}

        public Node(K key, V value) {
            this(key,value,1,null,null);
        }

        public Node(K key, V value, int height, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        BalancedBinarySearchTree<Integer, String> tree = new BalancedBinarySearchTree<>();
        tree.put(1,"小红");
        tree.put(2,"小刚");
        tree.put(3,"小黄");
        tree.put(4,"小白");
        tree.put(5,"小明");
        tree.put(6,"小黑");
        tree.put(7,"小蓝");
        System.out.println("层级遍历");
        tree.levelOrder();
    }
}
