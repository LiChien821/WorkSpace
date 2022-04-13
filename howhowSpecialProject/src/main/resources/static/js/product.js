const dataObj = {

	course: "",

	favstatus: "",

	shopstatus: "",

	addFav: "",

	purchasedstatus: "",

	ranks: "",

	res: "",

	pageNo: "",

	currentLecturesID: "",

	lecture: "",

	videoSrcUrl: "",

	notesList: "",

	baseUrl: "",

	courseid: "",

	lecturesList: "",

	sectionList: "",

	skipTime: "",

	blobSetting: "",
	
	userid:"",
	
	notestatus: false,
	
	notesList: "",
	
	notescontext:"",
	
	duration:"",
	
	currentTime: "",
	
	admin:""

};


var product = Vue.createApp({
	data() {
		return dataObj;
	},
	beforeMount: function () {
		axios({
			method: 'get',
			url: '/api/getBlobUrl',
			headers: { "Access-Control-Allow-Origin": "*" },
		})
			.then(response => (this.baseUrl = response.data))
			.catch(function (error) {
				console.log(error);
			});

	},

	mounted: function () {
		this.courseid = document.getElementById("courseid").value;
		this.admin = document.getElementById("admin").value;
		axios({
			method: 'get',
			url: '/api/findcoursebyid/' + this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }

		})
			.then(response => (this.course = response.data))
			.catch(function (error) {
				console.log(error);
			});
		this.userid = document.getElementById("userid").value;
		axios({
			method: 'get',
			url: '/api/findfavoritecoursestatus/' + this.userid + '/' + this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.favstatus = response.data))
			.catch(function (error) {
				console.log(error);
			});
		this.userid = document.getElementById("userid").value;
		axios({
			method: 'get',
			url: '/api/findshoppingcartstatus/' + this.userid + '/' + this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.shopstatus = response.data))
			.catch(function (error) {
				console.log(error);
			});

		this.userid = document.getElementById("userid").value;
		axios({
			method: 'get',
			url: '/api/findpurchasedcoursestatus/' + this.userid + '/' + this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => {
				this.purchasedstatus = response.data;
				setTimeout(() => {
					if (this.purchasedstatus == true || this.admin == 1) {
						this.handlefirstVideoUrl();
					} else {
						this.handlefirstPreviewVideoUrl();
					}
				}, 100);

			})
			.catch(function (error) {
				console.log(error);
			});
		this.pageNo = document.getElementById("pageNo").value;
		axios({
			method: 'get',
			url: '/api/querycourserankbycourseid/' + this.courseid + "/1",
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.ranks = response.data, this.res = response))
			.catch(function (error) {
				console.log(error)
			});

		axios({
			method: 'get',

			url: '/api/getSectionList/' + this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" },
		})

			.then(response => (this.sectionList = response.data,
				this.currentLecturesID = response.data[0].lecturesList[0].lecturesID,
				this.lecture = response.data[0].lecturesList[0],
				this.videoSrcUrl = response.data[0].lecturesList[0].previewViedeoUrl
			))
			.catch(function (error) {
				console.log(error);
			});

		axios({
			method: 'get',
			url: '/api/getBlobUrl',
			headers: { "Access-Control-Allow-Origin": "*" },
		})
			.then(response => (this.blobSetting = response.data))
			.catch(function (error) {
				console.log(error);
			})


	},

	methods: {
		
		createNotes: function() {
			this.duration = player.currentTime();
			axios({
				method: 'post',
				url: '/api/createNotes',
				headers: { "Access-Control-Allow-Origin": "*" },
				data: {
					userID: this.userid,

					lectureID: this.currentLecturesID,

					duration: this.duration,

					notescontext: this.notescontext,

				},

			})
				.then(response => (this.notesList = response.data, this.notescontext = ""))
				.catch(function(error) {
					console.log(error);
				});
		},


		changetime: function (time) {
			player.pause();
			this.skipTime = time;
			player.currentTime(this.skipTime);


		},

		addFavorite: function () {
			axios({
				method: 'get',
				url: '/api/checklogin',
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => {
					if (response.data == "") {
						location.href = '/login';
					} else {
						this.addFavoriteAction(response.data);
						console.log("addFavorite finish", "now favstatus is:", this.favstatus);
					}
				})
				.catch(function (error) {
					console.log(error);
				})
		},

		addFavoriteAction(userid) {
			this.courseid = document.getElementById("courseid").value;
			axios({
				method: 'post',
				url: '/api/insertfavoritecourse',
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },

				data: { userID: userid, courseID: this.courseid }
			})
				.then(response => (this.favstatus = true))
				.catch(function (error) {
					console.log(error);
				});
		},
		removeFavorite: function () {
			axios({
				method: 'get',
				url: '/api/checklogin',
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => {
					if (response.data == "") {
						location.href = '/login';
					} else {
						this.removeFavoriteAction(response.data);
						console.log("removeFavorite finish", "now favstatus is:", this.favstatus);
					}
				})
				.catch(function (error) {
					console.log(error);
				})
		},


		removeFavoriteAction(userid) {
			this.courseid = document.getElementById("courseid").value;
			axios({
				method: 'get',
				url: '/api/removefavoritecourse/' + userid + "/" + this.courseid,
				headers: { "Access-Control-Allow-Origin": "*" }

			})
				.then(response => (this.favstatus = false))
				.catch(function (error) {
					console.log(error);
				});
		},

		addShoppingCart: function () {
			axios({
				method: 'get',
				url: '/api/checklogin',
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => {
					if (response.data == "") {
						location.href = '/login';
					} else {
						this.addShoppingCartAction(response.data);
						console.log("addShoppingCart finish", "now shopstatus is:", this.shopstatus);
					}
				})
				.catch(function (error) {
					console.log(error);
				})
		},

		addShoppingCartAction(userid) {
			this.courseid = document.getElementById("courseid").value;
			axios({
				method: 'post',
				url: '/api/insertshoppingcart',
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },

				data: { userID: userid, courseID: this.courseid }
			})
				.then(response => (this.shopstatus = true))
				.catch(function (error) {
					console.log(error);
				});
		},

		removeShoppingCart: function () {
			axios({
				method: 'get',
				url: '/api/checklogin',
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => {
					if (response.data == "") {
						location.href = '/login';
					} else {
						this.removeShoppingCartAction(response.data);
						console.log("removeShoppingCart finish", "now shopstatus is:", this.shopstatus);
					}
				})
				.catch(function (error) {
					console.log(error);
				})
		},

		removeShoppingCartAction: function (userid) {
			this.courseid = document.getElementById("courseid").value;
			axios({
				method: 'get',
				url: '/api/removeshoppingcart/' + userid + "/" + this.courseid,
				headers: { "Access-Control-Allow-Origin": "*" }

			})
				.then(response => (this.shopstatus = false))
				.catch(function (error) {
					console.log(error);
				});
		},

		nextPage: function () {
			if (document.getElementById("pageNo").value != this.ranks.totalPages) {
				document.getElementById("pageNo").value = (document.getElementById("pageNo").value) * 1 + 1;
			}
			this.pageNo = document.getElementById("pageNo").value;
			axios({
				method: 'get',
				url: '/api/querycourserankbycourseid/' + this.courseid + "/" + this.pageNo,
				headers: { "Access-Control-Allow-Origin": "*" }
			})
				.then(response => (
					this.ranks = response.data, this.res = response))
				.catch(function (error) {
					console.log(error);
				});
		},

		previousPage: function () {
			if (document.getElementById("pageNo").value != 1) {
				document.getElementById("pageNo").value = (document.getElementById("pageNo").value) * 1 - 1;
			}
			this.pageNo = document.getElementById("pageNo").value;
			axios({
				method: 'get',
				url: '/api/querycourserankbycourseid/' + this.courseid + "/" + this.pageNo,
				headers: { "Access-Control-Allow-Origin": "*" }
			})
				.then(response => (
					this.ranks = response.data, this.res = response))
				.catch(function (error) {
					console.log(error);
				});
		},

		handlefirstVideoUrl: function () {
			this.currentLecturesID = this.lecture.lecturesID;
			this.videoSrcUrl = this.baseUrl + this.lecture.videoSource;
			player.src(this.videoSrcUrl)
		},

		handlefirstPreviewVideoUrl: function () {
			this.currentLecturesID = this.lecture.lecturesID;
			this.videoSrcUrl = this.baseUrl + this.lecture.previewViedeoUrl;
			player.src(this.videoSrcUrl)
		},

		handleVideoUrl: function (lecture) {
			this.lecture = lecture;
			this.currentLecturesID = this.lecture.lecturesID;
			this.videoSrcUrl = this.baseUrl + this.lecture.videoSource;
			player.src(this.videoSrcUrl);
		},
		
		changeNoteStatus: function() {
			if(this.notestatus==true) {
				this.notestatus=false;
			} else {
				this.notestatus=true;
			}
		},
		
		handlePreviewVideoUrl: function (lecture) {
			this.lecture = lecture;
			this.currentLecturesID = this.lecture.lecturesID;
			this.videoSrcUrl = this.baseUrl + this.lecture.previewViedeoUrl;
			player.src(this.videoSrcUrl);
		},
		// by weijie
		JumpToMyshop: function () {
			if (this.shopstatus == true) {
				location.href = '/myshop';
			} else {
				//adding
				axios({
					method: 'get',
					url: '/api/checklogin',
					headers: { "Access-Control-Allow-Origin": "*" },
				})
					.then(response => {
						if (response.data == "") {
							location.href = '/login';
						} else {
							//adding action
							var userid = response.data;
							this.courseid = document.getElementById("courseid").value;
							axios({
								method: 'post',
								url: '/api/insertshoppingcart',
								headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },
								data: { userID: userid, courseID: this.courseid }
							})
								.then(response => {
									//JumpToNextPage
									this.shopstatus = true;
									location.href = '/myshop';
								})
								.catch(function (error) {
									console.log(error);
								});
						}
					})
					.catch(function (error) {
						console.log(error);
					})
			}
		}
	}

})
product.mount('#product')

var player = videojs('my-video', {

	loop: true,
	muted: true,
	width: "800px",
	height: "720px",
	controls: true
});





//weiji

const dataObj2 = {
	test: "",
	userId: -1,
	courseCreatorId: "",
	userName: "學生",
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
	bulletins: "",
	courseId: "",

	purchasedstatus: "",
	admin:""
};

var bulletin = Vue.createApp({
	data() {
		return dataObj2;
	},
	computed: {
		toggleQuestionBtnDisabled() {
			if (this.currQuestionLectionId == "" || this.currQuestionTitle == ""
				|| this.currQuestionContent == "") {
				return 'disabled';
			}
		}
	},
	mounted:
		function () {
			this.courseId = document.getElementById("courseid").value;
			this.userId = document.getElementById("userid").value;
			this.admin = document.getElementById("admin").value;
			var courseId = this.courseId;
			function getBulletinByCourseId(courseId) {
				console.log(courseId);
				return axios.get(
					"/api/initBulletin.controller",
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

			function getCreatorIdByCourseId(courseId) {

				return axios.get(
					"/api/findCreatorIdByCourseId.controller",
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
			function getLoggedUserId() {
				return axios.get("/api/findLoggedUser.controller");
			}
			function getSectionsByCourseid(courseId) {
				return axios.get("/api/findAllSectionByCourseId.controller",
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
				.all([getBulletinByCourseId(courseId), getCreatorIdByCourseId(courseId), getSectionsByCourseid(courseId)])
				.then(axios.spread((...responses) => {
					const resp1 = responses[0];
					const resp2 = responses[1];
					const resp3 = responses[2];
					this.bulletins = resp1.data;
					this.courseCreatorId = resp2.data;
					this.sections = resp3.data;
					console.log(this.bulletins);
					console.log(this.courseCreatorId);
					console.log("now uid, uname cid", this.userId, this.userName, this.courseCreatorId);
					console.log(this.sections);
				})).catch(errors => {
					console.log(errors);
				});

			axios({
				method: 'get',
				url: '/api/findpurchasedcoursestatus/' + this.userId + '/' + this.courseId,
				headers: { "Access-Control-Allow-Origin": "*" }
			})
				.then(response => {
					this.purchasedstatus = response.data;
					setTimeout(() => {
						if (this.purchasedstatus == true) {
							this.handlefirstVideoUrl();
						} else {
							this.handlefirstPreviewVideoUrl();
						}
					}, 100);

				})
				.catch(function (error) {
					console.log(error);
				});

		},
	methods: {
		sendSearch: function () {
			axios({
				method: 'get',
				url: '/api/findBulletinBySearch.controller',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded',
					"data-Type": "JSON",
					"Access-Control-Allow-Origin": "*"
				},
				params: {
					query: this.currQuery, 
					courseid: this.courseId
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
		toggleQuestionLectureId: function (lecId, secName, lecName) {
			this.currQuestionLectionId = lecId;
			this.currQuestionSelection = secName + "." + lecName;
			console.log("lecId: ", lecId);
		},
		sendQuestion: function () {
			axios({
				method: 'post',
				url: '/api/insertBulletin2.controller',
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
		sendReplyInput: function (bltId) {
			axios({
				method: 'post',
				url: '/api/insertBulletinReply.controller',
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
					for (var i = 0; i < this.bulletins.length; i++) {
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
		getBulletinByLectureId: function (lectureId) {
			axios.get(
				"/api/initBulletinByLectureId.controller",
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
			console.log(courseId);

			axios.get(
				"/api/initBulletin.controller",
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
		},
		reportBulletin: function(bulletinId, reportTypeId) {
			axios.post(
				"/cms/bulletinreport",
				{
					data: {
						bulletinid : bulletinId,
						reporttypeid : reportTypeId
					},
					headers: {
						'Content-Type': 'application/json',
						"dataType": "JSON",
						"Access-Control-Allow-Origin": "*"
					}
				})
				.then((response) => {
					console.log("reportBulletin finished");
				})
				.catch((error) => {
					console.log("reportBulletin error: ", error);
				})

		},
		reportBulletinReply: function() {

		}
	}

})
bulletin.mount('#bulletin')


