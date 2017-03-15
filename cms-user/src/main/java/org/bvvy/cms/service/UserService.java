package org.bvvy.cms.service;

import org.bvvy.basic.model.Pager;
import org.bvvy.cms.dao.IGroupDao;
import org.bvvy.cms.dao.IRoleDao;
import org.bvvy.cms.dao.IUserDao;
import org.bvvy.cms.model.CmsException;
import org.bvvy.cms.model.Group;
import org.bvvy.cms.model.Role;
import org.bvvy.cms.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("userService")
public class UserService implements IUserService {
    private IUserDao userDao;
    private IRoleDao roleDao;
    private IGroupDao groupDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    @Resource
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public IRoleDao getRoleDao() {
        return roleDao;
    }

    @Resource
    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public IGroupDao getGroupDao() {
        return groupDao;
    }

    @Resource
    public void setGroupDao(IGroupDao groupDao) {
        this.groupDao = groupDao;
    }

    private void addUserRole(User user, int rid) {
        Role role = roleDao.load(rid);
        if (role == null) throw new CmsException("要添加的角色不存在");
        userDao.addUserRole(user, role);
    }

    private void addUserGroup(User user, int gid) {
        Group group = groupDao.load(gid);
        if (group == null) throw new CmsException("要添加的组不存在");
        userDao.addUserGroup(user, group);
    }

    @Override
    public void add(User user, Integer[] roleIds, Integer[] groupIds) {
        User tu = userDao.loadByUsername(user.getUsername());
        if (tu != null) throw new CmsException("要添加的用户已经存在");
        user.setCreateDate(new Date());
        userDao.add(user);
        //添加角色对象
        for (Integer rId : roleIds) {
            this.addUserRole(user, rId);
        }
        //添加组对象
        for (Integer gId : groupIds) {
            this.addUserGroup(user, gId);
        }
    }

    @Override
    public void delete(int id) {

        //todo 是否删除文章
        userDao.deleteUserGroups(id);
        userDao.deleteUserRoles(id);
        userDao.delete(id);
    }

    @Override
    public void update(User user, Integer[] roleIds, Integer[] groupIds) {
        List<Integer> rIds  = Arrays.asList(roleIds);
        List<Integer> gIds = Arrays.asList(groupIds);
        List<Integer> erIds = userDao.listUserRoleIds(user.getId());
        List<Integer> egIds = userDao.listUserGroupIds(user.getId());
        for (Integer rid : roleIds) {
            if (!erIds.contains(rid)) {
                this.addUserRole(user, rid);
            }
        }
        for (Integer erId : erIds) {
            if (!rIds.contains(erId)) {
                userDao.deleteUserRole(user.getId(), erId);
            }
        }

        for (Integer gid : groupIds) {
            if (!egIds.contains(gid)) {
                this.addUserGroup(user, gid);
            }
        }
        for (Integer egId : egIds) {
            if (!gIds.contains(egId)) {
                userDao.deleteUserGroup(user.getId(), egId);
            }
        }
    }

    @Override
    public void updateStatus(int id) {

        User u = userDao.load(id);
        if (u == null) throw new CmsException("修改状态的用户不存在");
        if (u.getStatus() == 0) u.setStatus(1);
        else u.setStatus(0);
        userDao.update(u);
    }

    @Override
    public Pager<User> findUser() {
        return userDao.findUser();
    }

    @Override
    public User load(int id) {
        return userDao.load(id);
    }

    @Override
    public List<Role> listUserRoles(int id) {
        return userDao.listUserRoles(id);
    }

    @Override
    public List<Group> listUserGroups(int id) {
        return userDao.listUserGroups(id);
    }

    @Override
    public List<User> listGroupUsers(int groupId) {
        return userDao.listGroupUsers(groupId);
    }

    @Override
    public List<User> listRoleUsers(int roleId) {
        return userDao.listRoleUsers(roleId);
    }

    @Override
    public void deleteGroupUsers(int groupId) {
        userDao.deleteGroupUsers(groupId);
    }

    @Override
    public void deleteRoleUsers(int roleId) {
        userDao.deleteRoleUsers(roleId);
    }
}
