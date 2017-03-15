package org.bvvy.cms.dao;

import org.bvvy.basic.dao.IBasicDao;
import org.bvvy.basic.model.Pager;
import org.bvvy.cms.model.Group;

import java.util.List;

public interface IGroupDao extends IBasicDao<Group> {
    public List<Group> listGroup();

    public Pager<Group> findGroup();
}
