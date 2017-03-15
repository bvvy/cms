package org.bvvy.cms.model;

import javax.persistence.*;

@Entity(name="t_channel")
public class Channel {
    private int id;
    private String name;
    private int customLink;
    private String customLinkUrl;
    private ChannelType channelType;
    private int isIndex;
    private int isTopNav;
    private int recommend;
    private int status;
    private int orders;
    private Channel parent;



    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="custom_link")
    public int getCustomLink() {
        return customLink;
    }

    public void setCustomLink(int customLink) {
        this.customLink = customLink;
    }

    @Column(name="custom_link_url")
    public String getCustomLinkUrl() {
        return customLinkUrl;
    }

    public void setCustomLinkUrl(String customLinkUrl) {
        this.customLinkUrl = customLinkUrl;
    }

    @Column(name="channel_type")
    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }

    @Column(name="is_index")
    public int getIsIndex() {
        return isIndex;
    }

    public void setIsIndex(int isIndex) {
        this.isIndex = isIndex;
    }

    @Column(name="is_top_nav")
    public int getIsTopNav() {
        return isTopNav;
    }

    public void setIsTopNav(int isTopNav) {
        this.isTopNav = isTopNav;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    @ManyToOne
    @JoinColumn(name="pid")
    public Channel getParent() {
        return parent;
    }

    public void setParent(Channel parent) {
        this.parent = parent;
    }
}
