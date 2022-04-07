Vue.createApp({
    data() {
        return {
            getdata: {},
            postdata:{
                name:'',
                descriptior:''
            },
            oldPostData:{}
        }
    },
    methods: {
        addCategory() {
            if(this.postdata.name.length === 0 || this.postdata.descriptior.length === 0){
                alert("請勿空白")
                return
            }

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
            console.log("do")
        },
        updateCategory(item){
            axios({
                method: 'put',
                url: 'http://localhost:8082/howhow/cms/categorydata',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(item)
            }).then(res =>{
                this.getdata = res.data;
            })

            alert("修改成功")
        }
    },
    mounted() {
        axios.get("http://localhost:8082/howhow/cms/categorydata").then(res => {
            this.getdata = res.data;
        })
    },
}).mount('#app')