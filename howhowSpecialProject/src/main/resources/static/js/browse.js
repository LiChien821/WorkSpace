import { createApp } from 'vue'

const dataObj = {

	courses: "",

	res: "",

	search: "",

	pageNo: "",

	userid: "",

	favstatus: "",

	categories: "",

	currentcategoryid: 0,

	currentcategorydescription: "",

	currentcategoryname: "",

	currentsearch: "",
	
	loginstatus:""

};

createApp({
	data() {
		return dataObj;
	},
	mounted: function() {
		this.pageNo = document.getElementById("pageNo").value;
		this.userid = document.getElementById("userid").value;
		axios({
			method: 'get',
			url: '/howhow/api/findallcourses/1',
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.courses = response.data, this.res = response))
			.catch(function(error) {
				console.log(error);
			});

		axios({
			method: 'get',
			url: '/howhow/api/findfavoritecoursestatusbyuserid/' + this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.favstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});

		axios({
			method: 'get',
			url: '/howhow/api/getallcategory',
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.categories = response.data))
			.catch(function(error) {
				console.log(error);
			})
	},
	methods: {
		goSearch: function(search) {
			let empty = new String("");
			this.currentsearch = this.search;
			this.search = "";
			if (empty == this.currentsearch) {
				this.pageNo = document.getElementById("pageNo").value;
				axios({
					method: 'get',
					url: '/howhow/api/findallcourses/1',
					headers: { "Access-Control-Allow-Origin": "*" }

				})
					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});
			} else {
				axios({
					method: 'get',
					url: '/howhow/api/findcoursebynamelike/' + search + "/1",
					headers: { "Access-Control-Allow-Origin": "*" },
				})

					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});
			}
		},

		changePage: function(page) {
			document.getElementById("pageNo").value = page;
			this.pageNo = document.getElementById("pageNo").value;
			let empty = new String("");
			if (empty != this.currentsearch) {
				axios({
					method: 'get',
					url: '/howhow/api/findcoursebynamelike/' + this.search + "/" + page,
					headers: { "Access-Control-Allow-Origin": "*" },
				})
					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});

			} else if (this.currentcategoryid == 0) {
				axios({
					method: 'get',
					url: '/howhow/api/findallcourses/' + page,
					headers: { "Access-Control-Allow-Origin": "*" },
				})
					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});
			} else {
				axios({
					method: 'get',
					url: '/howhow/api/findcoursebycategoryid/' + this.currentcategoryid + "/" + page,
					headers: { "Access-Control-Allow-Origin": "*" }
				})
					.then(response => (
						this.courses = response.data,
						this.res = response,
						this.findCategoryDetail(this.currentcategoryid)
					))
					.catch(function(error) {
						console.log(error);
					})
			}
		},

		nextPage: function() {
			if (document.getElementById("pageNo").value != this.courses.totalPages) {
				document.getElementById("pageNo").value = (document.getElementById("pageNo").value) * 1 + 1;
			}
			let empty = new String("");
			if (empty != this.currentsearch) {
				this.pageNo = document.getElementById("pageNo").value;
				axios({
					method: 'get',
					url: '/howhow/api/findcoursebynamelike/' + this.search + "/" + this.pageNo,
					headers: { "Access-Control-Allow-Origin": "*" },
				})
					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});
			} else if (this.currentcategoryid == 0) {
				this.pageNo = document.getElementById("pageNo").value;
				axios({
					method: 'get',
					url: '/howhow/api/findallcourses/' + this.pageNo,
					headers: { "Access-Control-Allow-Origin": "*" },
				})
					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});
			} else {
				this.pageNo = document.getElementById("pageNo").value;
				axios({
					method: 'get',
					url: '/howhow/api/findcoursebycategoryid/' + this.currentcategoryid + "/" + this.pageNo,
					headers: { "Access-Control-Allow-Origin": "*" }
				})
					.then(response => (
						this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					})
			}
		},

		previousPage: function() {
			if (document.getElementById("pageNo").value != 1) {
				document.getElementById("pageNo").value = (document.getElementById("pageNo").value) * 1 - 1;
			}
			let empty = new String("");
			if (empty != this.currentsearch) {
				this.pageNo = document.getElementById("pageNo").value;
				axios({
					method: 'get',
					url: '/howhow/api/findcoursebynamelike/' + this.search + "/" + this.pageNo,
					headers: { "Access-Control-Allow-Origin": "*" },
				})
					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});
			} else if (this.currentcategoryid == 0) {
				this.pageNo = document.getElementById("pageNo").value;
				axios({
					method: 'get',
					url: '/howhow/api/findallcourses/' + this.pageNo,
					headers: { "Access-Control-Allow-Origin": "*" },
				})

					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});
			} else {
				this.pageNo = document.getElementById("pageNo").value;
				axios({
					method: 'get',
					url: '/howhow/api/findcoursebycategoryid/' + this.currentcategoryid + "/" + this.pageNo,
					headers: { "Access-Control-Allow-Origin": "*" }
				})
					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					})
			}
		},
		
		addFavorite: function(courseid) {
			axios({
				method: 'get',
				url: '/howhow/api/checklogin',
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => {
					if(response.data=="") {
						location.href='/howhow/login';
					} else {
						this.addFavoriteAction(courseid,response.data);
					}
				})
				.catch(function(error) {
					console.log(error);
				})
		},
		
		addFavoriteAction(courseid,userid) {
			this.userid = document.getElementById("userid").value;
			axios({
				method: 'post',
				url: '/howhow/api/insertfavoritecourse',
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },

				data: { userID: userid, courseID: courseid }
			})
				.then(response => (this.favstatus.push(courseid)))
				.catch(function(error) {
					console.log(error);
				})
		},
		
		removeFavorite: function(courseid) {
			axios({
				method: 'get',
				url: '/howhow/api/checklogin',
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => {
					if(response.data=="") {
						location.href='/howhow/login';
					} else {
						this.removeFavoriteAction(courseid,response.data);
					}
				})
				.catch(function(error) {
					console.log(error);
				})
		},
		
		removeFavoriteAction: function(courseid,userid) {
			const index = this.favstatus.indexOf(courseid);
			axios({
				method: 'get',
				url: '/howhow/api/removefavoritecourse/' + userid + '/' + courseid,
				headers: { "Access-Control-Allow-Origin": "*" }
			})
				.then(response => (this.favstatus.splice(index, 1)))
				.catch(function(error) {
					console.log(error);
				})
		},

		searchByCategory: function(categoryid) {
			this.currentsearch = "";
			this.currentcategoryid = categoryid;
			axios({
				method: 'get',
				url: '/howhow/api/findcoursebycategoryid/' + categoryid + "/1",
				headers: { "Access-Control-Allow-Origin": "*" }
			})
				.then(response => (
					this.courses = response.data,
					this.res = response
				))
				.catch(function(error) {
					console.log(error);
				})
			this.findCategoryDetail(this.currentcategoryid)
		},

		findCategoryDetail(categoryid) {
			axios({
				method: 'get',
				url: '/howhow/api/findcategorydetail/' + categoryid,
				headers: { "Access-Control-Allow-Origin": "*" }
			})
				.then(response => (
					this.currentcategoryname = response.data.name,
					this.currentcategorydescription = response.data.descriptior
				))
				.catch(function(error) {
					console.log(error);
				})
		},
		searchAll() {
			this.currentcategoryid = 0;
			document.getElementById("pageNo").value = 1;
			axios({
				method: 'get',
				url: '/howhow/api/findallcourses/1',
				headers: { "Access-Control-Allow-Origin": "*" }
			})
				.then(response => (this.courses = response.data, this.res = response))
				.catch(function(error) {
					console.log(error);
				});
		}

	}
}).mount('#browse')