<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" >
<head>
<title>Java4Us.Net || Weekly Newsletter Feed messages</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
    body{margin:0;mso-line-height-rule:exactly;padding:0;min-width:100%}table{border-collapse:collapse;border-spacing:0}td{padding:0;vertical-align:top}.border,.spacer{font-size:1px;line-height:1px}.spacer{width:100%}img{border:0;-ms-interpolation-mode:bicubic}.image{font-size:0;Margin-bottom:24px}.image img{display:block}.logo{mso-line-height-rule:at-least}.logo img{display:block}strong{font-weight:700}h1,h2,h3,li,ol,p,ul{Margin-top:0}li,ol,ul{padding-left:0}blockquote{Margin-top:0;Margin-right:0;Margin-bottom:0;padding-right:0}.column-top{font-size:32px;line-height:32px}.column-bottom{font-size:8px;line-height:8px}.column{text-align:left}.contents{width:100%}.padded{padding-left:32px;padding-right:32px}.wrapper{display:table;table-layout:fixed;width:100%;min-width:620px;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}table.wrapper{table-layout:fixed}.one-col,.three-col,.two-col{width:600px}.centered{Margin-left:auto;Margin-right:auto}.two-col .image{Margin-bottom:23px}.two-col .column-bottom{font-size:9px;line-height:9px}.two-col .column{width:300px}.three-col .image{Margin-bottom:21px}.three-col .column-bottom{font-size:11px;line-height:11px}.three-col .column{width:200px}.three-col .first .padded{padding-left:32px;padding-right:16px}.three-col .second .padded{padding-left:24px;padding-right:24px}.three-col .third .padded{padding-left:16px;padding-right:32px}@media only screen and (min-width:0){.wrapper{text-rendering:optimizeLegibility}}@media only screen and (max-width:620px){[class=wrapper]{min-width:318px!important;width:100%!important}[class=wrapper] .one-col,[class=wrapper] .three-col,[class=wrapper] .two-col{width:318px!important}[class=wrapper] .column,[class=wrapper] .gutter{display:block;float:left;width:318px!important}[class=wrapper] .padded{padding-left:32px!important;padding-right:32px!important}[class=wrapper] .block{display:block!important}[class=wrapper] .hide{display:none!important}[class=wrapper] .image{margin-bottom:24px!important}[class=wrapper] .image img{height:auto!important;width:100%!important}}.wrapper h1{font-weight:700}.wrapper h2{font-style:italic;font-weight:400}.wrapper h3{font-weight:400}.one-col blockquote,.three-col blockquote,.two-col blockquote{font-style:italic}.one-col-feature h1{font-weight:400}.one-col-feature h2{font-style:normal;font-weight:700}.one-col-feature h3{font-style:italic}td.border{width:1px}tr.border{background-color:#e9e9e9;height:1px}tr.border td{line-height:1px}.one-col,.one-col-feature,.three-col,.two-col{background-color:#fff;font-size:14px}.footer,.header,.one-col,.one-col-feature,.preheader,.three-col,.two-col{Margin-left:auto;Margin-right:auto}.preheader table{width:602px}.preheader .title,.preheader .webversion{padding-top:10px;padding-bottom:12px;font-size:12px;line-height:21px}.preheader .title{text-align:left}.preheader .webversion{text-align:right;width:300px}.header{width:602px}.header .logo{font-size:26px;font-weight:700;letter-spacing:-.02em;line-height:32px;padding:32px 0}.header .logo a{text-decoration:none}.header .logo .logo-center{text-align:center}.header .logo .logo-center img{Margin-left:auto;Margin-right:auto}.gmail{width:650px;min-width:650px}.gmail td{font-size:1px;line-height:1px}.wrapper a{text-decoration:underline;transition:all .2s}.wrapper h1{font-size:36px;Margin-bottom:18px}.wrapper h2{font-size:26px;line-height:32px;Margin-bottom:20px}.wrapper h3{font-size:18px;line-height:22px;Margin-bottom:16px}.wrapper h1 a,.wrapper h2 a,.wrapper h3 a{text-decoration:none}.one-col blockquote,.three-col blockquote,.two-col blockquote{font-size:14px;border-left:2px solid #e9e9e9;Margin-left:0;padding-left:16px}table.divider{width:100%}.divider .inner{padding-bottom:24px}.divider table{background-color:#e9e9e9;font-size:2px;line-height:2px;width:60px}.wrapper .gray{background-color:#f7f7f7}.wrapper .gray blockquote{border-left-color:#ddd}.wrapper .gray .divider table{background-color:#ddd}.image-frame{padding:8px}.image-background{display:inline-block}.btn{Margin-bottom:24px;padding:2px}.btn a{border:1px solid #fff;display:inline-block;font-size:13px;font-weight:700;line-height:15px;outline-style:solid;outline-width:2px;padding:10px 30px;text-align:center;text-decoration:none!important}.one-col .column table:nth-last-child(2) td h1:last-child,.one-col .column table:nth-last-child(2) td h2:last-child,.one-col .column table:nth-last-child(2) td h3:last-child,.one-col .column table:nth-last-child(2) td ol:last-child,.one-col .column table:nth-last-child(2) td p:last-child,.one-col .column table:nth-last-child(2) td ul:last-child{Margin-bottom:24px}.one-col ol,.one-col p,.one-col ul{font-size:16px}.one-col ol,.one-col ul{Margin-left:18px}.two-col .column table:nth-last-child(2) td h1:last-child,.two-col .column table:nth-last-child(2) td h2:last-child,.two-col .column table:nth-last-child(2) td h3:last-child,.two-col .column table:nth-last-child(2) td ol:last-child,.two-col .column table:nth-last-child(2) td p:last-child,.two-col .column table:nth-last-child(2) td ul:last-child{Margin-bottom:23px}.two-col .image-frame{padding:6px}.two-col h1{font-size:26px;Margin-bottom:16px}.two-col h2{font-size:20px;Margin-bottom:18px}.two-col h3{font-size:16px;Margin-bottom:14px}.two-col ol,.two-col p,.two-col ul{font-size:14px}.two-col ol,.two-col ul{Margin-left:16px}.two-col li{padding-left:5px}.two-col .divider .inner{padding-bottom:23px}.two-col .btn{Margin-bottom:23px}.two-col blockquote{padding-left:16px}.three-col .column table:nth-last-child(2) td h1:last-child,.three-col .column table:nth-last-child(2) td h2:last-child,.three-col .column table:nth-last-child(2) td h3:last-child,.three-col .column table:nth-last-child(2) td ol:last-child,.three-col .column table:nth-last-child(2) td p:last-child,.three-col .column table:nth-last-child(2) td ul:last-child{Margin-bottom:21px}.three-col .image-frame{padding:4px}.three-col h1{font-size:20px;Margin-bottom:12px}.three-col h2{font-size:16px;Margin-bottom:14px}.three-col h3{font-size:14px;Margin-bottom:10px}.three-col ol,.three-col p,.three-col ul{font-size:12px}.three-col ol,.three-col ul{Margin-left:14px}.three-col li{padding-left:6px}.three-col .divider .inner{padding-bottom:21px}.three-col .btn{Margin-bottom:21px}.three-col .btn a{font-size:12px;line-height:14px;padding:8px 19px}.three-col blockquote{padding-left:16px}.one-col-feature .column-top{font-size:36px;line-height:36px}.one-col-feature .column-bottom{font-size:4px;line-height:4px}.one-col-feature .column{text-align:center;width:600px}.one-col-feature .column table:nth-last-child(2) td h1:last-child,.one-col-feature .column table:nth-last-child(2) td h2:last-child,.one-col-feature .column table:nth-last-child(2) td h3:last-child,.one-col-feature .column table:nth-last-child(2) td ol:last-child,.one-col-feature .column table:nth-last-child(2) td p:last-child,.one-col-feature .column table:nth-last-child(2) td ul:last-child,.one-col-feature .image{Margin-bottom:32px}.one-col-feature h1,.one-col-feature h2,.one-col-feature h3{text-align:center}.one-col-feature h1{font-size:52px;Margin-bottom:22px}.one-col-feature h2{font-size:42px;Margin-bottom:20px}.one-col-feature h3{font-size:32px;line-height:42px;Margin-bottom:20px}.one-col-feature ol,.one-col-feature p,.one-col-feature ul{font-size:21px;line-height:32px;Margin-bottom:32px}.one-col-feature ol a,.one-col-feature p a,.one-col-feature ul a{text-decoration:none}.one-col-feature p{text-align:center}.one-col-feature ol,.one-col-feature ul{Margin-left:40px;text-align:left}.one-col-feature li{padding-left:3px}.one-col-feature .btn{Margin-bottom:32px;text-align:center}.one-col-feature .divider .inner{padding-bottom:32px}.one-col-feature blockquote{border-bottom:2px solid #e9e9e9;border-left-color:#fff;border-left-width:0;border-left-style:none;border-top:2px solid #e9e9e9;Margin-bottom:32px;Margin-left:0;padding-bottom:42px;padding-left:0;padding-top:42px;position:relative}.one-col-feature blockquote:after,.one-col-feature blockquote:before{background:-moz-linear-gradient(left,#fff 25%,#e9e9e9 25%,#e9e9e9 75%,#fff 75%);background:-webkit-gradient(linear,left top,right top,color-stop(25%,#fff),color-stop(25%,#e9e9e9),color-stop(75%,#e9e9e9),color-stop(75%,#fff));background:-webkit-linear-gradient(left,#fff 25%,#e9e9e9 25%,#e9e9e9 75%,#fff 75%);background:-o-linear-gradient(left,#fff 25%,#e9e9e9 25%,#e9e9e9 75%,#fff 75%);background:-ms-linear-gradient(left,#fff 25%,#e9e9e9 25%,#e9e9e9 75%,#fff 75%);background:linear-gradient(to right,#fff 25%,#e9e9e9 25%,#e9e9e9 75%,#fff 75%);content:'';display:block;height:2px;left:0;outline:#fff solid 1px;position:absolute;right:0}.one-col-feature blockquote:before{top:-2px}.one-col-feature blockquote:after{bottom:-2px}.one-col-feature blockquote ol,.one-col-feature blockquote p,.one-col-feature blockquote ul{font-size:42px;Margin-bottom:48px}.one-col-feature blockquote ol:last-child,.one-col-feature blockquote p:last-child,.one-col-feature blockquote ul:last-child{Margin-bottom:0!important}.footer{width:602px}.footer .padded{font-size:12px;line-height:20px}.social{padding-top:32px;padding-bottom:22px}.social img{display:block}.social .divider{font-family:sans-serif;font-size:10px;line-height:21px;text-align:center;padding-left:14px;padding-right:14px}.social .social-text{height:21px;vertical-align:middle!important;font-size:10px;font-weight:700;text-decoration:none;text-transform:uppercase}.social .social-text a{text-decoration:none}.address{width:250px}.address .padded{text-align:left;padding-left:0;padding-right:10px}.subscription{width:350px}.subscription .padded{text-align:right;padding-right:0;padding-left:10px}.address,.subscription{padding-top:32px;padding-bottom:64px}.address a,.subscription a{font-weight:700;text-decoration:none}.address table,.subscription table{width:100%}@media only screen and (max-width:651px){.gmail{display:none!important}}@media only screen and (max-width:620px){[class=wrapper] .one-col .column:last-child table:nth-last-child(2) td h1:last-child,[class=wrapper] .one-col .column:last-child table:nth-last-child(2) td h2:last-child,[class=wrapper] .one-col .column:last-child table:nth-last-child(2) td h3:last-child,[class=wrapper] .one-col .column:last-child table:nth-last-child(2) td ol:last-child,[class=wrapper] .one-col .column:last-child table:nth-last-child(2) td p:last-child,[class=wrapper] .one-col .column:last-child table:nth-last-child(2) td ul:last-child,[class=wrapper] .one-col-feature .column:last-child table:nth-last-child(2) td h1:last-child,[class=wrapper] .one-col-feature .column:last-child table:nth-last-child(2) td h2:last-child,[class=wrapper] .one-col-feature .column:last-child table:nth-last-child(2) td h3:last-child,[class=wrapper] .one-col-feature .column:last-child table:nth-last-child(2) td ol:last-child,[class=wrapper] .one-col-feature .column:last-child table:nth-last-child(2) td p:last-child,[class=wrapper] .one-col-feature .column:last-child table:nth-last-child(2) td ul:last-child,[class=wrapper] .three-col .column:last-child table:nth-last-child(2) td h1:last-child,[class=wrapper] .three-col .column:last-child table:nth-last-child(2) td h2:last-child,[class=wrapper] .three-col .column:last-child table:nth-last-child(2) td h3:last-child,[class=wrapper] .three-col .column:last-child table:nth-last-child(2) td ol:last-child,[class=wrapper] .three-col .column:last-child table:nth-last-child(2) td p:last-child,[class=wrapper] .three-col .column:last-child table:nth-last-child(2) td ul:last-child,[class=wrapper] .two-col .column:last-child table:nth-last-child(2) td h1:last-child,[class=wrapper] .two-col .column:last-child table:nth-last-child(2) td h2:last-child,[class=wrapper] .two-col .column:last-child table:nth-last-child(2) td h3:last-child,[class=wrapper] .two-col .column:last-child table:nth-last-child(2) td ol:last-child,[class=wrapper] .two-col .column:last-child table:nth-last-child(2) td p:last-child,[class=wrapper] .two-col .column:last-child table:nth-last-child(2) td ul:last-child{Margin-bottom:24px!important}[class=wrapper] .address,[class=wrapper] .subscription{display:block;float:left;width:318px!important;text-align:center!important}[class=wrapper] .address{padding-bottom:0!important}[class=wrapper] .subscription{padding-top:0!important}[class=wrapper] h1{font-size:36px!important;line-height:42px!important;Margin-bottom:18px!important}[class=wrapper] h2{font-size:26px!important;line-height:32px!important;Margin-bottom:20px!important}[class=wrapper] h3{font-size:18px!important;line-height:22px!important;Margin-bottom:16px!important}[class=wrapper] ol,[class=wrapper] p,[class=wrapper] ul{font-size:16px!important;line-height:24px!important;Margin-bottom:24px!important}[class=wrapper] ol,[class=wrapper] ul{Margin-left:18px!important}[class=wrapper] li{padding-left:2px!important}[class=wrapper] blockquote{padding-left:16px!important}[class=wrapper] .two-col .column:nth-child(n+3){border-top:1px solid #e9e9e9}[class=wrapper] .btn{margin-bottom:24px!important}[class=wrapper] .btn a{display:block!important;font-size:13px!important;font-weight:700!important;line-height:15px!important;padding:10px 30px!important}[class=wrapper] .column-bottom{font-size:8px!important;line-height:8px!important}[class=wrapper] .first .column-bottom,[class=wrapper] .second .column-top,[class=wrapper] .third .column-top,[class=wrapper] .three-col .second .column-bottom{display:none}[class=wrapper] .image-frame{padding:4px!important}[class=wrapper] .header .logo{font-size:26px!important;line-height:32px!important;padding-left:10px!important;padding-right:10px!important}[class=wrapper] .header .logo img{display:inline-block!important;max-width:280px!important;height:auto!important}[class=wrapper] .footer,[class=wrapper] .header,[class=wrapper] .webversion,[class=wrapper] table.border{width:320px!important}[class=wrapper] .header .logo a,[class=wrapper] .preheader .webversion{text-align:center!important}[class=wrapper] .border td,[class=wrapper] .preheader table{width:318px!important}[class=wrapper] .border td.border{width:1px!important}[class=wrapper] .image .border td{width:auto!important}[class=wrapper] .title{display:none}[class=wrapper] .footer .padded{text-align:center!important}[class=wrapper] .footer .subscription .padded{padding-top:20px!important}[class=wrapper] .footer .social-link{display:block!important}[class=wrapper] .footer .social-link table{margin:0 auto 10px!important}[class=wrapper] .footer .divider{display:none!important}[class=wrapper] .one-col-feature .btn,[class=wrapper] .one-col-feature .image{margin-bottom:28px!important}[class=wrapper] .one-col-feature .divider .inner{padding-bottom:28px!important}[class=wrapper] .one-col-feature h1{font-size:42px!important;line-height:48px!important;margin-bottom:20px!important}[class=wrapper] .one-col-feature h2{font-size:32px!important;line-height:36px!important;margin-bottom:18px!important}[class=wrapper] .one-col-feature h3{font-size:26px!important;line-height:32px!important;margin-bottom:20px!important}[class=wrapper] .one-col-feature ol,[class=wrapper] .one-col-feature p,[class=wrapper] .one-col-feature ul{font-size:20px!important;line-height:28px!important;margin-bottom:28px!important}[class=wrapper] .one-col-feature blockquote{font-size:18px!important;line-height:26px!important;margin-bottom:28px!important;padding-bottom:26px!important;padding-left:0!important;padding-top:26px!important}[class=wrapper] .one-col-feature blockquote ol,[class=wrapper] .one-col-feature blockquote p,[class=wrapper] .one-col-feature blockquote ul{font-size:26px!important;line-height:32px!important}[class=wrapper] .one-col-feature blockquote ol:last-child,[class=wrapper] .one-col-feature blockquote p:last-child,[class=wrapper] .one-col-feature blockquote ul:last-child{margin-bottom:0!important}[class=wrapper] .one-col-feature .column table:last-of-type h1:last-child,[class=wrapper] .one-col-feature .column table:last-of-type h2:last-child,[class=wrapper] .one-col-feature .column table:last-of-type h3:last-child{margin-bottom:28px!important}}@media only screen and (max-width:320px){[class=wrapper] td.border{display:none}[class=wrapper] .footer,[class=wrapper] .header,[class=wrapper] .webversion,[class=wrapper] table.border{width:318px!important}}.emb-editor-canvas,.wrapper,</style> <meta name="robots" content="noindex,nofollow"/> <meta property="og:title" content="Java4Us.Net"/> <link href="$rootUrl/resources/css/mail/mailsocial.min.css" media="screen,projection" rel="stylesheet" type="text/css"/> </head> <body style="margin: 0;mso-line-height-rule: exactly;padding: 0;min-width: 100%;background-color: #fbfbfb"> <style type="text/css"> body{background-color:#fbfbfb}.border{background-color:#e9e9e9}h1{color:#565656}.wrapper h1{font-family:sans-serif}@media only screen and (min-width:0){.wrapper h1{font-family:Avenir,sans-serif!important}}.one-col h1{line-height:42px}.two-col h1{line-height:32px}.three-col h1{line-height:26px}.wrapper .one-col-feature h1{line-height:58px}@media only screen and (max-width:620px){h1{line-height:42px!important}}h2{color:#555}.wrapper h2{font-family:Georgia,serif}.one-col h2{line-height:32px}.two-col h2{line-height:26px}.three-col h2{line-height:22px}.wrapper .one-col-feature h2{line-height:52px}@media only screen and (max-width:620px){h2{line-height:32px!important}}h3{color:#555}.wrapper h3{font-family:Georgia,serif}.one-col h3{line-height:26px}.two-col h3{line-height:22px}.three-col h3{line-height:20px}.wrapper .one-col-feature h3{line-height:42px}@media only screen and (max-width:620px){h3{line-height:26px!important}}ol,p,ul{color:#565656}.wrapper ol,.wrapper p,.wrapper ul{font-family:Georgia,serif}.one-col ol,.one-col p,.one-col ul{line-height:25px;Margin-bottom:25px}.two-col ol,.two-col p,.two-col ul{line-height:23px;Margin-bottom:23px}.three-col ol,.three-col p,.three-col ul{line-height:21px;Margin-bottom:21px}.wrapper .one-col-feature ol,.wrapper .one-col-feature p,.wrapper .one-col-feature ul{line-height:32px}.one-col-feature blockquote ol,.one-col-feature blockquote p,.one-col-feature blockquote ul{line-height:50px}@media only screen and (max-width:620px){ol,p,ul{line-height:25px!important;Margin-bottom:25px!important}}.wrapper a{color:#41637e}.wrapper a:hover{color:#30495c!important}.wrapper .logo{color:#41637e;font-family:sans-serif}@media only screen and (min-width:0){.wrapper .logo{font-family:Avenir,sans-serif!important}}.wrapper .logo a{color:#41637e}.wrapper .logo a:hover{color:#41637e!important}.wrapper .one-col-feature ol a,.wrapper .one-col-feature p a,.wrapper .one-col-feature ul a{border-bottom:1px solid #41637e}.wrapper .one-col-feature ol a:hover,.wrapper .one-col-feature p a:hover,.wrapper .one-col-feature ul a:hover{color:#30495c!important;border-bottom:1px solid #30495c!important}.wrapper .btn a{font-family:Georgia,serif;background-color:#29cc41;color:#000!important;outline-color:#29cc41;text-shadow:0 1px 0 #3ed154}.wrapper .btn a:hover{background-color:#3ed154!important;color:#000!important;outline-color:#3ed154!important}.footer .padded,.preheader .title,.preheader .webversion{color:#999;font-family:Georgia,serif}.footer .padded a,.preheader .title a,.preheader .webversion a{color:#999}.footer .padded a:hover,.preheader .title a:hover,.preheader .webversion a:hover{color:#737373!important}.footer .social .divider{color:#e9e9e9}.footer .social .social-text,.footer .social a{color:#999}.wrapper .footer .social .social-text,.wrapper .footer .social a{font-family:Georgia,serif}.footer .social .social-text,.footer .social a{letter-spacing:.05em}.footer .social .social-text:hover,.footer .social a:hover{color:#737373!important}.image .border{background-color:#c8c8c8}.image-frame{background-color:#dadada}.image-background{background-color:#f7f7f7}
</style>
<meta name="robots" content="noindex,nofollow"/>
<meta property="og:title" content="Java4Us.Net"/>
<link href="<c:url value="/resources/css/mail/mailsocial.min.css" />" media="screen,projection" rel="stylesheet"
      type="text/css"/>
</head>

<body style="margin: 0;mso-line-height-rule: exactly;padding: 0;min-width: 100%;background-color: #fbfbfb">
<style type="text/css">
    .emb-editor-canvas,.wrapper,body{background-color:#fbfbfb}.border{background-color:#e9e9e9}h1{color:#565656}.wrapper h1{font-family:sans-serif}@media only screen and (min-width:0){.wrapper h1{font-family:Avenir,sans-serif!important}}.one-col h1{line-height:42px}.two-col h1{line-height:32px}.three-col h1{line-height:26px}.wrapper .one-col-feature h1{line-height:58px}@media only screen and (max-width:620px){h1{line-height:42px!important}}h2{color:#555}.wrapper h2{font-family:Georgia,serif}.one-col h2{line-height:32px}.two-col h2{line-height:26px}.three-col h2{line-height:22px}.wrapper .one-col-feature h2{line-height:52px}@media only screen and (max-width:620px){h2{line-height:32px!important}}h3{color:#555}.wrapper h3{font-family:Georgia,serif}.one-col h3{line-height:26px}.two-col h3{line-height:22px}.three-col h3{line-height:20px}.wrapper .one-col-feature h3{line-height:42px}@media only screen and (max-width:620px){h3{line-height:26px!important}}ol,p,ul{color:#565656}.wrapper ol,.wrapper p,.wrapper ul{font-family:Georgia,serif}.one-col ol,.one-col p,.one-col ul{line-height:25px;Margin-bottom:25px}.two-col ol,.two-col p,.two-col ul{line-height:23px;Margin-bottom:23px}.three-col ol,.three-col p,.three-col ul{line-height:21px;Margin-bottom:21px}.wrapper .one-col-feature ol,.wrapper .one-col-feature p,.wrapper .one-col-feature ul{line-height:32px}.one-col-feature blockquote ol,.one-col-feature blockquote p,.one-col-feature blockquote ul{line-height:50px}@media only screen and (max-width:620px){ol,p,ul{line-height:25px!important;Margin-bottom:25px!important}}.wrapper a{color:#41637e}.wrapper a:hover{color:#30495c!important}.wrapper .logo{color:#41637e;font-family:sans-serif}@media only screen and (min-width:0){.wrapper .logo{font-family:Avenir,sans-serif!important}}.wrapper .logo a{color:#41637e}.wrapper .logo a:hover{color:#41637e!important}.wrapper .one-col-feature ol a,.wrapper .one-col-feature p a,.wrapper .one-col-feature ul a{border-bottom:1px solid #41637e}.wrapper .one-col-feature ol a:hover,.wrapper .one-col-feature p a:hover,.wrapper .one-col-feature ul a:hover{color:#30495c!important;border-bottom:1px solid #30495c!important}.wrapper .btn a{font-family:Georgia,serif;background-color:#29cc41;color:#000!important;outline-color:#29cc41;text-shadow:0 1px 0 #3ed154}.wrapper .btn a:hover{background-color:#3ed154!important;color:#000!important;outline-color:#3ed154!important}.footer .padded,.preheader .title,.preheader .webversion{color:#999;font-family:Georgia,serif}.footer .padded a,.preheader .title a,.preheader .webversion a{color:#999}.footer .padded a:hover,.preheader .title a:hover,.preheader .webversion a:hover{color:#737373!important}.footer .social .divider{color:#e9e9e9}.footer .social .social-text,.footer .social a{color:#999}.wrapper .footer .social .social-text,.wrapper .footer .social a{font-family:Georgia,serif}.footer .social .social-text,.footer .social a{letter-spacing:.05em}.footer .social .social-text:hover,.footer .social a:hover{color:#737373!important}.image .border{background-color:#c8c8c8}.image-frame{background-color:#dadada}.image-background{background-color:#f7f7f7}
</style>
<center class="wrapper"
        style="display: table;table-layout: fixed;width: 100%;min-width: 620px;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;background-color: #fbfbfb">
<table class="gmail" style="border-collapse: collapse;border-spacing: 0;width: 650px;min-width: 650px">
    <tbody>
    <tr>
        <td style="padding: 0;vertical-align: top;font-size: 1px;line-height: 1px">&nbsp;</td>
    </tr>
    </tbody>
</table>
<table class="header centered"
       style="border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 602px">
    <tbody>
    <tr>
        <td class="border"
            style="padding: 0;vertical-align: top;font-size: 1px;line-height: 1px;background-color: #e9e9e9;width: 1px">
            &nbsp;</td>
    </tr>
    <tr>
        <td class="logo"
            style="padding: 32px 0;vertical-align: top;mso-line-height-rule: at-least;font-size: 26px;font-weight: 700;letter-spacing: -0.02em;line-height: 32px;color: #41637e;font-family: sans-serif">
            <div class="logo-center" style="text-align: center" align="center" id="emb-email-header"><a
                    style="text-decoration: none;transition: all .2s;color: #41637e"
                    href="${rootUrl}"><img
                    style="border: 0;-ms-interpolation-mode: bicubic;display: block;Margin-left: auto;Margin-right: auto;max-width: 200px"
                    src="<c:url value="/resources/img/j4uslogo.jpg" />"
                    alt="Java4Us.Net | The biggest Java and Android Technologies RSS Feeds website in the world"
                    width="200" height="45"></a></div>
        </td>
    </tr>
    </tbody>
</table>
<table class="border"
       style="border-collapse: collapse;border-spacing: 0;font-size: 1px;line-height: 1px;background-color: #e9e9e9;Margin-left: auto;Margin-right: auto"
       width="602">
    <tr style="padding: 32px 0;vertical-align: top;mso-line-height-rule: at-least;font-size: 26px;font-weight: 700;letter-spacing: -0.02em;line-height: 32px;color: #41637e;font-family: sans-serif">
        <td class="logo" style="font-size: 20px;">
            <div><label></label> <label style="float: right; padding-right: 5px;">${currentDate}</label></div>
        </td>
    </tr>
</table>

<table class="border"
       style="border-collapse: collapse;border-spacing: 0;font-size: 1px;line-height: 1px;background-color: #e9e9e9;Margin-left: auto;Margin-right: auto"
       width="602">
    <tbody>
    <tr>
        <td style="padding: 0;vertical-align: top">&#8203;</td>
    </tr>
    </tbody>
</table>

<table class="centered" style="border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto">
    <tbody>
    <tr>
        <td class="border"
            style="padding: 0;vertical-align: top;font-size: 1px;line-height: 1px;background-color: #e9e9e9;width: 1px">
            &#8203;</td>
        <td style="padding: 0;vertical-align: top">
            <table class="one-col-feature"
                   style="border-collapse: collapse;border-spacing: 0;background-color: #ffffff;font-size: 14px;Margin-left: auto;Margin-right: auto">
                <tbody>
                <tr>
                    <td class="column" style="padding: 0;vertical-align: top;text-align: center;width: 600px">
                        <div>
                            <div class="column-top" style="font-size: 36px;line-height: 36px">&nbsp;</div>
                        </div>
                        <table class="contents" style="border-collapse: collapse;border-spacing: 0;width: 100%">
                            <tbody>
                            <tr>
                                <td class="padded"
                                    style="padding: 0;vertical-align: top;padding-left: 32px;padding-right: 32px">

                                    <p style="Margin-top: 0;color: #565656;font-family: Georgia,serif;font-size: 21px;line-height: 32px;Margin-bottom: 32px;text-align: center">
                                        <a style="text-decoration: none;transition: all .2s;color: #41637e;border-bottom: 1px solid #41637e"
                                           data-emb-href-display="${rootUrl}"
                                           href="${rootUrl}">Java4Us.Net</a>,
                                        Weekly Newsletter : Recommended Feed Messages..</p>

                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <c:forEach var="feedMessage" items="${feedMessages}">
                            <table class="contents" style="border-collapse: collapse;border-spacing: 0;width: 100%">
                                <tbody>
                                <tr>
                                    <td class="padded"
                                        style="padding: 0;vertical-align: top;padding-left: 32px;padding-right: 32px">

                                        <p style="Margin-top: 0;color: #565656;font-family: Georgia,serif;font-size: 21px;line-height: 32px;Margin-bottom: 32px;text-align: left">
                                            <strong style="font-weight: bold"><a href="${feedMessage.link}" target="_blank">${feedMessage.title}</a></strong>
                                        </p>

                                    </td>
                                </tr>
                                </tbody>
                            </table>

                            <div class="column-bottom" style="font-size: 4px;line-height: 4px">&nbsp;</div>
                        </c:forEach>
                    </td>
                </tr>
                </tbody>
            </table>
        </td>
        <td class="border"
            style="padding: 0;vertical-align: top;font-size: 1px;line-height: 1px;background-color: #e9e9e9;width: 1px">
            &#8203;</td>
    </tr>
    </tbody>
</table>

<table class="border"
       style="border-collapse: collapse;border-spacing: 0;font-size: 1px;line-height: 1px;background-color: #e9e9e9;Margin-left: auto;Margin-right: auto"
       width="602">
    <tbody>
    <tr>
        <td style="padding: 0;vertical-align: top">&#8203;</td>
    </tr>
    </tbody>
</table>

<div class="spacer" style="font-size: 1px;line-height: 32px;width: 100%">&nbsp;</div>
<table class="footer centered"
       style="border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 602px">
    <tbody>
    <tr>
        <td class="social" style="padding: 0;vertical-align: top;padding-top: 32px;padding-bottom: 22px"
            align="center">
            <table style="border-collapse: collapse;border-spacing: 0">
                <tbody>
                <tr>
                    <td class="social-link" style="padding: 0;vertical-align: top">
                        <table style="border-collapse: collapse;border-spacing: 0">
                            <tbody>
                            <tr>
                                <td style="padding: 0;vertical-align: top">
                                    <a style="text-decoration: none;transition: all .2s;color: #999;letter-spacing: 0.05em;font-family: Georgia,serif"
                                       href="http://www.facebook.com/java4usnet"
                                       likeurl="http://www.facebook.com/java4usnet" rel="cs_facebox">
                                        <img style="border: 0;-ms-interpolation-mode: bicubic;display: block"
                                             src="<c:url value="/resources/img/signin/facebook-dark.png" />"
                                             width="26" height="21">
                                    </a>
                                </td>
                                <td class="social-text"
                                    style="padding: 0;vertical-align: middle !important;height: 21px;font-size: 10px;font-weight: bold;text-decoration: none;text-transform: uppercase;color: #999;letter-spacing: 0.05em;font-family: Georgia,serif">
                                    <a style="text-decoration: none;transition: all .2s;color: #999;letter-spacing: 0.05em;font-family: Georgia,serif"
                                       href="http://www.facebook.com/java4usnet"
                                       likeurl="http://www.facebook.com/java4usnet" rel="cs_facebox">
                                        Like
                                    </a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                    <td class="divider"
                        style="padding: 0;vertical-align: top;font-family: sans-serif;font-size: 10px;line-height: 21px;text-align: center;padding-left: 14px;padding-right: 14px;color: #e9e9e9">
                        <img style="border: 0;-ms-interpolation-mode: bicubic;display: block"
                             src="<c:url value="/resources/img/signin/diamond.png" />" width="5"
                             height="21" alt="">
                    </td>
                    <td class="social-link" style="padding: 0;vertical-align: top">
                        <table style="border-collapse: collapse;border-spacing: 0">
                            <tbody>
                            <tr>
                                <td style="padding: 0;vertical-align: top">
                                    <a style="text-decoration: none;transition: all .2s;color: #999;letter-spacing: 0.05em;font-family: Georgia,serif"
                                       href="https://www.twitter.com/java4usnet">
                                        <img style="border: 0;-ms-interpolation-mode: bicubic;display: block"
                                             src="<c:url value="/resources/img/signin/twitter-dark.png" />"
                                             width="26" height="21">
                                    </a>
                                </td>
                                <td class="social-text"
                                    style="padding: 0;vertical-align: middle !important;height: 21px;font-size: 10px;font-weight: bold;text-decoration: none;text-transform: uppercase;color: #999;letter-spacing: 0.05em;font-family: Georgia,serif">
                                    <a style="text-decoration: none;transition: all .2s;color: #999;letter-spacing: 0.05em;font-family: Georgia,serif"
                                       href="https://www.twitter.com/java4usnet">
                                        Tweet
                                    </a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>

                    <td class="social-link" style="padding: 0;vertical-align: top">

                    </td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td class="border"
            style="padding: 0;vertical-align: top;font-size: 1px;line-height: 1px;background-color: #e9e9e9;width: 1px">
            &nbsp;</td>
    </tr>
    <tr>
        <td style="padding: 0;vertical-align: top">
            <table style="border-collapse: collapse;border-spacing: 0">
                <tbody>
                <tr>
                    <td class="address"
                        style="padding: 0;vertical-align: top;width: 250px;padding-top: 32px;padding-bottom: 64px">
                        <table class="contents" style="border-collapse: collapse;border-spacing: 0;width: 100%">
                            <tbody>
                            <tr>
                                <td class="padded"
                                    style="padding: 0;vertical-align: top;padding-left: 0;padding-right: 10px;text-align: left;font-size: 12px;line-height: 20px;color: #999;font-family: Georgia,serif">
                                    <div>www.Java4Us.Net</div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                    <td class="subscription"
                        style="padding: 0;vertical-align: top;width: 350px;padding-top: 32px;padding-bottom: 64px">
                        <table class="contents" style="border-collapse: collapse;border-spacing: 0;width: 100%">
                            <tbody>
                            <tr>
                                <td class="padded"
                                    style="padding: 0;vertical-align: top;padding-left: 10px;padding-right: 0;font-size: 12px;line-height: 20px;color: #999;font-family: Georgia,serif;text-align: right">

                                    <div>
                          <span class="block">
                            <span>
                              <a style="font-weight: bold;text-decoration: none;transition: all .2s;color: #999"
                                 href="${rootUrl}/about">
                                  Preferences
                              </a>
                              <span class="hide">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
                                <a style="font-weight: bold;text-decoration: none;transition: all .2s;color: #999"
                                   href="${rootUrl}/">
                                    Home Page
                                </a>
                            </span>
                          </span>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
    </tbody>
</table>
</center>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.7.2.min.js" />"></script>
<div id="fb-root"></div>
<script src="<c:url value="/resources/js/social/fb.js" />"></script>

</body>
</html>

