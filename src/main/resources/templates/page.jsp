<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
    final String path = request.getContextPath();
    final String tmpPath = System.getProperty("basePath");
    String basePath;
    if (tmpPath == null) {
        basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
                + "/";
    } else {
        basePath = System.getProperty("basePath") + path + "/";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Security-Policy"
          content="upgrade-insecure-requests">
    <%@ include file="/common/tagMeta.jsp"%>
</head>
<body style="overflow: auto" id="ad_win">

<table style="width: 100%; table-layout: fixed;" border="0"
       cellspacing="0" cellpadding="0"
       class="table table-hover table-striped">
    <thead>
    <tr>
        <td>支付物品</td>

        <td>渠道名</td>

        <td>国家代码</td>

        <td>操作</td>
    </tr>
    </thead>
    <c:forEach var="row" items="${subsItemCountries}">
        <tr class="row_sel">
            <td>${row.itemId}</td>

            <td>${row.channel}</td>

            <td>${row.country}</td>
            <td>
                <button type="button" class="btn btn-default">
                    编辑
                </button>
            </td>
        </tr>
    </c:forEach>
</table>


<div id="update_box" class="box dn" style="top: 1px;width:auto; height: auto;">
    <form id="update_form" method="post">
        <div class="box_title">
            编辑
            <div class="box_close shut_close"></div>
        </div>
        <div class="box_cnt" style="padding-top: 3px;">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">

                <tr>
                    <td width="90" height="40" align="right">支付物品</td>
                    <td class="pos-relative">
                        <input name="itemId" type="text" class="form-control" />
                    </td>
                </tr>

                <tr>
                    <td width="90" height="40" align="right">渠道名</td>
                    <td class="pos-relative">
                        <input name="channel" type="text" class="form-control" />
                    </td>
                </tr>

                <tr>
                    <td width="90" height="40" align="right">国家代码</td>
                    <td class="pos-relative">
                        <input name="country" type="text" class="form-control" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center"><input
                            type="submit" class="btn btn-success" name="save" value="保 存" />
                        <input type="button" class="btn btn-default"
                               onclick="$('#update_box').fadeOut(500)" value="取 消" /></td>
                </tr>
            </table>
        </div>
    </form>
</div>
<div id="boxadd" class="box dn" style="top: 1px;width:400px; height: 300px;">
    <form id="addForm" method="post">
        <div class="box_title">
            新增
            <div class="box_close shut_close"></div>
        </div>
        <div class="box_cnt" style="padding-top: 3px;">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">

                <tr>
                    <td width="90" height="40" align="right">支付物品</td>
                    <td class="pos-relative">
                        <input name="itemId" type="text" class="form-control" />
                    </td>
                </tr>

                <tr>
                    <td width="90" height="40" align="right">渠道名</td>
                    <td class="pos-relative">
                        <input name="channel" type="text" class="form-control" />
                    </td>
                </tr>

                <tr>
                    <td width="90" height="40" align="right">国家代码</td>
                    <td class="pos-relative">
                        <input name="country" type="text" class="form-control" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center"><input
                            type="submit" class="btn btn-success" name="save" value="保 存" />
                        <input type="button" class="btn btn-default"
                               onclick="$('#boxadd').fadeOut(500)" value="取 消" /></td>
                </tr>
            </table>
        </div>
    </form>
</div>

<div class="list_btn">
    <input name="btn-upload-pre" type="button" class="btn btn-success"
           value="新增">
</div>
<script>
    $(function() {
        $('.box_close').click(function() {
            $('.box').hide();
        });

        $('.shut_close').click(function() {
            $('.box_close').trigger('click');
        });

        $('input[name=btn-upload-pre]').click(function() {
            $('#boxadd').removeClass('dn').show();
        });
        $('#addForm').validate({
            rules : {

                itemId : {
                    required : true
                },

                channel : {
                    required : true
                },

                country : {
                    required : true
                },
            },
            messages : {

                itemId : {
                    required : '请填写支付物品'
                },

                channel : {
                    required : '请填写渠道名'
                },

                country : {
                    required : '请填写国家代码'
                },
            },
            submitHandler : function(form) {

                var itemId = $('#boxadd input[name=itemId]').val();

                var channel = $('#boxadd input[name=channel]').val();

                var country = $('#boxadd input[name=country]').val();
                $.ajax({
                    type : 'POST',
                    url : BaseUtils.proPath + 'subscription/saveSubItemCountry.html',
                    data : {

                        itemId : itemId,

                        channel : channel,

                        country : country,
                    },
                    success : function(req) {
                        var res = eval("(" + req + ")");
                        if (res.code != '10000') {
                            alert(res.message);
                        } else {
                            BaseUtils.reload();
                        }
                    },
                    error : function() {
                        alert("操作失败，请重试！");
                    }
                });
                return false;
            }
        });

        $('.row_sel .btn-default').click(function () {
            $('#boxadd').addClass('dn').hide();
        });

        $('#update_form').validate({
            rules : {

                itemId : {
                    required : true
                },

                channel : {
                    required : true
                },

                country : {
                    required : true
                },
            },
            messages : {

                itemId : {
                    required : '请填写支付物品'
                },

                channel : {
                    required : '请填写渠道名'
                },

                country : {
                    required : '请填写国家代码'
                },
            },
            submitHandler: function () {

                var itemId = $('#update_box input[name=itemId]').val();

                var channel = $('#update_box input[name=channel]').val();

                var country = $('#update_box input[name=country]').val();
                $.ajax({
                    type : 'POST',
                    url : BaseUtils.proPath + 'subscription/updateSubItemCountry.html',
                    data : {

                        itemId : itemId,

                        channel : channel,

                        country : country,
                    },
                    success : function(req) {
                        var res = eval("(" + req + ")");
                        if (res.code != '10000') {
                            alert(res.message);
                        } else {
                            BaseUtils.reload();
                        }
                    },
                    error : function() {
                        alert("操作失败，请重试！");
                    }
                });
                return false;
            }
        });

        $('.row_sel .btn-default').click(function () {
            $('#boxadd').addClass('dn').hide();

            var itemId = $(this).parent().parent().find("td:eq(0)").text();

            var channel = $(this).parent().parent().find("td:eq(1)").text();

            var country = $(this).parent().parent().find("td:eq(2)").text();
            $('#update_box input[name = itemId]').val(itemId);
            $('#update_box input[name = channel]').val(channel);
            $('#update_box input[name = country]').val(country);
            $('#update_box').removeClass('dn').show();
        });
    });
</script>
</body>

</html>
