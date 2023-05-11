<template>
    <div v-if="info.length!=0">  
    <h1 > Event Detail </h1>
    <ul >
        <li>  {{ info.name }}  </li><br/>
        <li>  {{ info.id }}    </li> <br/>
         <li> {{ info.hosts.toString() }}    </li>  
    </ul>
    <div>
        <button @click="inCart"> Put in Cart </button>
    </div>
    <div v-if="clicked">
        <event-cart :items="cartItems" :changeClickStatus="changeClickStatus"> </event-cart>
    </div>

</div>
  </template>
    
    <script>
    import EventCart from '../components/EventCart.vue';
    
    export default{
        name:"aeventsDetail",
        components:{
            EventCart,
        },
        props:["info"],
        data(){
            return{
                cartItems:[],
                clicked:false,
            }
        },
        methods:{
            inCart(event){
                event.preventDefault();
                this.cartItems.push(this.info);
                this.changeClickStatus(true);
            },
            changeClickStatus(boolVal){
                this.clicked=boolVal;
            }
        },
        mounted(){
            
        },
        watch(){
        }
    }
    </script>