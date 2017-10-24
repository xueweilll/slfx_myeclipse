<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%@include file="../header.jsp"%>
    <title>My JSP 'areaIntroduce.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function addTab(title,url,icon) {
	window.parent.addTab(title, url, icon);
}
</script>
  </head>
  
  <body>
   <table style=" width:100%; height:100%;">
<tr>
 <td class="lcbt" colspan="7">- 自              主             调                度 -</td>

</tr>
<tr>
<td></td>
<td style=" width:120px; "><a href="javascript:void(0)"  class="lctbk"  style="margin-bottom:7px">调度开始</a></td>
<td style=" width:60px;"><span  class="lctzx"></span></td>
<td style=" width:120px; height:80px;"><a  href="javascript:void(0)"  class="lctbk" style=" line-height:28px; padding-top:7px;" onclick="addTab('执行人员汇报(无上级调度令)','dispatchinstructions.html','icon icon-icon38')">执行部门<br/>值班人员</a></td>
<td style=" width:60px;"><span  class="lctzx"></span></td>
<td style=" width:120px;"><a href="javascript:void(0)"  class="lctbk" style=" line-height:28px; padding-top:7px;" onclick="addTab('执行部门请示','sdAuditor.html','icon icon-icon38')">执行部门<br/>负责人</a></td>


<td></td>


</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td style=" height:30px; text-align:center"><span  class="lctzxb"></td>
<td></td>
</tr>
<tr>
<td></td>
<td style=" width:120px; height:80px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('执行人员具体实施(无上级调度令)','sdexecute.html','icon icon-icon38')">执行人员</a></td>
<td style=" width:60px;"><span  class="lctzxl"></span></td>
<td style=" width:120px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('执行部门执行调度令(无上级调度令)','dispatchIssuedList.html','icon icon-icon38')">执行部门</a></td>
<td style=" width:60px;"><span  class="lctzxl"></span></td>
<td style=" width:120px;"><a href="javascript:void(0)" class="lctbk" style=" line-height:28px; padding-top:7px;" onclick="addTab('领导批准并下发调度令(电话指令)','sdApproval.html','icon icon-icon38')">单位领导<br/>分管领导</a></td>
<td></td>
</tr>
<tr>
<td class="lcbt" colspan="7">&nbsp;</td>
</tr>
</table>
  </body>
</html>
