#include<iostream>
#include"color.h"
#include <stdio.h>
using namespace std;

int Color::number=0;
Color::Color(){Color::number++;};
Color::~Color(){Color::number--;};
Color::Color(color_t coc,bool cocc){color = coc;fill = cocc;};
Color  Color::operator = (Color &a)
{
	return Color(a.color,a.fill);
};
bool   Color::operator == (Color &b)
{
	if(color == b.color) return 1;
	else return 0;
};
bool   Color::operator != (Color &c)
{
	if(color != c.color) return 1;
	else return 0;
};
color_t  Color::operator [] (int g)
{
	return color;
};