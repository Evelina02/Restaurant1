$(document).ready(function(){
$('.plus').click(function () {
    var $input = $(this).parent().find('#count');
    $input.val(parseInt($input.val()) + 1);
    $input.change();

	var $price = $(this).parent().parent().parent().siblings().find('#price');

	var command = $(this).parent().find('#command').val();
    var count = $(this).parent().find('#count').val();
	var id = $(this).parent().find('#dishId').val();
	$.ajax({
		type: 'POST',
		data: {command: command, count:count, id:id},
		url:  'http://localhost:8080/MyWebsite/AjaxController',
		success : function(result){
			var obj = JSON.parse(result)
			
			$('#totalPrice').html(obj.totalPrice);
			$price.html(obj.dishPrice);

		}	
	});
});
});

$(document).ready(function(){
	$('.minus').click(function () {
		 var $input = $(this).parent().find('#count');
         var count = parseInt($input.val()) - 1;
         count = count < 1 ? 1 : count;
         $input.val(count);
         $input.change();
		
		var $price = $(this).parent().parent().parent().siblings().find('#price');

		var command = $(this).parent().find('#command').val();
	    var count = $(this).parent().find('#count').val();
		var id = $(this).parent().find('#dishId').val();
		$.ajax({
			type: 'POST',
			data: {command: command, count:count, id:id},
			url:  'http://localhost:8080/MyWebsite/AjaxController',
			success : function(result){
				var obj = JSON.parse(result)
				
				$('#totalPrice').html(obj.totalPrice);
				$price.html(obj.dishPrice);

			}	
		});
	});
	});



$(document).ready(function(){
	$('.change').click(function () {

		var dishIdValue = $(this).parent().siblings().find('#id').text();
		var dishPictureValue = $(this).parent().siblings().find('#dishPicture').attr('src');
		var dishPriceValue = $(this).parent().siblings().find('#dishPrice').text();
		var dishAmountValue = $(this).parent().siblings().find('#dishAmount').text();


			$('#dishId').text(dishIdValue);
			$('#picture').text(dishPictureValue);
			$('#price').text(dishPriceValue);
			$('#amount').text(dishAmountValue);
			
			
			$('#dishId').val(dishIdValue);
			$('#picture').val(dishPictureValue);
			$('#price').val(dishPriceValue);
			$('#amount').val(dishAmountValue);
			

	});
});




$(document).ready(function(){
	$('.addToBasket').click(function () {

		var dishId = $(this).parent().find('.dishId').val();
		var dishCategory = $(this).parent().find('.dishCategory').val();
		var command = $(this).parent().find('.command').val();
		var count = $(this).siblings().find('.count').val();

		$.ajax({
			type: 'POST',
			data: {command: command, dishId:dishId, dishCategory:dishCategory, count:count},
			url:  'http://localhost:8080/MyWebsite/AjaxController',
			success : function(result){
				
				var obj = JSON.parse(result)

				if(obj.dishAdded == "yes"){
					alert("ДОБАВЛЕНО !!!!!!")
				}
				if(obj.dishAdded == "no"){
					document.location.href = "http://localhost:8080/MyWebsite/jsp/authorization.jsp"
					alert("Пожалуйста, войдите, чтобы добавлять в корзину !!!")
				}
			}	
		});
	});
	});