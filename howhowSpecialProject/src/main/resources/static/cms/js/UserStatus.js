Vue.createApp({
    data() {
        return {
            applyData: {},
            userStatusData: {},
            updateUserStatus: '',
            userID: '',
            isNull: true,

        }
    },
    methods: {
        searchUser() {

            axios({
                method: 'get',
                url: 'http://localhost:8082/howhow/cms/userstatusdata/' + this.userID.toString(),
                headers: { 'Content-Type': 'application/json' }
            }).then(res => {
                this.isNull = false;
                this.userStatusData = res.data;
            });
        },
        editUserStatus(item) {
            item.accountLevel = this.updateUserStatus;
            axios({
                method: 'put',
                url: 'http://localhost:8082/howhow/cms/userstatusdata',
                headers: { 'Content-Type': 'application/json' },
                data: JSON.stringify(item)
            }).then(res => {
                this.userStatusData = res.data;
            })

            alert("修改成功")
        },
        editApplyStatus(item) {
            item.applystatus = '已處理';
            axios({
                method: 'put',
                url: 'http://localhost:8082/howhow/cms/applydata',
                headers: { 'Content-Type': 'application/json' },
                data: JSON.stringify(item)
            }).then(res => {
                this.applyData = res.data;
            })
        }
    },
    mounted() {
        axios({
            method: 'get',
            url: 'http://localhost:8082/howhow/cms/applydata',
            headers: { 'Content-Type': 'application/json' },
        }).then(res => {
            this.applyData = res.data;
        })
    }

}).mount('#app')