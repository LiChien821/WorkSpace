const dataObj = {
	baseUrl: "",
	videoSrcUrl: "",
	videotype: "video/mp4",

	currentTime: "",
	skipTime: "",
<<<<<<< HEAD
	duration: "",
	notescontext:"",
	notesList:"",
=======
	duration: 0,
	notescontext: "",
	notesList: "",
>>>>>>> 82be4571b230a83ce00bbc27ff78481ae8d037d6

	lecture: "",
	course: "",
	currentCourseID: "",
<<<<<<< HEAD
	currentSectionID:"",
	currentLecturesID:"",
	userAccoountID:"",
=======
	currentSectionID: "",
	currentLecturesID: "",
	userAccountID: "",
	userAccountCreatTime:"",
>>>>>>> 82be4571b230a83ce00bbc27ff78481ae8d037d6

	sectionList: "",
	lecturesList: "",
	sectionID: "",
	
<<<<<<< HEAD
=======
	totalSection:"",
	totalLecture:"",
	
	
>>>>>>> 82be4571b230a83ce00bbc27ff78481ae8d037d6

};



Vue.createApp({
	data() {
		return dataObj;
	},

	methods: {
<<<<<<< HEAD
		createNotes :function(){
				axios({
			method: 'post',
			url: '/howhow/api/createNotes',
			headers: { "Access-Control-Allow-Origin": "*" },
			data:{
				 UID : this.userAccoountID ,

				lectureID: this.currentLecturesID,
		
				duration: this.duration,
		
				 notescontext:this.notescontext,
				
			},

		})
			.then(response => (this.notesList= response.data))
			.catch(function(error) {
				console.log(error);

			});
		}
	
	},
	mounted: function() {
	
		this.userAccoountID=document.getElementById("deafultUID").value;
		axios({
			method: 'post',
			url: '/howhow/api/getAllNotes/'+this.userAccoountID+'/'+this.currentLecturesID,
			headers: { "Access-Control-Allow-Origin": "*" },
		

		})
			.then(response => (this.notesList= response.data))
			.catch(function(error) {
				console.log(error);

			});

	},

}).mount('notesVue')
=======
		createNotes: function() {
			this.userAccountID = document.getElementById("defaultAccountID").value;
			this.duration = player.currentTime();
			axios({
				method: 'post',
				url: '/howhow/api/createNotes',
				headers: { "Access-Control-Allow-Origin": "*" },
				data: {
					userID: this.userAccountID,

					lectureID: this.currentLecturesID,

					duration: this.duration,

					notescontext: this.notescontext,

				},
>>>>>>> 82be4571b230a83ce00bbc27ff78481ae8d037d6

			})
				.then(response => (this.notesList = response.data, this.notescontext = ""))
				.catch(function(error) {
					console.log(error);

				});
		},

		changetime: function(time) {
			player.pause();
			this.skipTime = time;
			player.currentTime(this.skipTime);
			

		}


<<<<<<< HEAD
=======
	},
	mounted: function() {



	},

}).mount('#notesVue')



>>>>>>> 82be4571b230a83ce00bbc27ff78481ae8d037d6
Vue.createApp({
	data() {
		return dataObj;
	},
	emits: {
		storeID(id) {
			this.sectionID = id
		}
	},
	methods: {
		sendlecturemessage: function(id) {

		},
		getAllNotesByUIDANDLectureID: function() {
			axios({
				method: 'get',
				url: '/howhow/api/getAllNotes/' + this.userAccountID + "/" + this.currentLecturesID,
				headers: { "Access-Control-Allow-Origin": "*" },


			})
				.then(response => (this.notesList = response.data))
				.catch(function(error) {
					console.log(error);

				});

		},
		handleVideoUrl: function(lecture) {
			this.lecture = lecture;
			this.currentLecturesID = this.lecture.lecturesID;
			this.videoSrcUrl = this.baseUrl + this.lecture.videoSource;
			player.src(this.videoSrcUrl);
			this.notesList="";
			this.getAllNotesByUIDANDLectureID();
		},
		handlefirstVideoUrl: function() {

			this.currentLecturesID = this.lecture.lecturesID;
			this.videoSrcUrl = this.baseUrl + this.lecture.videoSource;
			player.src(this.videoSrcUrl)
		},
		getLectureListFromSection: function(id) {
			this.currentSectionID = id;
			axios({
				method: 'get',

				url: '/howhow/api/getLectureList/' + this.currentSectionID,
				headers: { "Access-Control-Allow-Origin": "*" },


			})

				.then(response => (this.lecturesList = response.data))
				.catch(function(error) {
					console.log(error);

				});

		},
	},
	beforeMount: function(){
		axios({
			method: 'get',
			url: '/howhow/api/getBlobUrl',
			headers: { "Access-Control-Allow-Origin": "*" },
		})
			.then(response => (this.baseUrl = response.data))
			.catch(function(error) {
				console.log(error);
			});
		
	},
	mounted: function() {
		this.currentCourseID = document.getElementById("playPageDeafultId").value;
		this.userAccountID = document.getElementById("defaultAccountID").value;
		axios({
			method: 'get',

			url: '/howhow/api/getSectionList/' + this.currentCourseID,
			headers: { "Access-Control-Allow-Origin": "*" },
		})

<<<<<<< HEAD
			.then(response => (this.course = response.data, this.sectionList = response.data.sectionList,this.currentLecturesID=response.data.sectionList[0].lecturesList[0].lecturesID ))
=======
			.then(response => (this.sectionList = response.data,
				this.currentLecturesID = response.data[0].lecturesList[0].lecturesID,
				this.lecture = response.data[0].lecturesList[0],
				this.videoSrcUrl = response.data[0].lecturesList[0].videoSource,
				this.handlefirstVideoUrl(),
			this.getAllNotesByUIDANDLectureID()
			))
>>>>>>> 82be4571b230a83ce00bbc27ff78481ae8d037d6
			.catch(function(error) {
				console.log(error);
			});
			
		
		
	},
	
}).mount('#playSectionList')
<<<<<<< HEAD
const app = Vue.createApp({
=======

Vue.createApp({
>>>>>>> 82be4571b230a83ce00bbc27ff78481ae8d037d6
	data() {
		return dataObj;
	},
	computed:{
		
	},
	methods: {
		getCourse:function(){
			
			axios({
				method: 'get',
	
				url: '/howhow/api/getCourse/' + this.currentCourseID,
				headers: { "Access-Control-Allow-Origin": "*" },
			})
	
				.then(response => (this.course = response.data,this.gettotalSection(),
		this.gettotalLecture()))
				.catch(function(error) {
					console.log(error);
	
				});
		},
		gettotalSection:function (){
			this.totalSection= this.course.sectionList.length;
		},
		gettotalLecture:function(){
			var num;
			
			this.course.sectionList.forEach((section) =>{ num+=section.lecturesList.length  }    );
			
			this.totalLecture=num;
		}
		


	},
	mounted: function() {
			this.userAccountCreatTime = document.getElementById("userAccountCreatTime").value;
		this.getCourse();
		

	},

}).mount('#introduceBlock')


var player = videojs('my-video', {
	
	loop: true,
	muted: true,
	width: "800px",
	height: "720px",
	controls: true
});

