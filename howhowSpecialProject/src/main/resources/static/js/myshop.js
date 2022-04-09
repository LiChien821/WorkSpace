import { createApp } from 'vue'

const dataObj = {

	favdetail: "",

	shopdetail: "",

	favstatus: "",

	shopstatus: "",

	totalprice: 0,

	userid: "",

	purchasedstatus: "",

	orderisdisabled: "",

	ccf: false,
	
	blobSetting:"",
	
	orderdto:"",
	
	TotalAmount:"",
	
	TradeDesc:"",
	
	ItemName:"",
	
	CustomField1:""

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
			url: '/api/findfavoritecoursestatusbyuserid/' + this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.favstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});
		axios({
			method: 'get',
			url: '/api/findshoppingcartstatusbyuserid/' + this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.shopstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});
		axios({
			method: 'get',
			url: '/api/findpurchasedcoursestatusbyuserid/' + this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => {
				this.purchasedstatus = response.data;
				console.log("pur:", this.purchasedstatus);
			})
			.catch(function(error) {
				console.log(error);
			});
		axios({
			method: 'get',
			url: '/api/getBlobUrl',
			headers: { "Access-Control-Allow-Origin": "*" },
		})
			.then(response => (this.blobSetting = response.data))
			.catch(function(error) {
				console.log(error);
			})

	},

	methods: {
		moveToFavorite: function(courseid) {
			axios({
				method: 'get',
				url: '/api/checklogin',
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => {
					if (response.data == "") {
						location.href = '/login';
					} else {
						this.moveToFavoriteAction(courseid, response.data);
					}
				})
				.catch(function(error) {
					console.log(error);
				})
		},


		moveToFavoriteAction(courseid, userid) {
			const index = this.shopstatus.indexOf(courseid);
			axios({
				method: 'get',
				url: '/api/movetofavoritecourse/' + userid + "/" + courseid,
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
			}, 20);

		},

		removeShoppingCart: function(courseid) {
			axios({
				method: 'get',
				url: '/api/checklogin',
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => {
					if (response.data == "") {
						location.href = '/login';
					} else {
						this.removeShoppingCartAction(courseid, response.data);
					}
				})
				.catch(function(error) {
					console.log(error);
				})
		},

		removeShoppingCartAction(courseid, userid) {
			const index = this.shopstatus.indexOf(courseid);
			axios({
				method: 'get',
				url: '/api/removeshoppingcart/' + userid + '/' + courseid,
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
			}, 20);

		},

		removeFavoriteCourse: function(courseid) {
			axios({
				method: 'get',
				url: '/api/checklogin',
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => {
					if (response.data == "") {
						location.href = '/login';
					} else {
						this.removeFavoriteCourseAction(courseid, response.data);
					}
				})
				.catch(function(error) {
					console.log(error);
				})
		},

		removeFavoriteCourseAction(courseid, userid) {
			const index = this.favstatus.indexOf(courseid);
			axios({
				method: 'get',
				url: '/api/removefavoritecourse/' + userid + '/' + courseid,
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => (this.favstatus.splice(index, 1)))
				.catch(function(error) {
					console.log(error);
				});
			setTimeout(() => {
				this.findfavoritecourse();
			}, 20);
		},

		createOrder: function() {
			axios({
				method: 'get',
				url: '/api/checklogin',
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => {
					if (response.data == "") {
						location.href = '/login';
					} else {
						this.createOrderAction(response.data);
					}
				})
				.catch(function(error) {
					console.log(error);
				})
		},

		createOrderAction(userid) {
			axios({
				method: 'get',
				url: '/api/createorder/' + userid,
				headers: { "Access-Control-Allow-Origin": "*" }
			})
				.then(response =>{
					document.getElementById('TotalAmount').value=response.data.totalamount;
					document.getElementById('ItemName').value=response.data.itemname;
					document.getElementById('TradeDesc').value=response.data.description;
					document.getElementById('CustomField1').value=response.data.customfield1;
				})
				.catch(function(error) {
					console.log(error);
				})

		},

		addShoppingCart: function(courseid) {
			axios({
				method: 'get',
				url: '/api/checklogin',
				headers: { "Access-Control-Allow-Origin": "*" },
			})
				.then(response => {
					if (response.data == "") {
						location.href = '/login';
					} else {
						this.addShoppingCartAction(courseid, response.data);
					}
				})
				.catch(function(error) {
					console.log(error);
				})
		},

		addShoppingCartAction(courseid, userid) {
			axios({
				method: 'post',
				url: '/api/insertshoppingcart',
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" },
				data: { userID: userid, courseID: courseid }
			})
				.then(response => (this.shopstatus.push(courseid)))
				.catch(function(error) {
					console.log(error);
				});
			setTimeout(() => {
				this.findshoppingcart();
				this.findfavoritecourse();
				this.findtotalprice();
			}, 20);
		},
		findfavoritecourse() {
			axios({
				method: 'get',
				url: '/api/findfavoritecoursedetailbyuserid/' + this.userid,
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
				url: '/api/findshoppingcartdetailbyuserid/' + this.userid,
				headers: { "Access-Control-Allow-Origin": "*" }
			})
				.then(response => (this.shopdetail = response.data))
				.catch(function(error) {
					console.log(error);
				});
		},
		findtotalprice() {
			setTimeout(() => {
				this.totalprice = 0;
				for (let i = 0; i < this.shopdetail.length; i++) {
					this.totalprice = this.totalprice + this.shopdetail[i].discountprice;
				}
				if (this.totalprice == 0) {
					this.orderisdisabled = true;
				} else {
					this.orderisdisabled = false;
				}
			}, 200);
		}
	}

}).mount('#myshop')