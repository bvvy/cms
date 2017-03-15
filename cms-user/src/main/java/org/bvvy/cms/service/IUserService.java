package org.bvvy.cms.service;

import org.bvvy.basic.model.Pager;
import org.bvvy.cms.model.Group;
import org.bvvy.cms.model.Role;
import org.bvvy.cms.model.User;

import java.util.List;

public interface IUserService {
    /**
     * 添加用户  判断用户是否存在
     * @param user
     * @param roleIds
     * @param groupIds
     */
    void add(User user, Integer[] roleIds, Integer[] groupIds);

    void delete(int id);

    /**
     * 如果roleIds groupIds 已经存在，不操作
     * 如果不存在  就添加
     * 如果不存在 就删除
     *
     * @param user
     * @param roleIds
     * @param groupIds
     */
    void update(User user, Integer[] roleIds, Integer[] groupIds);


    public void updateStatus(int id);

    public Pager<User> findUser();

    public User load(int id);

    /**
     * 获取用户的角色
     * @param id
     * @return
     */
    public List<Role> listUserRoles(int id);

    /**
     * 获取用户的组
     * @param id
     * @return
     */
    public List<Group> listUserGroups(int id);


    public List<User> listGroupUsers(int groupId);

    public List<User> listRoleUsers(int roleId);


    public void deleteGroupUsers(int groupId);

    public void deleteRoleUsers(int roleId);

}
