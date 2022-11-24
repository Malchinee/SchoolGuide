package main.dao.buildHelper;

import main.entity.Building;

import java.io.Serializable;
import java.util.List;

public class Graph implements Serializable {
    private Building[] buildings;
    private int [][] edges;

    public Graph(List<Building> list,int [][] edges){
        buildings = new Building[list.size()];
        for(int i = 0;i<buildings.length;i++){
            buildings[i] = list.get(i);
        }
        this.edges = edges;
    }

}
