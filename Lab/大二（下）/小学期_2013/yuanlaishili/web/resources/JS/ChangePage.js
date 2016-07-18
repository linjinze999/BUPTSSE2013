/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function ChangePageToITByPush(id){
    window.location.href='faces/ItemIntroduction.xhtml?'+id;
}

function ChangePageToIT(Item){
    window.location.href='faces/ItemIntroduction.xhtml';
}

function ChangePageToCP(brand,object,ocation,sort,festival,price){
    window.location.href='faces/ClientPage.xhtml?'+brand+object+ocation+sort+festival+price; 
}

function getGoodsId(){
    var numbers=window.location.search;
    return numbers[1];
}