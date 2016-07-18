#pragma once
#include<iostream>
#include <graphics.h>
#include <stdio.h>
using namespace std;

class Point{
public:
	Point();
	Point(Point &);
	~ Point();
	double x;double y;
	static int getNumber(){return number;}
private:
	static int number;
    };