function check() {
    if($("#loginName").val().trim().length==0) {
		$("#msg").text("用户名不能为空!").show();
		return;
	}
	if($("#_password").val().length==0) {
		$("#msg").text("密码不能为空!").show();
		return;
	}
	//TODO: 进行加密处理
	$("#password").val($("#_password").val());
	$("#loginform").submit();
}

$("html").off().on("keydown",function(event){  
    if(event.keyCode==13){  
    	check();
    }  
});
    
$(document).ready(function(){
	$('#to-recover').click(function(){
		$("#loginform").slideUp();
		$("#recoverform").fadeIn();
	});
	$('#to-login').click(function(){
		$("#recoverform").hide();
		$("#loginform").fadeIn();
	});
	
	
	$('#to-login').click(function(){
	
	});
    
    if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
        $('input[placeholder]').each(function(){ 
	        var input = $(this);       
	        $(input).val(input.attr('placeholder'));
	        $(input).focus(function(){
	             if (input.val() == input.attr('placeholder')) {
	                 input.val('');
	             }
	        });
	        $(input).blur(function(){
	            if (input.val() == '' || input.val() == input.attr('placeholder')) {
	                input.val(input.attr('placeholder'));
	            }
	        });
	    });
    }
});