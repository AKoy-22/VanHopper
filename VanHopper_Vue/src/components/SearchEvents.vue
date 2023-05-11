<template>
<!--SearchEvents.vue is for users to search for events. Users can look for event by category or area and also register for evetns on this page -->
    <div  className="SearchEvents">
         <form Filter="">
            <h3>Filter by</h3>
            <label for="filtLocationlbl">&nbsp;&nbsp;&nbsp;Area &nbsp;</label>
            <select v-model="areaFilter" class="appDropDownsHalf" name="filterDropDown" id="filterArea"  ><br>
                <option value="All">All</option>
                <option value="East">East Vancouver</option>
                <option value="North">North Vancouver</option>
                <option value="New">New Westminster</option>
                <option value="Brentwood">Brentwood</option>
                <option value="Downtown">Downtown</option>
                <option value="Surrey">Surrey</option>
            </select> <br>
            <label for="filtCatlbl">Category </label>
            <select v-model="categoryFilter" class="appDropDownsHalf" name="filterDropDown" id="filterCategory"><br>
                <option value="All">All</option> 
                <option value="Comedy">Comedy</option>
                <option value="Health & Lifestyle">Health & Lifestyle</option>
                <option value="Science and Technology">Science and Technology</option>
                <option value="Family">Family</option>  
                <option value="Performing and Visual arts">Performing and Visual arts</option>   
                <option value="Music">Music</option>
            </select> <br><br>
             <button class="smallbutton" type="submit" value="Search" @click="handleFilter">Search</button> 
        </form>

        <table class="table" id="MyEventsTable">
            <thead>
                <tr>
                <th scope="col">Event Name</th>
                <th scope="col">Location</th>
                <th scope="col">Date</th>
                <th scope="col">Category</th>
                <th scope="col">Link</th>
                <th scope="col">Area</th>
                <th scope="col">Register</th>
                </tr>
            </thead>

            <tbody v-if="events.length">
                <tr v-for="(e, index) in events" :key="index" >
                <td  >{{ e.name }} </td>
                <td>{{ e.location }}</td>
                <td>{{ e.date }}</td>
                        <td>{{ e.category }}</td>
                        <td>{{ e.link }}</td>
                        <td>{{ e.area }}</td>
                    
                       <td><Button class="delete" @click="register" :id="e.id">Register</Button></td>
                        </tr>
            </tbody> 
        </table> <br>
        <label>{{ noRecord }}</label>  
        <br>
        <div>
            <Button type="submit" className="smallbutton" @click="goMyEvents">Check My Events</Button>
        </div>
    </div>
    <div>
       <router-link to ="/" @click="logout">Logout</router-link>
    </div>
</template>



<script>
import UserService from '@/services/UserService';
import '../App.css';

export default{

    name:"searchEvents",
    data(){
        return{
            userId:'',
            uName:"",
            events:[],
            unRegisteredEvents:[],
            noRecord:"",
            areaFilter:"All",
            categoryFilter:"All",
            filteredEvents:[]
        }
    },
    methods:{
       //register use for an event
        register(event){
            event.preventDefault();
            console.log(event.target.id);
            UserService.register(this.userId, parseInt(event.target.id))

                .then(response=>{
                    console.log(response);
                    this.getEvents();
                })
                .catch(error=>{
                    console.log(error);
                })
            this.getEvents();
        },
        //displays user or admin home page
        goMyEvents(){
            UserService.loginUser(this.uName)
                if(this.uName==='admin'){
                    this.$router.push({name:"adminPage"});
                }else{
                    this.$router.push({name:"userPage"});
                }
        } ,
       //retrieves unregistered events 
        getEvents(){
            UserService.getUnregisteredEvents(this.userId)
                .then(response=>{
                    this.events=response.data;
                    this.unRegisteredEvents=this.events;
                    console.log("list of uregistered events getting"+this.events);
                })
                .catch(error=>{
                    console.log(error);
                })
        },
        //filters by category and area
        handleFilter(event)
       {
            event.preventDefault();
            this.afterSearchMsg="you are searching events in method";
        
            this.events=this.unRegisteredEvents; 
            // filtering the events based on area and category
            this.filteredEvents = this.events.filter((event) => {
            // case1: filter if both area and category all   
            if(this.areaFilter==="All"&& this.categoryFilter==="All")
            {
               // console.log("print all "+this.events);
                return this.events;
           
            }
            // case2: areaFilter is all but categoryFilter is required value  
            else if(this.areaFilter==="All")
            {
            return event.category.toLowerCase().includes(this.categoryFilter.toLowerCase())
            }
            // case3: areaFilter is specific but categoryFilter is all  
            else if(this.categoryFilter==="All")
            {
                //console.log("when category all"+event.area.toLowerCase().includes(this.areaFilter.toLowerCase()))
               return event.area.toLowerCase().includes(this.areaFilter.toLowerCase())
            }
            // case4: when area and category set to desired value    
            else
            {
            return event.area.toLowerCase().includes(this.areaFilter.toLowerCase()) && event.category.toLowerCase().includes(this.categoryFilter.toLowerCase());
            }
            //look for possible cases 
     });

     // Checks if filteredEvents are there or not if not sets the events to null and display msg no events
     if(this.filteredEvents.length===0)
     {
     this.events=[];
     this.noRecord="There are no Events of your search"
     } 
     else{
          this.noRecord="";
          this.events=this.filteredEvents;
     }
    },
    //logs out a user and local storage is deleted
    logout(event){
            event.preventDefault();
            localStorage.removeItem('uid');
            localStorage.removeItem('uName');
            this.$router.push({name:"userLogin"});
        },
    },
    
    mounted(){
        this.userId=localStorage.getItem('uid');
        this.userName=localStorage.getItem('uName');
        this.getEvents();
    }
}

</script>


<style>
</style>