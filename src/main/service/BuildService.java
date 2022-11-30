package main.service;

import main.common.R;
import main.dao.BuildDao;
import main.entity.Building;


import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildService {
    private BuildDao buildDao = new BuildDao();


    public R getBuildMessage(int num) throws IOException, ClassNotFoundException {
        buildDao.load();
        if(num <= 0 || num>= 33){
            return R.error("请输入正确的编号");
        }
        Building building = buildDao.query(num);
        return R.success(building.getMessage());
    }
    public Building queryBuild(int num) throws IOException, ClassNotFoundException {

        buildDao.load();
        if(num <= 0 ||num >= 33){
            return null;
        }
        return  buildDao.query(num);
    }

    public R updateBuildMsg(int num,String msg) throws IOException {

        if(num <= 0 || num >= 33){
            return R.error("请输入正确的编号");
        }
        R r = buildDao.updateBuildMsg(num,msg);
        buildDao.dump();
        return r;
    }

    public R queryMinPath(String str1,String str2) throws IOException, ClassNotFoundException {
        if(!check(str1,str2)){
            return R.error("输入的格式错误");
        }
        buildDao.load();
        int sNum = Integer.parseInt(str1);
        int eNum = Integer.parseInt(str2);
        System.out.println(str1);
        if(sNum <= 0 || sNum >= 33 || eNum <= 0 || eNum >= 33){
            return R.error("请输入正确的编号");
        }
        return buildDao.queryMinPath(sNum,eNum);
    }

    public R queryAllPath(String str1,String str2) throws IOException, ClassNotFoundException {
        if(!check(str1,str2)){
            return R.error("输入的格式错误");
        }
        buildDao.load();
        int sNum = Integer.parseInt(str1);
        int eNum = Integer.parseInt(str2);
        if(sNum <= 0 || sNum >=33 || eNum <= 0 || eNum >= 33){
            return R.error("请输入正确的编号");
        }
        return buildDao.queryAllPath(sNum, eNum);
    }

    public R queryMore(String str) throws IOException, ClassNotFoundException {
        //先判断输入是否合法
        //输入的顶点以空格隔开
        for(int i = 0;i < str.length();i++){
            if(str.charAt(i) != ' ' && (str.charAt(i) < '0' || str.charAt(i) > '9')){
                return R.error("非法的输入");
            }
        }
        String[] strings = str.split(" ");
        List<Integer> list = new ArrayList<>();
        for(String item : strings){
            list.add(Integer.parseInt(item));
        }
        buildDao.load();
        return buildDao.queryMore(list);
    }

    private boolean check(String str1,String str2){
        for(int i = 0;i < str1.length();i++ ){
            if(str1.charAt(i) < '0' ||str1.charAt(i) > '9'){
                return false;
            }
        }
        for(int i = 0;i < str2.length();i++){
            if(str2.charAt(i) < '0' || str2.charAt(i) >'9'){
                return false;
            }
        }
        return true;
    }
}
