package com.rpy.list;


/**
 * @program: myDemo
 * @description: 线性表(顺序表)
 * @author: 任鹏宇
 * @create: 2021-05-07 20:40
 **/
public class SeqList<E> {

    /**
     * 默认最大容量为10
     */
    public static final int DEFAULT_MAX_CAPTION = 10;

    /**
     * 数据域
     */
    private Object[] datas;

    /**
     * 容量
     */
    private int caption;

    /**
     * 当前元素个数
     */
    private int size;

    /**
     * 默认初始化
     */
    public SeqList() {
        this(DEFAULT_MAX_CAPTION);
    }

    /**
     * 设置顺序容量大小
     * @param caption
     */
    public SeqList(int caption) {
        this.size = 0;
        this.caption = caption;
        this.datas = new Object[caption];
    }

    /**
     * 判断顺序表是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0 || caption == 0;
    }

    /**
     * 返回当前元素个数
     * @return
     */
    public int size(){
        return this.size;
    }

    /**
     * 添加元素
     * @param data
     */
    public void add(E data){
        checkCaptionOut();
        this.datas[this.size++] = data;
    }

    /**
     * 添加元素到指定位置
     * @param data
     * @param index
     */
    public void add(E data,int index){
        checkCaptionOut();//首先判断元素是否满了
        rangeCheckOfAdd(index);//判断下标

        //如果位置大于等于当前元素长度，直接追加
        if(index >= size){
            add(data);
        }else {
            // 将index之后包含index位置的元素向后移动一位
            for(int i=index+1;i<size;i++){
                this.datas[i] = this.datas[i-1];
            }
            // 将index位置值赋值
            this.datas[index] = data;
        }
    }


    /**
     * 删除最后元素
     * @return
     */
    public E remove(){
        E removeData = null;
        if(!isEmpty()) {
            this.size--;
            removeData = (E) this.datas[size];
        }
        return removeData;
    }

    /**
     * 删除指定位置的元素
     * @param index
     * @return
     */
    public E remove(int index){
        rangeCheck(index);
        E removeData = null;
        if(!isEmpty()){
            removeData = (E) this.datas[index];
            for(int i=index;i<size-1;i++){
                this.datas[i] = this.datas[i+1];
            }
            this.size--;
        }
        return removeData;
    }


    /**
     * 修改指定位置的值
     * @param index
     * @param data
     */
    public void set(int index,E data){
        rangeCheck(index);
        this.datas[index] = data;
    }



    /**
     * 获取下标数据值
     * @param index
     * @return
     */
    public E get(int index){
        rangeCheck(index);
        return (E) this.datas[index];
    }

    /**
     * 判断值是否在当前顺序表内
     * @param data
     * @return 找到返回第一个下标地址；没有则返回-1
     */
    public int contains(E data){
        for (int i = 0; i < size; i++) {
            if(datas[i].equals(data)) return i;
        }
        return -1;
    }

    /**
     * 判断是否越界
     * @param index
     */
    private void rangeCheck(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("当前数组下标越界! "+index+" not in "+ (size-1));
        }
    }

    /**
     * 指定位置判断
     */
    private void rangeCheckOfAdd(int index) {
        if(index <0 || index >= caption){
            throw new RuntimeException("下标不合法["+index+"]!");
        }
    }

    /**
     * 判断是否超过了容量
     */
    private void checkCaptionOut() {
        if(this.size >= this.caption) {
            throw new RuntimeException("当前顺序表容量为"+caption+"已满;");
        }
    }
}
