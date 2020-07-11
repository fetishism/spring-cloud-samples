package com.ascrud.cloud.samples.user.server.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author walkman
 */
@Data
@ToString
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = -6919496576458903349L;

    /**
     * 用户ID
     */
    @Id
    private String userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户描述
     */
    private String userDesc;

    /**
     * 删除标识
     */
    private char delFlag;

}
