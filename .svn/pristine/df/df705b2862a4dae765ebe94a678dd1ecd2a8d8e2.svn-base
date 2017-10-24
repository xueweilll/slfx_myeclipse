/**
 * 获取十进制数值
 */
function decimalismResult(obj){
	var selects=$("#"+obj).find(".register_input");
	var result="";
	for (var i = 0; i < selects.length; i++) {
		var select = selects[i];
		var selectResult=$(select).val();
		result=selectResult+result;
	}
	var decimalism=parseInt(result,2);
	return decimalism;
}
/**
 * 传入10进制数据，勾选相关选项
 */
function binaryBind(obj,decimalism){
	var selects=$("#"+obj).find(".register_input");
	var binary=parseInt(decimalism).toString(2);
	var count=selects.length-binary.length;
	if(count !=0){
		for(var i=0;i<count;i++){
			binary="0"+binary;
		}
	}
	for (var i = 0; i < selects.length; i++) {
		var select = selects[i];
		if(binary[i]==0){
			$(select).val(0);
		}else{
			$(select).val(1);
		}
	}
}
/**
 * 获取table下所有 select 中class ==.register_input 的结果不为0的值，拼接字符串，以","分割
 */
function backResult(obj){
	var selects=$("#"+obj).find(".register_input");
	var result="";
	for (var i = 0; i < selects.length; i++) {
		var select = selects[i];
		var selectResult=$(select).val();
		if(selectResult==1){
			result=result+selectResult+",";
		}
	}
	if(result!=""){
		result.substr(0,result.length-1);
	}
	return result;
}
/**
 * 传入以","分割的字符串，勾选相关选项
 */
function selectByResult(obj){
	var selects=$("#"+obj).find(".register_input");
	var resultArray=obj.split(",");
	for (var j = 0; j < resultArray.length; j++) {
		var result = resultArray[j];
		for (var i = 0; i < selects.length; i++) {
			var select = selects[i];
			var options=$(select).find("option");
			var optionResult="";
			for (var k = 0; k < options.length; k++) {
				var option = options[k];
				var optionValue=$(option).attr("value");
				if(optionValue!=0){
					optionResult=optionValue;
				}
			}
			if(optionResult==result){
				$(select).val(optionResult);
				break;
			}
		}
	}
	
}