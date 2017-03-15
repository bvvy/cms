package org.bvvy.cms.service;

import org.bvvy.basic.model.Pager;
import org.bvvy.cms.model.Group;

import java.util.List;

public interface IGroupService {
    public List<Group> listGroup();

    public Pager<Group> findGroup();

    public void add(Group group);

    public void update(Group group);

    public Group load(int id);

    public void delete(int id);

}
