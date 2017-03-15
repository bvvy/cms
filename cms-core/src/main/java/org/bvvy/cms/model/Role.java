package org.bvvy.cms.model;

import javax.persistence.*;

/**
 * 角色对象，用来对应可以访问的功能。
 * 现在之定义了 管理员 。。。以后再考虑其他的
 * //todo
 */
@Entity
@Table(name="t_role")
public class Role {
    private int id;
    private String name;//名称
    private RoleType roleType;//角色的编号 todo 是否可以考录字典的方式

    public Role(int id, String name, RoleType roleType) {
        this.id = id;
        this.name = name;
        this.roleType = roleType;
    }

    public Role() {
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="role_type")
    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
