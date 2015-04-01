package com.beans;

/**
 * Created by Дарья on 29.03.2015.
 */

import com.jpa.entities.TestEntity;
import com.jpa.servises.TestServises;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ManagedBean
@SessionScoped
public class TestBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;


    public String validatePage(){
        Pattern pName = Pattern.compile("^[A-Za-z]{1,20}$");
        Matcher m1 = pName.matcher(name);
        boolean m2 = age >= 0 && age < 130;
        if( m1.matches()&& m2) {
            return "success";
        }else {
            return "fail";
        }
    }

    public String getName() {
        TestServises ts = new TestServises();
        List<TestEntity> list = ts.getAll();
        TestEntity te =  list.get(0);
        name = te.getName();
        if(name.equals(null)){
            name = "Can't get name";
        }
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
}
