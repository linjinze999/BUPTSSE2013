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
	Color(color_t coc,bool cocc);
	Color  operator = (Color &a);
	bool  operator == (Color &b);
	bool  operator != (Color &c);
	color_t  operator [] (int g);
	color_t color;bool fill;
	static int getNumber(){return number;}
private:
	static int number;
    };