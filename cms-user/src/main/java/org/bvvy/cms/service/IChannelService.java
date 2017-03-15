package org.bvvy.cms.service;

import org.bvvy.cms.dao.IChannelDao;
import org.bvvy.cms.model.Channel;
import org.bvvy.cms.model.ChannelTree;

import java.util.List;

public interface IChannelService {


    void add(Channel channel, Integer pid);

    public void update(Channel channel,int pid);

    public void delete(int id);

    public Channel load(int id);

    /**
     * 根据父Id来加载栏目
     * @param pid
     * @return
     */
    public List<Channel> listByParent(Integer pid);

    /**
     * 得到最大的顺序号
     * @param pid
     * @return
     */
    public int getMaxOrderByParent(Integer pid);

    /**
     * 清空栏目的文章
     * @param id
     */
    public void clearTopic(int id);


    public List<ChannelTree> generateTree();

    public List<ChannelTree> generateTreeByParent(Integer pid);
}
