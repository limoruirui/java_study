package com.company.dao.domain;

public class Student {
    private Integer id;
    private String name;
    private Integer chinese;
    private Integer english;
    private Integer math;

    public Student() {
    }

    public Student(Integer id, String name, Integer chinese, Integer english, Integer math) {
        this.id = id;
        this.name = name;
        this.chinese = chinese;
        this.english = english;
        this.math = math;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getChinese() {
        return chinese;
    }

    public void setChinese(Integer chinese) {
        this.chinese = chinese;
    }

    public Integer getEnglish() {
        return english;
    }

    public void setEnglish(Integer english) {
        this.english = english;
    }

    public Integer getMath() {
        return math;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chinese=" + chinese +
                ", english=" + english +
                ", math=" + math +
                '}';
    }

    public void setMath(Integer math) {
        this.math = math;
    }
}
