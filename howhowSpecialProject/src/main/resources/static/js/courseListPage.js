import { createApp } from 'vue'

const dataObj = {
	currentAccountID: "",
	
	editCourseID:"",
	
	courseList: "",

	Account: ""

};




createApp({
	data() {
		return dataObj;
	},
	mounted: function() {
		this.currentAccountID = document.getElementById("defaultAccountID").value;
		axios({
			method: 'get',

			url: '/howhow/api/getAllCourse/' + this.currentAccountID,
			headers: { "Access-Control-Allow-Origin": "*" },


		})

			.then(response => (this.courseList = response.data))
			.catch(function(error) {
				console.log(error);

			});

	},
	methods: {
		courseToEdit: function(id) {
			document.getElementById('forminput').value=id;
			
			document.getElementById('editForm').submit();
		}
	}
}).mount('#courseList')