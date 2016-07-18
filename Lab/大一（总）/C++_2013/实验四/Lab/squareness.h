#pragma once
#include<iostream>
#include <graphics.h>
#include <stdio.h>
#include"point.h"
#include"draw.h"
#include"color.h"
using namespace std;

class Squareness:public Draw{
public:
	Squareness();
	Squareness(Squareness &);
	~Squareness();
	Squareness(double x1,double y1,double x2,double y2,bool f,color_t c);
	bool operator < (Squareness &a);
	bool operator > (Squareness &b);
	bool operator <= (Squareness &c);
	bool operator >= (Squareness &d);
	bool operator == (Squareness &e);
	Squareness operator = (Squareness &f);
	Point operator [] (int g);
	void draw();
	void setTopleft(double stlx,double stly)
	{
        topleft.x=stlx;
		topleft.y=stly;
	}
	double getTopleftx(){return topleft.x;}
	double getToplefty(){return topleft.y;}
	void setRightdown(double srdx,double srdy)
	{
        rightdown.x=srdx;
		rightdown.y=srdy;
	}
	double getRightdownx(){return rightdown.x;}
	double getRightdowny(){return rightdown.y;}
	void setSquareness(color_t squcolor,bool squfill)
	{
        squareness.color = squcolor;
		squareness.fill=squfill;
	}
	bool getSqufill(){return squareness.fill;}
	int getSqucolorR(){return EGEGET_R(squareness.color);}
	int getSqucolorG(){return EGEGET_G(squareness.color);}
	int getSqucolorB(){return EGEGET_B(squareness.color);}
	int colornum;
	static int getNumber(){return number;}
private:
	Point topleft;Point rightdown;Color squareness;
	static int number;
    };