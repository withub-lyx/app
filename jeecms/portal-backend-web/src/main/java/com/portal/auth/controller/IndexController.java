package com.portal.auth.controller;

import com.alibaba.fastjson.JSON;
import com.portal.auth.anno.CurrentUser;
import com.portal.auth.authdb.entity.Func;
import com.portal.auth.authdb.entity.Users;
import com.portal.auth.authdb.service.IFuncService;
import com.portal.auth.controller.form.MenuItem;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.omg.CORBA.SetOverrideType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by liuquan on 2016/12/9 10:51
 */
@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private IFuncService funcService;

/*    @Value("${ssoServerHost}")
    private String ssoServerHost;*/

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index(Model model , @CurrentUser  Users user){
        //System.out.println("当前登录用户"+SecurityUtils.getSubject().getPrincipal());

        List<Func> funcs = funcService.getFuncByUser();
        //System.out.println(JSON.toJSON(funcs));
        //System.out.println(SecurityUtils.getSubject().getPrincipal());

        List<MenuItem> menus = new ArrayList<MenuItem>();

        Map<String, List<Func>> pidMap = new HashMap<String, List<Func>>();
        if (CollectionUtils.isNotEmpty(funcs)) {

            for (Func item : funcs) {
                List<Func> menuItems = pidMap.get(item.getParid());
                if (menuItems == null) {
                    menuItems = new ArrayList<Func>();
                }
                menuItems.add(item);
                pidMap.put(item.getParid(), menuItems);
            }
        }


        for (Map.Entry<String, List<Func>> entry : pidMap.entrySet()) {
            Collections.sort(entry.getValue(), new Comparator<Func>() {
                @Override
                public int compare(Func o1, Func o2) {
                    return o1.getOrdernum().compareTo(o2.getOrdernum());
                }
                @Override
                public boolean equals(Object obj) {
                    return false;
                }
            });
            pidMap.put(entry.getKey(),entry.getValue());
        }


        List<Func> root = pidMap.get("ROOT");
        if (CollectionUtils.isNotEmpty(root)) {
            for (Func func : root) {
                menus.add(new MenuItem(func, Collections.unmodifiableMap(pidMap)));
            }
        }


        model.addAttribute("menus", menus);


        model.addAttribute("user", user);
//        model.addAttribute("serverHost", ssoServerHost);
        return "/page/index";
    }
}
