import { createApp } from 'vue'

let returnSeconds = 20;

createApp({
	data() {
		return dataObj;
	},

	mounted: function() {

		//this.userid = document.getElementById("userid").value;



	},

	methods: {
		goLearning: function(courseid) {
			location.href = '/product?id=' + courseid;
		},

		"goMycourse": function() {
			location.href = 'http://localhost/mycourse';
		},

		reciprocal: function() {
			document.getElementById("showtime").innerHTML = returnSeconds;
			setTimeout(reciprocal, 1000);
			returnSeconds -= 1;

			if (returnSeconds == 97) {
				location.href = 'http://localhost/mycourse';
			}
		}



	}

}).mount('#checkoutsucceed')