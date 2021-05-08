package com.rpy.list;

import org.w3c.dom.Node;

/**
 * @program: myDemo
 * @description: 单链表
 * @author: 任鹏宇
 * @create: 2021-05-08 19:46
 **/
public class LinkList<E> {

    /**
     * 头结点
     */
    private Node<E> head;

    /**
     * 当前元素个数
     */
    private int size;


    /**
     * 初始化单链表
     */
    public LinkList() {
        this.size = 0;
        this.head = new Node<E>();
    }

    /**
     * 返回元素个数
     *
     * @return
     */
    public int size() {
        return this.size;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.size == 0 || head == null || head.next == null;
    }

    /**
     * 添加元素
     *
     * @param data
     */
    public void add(E data) {
//        headInsert(data); //头插法
        tailInsert(data); //尾插法
        this.size++;
    }

    /**
     * 添加到index
     *
     * @param index
     * @param data
     */
    public void add(int index, E data) {
        // 只校验index为负数情况，index如果大于size直接后加
        if (index < 0) throw new RuntimeException("index为负数不合法！");
        if (index >= size) {
            //尾插法直接后加
            tailInsert(data);
        } else if (index == 0) {
            //头插法直接在第一位置加入
            headInsert(data);
        } else {
            // index前驱结点
            Node<E> pre = getNode(index - 1);
            // index当前结点
            Node<E> curNode = getNode(index);
            // 要插入的结点
            Node<E> addNode = new Node<>(data);

            // index前驱结点的next指向插入结点
            pre.next = addNode;
            // 插入结点的next指向原index结点
            addNode.next = curNode;
        }

    }

    /**
     * 删除元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        rangeCheck(index);
        Node<E> root = head;
        Node<E> pre = head; //指向前驱
        while (null != root.next && index >= 0) {
            pre = root;
            root = root.next;
            index--;
        }
        Node<E> delNode = root;
        // 上一个结点的next域指向删除结点的next域
        pre.next = delNode.next;
        this.size--;
        return delNode.data;
    }

    /**
     * 修改index的data值
     *
     * @param index
     * @param data
     * @return
     */
    public E set(int index, E data) {
        Node<E> node = getNode(index);
        node.data = data;
        return node.data;
    }


    /**
     * 得到元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        return getNode(index).data;
    }

    private Node<E> getNode(int index) {
        rangeCheck(index);
        // 指向头指针
        Node<E> root = head;
        while (null != root.next && index >= 0) {
            root = root.next;
            index--;
        }
        return root;
    }


    /**
     * 头插法 添加元素
     *
     * @param data
     */
    private void headInsert(E data) {
        Node<E> newNode = new Node<E>(data); //新结点
        newNode.next = head.next; // 新节点的next域指向首结点
        head.next = newNode; //最后在新结点作为首结点
    }

    /**
     * 尾插法
     *
     * @param data
     */
    private void tailInsert(E data) {
        Node<E> newNode = new Node<E>(data); //新结点
        Node<E> root = head; //移动指针
        while (root.next != null) {
            root = root.next;
        }
        // root此时为链表的最后一个结点
        root.next = newNode;
    }

    /**
     * 判断index是否合法
     *
     * @param index
     * @return
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("指针不合法！" + index + "no in " + (size - 1));
        }
    }

    class Node<E> {
        /**
         * 数据域
         */
        E data;

        /**
         * 指针域
         */
        Node<E> next;

        public Node() {
        }

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        LinkList<Integer> list = new LinkList<>();
        System.out.print("头插法插入元素:");
        for (int i = 0; i < 10; i++) {
            list.add(i);
            System.out.print(i);
        }
        System.out.println();
        System.out.print("输出结果:");
        for (int i = 0; i < 10; i++) {
            System.out.print(list.get(i));
        }

        System.out.println("--------------------");
        int index = 1;
        System.out.println("删除前"+index+"位置元素为:"+list.get(index));
        Integer removeData = list.remove(index);
        System.out.println("删除"+index+"的位置元素为:"+removeData);
        System.out.println("删除后"+index+"位置元素为:"+list.get(index));
    }
}
