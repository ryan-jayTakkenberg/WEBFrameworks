<template>
  <div class="navbar">
    <router-link to="/" active-class="highlight">Home</router-link>
    <div class="dropdown">
      <button class="dropbtn" >Offers
        <i class="fa fa-caret-down"></i>
      </button>
      <div class="dropdown-content">
        <router-link to="/offers/overview31" active-class="highlight">All Offers overview</router-link>
        <router-link to="/offers/overview32" active-class="highlight">Offers details(comp)</router-link>
        <router-link to="/offers/overview33" active-class="highlight">Offers edit(router)</router-link>
        <router-link to="/offers/overview34" active-class="highlight">Offers edit(managed)</router-link>
        <router-link to="/offers/overview37" active-class="highlight">3.7(Don't know a name )</router-link>
      </div>
    </div>
    <div class="dropdown">
      <button class="dropbtn" >My Bids
        <i class="fa fa-caret-down"></i>
      </button>
      <div class="dropdown-content">
        <router-link to="/offer/active" active-class="highlight">Active</router-link>
        <router-link to="/offer/history" active-class="highlight">History</router-link>
      </div>
    </div>
    <router-link to="/myaccount" active-class="highlight">My Account</router-link>
    <div class="nav-right">
        <router-link v-if="!sessionService.isAuthenticated()" to="/sign-up" active-class="highlight">Sign Up</router-link>
        <router-link v-if="!sessionService.isAuthenticated()" to="/sign-in" active-class="highlight">Log In</router-link>
        <router-link v-if="sessionService.isAuthenticated()" to="/sign-out" active-class="highlight" @click="logout">Log Out</router-link>


    </div>
  </div>
</template>

<script>
export default {
  name: "NavBarSbComponent",
  inject: ['sessionService'],
  data(){
    return{

    }
  },
  methods: {
    logout() {
      this.sessionService.signOut();
    }
  }

}
</script>

<style scoped>
.highlight{
  background-color: sandybrown;
}

/* Navbar container */
.navbar {
  overflow: hidden;
  background-color: pink;
  margin-top: 5px;
}

/* Links inside the navbar */
.navbar a {
  float: left;
  font-size: 16px;
  color: black;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

/* The dropdown container */
.dropdown {
  float: left;
  overflow: hidden;
}

/* Dropdown button */
.dropdown .dropbtn {
  font-size: 16px;
  border: none;
  outline: none;
  color: black;
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.navbar a:hover, .dropdown:hover .dropbtn {
  background-color: sandybrown;
}

/* Dropdown content (hidden by default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: pink;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.dropdown-content a:hover {
  background-color: sandybrown;
}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
  display: block;
}

.nav-right{
  position: relative;
  float: right;
}
</style>