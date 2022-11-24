package test;

import main.common.BaseContext;
import main.dao.BuildDao;
import main.dao.UserDao;
import main.dao.buildHelper.Graph;
import main.entity.Building;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class testGraph {
    @Test
    public void testCreate(){
        List<Building> list = new ArrayList<>();
        list.add( new Building("1公寓(怡海楼)","男生公寓",1));
        list.add (new Building("2公寓(安海楼)","女生公寓",2));
        list.add (new Building("3公寓(山海楼)","女生公寓",3));
        list.add (new Building("4公寓(思海楼)","男生公寓",4));
        list.add (new Building("5公寓(舞海楼)","女生公寓",5));
        list.add (new Building("6公寓(鲁海楼)","男生公寓",6));
        list.add (new Building("7公寓(齐海楼)","男女混寝",7));
        list.add (new Building("8公寓(抱海楼)","男生公寓",8));
        list.add (new Building("9公寓(聚海楼)","男生公寓",9));
        list.add (new Building("10公寓(拾海楼)","男生公寓",10));
        list.add  (new Building("11公寓(曦海楼)","男生公寓",11));
        list.add  (new Building("12公寓(沙海楼)","男生公寓",12));
        list.add  (new Building("留学生公寓","留学生公寓",13));
        list.add  (new Building("A楼(安学楼)","材料学院楼",14));
        list.add (new Building("B楼(博学楼)","语言文学院楼",15));
        list.add  (new Building("C楼(素学楼)","暂时不知道",16));
        list.add  (new Building("E楼(怡学楼)","暂时不知道",17));
        list.add (new Building("F楼(范学楼)","海院实验楼",18));
        list.add (new Building("H楼(主楼)","行政楼、教学楼",19));
        list.add (new Building("M楼(明学楼)","主要教学楼之一",20));
        list.add (new Building("G楼(功学楼)","主要教学楼之一",21));
        list.add (new Building("N楼(宁学楼)","主要教学楼之一",22));
        list.add (new Building("学苑餐厅","二层食堂",23));
        list.add (new Building("学子餐厅","四层食堂",24));
        list.add (new Building("图书馆","五层图书馆",25));
        list.add (new Building("大学生活动中心","一些活动举办场地",26));
        list.add (new Building("综合球类馆","打球的",27));
        list.add (new Building("大学生服务区","外包出去的商铺",28));
        list.add (new Building("体育场","田径场、篮球场、网球场、排球场",29));
        list.add (new Building("主楼广场","广场、日月湖、校训石",30));
        list.add (new Building("研究院","实验室、机房、研究生院",31));
        list.add (new Building("菜鸟驿站","快递点",32));
        Graph graph = new Graph(list);
        graph.list();
    }

    @Test
    public void testDump() throws IOException, ClassNotFoundException {
        List<Building> list = new ArrayList<>();
        list.add( new Building("1公寓(怡海楼)","男生公寓",1));
        list.add (new Building("2公寓(安海楼)","女生公寓",2));
        list.add (new Building("3公寓(山海楼)","女生公寓",3));
        list.add (new Building("4公寓(思海楼)","男生公寓",4));
        list.add (new Building("5公寓(舞海楼)","女生公寓",5));
        list.add (new Building("6公寓(鲁海楼)","男生公寓",6));
        list.add (new Building("7公寓(齐海楼)","男女混寝",7));
        list.add (new Building("8公寓(抱海楼)","男生公寓",8));
        list.add (new Building("9公寓(聚海楼)","男生公寓",9));
        list.add (new Building("10公寓(拾海楼)","男生公寓",10));
        list.add  (new Building("11公寓(曦海楼)","男生公寓",11));
        list.add  (new Building("12公寓(沙海楼)","男生公寓",12));
        list.add  (new Building("留学生公寓","留学生公寓",13));
        list.add  (new Building("A楼(安学楼)","材料学院楼",14));
        list.add (new Building("B楼(博学楼)","语言文学院楼",15));
        list.add  (new Building("C楼(素学楼)","暂时不知道",16));
        list.add  (new Building("E楼(怡学楼)","暂时不知道",17));
        list.add (new Building("F楼(范学楼)","海院实验楼",18));
        list.add (new Building("H楼(主楼)","行政楼、教学楼",19));
        list.add (new Building("M楼(明学楼)","主要教学楼之一",20));
        list.add (new Building("G楼(功学楼)","主要教学楼之一",21));
        list.add (new Building("N楼(宁学楼)","主要教学楼之一",22));
        list.add (new Building("学苑餐厅","二层食堂",23));
        list.add (new Building("学子餐厅","四层食堂",24));
        list.add (new Building("图书馆","五层图书馆",25));
        list.add (new Building("大学生活动中心","一些活动举办场地",26));
        list.add (new Building("综合球类馆","打球的",27));
        list.add (new Building("大学生服务区","外包出去的商铺",28));
        list.add (new Building("体育场","田径场、篮球场、网球场、排球场",29));
        list.add (new Building("主楼广场","广场、日月湖、校训石",30));
        list.add (new Building("研究院","实验室、机房、研究生院",31));
        list.add (new Building("菜鸟驿站","快递点",32));
        Graph graph = new Graph(list);
        BaseContext.setGraph(graph);
        Graph graph1 = BaseContext.getGraph();
        graph1.list();
        BuildDao buildDao = new BuildDao();
        buildDao.dump();
    }
    @Test
    public void testLoad() throws IOException, ClassNotFoundException {
        BuildDao  buildDao = new BuildDao();
        buildDao.load();
        Graph  graph = BaseContext.getGraph();
        graph.list();
    }

    @Test
    public void testAdd() throws IOException, ClassNotFoundException {
        BuildDao  buildDao = new BuildDao();
        buildDao.load();
        Graph  graph = BaseContext.getGraph();
        graph.addEdge(1,3,50);
        graph.list();
    }
}
