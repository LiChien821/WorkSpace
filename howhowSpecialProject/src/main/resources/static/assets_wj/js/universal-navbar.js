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
			cUrl: "#",
			cItemName: "開發",
			subCategories:[
				{
					scUrl: "#",
					scItemName: "網頁開發"
				},
				{
					scUrl: "#",
					scItemName: "資料科學"
				},
			]
		},
		{
			cUrl: "#",
			cItemName: "IT與軟體",
			subCategories:[
				{
					scUrl: "#",
					scItemName: "IT認證"
				},
				{
					scUrl: "#",
					scItemName: "硬體"
				},
			]
		}
		
	],
	recentCourseTitle: "從一開始到最後的教學都讓我驚豔",
	recentCourseProgress: 20,
	recentCourseInfo: "課程",
	courseInfos:[
		{
			ctitle: "從一開始到最後的教學都讓我驚豔",
			cProgress: 20,
			cInfo: "課程"
		},
		{
			ctitle: "從一開始到最後的教學都讓我驚豔",
			cProgress: 20,
			cInfo: "課程"
		}
	],
	shoppingInfos:[
		{
			ctitle: "從一開始到最後的教學都讓我驚豔",
			cStatus: "已開課",
			cPrice: 1100
		},
		{
			ctitle: "從一開始到最後的教學都讓我驚豔",
			cStatus: "募資中",
			cPrice: 1100
		},
		{
			ctitle: "從一開始到最後的教學都讓我驚豔",
			cStatus: "已開課",
			cPrice: 1100
		}
	],
	reminderInfos: [
		{
			content: "從一開始到最後的教學都讓我驚豔，沒想到原來唱歌前需要做那麼多的前置準備，以及更多時間的練習基礎、技巧，還有給自己更多的時間去感受自己的變化<謝謝老師開了新的一扇窗，讓我也更注意自己的嘴巴、舌頭、喉嚨等部位，想要讓他們更能放鬆的應用不同技巧!",
			dateTime: "一個月前"
		},
		{
			content: "從一開始到最後的教學都讓我驚豔，沒想到原來唱歌前需要做那麼多的前置準備，以及更多時間的練習基礎、技巧，還有給自己更多的時間去感受自己的變化<謝謝老師開了新的一扇窗，讓我也更注意自己的嘴巴、舌頭、喉嚨等部位，想要讓他們更能放鬆的應用不同技巧!",
			dateTime: "一個月前"
		},
		{
			content: "從一開始到最後的教學都讓我驚豔，沒想到原來唱歌前需要做那麼多的前置準備，以及更多時間的練習基礎、技巧，還有給自己更多的時間去感受自己的變化<謝謝老師開了新的一扇窗，讓我也更注意自己的嘴巴、舌頭、喉嚨等部位，想要讓他們更能放鬆的應用不同技巧!",
			dateTime: "一個月前"
		},
		{
			content: "從一開始到最後的教學都讓我驚豔，沒想到原來唱歌前需要做那麼多的前置準備，以及更多時間的練習基礎、技巧，還有給自己更多的時間去感受自己的變化<謝謝老師開了新的一扇窗，讓我也更注意自己的嘴巴、舌頭、喉嚨等部位，想要讓他們更能放鬆的應用不同技巧!",
			dateTime: "一個月前"
		}
	]
};

const app = createApp({
	data() {
		return dataObj;
	},
	mounted: function () {
		function checkLoggedStatus(){
			return axios.get(
				"/checkLoginStatus",
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
		.all([checkLoggedStatus()])
		.then(axios.spread((...responses) => {
			const resp1 = responses[0];
			this.isLogged = resp1.data;
			// console.log(resp1);
		})).catch(errors => {
			console.log(errors);
		})

	},
	methods: {
		goToCoursePage: function(query, categorId) {
			var query = 1;
			var categoryId = 1;
			self.location.href = "/howhow/searchCourseInfo/" + query;
			// this.$router.push('https://www.youtube.com/'); 
		}
	}
})
// app.use(router);
app.mount('#howhowdo-navbar');