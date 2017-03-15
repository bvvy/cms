package org.bvvy.cms.dao;

import org.bvvy.basic.dao.IBasicDao;
import org.bvvy.basic.model.Pager;
import org.bvvy.cms.model.*;

import java.util.List;

public interface IUserDao extends IBasicDao<User> {

    List<Role> listUserRoles(int userId);

    /**
     * 用户的角色id
     *
     * @param userId
     * @return
     */
    List<Integer> listUserRoleIds(int userId);

    /**
     * 组
     *
     * @param userId
     * @return
     */
    List<Group> listUserGroups(int userId);

    /**
     * 组id
     *
     * @param userId
     * @return
     */
    List<Integer> listUserGroupIds(int userId);

    /**
     * 用户角色对象
     *
     * @param userId
     * @param roleId
     * @return
     */
    public UserRole loadUserRole(int userId, int roleId);

    /**
     * @param userId
     * @param groupId
     * @return
     */
    public UserGroup loadUserGroup(int userId, int groupId);

    public User loadByUsername(String username);

    /**
     * 角色id得到user
     *
     * @param roleId
     * @return
     */
    List<User> listRoleUsers(int roleId);

    /**
     * 角色类型获得用户对象
     *
     * @param roleType
     * @return
     */
    List<User> listRoleUsers(RoleType roleType);

    /**
     * 组获得用户
     *
     * @param groupId
     * @return
     */
    List<User> listGroupUsers(int groupId);

    /**
     * 添加用户角色
     *
     * @param user
     * @param role
     */
    public void addUserRole(User user, Role role);


    /**
     * 添加用户组
     *
     * @param user
     * @param group
     */
    void addUserGroup(User user, Group group);

    /**
     * 删除用户角色
     */
    public void deleteUserRoles(int userId);

    void deleteUserGroups(int userId);

    Pager<User> findUser();

    public void deleteUserRole(int userId, int roleId);

    public void deleteUserGroup(int userId, int groupId);

    void deleteGroupUsers(int groupId);

    void deleteRoleUsers(int roleId);
}
