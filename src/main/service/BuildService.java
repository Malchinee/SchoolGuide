package main.service;

import main.common.BaseContext;
import main.common.R;
import main.dao.BuildDao;
import main.dao.buildHelper.Graph;

import java.io.IOException;

public class BuildService {
    private BuildDao  buildDao;

    /**
     * 将图写入磁盘中
     * @throws IOException
     */
    public void dump() throws IOException {
        buildDao.dump();
    }

    /**
     * 加入边
     * @return
     */
//    public R addEdges(int vex1,int vex2,int weight){
//        Graph graph = BaseContext.getGraph();
//
//    }
}
