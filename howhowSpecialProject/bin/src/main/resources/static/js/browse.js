import { createApp } from 'vue'

const dataObj = {

	courses: "",
	
	res:""
	
};

createApp({
	data() {
		return dataObj;
	},
	mounted: function() {
		this.pageNo = document.getElementById("pageNo").value;
		
		axios({
			method: 'get',
			
			url: '/howhow/findallcourses/'+this.pageNo,
			headers: { "Access-Control-Allow-Origin": "*" }

		})
			.then(response => (this.courses = response.data))
			.then(response => (this.res = response))
			.catch(function(error) {
				console.log(error);

			});

	}

}).mount('#app')