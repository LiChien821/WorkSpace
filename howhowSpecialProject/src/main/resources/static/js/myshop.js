import { createApp } from 'vue'

const dataObj = {

	favdetail: "",
	
	shopdetail: "",
	
	favstatus:"",
	
	shopstatus:"",
	
	totalprice: 0,
	
	userid:""
	
};

createApp({
	data() {
		return dataObj;
	},
	
	mounted: function() {
		
		this.userid = document.getElementById("userid").value;
		
		axios({
			method: 'get',
			url: '/howhow/findfavoritecoursedetailbyuserid/'+this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => (this.favdetail = response.data))
			.catch(function(error) {
				console.log(error);
			});
			
		axios({
			method:'get',
			url: '/howhow/findshoppingcartdetailbyuserid/'+this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response =>(this.shopdetail = response.data))
			.catch(function(error) {
				console.log(error);
			});
		axios({
			method:'get',
			url: '/howhow/findfavoritecoursestatusbyuserid/'+this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response =>(this.favstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});
		axios({
			method: 'get',
			url: '/howhow/findshoppingcartstatusbyuserid/'+this.userid,
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response =>(this.shopstatus = response.data))
			.catch(function(error) {
				console.log(error);
			});
		setTimeout(() => {
			for (let i = 0;  i < this.shopdetail.length; i++) {
			this.totalprice = this.totalprice + this.shopdetail[i].discountprice;
		}
		}, 100);
		

	}
}).mount('#myshop')