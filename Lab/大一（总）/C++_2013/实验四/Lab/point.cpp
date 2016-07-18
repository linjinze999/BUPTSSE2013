#include<iostream>
#include"point.h"
#include <stdio.h>
using namespace std;

int Point::number=0;
Point::Point(){Point::number++;};
Point::~Point(){Point::number--;};
Point::Point(int x1,int y1){Point::x=x1;Point::y=y1;};
Point Point::operator + (Point &a)
{
	return Point((x + a.x) / 2,(y + a.y) / 2);
};
Point Point::operator = (Point &b)
{
	x = b.x;
	y = b.y;
	return *this;
};
bool Point::operator == (Point &c)
{
	if(x == c.x && y == c.y)
	{
		return 1;
	}
	else
	{
		return 0;
	}
};
bool Point::operator != (Point &d)
{
	if(x != d.x || y != d.y)
	{
		return 1;
	}
	else
	{
		return 0;
	}
};
int Point::operator [] (int poi)
{
	if(poi == 0) {return x;}
	else {
		if(poi == 1) return y;
		else return 0;
	}
};
Point Point::operator += (Point &f)
{
	x = x + f.x;
	y = y + f.y;
	return *this;
};
