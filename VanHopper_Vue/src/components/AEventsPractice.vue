<template>
<ul v-if="events.length">
    <li style="text-decoration: underline" v-for="ev in events" :key="ev.id" @click="selected" :id="ev.id">
        {{ ev.name }}
    </li>
</ul>

<div>
    <aevents-detail :info="selectedEvent" ></aevents-detail>
</div>
</template>

<script>
import EventService from '@/services/EventService';
import AeventsDetail from './AeventsDetail.vue';
export default{
    name:"aeventsPractice",
    components:{
        AeventsDetail
    },
    data(){
        return{
            events:[],
            selectedEvent:[]
        }
    },
    methods:{
        getEvents(){
            EventService.getAllEvents()
                .then(response=>{
                    this.events=response.data;
                })
        },
        selected(event){
            event.preventDefault();
            var selId=event.target.id;
            this.selectedEvent=this.events.filter(ev=>ev.id==selId)[0];
            console.log(this.selectedEvent.name);
        }
    },
    mounted(){
        this.getEvents();
    }
}
</script>