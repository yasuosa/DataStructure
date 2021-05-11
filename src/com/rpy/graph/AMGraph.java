package com.rpy.graph;


import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @program: myDemo
 * @description: 邻接举证表示法
 * @author: 任鹏宇
 * @create: 2021-05-10 22:37
 **/
public class AMGraph<E> {

    /**
     * 默认顶点表的大小
     */
    public static final Integer DEFAULT_VEXNUM = 15;
    /**
     * 顶点表
     */
    private E[] vexs;

    /**
     * 邻接矩阵
     */
    private int[][] arcs;

    /**
     * 当前顶点个数
     */
    int V;

    /**
     * 当前边数
     */
    int E;

    /**
     * 顶点数
     */
    int vexnum;

    /**
     * 边数
     */
    int arcnum;

    public AMGraph() {
        this(DEFAULT_VEXNUM, DEFAULT_VEXNUM*DEFAULT_VEXNUM / 2);
    }

    /**
     * @param vexnum 顶点数
     * @param arcnum 边数
     */
    public AMGraph(int vexnum, int arcnum) {
        this.vexnum = vexnum;
        this.arcnum = arcnum;
        this.V = 0;
        this.E = 0;
        //初始顶点表
        this.vexs = (E[]) new Object[vexnum];
        //初始化邻接矩阵
        this.arcs = new int[vexnum][vexnum];
    }

    /**
     * 返回边数V
     *
     * @return
     */
    public int V() {
        return this.V;
    }

    /**
     * 返回边数
     *
     * @return
     */
    public int E() {
        return this.E;
    }

    /**
     * 添加有向边
     *
     * @param x
     * @param y
     */
    public void addEdge(E x, E y) {
        //检查关系是否超出
        rangeCheck();
        //添加顶点x
        int i1 = addVex(x);
        //添加顶点y
        int i2 = addVex(y);
        //标记
        this.arcs[i1][i2] = 1;
        this.E++;
    }

    /**
     * 添加无向边
     */
    public void addUEdge(E x, E y) {
        //检查关系是否超出
        rangeCheck();
        //添加顶点x
        int i1 = addVex(x);
        //添加顶点y
        int i2 = addVex(y);
        //标记
        this.arcs[i1][i2] = 1;
        this.arcs[i2][i1] = 1;
        this.E++;
    }


    /**
     * 添加顶点到顶点表,并返回位置
     *
     * @param e
     * @return
     */
    private int addVex(E e) {
        int i = locateVex(e);
        if (-1 == i) {
            i = V;
            vexs[V++] = e;
        }
        return i;
    }


    /**
     * 找到顶点在顶点表的位置
     *
     * @param e
     * @return
     */
    private int locateVex(E e) {
        for (int i = 0; i < V; i++) {
            if (e.equals(vexs[i])) return i;
        }
        return -1;
    }

    /**
     * 判断范围
     */
    private void rangeCheck() {
        if (this.E >= this.arcnum) throw new RuntimeException("边数限定已满");
    }


    /**
     * 打印矩阵
     */
    public void  print(){
        System.out.print("  ");
        for (int i = 0; i < V; i++) {
            System.out.print(vexs[i]+" ");
        }
        System.out.println();
        for (int i = 0; i < V; i++) {
            System.out.print(vexs[i]+" ");
            for (int j = 0; j < V; j++) {
                System.out.print(arcs[i][j]+" ");
            }
            System.out.println();
        }
    }


    /**
     *
     *深度优先搜索
     *
     * @param start 开始点
     */
    public void dfs(E start){
        int i = locateVex(start);
        dfs(i,new boolean[V]);
    }

    /**
     * dfs
     * @param start
     * @param route 记录已被查询顶点
     */
    private void dfs(int start,boolean[] route){
        // 没被访问
        if(!route[start]){
            //标记当前点被访问
            route[start] = true;
            System.out.print(vexs[start]);
            for(int i=0;i<V;i++){
                // 找邻接点
                if(1==arcs[start][i]){
                    dfs(i,route);//继续深度搜索
                }
            }
        }
    }


    /**
     * 广度优先搜索
     * @param e
     */
    private void bfs(E e){
        int i = locateVex(e); //查询起始点位置
        boolean[] route = new boolean[V]; //辅助标记
        bfs(i,route);

        // 不连通的情况
        for (int j = 0; j < V; j++) {
            if(!route[j]) dfs(j,route);
        }
    }

    private void bfs(int start,boolean[] route){
        Queue<Integer> q=new ArrayDeque<>(); //创建一个队列
        q.add(start); //将起点入队
        while (!q.isEmpty()){
            int size= q.size();
            while (size-- > 0){
                int i = q.poll(); //出队
                if(!route[i]){ //是否被访问
                    route[i] = true; //标记访问过了
                    System.out.print(vexs[i]);
                    for (int j = 0; j < V; j++) {
                        if(arcs[i][j]==1) q.add(j); //存在邻接关系 入队
                    }
                }
            }
            System.out.println();
        }
    }



    public static void main(String[] args) {
        AMGraph<String> g1= new AMGraph<>();
        System.out.println("有向图");
        g1.addEdge("A","B");
        g1.addEdge("A","D");
        g1.addEdge("B","C");
        g1.addEdge("D","C");
        g1.print();
        g1.bfs("A");
        System.out.println();
        System.out.println("--------------");
        System.out.println("无向图");
        AMGraph<String> g2 =new AMGraph<>();
        g2.addUEdge("A","B");
        g2.addUEdge("A","D");
        g2.addUEdge("C","D");
        g2.addUEdge("C","B");
        g2.print();
        g2.bfs("A");
    }
}
