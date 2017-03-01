<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
#profilePostsDivider{
	display: block;
    height: 1px;
    border: 0;
    border-top: 1px solid #ccc;
    margin: 1em 0;
    padding: 0;
}
.username{
	color:blue;
	font-weight:bold;
	cursor: pointer;
}
.likes,.shares{
	color:blue;
	font-weight: 600;
	cursor: pointer;
}
#header{
	margin-left: 30px;
}
#ProfileUsername{
	color:black;
	font-weight:400;
}
#ProfileUsername:hover{
	 font-weight:bold;
}

#searchUsers{
	height: 600px;
	overflow: scroll; 
}
#displayUsers{
 list-style-type: none;
}
.eachUser{
	color:blue;
}
.eachUser:HOVER{
	font-weight:bold;
	cursor: pointer;
}
.eachUser:HOVER {
	background-color: infobackground; 
}
#chatBoxMessanger{
	display: none;
	height: 500px;
}
#chatWindow{
	height: 400px;
	background-color: white;
	overflow-y: scroll;
	overflow-x: hidden;
}
#sendText{
	position:absolute;
}
textarea{
	resize:none;
	width:700px;
}

#closeChatBox{
	float: right;
	cursor: pointer;
}
#chatBoxMessangerWell{
display: none;
}
.commentsInProfile{
	color: blue;
	font-weight: 600;
	cursor: pointer;
}
#profileSharedPost , #profileComments{
	width: 500px;
	height: 250px;
	display: none;
	overflow: scroll;
}

#profileCloseSharedPost, #profileCloseComments {
	float: right;
	cursor: pointer;
}

</style>
</head>
<body>
<div id="header">
  <h1 id="ProfileFullname"></h1><nav id="ProfileUsername"></nav>  
  
</div>
<hr>
<hr>

<div class="container-fluid">
  <div class="row">
    <div  class="col-sm-3" >
    	<div class="well well-lg">
    		<div >
    			<div class="list-group">
  					<a href="#" class="list-group-item" id="openHome">Home</a>
  					<a href="#" class="list-group-item" id="chatBox">Chat</a>
 					<a href="#" class="list-group-item" id="logoutFromProfile">Logout</a>
				</div>
    		</div>
    	</div>
    </div>
    <div class="col-sm-9" >
    	<div class="container-fluid">
    		<div class="well well-lg" id="profileSharedPost">
						<span id="profileCloseSharedPost" class="glyphicon glyphicon-remove"
							aria-hidden="true"></span>
						<hr>
						<div id="profileShowsSharedPost"></div>
					</div>
					<div class="well well-lg" id="profileComments">
						<span id="profileCloseComments" class="glyphicon glyphicon-remove"
							aria-hidden="true"></span>
						<hr>
						<div id="profileShowsComments">
							<div id="profileDisplayComments"></div>
							<hr>
							<textarea class="form-control" id="profilePostComment"
								placeholder="Add Comment"></textarea>
							<input id="profiledoComment" class="btn btn-default" type="submit"
								value="Comment">
						</div>
					</div>
					<div id="profileAllPosts">
    		
    		<div id="profilePostsWell" class="well well-lg">
    			<div id="profilePosts">
    		
    			</div>
    		</div>
    	</div>
    	<div class="well well-lg" id="chatBoxMessangerWell" >
    		<span id="closeChatBox" class="glyphicon glyphicon-remove" aria-hidden="true"></span>
    		<hr>
    		<div id="chatWindow">
    			
    		</div>
    		<br>
    		<div id="inputForChatBox">
    			<textarea id="textInput" placeholder = "Enter your message here..." rows="" cols="5"></textarea>
    			<button id="sendText"type="button" class="btn btn-success">Send</button><br>
    			<input checked type = "checkbox" id = "enter" />
				<label>Send on Enter</label>
    		</div>
    	</div>
    </div>
  </div>
</div>
</div>

<script type="text/javascript">

var timer;
var chatboxtimer;
function startTimer() {
	timer = window.setInterval(onLoad, 10000);
	chatboxtimer = window.setInterval(getChatTexts(profilename),100);
}
function stopTimer() {
	window.clearInterval(timer);
	window.clearInterval(chatboxtimer);
}


$("#profileCloseSharedPost").click(function() {
	$("#profileSharedPost").hide();
	$("#profileAllPosts").show();
});

$("#profileCloseComments").click(function() {
	$("#profileComments").hide();
	$("#profileAllPosts").show();
});

function getUserDetails() {
	$.getJSON("http://localhost:8080/university-portal/getProfileDetails?profilename="+profilename, showUserDetails);
}
function showUserDetails(data){
	var profileDetails = data.profileDetails[0];
	var fullname = profileDetails.fullname;
	var username = profileDetails.username;
	
	$("#ProfileFullname").html(fullname);
	$("#ProfileUsername").html("@"+username+"<br>");
}

function onLoad() {
	startTimer();
	getUserDetails();
	getProfilePosts();

}

function getProfilePosts(){
	$.getJSON("http://localhost:8080/university-portal/getProfileUserPosts?profilename="+profilename, showProfilePosts);
}



function showProfilePosts(data) {

	$("#profilePosts").html("");
	for (var i = 0; i < data.profilePosts.length; i++) {
		var profilePosts = data.profilePosts[i];
		var br = document.createElement("br");
		var br1 = document.createElement("br");
		var br2 = document.createElement("br");
		var br3 = document.createElement("br");
		var hr = document.createElement("hr");
		var likesID = "id" + profilePosts.postID;
		hr.setAttribute("id", "profilePostsDivider");

		var profilePostsDiv = document.createElement("div");
		profilePostsDiv.setAttribute("class", "profilePosts");

		var usernameSpan = document.createElement("span");
		usernameSpan.setAttribute("class", "username");
		usernameSpan.appendChild(document
				.createTextNode(profilePosts.username));

		if ((profilePosts.text)
				.includes("http://localhost:8080/university-portal/posts?postid=")) {
			var textSpan = document.createElement("span");
			textSpan.setAttribute("class", "sharedtextLink");
			textSpan.setAttribute("id", "textProfile"
					+ profilePosts.postID);
			textSpan.setAttribute("data-pid", profilePosts.postID);
			var showPost = document.createElement('div');
			showPost.innerHTML = ("Shared <br>" + profilePosts.text)
					.bold();
			var postID = profilePosts.postID;
			textSpan.appendChild(showPost);

			profilePostsDiv.appendChild(usernameSpan);
			profilePostsDiv.appendChild(br);
			profilePostsDiv.appendChild(textSpan);
			profilePostsDiv.appendChild(br1);
			profilePostsDiv.appendChild(br2);
			profilePostsDiv.appendChild(hr);

			$("#profilePosts").append(profilePostsDiv);

		} else {
			var textSpan = document.createElement("span");
			textSpan.setAttribute("class", "text");
			textSpan.appendChild(document
					.createTextNode(profilePosts.text));

			var likesSpan = document.createElement("span");
			likesSpan.setAttribute("class", "likes");
			likesSpan.setAttribute("id", "likesID");
			likesSpan.setAttribute("data-pid", profilePosts.postID);
			likesSpan.setAttribute("data-likes", profilePosts.likes);
			likesSpan.appendChild(document.createTextNode("Likes: "
					+ getLikesInfo(profilePosts.postID)));

			var sharesSpan = document.createElement("span");
			sharesSpan.setAttribute("class", "shares");
			sharesSpan.setAttribute("data-pid", profilePosts.postID);
			sharesSpan.appendChild(document
					.createTextNode(" ||  Share Post   ||"));
			
			var commentsSpan = document.createElement("span");
			commentsSpan.setAttribute("class", "commentsInProfile");
			commentsSpan.setAttribute("data-pid", profilePosts.postID);
			commentsSpan.appendChild(document.createTextNode(" Comment"));


			profilePostsDiv.appendChild(usernameSpan);
			profilePostsDiv.appendChild(br);
			profilePostsDiv.appendChild(textSpan);
			profilePostsDiv.appendChild(br1);
			profilePostsDiv.appendChild(br2);
			profilePostsDiv.appendChild(likesSpan);
			profilePostsDiv.appendChild(sharesSpan);
			profilePostsDiv.appendChild(commentsSpan);
			profilePostsDiv.appendChild(hr);

			$("#profilePosts").append(profilePostsDiv);

		}

		updateLike = $(".likes")
				.click(
						function() {
							var postID = $(this).attr("data-pid");
							var link = "http://localhost:8080/university-portal/updateLikes";
							$.ajax({
								type : 'POST',
								contentType : 'application/json',
								url : link,
								data : JSON.stringify({
									"postID" : postID
								}),
								success : function(data) {
									getLikesInfo();
								},

							});

						});

		$(".shares")
				.click(
						function() {
							var postID = $(this).attr("data-pid");
							var link = "http://localhost:8080/university-portal/sharePost";
							$.ajax({
								type : 'POST',
								contentType : 'application/json',
								url : link,
								data : JSON.stringify({
									"postid" : postID
								}),

							});
						});

		$(".sharedtextLink").click(
				function() {
					$("#profileAllPosts").hide();
					$("#profileSharedPost").show();
					var postID = $(this).attr("data-pid");
					$.getJSON(
							"http://localhost:8080/university-portal/posts?postid="
									+ postID, showSharedPost);
				});
		
		$(".commentsInProfile").click(function(){
			$("#profileAllPosts").hide();
			$("#profileComments").show();
			commentPostId = $(this).attr("data-pid");
			
			$.getJSON("http://localhost:8080/university-portal/getComments?postid="+commentPostId,
					showCommentsInProfile);
			
			
		});


	}

}

function showSharedPost(data) {
	$("#profileShowsSharedPost").html("<b>"+
			data.actualUser+"<b>" + "<br><br>" + data.actualText);

}

function showCommentsInProfile(data){
	$("#profileDisplayComments").html("");
	if(data.commentForPost==0){
		$("#profileDisplayComments").html("");
		
	}else{
		for(var i=0;i<data.postComments.length;i++){
			var eachComment = 	data.postComments[i];
			var username= eachComment.username;
			var commentText = eachComment.text;
			console.log(username+" "+commentText);
			$("#profileDisplayComments").append("<b>"+username+"</b>"+" said  "+"<b>"+commentText+"</b><br>");
			
		}
		
		
		
	}
	
}

$("#chatBox").click(function (){
	getChatTexts(profilename);
	$("#profilePostsWell").hide();
	$("#chatBoxMessangerWell").show();
	 $('#chatWindow').animate({scrollTop: $('#chatWindow')[0].scrollHeight}, 700);

});

$("#closeChatBox").click(function (){
	$("#profilePostsWell").show();
	$("#chatBoxMessangerWell").hide();
});
$("#openHome").click(function (){
	var link = "http://localhost:8080/university-portal/";
	window.location=link;
});

$("#logoutFromProfile").click(function (){
	var link = "http://localhost:8080/university-portal/loggedout";
	window.location=link;
});
var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};
var profilename = getUrlParameter('username');
$(document).ready(onLoad);

$("#textInput").keypress(function(event){
	if ( event.which == 13){
		if ( $("#enter").prop("checked") ){
			$("#sendText").click();
				event.preventDefault();
				}
		}

	});
$("#sendText").click(function (){
	var chatText=$("#textInput").val();
	sendChatText(profilename,chatText);
	$("#textInput").val("");
	$('#chatWindow').animate({scrollTop: $('#chatWindow')[0].scrollHeight}, 700);
});

function sendChatText(profilename,chatText){
	
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: "http://localhost:8080/university-portal/sendChatText",
		data: JSON.stringify({"profilename": profilename,"chatText":chatText}),
		success: function (msg) 
        { getChatTexts(profilename) },
		error:function (msg){
			alert("Sorry message sent failed")
		}
	});
	
}

function getChatTexts(profilename){
	$.getJSON("http://localhost:8080/university-portal/getChatTexts?profilename="+profilename, showChatMessages);
}
function showChatMessages(data){
	$("#chatWindow").html("");
	for(var i=0; i<data.showAllChats.length; i++){
		var chatTexts = data.showAllChats[i];
		var br = document.createElement("br");
		var br1 = document.createElement("br");
		var br2 = document.createElement("br");
		var br3 = document.createElement("br");
		var hr =  document.createElement("hr");
		
		var chatTextsDiv = document.createElement("div");
		chatTextsDiv.setAttribute("class", "chatTexts");
		
		var senderSpan = document.createElement("span");
		senderSpan.setAttribute("class", "sender");
		senderSpan.appendChild(document.createTextNode(chatTexts.sender));
		
		var textSpan = document.createElement("span");
		textSpan.setAttribute("class", "text");
		textSpan.appendChild(document.createTextNode(" :: " + chatTexts.text));
		
		chatTextsDiv.appendChild(senderSpan);
		chatTextsDiv.appendChild(textSpan);
		chatTextsDiv.appendChild(br);
		chatTextsDiv.appendChild(br1);		
		
		$("#chatWindow").append(chatTextsDiv);
		

	}
	for(var i=0; i<data.showAllChats.length; i++){
		
			 $(".sender").css("color", "blue");
		
	}
	$('#chatWindow').animate({scrollTop: $('#chatWindow')[0].scrollHeight}, 700);
}

function getLikesInfo(postID) {

	var numOfLikes = 0;

	$
			.ajax({
				async : false,
				url : "http://localhost:8080/university-portal/getLikesInfo?postid="
						+ postID,
				success : function(data) {
					numOfLikes = data.profileLikes.length;

				}
			});

	return numOfLikes;

}

var commentPostId;	
$("#profiledoComment").click(function(){
	var commentText = $("#profilePostComment").val();
	var postid =   commentPostId;
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : "http://localhost:8080/university-portal/doComment",
		data : JSON.stringify({
			"commentText" : commentText,
			"postid": postid
		}),
		success : function(msg) {
			$("#profilePostComment").val("");
		}
	});
	
});

function showSharedPost(data) {
	$("#profileShowsSharedPost").html("<b>"+
			data.actualUser+"<b>" + "<br><br>" + data.actualText);

}

</script>