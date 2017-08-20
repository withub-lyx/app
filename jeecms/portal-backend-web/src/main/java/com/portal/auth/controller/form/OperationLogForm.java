package com.portal.auth.controller.form;

import com.portal.auth.authdb.entity.OperationLog;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

/**
 * Created by liuquan on 2017/1/6 14:25
 */
public class OperationLogForm {
    private OperationLog operationLog;

    public OperationLogForm() {
    }


    public OperationLogForm(OperationLog operationLog) {
        this.operationLog = operationLog;
    }

    public void setSuccess(Integer success) {
        operationLog.setSuccess(success);
    }

    public Integer getId() {
        return operationLog.getId();
    }

    public void setId(Integer id) {
        operationLog.setId(id);
    }

    public String getType() {
        return operationLog.getType();
    }

    public void setType(String type) {
        operationLog.setType(type);
    }

    public String getOperator() {
        return operationLog.getOperator();
    }

    public void setOperator(String operator) {
        operationLog.setOperator(operator);
    }

    public Date getOperateTime() {
        return operationLog.getOperateTime();
    }
    public String getOperateTimeStr() {
        Date operateTime = operationLog.getOperateTime();
        if (operateTime == null) {
            return "-";
        }
        return DateFormatUtils.format(operateTime, "yyyy-MM-dd HH:mm:ss");
    }

    public void setOperateTime(Date operateTime) {
        operationLog.setOperateTime(operateTime);
    }




    public String getDetail() {
        if (StringUtils.isNotBlank(operationLog.getDetail()) && operationLog.getDetail().length() > 300) {
            return operationLog.getDetail().substring(0, 300) + "...";
        }
        return operationLog.getDetail();
    }

    public void setDetail(String detail) {
        operationLog.setDetail(detail);
    }

    public String getExceptoinDetail() {
        if (StringUtils.isNotBlank(operationLog.getExceptoinDetail()) && operationLog.getExceptoinDetail().length() > 300) {
            return operationLog.getExceptoinDetail().substring(0, 300) + "...";
        }
        return operationLog.getExceptoinDetail();
    }

    public void setExceptoinDetail(String exceptoinDetail) {
        operationLog.setExceptoinDetail(exceptoinDetail);
    }

    public Integer getSuccess() {
        return operationLog.getSuccess();
    }
}
