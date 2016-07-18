/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var lists=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38];

var Page = document.getElementById("Page");

var value = Page.options[Page.selectedIndex].value;
var text = Page.options[Page.selectedIndex].text;
var Max_Goods=16;
var LastPage_GoodsNumber=lists.length%Max_Goods;
var Max_Page=parseInt((LastPage_GoodsNumber>0)?lists.length/Max_Goods+1:lists.length/Max_Goods);

function CreateSelect(){
    Max_Page=parseInt((LastPage_GoodsNumber>0)?lists.length/Max_Goods+1:lists.length/Max_Goods);
    Page.options.length=0;
    for(var n=1;n<=Max_Page;n++)
        Page.options.add(new Option(n,n));
}

function NextPage(){
    var NowValue = Page.options[Page.selectedIndex].value;
    if(parseInt(NowValue)<parseInt(Max_Page)){
        Page.value=parseInt(NowValue)+1;
        setGoodsList();
    }
}

function LastPage(){
    var NowValue = Page.options[Page.selectedIndex].value;
    if(parseInt(NowValue)>1){
        Page.value=parseInt(NowValue)-1;
        setGoodsList();
    }
}

function setGoodsList(){
    var goods = document.getElementsByClassName("GoodsList_Item");
    for(var i=0;i<Max_Goods;i++){
        goods[i].style.display='none';
    }
    value =parseInt(Page.options[Page.selectedIndex].value);
    var ThisPageItemsNumber=(value<Max_Page)?Max_Goods:LastPage_GoodsNumber; 
    for(var i=0;i<ThisPageItemsNumber;i++){
        goods[i].style.display='inline-block';
        goods[i].style.background="url('resources/image/webpage/1.jpg')";
        goods[i].style.backgroundRepeat="no-repeat";
        goods[i].style.backgroundSize="100% 70%";
        goods[i].style.backgroundPosition="50% 0%";
        goods[i].innerText="\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+
                "\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+lists[i+(value-1)*16];
    }
}

