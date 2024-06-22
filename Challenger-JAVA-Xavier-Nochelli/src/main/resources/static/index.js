const app = Vue.createApp({
    data() {
        return {
            nombre: "",
            apellido: "",
            email: "",
            password: "",
            isSignUpActive: false,
        };
    },
    methods: {
        signin() {
            console.log("Iniciando sesión con", this.email, this.password);
            axios.post("/api/login?email=" + this.email + "&password=" + this.password)
                .then(response => {
                    console.log("response", response);
                    if (response.status === 200) {
                        window.location.href = "./pages/usuary.html";
                    }
                })
                .catch(error => {
                    console.error("Error en la solicitud de inicio de sesión", error);
                    this.handleError(error);
                });
        },

        signup() {
            const requestBody = {
                nombre: this.nombre,
                apellido: this.apellido,
                email: this.email,
                password: this.password
            };

            console.log("Registrando con", requestBody);
            axios.post("/usuary/create", requestBody)
                .then(response => {
                    console.log("Register response", response.data);
                    this.signin();
                })
                .catch(error => {
                    console.error("Error en la solicitud de registro", error);
                    this.handleError(error);
                });
        },

        handleForgotPassword() {
            axios.post('/usuary/forgot-password', null, {
                params: {
                    email: this.email
                }
            })
                .then(response => {
                    Swal.fire({
                        icon: 'success',
                        title: '¡Contraseña enviada!',
                        text: 'Se ha enviado una nueva contraseña al correo electrónico proporcionado.'
                    });
                })
                .catch(error => {
                    console.error('Error al procesar la solicitud:', error);
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Error al procesar la solicitud. Por favor, intenta nuevamente más tarde.'
                    });
                });
        },

        togglePassword() {
            const passwordFields = [document.getElementById("passwordSignIn"), document.getElementById("passwordSignup")];
            passwordFields.forEach(field => {
                field.type = field.type === "password" ? "text" : "password";
            });
            document.getElementById("togglePassword").classList.toggle("fa-eye-slash");
        },

        getFirstname(event) {
            this.nombre = event.target.value;
            console.log("nombre", this.nombre);
        },
        getLastname(event) {
            this.apellido = event.target.value;
            console.log("apellido", this.apellido);
        },

        getEmail(event) {
            this.email = event.target.value;
            console.log("Email", this.email);
        },

        getPassword(event) {
            this.password = event.target.value;
            console.log("password", this.password);
        },

        signInButton() {
            this.isSignUpActive = false;
            if (this.$refs.container) {
                this.$refs.container.classList.remove('right-panel-active');
            }
        },

        signUpButton() {
            this.isSignUpActive = true;
            if (this.$refs.container) {
                this.$refs.container.classList.add('right-panel-active');
            }
        },

        handleError(error) {
            if (error.response) {
                Swal.fire({
                    icon: 'error',
                    title: `Error de respuesta: ${error.response.status}`,
                    text: error.response.data.message || 'Error desconocido',
                    footer: `<pre>${JSON.stringify(error.response.data, null, 2)}</pre>`
                });
            } else if (error.request) {
                Swal.fire({
                    icon: 'error',
                    title: 'Error de solicitud',
                    text: 'No se recibió respuesta del servidor'
                });
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Error general',
                    text: error.message
                });
            }
        }
    }
}).mount('#app');
