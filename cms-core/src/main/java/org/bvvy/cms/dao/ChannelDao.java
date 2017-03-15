package org.bvvy.cms.dao;

import org.bvvy.basic.dao.BaseDao;
import org.bvvy.cms.model.Channel;
import org.bvvy.cms.model.ChannelTree;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("channelDao")
public class ChannelDao extends BaseDao<Channel> implements IChannelDao {
    @Override
    public List<Channel> listByParent(Integer pid) {
        String hql = "select c from Channel c left join c.parent cp where cp.id="+pid;
        if(pid==null||pid==0) hql = "select c from Channel c where c.parent is null";
        return this.list(hql);
    }

    @Override
    public int getMaxOrderByParent(Integer pid) {

        String hql = "select max(c.orders) from Channel c left join c.parent cp where cp.id="+pid;
        if(pid==null) hql = "select max(c.orders) from Channel c where c.parent is null";
        Object obj = this.queryObject(hql);
        if (obj == null)  return 0;
        return (Integer)obj;
    }

    @Override
    public List<ChannelTree> generateTree(Integer pid) {
        return null;
    }

    @Override
    public List<ChannelTree> generateTree() {
        String sql = "select id,name,pid from t_channel";
        List<ChannelTree> cts = this.listBySql(sql, ChannelTree.class, false);
        cts.add(0, new ChannelTree(0, "网站系统栏目", -1));
        for (ChannelTree ct : cts) {
            if (ct.getPid() == null) ct.setPid(0);
        }
        return cts;
    }

    @Override
    public List<ChannelTree> generateTreeByParent(Integer pid) {
        if (pid == null || pid == 0) {
            return this.listBySql("select id ,name,pid from t_channel where pid is null", ChannelTree.class, false);
        } else {
            return this.listBySql("select id,name,pid from t_channel where pid=" + pid, ChannelTree.class, false);
        }
    }
}
