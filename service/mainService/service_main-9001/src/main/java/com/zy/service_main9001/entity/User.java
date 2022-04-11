package com.zy.service_main9001.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author zy-栀
 * @since 2022-04-11
 */
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("age")
    private Integer age;

    @TableField("all_kytime")
    private String allKytime;

    @TableField("all_time")
    private String allTime;

    @TableField("cur_num")
    private Integer curNum;

    @TableField("has_words")
    private String hasWords;

    @TableField("name")
    private String name;

    @TableField("p_num")
    private Integer pNum;

    @TableField("pwd")
    private String pwd;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAllKytime() {
        return allKytime;
    }

    public void setAllKytime(String allKytime) {
        this.allKytime = allKytime;
    }

    public String getAllTime() {
        return allTime;
    }

    public void setAllTime(String allTime) {
        this.allTime = allTime;
    }

    public Integer getCurNum() {
        return curNum;
    }

    public void setCurNum(Integer curNum) {
        this.curNum = curNum;
    }

    public String getHasWords() {
        return hasWords;
    }

    public void setHasWords(String hasWords) {
        this.hasWords = hasWords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getpNum() {
        return pNum;
    }

    public void setpNum(Integer pNum) {
        this.pNum = pNum;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", age=" + age +
        ", allKytime=" + allKytime +
        ", allTime=" + allTime +
        ", curNum=" + curNum +
        ", hasWords=" + hasWords +
        ", name=" + name +
        ", pNum=" + pNum +
        ", pwd=" + pwd +
        "}";
    }
}
