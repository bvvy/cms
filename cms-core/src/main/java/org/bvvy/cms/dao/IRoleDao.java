package org.bvvy.cms.dao;

import org.bvvy.basic.dao.IBasicDao;
import org.bvvy.basic.model.Pager;
import org.bvvy.cms.model.Role;

import java.util.List;

public interface IRoleDao extends IBasicDao<Role> {
    public List<Role> listRole();

    public Pager<Role> findRole();
}
