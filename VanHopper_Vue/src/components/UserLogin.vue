<template>
    <!--UserLogin.vue is used for users to login and also for admin to login. pushed to diffrent vue depending on user or admin-->
    <div className="Login">
        <h4>Login</h4>
        <form>
            <div>
                <label for="userId">User ID:</label>
                <input type="text" id="userId" v-model="userLoginRequest.userId"/>  
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" v-model="userLoginRequest.password"/>
            </div>
           
        </form>
        <div>
                <button type="submit" @click="login">Login</button> 
                <p>{{ message }}</p> 
                <br>
                <button class="smallbutton" type="submit" @click="register">Register</button> 
            </div>
       
    </div>
</template>

<script>
import UserService from '../services/UserService'; 
 
export default {
    name: "userLogin", 
    data(){
        return {
            userLoginRequest:{userId:"", password:""},  
            message:""
        }
    },
    methods:{
        //check uesrs credentials and renders admin or user homepage 
        login(){
            UserService.loginUser(this.userLoginRequest) 
            .then(response => { 
                let user= response.data;
                console.log(user);
                this.message=`Welcome ${user.name} !`;
                localStorage.setItem('uid', user.id);  
                localStorage.setItem('uName', user.name);
                if(user.name==='admin'){
                    this.$router.push({name:"adminPage"});
                }else{
                    this.$router.push({name:"userPage"});
                }
            })
            .catch(error =>{
                this.userLoginRequest.userId="";
                this.userLoginRequest.password="";
                this.message= error.response.data.message;
                console.log(error.response.data);
            })
        } ,
        //renders view to create new user 
        register(){
            this.$router.push({name:"createUser"});
        } 
        
    },
    mounted(){
        this.message="";  
    }
}
</script>

<style>
</style>