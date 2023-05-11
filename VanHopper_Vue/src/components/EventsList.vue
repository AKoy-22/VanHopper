<template>
    <!--EventList.vue is used in User page and Admin page.-->
    <!--If it is a user, only events User has registered for is displayed and user can de-register from any event -->
    <!--If it is admin, all events are displayed and admin can delete events.-->
    <!--this component is a child component of UserPage.vue and AdminPage.vue-->
    <div style="overflow-x:auto;">
            <table class="table" id="MyEventsTable" >
                <thead>
                    <tr>
                    <th scope="col">Event Name</th>
                    <th scope="col">Location</th>
                    <th scope="col">Area</th>
                    <th scope="col">Link</th>
                    <th scope="col" v-if="userName==='admin'">Delete </th>
                    <th scope="col" v-else>De-Register</th>
                    </tr>
                </thead>
                <tbody v-if="events.length">
                    <tr v-for="(e, index) in events" :key="index">
                    <td>{{ e.name }} </td>
                    <td>{{ e.location }}</td>
                    <td>{{ e.area }}</td>
                    <td>{{ e.link }}</td>
                    <td v-if="btnLabel==='De-Register'"><Button class="delete" :id="e.id" @click="deRegister" >{{ btnLabel }}</Button></td>
                    
                    <td v-if="userName==='admin'" ><Button class="delete" @click="deleteEvent" :id="e.id">Delete</Button></td> 
                    </tr>
                </tbody> 
            </table>
        </div>
</template>


<script>
import UserService from '../services/UserService';
import EventService from '../services/EventService';

export default{
    name: "eventList", 
    data(){
        return {
            userId:"",
            userName:"",
            events:[],
            btnLabel:"De-Register",
        }
    },
    methods:{
        //deregister a user from an event - for users
        deRegister(event){
            event.preventDefault();
            UserService.deregister(event.target.id, this.userId)
                .then(response=>{
                    console.log(response)
                    this.getEvents(this.userId);
                })
                .catch(error=>{
                    console.log(error);
                })
        },
        //delete event - for admin
        deleteEvent(event){
            event.preventDefault();
            EventService.deleteEvent(event.target.id)
                .then(response=>{
                    console.log(response);
                    this.getAll();
                })
                .catch(error=>{
                    console.log(error);
                })
        },
        //get event user has registered for - for user
        getEvents(uid){
            UserService.getRegisteredEvents(uid)
                .then(response=>{
                    this.events=response.data;
                    console.log(this.events);
                })
                .catch(error=>{
                    console.log(error);
                })
        },
        //get all events - admin
        getAll(){
            EventService.getAllEvents()
                .then(response=>{
                    this.events=response.data;
                })
                .catch(error=>{
                    console.log(error);
                })
        }
    },
    mounted(){
        //manipulates button labels and initial display depending on whether it is an admin or a user
       this.userName=localStorage.getItem('uName');
       if(this.userName==='admin'){
        this.btnLabel="Edit";
        this.getAll();
       }else{
        this.userId=localStorage.getItem('uid');
        console.log(this.userId);
        this.getEvents(this.userId);  
       }
    }
}
</script>


<style>
</style>