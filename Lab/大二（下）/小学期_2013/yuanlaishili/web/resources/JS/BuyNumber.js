/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function AddBuyNumber(max){
    var no=document.getElementById('BuyNumber');
    if(no.value<max)
        no.value++;
    else
        alert("Have no stock!");
}

function SubBuyNumber(){
    var no=document.getElementById('BuyNumber');
    if(no.value>1)
        no.value--;
}

function Buy(){
    alert("Buy succed!");
}

function AddToCart(){
    alert("Add succed!");
}

