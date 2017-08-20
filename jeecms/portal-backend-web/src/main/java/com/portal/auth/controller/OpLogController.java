package com.portal.auth.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.portal.auth.authdb.entity.*;
import com.portal.auth.authdb.service.IOperationLogService;
import com.portal.auth.authdb.service.IUsersService;
import com.portal.auth.controller.form.AppSsoForm;
import com.portal.auth.controller.form.OperationLogForm;
import com.portal.common.page.BootstrapTablePage;
import com.portal.common.page.BootstrapTablePageRequest;
import com.portal.common.page.JsonData;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuquan on 2017/1/6 14:15
 */
@Controller
@RequestMapping("/opLog")
public class OpLogController {


    @Autowired
    private IOperationLogService operationLogService;

    @Autowired
    private IUsersService usersService;

    @RequestMapping(value = "/listPage",method = RequestMethod.GET)
    public String listPage(Model model) {

        List<String> typeList = operationLogService.getTypeList();

        List<Users> userses = usersService.selectList(new Condition());
        model.addAttribute("typeList", typeList);
        model.addAttribute("userses", userses);
        return "/page/auth/oplog/oplog_list";
    }


    @ResponseBody
    @RequestMapping(value = "/pageJson", method ={ RequestMethod.GET, RequestMethod.POST})
    public JsonData<BootstrapTablePage> pageJson(BootstrapTablePageRequest<OperationLog> pageRequest, OperationLog queryParam) {
        Condition conditon = buildCondition(queryParam);
        pageRequest.setOrder("desc");
        pageRequest.setSort(OperationLog.ID);
        Page<OperationLog> selectPage = operationLogService.selectPage(pageRequest.toPage(), conditon);


        List<OperationLogForm> result = new ArrayList<OperationLogForm>();
        List<OperationLog> records = selectPage.getRecords();
        if (CollectionUtils.isNotEmpty(records)) {
            for (OperationLog temp : records) {
                result.add(new OperationLogForm(temp));
            }
        }

        return new JsonData<BootstrapTablePage>(new BootstrapTablePage(selectPage,result));
    }

    private Condition buildCondition(OperationLog queryParam) {
        Condition conditon = new Condition();
        if (StringUtils.isNotBlank(queryParam.getType())) {
            conditon.like(OperationLog.TYPE, queryParam.getType());
        }
        if (StringUtils.isNotBlank(queryParam.getDetail())) {
            conditon.like(OperationLog.DETAIL, queryParam.getDetail());
        }
        if (StringUtils.isNotBlank(queryParam.getExceptoinDetail())) {
            conditon.like(OperationLog.EXCEPTOIN_DETAIL, queryParam.getExceptoinDetail());
        }

        if (StringUtils.isNotBlank(queryParam.getOperator())) {
            conditon.like(OperationLog.OPERATOR, queryParam.getOperator());
        }
        return conditon;
    }

    @RequestMapping(value = "/detailPage",method = RequestMethod.GET)
    public String detailPage(String id, Model model) {
        OperationLog operationLog = operationLogService.selectById(id);
        String exceptoinDetail = operationLog.getExceptoinDetail();
        String detail = operationLog.getDetail();
        model.addAttribute("exceptoinDetail", exceptoinDetail);
        model.addAttribute("detail",detail );
        return "/page/auth/oplog/oplog_detailPage";
    }
}
