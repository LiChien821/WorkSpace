import { createApp } from 'vue'

const dataObj = {
	blobSetting:"",
	
	currentAccountID: "",
	currentCourseID: "",
	currentSectionID: "",
	currentLectureID:"",
	currentSectionIndex:"",
	currentLectureIndex:"",
	
	categoryList:"",
	category: "",
	course: "",
	currentSection:"",
	videoSrc:"",
	lecture:"",

	sectionList: "",
	lectureList: "",


	newSectionNum: "",
	newSectionName: "",
	newLectureNum: "",
	newLectureName: "",


	coverFile:"",
	videoFile:""

};


createApp({
	data() {
		return dataObj;
	},
	methods: {
		changeCategory(id) {
			this.category=this.categoryList[id-1];
			this.course.category=this.category;
		},
		handleFileUpload() {
				this.coverFile = this.$refs.file.files[0];
			},
		 createForm: function () {
				if(this.coverFile !==""){
				var postforms = new FormData();
				postforms.append("file", this.coverFile);
				postforms.append("courseID", this.course.courseId);
			
				let config = {
					headers: {
						"Content-Type": "multipart/form-data"
					}
				};


				axios
					.post(
						'/howhow/api/updateCourseAbstractCover',
						postforms,
						config
					)
					.then(response => (this.course = response.data))
					.catch(function (error) {
						console.log(error);
						
					});
			
				}	
			
			},
			
			handleVideoUpload() {
				this.videoFile = this.$refs.videofile.files[0];
			},
		
		},
	mounted: function() {
		this.currentCourseID = document.getElementById("defaultCourseID").value;
		axios({
			method: 'get',
			url: '/howhow/api/getBlobUrl',
			headers: { "Access-Control-Allow-Origin": "*" },
		})
			.then(response => (this.blobSetting = response.data))
			.catch(function(error) {
				console.log(error);
			});
		axios({
			method: 'get',
			url: '/howhow/api/getAllCategory',
			headers: { "Access-Control-Allow-Origin": "*" },
		})
			.then(response => (this.categoryList = response.data))
			.catch(function(error) {
				console.log(error);
			});
		
		axios({
			method: 'get',

			url: '/howhow/api/getCourse/' + this.currentCourseID,
			headers: { "Access-Control-Allow-Origin": "*" },


		})

			.then(response => (this.course = response.data, this.sectionList = response.data.sectionList, this.category = response.data.category))
			.catch(function(error) {
				console.log(error);

			});

	},


}).mount('#editCourseAbsract')




createApp({
	data() {
		return dataObj;
	},
	methods: {
		sendsectionmessage() {
			axios({
				method: 'post',

				url: '/howhow/api/createSection/' + this.currentCourseID,
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },

				data: { sectionNumber: this.newSectionNum, sectionName: this.newSectionName }
			})

				.then(response => (this.sectionList = response.data))
				.catch(function(error) {
					console.log(error);

				});
			this.newSectionNum = "";
			this.newSectionName = "";
		}
	},


}).mount('#createSection')


createApp({
	data() {
		return dataObj;
	},

	methods: {
		sendlecturemessage: function(id) {
			this.currentSectionID = id;
			axios({
				method: 'post',

				url: '/howhow/api/createLecture/' + this.currentSectionID,
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },

				data: { lectureNumber: this.newLectureNum, lecturesName: this.newLectureName }
			})

				.then(response => (this.lectureList = response.data))
				.catch(function(error) {
					console.log(error);

				});
			this.newLectureNum = "";
			this.newLectureName = "";

		},
		getLectureListFromSection: function(id) {
			this.currentSectionID = id;
			axios({
				method: 'get',

				url: '/howhow/api/getLectureList/' + this.currentSectionID,
				headers: { "Access-Control-Allow-Origin": "*" },


			})

				.then(response => (this.lectureList = response.data))
				.catch(function(error) {
					console.log(error);

				});

		},
		storeID: function(id) {
			this.currentSectionID = id
		}
	},

}).mount('#createSectionList')



createApp({
	data() {
		return dataObj;
	},
	methods: {
		navToPlay: function() {
			document.getElementById('postForm').submit();
		}
	}

}).mount('#header')

createApp({
	data() {
		return dataObj;
	},
	methods: {
		changeLectureList: function(id) {
			this.lecture="";
			this.lectureList=this.sectionList[id].lecturesList;
		
		},
		selectLecture:function(id) {
			this.lecture=this.lectureList[id];
		
		},
		 createForm: function () {
				var postforms = new FormData();
			
				
				postforms.append("videofile", this.videoFile);
				postforms.append("lectureID", this.lecture.lecturesID);
				let config = {
					headers: {
						"Content-Type": "multipart/form-data"
					}
				};


				axios
					.post(
						'/howhow/api/updateLectureVideo',
						postforms,
						config
					)
					.then(response => (this.lecture = response.data))
					.catch(function (error) {
						console.log(error);
						
					});
				
			},
			
			handleVideoUpload() {
				this.videoFile = this.$refs.videofile.files[0];
			},
		
	}

}).mount('#editCourseLectures')
