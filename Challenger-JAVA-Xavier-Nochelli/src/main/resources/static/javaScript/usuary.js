const { createApp } = Vue

const app = createApp({
    data() {
        return {
            user:null,
        }
    },
    created() {
        this.loadData()
    },
    methods: {
        loadData() {
            axios.get("/usuary/current")
                .then(response => {
                    this.user = response.data
                    console.log(this.user)
                })
                .catch(error => console.log(error))
        },

    logout() {
        axios.post("/api/logout")
            .then(response => {
                console.log("LogOut", response)
                window.location.href = "/index.html"
            })
    },

}// Aca termina Methods
}).mount('#app')