package com.bici.tsdb.common.entity;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Student
 * @author: bici
 * @date: 2018/4/13 18:06
 * @version: 1.0
 */
@Measurement(name = "student", timeUnit = TimeUnit.NANOSECONDS)
public class Student implements Serializable {
    @Column(name = "time")
    private Instant time;
    @Column(name = "class", tag = true)
    private String clas;
    @Column(name = "sex", tag = true)
    private String sex;
    @Column(name = "age")
    private Integer age;
    @Column(name = "name")
    private String name;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Student student = (Student) o;
        return Objects.equals(time, student.time) && Objects.equals(clas, student.clas) && Objects.equals(sex, student.sex) && Objects.equals(age, student.age) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(time, clas, sex, age, name);
    }

    @Override
    public String toString() {
        return "Student:{" + "time:'" + time + '\'' + ", clas:'" + clas + '\'' + ", sex:'" + sex + '\'' + ", age:" + age + ", name:'" + name + '\'' + '}';
    }
}
