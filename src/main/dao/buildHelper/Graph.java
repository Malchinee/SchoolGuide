package main.dao.buildHelper;

import main.common.R;
import main.entity.Building;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Graph implements Serializable {
    private Building[] buildings;
    private int [][] edges;

    private String routeInfo;

    public void resetRouteInfo(){
        routeInfo = new String("");
    }

    public String getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(String routeInfo) {
        this.routeInfo = routeInfo;
    }

    /**
     * 构造函数
     * @param list
     */
    public Graph(List<Building> list){
        buildings = new Building[list.size()+1];
        for(int i = 1;i<buildings.length;i++){
            buildings[i] = list.get(i-1);
        }
        edges = new int[list.size()+1][list.size()+1];
    }

    public Graph(List<Building> list,int [][] edges){
        buildings = new Building[list.size()];
        for(int i = 0;i<buildings.length;i++){
            buildings[i] = list.get(i);
        }
        this.edges = edges;
        buildingsNum = list.size();
        dist = new int[list.size()][list.size()];
        path = new int[list.size()][list.size()];
        pathStack = new int[list.size()];
        visited = new int[list.size()];
        ShortPath();
    }
    public final int INFINALITY = 1000;
    private int buildingsNum;
    private int dist[][];
    private int path[][];

    /**
     * Floyd算法求两景点间的一条最短的路径
     */
    public void ShortPath() {
        int i, j, k;
        for (i = 0; i < getBuildingsNum(); i++)                                /*初始化距离向量矩阵与路径向量矩阵*/
            for (j = 0; j < getBuildingsNum(); j++) {
                dist[i][j] = getEdges()[i][j];
                if (i != j && dist[i][j] != INFINALITY) path[i][j] = i;
                else path[i][j] = -1;                                  /*代表当前两点不可达*/
            }
        for (k = 0; k < getBuildingsNum(); k++)                                /*递推求解每两景点的最短路径*/
            for (i = 0; i < getBuildingsNum(); i++)
                for (j = 0; j < getBuildingsNum(); j++)                        /*更新dist[i][j]的值*/
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
        String a = new String();
        if (path[sNum][eNum] == INFINALITY || path[sNum][eNum] == eNum || path[sNum][eNum] == sNum)
            return a;
        else {
            String b = Floyd_Print(sNum, path[sNum][eNum]);                 /*将中间点作为终点继续打印路径*/
            //System.out.println(getBuildings()[path[sNum][eNum]].getBuildName()+"->");         /*打印中间景点名字*/
            a = (getBuildings()[path[sNum][eNum]].getBuildName()+"->");
            String c = Floyd_Print(path[sNum][eNum], eNum);                 /*将中间点作为起点继续打印路径*/
            return b+a+c;
        }
    }

    /**
     * 输出并打印两点间的最短路径
     */
    public String Shortpath_Print(int sNum,int eNum) {
        resetRouteInfo();

        routeInfo = routeInfo+(getBuildings()[--sNum].getBuildName()+"到"+getBuildings()[--eNum].getBuildName()+"的最短距离是:"+ dist[sNum][eNum]+"\n");
        routeInfo = routeInfo+"这是最佳游览路线：\n";
        routeInfo = routeInfo+(getBuildings()[sNum].getBuildName()+"->");

        //System.out.println(getBuildings()[sNum].getBuildName()+"->");                                       /*输出路径上的起点*/
        routeInfo = routeInfo+Floyd_Print(sNum, eNum);                                               /*输出路径上的中间点*/
        routeInfo = routeInfo+(getBuildings()[eNum].getBuildName());
        //System.out.println(getBuildings()[eNum].getBuildName());                                     /*输出路径上的终点*/

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

    public String Dfs_Print( int sNum, int eNum) {
        resetRouteInfo();
        int dis = 0;                                                              /*用于记录路径长度*/
        pathStack[top++] = sNum;                                                    /*将本趟起点入栈*/
        visited[sNum] = 1;                                                        /*将入栈点标记为已入栈*/
        for (int i = 0; i < getBuildingsNum(); i++) {
            if (getEdges()[sNum][i] != INFINALITY && visited[i] != 1) {
                /*表明前一个入栈点与该点可达，且该点未入栈（未被访问）*/
                if (i == eNum) {                                                  /*如果深度遍历搜到了终点，就输出刚才的路径*/
                    //System.out.println("第"+(count++)+"条路:");
                    routeInfo = routeInfo+("第"+(count++)+"条路:")+"\n";
                    for (int j = 0; j < top; j++) {
                        //System.out.println(getBuildings()[pathStack[j]].getBuildName()+"->");
                        routeInfo = routeInfo+(getBuildings()[pathStack[j]].getBuildName()+"->");
                        if (j < top - 1)
                            dis = dis + getEdges()[pathStack[j]][pathStack[j + 1]];        /*统计路径长度*/
                    }
                    dis = dis + getEdges()[pathStack[top - 1]][eNum];                      /*最后一条路单独出来，因为enum不能入栈*/
                    routeInfo = routeInfo+( getBuildings()[eNum].getBuildName())+"\n";
                    routeInfo = routeInfo+("总长度是："+ dis+"\n");
                    //System.out.println( getBuildings()[eNum].getBuildName());
                    //System.out.println("总长度是："+ dis);
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
    public String Allpath_Print() {
        resetRouteInfo();
        int sNum, eNum;
        count = 1;                                                       /*路径计数器*/
        top = 0;                                                         /*栈顶*/
        do {
            System.out.println("请输入起点编号：");
            Scanner scanner = new Scanner(System.in);
            sNum = scanner.nextInt();
        } while (sNum <= 0 || sNum > getBuildingsNum());
        do {
            System.out.println("请输入终点编号：");
            Scanner scanner = new Scanner(System.in);
            eNum = scanner.nextInt();
        } while (eNum <= 0 || eNum > getBuildingsNum());
        routeInfo = routeInfo + Dfs_Print(sNum - 1, eNum - 1);
        return routeInfo;
    }

    /**
     * 多景点间求最佳路径
     */
    public String BestPath() {
        resetRouteInfo();
        ShortPath();
        int vNum[] = new int[getBuildingsNum()], j = 1;                                       /*记录用户输入的编号信息*/
        int d = 0;                                                        /*统计全程总长*/

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("请输入你要游览的第1个景点的编号（输入-1结束输入）：");
            vNum[j-1] = scanner.nextInt();
        } while ((vNum[j-1] <= 0 || vNum[j-1] > getBuildingsNum())&&vNum[j-1] != -1 || judgeInput(vNum, j));
        if (vNum[j - 1] == -1) return"";
        j++;
        while ( j <= getBuildingsNum() && vNum[j - 1] != -1) {
            //while (vNum[j - 1] > 0 && vNum[j-1] <= getBuildingsNum()) {
            do {
                System.out.println("请输入你要游览的第"+j+"个景点的编号（输入-1结束输入）：");
                vNum[j-1] = scanner.nextInt();
            } while ((vNum[j-1] <= 0 || vNum[j-1] > getBuildingsNum()) && vNum[j-1] != -1 || judgeInput(vNum, j));

            //}
            if (vNum[j - 1] == -1) break;
            if(j == getBuildingsNum()) break;
            ++j;
        }
        routeInfo = routeInfo + ("这是最佳访问路径："+"\n");
        //System.out.println("这是最佳访问路径：");
        int i = 0;
        for (;(i+1) < getBuildingsNum() && vNum[i] > 0 && vNum[i + 1] > 0; i++) {
            routeInfo = routeInfo + (getBuildings()[vNum[i] - 1].getBuildName()+"->");
            //System.out.println(getBuildings()[vNum[i] - 1].getBuildName()+"->");                   /*输出路径上的起点*/
            String b = Floyd_Print(vNum[i] - 1, vNum[i + 1] - 1);                /*利用Floyd算法*/
            routeInfo = routeInfo+b;
            d += dist[vNum[i] - 1][vNum[i + 1] - 1];
        }
        if(i+1 != getBuildingsNum())
            routeInfo = routeInfo+(getBuildings()[vNum[j - 2] - 1].getBuildName());
            //System.out.println(getBuildings()[vNum[j - 2] - 1].getBuildName());                 /*输出路径上的终点*/
        else
            routeInfo = routeInfo+(getBuildings()[vNum[j-1]-1].getBuildName());
        //System.out.println(getBuildings()[vNum[j-1]-1].getBuildName());
        routeInfo = routeInfo+("\n"+"全程总长为："+ d);
        //System.out.println("全程总长为："+ d);
        return routeInfo;
    }

    public boolean judgeInput(int[] a, int nums){
        for(int i = 0; i < nums-1; i++){
            if(a[i] == a[nums-1])
                return true;
        }
        return false;
    }


    public Building[] getBuildings() {
        return buildings;
    }

    public void setBuildings(Building[] buildings) {
        this.buildings = buildings;
    }

    public int[][] getEdges() {
        return edges;
    }

    public void setEdges(int[][] edges) {
        this.edges = edges;
    }

    public int getINFINALITY() {
        return INFINALITY;
    }

    public int getBuildingsNum() {
        return buildingsNum;
    }

    public void setBuildingsNum(int buildingsNum) {
        this.buildingsNum = buildingsNum;
    }

    public int[][] getDist() {
        return dist;
    }

    public void setDist(int[][] dist) {
        this.dist = dist;
    }

    public int[][] getPath() {
        return path;
    }

    public void setPath(int[][] path) {
        this.path = path;
    }


    /**
     * 添加边
     * @param vex1
     * @param vex2
     * @param weight
     */
    public void addEdge(int vex1 ,int vex2,int weight){
        edges[vex1][vex2] = weight;
        edges[vex2][vex1] = weight;

    }
    
    public Building getBuild(int num){
        return buildings[num];
    }

    public void updateMsg(int num,String newMsg){
        buildings[num].setMessage(newMsg);
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
