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
			
			
			
			var RoundedTotalPrice = Math.floor(obj.totalPrice * 100) / 100;
			$('#totalPrice').html(RoundedTotalPrice);
			var RoundedDishPrice = Math.floor(obj.dishPrice * 100) / 100;

			$price.html(RoundedDishPrice);
			
			$('#original_total_price').val(obj.totalPrice);
			
			
			
//			$('#totalPrice').html(obj.totalPrice);
//			$price.html(obj.dishPrice);
//			
//			$('#original_total_price').val(obj.totalPrice);

			
			var originalLoyaltyPoints = (document.getElementById('original_loyalty_points')).value;
			$('#loyalty_points').html(originalLoyaltyPoints);


			var count =document.getElementById('countOfPoints');
			count.value = 0;

			var usedPoints =document.getElementById('usedPoints');
			usedPoints.value = 0;
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
				
//				$('#totalPrice').html(obj.totalPrice);
//				$price.html(obj.dishPrice);
//
//				$('#original_total_price').val(obj.totalPrice);


				
				var RoundedTotalPrice = Math.floor(obj.totalPrice * 100) / 100;
				$('#totalPrice').html(RoundedTotalPrice);
				var RoundedDishPrice = Math.floor(obj.dishPrice * 100) / 100;

				$price.html(RoundedDishPrice);
				
				$('#original_total_price').val(obj.totalPrice);
				
				
				
				
				
				
				
				//сброс баллов
				var originalLoyaltyPoints = (document.getElementById('original_loyalty_points')).value;
				$('#loyalty_points').html(originalLoyaltyPoints);


				var count =document.getElementById('countOfPoints');
				count.value = 0;

				var usedPoints =document.getElementById('usedPoints');
				usedPoints.value = 0;
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





$(".multiple_select").mousedown(function(e) {
    if (e.target.tagName == "OPTION") 
    {
      return; //don't close dropdown if i select option
    }
    $(this).toggleClass('multiple_select_active'); //close dropdown if click inside <select> box
});
$(".multiple_select").on('blur', function(e) {
    $(this).removeClass('multiple_select_active'); //close dropdown if click outside <select>
});
	
$('.multiple_select option').mousedown(function(e) { //no ctrl to select multiple
    e.preventDefault(); 
    $(this).prop('selected', $(this).prop('selected') ? false : true); //set selected options on click
    $(this).parent().change(); //trigger change event
});

	
	$(".multiple_select").on('change', function() {
		var selected = $(this).val().toString(); 

		var dishId = $(this).parent().find('#dishId').val();
		var command = "refuse_of_ingredients";

      $.ajax({
			type: 'POST',
			data: {command: command, dishId:dishId, selected:selected},
			url:  'http://localhost:8080/MyWebsite/AjaxController',
			success : function(result){
				
				var obj = JSON.parse(result)

				if(obj.status == "yes"){
					//alert("Ok!")
				}else{
					//alert("No!")
				}
			}	
		});
	});
	
	
	
	$(document).ready(function(){
		$('.use_loyalty_points').click(function () {

			var loyalty_points_value = $(this).parent().find('#loyalty_points').text();

			var countVal = $(this).parent().find('#countOfPoints').val();
			var count = Number(countVal);
			var totalPriceElement =document.getElementById('totalPrice');
			var totalPrice = Number(totalPriceElement.innerHTML);

			if(count <= 0 || count > loyalty_points_value){
				alert("Введите корректное число баллов!!!")
			}else if(count > totalPrice){
				alert("Вы не можете использовать больше баллов, чем Вам заказ!!!")
			}
			else{
			
				var newLoyaltyPoints = parseFloat(loyalty_points_value) - count;
				
				var newTotalPrice =  parseFloat(totalPrice) - count;

				var $loyalty_points = $(this).parent().parent().find('#loyalty_points');
				
				
				$('#totalPrice').html(newTotalPrice);
				//остаток баллов
				
	
				var roundedNewLoyaltyPoints = Math.floor(newLoyaltyPoints * 100) / 100;
				$loyalty_points.html(roundedNewLoyaltyPoints);

				
				
				var usedPointsElement =document.getElementById('usedPoints');
				var usedPointsValue = usedPoints.value;
				var newUsedPoints = Number(count) + Number(usedPointsValue);				
				usedPointsElement.value = newUsedPoints;
			
				var count =document.getElementById('countOfPoints');
				count.value = 0;
				
			}
		});
});
	
	
	
	
	
	
/////////////////////////////////////////////////////////////	
	$(document).ready(function(){
		$('.reset_loyalty_points').click(function () {

			
			var originalLoyaltyPoints = (document.getElementById('original_loyalty_points')).value;
			$('#loyalty_points').html(originalLoyaltyPoints);

			var originalTotalPrice = (document.getElementById('original_total_price')).value;
			$('#totalPrice').html(originalTotalPrice);

			var count =document.getElementById('countOfPoints');
			count.value = 0;

			var usedPoints =document.getElementById('usedPoints');
			usedPoints.value = 0;
			
			
		});
});
/////////////////////////////////////////////////////////////	

	
	$(document).ready(function(){
		$('.reset_password').click(function () {
			
		var command = "reset_password";
		var login = $(this).parent().parent().parent().find('#user_login').val();

		if(login == "" || login == " "){
			alert("Вы не ввели свой логин!!!");
			
		}else
		
		if(confirm('Отправить новый пароль на Вашу почту?')) {
		      $.ajax({
					type: 'POST',
					data: {command: command, login: login},
					url:  'http://localhost:8080/MyWebsite/AjaxController',
					success : function(result){
						
						var obj = JSON.parse(result)

						if(obj.message == "no_such_login"){
							alert("Нет пользователя с таким логином");
						}else if(obj.message == "error"){
							alert("Ошибка при отправке сообщения. Попробуйте позже");
						}else if(obj.message == "ok"){
							alert("Ваш новый пароль отправлен на Вашу почту! Вы можете изменить его в разделе 'Профиль'!");
						}

					}	
				});
		    
		  } else { }
		});
	});
	

