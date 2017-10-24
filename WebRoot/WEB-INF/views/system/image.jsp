<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'image.jsp' starting page</title>
    <%@include file="../header.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/easyui/plugins/jquery.treegrid.js"></script>
  </head>
  
  <body>
  <input type="hidden" id="pid" value="${pid}">
  <input type="hidden" id="sid" value="${id}">
    <script type="text/javascript">
    	var array=[];
  		$(function(){
  			//$("#imgs").attr("src","workFlow/viewBind.html?id="+$("#pid").val());
  			$('#dd').hide();
  			$.post("dispatchExecute/taskBind.html",{"id":$("#sid").val()},function(data){
  				$json=$(data);
  				var html="";
  				$json.each(function(index){
  					var color="";
  					var str="";
  					if(this.name==="callactivity"){
  						color="red";
  						str="辖区调度情况";
  						html=html+'<div style="color:'+color+';text-align: center;font-style:italic;font-weight:bold;position: absolute;top:'+(parseInt(this.y)+30)+'px;left: '+this.x+'px;width: '+this.wi+'px;height:'+this.hi+'px;" >'+str+'</div>';
  					}else if(this.name=="count"){
  						$('#dd').show();
  					}else{
  						if(this.state===0){
	  						color="red";
	  						str="未完成";
		  					html=html+'<div style="color:'+color+';text-align: center;font-style:italic;font-weight:bold;position: absolute;top:'+(parseInt(this.y)+30)+'px;left: '+this.x+'px;width: '+this.wi+'px;height:'+this.hi+'px;"   title="'+'开始时间：'+this.starttime+'" >'+str+'</div>';
	  					}else{
	  						str="已完成";
	  						color="green";
		  					html=html+'<div style="color:'+color+';text-align: center;font-style:italic;font-weight:bold;position: absolute;top:'+(parseInt(this.y)+30)+'px;left: '+this.x+'px;width: '+this.wi+'px;height:'+this.hi+'px;"   title="'+'开始时间：'+this.starttime+'  结束时间：'+this.endtime+'" >'+str+'</div>';
	  					}
  					}
  					//html=html+'<div style="color:'+color+';text-align: center;font-style:italic;font-weight:bold;border:2px solid '+color+';position: absolute;top:'+this.y+'px;left: '+this.x+'px;width: '+this.wi+'px;height:'+this.hi+'px;"   title="'+this.state+'开始时间：'+this.starttime+'" >'+str+'</div>';
  				});
  				$("#aaa").html($("#aaa").html()+html);
  			},"json");
  		});
  	</script>
  	<div id="aaa" style="overflow:hidden;margin-top:30px;">
    	<img  id="imgs" alt=""  src="workFlow/viewBind.html?id=${pid}" style="position:absolute;top:0px;left:0px;margin-top:30px;">
    </div>
    <div id="dd" style="float: right;position:absolute;top:0px;right:0; margin-top:30px;"><iframe src="dispatchExecute/imageInfo.html?sid=${id}" height="600" width="700" border="0" style="float:right;border:0px;padding:0px;margin-right:25px"></iframe></div>
  </body>
</html>
