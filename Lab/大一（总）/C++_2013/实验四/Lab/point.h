#pragma once
#include<iostream>
#include <graphics.h>
#include <stdio.h>
using namespace std;

class Point{
public:
	Point();
	Point (int x1,int y1);
	~ Point();
	Point operator + (Point &a);
	Point operator = (Point &b);
	bool operator == (Point &c);
	bool operator != (Point &d);
	int operator [] (int poi);
	Point operator += (Point &f);
	double x;double y;
	static int getNumber(){return number;}
private:
	static int number;
    };