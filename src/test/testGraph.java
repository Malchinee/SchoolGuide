package test;

import main.controller.BuildController;
import main.dao.BuildDao;
import main.dao.buildHelper.Graph;
import main.entity.Building;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class testGraph {

    @Test
    public void graphHelper() throws IOException, ClassNotFoundException {
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
        BuildDao buildDao = new BuildDao();
        BuildDao.setGraph(graph);
        buildDao.add(1,3,50);
        buildDao.add(1,5,30);
        buildDao.add(1,23,100);
        buildDao.add(2,3,100);
        buildDao.add(2,28,20);
        buildDao.add(2,5,40);
        buildDao.add(2,7,50);
        buildDao.add(3,4,50);
        buildDao.add(3,7,100);
        buildDao.add(4,8,200);
        buildDao.add(4,23,50);
        buildDao.add(4,6,100);
        buildDao.add(5,15,50);
        buildDao.add(5,28,50);
        buildDao.add(6,8,100);
        buildDao.add(6,9,150);
        buildDao.add(6,12,300);
        buildDao.add(6,23,200);
        buildDao.add(6,24,120);
        buildDao.add(6,32,30);
        buildDao.add(7,28,100);
        buildDao.add(8,9,30);
        buildDao.add(9,10,30);
        buildDao.add(9,22,300);
        buildDao.add(9,24,50);
        buildDao.add(9,26,300);
        buildDao.add(10,11,30);
        buildDao.add(10,22,270);
        buildDao.add(12,13,50);
        buildDao.add(12,14,500);
        buildDao.add(12,27,300);
        buildDao.add(13,27,250);
        buildDao.add(14,19,70);
        buildDao.add(15,28,30);
        buildDao.add(15,20,40);
        buildDao.add(15,21,50);
        buildDao.add(16,17,30);
        buildDao.add(16,18,50);
        buildDao.add(16,19,100);
        buildDao.add(16,30,120);
        buildDao.add(17,31,100);
        buildDao.add(19,30,30);
        buildDao.add(19,21,70);
        buildDao.add(20,21,30);
        buildDao.add(20,28,40);
        buildDao.add(20,29,80);
        buildDao.add(21,29,70);
        buildDao.add(22,26,30);
        buildDao.add(22,25,100);
        buildDao.add(24,32,10);
        buildDao.add(25,26,100);
        buildDao.dump();
        BuildDao.setGraph(graph);
        graph.list();
    }

    @Test
    public void testLoad() throws IOException, ClassNotFoundException {
        BuildDao buildDao = new BuildDao();
        buildDao.load();
        BuildDao.getGraph().list();
    }

    @Test
    public void testSortPath() throws IOException, ClassNotFoundException {
        BuildDao buildDao = new BuildDao();
        buildDao.load();
        BuildDao.getGraph().list();
        System.out.println(buildDao.queryMinPath(11,13));
    }
}
