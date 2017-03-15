package org.bvvy.cms.service;

import org.bvvy.basic.model.Pager;
import org.bvvy.cms.model.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> listRole();

    public void add(Role role);

    public void update(Role role);


    public void delete(int id);

    public Role load(int id);

    public Pager<Role> findRole();
}
