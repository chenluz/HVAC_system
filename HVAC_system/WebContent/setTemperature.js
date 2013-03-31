 function setTemperature(temperature,id, invoker){
    //get the cell that be clicked
    var tdobj = $(invoker);
    tdobj.html("");
    console.log("temperature is: "+ temperature);
    if (tdobj.children('input').length == 0) { 
    	  // create a input without bolder
        var inputobj = $("<input name = 'temperature' type='text' maxlength='3' onkeypress='javascript:return isNumber(event);'  />").css("border-width", "0px")  
        .css("font-size", "15px").css("background-color", $('#schedule td').css("background-color")).css("color","white")
        .width("30px").height("18px").val(temperature).appendTo(invoker); 
       
        inputobj.trigger("focus").trigger("select"); //first trigger focus then trigger select?? 
    	$("<input type='hidden' name='id'/>").val(id).appendTo(invoker);  
   /*	 //register Enter and ESC for input text
	    inputobj.keyup(function (event) { 
	        var keynode = event.which; //get the value of key that be pressed 
	        //if press Enter
	        if (keynode == "13") { 
	        	alert(inputobj);
	            var cellvalue = parseInt($(this).val());
	            if((cellvalue < 0)||(cellvalue > 127))
	             {
	               alert("temperature should between 0 - 127");
	 
	               	inputobj.val(temperature);//reset value
	             }
	             else
	              {         
	            	 inputobj.val($(this).val());//save the new input value on browser 
	   
	                 inputobj.trigger("blur");
	              }
	                
	        }  
	        //When press ESC  
	        if (keynode == "27") { 
	        	inputobj.val(temperature);//reset the value 
	        	inputobj.trigger("blur"); 
	        }
	    });*/ //I use form, so don't need this
    }
}

 var hide = false;
 function showData(temperaturelist)
 {
	 hide =!hide;
	 if(hide == true){
		 for(var i=0; i<7;i++){
			 for(var j=1;j<25;j++){		
				 if ($('#schedule tr:eq('+i+') td :eq('+j+')').children('input').length > 0) {
					// remove all created input first
					 $('#schedule tr:eq('+i+') td :eq('+j+')').children('input').empty(); 
					// then table show data
					 $('#schedule tr:eq('+i+') td :eq('+j+')').html(temperaturelist[24*i+j-1]);
				    }
				 else{
					 $('#schedule tr:eq('+i+') td :eq('+j+')').html(temperaturelist[24*i+j-1]);
				 }
			 }
		 }
		 $('#data').html('Hide Data'); 
	 }
	 else{
	
		 for(var i=0; i<7;i++){
			 for(var j=1;j<25;j++){		
					 $('#schedule tr:eq('+i+') td :eq('+j+')').html('');  
			 }
		 }
		 $('#data').html('Show Data'); 
	 }
 }
 



