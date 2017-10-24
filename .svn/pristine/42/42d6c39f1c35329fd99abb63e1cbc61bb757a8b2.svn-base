/**
 * 消息推送
 */
function openMessageCenter() {
		addTab("消息中心", "messageCenter.html", "icon icon-home");
	}
	function mCenter() {
		var count = parseInt($("#messagecount").html());
		if (count != 0) {
			$("#messagecount").html(count - 1);
		}
	}
	function distoryAll(){
		$.messager.progress();
		$.post("messageCenter/deleteAll.html",function(data){
			data = eval('(' + data + ')');
			$.messager.progress('close');
			if(data.result){
				$("#messageCenter_list").datagrid("reload");
				$("#messagecount").html(0);
			}else{
				$.messager.alert("操作提示", data.msg, "error");
			}
			
		});
	}
	function openTab(index) {
		var rows = $("#messageCenter_list").datagrid("getRows");
		for (var int = 0; int < rows.length; int++) {
			var index1 = $("#messageCenter_list").datagrid("getRowIndex",
					rows[int]);
			if (index1 == index) {
				/*$.post("messageCenter/delete.html", {
					"id" : rows[int].id
				});*/
				addTab(rows[int].mname, rows[int].murl, "icon "
						+ rows[int].micon);
				/*mCenter();*/
				$('#messagedialog').dialog('close');
			}
		}
	}
	$(document).ready(function() {
		onPageLoad();
		dwr.engine.setActiveReverseAjax(true);
		dwr.engine.setNotifyServerOnPageUnload(true);
		dwr.engine.setErrorHandler(function() {
			// 
		});
	});
	function onPageLoad() {
		//var usrid='';//获取session中的ID值
		directController.onPageLoad($("#userid").val());
	}
	//推送信息  
	function receiveMessages(autoMessage) {
		var count = parseInt($("#messagecount").html());
		$("#messagecount").html(count + parseInt(autoMessage));
	}
	//显示消息中心面板
	function showmessageCenter() {
		$("#messageCenter_list").datagrid("reload");
		$('#messagedialog').dialog({
			title : '消息中心',
			width : 530,
			height : 500,
			closed : false,
			cache : false,
			modal : true
		});
	}
	
	//消息中心绑定
	function messageCenter() {
		$("#messageCenter_list")
				.datagrid(
						{
							//title : '消息中心',
							//iconCls : 'icon icon-icon7',
							width : 'auto',
							height : 'auto',
							pageSize : 20,
							nowrap : false,
							striped : true,
							border : true,
							collapsible : false,
							fit : true,
							url : 'messageCenter/bind.html',
							remoteSort : false,
							idField : 'id',
							singleSelect : true,
							pagination : true,
							rownumbers : true,
							toolbar: [{
								iconCls: 'icon_delete',
								text:'全部清空',
								handler: function(){distoryAll();}
							}],
							columns : [ [
									{
										field : 'sname',
										title : '发送人',
										width : 80
									},
									{
										field : 'mname',
										title : '消息类别',
										width : 150
									},
									{
										field : 'sendtime',
										title : '发送时间',
										width : 160
									},
									{
										field : 'aaa',
										title : '操作',
										width : 80,
										formatter : function(val, row, index) {
											return '<input name="btn" type="button" onclick="openTab('
													+ index
													+ ')"      value="去查看" />';
										}
									} ] ],
							onLoadSuccess:function(){
									$('input[name=btn]').linkbutton({
										width: '80',
										iconCls:'icon-add'
									});

								}
						});
		var p = $("#messageCenter_list").datagrid('getPager');
		//console.info(p);
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		$.post("messageCenter/selectCount.html", function(data) {
			$("#messagecount").html(data);
		});
	}