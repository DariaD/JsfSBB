package com.jpa.entities;

/**
 * Created by Дарья on 29.03.2015.
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "testTable")
@NamedQueries({
        @NamedQuery(name = "TestEntity.getAll", query = "SELECT c from TestEntity c"),
        @NamedQuery(name = "TestEntity.findByName", query="SELECT c FROM TestEntity c WHERE c.name = :name")
})

public class TestEntity implements Serializable {
    private static final long serialVersionUID = 555;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTestTable;

    @Column(name = "Name", length = 45)
    private String name;

    @Column(name = "Age")
    private int age;


    public TestEntity(String name) {
        this.name = name;
    }

    public TestEntity() {}

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
}

