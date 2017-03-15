package org.bvvy.cms.service;

import org.bvvy.basic.model.Pager;
import org.bvvy.cms.dao.IGroupDao;
import org.bvvy.cms.dao.IUserDao;
import org.bvvy.cms.dao.UserDao;
import org.bvvy.cms.model.CmsException;
import org.bvvy.cms.model.Group;
import org.bvvy.cms.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("groupService")
public class GroupService implements IGroupService{
    private IGroupDao groupDao;
    private IUserDao userDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    @Resource
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public IGroupDao getGroupDao() {
        return groupDao;
    }
    @Resource
    public void setGroupDao(IGroupDao groupDao) {
        this.groupDao = groupDao;
    }



    @Override
    public List<Group> listGroup() {
        return groupDao.listGroup();
    }

    @Override
    public Pager<Group> findGroup() {
        return groupDao.findGroup();
    }

    @Override
    public void add(Group group) {
        groupDao.add(group);
    }

    @Override
    public void update(Group group) {
        groupDao.update(group);
    }

    @Override
    public Group load(int id) {
        return groupDao.load(id);
    }

    @Override
    public void delete(int id) {

        List<User> u = userDao.listGroupUsers(id);
        if (u.size()>0) {
            throw new CmsException("要删除的组还有用户，请先清除组里面的用户");
        }
        groupDao.delete(id);
    }
}
