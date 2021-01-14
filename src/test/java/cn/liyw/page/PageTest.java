package cn.liyw.page;

import cn.liyw.Application;
import cn.liyw.dao.UserDao;
import cn.liyw.domin.User;
import cn.liyw.domin.k_v;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PageTest {
    @Test
    public void T_add(){
        List<String> tableListName = Arrays.asList(( "支付物品,渠道名,国家代码,操作").split(","));
        List<String> tableValueName = Arrays.asList(( "itemId,channel,country").split(","));
        List<k_v> list = new ArrayList<>();
        k_v k1 = new k_v("支付物品","itemId");
        list.add(k1);
        k_v k2 = new k_v("渠道名","channel");
        list.add(k2);
        k_v k3 = new k_v("国家代码","country");
        list.add(k3);

        String addUrl ="subscription/saveSubItemCountry.html";
        String updateUrl ="subscription/updateSubItemCountry.html";



        String ss = "<%@ page language=\"java\" pageEncoding=\"UTF-8\"%>\n" +
                "<%@ include file=\"/common/taglibs.jsp\"%>\n" +
                "<%\n" +
                "final String path = request.getContextPath();\n" +
                "final String tmpPath = System.getProperty(\"basePath\");\n" +
                "String basePath;\n" +
                "if (tmpPath == null) {\n" +
                "basePath = request.getScheme() + \"://\" + request.getServerName() + \":\" + request.getServerPort() + path\n" +
                "+ \"/\";\n" +
                "} else {\n" +
                "basePath = System.getProperty(\"basePath\") + path + \"/\";\n" +
                "}\n" +
                "%>\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <base href=\"<%=basePath%>\">\n" +
                "    <meta http-equiv=\"Content-Security-Policy\"\n" +
                "          content=\"upgrade-insecure-requests\">\n" +
                "    <%@ include file=\"/common/tagMeta.jsp\"%>\n" +
                "</head>\n" +
                "<body style=\"overflow: auto\" id=\"ad_win\">\n" +
                "\n" +
                "<table style=\"width: 100%; table-layout: fixed;\" border=\"0\"\n" +
                "       cellspacing=\"0\" cellpadding=\"0\"\n" +
                "       class=\"table table-hover table-striped\">\n" +
                "    <thead>\n" +
                "    <tr>";
        StringBuilder sb = new StringBuilder(ss);
        for(String s : tableListName){
            sb.append("\n").append("       <td>").append(s).append("</td>").append("\n");
        }
        String ss1 = "    </tr>\n" +
                "    </thead>\n" +
                "    <c:forEach var=\"row\" items=\"${subsItemCountries}\">\n" +
                "        <tr class=\"row_sel\">";
        sb.append(ss1);
        for(String s : tableValueName){
            sb.append("\n").append("       <td>${row.").append(s).append("}</td>").append("\n");
        }

        String ss2 = "            <td>\n" +
                "                <button type=\"button\" class=\"btn btn-default\">\n" +
                "                    编辑\n" +
                "                </button>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </c:forEach>\n" +
                "</table>\n" +
                "\n" +
                "\n" +
                "<div id=\"update_box\" class=\"box dn\" style=\"top: 1px;width:auto; height: auto;\">\n" +
                "    <form id=\"update_form\" method=\"post\">\n" +
                "        <div class=\"box_title\">\n" +
                "            编辑\n" +
                "            <div class=\"box_close shut_close\"></div>\n" +
                "        </div>\n" +
                "        <div class=\"box_cnt\" style=\"padding-top: 3px;\">\n" +
                "            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n";
        sb.append(ss2);
        for(k_v s : list){
            sb.append("\n").append("                <tr>\n" +
                    "                    <td width=\"90\" height=\"40\" align=\"right\">").append(s.getName()).append("</td>\n" +
                    "                    <td class=\"pos-relative\">\n" +
                    "                        <input name=\"").append(s.getValue()).append("\" type=\"text\" class=\"form-control\" />\n" +
                    "                    </td>\n" +
                    "                </tr>").append("\n");
        }
        String ss3 = "                <tr>\n" +
                "                    <td colspan=\"2\" style=\"text-align: center\"><input\n" +
                "                            type=\"submit\" class=\"btn btn-success\" name=\"save\" value=\"保 存\" />\n" +
                "                        <input type=\"button\" class=\"btn btn-default\"\n" +
                "                               onclick=\"$('#update_box').fadeOut(500)\" value=\"取 消\" /></td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "    </form>\n" +
                "</div>\n";
        sb.append(ss3);
        String ss4 = "<div id=\"boxadd\" class=\"box dn\" style=\"top: 1px;width:400px; height: 300px;\">\n" +
                "    <form id=\"addForm\" method=\"post\">\n" +
                "        <div class=\"box_title\">\n" +
                "            新增\n" +
                "            <div class=\"box_close shut_close\"></div>\n" +
                "        </div>\n" +
                "        <div class=\"box_cnt\" style=\"padding-top: 3px;\">\n" +
                "            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n";
        sb.append(ss4);
        for(k_v s : list){
            sb.append("\n").append("                <tr>\n" +
                    "                    <td width=\"90\" height=\"40\" align=\"right\">").append(s.getName()).append("</td>\n" +
                    "                    <td class=\"pos-relative\">\n" +
                    "                        <input name=\"").append(s.getValue()).append("\" type=\"text\" class=\"form-control\" />\n" +
                    "                    </td>\n" +
                    "                </tr>").append("\n");
        }
        String ss5 =
                "                <tr>\n" +
                "                    <td colspan=\"2\" style=\"text-align: center\"><input\n" +
                "                            type=\"submit\" class=\"btn btn-success\" name=\"save\" value=\"保 存\" />\n" +
                "                        <input type=\"button\" class=\"btn btn-default\"\n" +
                "                               onclick=\"$('#boxadd').fadeOut(500)\" value=\"取 消\" /></td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "    </form>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"list_btn\">\n" +
                "    <input name=\"btn-upload-pre\" type=\"button\" class=\"btn btn-success\"\n" +
                "           value=\"新增\">\n" +
                "</div>\n" +
                "<script>\n" +
                "    $(function() {\n" +
                "        $('.box_close').click(function() {\n" +
                "            $('.box').hide();\n" +
                "        });\n" +
                "\n" +
                "        $('.shut_close').click(function() {\n" +
                "            $('.box_close').trigger('click');\n" +
                "        });\n" +
                "\n" +
                "        $('input[name=btn-upload-pre]').click(function() {\n" +
                "            $('#boxadd').removeClass('dn').show();\n" +
                "        });\n" +
                "        $('#addForm').validate({\n" +
                "            rules : {\n";
        sb.append(ss5);
        for(String s : tableValueName){
            sb.append("\n").append("                ").append(s).append(" : {\n" +
                    "                    required : true\n" +
                    "                },\n" +
                    "                ");
        }
        sb.append("},\n" +
                "            messages : {\n" );
        for(k_v s : list){
            sb.append("\n").append("                ").append(s.getValue()).append(" : {\n" +
                    "                    required : '请填写" +s.getName()+"'\n"+
                    "                },\n" +
                    "                ");
        }
        sb.append("},\n" +
                "            submitHandler : function(form) {\n" );
        for(String s : tableValueName){
            sb.append("\n").append("                var ").append(s).append(" = $('#boxadd input[name=")
            .append(s).append("]').val();\n");
        }
        sb.append("                $.ajax({\n" +
                "                    type : 'POST',\n" +
                "                    url : BaseUtils.proPath + '"+addUrl+"',\n" +
                "                    data : {\n");
        for(String s : tableValueName){
            sb.append("\n").append("                        ").append(s).append(" : ")
                    .append(s).append(",\n");
        }
        String ss6 =
                "                    },\n" +
                "                    success : function(req) {\n" +
                "                        var res = eval(\"(\" + req + \")\");\n" +
                "                        if (res.code != '10000') {\n" +
                "                            alert(res.message);\n" +
                "                        } else {\n" +
                "                            BaseUtils.reload();\n" +
                "                        }\n" +
                "                    },\n" +
                "                    error : function() {\n" +
                "                        alert(\"操作失败，请重试！\");\n" +
                "                    }\n" +
                "                });\n" +
                "                return false;\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        $('.row_sel .btn-default').click(function () {\n" +
                "            $('#boxadd').addClass('dn').hide();\n" +
                "        });\n" +
                "\n" +
                "        $('#update_form').validate({\n" +
                "            rules : {\n" ;
        sb.append(ss6);
        for(String s : tableValueName){
            sb.append("\n").append("                ").append(s).append(" : {\n" +
                    "                    required : true\n" +
                    "                },\n" +
                    "                ");
        }
        sb.append("},\n" +
                "            messages : {\n" );
        for(k_v s : list){
            sb.append("\n").append("                ").append(s.getValue()).append(" : {\n" +
                    "                    required : '请填写" +s.getName()+"'\n"+
                    "                },\n" +
                    "                ");
        }
        sb.append("            },\n" +
                "            submitHandler: function () {\n" );
        for(String s : tableValueName){
            sb.append("\n").append("                var ").append(s).append(" = $('#update_box input[name=")
                    .append(s).append("]').val();\n");
        }
        sb.append(
                "                $.ajax({\n" +
                "                    type : 'POST',\n" +
                "                    url : BaseUtils.proPath + '"+updateUrl+"',\n" +
                "                    data : {\n" );
        for(String s : tableValueName){
            sb.append("\n").append("                        ").append(s).append(" : ")
                    .append(s).append(",\n");
        }
        String ss7 =
                "                    },\n" +
                "                    success : function(req) {\n" +
                "                        var res = eval(\"(\" + req + \")\");\n" +
                "                        if (res.code != '10000') {\n" +
                "                            alert(res.message);\n" +
                "                        } else {\n" +
                "                            BaseUtils.reload();\n" +
                "                        }\n" +
                "                    },\n" +
                "                    error : function() {\n" +
                "                        alert(\"操作失败，请重试！\");\n" +
                "                    }\n" +
                "                });\n" +
                "                return false;\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        $('.row_sel .btn-default').click(function () {\n" +
                "            $('#boxadd').addClass('dn').hide();\n" ;
        sb.append(ss7);
        for(int i=0;i<tableValueName.size();i++ ){
            sb.append("\n").append("            var ").append(tableValueName.get(i)).append(" = $(this).parent().parent().find(\"td:eq(")
                    .append(i).append(")\").text();\n");
        }
        for(String s : tableValueName){
            sb.append("            $('#update_box input[name = ").append(s).append("]').val(").append(s).append(");\n");
        }
        sb.append("            $('#update_box').removeClass('dn').show();\n" +
                "        });\n" +
                "    });\n" +
                "</script>\n" +
                "</body>\n" +
                "\n" +
                "</html>");


        System.out.println(sb.toString());
    }

}