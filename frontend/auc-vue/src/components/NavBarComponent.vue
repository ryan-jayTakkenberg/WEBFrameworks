<template>
  <div class="navbar">
    <a href="#">Home</a>
    <a href="#m">My Offers</a>
    <div class="dropdown">
      <button class="dropbtn">My Bids
        <i class="fa fa-caret-down"></i>
      </button>
      <div class="dropdown-content">
        <a href="#">Active</a>
        <a href="#">History</a>
      </div>
    </div>
    <a href="#myaccount">My Account</a>
    <div class="nav-right">
      <a href="#SignUp">Sign Up</a>
      <a href="#LogIn">Log In</a>
    </div>
  </div>
</template>

<script>
export default {
  name: "NavBarComponent"
}
</script>

<style scoped>

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