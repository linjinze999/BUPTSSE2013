#pragma once
#include<iostream>
#include <graphics.h>
#include <stdio.h>
#include"point.h"
#include"color.h"
#include"draw.h"
using namespace std;

class Circle:public Draw{
public:
	Circle();
	Circle(Circle&);
	~Circle();
	void draw();
	bool operator < (Circle &a);
	bool operator > (Circle &b);
	bool operator <= (Circle &c);
	bool operator >= (Circle &d);
	bool operator == (Circle &e);
	Circle operator = (Circle &f);
	Point operator [] (int circle);
	Circle(double x,double y,double r,bool fill,color_t c);
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
        circlee.color = circolor;
		circlee.fill=cirfill;
	    }
	bool getCirfill(){return circlee.fill;}
	int getCircolorR(){return EGEGET_R(circlee.color);}
	int getCircolorG(){return EGEGET_G(circlee.color);}
	int getCircolorB(){return EGEGET_B(circlee.color);}
	static int getNumber(){return number;}
	Point centre;
	int colornum;
private:
	double radius;Color circlee;
	static int number;
    };