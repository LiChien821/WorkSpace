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
		goLearning: function(courseid) {
			location.href = '/play/' + courseid;
		},

		insertcourserank: function() {

			this.rank = document.getElementById("rank").value;
			this.message = document.getElementById("message").value;

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
				.then(this.rankstatus.splice(index, 1))
				.catch(function(error) {
					console.log(error);
				});

		}

	}

}).mount('#mycourse')