package org.bvvy.cms.controller;

import org.bvvy.cms.model.Role;
import org.bvvy.cms.service.IRoleService;
import org.bvvy.cms.service.IUserService;
import org.bvvy.cms.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/role")
public class RoleController {
    private IRoleService roleService;
    private IUserService userService;

    public IRoleService getRoleService() {
        return roleService;
    }

    @Resource
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    public IUserService getUserService() {
        return userService;
    }

    @Resource
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/roles")
    public String list(Model model) {
        model.addAttribute("datas", roleService.findRole());
        return "role/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
      public String add(Model model) {
        model.addAttribute(new Role());
        return "role/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@Valid Role role,BindingResult br) {
        if (br.hasErrors()) {
            return "role/add";
        }
        roleService.add(role);
        return "redirect:/admin/role/roles";
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable int id,Model model) {
        model.addAttribute(roleService.load(id));
        return "role/update";
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public String update(@PathVariable int id,@Valid Role role,BindingResult br) {
        if (br.hasErrors()) {
            return "role/update";
        }
        Role r = roleService.load(id);
        r.setName(role.getName());
        r.setRoleType(role.getRoleType());
        roleService.update(r);
        return "redirect:/admin/role/roles";
    }
    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id) {
        roleService.delete(id);
        return "redirect:/admin/role/roles";
    }

    @RequestMapping(value = "{id}")
    public String show(Model model,@PathVariable int id) {
        model.addAttribute(roleService.load(id));
        model.addAttribute("users",userService.listRoleUsers(id));
        return "role/show";
    }

    @RequestMapping("/clearUsers/{id}")
    public String  clearUsers(@PathVariable int id) {
        userService.deleteRoleUsers(id);
        return "redirect:/admin/role/roles";
    }
}
