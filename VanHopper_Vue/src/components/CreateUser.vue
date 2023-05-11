<template>
    <!--Create user page -->
    <div className="CreateUser">
        <h4>Sign Up </h4>
        <form>
            <div>
                <label for="userId">Your account ID: </label>
                <input type="text" id="userId"  v-model="createUserRequest.userId" /><br>
                <label for="userName">Your name: </label>
                <input type="text" id="userName" v-model="createUserRequest.name" /><br>
                <label for="userEmail">Your email: </label>
                <input type="email" id="userEmail"  v-model="createUserRequest.email" /><br>
                <label for="password">Your password: </label>
                <input type="password" id="userPassword" v-model="createUserRequest.password" /><br>
                <label for="phone">Your phone number: </label>
                <input type="tel" id="userPhone"   v-model="createUserRequest.phone" /><br>
            </div>
        </form> 
        <button type="submit" @click="createUser"> Create Account </button> 
        <p>{{ message }} </p>
        <button class="smallbutton" type="submit" @click="login">Log in</button> 
    </div>
</template>

<script>
import '../App.css';
import UserService from '../services/UserService';

export default {
    name:"createUser",
    data(){
        return{
            createUserRequest:{userId:"", name:"", email:"", phone:"", password:""},
            action:"showLoginBtn"
        } 
    },
    methods:{
        //creates a new user and registered in database
        createUser(event){
            event.preventDefault();
            UserService.createUser(this.createUserRequest)
            .then(response => { 
                var user=response.data;
                console.log(user.userEmail);
                this.message=`Welcome ${user.name}! Your account has been successfully created. Please Log in `;   
                this.$router.push({name:"login"}); 
            })
            .catch(error =>{
                this.createUserRequest.userId="";
                this.createUserRequest.name="";
                this.createUserRequest.email="";
                this.createUserRequest.phone="";
                this.createUserRequest.password="";
                console.log(error.response.data);
            })
        },
        //navigates back to the inital login page
        login(){
            this.$router.push({name:"userLogin"});
        }    
    },
    mounted(){

    }
}

</script>



<style>

</style>