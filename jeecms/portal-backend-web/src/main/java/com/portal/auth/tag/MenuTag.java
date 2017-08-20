package com.portal.auth.tag;

import com.portal.auth.controller.form.MenuItem;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by liuquan on 2016/12/27 16:18
 */
public class MenuTag extends TagSupport {


    private List<MenuItem> menus;


    @Override
    public int doStartTag() throws JspException {

        StringBuffer result = new StringBuffer();
        JspWriter out = pageContext.getOut();

        for (MenuItem menuItem : menus) {
            result.append(buildItem(menuItem));
        }

        try {
            //System.out.println(result.toString());
            out.write(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return super.doStartTag();
    }

    public List<MenuItem> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuItem> menus) {
        this.menus = menus;
    }



    public String buildItem(MenuItem item){
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("<li %s>",(item.getLevel()>=2)?" style='    border-left: none' ":""));

        /**
         * 有子菜单
         */
        if (item.getIsSubMenu()) {
            sb.append(String.format(
                            "                    <a href='#'>\n" +
                                    "<i class='%s'></i>"+
                            "                        <span class='nav-label'>%s</span>\n" +
                            "                        <span class='fa arrow'></span>\n" +
                            "                    </a>",item.getIcon(),item.getName()));


            sb.append(String.format("<ul class='nav %s'>",getUlClass(item.getLevel()+1)));

            List<MenuItem> items = item.getItems();
            for (MenuItem temp : items) {
                sb.append(buildItem(temp));
            }
            sb.append("</ul>");
        }else{
            sb.append(String.format(

                            "                    <a class='J_menuItem' href='%s'>\n" +
                                    "<i class='%s'></i>"+
                            "                        <span class='nav-label'>%s</span>\n" +
                            "                    </a>\n"
                    ,item.getLink(),item.getIcon(),item.getName()));
        }
        sb.append("</li>");

        return sb.toString();
    }


    public String getUlClass(int level){
        switch (level) {
            case 1:{
                return " ";
            }
            case 2:{
                return " nav-second-level ";
            }
            case 3:{
                return " nav-third-level ";
            }
            case 4:{
                return " nav-fourth-level ";
            }
            case 5:{
                return " nav-fifth-level ";
            }
            default: return "";
        }


    }
}
