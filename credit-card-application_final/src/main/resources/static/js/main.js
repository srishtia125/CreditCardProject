$(document).ready(function () {

    $("#credit-card-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        addCreditCard();

    });

});

function addCreditCard() {

    var creditCardRequest = {}
    creditCardRequest["cardNumber"] = $("#cardNumber").val();
    creditCardRequest["cardLimit"] = $("#cardLimit").val();
    creditCardRequest["name"] = $("#name").val();
    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/credit-card",
        data: JSON.stringify(creditCardRequest),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function(data,status,xhr) {

            console.log("SUCCESS");
            $('#errors').empty();
            setTimeout(function(){
                location.reload(true);
            },1000);

            $('#errors').append("<li class=\"list-group-item list-group-item-success\">Credit card added successfully.</li>")
        },
        error: function(e) {

            console.log("ERROR : ", e);
            $('#errors').empty();
            for (var i = 0; i < e.responseJSON.errorDetails.length; i++) {
                var errorMessageData = e.responseJSON.errorDetails[i];
                var errorData = {};
                errorData.message = errorMessageData.field + "----->" + errorMessageData.errorMessage;
                $('#errors').append("<li class=\"list-group-item list-group-item-danger\">" + errorMessageData.errorMessage + "</li>")
            }

            $("#btn-search").prop("disabled", false);

        }
    });

}