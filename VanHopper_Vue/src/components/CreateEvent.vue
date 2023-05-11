<template>
    <!--Create event page. Users can create an event-->
    <div className="CreateEvent">
        <h4>Create Event </h4>
        <form>
            <div>
                
                <label for="eventLabel">Event's name: </label>
                <input type="text" id="eventName" v-model="createEventRequest.name" /><br>
                <label for="locationLabel">Location: </label>
                <input type="text" id="location" v-model="createEventRequest.location" /><br>
                <label for="areaLabel">Area: </label>
                <select class="appDropDowns" name="areasDropDown" id="area"  v-model="createEventRequest.area" ><br>
                    <option value="East Vancouver">East Vancouver</option>
                    <option value="North Vancouver">North Vancouver</option>
                    <option value="Surrey">Surrey</option>
                    <option value="Downtown">Downtown</option>
                    <option value="Brentwood">Brentwood</option>
                    <option value="New Westminster">New Westminster</option>
                </select>
                
                <label for="date">Date: </label>
                <input type="date" id="date" v-model="createEventRequest.date" /><br>
                <label for="category">Category: </label>
                <select class="appDropDowns" name="categoryDropDown" id="category" v-model="createEventRequest.category"><br>
                        <option value="Comedy">Comedy</option>
                        <option value="Health">Health & Lifestyle</option>
                        <option value="SciTech">Science and Technology</option>
                        <option value="Fam">Family</option> 
                        <option value="Fam">Performing and Visual arts</option>
                        <option value="Fam">Music</option>
                </select>
                <label for="hostsLabel">Event's hosts: </label>
                <input type="text" id="eventHosts"  @change="onChangeHost"/><br>


                <label for="linkLabel">Link to the official site: </label>
                <input type="text" id="eventLink" v-model="createEventRequest.link" /><br>
               
            </div>
        </form> 
        <button type="submit" @click="createHopperEvent" > Create event </button> 
        <p>{{ message }}</p>
        <br><br>
        <button class="smallbutton" type="submit" @click="goToEvents" > Show events </button> 
    </div>
    
    <div>
        <br><br>

        <router-link to ="/" @click="logout">Logout</router-link>
    </div>
</template>

<script>
import '../App.css';
import EventService from '../services/EventService';

export default {
    name:"createEvent",
    data(){
        return{
            createEventRequest:{name:"", location:"", area:"", date:"", category:"", hosts:[], link:""},
            message: "",
        }
    },
    methods:{
        //host is an array in springboot 
        onChangeHost(event){
            this.createEventRequest.hosts = event.target.value.split(",");
        },
        //creates a new event
        createHopperEvent(event){
            event.preventDefault();
            EventService.createHopperEvent(this.createEventRequest)
            .then(response => { 
                var hopperEvent=response.data;
                console.log(hopperEvent.name);
                this.message =`Event ${hopperEvent.name} was successfully created!`;               
               
            })
            .catch(error =>{
                this.createEventRequest.name="";
                this.createEventRequest.location="";
                this.createEventRequest.area="";
                this.createEventRequest.date="";
                this.createEventRequest.category="";
                this.createEventRequest.hosts="";
                this.createEventRequest.link="";
                console.log(error);
            })
        },
        //logs out teh user
        logout(event){
            event.preventDefault();
            localStorage.removeItem('uid');
            localStorage.removeItem('uName');
            this.$router.push({name:"userLogin"});
        }, 
        //navigates to teh search events page   
        goToEvents(event){
             event.preventDefault();
            this.$router.push({name:"searchEvents"}); 
        } 

    },
    mounted(){
        
    }
}

</script>



<style>

</style>