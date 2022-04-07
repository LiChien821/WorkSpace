import { createApp } from 'vue'

const dataObj = {

	purchasedcourses:""

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
			
	},

	methods: {
		goLearning: function(courseid) {
			
		}
	}

}).mount('#mycourse')