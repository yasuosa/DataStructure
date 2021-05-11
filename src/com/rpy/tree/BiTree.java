package com.rpy.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: myDemo
 * @description: 二叉树
 * @author: 任鹏宇
 * @create: 2021-05-09 16:17
 **/
public class BiTree<E> {

    /**
     * 根结点
     */
    private TreeNode<E> root;


    /**
     * 先序遍历 根 -> 左 -> 右
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 1.确定返回(终止)条件 : 如果当前结点不存在 就返回终止
     * 2.确定执行的内容:读取当前结点
     * 3.确定顺序：读取当前结点，在读取左子树，最后读取右子树
     *
     * @param root
     */
    private void preOrder(TreeNode<E> root) {
        if (null == root) return;
        System.out.println(root.data); //读取
        preOrder(root.left); //向左
        preOrder(root.right);//向右
    }


    /**
     * 中序遍历 左 -> 根 -> 右
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(TreeNode<E> root) {
        if (null == root) return;
        preOrder(root.left); //向左
        System.out.println(root.data); //读取
        preOrder(root.right);//向右
    }

    /**
     * 后序遍历 左 -> 右 ->根
     */
    public void postOrder() {
        inOrder(root);
    }

    private void postOrder(TreeNode<E> root) {
        if (null == root) return;
        preOrder(root.left); //向左
        preOrder(root.right);//向右
        System.out.println(root.data); //读取
    }


    /**
     * 层级遍历
     */
    public void levelOrder() {
        levelOrder(root);
    }

    private void levelOrder(TreeNode<E> root) {
        //用队列放入每层的结点
        Queue<TreeNode<E>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();//每个层结点个数
            while (size-- > 0) {
                TreeNode<E> node = q.poll(); //出队列
                System.out.print(node.data); //读取
                if (null != node.left) q.add(node.left);
                if (null != node.right) q.add(node.right);
            }
            System.out.println();
        }
    }
}
