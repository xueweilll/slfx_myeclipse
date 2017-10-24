<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>My JSP 'workplan.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/day.css" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<script type="text/javascript">
	
</script>

<body>
	<div id="CalendarMain">
		<div id="title">
			<a class="selectBtn month" href="javascript:" 
				onclick="lastMonth()"><</a><a
				class="selectBtn selectYear" href="javascript:"
				onClick="CalendarHandler.CreateSelectYear(CalendarHandler.showYearStart);">2014年</a><a
				class="selectBtn selectMonth"
				onClick="">6月</a> <a
				class="selectBtn nextMonth" href="javascript:"
				onClick="nextMonth()">></a><a
				class="selectBtn currentDay" href="javascript:"
				onClick="currentDay()">今天</a>
		</div>
		<div id="context">
			<div class="week">
				<h3>星期一</h3>
				<h3>星期二</h3>
				<h3>星期三</h3>
				<h3>星期四</h3>
				<h3>星期五</h3>
				<h3>星期六</h3>
				<h3>星期日</h3>
			</div>
			<div id="center">
				<div id="centerMain" style="float:left"">
					<div id="selectYearDiv"></div>
					<div id="centerCalendarMain">
						<div id="Container"></div>
					</div>
					<div id="selectMonthDiv"></div>
				</div>
			</div>
			<div id="foots">
				<a id="footNow">19:14:34</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		
		$(document).ready(function(e) {
			CalendarHandler.initialize();
			drawExsit(CalendarHandler.currentYear + "-" + CalendarHandler.currentMonth);
			clickDiv();			
		});
		
		function lastMonth(){
			CalendarHandler.CalculateLastMonthDays();	
			clickDiv();
		}
		

		function nextMonth(){
			CalendarHandler.CalculateNextMonthDays();
			clickDiv();
		}
		
		function currentDay(){
			CalendarHandler.CreateCurrentCalendar(0,0,0);
			clickDiv();
		}
		
		function clickDiv(){
			$("#datetb div").click(function(event){
				var td = $(this).children("a").html();	
				if(parseInt(td)<10){
					td="0" + td;
				}
				var value= CalendarHandler.currentYear + "-" + CalendarHandler.currentMonth + "-" + td + " 00:00:00"; 
				var parentId=this.id;
				var chidId= (event.target).id;
				if(chidId == null || chidId.length == 0){
					showDialogWH("添加工作计划",
							"workplan/workplanInfo.html?id=0&date="+value, "450px","400px");
				}else if(parentId == chidId){
					showDialogWH("添加工作计划","workplan/workplanInfo.html?id=0&date="+value, "450px","400px");
				}
				else {
					showDialogWH("添加工作计划","workplan/workplanInfo.html?id="+chidId, "450px", "400px"); 
				}
			});
		}
		
		function drawExsit(conditions){
			$.ajax({
				type : "POST",
				url : "workplan/searchBydata.html",
				dataType : "text",				
				data : {
					"conditions" : conditions
				},
				success : function(msg) {
					data = eval('(' + msg + ')');
					
					for (var j = 0; j < data.length; j++) {
						
						var day=(data[j].beigintime).substring(8,10);
						var title = data[j].title;
						if(title.length >9){
							title=title.substring(0,8) +"..";
							$("#idCurrent"+parseInt(day)).append("<a class=\"box\" title=\""+data[j].title+"\" id=\""+data[j].id+"\" ><br>"+title+"</a>");
						}else{
							$("#idCurrent"+parseInt(day)).append("<a class=\"box\" title=\""+data[j].title+"\" id=\""+data[j].id+"\" ><br>"+data[j].title+"</a>");
						}
					}
				}					
			});
		}
		
		function removeBox(){
			$("a").remove(".box");
		}
		
		/* function C(i, y, m, d) {
			var f = 0;
			i.addEventListener("click", function(ev) {
				f = f + 1;
				var target = ev.target;
				if (target.className.indexOf('box') !== -1) {
					return target;
				}//这种情况下，点击的就是box元素，比如box里面有个padding的值，返回的就是自己。
				if (target == i) {
					if (f == 1) {
						showDialogWH("添加工作计划",
								"workplan/workplanInfo.html?id=0", "450px",
								"400px");
						//$(i).append("<a class=\"box\" href=\"javascript:click(this)\"><br>ssssssssssssaaaaaa</a>");
					}
					return false
				}//这种情况下，点到的是box与box之间的空隙，比如box有个margin-bottom:20px，返回false
				while (target.className.indexOf('item') === -1) {
					if (f == 1) {
						//$(i).append("<a class=\"box\" href=\"javascript:click(this)\"><br>ssssssssssssaaaaaa</a>");
						target = target.parentNode;
					}
					return false;
				}

				return target;//返回的target即是你要的box元素
			});

		
		} */

		var CalendarHandler = {
			currentYear : 0,
			currentMonth : 0,
			isRunning : false,
			showYearStart : 2009,
			tag : 0,
			
			initialize : function() {
				$calendarItem = this.CreateCalendar(0, 0, 0);
				$("#Container").append($calendarItem);
				
				$(".dayItem").css("width", $("#context").width() + "px");
				//var itemPaddintTop = $(".dayItem").height() / 6;
				var itemPaddintTop = 120;
				$(".item").css({
					"width" : ($(".week").width() / 7 -1)+ "px",
					/* "line-height" : itemPaddintTop + "px", */
					"height" : (itemPaddintTop-1) + "px"
				});
				
				var nowWeek = parseInt(this.CalculateWeek(this.currentYear, this.currentMonth, 1));
				var lines=6;
				if(nowWeek == 7 || nowWeek==6){
					lines = 6;
				}
				else if (nowWeek == 1){
					lines = 5;
				}
				console.info(lines);
				$("#dayItem").css("height", (lines * 130) + "px");
				

				/* $("#Container").css("height",
						( (lines * 120) + 5) + "px"); */
				$("#Container").css("height", (lines * 130) + "px").css("width", "0px").css(
						"margin-left", $("#context").width() / 2 + "px").css(
						"margin-top", ($("#context").height() - 30) / 2 + "px");
				$("#Container").animate({
					width : $("#context").width() + "px",
					height : ($("#context").height() - 30) + "px",
					marginLeft : "0px",
					marginTop : "0px"
				}, 400, function() {
					$calendarItem.css("visibility", "visible");
				});
				$("#centerCalendarMain").css("height", $("#Container").height() + "px");
				$("#center").css("height", $("#centerCalendarMain").height()  + "px");
				var centerHeight= $("#center").height() + $(".week").height();
				$("#context").css("height", centerHeight + "px");
				$("#foots").css("height", 36 + "px");
				$("#selectYearDiv").css("height",
						$("#context").height() - 30 + "px").css("width",
						$("#context").width() + "px");
				$("#selectMonthDiv").css("height",
						$("#context").height() - 30 + "px").css("width",
						$("#context").width() + "px");
				$("#CalendarMain").css("height", ($("#context").height() + 96) + "px");
				/* $("#centerCalendarMain").css("height",
						$("#context").height() - 30 + "px").css("width",
						$("#context").width() + "px"); */

				//$calendarItem.css("height", $("#context").height() - 30 + "px"); //.css("visibility","hidden");
				
				
				$(".currentItem a").css("margin-left",
						($(".item").width() - 25) / 2 + "px").css("margin-top",
						($(".item").height() - 25) / 2 + "px");
				$(".week h3").css("width", $(".week").width() / 7 + "px");
				
				this.RunningTime();
			},
			CreateSelectYear : function(showYearStart) {
				CalendarHandler.showYearStart = showYearStart;
				$(".currentDay").show();
				$("#selectYearDiv").children().remove();
				var yearindex = 0;
				for (var i = showYearStart; i < showYearStart + 12; i++) {
					yearindex++;
					if (i == showYearStart) {
						$last = $("<div>往前</div>");
						$("#selectYearDiv").append($last);
						$last
								.click(function() {
									CalendarHandler
											.CreateSelectYear(CalendarHandler.showYearStart - 10);
								});
						continue;
					}
					if (i == showYearStart + 11) {
						$next = $("<div>往后</div>");
						$("#selectYearDiv").append($next);
						$next
								.click(function() {
									CalendarHandler
											.CreateSelectYear(CalendarHandler.showYearStart + 10);
								});
						continue;
					}

					if (i == this.currentYear) {
						$yearItem = $("<div class=\"currentYearSd\" id=\"" + yearindex + "\">"
								+ i + "</div>")

					} else {
						$yearItem = $("<div id=\"" + yearindex + "\">" + i
								+ "</div>");
					}
					$("#selectYearDiv").append($yearItem);
					$yearItem.click(function() {
						$calendarItem = CalendarHandler.CreateCalendar(
								Number($(this).html()), 1, 1);
						$("#Container").append($calendarItem);
						CalendarHandler.CSS()
						CalendarHandler.isRunning = true;
						$($("#Container").find(".dayItem")[0]).animate({
							height : "0px"
						}, 400, function() {
							$(this).remove();
							CalendarHandler.isRunning = false;
						});
						$("#centerMain").animate({
							marginLeft : -$("#center").width() + "px"
						}, 500);
					});
					if (yearindex == 1 || yearindex == 5 || yearindex == 9)
						$("#selectYearDiv").find("#" + yearindex).css(
								"border-left-color", "#fff");
					if (yearindex == 4 || yearindex == 8 || yearindex == 12)
						$("#selectYearDiv").find("#" + yearindex).css(
								"border-right-color", "#fff");

				}
				$("#selectYearDiv div").css("width",
						($("#center").width() - 4) / 4 + "px").css(
						"line-height", ($("#center").height() - 4) / 3 + "px");
				$("#centerMain").animate({
					marginLeft : "0px"
				}, 400);
			},
			CreateSelectMonth : function() {
				$(".currentDay").show();
				$("#selectMonthDiv").children().remove();
				for (var i = 1; i < 13; i++) {
					if (i == this.currentMonth)
						$monthItem = $("<div class=\"currentMontSd\" id=\"" + i + "\">"
								+ i + "月</div>");
					else
						$monthItem = $("<div id=\"" + i + "\">" + i + "月</div>");
					$("#selectMonthDiv").append($monthItem);
					$monthItem.click(function() {
						$calendarItem = CalendarHandler.CreateCalendar(
								CalendarHandler.currentYear, Number($(this)
										.attr("id")), 1);
						$("#Container").append($calendarItem);
						CalendarHandler.CSS()
						CalendarHandler.isRunning = true;
						$($("#Container").find(".dayItem")[0]).animate({
							height : "0px"
						}, 400, function() {
							$(this).remove();
							CalendarHandler.isRunning = false;
						});
						$("#centerMain").animate({
							marginLeft : -$("#center").width() + "px"
						}, 500);
					});
					if (i == 1 || i == 5 || i == 9)
						$("#selectMonthDiv").find("#" + i).css(
								"border-left-color", "#fff");
					if (i == 4 || i == 8 || i == 12)
						$("#selectMonthDiv").find("#" + i).css(
								"border-right-color", "#fff");
				}
				$("#selectMonthDiv div").css("width",
						($("#center").width() - 4) / 4 + "px").css(
						"line-height", ($("#center").height() - 4) / 3 + "px");
				$("#centerMain").animate({
					marginLeft : -$("#center").width() * 2 + "px"
				}, 400);
			},
			IsRuiYear : function(aDate) {
				return (0 == aDate % 4 && (aDate % 100 != 0 || aDate % 400 == 0));
			},
			CalculateWeek : function(y, m, d) {
				var arr = "7123456".split("");
				with (document.all) {
					var vYear = parseInt(y, 10);
					var vMonth = parseInt(m, 10);
					var vDay = parseInt(d, 10);
				}
				var week = arr[new Date(y, m - 1, vDay).getDay()];
				return week;
			},
			CalculateMonthDays : function(m, y) {
				var mDay = 0;
				if (m == 0 || m == 1 || m == 3 || m == 5 || m == 7 || m == 8
						|| m == 10 || m == 12) {
					mDay = 31;
				} else {
					if (m == 2) {
						//判断是否为芮年
						var isRn = this.IsRuiYear(y);
						if (isRn == true) {
							mDay = 29;
						} else {
							mDay = 28;
						}
					} else {
						mDay = 30;
					}
				}
				return mDay;
			},
			
			ReCreateCalendar : function(y, m, d) {
				$dayItem = $("<div id=\"datetb\" class=\"dayItem\" ></div>");
				//获取当前月份的天数
				var nowDate = new Date();
				if (y == nowDate.getFullYear() && m == nowDate.getMonth() + 1
						|| (y == 0 && m == 0))
					$(".currentDay").hide();
				var nowYear = y == 0 ? nowDate.getFullYear() : y;
				this.currentYear = nowYear;
				var nowMonth = m == 0 ? nowDate.getMonth() + 1 : m;
				this.currentMonth = nowMonth;
				var nowDay = d == 0 ? nowDate.getDate() : d;
				$(".selectYear").html(nowYear + "年");
				$(".selectMonth").html(nowMonth + "月");
				var nowDaysNub = this.CalculateMonthDays(nowMonth, nowYear);
				//获取当月第一天是星期几
				//var weekDate = new Date(nowYear+"-"+nowMonth+"-"+1);
				//alert(weekDate.getDay());
				var nowWeek = parseInt(this.CalculateWeek(nowYear, nowMonth, 1));
				//nowWeek=weekDate.getDay()==0?7:weekDate.getDay();
				//var nowWeek=weekDate.getDay();
				//获取上个月的天数
				var lastMonthDaysNub = this.CalculateMonthDays((nowMonth - 1),
						nowYear);
				var lines = 5;
				if(nowWeek  ==6 || nowWeek == 7){
					lines = 6;
				}else if(nowWeek  == 1){
					lines = 5;
				}
				
				var list={};
				$.ajax({
					type : "POST",
					url : "workplan/searchBydata.html",
					dataType : "text",
					async : false,
					data : {
						"conditions" : nowYear+"-"+ (nowMonth<10?"0"+nowMonth:nowMonth)
					},
					success : function(msg) {
						data = eval('(' + msg + ')');
						list=data;
					}					
				});
	

				if (nowWeek != 0) {
					//生成上月剩下的日期
					for (var i = (lastMonthDaysNub - (nowWeek - 1)); i < lastMonthDaysNub; i++) {
						$dayItem.append("<div id=\"idLast" + (i + 1)
								+ "\" class=\"item lastItem\"><a class=\"\">" 
								+ "</a>&nbsp;</div>");
					}
				}

				//生成当月的日期
				for (var i = 0; i < nowDaysNub; i++) {
					
					var currentDay = new Date();
					var year= currentDay.getFullYear();
					var month = currentDay.getMonth() + 1;
					if ((y == year && m == month && i == (nowDay - 1)) || (m == 0&& y==0 &&d==0 && i == (nowDay - 1))) {
						var bool=false;
						for(var j = 0;j<list.length;j++){
							var day = list[j].beigintime;
							day =day.substring(0,10);
							if(day == (nowYear+"-"+nowMonth+"-"+((i+1)<10?"0"+(i+1):(i+1)))){
								bool=true;
								var title = data[j].title;
								if(title.length >9){
									title=title.substring(0,8) +"..";
									$dayItem.append("<div id=\"idCurrent"
											+ (i + 1)
											+ "\" class=\"item\" ><a class=\"currentPlanDay\">" + (i + 1)
											+ "</a>&nbsp;&nbsp;	<a class=\"box\" title=\""+list[j].title+"\" id=\""+list[j].id+"\" ><br>"+title+"</a></div>");
								}else{
									$dayItem.append("<div id=\"idCurrent"
											+ (i + 1)
											+ "\" class=\"item\" ><a class=\"currentPlanDay\">" + (i + 1)
											+ "</a>&nbsp;&nbsp;	<a class=\"box\" title=\""+list[j].title+"\" id=\""+list[j].id+"\" ><br>"+list[j].title+"</a></div>");
								}
								
								
								
							}
						}
						if(!bool){
							$dayItem.append("<div id=\"idCurrent"
										+ (i + 1)
										+ "\" class=\"item\" ><a class=\"currentPlanDay\">" + (i + 1)
										+ "</a>&nbsp;&nbsp;	</div>");
						}
					} else {
						var bool=false;
						var str="";
						for(var j = 0;j<list.length;j++){
							var day = list[j].beigintime;
							day =day.substring(0,10);
							if(day == (nowYear+"-"+nowMonth+"-"+((i+1)<10?"0"+(i+1):(i+1)))){
								bool=true;
								var title = data[j].title;
								if(title.length >9){
									title=title.substring(0,8) +"..";
									str = str+"<a class=\"box\" title=\""+list[j].title+"\" id=\""+list[j].id+"\" ><br>"+title+"</a>";
								/* 	$dayItem.append("<div class=\"item\" id=\"idCurrent"
											+ (i + 1) + "\" ><a class=\"\">" + (i + 1) + "</a>&nbsp;</div>"); */
								}else{
									str = str+"<a class=\"box\" title=\""+list[j].title+"\" id=\""+list[j].id+"\" ><br>"+list[j].title+"</a>";
									
								}
								
							}
						}
						if(!bool){
							$dayItem.append("<div class=\"item\" id=\"idCurrent"
								+ (i + 1) + "\" ><a class=\"\">" + (i + 1) + "</a>&nbsp;</div>");
						}
						else {
							$dayItem.append("<div class=\"item\" id=\"idCurrent"
									+ (i + 1) + "\" ><a class=\"\">" + (i + 1) + "</a>&nbsp;"+str+"</a></div>");
						}
					}
				}

				//获取总共已经生成的天数
				var hasCreateDaysNub = nowWeek + nowDaysNub -1;
				//如果小于42，往下个月推算
				if (hasCreateDaysNub < lines * 7) {
					for (var i = 0; i <= (lines * 7  -1 - hasCreateDaysNub); i++) {
						$dayItem
								.append("<div id=\"idNext"
										+ (i + 1)
										+ "\" class=\"item lastItem\"><a>" 
										+ "&nbsp;</a></div>");
					}
				}

				
				return $dayItem;
			},
			
			CreateCalendar : function(y, m, d) {
				$dayItem = $("<div id=\"datetb\" class=\"dayItem\" ></div>");
				//获取当前月份的天数
				var nowDate = new Date();
				if (y == nowDate.getFullYear() && m == nowDate.getMonth() + 1
						|| (y == 0 && m == 0))
					$(".currentDay").hide();
				var nowYear = y == 0 ? nowDate.getFullYear() : y;
				this.currentYear = nowYear;
				var nowMonth = m == 0 ? nowDate.getMonth() + 1 : m;
				this.currentMonth = nowMonth;
				var nowDay = d == 0 ? nowDate.getDate() : d;
				$(".selectYear").html(nowYear + "年");
				$(".selectMonth").html(nowMonth + "月");
				var nowDaysNub = this.CalculateMonthDays(nowMonth, nowYear);
				//获取当月第一天是星期几
				//var weekDate = new Date(nowYear+"-"+nowMonth+"-"+1);
				//alert(weekDate.getDay());
				var nowWeek = parseInt(this.CalculateWeek(nowYear, nowMonth, 1));
				//nowWeek=weekDate.getDay()==0?7:weekDate.getDay();
				//var nowWeek=weekDate.getDay();
				//获取上个月的天数
				var lastMonthDaysNub = this.CalculateMonthDays((nowMonth - 1),
						nowYear);
	
				var lines = 5;
				if(nowWeek  ==6 || nowWeek == 7){
					lines = 6;
				}else if(nowWeek  == 1){
					lines = 5;
				}
				if (nowWeek != 0) {
					//生成上月剩下的日期
					for (var i = (lastMonthDaysNub - (nowWeek - 1)); i < lastMonthDaysNub; i++) {
						$dayItem.append("<div id=\"idLast" + (i + 1)
								+ "\" class=\"item lastItem\"><a class=\"\">" 
								+ "</a>&nbsp;</div>");
					}
				}

				//生成当月的日期
				for (var i = 0; i < nowDaysNub; i++) {
					var currentDay = new Date();
					var year= currentDay.getFullYear();
					var month = currentDay.getMonth() + 1;
					if ((y == year && m == month && i == (nowDay - 1)) || (m == 0&& y==0 &&d==0 && i == (nowDay - 1))) {
						$dayItem.append("<div id=\"idCurrent"
										+ (i + 1)
										+ "\" class=\"item\" ><a class=\"currentPlanDay\">" + (i + 1)
										+ "</a>&nbsp;&nbsp;	</div>");
					} else {
						$dayItem.append("<div class=\"item\" id=\"idCurrent"
								+ (i + 1) + "\" ><a class=\"\">" + (i + 1) + "</a>&nbsp;</div>");
					}
				}

				//获取总共已经生成的天数
				var hasCreateDaysNub = nowWeek + nowDaysNub -1;
				//如果小于42，往下个月推算
				if (hasCreateDaysNub < lines * 7) {
					for (var i = 0; i <= ((lines * 7 - 1) - hasCreateDaysNub); i++) {
						$dayItem
								.append("<div id=\"idNext"
										+ (i + 1)
										+ "\" class=\"item lastItem\"><a>" 
										+ "&nbsp;</a></div>");
					}
				}

				
				return $dayItem;
			},

			CSS : function() {
				/* var itemPaddintTop = $(".dayItem").height() / 6; */
				var itemPaddintTop = 120;
				var nowWeek = parseInt(this.CalculateWeek(this.currentYear, this.currentMonth, 1));
				var lastMonthDaysNub = this.CalculateMonthDays(this.currentMonth ,this.currentYear);
				var lines=5;
				if(nowWeek == 7 || nowWeek==6){
					lines = 6;
				}
				else if (nowWeek == 1 || nowWeek == 2){
					lines = 5;
				}
				else if(nowWeek  == 5 ){
					lines=5;
				}
				$("#dayItem").css("height", (lines * 130) + "px");
				

				/* $("#Container").css("height",
						( (lines * 120) + 5) + "px"); */
				$("#Container").css("height", (lines * 130)  + "px").css("width", "0px").css(
						"margin-left", $("#context").width() / 2 + "px").css(
						"margin-top", ($("#context").height() - 30) / 2 + "px");
				$("#Container").animate({
					width : $("#context").width() + "px",
					height : (lines * 130) + "px",
					marginLeft : "0px",
					marginTop : "0px"
				}, 400, function() {
					$calendarItem.css("visibility", "visible");
				});
				$("#centerCalendarMain").css("height", $("#Container").height() + "px");
				
				$("#centerMain").css("height", ($("#Container").height()) + "px");
			
				$("#center").css("height", ($("#centerCalendarMain").height()) + "px");
				var centerHeight= $("#center").height() + $(".week").height();
				$("#context").css("height", centerHeight + "px");
				$("#foots").css("height", 36 + "px");
				$("#selectYearDiv").css("height",
						$("#context").height() - 30 + "px").css("width",
						$("#context").width() + "px");
				$("#selectMonthDiv").css("height",
						$("#context").height() - 30 + "px").css("width",
						$("#context").width() + "px");
				$("#CalendarMain").css("height", ($("#context").height() + 50 + $("#foots").height()) + "px");
				$(".item").css({
					"width" : ($(".week").width() / 7-1) + "px",
					/* "line-height" : itemPaddintTop + "px", */
					"height" : (itemPaddintTop-1) + "px"
				});
				$(".currentItem a").css("margin-left",
						($(".item").width() - 25) / 2 + "px").css("margin-top",
						($(".item").height() - 25) / 2 + "px");
			},
			CalculateNextMonthDays : function() {
				if (this.isRunning == false) {
					$(".currentDay").show();
					var m = this.currentMonth == 12 ? 1 : this.currentMonth + 1;
					var y = this.currentMonth == 12 ? (this.currentYear + 1)
							: this.currentYear;
					var d = 0;
					var nowDate = new Date();
					if (y == nowDate.getFullYear()
							&& m == nowDate.getMonth() + 1)
						d = nowDate.getDate();
					else
						d = 1;
					$calendarItem = this.ReCreateCalendar(y, m, d);
					$("#Container").append($calendarItem);

					$($("#Container").find(".dayItem")[0]).animate({
						height : "0px"
					}, 400, function() {
						$(this).remove();
						CalendarHandler.isRunning = false;
					});		
					
					this.CSS();
					this.isRunning = true;
					
					
					this.RunningTime();
					
				}
			},
			CalculateLastMonthDays : function() {
				if (this.isRunning == false) {
					$(".currentDay").show();
					var nowDate = new Date();
					var m = this.currentMonth == 1 ? 12 : this.currentMonth - 1;
					var y = this.currentMonth == 1 ? (this.currentYear - 1)
							: this.currentYear;
					var d = 0;

					if (y == nowDate.getFullYear()
							&& m == nowDate.getMonth() + 1)
						d = nowDate.getDate();
					else
						d = 1;
					$calendarItem = this.ReCreateCalendar(y, m, d);
					$("#Container").append($calendarItem);
					var itemPaddintTop = $(".dayItem").height() / 6;
					this.CSS();
					this.isRunning = true;
					
					$($("#Container").find(".dayItem")[0]).animate({
						height : "0px"
					}, 400, function() {
						$(this).remove();
						CalendarHandler.isRunning = false;
					});
					this.RunningTime();
					
				}
			},
			CreateCurrentCalendar : function() {
				if (this.isRunning == false) {
					$(".currentDay").hide();
					$calendarItem = this.ReCreateCalendar(0, 0, 0);
					$("#Container").append($calendarItem);
					this.isRunning = true;
					$($("#Container").find(".dayItem")[0]).animate({
						height : "0px"
					}, 400, function() {
						$(this).remove();
						CalendarHandler.isRunning = false;
					});
					this.CSS();
					$("#centerMain").animate({
						marginLeft : -$("#center").width() + "px"
					}, 500);
				}
			},
			RunningTime : function() {
				var mTiming = setInterval(function() {
					var nowDate = new Date();
					var h = nowDate.getHours() < 10 ? "0" + nowDate.getHours()
							: nowDate.getHours();
					var m = nowDate.getMinutes() < 10 ? "0"
							+ nowDate.getMinutes() : nowDate.getMinutes();
					var s = nowDate.getSeconds() < 10 ? "0"
							+ nowDate.getSeconds() : nowDate.getSeconds();
					var nowTime = h + ":" + m + ":" + s;
					$("#footNow").html("当前时间： "+nowTime);
				}, 1000);

			}
		}
	</script>

</body>
</html>
