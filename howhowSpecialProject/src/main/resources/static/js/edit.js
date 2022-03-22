import { createApp } from 'vue'

const dataObj = {
	blobSetting:"",
	
	currentAccountID: "",
	currentCourseID: "",
	currentSectionID: "",
	

	category: "",
	course: "",


	sectionList: "",
	lectureList: "",


	newSectionNum: "",
	newSectionName: "",
	newLectureNum: "",
	newLectureName: "",




};


createApp({
	data() {
		return dataObj;
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
