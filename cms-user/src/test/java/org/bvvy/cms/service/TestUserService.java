package org.bvvy.cms.service;

import org.junit.Assert;
import org.bvvy.cms.dao.IGroupDao;
import org.bvvy.cms.dao.IRoleDao;
import org.bvvy.cms.dao.IUserDao;

import static org.easymock.EasyMock.*;

import org.bvvy.cms.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testbeans.xml")
public class TestUserService {
    @Resource
    private IUserService userService;
    @Resource
    private IRoleDao roleDao;
    @Resource
    private IGroupDao groupDao;
    @Resource
    private IUserDao userDao;

    private User baseUser = new User(2,"222","222","222","333","2222",0);

    @Test
    public void testDelete() {
        reset(userDao);
        int uid = 2;
        userDao.deleteUserGroups(uid);
        expectLastCall();
        userDao.deleteUserRoles(uid);
        expectLastCall();
        userDao.delete(uid);
        expectLastCall();
        replay(userDao);
        userService.delete(uid);
        verify(userDao);
    }

    @Test
    public void testUpdateStatus() {
        reset(userDao);
        int uid = 2;
        expect(userDao.load(uid)).andReturn(baseUser);
        userDao.update(baseUser);
        expectLastCall();
        replay(userDao);
        userService.updateStatus(uid);
        Assert.assertEquals(baseUser.getStatus(), 1);
        verify(userDao);

    }
}
