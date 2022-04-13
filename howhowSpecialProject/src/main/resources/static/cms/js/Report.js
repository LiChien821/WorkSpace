Vue.createApp({
    data() {
        return {
            onActive:'bulletin',
            bulletin:''
        }
    },
    methods: {

    },
    mounted() {
        axios({
            method:'get',
            url:'/cms/bulletinreport',
            headers:{'Content-Type':'application/json'}
        }).then(res => {
            this.bulletin = res.data
        })
    },
}).mount('#app')