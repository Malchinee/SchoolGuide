package test;

import main.dao.userHelper.HashTab;
import main.entity.Person;
import org.junit.Test;

import java.io.*;

public class testHashTab {
    @Test
    public void test(){
        HashTab hashTab = new HashTab(26);
        hashTab.add(new Person("root","123456"));
        hashTab.add(new Person("asdfg","123456"));
        hashTab.add(new Person("dwad","123456"));
        hashTab.add(new Person("assdf","123456"));
        hashTab.add(new Person("fdbdf","123456"));
        hashTab.add(new Person("uyk,","123456"));
        System.out.println(hashTab.findPerByAc("root"));
        hashTab.list();
    }

    @Test
    public void testDump() throws IOException {
       ObjectOutputStream oos = new ObjectOutputStream(
               new BufferedOutputStream(new FileOutputStream(new File("./test.txt")))
       );
        HashTab hashTab = new HashTab(26);
        hashTab.add(new Person("root","123456"));
        hashTab.add(new Person("asdfg","123456"));
        hashTab.add(new Person("dwad","123456"));
        hashTab.add(new Person("assdf","123456"));
        hashTab.add(new Person("fdbdf","123456"));
        hashTab.add(new Person("uyk,","123456"));
        oos.writeObject(hashTab);
        oos.flush();
        oos.close();
    }

    @Test
    public void testLoad() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(new File("./test.txt")))
        );
        HashTab hashTab =(HashTab) ois.readObject();
        ois.close();
        hashTab.list();
    }
}
