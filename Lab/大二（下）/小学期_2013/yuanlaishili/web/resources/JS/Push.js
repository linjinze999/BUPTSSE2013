/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var stor = -1;
var maxstor = 5;
var timeout = 3500;
var ItemID=[];

function setStor(new_stor){
    stor=new_stor;
    rotateDiv(stor);
}

function getStor(){
    return stor;
}

function autorot() {
    showNext();
    setTimeout('autorot();', timeout);
}
function showNext(){
    if(stor < maxstor)
        stor++;
    else
        stor=0;
    rotateDiv(stor);
}
function rotateDiv(stor){
    switch(stor){
        case 0:
            showPush1();
            break;
        case 1:
            showPush2();
            break;
        case 2:
            showPush3();
            break;
        case 3:
            showPush4();
            break;
        case 4:
            showPush5();
            break;
    }
}

function hidPush(){
    var push1=document.getElementById('push1');
    push1.style.display='none';
    var pushas11=document.getElementById('pushas11');
    pushas11.style.background='#666';
    var pushas21=document.getElementById('pushas21');
    pushas21.style.color='#fff';
    var push2=document.getElementById('push2');
    push2.style.display='none';
    var pushas12=document.getElementById('pushas12');
    pushas12.style.background='#666';
    var pushas22=document.getElementById('pushas22');
    pushas22.style.color='#fff';
    var push3=document.getElementById('push3');
    push3.style.display='none';
    var pushas13=document.getElementById('pushas13');
    pushas13.style.background='#666';
    var pushas23=document.getElementById('pushas23');
    pushas23.style.color='#fff';
    var push4=document.getElementById('push4');
    push4.style.display='none';
    var pushas14=document.getElementById('pushas14');
    pushas14.style.background='#666';
    var pushas24=document.getElementById('pushas24');
    pushas24.style.color='#fff';
    var push5=document.getElementById('push5');
    push5.style.display='none';
    var pushas15=document.getElementById('pushas15');
    pushas15.style.background='#666';
    var pushas25=document.getElementById('pushas25');
    pushas25.style.color='#fff';
}

function showPush1(){
    hidPush();
    var push1=document.getElementById('push1');
    push1.style.display='block';
    var pushas11=document.getElementById('pushas11');
    pushas11.style.background='#fff';
    var pushas21=document.getElementById('pushas21');
    pushas21.style.color='#000';
}
function showPush2(){
    hidPush();
    var push2=document.getElementById('push2');
    push2.style.display='block';
    var pushas12=document.getElementById('pushas12');
    pushas12.style.background='#fff';
    var pushas22=document.getElementById('pushas22');
    pushas22.style.color='#000';
}
function showPush3(){
    hidPush();
    var push3=document.getElementById('push3');
    push3.style.display='block';
    var pushas13=document.getElementById('pushas13');
    pushas13.style.background='#fff';
    var pushas23=document.getElementById('pushas23');
    pushas23.style.color='#000';
}
function showPush4(){
    hidPush();
    var push4=document.getElementById('push4');
    push4.style.display='block';
    var pushas14=document.getElementById('pushas14');
    pushas14.style.background='#fff';
    var pushas24=document.getElementById('pushas24');
    pushas24.style.color='#000';
}
function showPush5(){
    hidPush();
    var push5=document.getElementById('push5');
    push5.style.display='block';
    var pushas15=document.getElementById('pushas15');
    pushas15.style.background='#fff';
    var pushas25=document.getElementById('pushas25');
    pushas25.style.color='#000';
}




