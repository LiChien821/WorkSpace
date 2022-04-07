import { createApp } from 'vue'

const dataObj = {

	purchasedcourses: ""

};

createApp({
	data() {
		return dataObj;
	},
	

	mounted: function() {

		this.userid = document.getElementById("userid").value;

		axios({
			method: 'get',
			url: '/howhow/api/findpurchasedcoursebyuserid/' + this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.purchasedcourses = response.data))
			.catch(function(error) {
				console.log(error);
			});

	},

	methods: {

		insertcourserank: function(courseid) {

			this.rank = document.getElementById("rank").value;
			this.message = document.getElementById("message").value;

			console.log("userid", this.userid);
			console.log("courseid", courseid);
			console.log("rank", this.rank);
			console.log("message", this.message);

			axios({
				method: 'post',
				url: '/howhow/api/insertcourserank',
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },
				data: { userid: this.userid, courseid: courseid, rank: this.rank, message: this.message }
			})
				.then(function(response) {
					console.log(response);
				})
				.catch(function(error) {
					console.log(error);
				});

		},
		
		deletecourserank: function(courseid) {

			this.rank = document.getElementById("rank").value;
			this.message = document.getElementById("message").value;

			console.log("userid", this.userid);
			console.log("courseid", courseid);
			console.log("rank", this.rank);
			console.log("message", this.message);

			axios({
				method: 'get',
				url: '/howhow/api/deletecourserank/',
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },
				data: { userid: this.userid, courseid: courseid, rank: this.rank, message: this.message }
			})
				.then(function(response) {
					console.log(response);
				})
				.catch(function(error) {
					console.log(error);
				});

		}

	}

}).mount('#mycourse')