package main.common;

import main.dao.buildHelper.Graph;
import main.dao.userHelper.HashTab;

public class BaseContext {
    private static HashTab hashTab ;
    private static Graph graph;

    public static HashTab getHashTab() {
        return hashTab;
    }

    public static void setHashTab(HashTab hashTab) {
        BaseContext.hashTab = hashTab;
    }

    public static Graph getGraph(){
        return graph;
    }

    public static void setGraph(Graph graph){
        BaseContext.graph = graph;
    }
}
