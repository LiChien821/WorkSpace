const dataObj = {
	currQuery: ""
}

var app = Vue.createApp({
	data() {
		return dataObj;
	},
	methods: {
		goToCoursePageBySearch: function() {
			self.location.href = "/courses?search=" + this.currQuery;
		}
	}
});
app.mount('#index');