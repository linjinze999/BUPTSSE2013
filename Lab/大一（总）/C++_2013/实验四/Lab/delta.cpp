#include<iostream>
#include"delta.h"
#include <stdio.h>
#include <math.h>
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
bool Delta::operator < (Delta &a)
{
	int c1,c2;
	c1 = sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y))+sqrt((p1.x-p3.x)*(p1.x-p3.x)+(p1.y-p3.y)*(p1.y-p3.y))+sqrt((p3.x-p2.x)*(p3.x-p2.x)+(p3.y-p2.y)*(p3.y-p2.y));
	c2 = sqrt((a.p1.x-a.p2.x)*(a.p1.x-a.p2.x)+(a.p1.y-a.p2.y)*(a.p1.y-a.p2.y))+sqrt((a.p1.x-a.p3.x)*(a.p1.x-a.p3.x)+(a.p1.y-a.p3.y)*(a.p1.y-a.p3.y))+sqrt((a.p3.x-a.p2.x)*(a.p3.x-a.p2.x)+(a.p3.y-a.p2.y)*(a.p3.y-a.p2.y));
	if(c1 < c2) return 1;
	else return 0;
};
bool Delta::operator > (Delta &b)
{
	int c3,c4;
	c3 = sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y))+sqrt((p1.x-p3.x)*(p1.x-p3.x)+(p1.y-p3.y)*(p1.y-p3.y))+sqrt((p3.x-p2.x)*(p3.x-p2.x)+(p3.y-p2.y)*(p3.y-p2.y));
	c4 = sqrt((b.p1.x-b.p2.x)*(b.p1.x-b.p2.x)+(b.p1.y-b.p2.y)*(b.p1.y-b.p2.y))+sqrt((b.p1.x-b.p3.x)*(b.p1.x-b.p3.x)+(b.p1.y-b.p3.y)*(b.p1.y-b.p3.y))+sqrt((b.p3.x-b.p2.x)*(b.p3.x-b.p2.x)+(b.p3.y-b.p2.y)*(b.p3.y-b.p2.y));
	if(c3 > c4) return 1;
	else return 0;
};
bool Delta::operator <= (Delta &c)
{
	int c5,c6;
	c5 = sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y))+sqrt((p1.x-p3.x)*(p1.x-p3.x)+(p1.y-p3.y)*(p1.y-p3.y))+sqrt((p3.x-p2.x)*(p3.x-p2.x)+(p3.y-p2.y)*(p3.y-p2.y));
	c6 = sqrt((c.p1.x-c.p2.x)*(c.p1.x-c.p2.x)+(c.p1.y-c.p2.y)*(c.p1.y-c.p2.y))+sqrt((c.p1.x-c.p3.x)*(c.p1.x-c.p3.x)+(c.p1.y-c.p3.y)*(c.p1.y-c.p3.y))+sqrt((c.p3.x-c.p2.x)*(c.p3.x-c.p2.x)+(c.p3.y-c.p2.y)*(c.p3.y-c.p2.y));
	if(c5 <= c6) return 1;
	else return 0;
};
bool Delta::operator >= (Delta &d)
{
	int c7,c8;
	c7 = sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y))+sqrt((p1.x-p3.x)*(p1.x-p3.x)+(p1.y-p3.y)*(p1.y-p3.y))+sqrt((p3.x-p2.x)*(p3.x-p2.x)+(p3.y-p2.y)*(p3.y-p2.y));
	c8 = sqrt((d.p1.x-d.p2.x)*(d.p1.x-d.p2.x)+(d.p1.y-d.p2.y)*(d.p1.y-d.p2.y))+sqrt((d.p1.x-d.p3.x)*(d.p1.x-d.p3.x)+(d.p1.y-d.p3.y)*(d.p1.y-d.p3.y))+sqrt((d.p3.x-d.p2.x)*(d.p3.x-d.p2.x)+(d.p3.y-d.p2.y)*(d.p3.y-d.p2.y));
	if(c7 >= c8) return 1;
	else return 0;
};
bool Delta::operator == (Delta &e)
{
	int c9,c10;
	c9 = sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y))+sqrt((p1.x-p3.x)*(p1.x-p3.x)+(p1.y-p3.y)*(p1.y-p3.y))+sqrt((p3.x-p2.x)*(p3.x-p2.x)+(p3.y-p2.y)*(p3.y-p2.y));
	c10 = sqrt((e.p1.x-e.p2.x)*(e.p1.x-e.p2.x)+(e.p1.y-e.p2.y)*(e.p1.y-e.p2.y))+sqrt((e.p1.x-e.p3.x)*(e.p1.x-e.p3.x)+(e.p1.y-e.p3.y)*(e.p1.y-e.p3.y))+sqrt((e.p3.x-e.p2.x)*(e.p3.x-e.p2.x)+(e.p3.y-e.p2.y)*(e.p3.y-e.p2.y));
	if(c9 == c10) return 1;
	else return 0;
};
Delta Delta::operator = (Delta &f)
{
	return Delta(f.p1.x,f.p1.y,f.p2.x,f.p2.y,f.p3.x,f.p3.y,f.delta.fill,f.delta.color);
};
Point Delta::operator [] (int delta)
{
	if (delta == 0) {return Delta::p1;}
	else {
		if(delta == 1) {return Delta::p2;}
		else {
			if (delta == 2) return Delta::p3;
			else return Point(0,0);
		}
	}
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