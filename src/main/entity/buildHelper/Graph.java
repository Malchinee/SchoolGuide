package main.entity.buildHelper;

import main.common.R;
import main.entity.Building;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Graph implements Serializable {
    private Building[] buildings;
    private int [][] edges;

    private String routeInfo;

    public final int INFINALITY = 10000;
    private int buildingsNum;
    private int dist[][];
    private int path[][];

    public void resetRouteInfo(){
        routeInfo = "";
    }


    public Graph(){

    }

    /**
     * 构造函数
     * @param list
     */


    public Graph(List<Building> list){
        buildings = new Building[list.size()];
        for(int i = 0;i<list.size();i++){
            buildings[i] = list.get(i);
        }
        edges = new int[list.size()][list.size()];
        for(int i = 0;i < list.size();i++){
            for(int j = 0 ;j < list.size() ;j++){
                if(i != j){
                    edges[i][j] = INFINALITY;
                }
            }
        }
        buildingsNum = list.size();
        dist = new int[list.size()][list.size()];
        path = new int[list.size()][list.size()];
        pathStack = new int[list.size()];
        visited = new int[list.size()];
        ShortPath();
    }


    /**
     * Floyd算法求两景点间的一条最短的路径
     */
    public void ShortPath() {
        int i, j, k;
        for (i = 0; i < buildingsNum; i++)                                /*初始化距离向量矩阵与路径向量矩阵*/
            for (j = 0; j < buildingsNum; j++) {
                if(edges[i][j] == -1)
                    edges[i][j] = INFINALITY;
                dist[i][j] = edges[i][j];


                if (i != j && dist[i][j] != INFINALITY) path[i][j] = i;
                else path[i][j] = -1;                                  /*代表当前两点不可达*/
            }
        for (k = 0; k < buildingsNum; k++)                                /*递推求解每两景点的最短路径*/
            for (i = 0; i < buildingsNum; i++)
                for (j = 0; j < buildingsNum; j++)                        /*更新dist[i][j]的值*/
                    if (dist[i][j] >(dist[i][k] + dist[k][j])) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = k;                                /*path用于记录最短路径上的经结点*/
                    }
    }

    /**
     * 递归实现打印两点间的最短路径
     * @param sNum
     * @param eNum
     */
    public String Floyd_Print(int sNum, int eNum) {
        String a = "";
        if (path[sNum][eNum] == INFINALITY || path[sNum][eNum] == eNum || path[sNum][eNum] == sNum)
            return a;
        else {
            String b = Floyd_Print(sNum, path[sNum][eNum]);                 /*将中间点作为终点继续打印路径*/
            a = (buildings[path[sNum][eNum]].getBuildName()+"->");
            String c = Floyd_Print(path[sNum][eNum], eNum);                 /*将中间点作为起点继续打印路径*/
            return b+a+c;
        }
    }

    /**
     * 输出并打印两点间的最短路径
     */
    public String Shortpath_Print(int sNum,int eNum) {
        sNum--;
        eNum--;
        ShortPath();
        resetRouteInfo();
        routeInfo = buildings[sNum].getBuildName() + "到" + buildings[eNum].getBuildName() + "的最短距离是:" + dist[sNum][eNum] + "\r\n" +
                "这是最佳游览路线：\r\n" +
                buildings[sNum].getBuildName() + "->" +
                Floyd_Print(sNum, eNum) +                                              /*输出路径上的中间点*/
                buildings[eNum].getBuildName();
        return routeInfo;
    }




    //------------------------------------------------------------------------
    /**
     * 深度优先遍历查询任意两个景点之间的所有路径
     */
    public int[] pathStack;                                                             /*路径栈，存储路径信息*/
    /*栈顶*/
    public int[] visited;                                                               /*入栈标记，防止形成回路*/
    public int top = 0;                                                                    /*路径计数器*/
    public int count = 1;

    private String Dfs_Print( int sNum, int eNum) {
        resetRouteInfo();
        int dis = 0;                                                              /*用于记录路径长度*/
        pathStack[top++] = sNum;                                                    /*将本趟起点入栈*/
        visited[sNum] = 1;                                                        /*将入栈点标记为已入栈*/
        for (int i = 0; i < buildingsNum; i++) {
            if (edges[sNum][i] != INFINALITY && visited[i] != 1) {
                /*表明前一个入栈点与该点可达，且该点未入栈（未被访问）*/
                if (i == eNum) {                                                  /*如果深度遍历搜到了终点，就输出刚才的路径*/
                    routeInfo = routeInfo+("第"+(count++)+"条路:")+"\n";
                    for (int j = 0; j < top; j++) {
                        routeInfo = routeInfo+(buildings[pathStack[j]].getBuildName()+"->");
                        if (j < top - 1)
                            dis = dis + edges[pathStack[j]][pathStack[j + 1]];        /*统计路径长度*/
                    }
                    dis = dis + edges[pathStack[top - 1]][eNum];                      /*最后一条路单独出来，因为enum不能入栈*/
                    routeInfo = routeInfo+( buildings[eNum].getBuildName())+"\n";
                    routeInfo = routeInfo+("总长度是："+ dis+"\n");
                }
                else {
                    routeInfo = routeInfo + Dfs_Print(i, eNum);                                              /*如果该点不是终点,接着深度搜索*/
                    top--;                                                              /*支路全被访问一遍后，顶点出栈*/
                    visited[i] = 0;                                                     /*将出栈点标记为已出栈，允许下次访问*/
                }
            }
        }
        return routeInfo;
    }

    /**
     * 查询任意两个景点之间的所有路径并打印
     */
    public String Allpath_Print(int sNum,int eNum) {
        resetRouteInfo();
        routeInfo = routeInfo + Dfs_Print(sNum - 1, eNum - 1);
        return routeInfo;
    }

    /**
     * 多景点间求最佳路径
     */
    public R BestPath(List<Integer> list) {
        resetRouteInfo();
        ShortPath();
        int d = 0;                                                        /*统计全程总长*/
        int vNum[] = new int[buildingsNum];
        if(list.size() > buildingsNum){
            return R.error("输入景点超出最大数目");
        }

        int j = 0;
        for(; j < list.size(); j++){
            vNum[j] = list.get(j);
            if(judgeInput(vNum,j)){
                return R.error("景点重复");
            }
            if((list.get(j) <= 0 || list.get(j) > buildingsNum)){
                return R.error("景点输入错误");
            }
        }

        routeInfo = routeInfo + ("这是最佳访问路径："+"\n");

        int i = 0;
        for (;(i+1) < buildingsNum && vNum[i] > 0 && vNum[i + 1] > 0; i++) {
            routeInfo = routeInfo + (buildings[vNum[i] - 1].getBuildName()+"->");
            /*输出路径上的起点*/
            String b = Floyd_Print(vNum[i] - 1, vNum[i + 1] - 1);                /*利用Floyd算法*/
            routeInfo = routeInfo+b;
            d += dist[vNum[i] - 1][vNum[i + 1] - 1];
        }

        routeInfo = routeInfo+buildings[vNum[list.size()-1]-1].getBuildName();

        routeInfo = routeInfo+("\n"+"全程总长为："+ d);

        return R.success(routeInfo);
    }
    private boolean judgeInput(int[] a, int nums){
        for(int i = 0; i < nums-1; i++){
            if(a[i] == a[nums-1])
                return true;
        }
        return false;
    }



    /**
     * 添加边
     * @param vex1
     * @param vex2
     * @param weight
     */
    public void addEdge(int vex1 ,int vex2,int weight){
        edges[vex1-1][vex2-1] = weight;
        edges[vex2-1][vex1-1] = weight;

    }

    public Building getBuild(int num){
        return buildings[num-1];
    }

    public void updateMsg(int num,String newMsg){
        buildings[num-1].setMessage(newMsg);
    }

    /**
     * 展示图
     */
    public void list(){
        for(int i = 1;i < buildings.length;i++){
            for(int j = 1 ;j < buildings.length;j++){
                String numbers = String.valueOf(edges[i][j]);
                if(numbers.length() == 3){
                    System.out.print(edges[i][j]+"\t");
                }else if(numbers.length() == 2){
                    System.out.print(edges[i][j] +" "+"\t");
                }else {
                    System.out.print(edges[i][j] +"  "+"\t");
                }

            }
            System.out.println();
        }
        System.out.println(Arrays.toString(buildings));
        System.out.println(buildings.length);
    }

}
