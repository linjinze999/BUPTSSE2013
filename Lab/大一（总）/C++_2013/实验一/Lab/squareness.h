#pragma once
#include<iostream>
#include <graphics.h>
#include <stdio.h>
#include"point.h"
#include"color.h"
using namespace std;

class Squareness{
public:
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
private:
	Point topleft;Point rightdown;Color squareness;
    };