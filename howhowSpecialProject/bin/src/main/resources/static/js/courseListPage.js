const dataObj = {
	currentAccountID: "",
	
	editCourseID:"",
	
	courseList: "",

	Account: "",
	
	blobSetting:"",
	
	currentPage:1,
	
	totalPages:"",
	
	totalElements:"",
	
	pageItem:true,

};




Vue.createApp({
	data() {
		return dataObj;
	},
	mounted: function() {
		this.currentAccountID = document.getElementById("defaultAccountID").value;
			axios({
			method: 'get',
<<<<<<< HEAD
			url: '/howhow/api/getAllCourse/' + this.currentAccountID,
=======
			url: '/howhow/api/getPageAllCourse/' + this.currentAccountID+"/"+this.currentPage,
>>>>>>> origin/master
			headers: { "Access-Control-Allow-Origin": "*" },

		})

			.then(response => (this.courseList = response.data.content,this.totalPages=response.data.totalPages))
			.catch(function(error) {
				console.log(error);

			});
		axios({
			method: 'get',
			url: '/howhow/api/getBlobUrl',
			headers: { "Access-Control-Allow-Origin": "*" },
		})
			.then(response => (this.blobSetting = response.data))
			.catch(function(error) {
				console.log(error);
			});	

	},
	methods: {
		courseToEdit: function(id) {
			document.getElementById('forminput').value=id;
			
			document.getElementById('editForm').submit();
		},
		pageLinkToCourse:function(num){
			this.currentPage=num;
				axios({
			method: 'get',
			url: '/howhow/api/getPageAllCourse/' + this.currentAccountID+"/"+this.currentPage,
			headers: { "Access-Control-Allow-Origin": "*" },

		})

			.then(response => (this.courseList = response.data.content,this.totalPages=response.data.totalPages))
			.catch(function(error) {
				console.log(error);

			});
		}
	}
}).mount('#courseList')