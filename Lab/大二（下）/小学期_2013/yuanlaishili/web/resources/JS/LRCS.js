/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var carts=["cart1","cart2","cart3","cart4","cart5","cart6"];
var Max_cart=6;
var cart_num=6;
function checkLogin(){
    if(1>0){
        var l=document.getElementsByClassName("Top_Left");
        for(var i=0;i<l.length;i++)
            l[i].style.display="block";
    }
}

function cart(){
    hidCarts();
    var cartss=document.getElementById("carts");
    cartss.style.display="block";
    for(var i=0;i<cart_num;i++){
        var cart=document.getElementById(carts[i]);
        cart.style.display="block";
        cart.innerText=""+carts[i];
        cart.style.background="url('resources/image/webpage/Avatar.jpg')";
        cart.style.backgroundSize="20% 100%";
        cart.style.backgroundRepeat="no-repeat";
    }
}

function search(){
    var wh=document.getElementById('WhatSearch');
    setGoodsList();
}

function hidCarts(){
    var cartss=document.getElementById("carts");
    cartss.style.display="none";
    for(var i=0;i<Max_cart;i++){
        var cart=document.getElementById(carts[i]);
        cart.style.display="none";
    }
}

function setOnCar(no){
    var cart=document.getElementById(carts[no]);
    cart.style.background="white";
    cart.style.color="#ED145B";
    cart.style.borderTop="1px solid #ED145B";
    cart.style.borderBottom="1px solid #ED145B";
    
}

function setOutCar(no){
    var cart=document.getElementById(carts[no]);
    cart.style.background="";
    cart.style.color="";
    cart.style.border="";
}




