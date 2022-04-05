import { createApp } from 'vue'

const dataObj = {

	refresh: true,

	favdetail: "",

	shopdetail: "",

	favstatus: "",

	shopstatus: "",

	totalprice: 0,

	userid: "",
	
	purchasedstatus: "",
	
	orderisdisabled: ""

};

createApp({
	data() {
		return dataObj;
	},

	mounted: function() {

		this.userid = document.getElementById("userid").value;

		this.findfavoritecourse();

		this.findshoppingcart();
	
		this.findtotalprice();
		
		axios({
			method: 'get',
			url: '/howhow/findfavoritecoursestatusbyuserid/' + this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.favstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});
		axios({
			method: 'get',
			url: '/howhow/findshoppingcartstatusbyuserid/' + this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.shopstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});
		axios({
			method: 'get',
			url: '/howhow/findpurchasedcoursestatusbyuserid/'+ this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.purchasedstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});
			
	},

	methods: {
		moveToFavorite: function(courseid) {
			this.userid = document.getElementById("userid").value;
			const index = this.shopstatus.indexOf(courseid);
			this.refresh = false;
			axios({
				method: 'get',
				url: '/howhow/movetofavoritecourse/' + this.userid + "/" + courseid,
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => (this.favstatus.push(courseid),
					this.shopstatus.splice(index, 1)
				))
				.catch(function(error) {
					console.log(error);
				});
			setTimeout(() => {
				this.findshoppingcart();
				this.findfavoritecourse();
				this.findtotalprice();
				this.refresh = true;
			}, 20);

		},
		removeShoppingCart: function(courseid) {
			this.userid = document.getElementById("userid").value;
			const index = this.shopstatus.indexOf(courseid);
			this.refresh=false;
			axios({
				method: 'get',
				url: '/howhow/removeshoppingcart/'+this.userid+'/'+courseid,
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => (this.shopstatus.splice(index, 1)))
				.catch(function(error) {
					console.log(error);
				});
			setTimeout(() => {
				this.findshoppingcart();
				this.findfavoritecourse();
				this.findtotalprice();
				this.refresh = true
			}, 20);
			
		},
		removeFavoriteCourse: function(courseid) {
			this.userid = document.getElementById("userid").value;
			const index = this.favstatus.indexOf(courseid);
			this.refresh=false;
			axios({
				method: 'get',
				url: '/howhow/removefavoritecourse/'+this.userid+'/'+courseid,
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => (this.favstatus.splice(index, 1)))
				.catch(function(error) {
					console.log(error);
				});
			setTimeout(() => {
				this.findfavoritecourse();
				this.refresh = true
			}, 20);
		},
		createOrder: function() {
			this.userid = document.getElementById("userid").value;
			axios({
				method: 'get',
				url: '/howhow/createorder/'+this.userid,
				headers: { "Access-Control-Allow-Origin": "*" }
			})
				.then(window.location.href='/howhow/')
				.catch(function(error) {
					console.log(error);
				})
			
		},
		addShoppingCart: function(courseid) {
			this.userid = document.getElementById("userid").value;
			this.refresh=false;
			axios({
				method: 'post',
				url: '/howhow/insertshoppingcart',
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },
				data: { userID: this.userid, courseID: courseid }
			})
				.then(response => (this.shopstatus.push(courseid)))
				.catch(function(error) {
					console.log(error);
				});
			setTimeout(() => {
				this.findshoppingcart();
				this.findfavoritecourse();
				this.findtotalprice();
				this.refresh = true
			}, 20);
		},
		findfavoritecourse() {
			axios({
				method: 'get',
				url: '/howhow/findfavoritecoursedetailbyuserid/' + this.userid,
				headers: { "Access-Control-Allow-Origin": "*" }
			})
				.then(response => (this.favdetail = response.data))
				.catch(function(error) {
					console.log(error);
				});
		},
		findshoppingcart() {
			axios({
				method: 'get',
				url: '/howhow/findshoppingcartdetailbyuserid/' + this.userid,
				headers: { "Access-Control-Allow-Origin": "*" }
			})
				.then(response => (this.shopdetail = response.data))
				.catch(function(error) {
					console.log(error);
				});
		},
		findtotalprice() {
			setTimeout(() => {
				this.totalprice=0;
				for (let i = 0; i < this.shopdetail.length; i++) {
					this.totalprice = this.totalprice + this.shopdetail[i].discountprice;
				}
				if(this.totalprice==0) {
					this.orderisdisabled=true;
				} else {
					this.orderisdisabled=false;
				}
			}, 20);
		}
	}

}).mount('#myshop')