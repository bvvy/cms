package org.bvvy.cms.dao;

import org.bvvy.basic.dao.IBasicDao;
import org.bvvy.cms.model.Channel;
import org.bvvy.cms.model.ChannelTree;

import java.util.List;

public interface IChannelDao extends IBasicDao<Channel>{

    public List<Channel> listByParent(Integer pid);

    /**
     * 最大的排序号
     * @param pid
     * @return
     */
    public int getMaxOrderByParent(Integer pid);


    /**
     * 生成一棵完整的树
     * @return
     */
    public List<ChannelTree> generateTree(Integer pid);


    public List<ChannelTree> generateTree();
    /**
     * 根据父对象生成子栏目的树
     * @return
     */
    public List<ChannelTree> generateTreeByParent(Integer pid);

}
