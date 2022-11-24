package main.dao.buildHelper;

import main.entity.Building;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Graph implements Serializable {
    private Building[] buildings;
    private int [][] edges;

    public Graph(List<Building> list){
        buildings = new Building[list.size()+1];
        for(int i = 1;i<buildings.length;i++){
            buildings[i] = list.get(i-1);
        }
        edges = new int[list.size()+1][list.size()+1];
    }
    public void addEdge(int vex1 ,int vex2,int weight){
        edges[vex1][vex2] = weight;
        edges[vex2][vex1] = weight;

    }
    public void list(){
        for(int i = 1;i < buildings.length;i++){
            for(int j = 1 ;j < buildings.length;j++){
                System.out.print(edges[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println(Arrays.toString(buildings));
        System.out.println(buildings.length);
    }

}
