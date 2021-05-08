package com.rpy.list;

/**
 * @program: myDemo
 * @description: 双链表
 * @author: 任鹏宇
 * @create: 2021-05-09 00:23
 **/
public class DLinkList<E> {

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
    public DLinkList() {
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
        Node<E> root = head;
        while (null != root.next) {
            root = root.next;
        }
        Node<E> newNode = new Node<>(data); //新结点
        root.next = newNode; //末尾结点的后继结点指向 新节点
        newNode.prior = newNode; //新节点的前驱结点指向末尾结点
        this.size++;
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
        while (null != root.next && index >= 0) {
            root = root.next;
            index--;
        }
        Node<E> delNode = root;
        Node<E> priorNode = delNode.prior; //前驱结点
        Node<E> nextNode = delNode.next; //后继结点
        //删除结点的前驱结点 的next指针 指向删除结点的后继结点
        priorNode.next=nextNode;
        //删除结点的后继结点 的prior指针 指向删除结点的前驱结点
        nextNode.prior=priorNode;

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
         * 指向前驱的指针
         */
        Node<E> prior;

        /**
         * 指向后继的指针
         */
        Node<E> next;

        public Node() {
        }

        public Node(E data) {
            this.data = data;
        }
    }
    public static void main(String[] args) {
        DLinkList<Integer> list = new DLinkList<>();
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
        System.out.println("删除前;"+index+"位置元素为:"+list.get(index));
        Integer removeData = list.remove(index);
        System.out.println("删除"+index+"的位置元素为:"+removeData);
        System.out.println("删除后;"+index+"位置元素为:"+list.get(index));


    }
}
