import { createApp } from 'vue'

const dataObj = {

	course: "",
	
	favstatus: "",
	
	shopstatus:"",
	
	addFav:"",
	
	purchasedstatus:"",
	
	ranks:"",
	
	res:"",
	
	pageNo:""

};


createApp({
	data() {
		return dataObj;
	},
	mounted: function() {
		this.courseid = document.getElementById("courseid").value;
		axios({
			method: 'get',
			url: '/howhow/api/findcoursebyid/'+this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }

		})
			.then(response => (this.course = response.data))
			.catch(function(error) {
				console.log(error);
			});
		this.userid = document.getElementById("userid").value;
		axios({
			method: 'get',
			url: '/howhow/api/findfavoritecoursestatus/'+this.userid+'/'+this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.favstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});
		this.userid = document.getElementById("userid").value;
		axios({
			method: 'get',
			url: '/howhow/api/findshoppingcartstatus/'+this.userid+'/'+this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response =>(this.shopstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});
		this.userid = document.getElementById("userid").value;
		axios({
			method: 'get',
			url: '/howhow/api/findpurchasedcoursestatus/'+this.userid+'/'+this.courseid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response =>(this.purchasedstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});
		this.pageNo = document.getElementById("pageNo").value;
		axios({
			method: 'get',
			url: '/howhow/api/querycourserankbycourseid/'+this.courseid +"/" +this.pageNo,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.ranks = response.data, this.res = response))
			.catch(function(error) {
				console.log(error)
			});
	},
	
	methods: {
		addFavorite : function() {
			this.userid = document.getElementById("userid").value;
			this.courseid = document.getElementById("courseid").value;
			axios({
				method: 'post',
				url: '/howhow/api/insertfavoritecourse',
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
				url: '/howhow/api/removefavoritecourse/'+this.userid+"/"+this.courseid,
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
				url: '/howhow/api/insertshoppingcart',
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
				url: '/howhow/api/removeshoppingcart/'+this.userid+"/"+this.courseid,
				headers : {"Access-Control-Allow-Origin": "*" }

			})
				.then(response =>(this.shopstatus = false))
				.catch(function(error) {
					console.log(this.userid);
					console.log(this.courseid);
					console.log(error);
				});
		},
		
		nextPage: function() {
			if (document.getElementById("pageNo").value != this.ranks.totalPages) {
				document.getElementById("pageNo").value = (document.getElementById("pageNo").value) * 1 + 1;
			}
				this.pageNo = document.getElementById("pageNo").value;
				axios({
					method: 'get',
					url: '/howhow/api/querycourserankbycourseid/' + this.courseid + "/" + this.pageNo,
					headers: { "Access-Control-Allow-Origin": "*" }
				})
					.then(response => (
						this.ranks = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
				});
		},
		
		previousPage: function() {
			if (document.getElementById("pageNo").value != 1) {
				document.getElementById("pageNo").value = (document.getElementById("pageNo").value) * 1 - 1;
			}
				this.pageNo = document.getElementById("pageNo").value;
				axios({
					method: 'get',
					url: '/howhow/api/querycourserankbycourseid/' + this.courseid + "/" + this.pageNo,
					headers: { "Access-Control-Allow-Origin": "*" }
				})
					.then(response => (
						this.ranks = response.data, this.res = response))
					.catch(function(error) {
						console.log(error);
				});
		},
	}

}).mount('#product')