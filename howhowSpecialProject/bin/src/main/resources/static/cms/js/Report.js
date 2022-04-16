Vue.createApp({
    data() {
        return {
            onActive: 'bulletin',
            bulletin: [],
            reply:[]
        }
    },
    methods: {
        handleBulletinReport(item) {
            
            if (item.handle !== '請選擇') {
                axios({
                    method: 'put',
                    url: '/cms/bulletinreport/' + item.handle,
                    headers: { 'Content-Type': 'application/json' },
                    data: JSON.stringify(item)
                }).then(res => {
                    this.bulletin = [];
                    res.data.forEach(element => {
                        element.handle = '請選擇';
                        this.bulletin.push(element);
                    });
                })
            }
        },
        heandleReplyReport(item) {
            if (item.handle !== '請選擇') {
                axios({
                    method: 'put',
                    url: '/cms/replyreport/' + item.handle,
                    headers: { 'Content-Type': 'application/json' },
                    data: JSON.stringify(item)
                }).then(res => {
                    this.reply = [];
                    res.data.forEach(element => {
                        element.handle = '請選擇';
                        this.reply.push(element);
                    });
                })
            }
        }
    },
    mounted() {
        axios({
            method: 'get',
            url: '/cms/bulletinreport',
            headers: { 'Content-Type': 'application/json' }
        }).then(res => {
            res.data.forEach(element => {
                element.handle = '請選擇';
                this.bulletin.push(element);
            });
        }),
        axios({
            method: 'get',
            url: '/cms/replyreport',
            headers: { 'Content-Type': 'application/json' }
        }).then(res => {
            res.data.forEach(element => {
                element.handle = '請選擇';
                this.reply.push(element);
            });
        })
    },
}).mount('#app')