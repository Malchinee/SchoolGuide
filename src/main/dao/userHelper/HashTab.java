package main.dao.userHelper;

import main.entity.Person;

import java.io.Serializable;

public class HashTab implements Serializable {


    //处理哈希冲突的方法：链地址法
    private PerLinkedList[] perLinkedLists;
    //应该取26个，哈希函数是直接取了account的首字母
    private int size;


    /**
     * 构造方法
     * @param size
     */
    public HashTab(int size){
        this.size = size;
        perLinkedLists = new PerLinkedList[size];
        for(int i = 0;i<size ;i++){
            perLinkedLists[i] = new PerLinkedList();
        }
    }

    public void list(){
        for(int i = 0 ;i < perLinkedLists.length;i++){
            perLinkedLists[i].list(i);
        }
    }
    /**
     * 根据account查询person
     * @param account
     * @return
     */
    public Person findPerByAc(String account){
        int perLinkedListNo = hashFun(account);
        Person per = perLinkedLists[perLinkedListNo].findPerById(account);
        return per;
    }
    /**
     * 将person添加到哈希表中
     * @param per
     */
    public void add(Person per){
        //得到第几条链
        int perLinkedListNo = hashFun(per.account);
        //将这个人添加
        perLinkedLists[perLinkedListNo].add(per);
    }

    /**
     * 哈希函数，将account的所有字母小写，取首字母
     * @param account
     * @return
     */
    private int hashFun(String account){
        //取出首字母
        account=account.toLowerCase();
        return account.charAt(0)-'a';
    }
}