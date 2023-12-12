<template>
  <div>
    <h2>Please provide your login credentials:</h2>
    <div class="form">
      <label for="userEmail"><b>Email</b></label>
      <input class="inputfieldText" type="text" v-model="email" placeholder="Please enter an e-mail">
      <label for="password"><b>Password</b></label>
      <input class="inputfieldText" type="password" v-model="password" placeholder="Enter your password">
      <button @click="signIn">Sign in</button>
    </div>
    <div>
      <h3>Current Token: </h3>
      <p class="token">{{ JWT }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "LogInComponent",
  inject: ['sessionService'],
  data(){
    return{
      password: "",
      email: "",
      JWT: null,
    }
  },
  methods: {
    async signIn(){
      try {
        const account = await this.sessionService.asyncSignIn(
            this.email,
            this.password
        )

        if (account){
          this.JWT = this.sessionService.currentToken;
        }
      } catch (error){
        console.log("Error tijdens het inloggen" + error)
      }
    }
  }
}
</script>

<style scoped>
.form {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 70%;
  margin: auto;
}

.inputfieldText {
  width: 60%;
  padding: 8px;
  margin-bottom: 10px;
}

button {
  margin-top: 10px;
  height: 35px;
  width: 10%;
  background-color: pink;
  border: none;
  font-size: 16px;
  cursor: pointer;
}

button:hover {
  background-color: sandybrown;
}
</style>