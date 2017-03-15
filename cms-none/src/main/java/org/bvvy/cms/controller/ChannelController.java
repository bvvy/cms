package org.bvvy.cms.controller;

import org.bvvy.basic.util.JsonUtil;
import org.bvvy.cms.model.Channel;
import org.bvvy.cms.model.ChannelTree;
import org.bvvy.cms.service.IChannelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/admin/channel")
@Controller
public class ChannelController {

    private IChannelService channelService;

    public IChannelService getChannelService() {
        return channelService;
    }

    @Resource
    public void setChannelService(IChannelService channelService) {
        this.channelService = channelService;
    }

    @RequestMapping("/channels")
    public String list(Model model) {
        return "channel/list_treeALl";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Channel());
        return "channel/add";
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Integer pid,@Valid Channel channel,BindingResult br) {
        if (br.hasErrors()) {
            return "channel/add";
        }
        channelService.add(channel, pid);
        return "redirect:/admin/channel/list";
    }

    @RequestMapping(value = "/treeAll" , method = RequestMethod.GET)
    public @ResponseBody List<ChannelTree> treeAll() {
        return channelService.generateTree();
    }
}
