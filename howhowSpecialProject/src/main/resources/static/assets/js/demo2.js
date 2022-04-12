Vue.createApp({
    data() {
        return {
            chapters:[
                {
                    id:1,
                    name:'第一章'
                },
                {
                    id:2,
                    name:'第二章'
                }
            ],
            sections:[
                {
                    id:1,
                    chaperid:1,
                    chapername:'第一章',
                    name:'第一單元'
                },
                {
                    id:2,
                    chaperid:1,
                    chapername:'第一章',
                    name:'第二單元'
                },
                {
                    id:3,
                    chaperid:2,
                    chapername:'第二章',
                    name:'第一單元'
                },
                {
                    id:4,
                    chaperid:2,
                    chapername:'第二章',
                    name:'第二單元'
                },
                {
                    id:5,
                    chaperid:2,
                    chapername:'第二章',
                    name:'第三單元'
                }
            ],
            // 被選擇的章節裡的單元
            selectedSection:[],
            // 要修改的物件
            catchChapter:[],
            // 修改中的內容
            catchContent:'',
            // 被選取的章節名稱
            catchChaptername:''
        }
    },
    methods: {
        // 顯示被選擇章節的單元
        showSection(item) {
            this.selectedSection = [];
            this.getChapterName(item);
            this.sections.forEach(element => {
                if(element.chaperid == item.id){
                    this.selectedSection.push(element);
                }
            });
        },
        // 切換編輯模式
        edditChapter(item) {
            this.catchChapter = item;
            this.catchContent = item.name
        },
        // 確認修改內容，切回普通模式
        commitEdit(item) {
            item.name = this.catchContent;
            this.catchChapter = []
            alert("修改成功")
        },
        // 取得被選取的章節名稱
        getChapterName(item){
            this.catchChaptername = item.name
        }
    },

}).mount('#app')