import { createApp } from 'vue'

const dataObj = {
	test: "",
	userId: 1,
	courseCreatorId: "",
	userName: "Big O",
	currQuery: "",

	showQuestionBar: false,
	currQuestionSelection: "選擇單元",
	currQuestionLectionId: "",
	currQuestionTitle: "",
	currQuestionContent: "",

	showReply: false,
	showReplyInput: true,
	currReplyInputContent: "",

	sections: "",
	bulletins: ""
};

createApp({
	data() {
		return dataObj;
	},
	computed: {
		toggleQuestionBtnDisabled(){
			if (this.currQuestionLectionId == "" || this.currQuestionTitle == ""
			|| this.currQuestionContent == "") {
				return 'disabled';
			}
		}
	},
	mounted: function () {
		var urls = window.location.href.split('/');
		var target = urls[urls.length - 1];
		var courseId = target.split('.')[0];
		console.log(courseId);
		this.getBulletinByCourseId();
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
			return axios.get("/howhow/findLoggedUser.controller");
		}
		function getSectionsByCourseid(courseId){
			return axios.get("/howhow/findAllSectionByCourseId.controller",
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
		axios
		.all([getBulletinByCourseId(courseId), getCreatorIdByCourseId(courseId), getLoggedUserId(), getSectionsByCourseid(courseId)])
		.then(axios.spread((...responses) => {
			const resp1 = responses[0];
			const resp2 = responses[1];
			const resp3 = responses[2];
			const resp4 = responses[3];
			this.bulletins = resp1.data;
			this.courseCreatorId = resp2.data;
			this.userId = resp3.data["loggedUserId"];
			this.userName = resp3.data["loggedUserName"];
			this.sections = resp4.data;
			console.log(this.bulletins);
			console.log(this.courseCreatorId);
			console.log("now uid, uname cid",this.userId, this.userName, this.courseCreatorId);
			console.log(this.sections);
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
		sendSearch: function () {
			axios({
				method: 'get',
				url: '/howhow/findBulletinBySearch.controller',
				headers: { 
					'Content-Type': 'application/x-www-form-urlencoded',
					"data-Type": "JSON",
					"Access-Control-Allow-Origin": "*"
				},
				params: {
					query: this.currQuery
				}
			})
			.then((response) => {
				console.log("resp: ", response.data);
				this.bulletins = response.data;
			})
			.catch(function (error) {
				console.log("error: ", error);
			})
			this.currQuery = "";
			this.showReply = false;
		},
		toggleQuestionBar: function () {
			this.showQuestionBar = true;
		},
		toggleQuestionLectureId: function (lecId, secName, lecName){
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
			.then((response) => {
				console.log("resp: ", response.data);
				this.bulletins.unshift(response.data
				)
			})
			.catch(function (error) {
				console.log("error: ", error);
			})

			this.showQuestionBar = false;
			this.currQuestionSelection = "選擇單元";
			this.currQuestionLectionId = "";
			this.currQuestionTitle = "";
			this.currQuestionContent = "";
			this.showReply = false;
			console.log(this.bulletins, "sendQuestion finish");

		},
		toggleReplyContent: function (bltId) {
			if (this.showReply == bltId) {
				this.showReply = false;
			} else {
				this.showReply = bltId;
			}
		},
		toggleReplyInput: function (bltId) {
			this.showReplyInput = bltId;
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
			.then((response) => {
				for(var i = 0; i < this.bulletins.length; i++) {
					if (this.bulletins[i]["bulletinId"] == bltId) {
						this.bulletins[i]["replies"].push(response.data);
						this.bulletins[i]["replyCount"] += 1;
					}
					continue;
				}
			})
			.catch(function (error) {
				console.log("error: ");
				console.log(error);
			})

			this.showReplyInput = true;
			this.currReplyInputContent = "";
		},
		getBulletinByLectureId: function (lectureId){
			axios.get(
				"/howhow/initBulletinByLectureId.controller",
				{
					params: {
						lectureid: lectureId
					},
					headers: { 
						'Content-Type': 'application/x-www-form-urlencoded',
						"data-Type": "JSON",
						"Access-Control-Allow-Origin": "*"
					}
				}
			)
			.then((response) => {
				console.log("resp: ");
				console.log(response);
				this.bulletins = response.data;
			})
			.catch((error) => {
				console.log("error: ");
				console.log(error);
			})
		},
		getBulletinByCourseId: function () {
			var urls = window.location.href.split('/');
			var target = urls[urls.length - 1];
			var courseId = target.split('.')[0];
			console.log(courseId);

			axios.get(
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
			)
			.then((response) => {
				console.log("resp: ");
				console.log(response);
				this.bulletins = response.data;
			})
			.catch((error) => {
				console.log("error: ");
				console.log(error);
			})
		}
	}
	
}).mount('#bulletin')