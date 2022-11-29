package main.controller;

import main.common.R;
import main.entity.Building;
import main.service.BuildService;

import java.io.IOException;

public class BuildController {
    private BuildService buildService = new BuildService();

    public R getBuildingMessage(int num) throws IOException, ClassNotFoundException {
        return buildService.getBuildMessage(num);
    }

    public R updateBuildMsg(int num,String msg) throws IOException {
        return buildService.updateBuildMsg(num,msg);
    }

    public Building queryBuild(int num) throws IOException, ClassNotFoundException {
        return buildService.queryBuild(num);
    }
}
