<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<div class="main">

    <div class="main-inner">

        <div class="container">

            <div class="row">

                <div class="span12">      		

                    <div class="widget ">

                        <div class="widget-header">
                            <i class="icon-user"></i>
                            <h3>Yeni RSS Besleyici Hesabı</h3>
                        </div> <!-- /widget-header -->

                        <div class="widget-content">

                            <div class="tabbable">
                                <ul class="nav nav-tabs">
                                    <li class="active" id="rssFeeder">
                                        <a href="#formcontrols" data-toggle="tab">RSS Kayıt Formu</a>
                                    </li>
                                </ul>

                                <br>

                                <div class="tab-content">
                                    <div id="formcontrols" class="tab-pane active">
                                        <form id="edit-profile" class="form-horizontal">
                                            <fieldset>
                                                <div class="control-group">											
                                                    <label class="control-label" for="exdomain">Örnek Domain</label>
                                                    <div class="controls">
                                                        <input type="text" class="span4 disabled" id="exdomain" value="http(s)//www.java4us.net || http(s)://java4us.net" disabled>
                                                        <p class="help-block">Domain adınız, kayıt edildikten sonra değiştirilemez.</p>
                                                    </div> <!-- /controls -->				
                                                </div> <!-- /control-group -->

                                                <div class="control-group">											
                                                    <label class="control-label" for="domain">Domain</label>
                                                    <div class="controls">
                                                        <input type="text" class="span4" id="domain"  placeholder="http(s)://www.java4us.net" value="">
                                                    </div> <!-- /controls -->				
                                                </div> <!-- /control-group -->

                                                <div class="control-group">											
                                                    <label class="control-label" for="email">E-Mail</label>
                                                    <div class="controls">
                                                        <input type="text" class="span4" id="email"  placeholder="info@java4us.net" value="">
                                                    </div> <!-- /controls -->				
                                                </div> <!-- /control-group -->
                                                
                                                <div class="control-group">											
                                                    <label class="control-label" for="javaRssUrl">Java RSS URL</label>
                                                    <div class="controls">
                                                        <input type="text" class="span4" id="javaRssUrl"  placeholder="http(s)://www.java4us.net/java.rss" value="">
                                                    </div> <!-- /controls -->				
                                                </div> <!-- /control-group -->
                                                
                                                <div class="control-group">											
                                                    <label class="control-label" for="androidRssUrl">Android RSS URL</label>
                                                    <div class="controls">
                                                        <input type="text" class="span4" id="androidRssUrl"  placeholder="http(s)://www.java4us.net/android.rss" value="">
                                                    </div> <!-- /controls -->				
                                                </div> <!-- /control-group -->

                                                <div class="control-group">											
                                                    <label class="control-label" for="firstname">Ad</label>
                                                    <div class="controls">
                                                        <input type="text" class="span4" id="firstname" placeholder="Turgay(Opsiyonel)" value="">
                                                    </div> <!-- /controls -->				
                                                </div> <!-- /control-group -->

                                                <div class="control-group">											
                                                    <label class="control-label" for="lastname">Soyad</label>
                                                    <div class="controls">
                                                        <input type="text" class="span4" id="lastname" placeholder="Can(Opsiyonel)" value="">
                                                    </div> <!-- /controls -->				
                                                </div> <!-- /control-group -->
                                            </fieldset>
                                        </form>
                                        <div id="feederResult"></div>   
                                        <div class="form-actions">
                                            <button type="submit" class="btn btn-primary" id="btnSave">Kaydet</button> 
                                            <button class="btn" id="btnClear">Temizle</button>
                                        </div> <!-- /form-actions -->
                                    </div>         
                                </div>
                            </div>
                        </div> <!-- /widget-content -->
                    </div> <!-- /widget -->
                </div> <!-- /span8 -->

            </div> <!-- /row -->

        </div> <!-- /container -->

    </div> <!-- /main-inner -->
</div> <!-- /main -->