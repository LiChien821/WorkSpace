$(document).ready(function () {


	getCourseId();

	init();
	addBulletinContent();
	deleteBulletinContentById();
	webSlideBluttinInnerContainerToggle();
	slideBluttinInnerContainerToggle();
	slideBluttinQuestionContainerToggle()
	slideBulltinReplyToggle();
	webSlideBulltinReplyToggle();
});

function getCourseId() {
    var urls = window.location.href.split('/');
	var target = urls[urls.length-1];
	var courseId = target.split('.')[0];
    return courseId;
	}


function init() {
	var courseId = getCourseId();
	console.log("get courseId: ", courseId);
	
	$.ajax({
		type: "post",
		url: "/howhow/init3.controller",
		data: {
			"courseid": courseId
		},
		dataType: "json",
		contentType: "application/x-www-form-urlencoded",
		success: function (resp) {
			console.log(resp);
			$("#show-all-bulletin").empty();
			if (resp == null) {
				$("table").prepend("<tr><td colspan='2'>暫無資料</td></tr>");
			} else {
				var blt = $("#show-all-bulletin");
				$.each(resp, function (i, n) {
					// console.log(n);
					var bulletinId = n.bulletinid;
					var title = n.title;
					var content = n.content;
					var bCreationTime = n.creationtime;
					var launcherName = n.launchername;
					var replyMap = n.replymap;
					// var single_msg = "<div class='d-flex' id=" + id + ">" +
					// 	"<div class='flex-shrink-0'>" +
					// 	"<h3><i class='fa fa-book' aria-hidden='true'></i>" +
					// 	"<h3>" +
					// 	"</div>" +
					// 	"<div class='flex-grow-1 ms-3 d-block'>" +
					// 	title + "<br/>" +
					// 	content + "<br/>" +
					// 	"<div class='d-flex justify-content-end'>" +
					// 	"<div class='mx-1'>" + launcherName + "</div>" +
					// 	"<div class='mx-1'>" + creationTime + "</div>" +
					// 	"<div class='mx-1'>icon</div>" +
					// 	"</div>" +
					// 	"</div>" + "<br/>" +
					// 	"</div>";
					var allReplyMsg = "";
					for (var key in replyMap){
						var value = replyMap[key];
						var bulletinReplyId = value.bulletinreplyid;
						var replyContent = value.replycontent;
						var brCreationTime = value.creationtime;
						var respondentName = value.respondentname;
						var replyMsg = `<div class="blt-reply-content d-flex mt-2">
                                                <div class="flex-shrink-0">
                                                    <h3><i class="fa fa-user-circle" aria-hidden="true"></i>
                                                        <h3>
                                                </div>
                                                <div class="container px-0 ms-2 mt-1">
                                                    <div class="d-flex justify-content-start align-items-center">
                                                        <div class="me-2"><small><span>授課老師</span></small></div>
                                                        <div class="me-2"><small><span>`+ respondentName +`</span></small></div>
                                                        <div class="me-2"><small><span>`+ brCreationTime +`</span></small></div>
                                                    </div>
                                                    <div class="flex-grow-1 m-0">
														<p>`+ replyContent +`</p>
													</div>
                                                </div>
                                            </div>`;
						allReplyMsg+=replyMsg;
					  }

					var single_msg = `<div class="d-flex mb-3">
                                <div class="flex-shrink-0">
                                    <h1><i class="fa fa-user-circle" aria-hidden="true"></i>
                                        <h1>
                                </div>
                                <div class="flex-grow-1 ms-2 d-block">
                                    <span>`+ title +`</span>
                                    <div class="d-flex justify-content-start align-items-center mb-1">
                                        <div class="me-3"><small><span>`+ launcherName +`</span></small></div>
                                        <div class="me-3"><small><span>`+ bCreationTime +`</span></small></div>
                                        <div class="me-3 blt-inner-flip">
                                            <small><span><i class="fa fa-comment" aria-hidden="true"></i></span></small>
                                        </div>
                                    </div>
                                    <div class="blt-inner-container">
                                        <div class="blt-inner-content">
											<div class="my-3 blt-title">
													<p>`+ content +`</p>
                                            </div>
					  	                       `+ allReplyMsg +`
                                            <div class="blt-reply-content d-flex mt-2">
                                                <div class="flex-shrink-0">
                                                    <h3><i class="fa fa-user-circle" aria-hidden="true"></i>
                                                        <h3>
                                                </div>
                                                <div class="container px-0 ms-2 mt-1">
                                                    <div class="d-flex justify-content-start align-items-center">
                                                        <div class="me-2"><small><span>授課老師</span></small></div>
                                                        <div class="me-2"><small><span>王韋傑</span></small></div>
                                                    </div>
                                                    <div class="flex-grow-1 m-0 d-grid ">
                                                        <textarea class="reply-input" type="text" style="height:30px;"
                                                            placeholder="請留言..."></textarea>
                                                    </div>
                                                    <div class="mt-1 d-flex justify-content-end ">
                                                        <div class="reply-input-toggle" style="display: none;">
                                                            <button
                                                                class="btn btn-dark btn-primary me-1 reply-input-toggle-cancel"
                                                                type="button">取消</button>
                                                            <button class="btn btn-dark btn-primary "
                                                                id="reply-input-submit" type="button">送出</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>`
					blt.append(single_msg);
				});
			}
		},
		error: function (err) {
			console.log(err);
		}
	});
}
function deleteBulletinContentById() {
	$(".show-blt").on("click", ".del", function (event) {
		event.preventDefault()
		var theId = $(this).parent().parent().attr('id')
		console.log(theId);
		$("#" + theId).empty();
		$.ajax({
			type: "post",
			url: "/deletebulletinbyid.controller",
			// data: JSON.stringify(data),
			data: {
				"id": theId
			},
			dataType: "JSON",
			contentType: "application/x-www-form-urlencoded",
			success: function (resp) { console.log(resp) },
			error: function (err) { console.log(err) },
		});
	});
}
function addBulletinContent() {
	$("#blt-qst-submit").click(function (event) {
		event.preventDefault()
		var title = $("#blt-qst-title").val();
		var content = $("#c1").val();
		var data = {
			"launcherid": 1,
			"lectureid": 1,
			"title": title,
			"content": content

		}
		console.log(title + " " + content);
		$.ajax({
			type: "post",
			url: "/howhow/insertBulletin2.controller",
			data: JSON.stringify(data),
			dataType: "JSON",
			contentType: "application/json",
			success: function (resp) { console.log(resp) },
			error: function (err) { console.log(err) },
		});
		init();
		return false;
	})

}
function slideBluttinInnerContainerToggle() {
	$(".blt-inner-flip").click(function (event) {
		$(this).parent().next().slideToggle("fast");
	});
}
function slideBluttinQuestionContainerToggle() {
	$("#blt-qst-title").focus(function () {
		$("#blt-qst-toggle").slideDown("fast");
	});
	$("#blt-qst-toggle-cancel").click(function () {
		$("#blt-qst-toggle").slideUp("fast");
	});
}
function slideBulltinReplyToggle() {
	$(".reply-input").focus(function () {
		$(this).animate({ "height": "100px" }, "fast");
		$(this).parent().next().find(".reply-input-toggle").slideDown("fast");
	});
	$(".reply-input-toggle-cancel").click(function () {
		$(this).parent().parent().prev().find(".reply-input").animate({ "height": "30px" }, "fast");
		$(this).parent().slideUp("fast");
	});
}
function webSlideBluttinInnerContainerToggle() {
	$("#show-all-bulletin").on("click", ".blt-inner-flip", function (event) {
		event.preventDefault();
		console.log("1234");
		$(this).parent().next().slideToggle("fast");
	});
}
function webSlideBulltinReplyToggle() {
	$("#show-all-bulletin").on("focus", ".reply-input", function (event) {
		$(this).animate({ "height": "100px" }, "fast");
		$(this).parent().next().find(".reply-input-toggle").slideDown("fast");
	});
	$("#show-all-bulletin").on("click", ".reply-input-toggle-cancel", function (event) {
		$(this).parent().parent().prev().find(".reply-input").animate({ "height": "30px" }, "fast");
		$(this).parent().slideUp("fast");
	});
}