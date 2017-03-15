package org.bvvy.cms.controller;

import org.bvvy.cms.model.Group;
import org.bvvy.cms.service.GroupService;
import org.bvvy.cms.service.IGroupService;
import org.bvvy.cms.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/group")
public class GroupController {
    private IGroupService groupService;
    private IUserService userService;

    public IUserService getUserService() {
        return userService;
    }

    @Resource
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public IGroupService getGroupService() {
        return groupService;
    }

    @Resource
    public void setGroupService(IGroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping("/groups")
    public String list(Model model) {
        model.addAttribute("datas", groupService.findGroup());
        return "group/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Group());
        return "group/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@Valid Group group,BindingResult br) {
        if (br.hasErrors()) {
            return "group/add";
        }
        groupService.add(group);
        return "redirect:/admin/group/groups";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model,@PathVariable int id) {
        model.addAttribute(groupService.load(id));
        return "group/update";
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id,@Valid Group group,BindingResult br) {
        if (br.hasErrors()) {
            return "group/update";
        }
        Group g = groupService.load(id);
        g.setName(group.getName());
        g.setRemarks(group.getRemarks());
        groupService.update(g);
        return "redirect:/admin/group/groups";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        groupService.delete(id);
        return "redirect:/admin/group/groups";
    }

    @RequestMapping("/{id}")
    public String show(Model model,@PathVariable int id) {
        model.addAttribute("users", userService.listGroupUsers(id));
        model.addAttribute("group", groupService.load(id));
        return "group/show";
    }


    @RequestMapping("/clearUsers/{id}")
    public String clearUsers(@PathVariable int id) {
        userService.deleteGroupUsers(id);
        return "redirect:/admin/group/groups";
    }
}
