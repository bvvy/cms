package org.bvvy.cms.dao;

import org.bvvy.basic.dao.BaseDao;
import org.bvvy.basic.model.Pager;
import org.bvvy.cms.model.Group;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("groupDao")
public class GroupDao extends BaseDao<Group>  implements IGroupDao {
    @Override
    public List<Group> listGroup() {
        return this.list("from Group");
    }

    @Override
    public Pager<Group> findGroup() {
        return find("from Group");
    }


}
