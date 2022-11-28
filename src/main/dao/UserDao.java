package main.dao;

import main.dao.userHelper.HashTab;
import main.entity.Person;

import java.io.*;

public class UserDao {
    private static HashTab hashTab ;
    /**
     * 将内存中的hashTab的数据全部序列化写入到磁盘中
     * @throws IOException
     */
    public  void dump() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(new File("./resources/users.bin"))
                )
        );
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
                        new FileInputStream(new File("./resources/users.bin"))
                )
        );
        hashTab = (HashTab) ois.readObject();
        hashTab.list();
        ois.close();
    }

    /**
     * 添加账户
     * @param person
     * @throws IOException
     */
    public void add(Person person) throws IOException {
        hashTab.add(person);
        dump();
    }

    /**
     * 根据账户名查询
     * @param account
     * @return
     */
    public Person query(String account) {
        return hashTab.findPerByAc(account);
    }
}
