package main.service;

import main.common.R;
import main.dao.BuildDao;
import main.entity.Building;


import java.io.IOException;

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

    public R queryMinPath(int sNum,int eNum) throws IOException, ClassNotFoundException {
        buildDao.load();
        if(sNum <=0 || eNum >= 33){
            return R.error("请输入正确的编号");
        }
        return buildDao.queryMinPath(sNum,eNum);
    }

}
