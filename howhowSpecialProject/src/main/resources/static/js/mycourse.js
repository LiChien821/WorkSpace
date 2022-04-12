import { createApp } from 'vue'

const dataObj = {

	purchasedcourses: "",

	rank: "",

	message: "",

	courseidforrank: "",

	rankstatus: ""

};

createApp({
	data() {
		return dataObj;
	},
	

	mounted: function() {

		this.userid = document.getElementById("userid").value;

		axios({
			method: 'get',
			url: '/api/findpurchasedcoursebyuserid/' + this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.purchasedcourses = response.data))
			.catch(function(error) {
				console.log(error);
			});

		axios({
			method: 'get',
			url: '/api/findcourserankstatusbyuserid/' + this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.rankstatus = response.data))
			.catch(function(error) {
				console.log(error);
			})
	},

	methods: {
<<<<<<< HEAD

		insertcourserank: function(courseid) {
=======
		goLearning: function(courseid) {
			location.href = '/product?id=' + courseid;
		},

		insertcourserank: function() {
>>>>>>> 276d1edf5bf391817d7f005ef8e698d533283805

			this.rank = document.getElementById("rank").value;
			this.message = document.getElementById("message").value;

<<<<<<< HEAD
			console.log("userid", this.userid);
			console.log("courseid", courseid);
			console.log("rank", this.rank);
			console.log("message", this.message);

=======
>>>>>>> 276d1edf5bf391817d7f005ef8e698d533283805
			axios({
				method: 'post',
				url: '/api/insertcourserank',
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },
				data: { userid: this.userid, courseid: this.courseidforrank, rank: this.rank, message: this.message }
			})
				.then(this.rankstatus.push(this.courseidforrank),
					this.courseidforrank="")
				.catch(function(error) {
					console.log(error);
				});

		},

		findCourseidForRank: function(courseid) {

			this.courseidforrank = courseid;
		},

		deleteCourseRank: function(courseid) {
			this.userid = document.getElementById("userid").value;
			const index = this.rankstatus.indexOf(courseid);
			axios({
				method: 'get',
				url: '/api/deletecourserank/'+this.userid+'/'+courseid,
				headers: {"Access-Control-Allow-Origin": "*" },
			})
<<<<<<< HEAD
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
=======
				.then(this.rankstatus.splice(index, 1))
>>>>>>> 276d1edf5bf391817d7f005ef8e698d533283805
				.catch(function(error) {
					console.log(error);
				});

		}

	}

}).mount('#mycourse')