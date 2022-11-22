package test;

import main.common.BaseContext;
import main.dao.UserDao;
import main.dao.HashTab;
import main.entity.Person;
import org.junit.Test;

import java.io.*;

public class testDao {
    @Test
    public void testDump() throws IOException {
        UserDao userDao = new UserDao();
        userDao.dump();
    }

    @Test
    public void testLoad() throws IOException, ClassNotFoundException {
        UserDao userDao = new UserDao();
       userDao.load();
    }

    @Test
    public void testHelper() throws IOException {
        HashTab hashTab = new HashTab(26);
        hashTab.add(new Person("root","123456"));
        hashTab.add(new Person("asdfg","123456"));
        hashTab.add(new Person("dwad","123456"));
        hashTab.add(new Person("assdf","123456"));
        hashTab.add(new Person("fdbdf","123456"));
        hashTab.add(new Person("uyk,","123456"));
        ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(new File("./resources/users.txt")))
        );
        oos.writeObject(hashTab);
        oos.flush();
        oos.close();
        BaseContext.setCurrent(hashTab);
        BaseContext.getCurrent().list();
    }
}
