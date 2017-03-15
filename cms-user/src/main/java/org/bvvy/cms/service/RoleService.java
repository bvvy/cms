package org.bvvy.cms.service;

import org.bvvy.basic.model.Pager;
import org.bvvy.cms.dao.IRoleDao;
import org.bvvy.cms.dao.IUserDao;
import org.bvvy.cms.model.CmsException;
import org.bvvy.cms.model.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("roleService")
public class RoleService implements IRoleService {
    private IRoleDao roleDao;

    private IUserDao userDao;

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

    @Override
    public List<Role> listRole() {
        return roleDao.listRole();
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void delete(int id) {
        if (userDao.listRoleUsers(id).size() > 0) {
            throw new CmsException("要删除的角色中还有用户，不能删除！");
        }
        roleDao.delete(id);
    }

    @Override
    public Role load(int id) {
        return roleDao.load(id);
    }

    @Override
    public Pager<Role> findRole() {
        return roleDao.findRole();
    }
}
