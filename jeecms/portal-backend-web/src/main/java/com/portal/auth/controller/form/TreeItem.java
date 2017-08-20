package com.portal.auth.controller.form;

import com.portal.auth.authdb.entity.Func;
import com.portal.auth.authdb.entity.Operbutton;

/**
 * Created by liuquan on 2016/12/19 18:13
 */
public class TreeItem {
    private String text;
    private Object children;

    private String type;

    private String id;

    private String icon;

    private TreeItemState state;



    public TreeItem() {
    }

    public TreeItem(Func temp, Object children) {
        this.setText(temp.getName());
        this.setChildren(children);
//        this.setType("default");
        this.setId(temp.getId());
        this.setIcon(temp.getIcon());
    }

    public TreeItem(Func temp,String id, Object children) {
        this.setText(temp.getName());
        this.setChildren(children);
//        this.setType("default");
        this.setId(id);
        this.setIcon(temp.getIcon());
    }


    public TreeItem(Func temp,Object children,boolean selected,boolean opened) {
        this.setText(temp.getName()+"["+temp.getCode()+"]");
        this.setChildren(children);
//        this.setType("default");
        this.setId(temp.getId());
        this.setIcon(temp.getIcon());

        this.state = new TreeItemState();
        this.state.setOpened(opened);
        this.state.setSelected(selected);

    }

    public TreeItem(Operbutton tempBtn,boolean btnselected) {
        this.setId(String.format("%s_%s",tempBtn.getFunc(),tempBtn.getButtonCode()));
        this.setText(tempBtn.getName()+"["+tempBtn.getButtonCode()+"]");
        this.setChildren(false);
//        this.setType("default");
        this.setIcon("fa fa-hand-o-up");

        this.state = new TreeItemState();
        this.state.setOpened(true);
        this.state.setSelected(btnselected);
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
       public Object getChildren() {
        return children;
    }

    public void setChildren(Object children) {
        this.children = children;
    }

        public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TreeItemState getState() {
        return state;
    }

    public void setState(TreeItemState state) {
        this.state = state;
    }
}
