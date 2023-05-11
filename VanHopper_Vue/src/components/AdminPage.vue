<template>
<!--AdminPage.vue is only displayed if logged in with admin credentials -->
<!--renders child component UsersList or EventsList conditionally, depending on button label -->
<!--Admin has the ability to delte an event or user as required -->
    <div className="UserPage">
        <h4>Welcome  {{uName}}</h4>
        
        <div v-if="btnLabel==='Display Events'">
            <h3>All Users</h3>
            <users-list ></users-list>
            <p>{{ message }}</p> 
        </div> 
        <div v-else>
            <h3>All Events</h3>
            <events-list ></events-list>
            <p>{{ message }}</p> 
        </div>  
        
        <Button type="submit" @click="refreshList">{{ btnLabel }}</Button>
        
        <div>
        </div>
    
    </div> <br><br>
        <router-link to ="/" @click="logout">Logout</router-link>     

</template>

<script>
import EventsList from "../components/EventsList.vue";
import UsersList from "../components/UsersList.vue";

export default {
    name: "adminPage", 

    components:{
       EventsList,
        UsersList
    },
    data(){
        return {
            btnLabel:"Display Users",
            action:"Display_Events",
            uName:""
        }
    },
    methods:{ 
        //button label switches when button is clicked which changes which child component to be displayed 
        refreshList(){
            if(this.btnLabel==='Display Users'){
                this.btnLabel='Display Events';
                this.action="Display_Events"                    
            }else{
                this.btnLabel='Display Users';           
            }
        },
        //logs out user 
        logout(event){
            event.preventDefault();
            localStorage.removeItem('uid');
            localStorage.removeItem('uName');
            this.$router.push({name:"userLogin"});
        },        
    },
    mounted(){
        var temp=localStorage.getItem('uName');
        this.uName=temp.charAt(0).toUpperCase()+temp.slice(1);
    }
}
</script>

<style>

.table{
    border: 1px solid #ddd;
    border-radius: 15px;
    background-color: white;
    margin-left: auto;
    margin-right: auto;

}
#MyEventsTable td, #MyEventsTable th {
 border: black;
 border-color: black;
  padding: 8px;
 
}



#MyEventsTable tr:hover {background-color: #ddd;}

#MyEventsTable th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #eccfe3;
  color: rgb(5, 5, 5);
  
}
button{
    border-radius: 5px;
    border: 1px;
}
  
  .delete:hover {
  background-color: #f01414;

}
</style>