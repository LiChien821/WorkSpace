

const dataObj = {
	blobSetting: "",

	currentAccountID: "",
	currentCourseID: "",
	currentSectionID: "",
	currentLectureID: "",
	currentSectionIndex: "",
	currentLectureIndex: "",

	categoryList: "",
	category: "",
	course: "",
	currentSection: "",
	videoSrc: "",
	coverSrc: "",
	lecture: "",

	sectionList: "",
	previewableSectionList: "",
	lectureList: "",


	newSectionNum: "",
	newSectionName: "",
	newLectureNum: "",
	newLectureName: "",


	coverFile: "",
	videoFile: "",
	previewVideoFile:"",


	upLoadingText: "",
	upLoadingCover:"",
	
	editingSectionName:0,
	editSectionName:"",
	editingLectureName:0,
	editLectureName:""

};


Vue.createApp({
	data() {
		return dataObj;
	},
	methods: {
		changeCategory(id) {
			this.category = this.categoryList[id - 1];
			this.course.category = this.category;
		},
		handleFileUpload() {
			this.coverFile = this.$refs.file.files[0];
		},
		createForm: function() {
			axios({
				method: 'post',

				url: '/howhow/api/updateCourseAbstract' ,
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },

				data: this.course,
			})

				.then(response => (this.course = response.data))
				.catch(function(error) {
					console.log(error);

				});
			if (this.coverFile !== "") {
				var postforms = new FormData();
				postforms.append("file", this.coverFile);
				postforms.append("courseID", this.course.courseID);

				let config = {
					headers: {
						"Content-Type": "multipart/form-data"
					}
				};

				this.upLoadingCover = `封面上傳中...`;
				this.coverSrc="";
				axios
					.post(
						'/howhow/api/updateCourseAbstractCover',
						postforms,
						config
					)
					.then(response => (this.course = response.data,this.upLoadingCover="",this.$forceUpdate()))
					.catch(function(error) {
						this.upLoadingCover="";
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




Vue.createApp({
	data() {
		return dataObj;
	},
	computed:{
		sectionNum: function(){
			return this.sectionList.length+1;
		}
		
	},
	methods: {
		sendsectionmessage() {
			axios({
				method: 'post',

				url: '/howhow/api/createSection/' + this.currentCourseID,
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },

				data: { sectionNumber: this.sectionNum, sectionName: this.newSectionName }
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


Vue.createApp({
	data() {
		return dataObj;
	},
	computed:{
		lectureNum: function(){
			return this.lectureList.length+1;
		}
		
	},

	methods: {
		rejectSection:function(){
			this.editingSectionName=0;
		},
		changeToEditSectionName:function(num){
			this.editingSectionName=num;
		},
		changeSectionName:function(sectionID){
				axios({
				method: 'post',

				url: '/howhow/api/updateSectionName/'+  this.currentCourseID,
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },

				data: { sectionID:sectionID, sectionName: this.editSectionName }
			})

				.then(response => (this.sectionList = response.data,this.editingSectionName=0,this.editSectionName=""))
				.catch(function(error) {
					console.log(error);

				});
			
			
		},
		rejectLecture:function(){
			this.editingLectureName=0;
		},
		changeToEditLectureName:function(num){
			this.editingLectureName=num;
		},
		changeLectureName:function(lecturesID){
				axios({
				method: 'post',

				url: '/howhow/api/updateLecturesName/'+  this.currentSectionID,
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },

				data: { lecturesID:lecturesID, lecturesName: this.editLectureName }
			})

				.then(response => (this.lectureList = response.data,this.editingLectureName=0,this.editLectureName=""))
				.catch(function(error) {
					console.log(error);

				});
			
			
		},
		sendlecturemessage: function(id) {
			this.currentSectionID = id;
			axios({
				method: 'post',

				url: '/howhow/api/createLecture/' + this.currentSectionID,
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },

				data: { lectureNumber: this.lectureNum, lecturesName: this.newLectureName }
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



Vue.createApp({
	data() {
		return dataObj;
	},
	methods: {
		navToPlay: function() {
			document.getElementById('postForm').submit();
		}
	}

}).mount('#header')

Vue.createApp({
	data() {
		return dataObj;
	},
	methods: {
		changeLectureList: function(id) {
			this.lecture = "";
			this.lectureList = this.sectionList[id].lecturesList;
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
		selectLecture: function(id) {
			this.lecture = this.lectureList[id];

		},
		createForm: function() {
			
			if (this.videoFile !== "") {
				var postforms = new FormData();


				postforms.append("videofile", this.videoFile);
				postforms.append("lectureID", this.lecture.lecturesID);
				let config = {
					headers: {
						"Content-Type": "multipart/form-data"
					}
				};
				this.upLoadingText = `影片上傳中...`;

				axios
					.post(
						'/howhow/api/updateLectureVideo',
						postforms,
						config
					)
					.then(response => (this.lecture = response.data,this.upLoadingText = ""))
					.catch(function(error) {
						this.upLoadingText = "";
						console.log(error);

					});

			}
			
			
		},

		handleVideoUpload() {
			this.videoFile = this.$refs.videofile.files[0];
		},

	}

}).mount('#editCourseLectures')


Vue.createApp({
	data() {
		return dataObj;
	},
	methods: {
		changePreviewLectureList: function(id) {
			this.lecture = "";
			this.lectureList = this.sectionList[id].lecturesList;
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
		selectPreviewLecture: function(id) {
			this.lecture = this.lectureList[id];

		},
		createPreviewForm: function() {
			
			if (this.previewVideoFile !== "") {
				var postforms = new FormData();


				postforms.append("previewVideofile", this.previewVideoFile);
				postforms.append("lectureID", this.lecture.lecturesID);
				let config = {
					headers: {
						"Content-Type": "multipart/form-data"
					}
				};
				this.upLoadingText = `影片上傳中...`;

				axios
					.post(
						'/howhow/api/updateLecturePreviewVideoReturnPreviewableSectionlist',
						postforms,
						config
					)
					.then(response => (this.previewableSectionList = response.data,this.upLoadingText = ""))
					.catch(function(error) {
						this.upLoadingText = "";
						console.log(error);

					});

			}
			
			
		},

		handlePreviewVideoUpload() {
			this.previewVideoFile = this.$refs.previewVideofile.files[0];
		},

	}

}).mount('#editPreviewLectures')


