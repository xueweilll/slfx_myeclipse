<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>运用记录打印</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.1.0.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
    		var str1="<tr><td class=\"gdlm\">类型</td><td class=\"gdlm\" style='width:140px'>发起时间 </td><td class=\"gdlm\" style='width:140px'> " +
			"发令人</td><td class=\"gdlm\">接收人</td><td  class=\"gdlm\">签发人</td><td class=\"gdlm\">签发时间 </td>"+
			"<td class=\"gdlm\">转发人</td><td class=\"gdlm\">转发时间</td></tr>";
            var str2="<tr><td class=\"gdlm\">类型</td><td class=\"gdlm\" style='width:140px'>发起时间 </td><td class=\"gdlm\" style='width:140px'> " +
			"发令人</td><td class=\"gdlm\">接收人</td><td  class=\"gdlm\">签发人</td><td class=\"gdlm\">签发时间 </td></tr>";
	        var str3="<tr><td class=\"gdlm\">类型</td><td class=\"gdlm\" style='width:140px'>发起时间 </td><td class=\"gdlm\" style='width:140px'> " +
			"调度发起人</td><td class=\"gdlm\">领导审批人</td><td  class=\"gdlm\">领导审批时间</td></tr>"/* <td class=\"gdlm\">部门执行人 </td><td class=\"gdlm\">部门执行时间</td> */;
    		var type=${type};
    		var firstDemo = ${firstDemo};
    		var types;
    		//console.info(type);
    		if(type==0){
    			types=str1;
    			$("#print_list").append(types);
    			for (var obj = 0; obj <firstDemo.length;obj++ ) {
        			//console.info(firstDemo[obj].dispatchtype);
        			var s = "<tr><td class=\"gdlm\">"+ firstDemo[obj].dispatchtype  + 
        			"</td><td class=\"\">"+(firstDemo[obj].launchTime==undefined?"":firstDemo[obj].launchTime)+
        			"</td><td class=\"\">"+(firstDemo[obj].launcher==undefined?"":firstDemo[obj].launcher)+
        			"</td><td class=\"\">"+(firstDemo[obj].receipter == undefined?"":firstDemo[obj].receipter)+
        			"</td><td class=\"\">"+(firstDemo[obj].handlername == undefined?"":firstDemo[obj].handlername)+
        			"</td><td class=\"\">"+(firstDemo[obj].handletime == undefined?"":firstDemo[obj].handletime)+
        			/* "</td><td class=\"\">"+(firstDemo[obj].sender == undefined?"":firstDemo[obj].sender)+
        			"</td><td class=\"\">"+(firstDemo[obj].sendTime == undefined?"":firstDemo[obj].sendTime)+ */
        			"</td><td class=\"\">"+(firstDemo[obj].transmitName==undefined?"":firstDemo[obj].transmitName)+
        			"</td><td class=\"\">"+(firstDemo[obj].transmittime == undefined?"":firstDemo[obj].transmittime)
        			"</td></tr>";
        			$("#print_list").append(s);
        		}
    		}else if(type==1){
    			types=str2;
    			$("#print_list").append(types);
    			for (var obj = 0; obj <firstDemo.length;obj++ ) {
        			console.info(firstDemo[obj].sender);
        			var s = "<tr><td class=\"gdlm\">"+ firstDemo[obj].dispatchtype  + 
        			"</td><td class=\"\">"+(firstDemo[obj].launchTime==undefined?"":firstDemo[obj].launchTime)+
        			"</td><td class=\"\">"+(firstDemo[obj].launcher==undefined?"":firstDemo[obj].launcher)+
        			"</td><td class=\"\">"+(firstDemo[obj].receipter == undefined?"":firstDemo[obj].receipter)+
        			"</td><td class=\"\">"+(firstDemo[obj].handlername == undefined?"":firstDemo[obj].handlername)+
        			"</td><td class=\"\">"+(firstDemo[obj].handletime == undefined?"":firstDemo[obj].handletime)
        			/*+ "</td><td class=\"\">"+(firstDemo[obj].sender == undefined?"":firstDemo[obj].sender)+
        			"</td><td class=\"\">"+(firstDemo[obj].sendTime == undefined?"":firstDemo[obj].sendTime) */
        			"</td></tr>";
        			$("#print_list").append(s);
        		}
    		}else{
    			types=str3;
    			$("#print_list").append(types);
    			for (var obj = 0; obj <firstDemo.length;obj++ ) {
        			//console.info(firstDemo[obj].dispatchtype);
        			var s = "<tr><td class=\"gdlm\">"+ firstDemo[obj].dispatchtype  + 
        			"</td><td class=\"\">"+(firstDemo[obj].launchTime==undefined?"":firstDemo[obj].launchTime)+
        			"</td><td class=\"\">"+(firstDemo[obj].promoter==undefined?"":firstDemo[obj].promoter)+
        			"</td><td class=\"\">"+(firstDemo[obj].ldapproval == undefined?"":firstDemo[obj].ldapproval)+
        			"</td><td class=\"\">"+(firstDemo[obj].ldapprovaltime == undefined?"":firstDemo[obj].ldapprovaltime)
        			/* +"</td><td class=\"\">"+(firstDemo[obj].sender == undefined?"":firstDemo[obj].sender)+
        			"</td><td class=\"\">"+(firstDemo[obj].sendTime == undefined?"":firstDemo[obj].sendTime) */
        			"</td></tr>";
        			$("#print_list").append(s);
        		}
    		}
    		
    		
    		//alert(firstDemo.length);
    	
    		if(window.print){
    			window.print();
    			//window.close();
    		} 
    		//
    		//$("#print_list").jqprint();
    		//window.close();
    		/* $("#print_list").jqprint({
    		     debug: true, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
    		     importCSS: true, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
    		     printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）。
    		     operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true
    		}); */
    	});
    </script>
  </body>
</html>
