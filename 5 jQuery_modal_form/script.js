$(function(){
    var myStr = "";
	$("#submitbutton").on("click", function(){
        $('#form1 input').attr('disabled','disabled');
        $("#popupform").css("display","initial");
    });

    $("#submitbutton2").on("click", function(){
        $('#form1 input').removeAttr('disabled');
        myStr = "";
        myStr += $("#inputOne2").val();
        myStr += $("#inputTwo2").val();
        myStr += $("#inputThree2").val();
        myStr += $("#inputFour2").val();
        currentVal = $("#inputOne").val();
        $("#inputOne").val(currentVal + myStr);

        $("#popupform").css("display","none");
    });

});