import { createApp } from 'vue';
const dataObj = {
	videoSrcUrl: "../video/atlantic_drone_traveling (720p).mp4",
	videotype: "video/mp4",

	currentTime: "",
	skipTime: "",
	duration: "",


	course: "",
	currentCourseID: "",
	currentSectionID:"",

	sectionList: "",
	lecturesList: "",
	sectionID: ""

};



const app = createApp({
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



createApp({
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

			.then(response => (this.course = response.data, this.sectionList = response.data.sectionList))
			.catch(function(error) {
				console.log(error);

			});

	},

}).mount('#playSectionList')


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