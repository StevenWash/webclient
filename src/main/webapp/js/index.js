/**
 * 项目启动后加载连接配置的默认属性
 */
//加载完js文件后自动执行的函数
$(function(){
	//alert("tt");
	$.ajax({
        type:"GET",
		url:"api/broker/getDefaultConnProp",
		success:function(res){// 请求响应成功后的回调函数。其中res是返回的数据
		 	//alert(res);
		 	var start=res.indexOf("[");
		 	var str=res.substring(start+1,res.length-1);
		 	//alert(str);
		 	str=str.split("=");
		 	//alert(str);
		 	for(var i=1;i<str.length;i++){
		 		str[i]=str[i].split(",")[0];
		 	}
		 	//alert(str);
		 	$(".header").find("#address").val(str[1]);
        	$(".header").find("#port").val(str[2]);
        	
        	if(str[3]==1){
        		$(".header").find("#connopts").find("#sessionopts").attr("value",1);
        		$(".header").find("#connopts").find("#sessionopts").html("Trace(start)");
        	}else{
        		$(".header").find("#connopts").find("#sessionopts").attr("value",0);
        		$(".header").find("#connopts").find("#sessionopts").html("Trace(stop)");
        	}
        	$(".header").find("#connopts").find("#clientId").val(str[4]);
        	
        	if(str[5]==1){
        		$(".header").find("#connopts").find("#cleansession").attr("value",1);
        		$(".header").find("#connopts").find("#cleansession").attr("checked",true);
        	}else{
        		$(".header").find("#connopts").find("#cleansession").attr("value",0);
        		$(".header").find("#connopts").find("#cleansession").attr("checked",false);
        	}
        		
        	$(".header").find("#connopts").find("#keepalive").val(str[6]);
        	$(".header").find("#connopts").find("#retryinterval").val(str[7]);
        	
        	//alert(str[8]);
        	if(str[8]==1){
        		$(".header").find("#connopts").find("#usepersis").attr("value",1);
        		$(".header").find("#connopts").find("#usepersis").attr("checked",true);
        	}else{
        		$(".header").find("#connopts").find("#usepersis").attr("value",0);
        		$(".header").find("#connopts").find("#usepersis").attr("checked",false);
        	}
        		
        	$(".header").find("#connopts").find("#directory").val(str[9]);
        	$(".header").find("#connopts").find("#topic").val(str[10]);
        	
        	//alert("qos:"+str[11]);
        	//alert($(".header").find("#connopts").find("#selQos").val());
        	$(".header").find("#connopts").find("#selQos").val(str[11]);
        	$("#content").find("#selQos").val(str[11]);
        	
        	if(str[12]==1){
        		$(".header").find("#connopts").find("#retained").attr("value",1);
        		$(".header").find("#connopts").find("#retained").attr("checked",true);
        		$("#content").find("#retained").attr("value",1);
        		$("#content").find("#retained").attr("checked",true);
        	}else{
        		$(".header").find("#connopts").find("#retained").attr("value",0);
        		$("#content").find("#retained").attr("value",0);
        		$(".header").find("#connopts").find("#retained").attr("checked",false);
        		$("#content").find("#retained").attr("checked",false);
        	}
        	$(".header").find("#connopts").find("#message-text").val(str[13]);
		 	
		 }
	});
});
 



/**
 * 在连接属性的页面，点击了Trace(start)之后将按钮设置为Trace(stop)
 */
$(".header").find("#sessionopts").bind('click',(function() {
	var btnname=$(this).html();
	// alert(btnname);
	if(btnname=="Trace(start)"){
		$(this).attr("value",0);
		$(this).html("Trace(stop)");
	}else{
		$(this).attr("value",1);
		$(this).html("Trace(start)");
	}
}))

/**
 * 添加connect按钮的点击事件
 */
$(".header").delegate("#connectbtn","click",function(event){
	// 当DOM元素加载完成后发送ajax请求，获取渲染界面的JSON数据
	//alert($(".header").find("#connopts").find("#cleansession").val());
    $.ajax({
        type:"GET",
        data:{
        	ipAddress:$(".header").find("#address").val(),
        	port:$(".header").find("#port").val(),
        	tracestart:$(".header").find("#connopts").find("#sessionopts").val(),
        	clientId:$(".header").find("#connopts").find("#clientId").val(),
        	cleanSession:$(".header").find("#connopts").find("#cleansession").val(),
        	keepAlive:$(".header").find("#connopts").find("#keepalive").val(),
        	retryInterval:$(".header").find("#connopts").find("#retryinterval").val(),
        	usePersistence:$(".header").find("#connopts").find("#usepersis").val(),
        	directory:$(".header").find("#connopts").find("#directory").val(),
        	topic:$(".header").find("#connopts").find("#topic").val(),
        	qos:$(".header").find("#connopts").find("#selQos").val(),
        	retained:$(".header").find("#connopts").find("#retained").val(),
        	data:$(".header").find("#connopts").find("#message-text").val()
    	},
        url:"api/broker/connect",
        success:function(res){// 请求响应成功后的回调函数。其中res是返回的数据
        	$(".header").find("#info").html(res);
        	if(res=="connect sunccess"){
        		$(".header").find("#connectbtn").attr("disabled", true);
        	}else{
        		$(".header").find("#disconnectbtn").attr("disabled", true);
        	}
        }// end success:function
    })
})

/**
 * 添加subscribe按钮的点击事件
 */
$(".content").delegate("#subscribeBtn","click",function(event){
	alert($(".content").find("#topic").val());
	if(topic!=null&&topic!=''&&topic.length>0){
		 $.ajax({
		        type:"GET",
		        data:{
		        	topic:$(".content").find("#topic").val()
		        },
		        url:"api/broker/subscribe",
		        success:function(res){// 请求响应成功后的回调函数。其中res是返回的数据
		        	alert(res);
		        }// end success:function
		    })
	}else
		alert("请输入topic...");
	
})


