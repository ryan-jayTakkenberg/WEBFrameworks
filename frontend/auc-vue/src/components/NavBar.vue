<script >
export default {
  name: 'NavBarComponent',
}


</script>
<template>
  <nav>
    <div class="navbar-content">
      <ul class="navbar-links">
        <li><a href="default.asp">Home</a></li>
        <li><a href="default.asp">My Offers</a></li>
        <li><a href="default.asp">My Bids</a></li>
        <li><a href="default.asp">My Account</a></li>
      </ul>

    </div>
  </nav>
</template>

<style scoped>
.navbar-content {
  height: 50px;
  background-color: #b6780e;
  border: 1px solid white;

}
li {
  float: left;
  background-color: #593b07;
  width: 150px;
  height: auto;
}

a {
  display: block;
  padding: 8px;
}

.navbar-links {

  display: flex;
  gap: 10px;
  padding: 0px;
  list-style-type: none;
  margin: 0;
}
.navbar-links a {
  text-decoration: none;
  color: white;
}


</style>
