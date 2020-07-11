package com.ascrud.cloud.samples.user.common.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 用户实体类
 *
 * @author walkman
 */
public class UserInput {

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 6, message = "用户名长度不能小于6")
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码长度不能小于6")
    private String password;

    /**
     * 用户描述
     */
    private String userDesc;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }
}
