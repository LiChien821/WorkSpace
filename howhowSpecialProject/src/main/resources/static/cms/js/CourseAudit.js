Vue.createApp({
    data() {
        return {
            courseData: [],
            auditStatus: '待審核'
        }
    },
    methods: {
        passAudit(item) {
            axios({
                method: 'put',
                url: '/cms/coursedata/2',
                headers: { 'Content-Type': 'application/json' },
                data: JSON.stringify(item)
            }).then(res => {
                this.courseData = res.data;
            })
        },
        failAudti(item) {
            axios({
                method: 'put',
                url: '/cms/coursedata/3',
                headers: { 'Content-Type': 'application/json' },
                data: JSON.stringify(item)
            }).then(res => {
                this.courseData = res.data;
            })
        }
    },
    mounted() {
        axios({
            method: 'get',
            url: '/cms/coursedata',
            headers: { 'Content-Type': 'application/json' }
        }).then(res => {
            this.courseData = res.data;
        })
    },
}).mount('#app')