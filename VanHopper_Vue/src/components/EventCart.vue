<template>
    <h1> YOUR CART </h1>
    <ul v-for="(i, index) in items" :key="i.id">
        <li>{{ index +1}} </li>
        <li>{{ i['name'] }}</li>
        <li> {{ i['hosts'].toString() }}</li><br>
    </ul>
    <div>
        <button @click="closeCart"> CLOSE </button>
    </div>

</template>
    
    <script>

    export default{
        name:"eventCart",
        props:["items","changeClickStatus"],
        data(){
            return{
                
            }
        },
        methods:{
            closeCart(event){
                event.preventDefault();
                this.changeClickStatus(false);
            }
        },
        mounted(){
  
        }
    }
    </script>