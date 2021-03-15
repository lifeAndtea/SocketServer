package com.fqy.qzdtest.proxytest;


import java.util.Objects;
import java.util.Random;

public class Leader implements IWork,Cloneable {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Leader(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void meeting() {
        System.out.println("领导早上要组织会议");
    }

    public Leader() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Leader{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int evaluate(String name) {
        int score = new Random(System.currentTimeMillis()).nextInt(20) + 80;
        System.out.println(String.format("领导给%s的考评为%s分", name, score));
        return score;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Leader leader = new Leader("one", 10);
        Leader clone = (Leader)leader.clone();
        clone.setName("second");
        clone.setAge(11);
        System.out.println(leader.equals(clone));
        System.out.println("first:"+leader.toString());
        System.out.println("second:"+clone.toString());
        Leader leader1 = Objects.requireNonNull(clone);
        System.out.println(leader1.toString());
    }
}