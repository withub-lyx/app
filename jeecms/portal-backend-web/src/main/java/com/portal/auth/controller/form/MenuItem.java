package com.portal.auth.controller.form;

import com.portal.auth.authdb.entity.Func;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuquan on 2016/12/27 15:11
 */
public class MenuItem implements Serializable{
    private String icon;

    private String name;

    private String link ;

    private boolean isSubMenu;

    private int level = 1;
    private List<MenuItem> items;


    public MenuItem() {
    }

    public MenuItem(Func func, Map<String, List<Func>> pidMap, int nowLevel) {
        this.setLevel(nowLevel);
        this.setIcon(func.getIcon());
        this.setName(func.getName());
        this.setLink(func.getUrl());
        this.setSubMenu(CollectionUtils.isNotEmpty(pidMap.get(func.getId())));
        if (this.getIsSubMenu()) {
            this.setItems(buildItems(pidMap.get(func.getId()),pidMap,nowLevel+1));
        }
    }
    public MenuItem(Func func, Map<String, List<Func>> pidMap) {
        this(func, pidMap, 1);
    }


    public static  List<MenuItem> buildItems( List<Func> items  , Map<String, List<Func>> pidMap,int nowLevel){

        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        if (CollectionUtils.isNotEmpty(items)) {
            for (Func func : items) {
                menuItems.add(new MenuItem(func,pidMap,nowLevel));
            }
        }
        return menuItems;
    }



    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean getIsSubMenu() {
        return isSubMenu;
    }

    public void setSubMenu(boolean subMenu) {
        isSubMenu = subMenu;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
