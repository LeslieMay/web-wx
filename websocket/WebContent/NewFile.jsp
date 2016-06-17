<%@ page language="java" contentType="text/html; charset=utf-8"
    %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

	<head>
		<meta charset="UTF-8">
		<title>微信 for Web</title>
		<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="css/webpage.css" rel="stylesheet" type="text/css">
		<link href="css/buttons.css" rel="stylesheet" type="text/css">
		<link href="css/jquery-ui.min.css">
		<link href="css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
		<script src="js/jquery-ui.min.js"></script>
		<script type="text/javascript">
			"use strict";
			var name = '${user.name}';
			var fromname = '${user.username}';
			var Chat = {};
			var to = null; //发送消息的目标
			var toname = null;
			Chat.socket = null;
			var mydiv = new Array(100); //存放聊天记录
			var N = 0; //计数
			function openchat(name) { //点击好友开始聊天

				var M = 0;
				$('#console').css('display', 'none');
				var Ishave = false; //是否已经存在该用户聊天
				for (var i = 0; i < N; i++) {
					document.getElementById(mydiv[i]).style.display = 'none';
				}
				for (var i = 0; i < N; i++) {
					if (mydiv[i] == name) {
						M = i;
						Ishave = true; //存在
					}
				}
				if (Ishave) { //如果用户聊天记录已经存在，打开装记录的div

					document.getElementById(mydiv[M]).style.display = 'block';
				} else { //不存在 ，新建一个以该用户名字为ID的div存放记录

					mydiv[N] = name;
					N++;
					var diiiv = "<div id='" + name + "'class='diiv'></div>";
					$('#console').after(diiiv);

				}
				$('#console').html('');
				to = name;
				$('#right_header').html(to);

			}
			Chat.connect = (function(host) { //连接websocket
				if ('WebSocket' in window) {
					Chat.socket = new WebSocket(host);
				} else if ('MozWebSocket' in window) {
					Chat.socket = new MozWebSocket(host);
				} else {
					Console.log('Error: WebSocket is not supported by this browser.');
					return;
				}

				Chat.socket.onopen = function() {

					document.getElementById('chat').onkeydown = function(event) {
						if (event.keyCode == 13) {
							Chat.sendMessage(); //发送消息去服务端
						}
					};
				};

				Chat.socket.onclose = function() {
					document.getElementById('chat').onkeydown = null;

				};

				Chat.socket.onmessage = function(message) { //接受消息并显示

					var msgg = message.data;
					var json = eval("(" + msgg + ")");

					if (json.name != name) {
						to = json.name;

						openchat(to);
						Console.log(json.msg, 1);
					} else {

						Console.log(json.msg, 0);
					}

				};
			});

			Chat.initialize = function() {
				if (window.location.protocol == 'http:') {
					Chat.connect('ws://' + window.location.host + '/websocket/chat?username=' + name);
				} else {
					Chat.connect('wss://' + window.location.host + '/websocket/chat?username=' + name);
				}
			};

			function onsend() {
				var messag = document.getElementById('chat').value;
				var msg = { //消息数据，json
					from: name,
					to: to,
					msg: messag,
					date: new Date(),
				}
				var message = JSON.stringify(msg);
				if (message != '') {
					Chat.socket.send(message);
					document.getElementById('chat').value = '';
				}
			}
			Chat.sendMessage = (function() {
				var messag = document.getElementById('chat').value;
				var msg = {
					from: name,
					to: to,
					msg: messag,
					date: new Date(),
				}
				var message = JSON.stringify(msg);
				if (message != '') {
					Chat.socket.send(message);
					document.getElementById('chat').value = '';
				}
			});

			var Console = {};

			Console.log = (function(message, who) {
				var console = document.getElementById(to);
				if (who == 0) {
					var p = document.createElement('div');
					p.className = "msgcontself";
					var img = document.createElement("img");
					img.src = "img/webwxgeticon.jpg";
					img.className = "msgimgself";
					p.appendChild(img);
					var self = document.createElement('div');
					self.className = "selfsenddiv";
					var arrowself = document.createElement('div');
					arrowself.className = "arrowself";
					var sendself = document.createElement('div');
					sendself.className = "sendself";
					sendself.innerHTML = message;
					self.appendChild(arrowself);
					self.appendChild(sendself);
					p.appendChild(self);
				}
				if (who == 1) {
					var p = document.createElement('div');
					p.className = "msgcontother";
					var img = document.createElement("img");
					img.src = "img/webwxgeticon.jpg";
					img.className = "msgimgother";
					p.appendChild(img);
					var self = document.createElement('div');
					self.className = "othersenddiv";
					var arrowself = document.createElement('div');
					arrowself.className = "arrowother";
					var sendself = document.createElement('div');
					sendself.className = "sendother";
					sendself.innerHTML = message;
					self.appendChild(arrowself);
					self.appendChild(sendself);
					p.appendChild(self);
				}
				console.appendChild(p);
				while (console.childNodes.length > 25) {
					console.removeChild(console.firstChild);
				}
				console.scrollTop = console.scrollHeight;
			});

			Chat.initialize();

			document.addEventListener("DOMContentLoaded", function() {
				// Remove elements with "noscript" class - <noscript> is not allowed in XHTML
				var noscripts = document.getElementsByClassName("noscript");
				for (var i = 0; i < noscripts.length; i++) {
					noscripts[i].parentNode.removeChild(noscripts[i]);
				}
			}, false);
		</script>
	</head>

	<body>
		<div id="container">
			<div id="pageleft">
				<div id="pagehead">
					<div id="headcontent">
						<div id="headimg"><img src="img/webwxgeticon.jpg"></div>
						<span id="name" style="color: #ffffff">${user.name}</span>

					</div>
					<div id="headnav">
						<a type="button" id="btn" class="btn btn-default btn-lg" style="background-color: #333333;border: none;">
							<span class="glyphicon glyphicon-list" aria-hidden="true" style="color: lightyellow"></span>
						</a>
						<div id="slipdown" style="display: none">

							<div class="cslid"><a href="#" data-toggle="modal" data-target="#myModal">添加好友</a></div>
							<div class="cslid"><a href="#">发起聊天</a></div>
							<div class="cslid"><a href="#">游戏</a></div>
							<div class="cslid"><a href="#">意见反馈</a></div>
							<div class="cslid"><a href="#">退出</a></div>
						</div>
					</div>
				</div>
				<div id="clear" style="clear: both"></div>
				<div id="pagesearch">
					<div class="input-group">
						<span class="input-group-addon" style="background-color: #222222;border: none;"><span class="glyphicon glyphicon-search"></span></span>
						<input type="search" class="form-control" id="inputsearch" placeholder="搜索">
					</div>
				</div>
				<div id="pagemenu">
					<ul id="pageli">
						<li>
							<a href="#" type="button" class="btn btn-default" style="background-color: #333333;border: none;">
								<span class="glyphicon glyphicon-comment sp1"></span>
							</a>
						</li>
						<li>
							<a href="#" type="button" class="btn btn-default" style="background-color: #333333;border: none;">
								<span class="glyphicon glyphicon-book"></span>
							</a>
						</li>
						<li>
							<a href="#" type="button" class="btn btn-default" style="background-color: #333333;border: none;">
								<span class="glyphicon glyphicon-user"></span>
							</a>
						</li>
					</ul>
				</div>
				<div id="pagelist">
					<ul id="menuli">
						<li class="menufirst showli">
							<div id="addfriend1"> <a style="color: #ffffff;text-decoration: none">聊天列表</a></div>
							<s:iterator value="userList" var="u">
								<div id="addfriend1">
									<a onclick="openchat('${u.name}')" style="color: #ffffff;text-decoration: none">${u.name}</a><br/>
								</div>
							</s:iterator>
						</li>
						<li class="menufirst">我是朋友圈</li>
						<li class="menufirst">
							<div id="addfriend"><a style="color: #ffffff;text-decoration: none">新的朋友</a></div>
							<div id="addlist" style="display: none">
								<div class="adlist">申请列表</div>
								<s:iterator value="applicantList" var="p">

									<div class="adlist">${p.applicant}<a href="Agree.action?friend.username=${user.username}&friend.friendname=${p.applicant}&username=${p.applicant}">同意</a>
										<a href="refused.action?username=${p.applicant}">拒绝</a>
									</div>

								</s:iterator>

							</div>

							<div id="addfriend1"> <a style="color: #ffffff;text-decoration: none">好友列表</a></div>
							<s:iterator value="userList" var="u">
								<div id="addfriend1">
									<a onclick="openchat('${u.name}')" style="color: #ffffff;text-decoration: none">${u.name}</a><br/>
								</div>
							</s:iterator>
						</li>
					</ul>
				</div>
			</div>
			<div id="pageright" class="col-md-9 col-lg-9 col-sm-9">
				<div id="right_header">详细信息</div>
				<div class="noscript">
					<h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websockets rely on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></div>
				<div>
				</div>

				<div id="console-container">
					<div id="console"></div>
				</div>
				<div id="right_send">
					<div id="right_nav">
						<a title="文件" class="btn btn-default" aria-label="Left Align" style="background-color: #d3d3d3;border: none;transform: scale(1.5);transform-origin: 0 0">
							<span class="glyphicon glyphicon-file" aria-hidden="true"></span>
						</a>
						<a title="抓屏" class="btn btn-default" aria-label="Left Align" style="background-color: #d3d3d3;border: none;transform: scale(1.5);transform-origin: 0 0">
							<span class="glyphicon glyphicon-scissors" aria-hidden="true"></span>
						</a>
					</div>

					<div id="right_msg">
						<textarea style="width: 100%;height: 100%;background-color: #d3d3d3;border: none" id="chat" placeholder="type and press enter to chat"></textarea>
					</div>
					<div id="right_btn " class="col-md-offset-9 col-md-3 col-lg-offset-9 col-lg-3 col-sm-offset-9 col-sm-3">
						<a class=" button button-glow button-border button-rounded" style="background-color: #555555;color: #ffffff" onclick="onsend()">发送</a>
					</div>

				</div>
			</div>
		</div>
		<div class="mmdoal"></div>
		<div class="mmdoal_msg">
			<p>我们检测到您目前设备的分辨率低于正常电脑的分辨率，建议您采用电脑访问本系统！</p>
		</div>
		<!--添加好友model-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="background-color: #333333">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel" style="color: #ffffff">添加好友</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="add_friends" method="post" action="AddUser.action">
							<div class="form-group">
								<label for="inputFriend" class="col-sm-2 control-label" style="color: #ffffff">好友名</label>
								<div class="col-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
										<input type="text" value="${user.username}" style="display:none" name="applicant.applicant">
										<input type="name" name="applicant.beapplicant" class="form-control" id="inputFriend" placeholder="FriendName">
									</div>
								</div>
							</div>
							<div class="form-group" id="signlog">
								<div class="col-sm-offset-8 col-sm-4">
									<input type="submit" value="添加" class="button button-glow button-border button-rounded button-primary" />

								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<script>
			$(document).ready(function() {
				var leftl = document.body.clientWidth;
				if (parseInt(leftl) <= 960) {
					$(".mmdoal").css("display", "block");
					$(".mmdoal_msg").css("display", "block");
					$(".mmdoal_msg").css("left", (leftl / 2 - $(".mmdoal_msg").width() / 2) + "px");
					$(".mmdoal_msg").css("opacity", "1");
				}
			})
			$(window).resize(function() {
				var leftl = document.body.clientWidth;
				if (parseInt(leftl) <= 960) {
					$(".mmdoal").css("display", "block");
					$(".mmdoal_msg").css("display", "block");
					$(".mmdoal_msg").css("left", (leftl / 2 - $(".mmdoal_msg").width() / 2) + "px");
					$(".mmdoal_msg").css("opacity", "1");
				} else {
					$(".mmdoal").css("display", "none");
					$(".mmdoal_msg").css("opacity", "0");
					$(".mmdoal_msg").css("display", "none");
				}
			})
		</script>

		<script src="js/myjs.js"></script>

	</body>

</html>