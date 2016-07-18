#pragma once
#include<iostream>
#include <graphics.h>
#include <stdio.h>
using namespace std;

class Color{
public:
	Color();
	Color(Color&);
	~Color();
	color_t color;bool fill;
	static int getNumber(){return number;}
private:
	static int number;
    };