import http from '../http-common';

class EventService{
   getAllEvents(){
        return http.get('/events')
    }

    
    createHopperEvent(data){//id, email, phone
      console.log("Event Create:");
      console.log(data);
      return http.post("/events", data)
  }
    deleteEvent(eventId){
       return http.delete(`/events/${eventId}`)
    }
   
}

export default new EventService();