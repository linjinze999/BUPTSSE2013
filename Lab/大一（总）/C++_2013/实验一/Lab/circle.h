#pragma once
#include<iostream>
#include <graphics.h>
#include <stdio.h>
#include"point.h"
#include"color.h"
using namespace std;

class Circle{
public:
	void setCentre(double cirx,double ciry)
	{
        centre.x=cirx; 
        centre.y=ciry;
	    }
	double getCentrex(){return centre.x;}
	double getCentrey(){return centre.y;}
	void setRadius(double radius)
	{
		this->radius=radius;
	    }
	double getRadius(){return radius;}
	void setCircle(color_t circolor,bool cirfill)
	{
        circle.color = circolor;
		circle.fill=cirfill;
	    }
	bool getCirfill(){return circle.fill;}
	int getCircolorR(){return EGEGET_R(circle.color);}
	int getCircolorG(){return EGEGET_G(circle.color);}
	int getCircolorB(){return EGEGET_B(circle.color);}
private:
	Point centre;double radius;Color circle;
    };