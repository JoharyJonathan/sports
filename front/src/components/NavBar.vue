<template>
    <nav class="bg-blue-600 shadow-lg p-4 sticky top-0 z-50">
      <div class="container mx-auto flex flex-col md:flex-row items-center justify-between">
        <!-- Logo/Brand -->
        <div class="flex items-center mb-4 md:mb-0">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8 mr-2" viewBox="0 0 20 20" fill="currentColor">
            <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM9.555 7.168A1 1 0 008 8v4a1 1 0 001.555.832l3-2a1 1 0 000-1.664l-3-2z" clip-rule="evenodd" />
          </svg>
          <span class="text-xl font-bold tracking-tight">SPORT ZONE</span>
        </div>
  
        <!-- Navigation Links -->
        <div class="flex flex-wrap justify-center space-x-1 md:space-x-4">
          <RouterLink to="/" class="nav-link group" active-class="nav-active">
            <span class="group-hover:border-b-2 group-hover:border-yellow-400 pb-1 px-1 transition duration-200">Home</span>
          </RouterLink>
          
          <RouterLink to="/products" class="nav-link group" active-class="nav-active">
            <span class="group-hover:border-b-2 group-hover:border-yellow-400 pb-1 px-1 transition duration-200">Products</span>
          </RouterLink>
          
          <RouterLink to="/cart" class="nav-link group" active-class="nav-active">
            <span class="flex items-center group-hover:border-b-2 group-hover:border-yellow-400 pb-1 px-1 transition duration-200">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-1" viewBox="0 0 20 20" fill="currentColor">
                <path d="M3 1a1 1 0 000 2h1.22l.305 1.222a.997.997 0 00.01.042l1.358 5.43-.893.892C3.74 11.846 4.632 14 6.414 14H15a1 1 0 000-2H6.414l1-1H14a1 1 0 00.894-.553l3-6A1 1 0 0017 3H6.28l-.31-1.243A1 1 0 005 1H3z" />
              </svg>
              Cart
            </span>
          </RouterLink>
          
          <RouterLink to="/profile" class="nav-link group" active-class="nav-active">
            <span class="flex items-center group-hover:border-b-2 group-hover:border-yellow-400 pb-1 px-1 transition duration-200">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-1" viewBox="0 0 20 20" fill="currentColor">
                <path fill-rule="evenodd" d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" clip-rule="evenodd" />
              </svg>
              Profile
            </span>
          </RouterLink>
          
          <RouterLink to="/admin" class="nav-link group" active-class="nav-active">
            <span class="group-hover:border-b-2 group-hover:border-yellow-400 pb-1 px-1 transition duration-200">Admin</span>
          </RouterLink>
        </div>
  
        <!-- Authentication Buttons -->
        <div class="flex mt-4 md:mt-0 space-x-2">
          <RouterLink to="/login" class="bg-gray-100 font-bold py-2 px-4 rounded-full transition duration-200 text-sm flex items-center">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 mr-1" viewBox="0 0 20 20" fill="currentColor">
              <path fill-rule="evenodd" d="M3 3a1 1 0 011 1v12a1 1 0 11-2 0V4a1 1 0 011-1zm7.707 3.293a1 1 0 010 1.414L9.414 9H17a1 1 0 110 2H9.414l1.293 1.293a1 1 0 01-1.414 1.414l-3-3a1 1 0 010-1.414l3-3a1 1 0 011.414 0z" clip-rule="evenodd" />
            </svg>
            Log In
          </RouterLink>
          
          <RouterLink to="/signup" class="bg-yellow-400 font-bold py-2 px-4 rounded-full transition duration-200 text-sm">
            Sign Up
          </RouterLink>
        </div>
      </div>
    </nav>
</template>
  
<script>
  import { RouterLink } from 'vue-router';
  
  export default {
    name: "NavBar",
    components: {
      RouterLink
    }
  }
</script>
  
<style>
  /* Utilisez des styles CSS standards au lieu de @apply */
  .nav-link {
    color: #f8f9fa;
    font-weight: 500;
    transition: color 0.2s;
  }
  
  .nav-link:hover {
    color: #fcd34d;
  }
  
  .nav-active span {
    border-bottom: 2px solid #fcd34d;
  }
</style>