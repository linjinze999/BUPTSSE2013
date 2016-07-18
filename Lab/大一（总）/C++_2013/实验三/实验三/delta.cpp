#include<iostream>
#include"delta.h"
#include <stdio.h>
using namespace std;

int Delta::number = 0;
Delta::Delta(){
	Delta::setP1(500,100);
	Delta::setP2(600,200);
	Delta::setP3(400,200);
	Delta::delta.fill=0;
	Delta::number++;
};
Delta::~Delta(){number--;};
Delta::Delta(int x1,int y1,int x2,int y2,int x3,int y3,bool fill,color_t d){
	Delta::setP1(x1,y1);
	Delta::setP2(x2,y2);
	Delta::setP3(x3,y3);
	Delta::delta.fill=fill;
	Delta::delta.color=d;
	Delta::number++;
};
void Delta::draw(){
	int del[8];
	del[0]=Delta::p1.x;
	del[1]=Delta::p1.y;
	del[2]=Delta::p2.x;
	del[3]=Delta::p2.y;
	del[4]=Delta::p3.x;
	del[5]=Delta::p3.y;
	del[6]=Delta::p1.x;
	del[7]=Delta::p1.y;
	if(Delta::getFill()){
		setfillcolor(Delta::delta.color);
	    fillpoly(3,del);
	}
	else{
		drawpoly(4,del);
	}
}