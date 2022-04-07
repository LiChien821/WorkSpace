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

	blobSetting: ""

};


Vue.createApp({
	data() {
		return dataObj;
	},

	beforeMount: function() {
		axios({
			method: 'get',
			url: '/api/getBlobUrl',
			headers: { "Access-Control-Allow-Origin": "*" },
		})
			.then(response => (this.baseUrl = response.data))
			.catch(function(error) {
				console.log(error);
			});

	},

	mounted: function() {
		this.courseid = document.getElementById("courseid").value;
		axios({
			method: 'get',
			url: '/api/findcoursebyid/' + this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }

		})
			.then(response => (this.course = response.data))
			.catch(function(error) {
				console.log(error);
			});
		this.userid = document.getElementById("userid").value;
		axios({
			method: 'get',
			url: '/api/findfavoritecoursestatus/' + this.userid + '/' + this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.favstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});
		this.userid = document.getElementById("userid").value;
		axios({
			method: 'get',
			url: '/api/findshoppingcartstatus/' + this.userid + '/' + this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.shopstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});

		this.userid = document.getElementById("userid").value;
		axios({
			method: 'get',
			url: '/api/findpurchasedcoursestatus/' + this.userid + '/' + this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.purchasedstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});
		this.pageNo = document.getElementById("pageNo").value;
		axios({
			method: 'get',
			url: '/api/querycourserankbycourseid/' + this.courseid + "/1",
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.ranks = response.data, this.res = response))
			.catch(function(error) {
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
				this.videoSrcUrl = response.data[0].lecturesList[0].previewViedeoUrl,
				this.handlefirstVideoUrl()
			))
			.catch(function(error) {
				console.log(error);
			});

		axios({
			method: 'get',
			url: '/api/getBlobUrl',
			headers: { "Access-Control-Allow-Origin": "*" },
		})
			.then(response => (this.blobSetting = response.data))
			.catch(function(error) {
				console.log(error);
			})


	},

	methods: {

		changetime: function(time) {
			player.pause();
			this.skipTime = time;
			player.currentTime(this.skipTime);


		},

		addFavorite: function() {
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
					}
				})
				.catch(function(error) {
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
				.catch(function(error) {
					console.log(error);
				});
		},
		removeFavorite: function() {
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
					}
				})
				.catch(function(error) {
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
				.catch(function(error) {
					console.log(error);
				});
		},

		addShoppingCart: function() {
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
					}
				})
				.catch(function(error) {
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
				.catch(function(error) {
					console.log(error);
				});
		},

		removeShoppingCart: function() {
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
					}
				})
				.catch(function(error) {
					console.log(error);
				})
		},

		removeShoppingCartAction: function(userid) {
			this.courseid = document.getElementById("courseid").value;
			axios({
				method: 'get',
				url: '/api/removeshoppingcart/' + userid + "/" + this.courseid,
				headers: { "Access-Control-Allow-Origin": "*" }

			})
				.then(response => (this.shopstatus = false))
				.catch(function(error) {
					console.log(error);
				});
		},

		nextPage: function() {
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
				.catch(function(error) {
					console.log(error);
				});
		},

		previousPage: function() {
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
				.catch(function(error) {
					console.log(error);
				});
		},

		handlefirstVideoUrl: function() {
			this.currentLecturesID = this.lecture.lecturesID;
			this.videoSrcUrl = this.baseUrl + this.lecture.previewViedeoUrl;
			player.src(this.videoSrcUrl)
		},


		handleVideoUrl: function(lecture) {
			this.lecture = lecture;
			this.currentLecturesID = this.lecture.lecturesID;
			this.videoSrcUrl = this.baseUrl + this.lecture.previewViedeoUrl;
			player.src(this.videoSrcUrl);
		},


	}

}).mount('#product')

var player = videojs('my-video', {

	loop: true,
	muted: true,
	width: "800px",
	height: "720px",
	controls: true
});
