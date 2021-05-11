package com.rpy.tree;

/**
 * @program: myDemo
 * @description: 树的结点
 * @author: 任鹏宇
 * @create: 2021-05-09 16:01
 **/
public class TreeNode<E> {
    /**
     * 数据域
     */
    E data;

    /**
     * 左指针
     */
    TreeNode<E> left;

    /**
     * 右指针
     */
    TreeNode<E> right;
}
