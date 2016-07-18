#include<iostream>
#include <graphics.h>
#include <stdio.h>
#include"circle.h"
#include"color.h"
#include"opengraph.h"
#include"point.h"
#include"squareness.h"
using namespace std;

int main(){
	Opengraph();
	Circle circl;
    circl.setCentre(100,200);
    circl.setRadius(50);
    circl.setCircle(GREEN,1);
	Squareness squ;
    squ.setTopleft(300,300);
    squ.setRightdown(500,700);
    squ.setSquareness(RED,1);
	if(circl.getCirfill())
	{
	    setfillcolor(EGERGB(circl.getCircolorR(),circl.getCircolorG(),circl.getCircolorB()));
	    fillellipse(circl.getCentrex(),circl.getCentrey(),circl.getRadius(),circl.getRadius());
	}        
	else 
	{
	     circle(circl.getCentrex(),circl.getCentrey(),circl.getRadius());
	 }
	 if(squ.getSqufill())
	 {
	     setfillcolor(EGERGB(squ.getSqucolorR(),squ.getSqucolorG(),squ.getSqucolorB()));
	     bar(squ.getTopleftx(),squ.getToplefty(),squ.getRightdownx(),squ.getRightdowny());
	 }
	 else
	{
	    rectangle(squ.getTopleftx(),squ.getToplefty(),squ.getRightdownx(),squ.getRightdowny());
	}
	getch();
	return 0;	
}