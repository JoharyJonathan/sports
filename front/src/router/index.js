import { createRouter, createWebHistory } from "vue-router";
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Products from '../views/Products.vue'
import Login from '../views/Login.vue'
import Signup from '../views/Signup.vue'
import Admin from '../views/Admin.vue'
import Profile from '../views/Profile.vue'
import Cart from '../views/Cart.vue'
import ProductDetail from '../views/ProductDetail.vue'
import Success from '../views/Success.vue'
import Cancel from '../views/Cancel.vue'
import Favoris from '../views/Favoris.vue'
import EditProfile from '../views/EditProfile.vue'
import ProductCategory from '../views/ProductCategory.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/about',
            name: 'about',
            component: About
        },
        {
            path: '/products',
            name: 'products',
            component: Products
        },
        {
            path: '/login',
            name: 'login',
            component: Login
        },
        {
            path: '/signup',
            name: 'signup',
            component: Signup
        },
        {
            path: '/admin',
            name: 'admin',
            component: Admin
        },
        {
            path: '/profile/:id',
            name: 'profile',
            component: Profile
        },
        {
            path: '/cart/:id',
            name: 'cart',
            component: Cart
        },
        {
            path: '/product/:id',
            name: 'product-detail',
            component: ProductDetail
        },
        {
            path: '/success/:id',
            name: 'success',
            component: Success
        },
        {
            path: '/cancel',
            name: 'cancel',
            component: Cancel
        },
        {
            path: '/favoris/:id',
            name: 'favoris',
            component: Favoris
        },
        {
            path: '/edit-profile/:id',
            name: 'edit-profile',
            component: EditProfile
        },
        {
            path: '/products/category/:category',
            name: 'category-view',
            component: ProductCategory
        }
    ]
})

export default router