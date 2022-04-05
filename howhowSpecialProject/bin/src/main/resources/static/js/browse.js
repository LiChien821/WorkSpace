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

	currentsearch: ""

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
			url: '/howhow/findallcourses/1',
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.courses = response.data, this.res = response))
			.catch(function(error) {
				console.log(error);
			});

		axios({
			method: 'get',
			url: '/howhow/findfavoritecoursestatusbyuserid/' + this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.favstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});

		axios({
			method: 'get',
			url: '/howhow/getallcategory',
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
					url: '/howhow/findallcourses/1',
					headers: { "Access-Control-Allow-Origin": "*" }

				})
					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});
			} else {
				axios({
					method: 'get',
					url: '/howhow/findcoursebynamelike/' + search + "/1",
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
					url: '/howhow/findcoursebynamelike/' + this.search + "/" + page,
					headers: { "Access-Control-Allow-Origin": "*" },
				})
					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});

			} else if (this.currentcategoryid == 0) {
				axios({
					method: 'get',
					url: '/howhow/findallcourses/' + page,
					headers: { "Access-Control-Allow-Origin": "*" },
				})
					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});
			} else {
				axios({
					method: 'get',
					url: '/howhow/findcoursebycategoryid/' + this.currentcategoryid + "/" + page,
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
					url: '/howhow/findcoursebynamelike/' + this.search + "/" + this.pageNo,
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
					url: '/howhow/findallcourses/' + this.pageNo,
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
					url: '/howhow/findcoursebycategoryid/' + this.currentcategoryid + "/" + this.pageNo,
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
					url: '/howhow/findcoursebynamelike/' + this.search + "/" + this.pageNo,
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
					url: '/howhow/findallcourses/' + this.pageNo,
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
					url: '/howhow/findcoursebycategoryid/' + this.currentcategoryid + "/" + this.pageNo,
					headers: { "Access-Control-Allow-Origin": "*" }
				})
					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					})
			}
		},

		addFavorite: function(courseid) {
			this.userid = document.getElementById("userid").value;

			axios({
				method: 'post',
				url: '/howhow/insertfavoritecourse',
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },

				data: { userID: this.userid, courseID: courseid }
			})
				.then(response => (this.favstatus.push(courseid)))
				.catch(function(error) {
					console.log(this.userid);
					console.log(this.courseid);
					console.log(error);
				})
		},

		removeFavorite: function(courseid) {
			this.userid = document.getElementById("userid").value;
			const index = this.favstatus.indexOf(courseid);
			axios({
				method: 'get',
				url: '/howhow/removefavoritecourse/' + this.userid + '/' + courseid,
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
				url: '/howhow/findcoursebycategoryid/' + categoryid + "/1",
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
				url: '/howhow/findcategorydetail/' + categoryid,
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
				url: '/howhow/findallcourses/1',
				headers: { "Access-Control-Allow-Origin": "*" }
			})
				.then(response => (this.courses = response.data, this.res = response))
				.catch(function(error) {
					console.log(error);
				});
		}

	}
}).mount('#browse')