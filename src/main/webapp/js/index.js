$(".header").delegate("#connectbtn","click",function(event){
	//alert(address);
	//当DOM元素加载完成后发送ajax请求，获取渲染界面的JSON数据
    $.ajax({
        type:"GET",
        data: {
        	address:$(".header").find("#address").val(),
        	port:$(".header").find("#port").val()
        },
        url:"api/broker/connect",
        success:function(res){//请求响应成功后的回调函数。其中res是返回的数据
        	$(".header").find("#info").html(res);
        	if(res=="connect sunccess"){
        		$(".header").find("#connectbtn").attr("disabled", true);
        	}else{
        		$(".header").find("#disconnectbtn").attr("disabled", true);
        	}
        	
        }//end success:function
    })
    
})
