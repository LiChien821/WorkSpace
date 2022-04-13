Vue.createApp({
    data() {
        return {
            testdata:{
                bulletinid:1,
                reporttypeid:1
            }
        }
    },
    methods: {
        test(){
            axios({
                method: 'post',
                url: '/api/bulletinreport',
                headers: { 'Content-Type': 'application/json' },
                data: JSON.stringify(this.testdata)
            })
        }
    },
}).mount('#app')