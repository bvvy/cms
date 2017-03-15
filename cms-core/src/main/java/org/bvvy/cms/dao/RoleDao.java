package org.bvvy.cms.dao;

import org.bvvy.basic.dao.BaseDao;
import org.bvvy.basic.model.Pager;
import org.bvvy.cms.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {
    public List<Role> listRole() {
        return this.list("from Role");
    }

    @Override
    public Pager<Role> findRole() {
        return this.find("from Role");
    }
}
