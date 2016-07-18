#include<iostream>
#include"circle.h"
#include <stdio.h>
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

