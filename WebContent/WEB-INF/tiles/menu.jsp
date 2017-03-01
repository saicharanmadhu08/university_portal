<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="menuTabs" class="menuClass">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li id="newsFeedTab" role="presentation" class="active"><a href="#newsfeed" aria-controls="newsfeed" role="tab" data-toggle="tab">News Feed</a></li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a></li>
    <li id="messagesTab" role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages<span id="numOfMsgs" class="badge"></span></a></li>
    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/loggedout">Log out</a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="newsfeed"></div>
    <div role="tabpanel" class="tab-pane" id="profile"></div>
    <div role="tabpanel" class="tab-pane" id="messages"></div>
    <div role="tabpanel" class="tab-pane" id="settings"></div>
  </div>

</div>


<script type="text/javascript">

	$("#newsfeed").load("${pageContext.request.contextPath}/static/html/newsfeed.html");
	$("#profile").load("${pageContext.request.contextPath}/static/html/profile.html");
	$("#settings").load("${pageContext.request.contextPath}/static/html/settings.html");
	$("#messages").load("${pageContext.request.contextPath}/static/html/messages.html");

</script>

