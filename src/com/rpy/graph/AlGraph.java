package com.rpy.graph;

/**
 * @program: myDemo
 * @description: 邻接表表示法
 * @author: 任鹏宇
 * @create: 2021-05-11 00:23
 **/
public class AlGraph<E>{
    /**
     * 默认顶点表的大小
     */
    public static final Integer DEFAULT_VEXNUM = 15;
    /**
     * 顶点表
     */
    private AdjList<E>[] vexs;

    /**
     * 顶点数
     */
    private int V;

    /**
     * 边数
     */
    private int E;

    /**
     * 顶点表最大容量
     */
    private int maxVexNum;

    public AlGraph(int vexnum){
        this.maxVexNum= vexnum;
        this.V= 0;
        this.E=0;
        this.vexs = new AdjList[maxVexNum];
    }

    public AlGraph(){
        this(DEFAULT_VEXNUM);
    }


    /**
     * 添加一条有向边
     * @param x
     * @param y
     */
    public void addEdge(E x,E y){
        int i1=addVex(x);
        int i2 = addVex(y);
        AdjList<E> adj1 = vexs[i1];
        ArcNode firstArc = adj1.firstArc;
        // 当前没有指向
        if(null == firstArc){
            adj1.firstArc = new ArcNode(i2,null);
        }else {
            ArcNode r = firstArc;
            while (null!=r.nextVex){
                r= r.nextVex;
            }
            r.nextVex = new ArcNode(i2,null);
        }
        E++;
    }



    /**
     * 添加一条无向边
     * @param x
     * @param y
     */
    public void addUEdge(E x,E y){
        int i1=addVex(x);
        int i2 = addVex(y);

        //x->y
        //顶点x
        AdjList<E> adj1 = vexs[i1];
        //顶点x第一个邻接顶点
        ArcNode firstArc1 = adj1.firstArc;
        //如果没有接添加
        if(null == firstArc1){
            adj1.firstArc = new ArcNode(i2,null);
        }else {
            //存在就追加在表的尾部
            ArcNode r = firstArc1;
            while (null!=r.nextVex){
                r= r.nextVex;
            }
            r.nextVex = new ArcNode(i2,null);
        }

        //y->x
        AdjList<E> adj2 = vexs[i2];
        ArcNode firstArc2 = adj2.firstArc;
        if(null == firstArc2){
            adj2.firstArc = new ArcNode(i1,null);
        }else {
            ArcNode r = firstArc2;
            while (null!=r.nextVex){
                r= r.nextVex;
            }
            r.nextVex = new ArcNode(i1,null);
        }
        E++;
    }


    private int addVex(E e){
        int index = locatedVex(e);
        if(-1 == index){
            rangCheck();
            AdjList<E> adj = new AdjList<>();
            adj.data = e;
            vexs[V] = adj;
            index = V++;
        }
        return index;
    }

    /**
     * 找到顶点位置
     * @param e
     * @return
     */
    private int locatedVex(E e){
        for (int i = 0; i < V; i++) {
            AdjList<E> adj = vexs[i];
            if(null ==adj) continue;
            if(e.equals(adj.data)) return i;
        }
        return -1;
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


    private void rangCheck(){
        if(this.V >= this.maxVexNum) throw new RuntimeException("顶点表已满！");
    }

    private void print(){
        for (int i = 0; i < V; i++) {
            System.out.print("("+i+","+vexs[i].data+"): ");
            ArcNode firstArc = vexs[i].firstArc;
            while (null != firstArc){
                System.out.print("->"+firstArc.adjVex);
                firstArc=firstArc.nextVex;
            }
            System.out.println();
        }
    }


    class AdjList<E>{
        /**
         * 数据
         */
        E data;

        /**
         * 指向第一个邻接顶点
         */
        ArcNode firstArc;

        public AdjList(){}

        public AdjList(E data) {
            this.data = data;
        }

        public AdjList(E data, ArcNode firstArc) {
            this.data = data;
            this.firstArc = firstArc;
        }
    }

    class ArcNode{
        /**
         * 顶点坐标
         */
        int adjVex;

        /**
         * 有邻接关系的顶点
         */
        ArcNode nextVex;

        public ArcNode(){}

        public ArcNode(int adjVex, ArcNode nextVex) {
            this.adjVex = adjVex;
            this.nextVex = nextVex;
        }
    }

    public static void main(String[] args) {
        AlGraph<String> g1= new AlGraph<>();
        System.out.println("有向图");
        g1.addEdge("A","B");
        g1.addEdge("A","D");
        g1.addEdge("B","C");
        g1.addEdge("D","C");
        g1.print();
        System.out.println("--------------");
        System.out.println("无向图");
        AlGraph<String> g2 =new AlGraph<>();
        g2.addUEdge("A","B");
        g2.addUEdge("A","D");
        g2.addUEdge("C","D");
        g2.addUEdge("C","B");
        g2.print();
    }
}
