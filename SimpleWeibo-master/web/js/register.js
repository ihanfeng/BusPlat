// JavaScript Document
//用于注册时表单的验证


var xmlhttp;
function createXMLHttpRequest(){
	if(window.ActiveXObject)
		xmlhttp = new  ActiveXObject("Microsoft.XMLHTTP");
	else if(window.XMLHttpRequest)
		xmlhttp = new XMLHttpRequest();	
	
}
function ifNull(objText){
	//文本框为空的话返回并给出提示信息
	if(!objText.value){
		objText.focus();
		document.getElementById("check_use").innerHTML = "用户名不能为空";
		return;
	}
	//创建异步请求
	createXMLHttpRequest();
	

}

























