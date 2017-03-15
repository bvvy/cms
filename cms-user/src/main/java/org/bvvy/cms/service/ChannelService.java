package org.bvvy.cms.service;

import org.bvvy.basic.model.SystemContext;
import org.bvvy.cms.dao.IChannelDao;
import org.bvvy.cms.model.Channel;
import org.bvvy.cms.model.ChannelTree;
import org.bvvy.cms.model.CmsException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("channelService")
public class ChannelService implements IChannelService {
    private IChannelDao channelDao;

    public IChannelDao getChannelDao() {
        return channelDao;
    }

    @Resource
    public void setChannelDao(IChannelDao channelDao) {
        this.channelDao = channelDao;
    }

    @Override
    public void add(Channel channel, Integer pid) {
        Integer orders = channelDao.getMaxOrderByParent(pid);

        if (pid != null && pid > 0) {
            Channel pc = channelDao.load(pid);
            if (pc == null) throw new CmsException("要添加的父类对象不正确");
            channel.setParent(pc);
        }
        channel.setOrders(orders + 1);
        channelDao.add(channel);
    }

    @Override
    public void update(Channel channel, int pid) {
        channelDao.update(channel);
    }

    @Override
    public void delete(int id) {
        //判断是否有子栏目
        List<Channel> cs = channelDao.listByParent(id);
        if (cs != null && cs.size() > 0) {
            throw new CmsException("要删除的栏目还有子栏目，不能删除");
        }
        channelDao.delete(id);
    }

    @Override
    public Channel load(int id) {
        return channelDao.load(id);
    }

    @Override
    public List<Channel> listByParent(Integer pid) {
        String sort = SystemContext.getSort();
        if (sort == null || "".equals(sort.trim())) {
            SystemContext.setSort("orders");
            SystemContext.setOrder("asc");
        }
        return channelDao.listByParent(pid);
    }

    @Override
    public int getMaxOrderByParent(Integer pid) {
        return channelDao.getMaxOrderByParent(pid);
    }



    @Override
    public void clearTopic(int id) {

    }

    @Override
    public List<ChannelTree> generateTree() {
        return channelDao.generateTree();
    }

    @Override
    public List<ChannelTree> generateTreeByParent(Integer pid) {
        return channelDao.generateTreeByParent(pid);
    }
}
