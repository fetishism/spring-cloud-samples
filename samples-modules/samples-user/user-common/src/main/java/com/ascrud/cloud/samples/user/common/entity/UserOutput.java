package com.ascrud.cloud.samples.user.common.entity;

/**
 * 用户实体类
 *
 * @author walkman
 */
public class UserOutput {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户描述
     */
    private String userDesc;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    @Override
    public String toString() {
        return "UserOutput{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", userDesc='" + userDesc + '\'' +
                '}';
    }
}
