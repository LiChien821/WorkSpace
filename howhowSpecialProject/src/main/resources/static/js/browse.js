import { createApp } from 'vue'

const dataObj = {

	courses: "",

	res: "",

	search: "",

	pageNo: ""

};

createApp({
	data() {
		return dataObj;
	},
	mounted: function() {
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
	},
	methods: {
		goSearch: function(search) {
			let empty = new String("");
			if (empty == this.search) {
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
			} else
				axios({
					method: 'get',
					url: '/howhow/findcoursebynamelike/' + search + "/1",
					headers: { "Access-Control-Allow-Origin": "*" },
				})

					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});
		},

		changePage: function(page) {
			document.getElementById("pageNo").value = page;
			this.pageNo = document.getElementById("pageNo").value;
			let empty = new String("");
			if (empty != this.search) {
				axios({
					method: 'get',
					url: '/howhow/findcoursebynamelike/' + this.search + "/" + page,
					headers: { "Access-Control-Allow-Origin": "*" },
				})
					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});

			} else {
				axios({
					method: 'get',
					url: '/howhow/findallcourses/' + page,
					headers: { "Access-Control-Allow-Origin": "*" },
				})
					.then(response => (this.courses = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
					});
			}
		},

		nextPage: function() {
			if (document.getElementById("pageNo").value != this.courses.totalPages) {
				document.getElementById("pageNo").value = (document.getElementById("pageNo").value) * 1 + 1;
			}
			let empty = new String("");
			if (empty != this.search) {
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
			} else {
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
			}
		},

		previousPage: function() {
			if (document.getElementById("pageNo").value != 1) {
				document.getElementById("pageNo").value = (document.getElementById("pageNo").value) * 1 - 1;
			}
			let empty = new String("");
			if (empty != this.search) {
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
			} else {
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
			}
		},

	}
}).mount('#app')