package test;

import main.dao.UserDao;
import main.dao.userHelper.HashTab;
import main.entity.Person;
import org.junit.Test;

import java.io.IOException;

public class testUser {

    @Test
    public void userHelper() throws IOException {
        HashTab hashTab = new HashTab(26);
        hashTab.add(new Person("root","123456"));
        UserDao userDao = new UserDao();
        UserDao.setHashTab(hashTab);
        userDao.dump();
    }
    @Test
    public void testLoad() throws IOException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.load();
        HashTab hashTab = UserDao.getHashTab();
        hashTab.list();
    }
}
