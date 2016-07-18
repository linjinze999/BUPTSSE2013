#include<iostream>
#include"squareness.h"
#include <stdio.h>
#include <math.h>
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
bool Squareness::operator < (Squareness &a)
{
	int s1,s2;
	s1 = abs((Squareness::topleft.x - Squareness::rightdown.x)*(Squareness::topleft.y - Squareness::rightdown.y));
	s2 = abs((a.topleft.x - a.rightdown.x)*(a.topleft.y - a.rightdown.y));
	if (s1 < s2) return 1;
	else return 0;
};
bool Squareness::operator > (Squareness &b)
{
	int s3,s4;
	s3 = abs((Squareness::topleft.x - Squareness::rightdown.x)*(Squareness::topleft.y - Squareness::rightdown.y));
	s4 = abs((b.topleft.x - b.rightdown.x)*(b.topleft.y - b.rightdown.y));
	if (s3 > s4) return 1;
	else return 0;
};
bool Squareness::operator <= (Squareness &c)
{
	int s5,s6;
	s5 = abs((Squareness::topleft.x - Squareness::rightdown.x)*(Squareness::topleft.y - Squareness::rightdown.y));
	s6 = abs((c.topleft.x - c.rightdown.x)*(c.topleft.y - c.rightdown.y));
	if (s5 <= s6) return 1;
	else return 0;
};
bool Squareness::operator >= (Squareness &d)
{
	int s7,s8;
	s7 = abs((Squareness::topleft.x - Squareness::rightdown.x)*(Squareness::topleft.y - Squareness::rightdown.y));
	s8 = abs((d.topleft.x - d.rightdown.x)*(d.topleft.y - d.rightdown.y));
	if (s7 >= s8) return 1;
	else return 0;
};
bool Squareness::operator == (Squareness &e)
{
	int s9,s10;
	s9 = abs((Squareness::topleft.x - Squareness::rightdown.x)*(Squareness::topleft.y - Squareness::rightdown.y));
	s10 = abs((e.topleft.x - e.rightdown.x)*(e.topleft.y - e.rightdown.y));
	if (s9 == s10) return 1;
	else return 0;
};
Squareness Squareness::operator = (Squareness &f)
{
	return Squareness(f.topleft.x,f.topleft.y,f.rightdown.x,f.rightdown.y,f.squareness.fill,f.squareness.color); 
};
Point Squareness::operator [] (int g)
{
	if (g == 0){return Squareness::topleft;}
	else{
		if(g == 1)return Squareness::rightdown;
		else return Point(0,0);
	}
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