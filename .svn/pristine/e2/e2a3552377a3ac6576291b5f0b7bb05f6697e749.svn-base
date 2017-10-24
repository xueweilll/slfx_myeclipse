<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dispatchGateRunTotalPrint.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript" src="js/jquery-migrate-1.1.0.js"></script>

<link rel="stylesheet" type="text/css" href="css/css.css">
<style>
.table01 td{text-align:center;}

</style>
  </head>
  
  <body>
    <table id="print_list" class="table01" style="width:1000px;" cellpadding="0" cellspacing="0">
    	
    </table>
    <script type="text/javascript">
    	$(function(){
    		var str1="<tr><td class=\"gdlm\">类型</td><td class=\"gdlm\">枢纽名称</td><td class=\"gdlm\" style='width:140px'>闸门名称 </td><td class=\"gdlm\" style='width:140px'> " +
			"操作时间</td><td class=\"gdlm\">操作</td>";
    		var type=${type};
    		var firstDemo = ${firstDemo};
    		var types;
    		if(type == "0"){
    			types = "大包围调度";
    		}else if(type =="1"){
    			types = "片区调度";
    		}else if(type == "2"){
    			types = "自主调度";
    		}
    		$("#print_list").append(str1);
    		for (var obj = 0; obj <firstDemo.length;obj++ ) {
    			var s = "<tr><td class=\"gdlm\">"+types  + 
    			"</td><td class=\"\">"+(firstDemo[obj].stationname==undefined?"":firstDemo[obj].stationname)+
    			"</td><td class=\"\">"+(firstDemo[obj].gatename==undefined?"":firstDemo[obj].gatename)+
    			"</td><td class=\"\">"+(firstDemo[obj].operatetime == undefined?"":firstDemo[obj].operatetime)+
    			"</td><td class=\"\">"+(firstDemo[obj].operate == undefined?"":firstDemo[obj].operate)
    			"</td></tr>";
    			$("#print_list").append(s);
    		}
    		if(window.print){
    			window.print();
    		} 

    	});
    </script>
  </body>
</html>
	