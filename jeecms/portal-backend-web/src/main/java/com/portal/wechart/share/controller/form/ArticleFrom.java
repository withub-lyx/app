package com.portal.wechart.share.controller.form;

import com.portal.auth.authdb.entity.Artict;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

/**
 * Created by liuquan on 2017/1/7.
 */
public class ArticleFrom {
    private Artict artict;

    public ArticleFrom() {
    }

    public ArticleFrom(Artict artict) {
        this.artict = artict;
    }

    public Artict getArtict() {
        return artict;
    }

    public void setArtict(Artict artict) {
        this.artict = artict;
    }


    public void setJumpUrl(String jumpUrl) {
        artict.setJumpUrl(jumpUrl);
    }

    public String getId() {
        return artict.getId();
    }

    public void setId(String id) {
        artict.setId(id);
    }

    public String getTitle() {
        return artict.getTitle();
    }

    public void setTitle(String title) {
        artict.setTitle(title);
    }

    public String getContent() {
        return artict.getContent();
    }

    public void setContent(String content) {
        artict.setContent(content);
    }

    public Integer getShowFront() {
        return artict.getShowFront();
    }

    public void setShowFront(Integer showFront) {
        artict.setShowFront(showFront);
    }

    public Date getCreateDate() {
        return artict.getCreateDate();
    }

    public String getCreateDateStr() {
        if (artict.getCreateDate() == null) {
            return "-";
        }
        return DateFormatUtils.format(artict.getCreateDate(),"yyyy-MM-dd HH:mm:ss");
    }

    public void setCreateDate(Date createDate) {
        artict.setCreateDate(createDate);
    }

    public String getCreator() {
        return artict.getCreator();
    }

    public void setCreator(String creator) {
        artict.setCreator(creator);
    }

    public String getJumpUrl() {
        return artict.getJumpUrl();
    }

    public void setUpdateDate(Date updateDate) {
        artict.setUpdateDate(updateDate);
    }

    public Date getUpdateDate() {
        return artict.getUpdateDate();
    }
    public String getUpdateDateStr() {
        if (artict.getUpdateDate() == null) {
            return "-";
        }
        return DateFormatUtils.format(artict.getUpdateDate(), "yyyy-MM-dd HH:mm:ss");
    }
}
