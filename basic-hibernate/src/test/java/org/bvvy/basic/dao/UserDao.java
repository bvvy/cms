package org.bvvy.basic.dao;

import org.bvvy.basic.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {
}
