package main.entity;

import java.io.Serializable;

public class  Person implements Serializable {
    public String account;
    public String password;
    public Person next;
    public Person(String account, String password) {
        this.account = account;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}