#include<iostream>
#include <graphics.h>
#include <stdio.h>
#include"circle.h"
#include"color.h"
#include"opengraph.h"
#include"point.h"
#include"squareness.h"
#include"draw.h"
using namespace std;

void draw(Draw *shap){shap -> draw();}

int main(){
	Opengraph();
	setfont(30,15,"楷体");
	setcolor(BLACK);
	setbkcolor(WHITE);
	int menu=0;
	
	for ( ; is_run(); delay_fps(60))
	{
		mouse_msg msg = {0};
		mousepos(&msg.x,&msg.y);
		cleardevice();
		while (mousemsg()){msg = getmouse();}
		xyprintf(0,500,"x=%3d",msg.x);
		xyprintf(0,530,"y=%3d",msg.y);
		if(menu==0){
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40 )
			{
				line(30,75,120,75);
				if(msg.is_left() && msg.is_down()){menu=1;}
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 120 && msg.y >= 80 )
			{
				line(30,115,120,115);
				if(msg.is_left() && msg.is_down()){menu=2;}
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 160 && msg.y >= 120)
			{
				line(30,155,120,155);
				if(msg.is_left() && msg.is_down()){menu=3;}
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 200 && msg.y >= 160 )
			{
				line(30,195,120,195);
				if(msg.is_left() && msg.is_down()){menu=4;}
			}
		    outtextxy(0,0,"欢迎来到简易画板：");
			outtextxy(0,40,"1、作画");
			outtextxy(0,80,"2、帮助");
			outtextxy(0,120,"3、设置");
			outtextxy(0,160,"4、退出");
			
		}
		if(menu==1){
			outtextxy(0,0,"退出");
	        Circle *circle1 = new Circle();
			draw(circle1);
			Squareness *squ = new Squareness();
			draw(squ);
			if(msg.x <= 60 && msg.x >= 0 && msg.y <= 40 && msg.y >= 0 )
			{
				line(0,35,50,35);
				if(msg.is_left() && msg.is_down()){
					delete circle1;
					delete squ;
					menu=0;
				}
			}
		}
		if(menu==2){
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 40 && msg.y >= 0 )
			{
				line(30,35,170,35);
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40 )
			{
				line(30,75,120,75);
				if(msg.is_left()){menu=0;}
			}
			outtextxy(0,0,"1、暂不支持");
			outtextxy(0,40,"2、退出");
		}
		if(menu==3){
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 40 && msg.y >= 0 )
			{
				line(30,35,170,35);
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40 )
			{
				line(30,75,120,75);
				if(msg.is_left()){menu=0;}
			}
			outtextxy(0,0,"1、暂不支持");
			outtextxy(0,40,"2、退出");
		}
		if(menu==4){
			break;
		}
	}

	 return 0;	
}