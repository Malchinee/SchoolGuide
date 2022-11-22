package main.service;

import main.common.BaseContext;
import main.common.R;
import main.dao.UserDao;
import main.dao.HashTab;
import main.entity.Person;

import java.io.IOException;

public class UserService {
    private UserDao userDao;

    //从磁盘读取用户
    public void load() throws IOException, ClassNotFoundException {
        userDao.load();
    }
    //将用户写入磁盘
    public void dump() throws IOException {
        userDao.dump();
    }

    public R register(Person person) throws IOException, ClassNotFoundException {
        //如果已经有该account注册的用户，返回false
        HashTab hashTab = BaseContext.getCurrent();
        if(hashTab.findPerByAc(person.account) != null){
            return R.error("该用户已经被注册");
        }
        //添加用户
        hashTab.add(person);
        return R.success();
    }

    public R login(Person person){
        HashTab hashTab = BaseContext.getCurrent();
        Person personChecked = hashTab.findPerByAc(person.account);
        //先查看是否该用户
        if(personChecked == null){
            return R.error("没有此用户");
        }
        //再对比密码是否一致
        if(personChecked.password != person.password){
            return R.error("密码错误");
        }
        return R.success();
    }
}
