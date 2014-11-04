//--NEW SUBSCRIBER -- //
$('#newSubscriber').click(function() {
    var firstname = $("#subscriberFirstname").val();
    if (_.isEmpty(firstname)) {
        $("#subscriberResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Uyarı!</strong> Ad alanını boş bırakmayınız!</div>");
        return;
    }
    var lastname = $("#subscriberLastname").val();
    if (_.isEmpty(lastname)) {
        $("#subscriberResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Uyarı!</strong> Soyad alanını boş bırakmayınız!</div>");
        return;
    }
    var email = $("#subscriberEmail").val();
    if (_.isEmpty(email)) {
        $("#subscriberResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Uyarı!</strong> E-Posta alanını boş bırakmayınız!</div>");
        return;
    }
    $.ajax({
        type: 'GET',
        dataType: 'json',
        data: { 
            'firstname': firstname, 
            'lastname': lastname,
            'email': email
        },
        beforeSend: function(x) {
            if (x && x.overrideMimeType) {
                x.overrideMimeType("application/json;charset=UTF-8");
            }
        },
        url: '/java4us-web/newsubscriber',
        success: function(data) {
            if (data.success === true) {
                $("#modelContent").css("display", "none");
                $("#newSubscriber").css("display", "none");
                $('#subscriberResult').html("<div class=\"alert alert-success\"><strong>" + email + "</strong> başarılı bir şekilde kayıt edildi!</div><br/><h1>Thank you for adding Java4Us.Net</h1>");
            } else if (data.domainFormat === true) {
                $('#subscriberResult').html("<div class=\"alert alert-block\"><h4>1 - <strong>Email</strong> Formatı hatalı </h4> <h4>2 - <strong>" + email + "</strong> daha önceden kaydedilmiştir!</h4></div>");
            } else {
                $('#subscriberResult').html("<div class=\"alert\"><strong>Tüm alanları doldurduğunuzdan emin olunuz!</strong></div>");
            }
        }
    });
});

//-- NEW FEEDER -- //

$('#btnSave').click(function() {
    var domain = $("#domain").val();
    if (_.isEmpty(domain)) {
        $("#feederResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Uyarı!</strong> Domain alanını boş bırakmayınız!</div>");
        return;
    }
    if (!checkDomain(domain)) {
        $("#feederResult").html("<div class=\"alert\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Uyarı!</strong> Domain formatı hatalı!</div>");
        return;
    }
    var email = $("#email").val();
    if (_.isEmpty(email)) {
        $("#feederResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Uyarı!</strong> E-Posta alanını boş bırakmayınız!</div>");
        return;
    }
    var javaRssUrl = $("#javaRssUrl").val();
    var androidRssUrl = $("#androidRssUrl").val();
    if (_.isEmpty(javaRssUrl) && _.isEmpty(androidRssUrl)) {
        $("#feederResult").html("<div class=\"alert alert-info\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Uyarı!</strong> RSS URL alanlarından en az birini doldurmalısınız!</div>");
        return;
    }
    if ((_.isEmpty(javaRssUrl) || !checkDomain(javaRssUrl)) && _.isEmpty(androidRssUrl)) {
        $("#feederResult").html("<div class=\"alert\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Uyarı!</strong> Java RSS URL formatı hatalı!</div>");
        return;
    }
    if ((_.isEmpty(androidRssUrl) || !checkDomain(androidRssUrl)) && _.isEmpty(javaRssUrl)) {
        $("#feederResult").html("<div class=\"alert\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Uyarı!</strong> Android RSS URL formatı hatalı!</div>");
        return;
    }
    var firstname = $("#firstname").val();
    var lastname = $("#lastname").val();
    $.ajax({
        type: 'GET',
        dataType: 'json',
        data: { 
            'domain': domain, 
            'email': email,
            'firstname': firstname,
            'lastname': lastname,
            'javaRssUrl': javaRssUrl,
            'androidRssUrl': androidRssUrl
        },
        beforeSend: function(x) {
            if (x && x.overrideMimeType) {
                x.overrideMimeType("application/json;charset=UTF-8");
            }
        },
        url: '/java4us-web/kayit/newfeeder',
        success: function(data) {
            if (data.success === true) {
                $('#feederResult').html("<div class=\"alert alert-success\"><strong>" + domain + "</strong> başarılı bir şekilde kayıt edildi!</div><br/><h1>Thank you for adding Java4Us.Net</h1>");
                $("#edit-profile")[0].reset();
                return;
            }
            if (data.domain === false) {
                $('#feederResult').html("<div class=\"alert\"><strong>" + domain + "</strong> alanını boş bırakmayınız!</div>");
                return;
            }
            if (data.email === false) {
                $('#feederResult').html("<div class=\"alert alert-block\"><h4>1 - <strong>Email</strong> Formatı hatalı!</h4></div>");
                return;
            } else if (data.domainFormat === false) {
                $('#feederResult').html("<div class=\"alert alert-block\"><h4>1 - <strong>Domain</strong> Formatı hatalı <br/>yada </h4> <h4>2 - <strong>" + domain + "</strong> daha önceden kaydedilmiştir!</h4></div>");
                return;
            } else if (data.rssUrls === false) {
                $("#feederResult").html("<div class=\"alert\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Uyarı!</strong> RSS URL alanlarından en az birini doldurmalısınız!</div>");
                return;
            } else if (data.javaUrl === false) {
                $("#feederResult").html("<div class=\"alert\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Uyarı!</strong> Java RSS URL formatı hatalı!</div>");
                return;
            } else if (data.androidUrl === false) {
                $("#feederResult").html("<div class=\"alert\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button><strong>Uyarı!</strong> Android RSS URL formatı hatalı!</div>");
                return;
            } else if (data.success === false) {
                $('#feederResult').html("<div class=\"alert\"><strong>Sistemsel bir hata oluştu!<br/>Lütfen info@java4us.net'e mail ile bildiriniz..</strong></div>");
            }
        }
    });
});

//-- FEED Form -- //

//-- Clear Form --//
$('#btnClear').click(function() {
    $('#jscontrols')[0].reset();
});

//-- URL CHECK -- //
function checkDomain(domain) {
    return domain.match(/(http|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/);
}