/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var IDList=["Brand_All","Brand_QuWan","Brand_Hardway","Brand_ZhuMingYuan","Brand_LiYiJiuJiu","Brand_LiGuoLeXiang",
            "Object_All","Object_Friend","Object_Lover","Object_Children","Object_Elder","Object_Customer",
            "Ocation_All","Ocation_Birthday","Ocation_Living","Ocation_Commerce","Ocation_ValentineDay","Ocation_BabyMemory",
            "Sort_All","Sort_CreativeLiving","Sort_IntelligentElectronics","Sort_MusicBox","Sort_WoolDoll","Sort_CreativeToy",
            "Festival_All","Festival_ValentineDay","Festival_FatherDay","Festival_MotherDay","Festival_ChildrenDay","Festival_TeacherDay",
            "Price_All","Price_FirstPrice","Price_SecendPrice","Price_ThirdPrice","Price_FourthPrice","Price_FifPrice"];
var SortBG=[0,0,0,0,0,0];

var AllItems=document.getElementById("GetAllItems");
var SortItems=document.getElementById("SortAllItems");
var SearchItems=document.getElementById("SearchAllItems");

function setSortBG(brand,object,ocation,sort,festival,price){
    if(brand<6)
        SortBG[0]=brand;
    if(object<6)
        SortBG[1]=object;
    if(ocation<6)
        SortBG[2]=ocation;
    if(sort<6)
        SortBG[3]=sort;
    if(festival<6)
        SortBG[4]=festival;
    if(price<6)
        SortBG[5]=price;
}

function setSortSelectBG(){
        clearLineBG(0);
        setBG(IDList[SortBG[0]]);
        clearLineBG(1);
        setBG(IDList[SortBG[1]+6]);
        clearLineBG(2);
        setBG(IDList[SortBG[2]+12]);
        clearLineBG(3);
        setBG(IDList[SortBG[3]+18]);
        clearLineBG(4);
        setBG(IDList[SortBG[4]+24]);
        clearLineBG(5);
        setBG(IDList[SortBG[5]+30]);
}

function setSortSelect(brand,object,ocation,sort,festival,price){
    setSortBG(brand,object,ocation,sort,festival,price); 
    setSortSelectBG();
    //更改list
    
    setGoodsList();
    
}
function setSortSelect2(){
    setSortSelectBG();
    var numbers=window.location.search;
    setSortBG(parseInt(numbers[1]),parseInt(numbers[2]),parseInt(numbers[3]),parseInt(numbers[4]),parseInt(numbers[5]),parseInt(numbers[6])); 
    setSortSelectBG();
     //更改list
     setGoodsList();
}




function setBG(ID){
    var a=document.getElementById(ID);
    a.style.background="#ED145B";
    a.style.color="white";
    a.style.border="1px solid #C8C8C8";
}

function clearBG(ID){
    var a=document.getElementById(ID);
    a.style.background="";
    a.style.color="";
    a.style.border="";
}

function clearLineBG(line){
    for(var i=0;i<6;i++)
        clearBG(IDList[i+line*6]);
}

function clearAllBG(){
    for(var i=0;i<IDList.length;i++)
        clearBG(IDList[i]);
}

function getSortBG1(){
    return SortBG[0];
}
function getSortBG2(){
    return SortBG[1];
}
function getSortBG3(){
    return SortBG[2];
}
function getSortBG4(){
    return SortBG[3];
}
function getSortBG5(){
    return SortBG[4];
}
function getSortBG6(){
    return SortBG[5];
}