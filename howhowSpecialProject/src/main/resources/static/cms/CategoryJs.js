Vue.createApp({
    data() {
        return {
            getdata: {},
            postdata:{
                name:'',
                descriptior:''
            }
        }
    },
    methods: {
        addCategory() {
            axios({
                method: 'post',
                url: 'http://localhost:8082/howhow/cms/categorydata',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(this.postdata)
            }).then(res => {
                this.getdata = res.data
            })
        },
        deleteCategory(item) {
            axios({
                method: 'delete',
                url: 'http://localhost:8082/howhow/cms/categorydata',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(item)
            }).then(res =>{
                this.getdata = res.data;
            })
        }
    },
    mounted() {
        axios.get("http://localhost:8082/howhow/cms/categorydata").then(res => {
            this.getdata = res.data;
            console.log(this.getdata);
        })
    },
}).mount('#app')