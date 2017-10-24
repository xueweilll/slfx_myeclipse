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
    <title>My JSP 'bigSurroundIntroduce.jsp' starting page</title>
    
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
 <td class="lcbt" colspan="9">-大包围调度-</td>

</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td style=" width:120px; height:80px;"><a  href="javascript:void(0)"  class="lctbk">调度开始</a></td>
<td style=" width:60px;"><span  class="lctzx"></span></td>
<td style=" width:120px; height:80px;"><a  href="javascript:void(0)"  class="lctbk">市防指</a></td>

<td style=" width:60px;"><span  class="lctzx"></span></td>
<td style=" width:120px;"><a href="javascript:void(0)"  class="lctbk" onclick="addTab('接收上级调度令(大包围)','bigsurroundreceipt.html','icon icon-icon38')">处值班室</a></td>


<td></td>


</tr>
<tr>


<td></td>
<td></td>
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


<td style=" width:120px; height:80px;"><a href="javascript:void(0)" class="lctbk" style=" line-height:28px; padding-top:7px;" onclick="addTab('大包围调度督察','supervise.html','icon icon-icon38')">处值班室</br>督查</a></td>
<td style=" width:60px;"><span  class="lctzxl"></span></td>
<td style=" width:120px;"><a href="javascript:void(0)" class="lctbk" style=" line-height:28px; padding-top:7px;" onclick="addTab('大包围调度回访','callback.html','icon icon-icon38')">处值班室<br/>回访</a></td>
<td style=" width:60px;"><span  class="lctzxl"></span></td>
<td style=" width:120px;"><a href="javascript:void(0)" class="lctbk" style=" line-height:28px; padding-top:7px;" onclick="addTab('大包围调度转发','dispatchTransmitList.html','icon icon-icon38')">相关单位<br/>转发</a></td>
<td style=" width:60px;"><span  class="lctzxl"></span></td>
<td style=" width:120px;"><a href="javascript:void(0)" class="lctbk" style=" line-height:28px; padding-top:7px;" onclick="addTab('单位/分管领导签发(大包围)','bigsurroundreceiptaduit.html','icon icon-icon38')">单位领导<br/>分管领导</a></td>
<td></td>


</tr>

<tr>

<td></td>

<td></td>
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
<td></td>
<td></td>
<td style=" width:120px; height:80px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('执行人员具体实施(有上级调度令)','rdexecute.html','icon icon-icon38')">执行人员</a></td>
<td style=" width:60px;"><span  class="lctzxl"></span></td>
<td style=" width:120px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('执行部门执行调度令(有上级调度令)','receiptDispatch.html','icon icon-icon38')">执行部门</a></td>
<td style=" width:60px;"><span  class="lctzxl"></span></td>
<td style=" width:120px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('工程科具体分解(大包围)','bigSurround.html','icon icon-icon38')">工程科</a></td>
<td></td>


</tr>
<tr>
<td class="lcbt" colspan="9">&nbsp;</td>

</tr>
</table>
  </body>
</html>
