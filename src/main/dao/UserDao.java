package main.dao;

import main.common.BaseContext;

import java.io.*;

public class UserDao {

    /**
     * 将内存中的hashTab的数据全部序列化写入到磁盘中
     * @throws IOException
     */
    public  void dump() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(new File("./resources/users.txt"))
                )
        );
        HashTab hashTab = BaseContext.getCurrent();
        oos.writeObject(hashTab);
        oos.flush();
        oos.close();
    }

    /**
     * 将磁盘中的数据加载到文件中
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public  void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(new File("./resources/users.txt"))
                )
        );
        HashTab hashTab= (HashTab) ois.readObject();
        BaseContext.setCurrent(hashTab);
        ois.close();
    }
}
