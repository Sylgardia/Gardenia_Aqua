package com.zy.service.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @auther zy-栀
 * @create 2023-02-44 11:47:56
 * @describe 用户表实体类
 */
@Data
@ToString
@TableName("sys_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysUser对象", description = "用户表")
public class SysUser implements Serializable{

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "登录账号")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "真实姓名")
    @TableField("realname")
    private String realname;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "md5密码盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "生日")
    @TableField("birthday")
    private Date birthday;

    @ApiModelProperty(value = "性别(0-默认未知,1-男,2-女)")
    @TableField("sex")
    private Boolean sex;

    @ApiModelProperty(value = "电子邮件")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "机构编码")
    @TableField("org_code")
    private String orgCode;

    @ApiModelProperty(value = "状态(1-正常,2-冻结)")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty(value = "删除状态(0-正常,1-已删除)")
    @TableField("del_flag")
    @TableLogic
    private Boolean delFlag;

    @ApiModelProperty(value = "第三方登录的唯一标识")
    @TableField("third_id")
    private String thirdId;

    @ApiModelProperty(value = "第三方类型")
    @TableField("third_type")
    private String thirdType;

    @ApiModelProperty(value = "同步工作流引擎(1-同步,0-不同步)")
    @TableField("activiti_sync")
    private Boolean activitiSync;

    @ApiModelProperty(value = "工号，唯一键")
    @TableField("work_no")
    private String workNo;

    @ApiModelProperty(value = "职务，关联职务表")
    @TableField("post")
    private String post;

    @ApiModelProperty(value = "座机号")
    @TableField("telephone")
    private String telephone;

    @ApiModelProperty(value = "创建人")
    @TableField("create_by")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新人")
    @TableField("update_by")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "身份（1普通成员 2上级）")
    @TableField("user_identity")
    private Boolean userIdentity;

    @ApiModelProperty(value = "负责部门")
    @TableField("depart_ids")
    private String departIds;

    @ApiModelProperty(value = "设备ID")
    @TableField("client_id")
    private String clientId;

    @ApiModelProperty(value = "是否运营用户")
    @TableField("is_admin")
    private Boolean isAdmin;

    @ApiModelProperty(value = "支付密码")
    @TableField("pay_passwd")
    private String payPasswd;

    @ApiModelProperty(value = "支付密码盐值")
    @TableField("pay_salt")
    private String paySalt;

    @ApiModelProperty(value = "无密码支付")
    @TableField("pay_ignore_pwd")
    private Boolean payIgnorePwd;


}
