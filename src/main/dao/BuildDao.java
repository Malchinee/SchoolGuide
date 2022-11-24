package main.dao;

import main.common.BaseContext;
import main.dao.buildHelper.Graph;

import java.io.*;

public class BuildDao {
    /**
     * 从磁盘中加载图的信息，然后把它放入BaseContext中
     */
    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(new File("./resources/buildings.bin")))
        );
        Graph graph = (Graph) ois.readObject();
        BaseContext.setGraph(graph);
        ois.close();
    }

    /**
     * 将图的信息持久化到磁盘里
     */
    public void dump() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(new File("./resources/buildings.bin")))
        );
        oos.writeObject(BaseContext.getGraph());
        oos.flush();
        oos.close();
    }
}
