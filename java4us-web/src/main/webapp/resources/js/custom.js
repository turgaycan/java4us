//--NEW SUBSCRIBER -- //
$('#newSubscriber').click(function () {
    var firstname = $("#subscriberFirstname").val();
    if (_.isEmpty(firstname)) {
        $("#subscriberResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> First Name field is not empty!</div>");
        return;
    }
    var lastname = $("#subscriberLastname").val();
    if (_.isEmpty(lastname)) {
        $("#subscriberResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Last Name field is not empty!</div>");
        return;
    }
    var email = $("#subscriberEmail").val();
    if (_.isEmpty(email)) {
        $("#subscriberResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Email field is not empty!</div>");
        return;
    }
    if (!validateEmail(email)) {
        $("#subscriberResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Email is not valid!</div>");
        return;
    }
    $.ajax({
        type: 'POST',
        dataType: 'json',
        data: {
            'firstname': firstname,
            'lastname': lastname,
            'email': email
        },
        beforeSend: function (x) {
            if (x && x.overrideMimeType) {
                x.overrideMimeType("application/json;charset=UTF-8");
            }
        },
        url: '/newsubscriber',
        success: function (data) {
            if (data.success === true) {
                $("#modelContent").css("display", "none");
                $("#newSubscriber").css("display", "none");
                $('#subscriberResult').html("<div class=\"alert alert-success\"><strong>" + email + "</strong> is successfuly saved!</div><br/><h1>Thank you for adding Java4Us.Net</h1>");
            } else if (data.isAlreadyExists === true) {
                $('#subscriberResult').html("<div class=\"alert alert-block\"><h4>1 - <strong>Email</strong> format is not proper</h4> <h4>2 - <strong>" + email + "</strong> was saved previously!</h4></div>");
            } else {
                $('#subscriberResult').html("<div class=\"alert\"><strong>Are you sure? All necessary fields is not empty!</strong></div>");
            }
        }
    });
});

//-- NEW FEEDER -- //

$('#btnSave').click(function () {
    var domain = $("#domain").val();
    if (_.isEmpty(domain)) {
        $("#feederResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Domain field is not empty!</div>");
        return;
    }
    if (!validateDomain(domain)) {
        $("#feederResult").html("<div class=\"alert\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Domain format is not proper!</div>");
        return;
    }
    var email = $("#email").val();
    if (_.isEmpty(email)) {
        $("#feederResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Email field is not empty!</div>");
        return;
    }
    if (!validateEmail(email)) {
        $("#feederResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Email is not valid!</div>");
        return;
    }
    var javaRssUrl = $("#javaRssUrl").val();
    var androidRssUrl = $("#androidRssUrl").val();
    if (_.isEmpty(javaRssUrl) && _.isEmpty(androidRssUrl)) {
        $("#feederResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong>Java/Android RSS URL is not empty!</div>");
        return;
    }
    if ((_.isEmpty(javaRssUrl) || !validateDomain(javaRssUrl)) && _.isEmpty(androidRssUrl)) {
        $("#feederResult").html("<div class=\"alert\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Java RSS URL format is not valid!</div>");
        return;
    }
    if ((_.isEmpty(androidRssUrl) || !validateDomain(androidRssUrl)) && _.isEmpty(javaRssUrl)) {
        $("#feederResult").html("<div class=\"alert\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Android RSS URL format is not valid!</div>");
        return;
    }
    var firstname = $("#firstname").val();
    var lastname = $("#lastname").val();
    $.ajax({
        type: 'POST',
        dataType: 'json',
        data: {
            'domain': domain,
            'email': email,
            'firstname': firstname,
            'lastname': lastname,
            'javaRssUrl': javaRssUrl,
            'androidRssUrl': androidRssUrl
        },
        beforeSend: function (x) {
            if (x && x.overrideMimeType) {
                x.overrideMimeType("application/json;charset=UTF-8");
            }
        },
        url: '/register/newfeeder',
        success: function (data) {
            if (data.success === true) {
                $('#feederResult').html("<div class=\"alert alert-success\"><strong>" + domain + "</strong> is successfuly saved!</div><br/><h1>Thank you for adding Java4Us.Net</h1>");
                $("#edit-profile")[0].reset();
                return;
            }
            if (data.domain === false) {
                $('#feederResult').html("<div class=\"alert\"><strong>" + domain + "</strong> is not empty!</div>");
                return;
            }
            if (data.email === false) {
                $('#feederResult').html("<div class=\"alert alert-block\"><h4>1 - <strong>Email</strong> is not proper format!</h4></div>");
                return;
            } else if (data.domainFormat === false) {
                $('#feederResult').html("<div class=\"alert alert-block\"><h4>1 - <strong>Domain</strong> is not proper <br/> or </h4> <h4>2 - <strong>" + domain + "</strong> was recorded previously!</h4></div>");
                return;
            } else if (data.rssUrls === false) {
                $("#feederResult").html("<div class=\"alert\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Java/Android RSS URL must not be empty!</div>");
                return;
            } else if (data.javaUrl === false) {
                $("#feederResult").html("<div class=\"alert\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Java RSS URL format is not proper!</div>");
                return;
            } else if (data.androidUrl === false) {
                $("#feederResult").html("<div class=\"alert\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Android RSS URL format is not proper!</div>");
                return;
            } else if (data.success === false) {
                $('#feederResult').html("<div class=\"alert\"><strong>Error!<br/>Please send error email to info@java4us.net</strong></div>");
            }
        }
    });
});

//-- FEED Form -- //

//--CONTACT FORM -- //
$('#btnContactSend').click(function () {
    var email = $("#email").val();
    if (_.isEmpty(email)) {
        $("#contactResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Email field is not empty!</div>");
        return;
    }
    if (!validateEmail(email)) {
        $("#contactResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Email field is not valid!</div>");
        return;
    }
    var content = $("#content").val();
    if (_.isEmpty(content)) {
        $("#contactResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Content field is not empty!</div>");
        return;
    }
    if (content.length > 4000) {
        $("#contactResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Content length is not higher than 4000 character!</div>");
        return;
    }
    var url;
    var type = $("#type").val();
    if (type === "feedback") {
        url = '/feedback/new.json';
    } else {
        url = '/question/new.json';
    }
    var postData = {
        'email': email,
        'content': content
    };
    $.ajax({
        type: 'POST',
        dataType: 'json',
        data: postData,
        beforeSend: function (x) {
            if (x && x.overrideMimeType) {
                x.overrideMimeType("application/json;charset=UTF-8");
            }
        },
        url: url,
        success: function (data) {
            if (data.success === true) {
                $('#contactResult').html("<div class=\"alert alert-success\"><strong>Your message</strong> is successfully sent!</div>");
                $('#contact-form')[0].reset();
            } else {
                $('#contactResult').html("<div class=\"alert\"><strong>Are you sure? All necessary fields is not empty or valid!</strong></div>");
            }
        }
    });
});

//--CHANGE PASSWORD FORM -- //
$('#changePassword').click(function () {
    var oldPassword = $("#oldPassword").val();
    if (_.isEmpty(oldPassword)) {
        $("#passwordResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> Old Password field is not empty!</div>");
        return;
    }
    var newPassword = $("#newPassword").val();
    if (_.isEmpty(newPassword)) {
        $("#passwordResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Warning!</strong> New Password field is not empty!</div>");
        return;
    }
    var postData = {
        'oldPassword': oldPassword,
        'newPassword': newPassword
    };
    $.ajax({
        type: 'POST',
        dataType: 'json',
        data: postData,
        beforeSend: function (x) {
            if (x && x.overrideMimeType) {
                x.overrideMimeType("application/json;charset=UTF-8");
            }
        },
        url: '/feeders/change/password',
        success: function (data) {
            if (data.success === true) {
                $('#passwordResult').html("<div class=\"alert alert-success\"><strong>Your password</strong> is successfully changed!</div>");
                $("#oldPassword").val("");
                $("#newPassword").val("");
                $("#modelContent").hide();
            } else {
                $("#oldPassword").val("");
                $("#newPassword").val("");
                $('#passwordResult').html("<div class=\"alert\"><strong>Are you sure? All necessary fields is not empty or valid!</strong></div>");
            }
        }
    });
});

//-- Clear Feeder Registration Form --//
$('#btnClear').click(function () {
    $('#edit-profile')[0].reset();
});

//-- Clear Contact Form --//
$('#btnContactClear').click(function () {
    $('#contact-form')[0].reset();
});
//-- URL CHECK -- //
function validateDomain(domain) {
    return domain.match(/(http|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/);
}

//-- EMAIL CHECK --//
function validateEmail(email) {
    return email.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
}