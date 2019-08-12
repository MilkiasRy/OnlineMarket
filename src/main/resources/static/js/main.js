"use strict";
(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);
$(function() {
   console.log()
    $('.cartItemQty').change(qtyChange);
   function qtyChange() {
       var qty = $(this).val();
       var cartItem_id=$(this).attr('id');
       // var item = {id: cartItem_id, quantity: qty};

       $.ajax({
           type: 'GET',
           url: '/buyer/shoppingCart/updateCartItem/'+qty+'/'+cartItem_id+'/',
           contentType: 'application/json',
           dataType: 'json',
           success: function(data){
               //append to category list
              console.log("result",data);

               $('#grandTotal').text(data)
           },
           error: function (xmlResponse) {
                       console.log(xmlResponse.responseJSON)
           }

       });
   }
    // function updatedItemSuccess(items) {
    //    console.log(items)
    //     console.log("Updated Item successfully!!!!");
    //
    // }
    // function error(err) {
    //     console.error(err);
    // }
})