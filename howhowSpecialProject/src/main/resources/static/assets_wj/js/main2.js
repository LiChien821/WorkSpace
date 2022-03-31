import { createApp } from 'vue'

const dataObj = {
	test: "",
	userId: 1,
	courseCreatorId: "",
	userName: "Big O",

	showQuestionBar: false,
	currQuestionSelection: "選擇單元",
	currQuestionLectionId: "",
	currQuestionTitle: "",
	currQuestionContent: "",

	showReply: false,
	showReplyInput: true,
	currReplyInputContent: "",

	sectiones: [
		{
			sectionId: 1,
			sectionName: "SEC-1",
			lectiones: [
				{
					lectionId: 1,
					lectionName: "LEC-1"
				},
				{
					lectionId: 2,
					lectionName: "LEC-2"
				}
			]
		},
		{
			sectionId: 2,
			sectionName: "SEC-2",
			lectiones: [
				{
					lectionId: 3,
					lectionName: "LEC-1"
				},
				{
					lectionId: 4,
					lectionName: "LEC-2"
				}
			]
		}
	],

	bulletins: [
		{
			bulletinId: 11,
			lectureId: 21,
			title: "this a title in vue",
			content: "this is a content in vue.",
			launcherName: "WeiJ",
			creationTime: "2022-02-22",
			replies: [
				{
					bulletinReplyId: 31,
					replyContent: "replyContent1",
					respondentName: "Apple",
					creationTime: "2022-02-12"
				},
				{
					bulletinReplyId: 32,
					replyContent: "replyContent1",
					respondentName: "Bed",
					creationTime: "2022-02-12"
				},
			]
		},
		{
			bulletinId: 12,
			lectureId: 23,
			title: "this a title in vue",
			content: "this is a content in vue.",
			launcherName: "WillJ",
			creationTime: "2022-02-22",
			replies: [
				{
					bulletinReplyId: 33,
					replyContent: "replyContent1",
					respondentName: "Cat",
					creationTime: "2022-02-12"
				},
				{
					bulletinReplyId: 34,
					replyContent: "replyContent1",
					respondentName: "Dog",
					creationTime: "2022-02-12"
				},
				{
					bulletinReplyId: 31,
					replyContent: "replyContent1",
					respondentName: "WillJ",
					creationTime: "2022-02-12"
				}
			]
		}

	]
};

createApp({
	data() {
		return dataObj;
	},
	mounted: function () {
		var urls = window.location.href.split('/');
		var target = urls[urls.length - 1];
		var courseId = target.split('.')[0];
		console.log(courseId);

		function getBulletinByCourseId(courseId){
			var urls = window.location.href.split('/');
			var target = urls[urls.length - 1];
			var courseId = target.split('.')[0];
			console.log(courseId) ;
			return axios.get(
				"/howhow/initBulletin.controller",
				{
					params: {
						courseid: courseId
					},
					headers: { 
						'Content-Type': 'application/x-www-form-urlencoded',
						"data-Type": "JSON",
						"Access-Control-Allow-Origin": "*"
					}
				}
			);
		}
		
		function getCreatorIdByCourseId(courseId){
			
			return axios.get(
				"/howhow/findCreatorIdByCourseId.controller",
				{
					params: {
						courseid: courseId
					},
					headers: { 
						'Content-Type': 'application/x-www-form-urlencoded',
						"data-Type": "JSON",
						"Access-Control-Allow-Origin": "*"
					}
				}
			);
		}
		function getLoggedUserId(){
			return axios.get("/howhow/findLoggedUserId.controller");
		}
		// console.log(this.bulletins);
		// axios({
		// 	method: 'get',
		// 	url: '/howhow/initBulletin.controller',
		// 	params: {
		// 		courseid: courseId,
		// 	},
		// 	headers: { 
		// 		// 'Authorization': 'Basic xxxxxxxxxxxxxxxxxxx',
		// 		'Content-Type': 'application/x-www-form-urlencoded',
		// 		"data-Type": "JSON",
		// 		"Access-Control-Allow-Origin": "*"
		// 	}
		// })
		// .then((response) => {
		// 	this.bulletins = response.data;
		// 	console.log(this.bulletins);
		// })
		// .catch(function(error) {
		// 	console.log(error);
		// });
		axios
		.all([getBulletinByCourseId(courseId), getCreatorIdByCourseId(courseId), getLoggedUserId()])
		.then(axios.spread((...responses) => {
			const resp1 = responses[0];
			const resp2 = responses[1];
			const resp3 = responses[2];
			this.bulletins = resp1.data;
			this.courseCreatorId = resp2.data;
			this.userId = resp3.data;

			console.log(this.bulletins);
			console.log(this.courseCreatorId);
			console.log(this.userId);
		})).catch(errors => {
			console.log(errors);
		})



	},
	methods: {
		getCourseId: function () {
			var urls = window.location.href.split('/');
			var target = urls[urls.length - 1];
			var courseId = target.split('.')[0];
			return courseId;
		}, 
		toggleQuestionBar: function () {
			this.showQuestionBar = !this.showQuestionBar
		},
		toggleQuestionLectionId: function (lecId, secName, lecName){
			this.currQuestionLectionId = lecId;
			this.currQuestionSelection = secName + "." + lecName;
			console.log("lecId: ",lecId);
		},
		sendQuestion: function () {
			axios({
				method: 'post',
				url: '/howhow/insertBulletin2.controller',
				headers: { 
					// 'Authorization': 'Basic xxxxxxxxxxxxxxxxxxx',
					'Content-Type': 'application/json',
					"dataType": "JSON",
					"Access-Control-Allow-Origin": "*"
				},
				data: {
					lectureid: this.currQuestionLectionId,
					title: this.currQuestionTitle,
					content: this.currQuestionContent
				}
			})
			.then(function (response) {
				console.log("resp: ");
				console.log(response);
			})
			.catch(function (error) {
				console.log("error: ");
				console.log(error);
			})

			console.log("sendQuestion finish");
		},
		toggleReplyContent: function (bltId) {
			if (this.showReply == bltId) {
				this.showReply = false;
			} else {
				this.showReply = bltId;
			}
		},
		toggleReplyInput: function (bltId) {
			// console.log(bltId);
			if (this.showReplyInput == bltId) {
				this.showReplyInput = false;
			} else {
				this.showReplyInput = bltId;
			}
		},
		cancelReplyInput: function () {
			this.showReplyInput = false;
		},
		sendReplyInput: function(bltId) {
			axios({
				method: 'post',
				url: '/howhow/insertBulletinReply.controller',
				headers: { 
					// 'Authorization': 'Basic xxxxxxxxxxxxxxxxxxx',
					'Content-Type': 'application/json',
					"dataType": "JSON",
					"Access-Control-Allow-Origin": "*"
				},
				data: {
					bulletinid: bltId,
					replycontent: this.currReplyInputContent
				}
			})
			.then(function (response) {
				console.log("resp: ");
				console.log(response);
			})
			.catch(function (error) {
				console.log("error: ");
				console.log(error);
			})


			console.log(bltId);
			console.log(this.currReplyInputContent);
		}

	}
}).mount('#bulletin')