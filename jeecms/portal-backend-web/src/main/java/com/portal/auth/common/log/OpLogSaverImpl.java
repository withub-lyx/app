package com.portal.auth.common.log;

import com.portal.auth.authdb.entity.OperationLog;
import com.portal.auth.authdb.service.IOperationLogService;
import com.portal.common.log.IOpLogSaver;
import com.portal.common.log.OpLogVo;

/**
 * Created by liuquan on 2017/1/6 11:33
 */
public class OpLogSaverImpl implements IOpLogSaver{

    private IOperationLogService operationLogService;

    @Override
    public void save(OpLogVo opLog) {
        OperationLog entity = new OperationLog();
        entity.setType(opLog.getType());
        entity.setDetail(opLog.getDetail());
        entity.setOperateTime(opLog.getOperate_time());
        entity.setOperator(opLog.getOperator());
        entity.setSuccess(opLog.getSuccess()?1:0);
        entity.setExceptoinDetail(opLog.getExceptoinDetail());
        operationLogService.insert(entity);
    }

    public IOperationLogService getOperationLogService() {
        return operationLogService;
    }

    public void setOperationLogService(IOperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }
}
