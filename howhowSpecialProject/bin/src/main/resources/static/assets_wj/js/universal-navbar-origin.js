import { createApp } from 'vue'

const dataObj = {
	blobSetting: "",
	courses: "",
	res: ""
};

const app = createApp({
	data() {
		return dataObj;
	},
	mounted: function () {
		axios({
			method: 'get',
			url: '/api/getBlobUrl',
			headers: { "Access-Control-Allow-Origin": "*" },
		})
			.then(response => {
				this.blobSetting = response.data;
				console.log("this blobSetting", this.blobSetting);
			})
			.catch(function (error) {
				console.log(error);
			})

		axios({
			method: 'get',
			url: '/api/findallcourses/1',
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => {
				this.courses = [];
				console.log("this response", response.data);
				var i = 0;
				var n = response.data['content'].length;
				console.log("n", n);
				while (i < n) {
					if (i >= 3 ) {break}
					this.courses.push(response.data['content'][i]);
					i++;
				}
				console.log("this courses", this.courses);
			})
			.catch(function (error) {
				console.log(error);
			});

	},
	methods: {

		goToThePage: function(url) {
			self.location.href = url;
		}
	}
})
app.mount('#test-navbar');