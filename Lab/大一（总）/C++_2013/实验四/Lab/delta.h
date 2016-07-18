#pragma once
#include<iostream>
#include"point.h"
#include"color.h"
#include"draw.h"
using namespace std;

class Delta:public Draw{
public:
	Delta();
	Delta(Delta&);
	~Delta();
	bool operator < (Delta &a);
	bool operator > (Delta &b);
	bool operator <= (Delta &c);
	bool operator >= (Delta &d);
	bool operator == (Delta &e);
	Delta operator = (Delta &f);
	Point operator [] (int delta);
	void draw();
	Delta(int x1,int y1,int x2,int y2,int x3,int y3,bool fill,color_t d);
	static int getNumber(){return number;}
	bool getFill(){return delta.fill;}
	int getP1x(){return p1.x;}
	int getP1y(){return p1.y;}
	int getP2x(){return p2.x;}
	int getP2y(){return p2.y;}
	int getP3x(){return p3.x;}
	int getP3y(){return p3.y;}
	void setP1(int x1,int y1){p1.x = x1;p1.y = y1;}
	void setP2(int x2,int y2){p2.x = x2;p2.y = y2;}
	void setP3(int x3,int y3){p3.x = x3;p3.y = y3;}
	void setDelta(color_t delcol,bool delfill){
		delta.color = delcol;
		delta.fill = delfill;
	};
	int colornum;
private:
	Color delta;Point p1;Point p2;Point p3;static int number;
};