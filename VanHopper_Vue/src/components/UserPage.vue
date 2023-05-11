<template><!--layout -->
    <div className="UserPage">
        <h4>Welcome {{uName}}</h4>
        <div>
            <h3>My Events</h3>
                <events-list></events-list>
            <button type="submit" @click="lookForEvent">Look for an event</button>
            <button type="submit" @click="addEvent">Host an event</button>
        </div>
        <div>
       
    </div>
    
    </div>

    <br><br>
    <router-link to ="/" @click="logout">Logout</router-link>

</template>

<script>
import EventsList from '../components/EventsList.vue';

export default {
    name: "userPage", 
    components:{
        EventsList
    },
    data(){
        return {
            uName:"",
            userId:""
        }
    },
    methods:{
        lookForEvent(){
            this.$router.push({name:"searchEvents"});
        },
        addEvent(){
            this.$router.push({name:"createEvent"}); //jumps to CreateEvent page
        },
        logout(event){
            event.preventDefault();
            localStorage.removeItem('uid');
            localStorage.removeItem('uName');
            this.$router.push({name:"userLogin"});
        },        
    },
    mounted(){
        this.uName=localStorage.getItem('uName');
        this.userId=localStorage.getItem('uid');
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