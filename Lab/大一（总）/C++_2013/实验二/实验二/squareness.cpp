#include<iostream>
#include"squareness.h"
#include <stdio.h>
using namespace std;

int Squareness::number = 0;
Squareness::Squareness(){
	Squareness::setRightdown(300,300);
	Squareness::setTopleft(500,700);
	Squareness::setSquareness(RED,1);
	Squareness::number++;
};
Squareness::~Squareness(){
    Squareness::number--;
};
Squareness::Squareness(double x1,double y1,double x2,double y2,bool f,color_t c){
	Squareness::setRightdown(x1,y1);
	Squareness::setTopleft(x2,y2);
	Squareness::setSquareness(c,f);
	Squareness::number++;
};
void Squareness::draw(){
	if(Squareness::getSqufill())
	        {
	        	setfillcolor(EGERGB(Squareness::getSqucolorR(),Squareness::getSqucolorG(),Squareness::getSqucolorB()));
	        	bar(Squareness::getTopleftx(),Squareness::getToplefty(),Squareness::getRightdownx(),Squareness::getRightdowny());
	        }
	        else
	        {
	        	rectangle(Squareness::getTopleftx(),Squareness::getToplefty(),Squareness::getRightdownx(),Squareness::getRightdowny());
			}

}