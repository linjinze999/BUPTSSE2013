#include<iostream>
#include"circle.h"
#include <stdio.h>
#include"point.h"
using namespace std;

int Circle::number=0;
Circle::Circle(){
	Circle::setCentre(100,200);	
	Circle::setRadius(50);
	Circle::setCircle(GREEN,1);
	number++;
};
Circle::~Circle(){number--;};
Circle::Circle(double x,double y,double r,bool fill,color_t c){
	Circle::setCentre(x,y);
	Circle::setRadius(r);
	Circle::setCircle(c,fill);
};
bool Circle::operator < (Circle &a)
{
	if (radius < a.radius) return 1;
	else return 0;
};
bool Circle::operator > (Circle &b)
{
	if (radius > b.radius) return 1;
	else return 0;
};
bool Circle::operator <= (Circle &c)
{
	if (radius <= c.radius) return 1;
	else return 0;
};
bool Circle::operator >= (Circle &d)
{
	if (radius >= d.radius) return 1;
	else return 0;
};
bool Circle::operator == (Circle &e)
{
	if (radius == e.radius) return 1;
	else return 0;
};
Circle Circle::operator = (Circle &f)
{
	return Circle(f.centre.x,f.centre.y,f.radius,f.circlee.fill,f.circlee.color);
};
Point Circle::operator [] (int circle)
{
	Point a;
	a = Point(Circle::centre.x,Circle::centre.y+Circle::radius);
	if (circle == 0) {
		return Point(centre.x,centre.y);
	}
	else {
		if(circle == 1)return a;
		else return Point(0,0);
	}
};

void Circle::draw(){
	if(Circle::getCirfill())
	    {
	        setfillcolor(EGERGB(Circle::getCircolorR(),Circle::getCircolorG(),Circle::getCircolorB()));
		    fillellipse(Circle::getCentrex(),Circle::getCentrey(),Circle::getRadius(),Circle::getRadius());
	    }
	    else 
	    {
	        circle(Circle::getCentrex(),Circle::getCentrey(),Circle::getRadius());
	    }
}

