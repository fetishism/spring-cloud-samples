package com.ascrud.cloud.samples.user.server.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 角色表
 *
 * @author walkman
 */
@Data
@Entity
@ToString
public class Role implements Serializable {
    private static final long serialVersionUID = -7307472888363829692L;

    /**
     * 角色Id
     */
    @Id
    private String roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 删除标识
     */
    private char delFlag;
}
