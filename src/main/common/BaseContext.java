package main.common;

import main.dao.userHelper.HashTab;

public class BaseContext {
    private static HashTab hashTab ;

    public static HashTab getHashTab() {
        return hashTab;
    }

    public static void setHashTab(HashTab hashTab) {
        BaseContext.hashTab = hashTab;
    }
}
