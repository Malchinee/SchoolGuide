package main.dao;

import main.entity.Person;

import java.io.Serializable;

public class PerLinkedList implements Serializable {
    private Person head;
    public void list(int no){
        if(head == null){
            System.out.println("第"+(no+1)+"条链表为空");
            return;
        }
        Person temp = head;
        System.out.println("第"+(no+1)+"条链表的信息为===》");
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println();
    }

    public void add(Person per){
        if(head == null) {
            head = per;
            return;
        }else{
            Person temp = head;
            while(temp.next != null) temp = temp.next;
            temp.next = per;
        }
    }
    public Person findPerById(String account){
        if(head == null){
            System.out.println("链表为空");
            return null;
        }
        Person temp = head;
        boolean flag = false;
        while(temp != null){
            if(temp.account == account){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) return temp;
        else return null;
    }

}