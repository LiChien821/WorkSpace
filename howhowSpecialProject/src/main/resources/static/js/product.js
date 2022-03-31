import { createApp } from 'vue'

const dataObj = {

	course: "",
	
	favstatus: "",
	
	shopstatus:"",
	
	addFav:""

};


createApp({
	data() {
		return dataObj;
	},
	mounted: function() {
		this.courseid = document.getElementById("courseid").value;
		axios({
			method: 'get',
			url: '/howhow/findcoursebyid/'+this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }

		})
			.then(response => (this.course = response.data))
			.catch(function(error) {
				console.log(error);
			});
		this.userid = document.getElementById("userid").value;
		axios({
			method: 'get',
			url: '/howhow/findfavoritecoursestatus/'+this.userid+'/'+this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.favstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});
		this.userid = document.getElementById("userid").value;
		axios({
			method: 'get',
			url: '/howhow/findshoppingcartstatus/'+this.userid+'/'+this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response =>(this.shopstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});

	},
	
	methods: {
		addFavorite : function() {
			this.userid = document.getElementById("userid").value;
			this.courseid = document.getElementById("courseid").value;
			axios({
				method: 'post',
				url: '/howhow/insertfavoritecourse',
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },

				data: { userID: this.userid, courseID: this.courseid }
			})
				.then(response =>(this.favstatus = true))
				.catch(function(error) {
					console.log(this.userid);
					console.log(this.courseid);
					console.log(error);
				});
		},
		
		removeFavorite : function() {
			this.userid = document.getElementById("userid").value;
			this.courseid = document.getElementById("courseid").value;
			axios({
				method: 'get',
				url: '/howhow/removefavoritecourse/'+this.userid+"/"+this.courseid,
				headers : {"Access-Control-Allow-Origin": "*" }

			})
				.then(response =>(this.favstatus = false))
				.catch(function(error) {
					console.log(this.userid);
					console.log(this.courseid);
					console.log(error);
				});
		},
		
		addShoppingCart : function() {
			this.userid = document.getElementById("userid").value;
			this.courseid = document.getElementById("courseid").value;
			axios({
				method: 'post',
				url: '/howhow/insertshoppingcart',
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },

				data: { userID: this.userid, courseID: this.courseid }
			})
				.then(response =>(this.shopstatus = true))
				.catch(function(error) {
					console.log(this.userid);
					console.log(this.courseid);
					console.log(error);
				});
		},
		
		removeShoppingCart : function() {
			this.userid = document.getElementById("userid").value;
			this.courseid = document.getElementById("courseid").value;
			axios({
				method: 'get',
				url: '/howhow/removeshoppingcart/'+this.userid+"/"+this.courseid,
				headers : {"Access-Control-Allow-Origin": "*" }

			})
				.then(response =>(this.shopstatus = false))
				.catch(function(error) {
					console.log(this.userid);
					console.log(this.courseid);
					console.log(error);
				});
		},
	}

}).mount('#product')