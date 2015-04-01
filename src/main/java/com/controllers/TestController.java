package com.controllers;

import com.ejb.TestEJB;
import com.jpa.entities.TestEntity;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарья on 29.03.2015.
 */

@Named(value = "testController")
@SessionScoped
@ManagedBean
public class TestController {
    @EJB
    private TestEJB testEJB;
    private TestEntity testEntity = new TestEntity();
    private List<TestEntity> testList = new ArrayList<TestEntity>();

    public void postCreate(){
        System.out.println("TestController crated");
    }

    public List<TestEntity> getTestList() {
        System.err.println("GetTestList start");
        testList = testEJB.findTestEntity();
        System.err.println("GetTestList list: "+testList);
        return testList;
    }

    public String viewEmployee(){
        return "employeeList.xhtml";
    }

    public String addNewTestEntity() {
        testEntity = testEJB.addNew(testEntity);
        testList = testEJB.findTestEntity();
        return "employeeList.xhtml";
    }
    public TestEJB getTestEJB() {
        return testEJB;
    }

    public void setTestEJB(TestEJB testEJB) {
        this.testEJB = testEJB;
    }
    public TestEntity getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;
    }
    public void setTestList(List<TestEntity> testList) {
        this.testList = testList;
    }

}