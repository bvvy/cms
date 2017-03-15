package org.bvvy.cms.controller;

import org.bvvy.cms.model.User;
import org.bvvy.cms.service.IGroupService;
import org.bvvy.cms.service.IRoleService;
import org.bvvy.cms.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private IUserService userService;
    private IRoleService roleService;
    private IGroupService groupService;

    public IUserService getUserService() {
        return userService;
    }

    @Resource
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public IRoleService getRoleService() {
        return roleService;
    }

    @Resource
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    public IGroupService getGroupService() {
        return groupService;
    }

    @Resource
    public void setGroupService(IGroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping("/users")
    public String list(Model model) {
        model.addAttribute("datas", userService.findUser());
        return "user/list";
    }

    private void form(Model model) {
        model.addAttribute("roles", roleService.listRole());
        model.addAttribute("groups", groupService.listGroup());
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new User());
        form(model);
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, Integer[] groupIds, Integer[] roleIds, @Valid User user, BindingResult br) {
        if (br.hasErrors()) {
            form(model);
            return "user/add";
        }
        userService.add(user, roleIds, groupIds);
        return "redirect:/admin/user/users";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable int id) {

        model.addAttribute(userService.load(id));
        model.addAttribute("userGroups",userService.listUserGroups(id));
        model.addAttribute("userRoles", userService.listUserRoles(id));
        form(model);
        return "user/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(Model model, @PathVariable int id, Integer[] groupIds, Integer[] roleIds,@Valid User user,BindingResult br) {

        if (br.hasErrors()) {
            model.addAttribute("userGroups",userService.listUserGroups(id));
            model.addAttribute("userRoles", userService.listUserRoles(id));
            form(model);
            return "user/update";
        }
        User tu = userService.load(id);
        tu.setStatus(user.getStatus());
        tu.setNickname(user.getNickname());
        tu.setEmail(user.getEmail());
        tu.setPhone(user.getPhone());
        userService.update(tu, roleIds, groupIds);
        return "redirect:/admin/user/users";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/admin/user/users";
    }

    @RequestMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable int id) {
        userService.updateStatus(id);
        return "redirect:/admin/user/users";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable int id,Model model) {
        model.addAttribute("user", userService.load(id));
        model.addAttribute("groups", userService.listUserGroups(id));
        model.addAttribute("roles", userService.listUserRoles(id));
        return "user/show";
    }
}
