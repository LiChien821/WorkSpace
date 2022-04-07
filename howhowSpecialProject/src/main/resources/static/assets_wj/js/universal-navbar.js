import { createApp } from 'vue'

const dataObj = {
	test: "",
	userId: 1,
	courseCreatorId: "",
	userName: "Big O",
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

createApp({

	data() {
		return dataObj;
	},
	mounted: function () {
	}
	
}).mount('#howhowdo-navbar')