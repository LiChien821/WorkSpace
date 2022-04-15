import { createApp } from 'vue'
// import { createRouter, createWebHistory}  from 'vue-router'
// const LoginPage = {template: '<div>Home</div>'}
// const routes = [
//     { path: '/', name: 'home', component: "" },
//     { path: '/login', redirect: '/' },
//     { path: '/howhow/login', name: 'login', component: () => {
// 		LoginPage
// 	}}
// ]
// const router = createRouter({
//     history:createWebHistory(),
//     routes: routes
// })


const dataObj = {
	test: "",
	userId: 1,
	courseCreatorId: "",
	userName: "Big O",
	currQuery: "",
	isLogged: true,

	categories:[
		{
			cId: "0",
			cItemName: "全部",
		}
		
	],
	blobSetting: "",
	courses: ""
	
};

const app = createApp({
	data() {
		return dataObj;
	},
	mounted: function () {

		axios({
			method: 'get',
			url: '/api/getBlobUrl',
			headers: { "Access-Control-Allow-Origin": "*" },
		})
			.then(response => {
				this.blobSetting = response.data;
				// console.log("this blobSetting", this.blobSetting);
			})
			.catch(function (error) {
				console.log(error);
			})

		axios({
			method: 'get',
			url: '/api/findallcourses/1',
			headers: { "Access-Control-Allow-Origin": "*" }
		})
			.then(response => {
				this.courses = [];
				// console.log("this response", response.data);
				var i = 0;
				var n = response.data['content'].length;
				// console.log("n", n);
				while (i < n) {
					if (i >= 3 ) {break}
					this.courses.push(response.data['content'][i]);
					i++;
				}
				// console.log("this courses", this.courses);
			})
			.catch(function (error) {
				console.log(error);
			});




		function checkLoggedStatus(){
			return axios.get(
				"/api/checkLoginStatus",
				{
					headers: { 
						'Content-Type': 'application/x-www-form-urlencoded',
						"data-Type": "JSON",
						"Access-Control-Allow-Origin": "*"
					}
				}
			);
		}

		function getAllCategoryInfo(){
			return axios.get(
				"/api/getallcategory",
				{
					headers: { 
						'Content-Type': 'application/x-www-form-urlencoded',
						"data-Type": "JSON",
						"Access-Control-Allow-Origin": "*"
					}
				}
			);
		}


		axios
		.all([checkLoggedStatus(), getAllCategoryInfo()])
		.then(axios.spread((...responses) => {
			const resp1 = responses[0];
			this.isLogged = resp1.data;

			const resp2 = responses[1];
			for (var i = 0; i < resp2.data.length; i++) {
				const item = resp2.data[i];
				var newCategoryObject = {
					cId: "",
					cItemName: ""
				}
				newCategoryObject.cId = item["id"].toString();
				newCategoryObject.cItemName = item["name"];
				this.categories.push(newCategoryObject);
			  }
		})).catch(errors => {
			console.log(errors);
		})

	},
	methods: {

		goToCoursePageByCategoryId: function(categroyId) {
			if (categroyId == 0) {
				self.location.href = "/courses";
			} else {
				self.location.href = "/courses?category=" + categroyId;
			}
		},

		goToCoursePageBySearch: function() {
			self.location.href = "/courses?search=" + this.currQuery;
		},

		sendTeacherApply: function() {
			var toastLiveExample = document.getElementById('liveToast')
			axios({
				method: 'post',
				url: '/api/applydata',
				headers: { 'Content-Type': 'application/json', "Access-Control-Allow-Origin": "*" }
			})
				.then((response) => {
					console.log("alreadyApplied status: ", response.data["alreadyApplied"]);
					console.log("sendTeacherApply finished");
					var toast = new bootstrap.Toast(toastLiveExample)
    				toast.show()
				})
				.catch((error) => {
					console.log(error);
				});
		}
	}
})
//  app.use(router);
app.mount('#howhowdo-navbar');