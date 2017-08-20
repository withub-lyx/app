;window.auth_global=(function(){
    var commonToast = {};
    function initToastOptions(option) {
        var defaultOption = {
            closeButton: true,
            progressBar: true,
            positionClass: 'toast-top-right',
            showEasing: 'swing',
            hideEasing: 'linear',
            showMethod: 'fadeIn',
            fadeOut: 'fadeOut'
        };

        if(option && typeof  option =='object'){
            $.extend(defaultOption,option);
        }

        toastr.options = defaultOption;
    }

    commonToast.toastError = function (title, msg,option) {
        initToastOptions(option);
        if (title && msg) {
            toastr.error(title, msg)
        } else if (title) {
            toastr.error(title)
        }
    };
    commonToast.toastSuccess = function toastSuccess(title, msg,option) {
        initToastOptions(option);
        if (title && msg) {
            toastr.success(title, msg)
        } else if (title) {
            toastr.success(title)
        }
    };
    commonToast.toastWarning = function toastWarning(title, msg,option) {
        initToastOptions(option);
        if (title && msg) {
            toastr.warning(title, msg)
        } else if (title) {
            toastr.warning(title)
        }
    };
    commonToast.toastInfo = function toastInfo(title, msg) {
        initToastOptions();
        if (title && msg) {
            toastr.info(title, msg)
        } else if (title) {
            toastr.info(title)
        }
    };

    var commonLayer=function(paramOption){
        var defaultOption= {
            id: 'addLayer', //用于控制弹层唯一标识，设置该值后，不管是什么类型的层，都只允许同时弹出一个。一般用于页面层和iframe层模式
            type: 2,   //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            //title: title,
            //skin: 'layui-layer-rim', //加上边框
            area: ['50%', '70%'],
            cancel: function () {
                //alert('右上角关闭');
            },
            closeBtn: 1,//可通过配置1和2来展示，如果不显示，则closeBtn: 0
            shade: 0.8,//shade - 遮罩
            shadeClose: false,//是否点击遮罩关闭,
            maxmin: true,//最大最小化
            fixed: true,//即鼠标滚动时，层是否固定在可视区域。如果不想，设置fixed: false即可
            resize: true,
            shift: 2,//飞入动画
            //content: url, //iframe的url
            end: function () { //此处用于演示
            }
        }
        var option = $.extend(defaultOption,paramOption);
        layer.open(option);
    };


    var formSubmit = function ($form, url,successCallback,errorCallback,method) {
        if(!method){
            method="POST";
        }
        $form.ajaxSubmit({
                url: url,
                type: method,
                dataType: "JSON",
                success: function (data) {
                    if (data.code == 0) {
                        if(successCallback && typeof successCallback=='function'){
                            successCallback(data.msg,data.data);
                        }
                    } else {
                        if(errorCallback && typeof errorCallback=='function'){
                            errorCallback(data.code,data.msg,data.data);
                        }
                    }
                },
                error: function () {
                    if(errorCallback && typeof errorCallback=='function'){
                        errorCallback(null,data.msg,data.data);
                    }
                }
            }
        )
    };


    var commonAjax = function (url, successCallback, errorCallback, data, method) {
        if (!method) {
            method = "POST";
        }
        $.ajax({
            "url": url,
            "method": method,
            "data": data,
            //contentType:"application/json",
            "dataType": "JSON",
            success: function (data) {
                if (data.code == 0) {
                    if (successCallback && typeof successCallback == 'function') {
                        successCallback(data.msg,data.data);
                    }
                } else {
                    if (errorCallback && typeof errorCallback == 'function') {
                        errorCallback(data.code,data.msg, data.data);
                    }
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (errorCallback && typeof errorCallback == 'function') {
                    errorCallback(null,textStatus+errorThrown);
                }
            }
        });
    };


    var validateErrorIcon = "<i class='fa fa-times-circle'></i> ";

    var tableUtil={};
    tableUtil.getSelections =function($table,idColomn){
        if(!idColomn) {
            idColomn='id';
        }
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row[idColomn];
        });
    };

    tableUtil.selectPage=function($table,page){
        $table.bootstrapTable('selectPage', page);
    };

    tableUtil.defalutOption = {
        method: 'get',//获取数据方式 get/post
        mobileResponsive: true,
        height: "450",//固定高度
        pagination: true,//显示分页条
        sidePagination: 'server',//在哪里分页
        pageSize: 10,//每页大小
        pageList: [10, 20, 30, 40],
        showFooter: false,//页脚是否显示
        showRefresh: true,//显示刷新按钮
        showToggle: false,//切换试图按钮
        //showPaginationSwitch: true,//是否显示 数据条数选择框  木什么鸟用
        striped: true,//行变色
        //这两个参数一起使用==========
        //detailView: false,//可显示行的详细内容
        //detailFormatter: function (index, row) {
        //    return JSON.stringify(row);
        //},
        //这两个参数一起使用==========
        //toolbar: '#exampleTableEventsToolbar',//工具栏
        //queryParams: function (params) {
        //    return $.extend(params, $queryBtn.data('queryParam'));
        //}
        checkbox: true,
        sortable:true//设置为false 将禁止所有列的排序 ，默认true
    };

    tableUtil.initTable=function($table,paramOption){
        var option = $.extend(tableUtil.defalutOption,paramOption);
        $table.bootstrapTable(option);
    };
    tableUtil.refresh=function($table){
        $table.bootstrapTable("refresh");
    };


    return {
        commonToast: commonToast,
        commonLayer:commonLayer,
        formSubmit:formSubmit,
        validateErrorIcon:validateErrorIcon,
        commonAjax:commonAjax,
        tableUtil:tableUtil
    }
})();

$(function(){
    function initValidate(){
        $.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            errorElement: "span",
            errorPlacement: function (error, element) {
                if (element.is(":radio") || element.is(":checkbox")) {
                    error.appendTo(element.parent().parent().parent());
                } else {
                    error.appendTo(element.parent());
                }
            },
            errorClass: "help-block m-b-none",
            validClass: "help-block m-b-none"
        });
    }
    function initIcheckBox(){
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    }

    function initChosen(){
        var config = {
            '.chosen-select': {},
            '.chosen-select-deselect': {
                allow_single_deselect: true
            },
            '.chosen-select-no-single': {
                disable_search_threshold: 10
            },
            '.chosen-select-no-results': {
                no_results_text: 'Oops, nothing found!'
            },
            '.chosen-select-width': {
                width: "95%"
            }
        }
        for (var selector in config) {
            $(selector).chosen(config[selector]);
        }
    }
    initValidate();
    initIcheckBox();
    initChosen();
});

