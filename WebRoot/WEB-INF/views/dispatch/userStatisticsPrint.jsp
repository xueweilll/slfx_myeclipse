<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>打印</title>
    
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
    		var str="<tr><td class=\"gdlm\">枢纽</td><td class=\"gdlm\" style='width:140px'>开机时间 </td><td class=\"gdlm\" style='width:140px'> " +
    				"停机时间 </td><td class=\"gdlm\">运行时间/(时) </td><td  class=\"gdlm\">开机时内河水位</td><td class=\"gdlm\">开机时外河水位 </td><td  class=\"gdlm\">停机时内河水位</td><td class=\"gdlm\">停机时外河水位 </td>"+
    				"<td class=\"gdlm\">开机台数</td><td class=\"gdlm\">开机台时/(时)</td><td class=\"gdlm\">抽水流量</td><td class=\"gdlm\">总流量(万立方米)</td></tr>"
    		$("#print_list").append(str);
    		var firstDemo = ${firstDemo};
    		//alert(firstDemo.length);
    		for (var obj = 0; obj <firstDemo.length;obj++ ) {
    			console.info(firstDemo[obj].startinlandlevel);
    			var s = "<tr><td class=\"gdlm\">"+ firstDemo[obj].sname  + 
    			"</td><td class=\"\">"+firstDemo[obj].begintime+
    			"</td><td class=\"\">"+firstDemo[obj].endtime+
    			"</td><td class=\"\">"+(firstDemo[obj].runtime == undefined?"":firstDemo[obj].runtime)+
    			"</td><td class=\"\">"+(firstDemo[obj].startinlandlevel == undefined?"":firstDemo[obj].startinlandlevel)+
    			"</td><td class=\"\">"+(firstDemo[obj].startouterlevel == undefined?"":firstDemo[obj].startouterlevel)+
    			"</td><td class=\"\">"+(firstDemo[obj].stopinlandlevel == undefined?"":firstDemo[obj].stopinlandlevel)+
    			"</td><td class=\"\">"+(firstDemo[obj].stopouterlevel == undefined?"":firstDemo[obj].stopouterlevel)+
    			"</td><td class=\"\">"+firstDemo[obj].count+
    			"</td><td class=\"\">"+(firstDemo[obj].kjtime == undefined?"":firstDemo[obj].kjtime)+
    			"</td><td class=\"\">"+(firstDemo[obj].dcscharge == undefined?"":firstDemo[obj].dcscharge)+
    			"</td><td class=\"\">"+(firstDemo[obj].cscharge == undefined?"":firstDemo[obj].cscharge)+
    			"</td></tr>";
    			$("#print_list").append(s);
    		}
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
