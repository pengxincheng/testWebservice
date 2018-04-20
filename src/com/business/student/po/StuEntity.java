package com.business.student.po;

import com.business.classes.po.ClassesEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pxc on 2017/8/21.
 */
@Entity
@Table(name = "tab_stu")
public class StuEntity implements Serializable {

    private String id;
    private String name;
    private Integer age;
    private String classesId;
    private ClassesEntity classesEntity;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "stuGenerator")
    @GenericGenerator(name = "stuGenerator", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "classes_id",nullable = false, insertable = false, updatable = false)
    public ClassesEntity getClassesEntity() {
        return classesEntity;
    }

    public void setClassesEntity(ClassesEntity classesEntity) {
        this.classesEntity = classesEntity;
    }

    @Column(name = "classes_id")
    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId;
    }
}
