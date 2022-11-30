package main.service;

import main.common.R;
import main.dao.UserDao;
import main.entity.Person;

import java.io.IOException;

public class UserService {
    private UserDao userDao =new UserDao() ;

    public R register(Person person) throws IOException, ClassNotFoundException {
        userDao.load();
        //先检查person是否合法
        //账户名必须是小写开头，并且全都是小写
        String account = person.account;
        for(int i = 0; i < account.length();i++){
            if(account.charAt(i) < 'a' || account.charAt(i) > 'z') return R.error("账户名格式错误");
        }
        //密码必须大于等于6位
        if(person.password!=null && person.password.length() < 6){
            return R.error("密码长度必须大于6位");
        }
        //如果已经有该account注册的用户，返回false
        if(userDao.query(person.account) != null){
            return R.error("该用户已经被注册");
        }

        //添加用户
        userDao.add(person);
        userDao.dump();
        return R.success("注册成功");
    }

    public R login(Person person) throws IOException, ClassNotFoundException {
        userDao.load();
        Person personChecked = userDao.query(person.account);
        //先查看是否该用户
        if(personChecked == null){
            return R.error("没有此用户");
        }
        //再对比密码是否一致
        if(!personChecked.password.equals(person.password)){
            return R.error("密码错误");
        }
        return R.success("登录成功");
    }


}
