const dataObj = {
	baseUrl:"https://stoageno1.blob.core.windows.net/mycontainer/",
	videoSrcUrl: "https://stoageno1.blob.core.windows.net/mycontainer/1單元測試.mp4",
	videotype: "video/mp4",

	currentTime: "",
	skipTime: "",
	duration: "",
	notescontext:"",
	notesList:"",

	lecture:"",
	course: "",
	currentCourseID: "",
	currentSectionID:"",
	currentLecturesID:"",
	userAccoountID:"",

	sectionList: "",
	lecturesList: "",
	sectionID: "",
	

};



Vue.createApp({
	data() {
		return dataObj;
	},

	methods: {
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
		handleVideoUrl: function(lecture) {
			this.lecture=lecture;
			this.videoSrcUrl=this.baseUrl+this.lecture.videoSource;
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
	mounted: function() {
		this.currentCourseID = document.getElementById("playPageDeafultId").value;
		axios({
			method: 'get',

			url: '/howhow/api/getCourse/' + this.currentCourseID,
			headers: { "Access-Control-Allow-Origin": "*" },


		})

			.then(response => (this.course = response.data, this.sectionList = response.data.sectionList,this.currentLecturesID=response.data.sectionList[0].lecturesList[0].lecturesID ))
			.catch(function(error) {
				console.log(error);

			});

	},

}).mount('#playSectionList')
const app = Vue.createApp({
	data() {
		return dataObj;
	},
	methods: {
		changetime: function() {
			player.pause();

			if (this.skipTime == 800) {
				this.videoSrcUrl = "4565"
				player.src(this.videoSrcUrl)
			} else {
				this.videoSrcUrl = "../course-videos/42/test.mp4"
				player.src(this.videoSrcUrl)
				player.currentTime(this.skipTime)
			}

		}
	}
}).mount('#form2')

var player = videojs('my-video', {
	sources: [{ src: dataObj.videoSrcUrl }],
	loop: true,
	muted: true,
	width: "800px",
	height: "720px",
	controls: true
});

player.on("playing", () => {
	dataObj.duration = player.duration();
	dataObj.currentTime = player.currentTime();
})