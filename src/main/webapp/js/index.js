/**
 * 项目启动后加载连接配置的默认属性
 */
//加载完js文件后自动执行的函数
$(function(){
	alert("tt");
	$.ajax({
        type:"GET",
		url:"api/broker/getDefaultConnProp",
		success:function(res){// 请求响应成功后的回调函数。其中res是返回的数据
		 	alert(res);
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
	alert($(".header").find("#connopts").find("#usepersis").val());
	// 当DOM元素加载完成后发送ajax请求，获取渲染界面的JSON数据
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
        	qos:$(".header").find("#connopts").find("#qos").val(),
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

$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	// alert(e.target);
 
 // alert(e.relatedTarget)
})


