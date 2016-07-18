#include<iostream>
#include <graphics.h>
#include <stdio.h>
#include<fstream>
#include"circle.h"
#include"color.h"
#include"opengraph.h"
#include"point.h"
#include"squareness.h"
#include"delta.h"
#include"draw.h"
#include <math.h>
#include"draw anything.h"
using namespace std;

void draw(Draw *shap){shap -> draw();}

int main(){
	Opengraph();
	setfont(30,15,"楷体");
	setcolor(BLACK);
	setbkcolor(WHITE);
	ofstream write;
	int menu = 0;
	int fu = 0,ser = 0,serv = 0;//鼠标辅助
	int type;//默认图形类型指标
	double cx = 0,cy = 0,cr = 0;Color ci;int cxc,cyc,cdr,ccolor=0;ci.fill = 0;ci.color = BLACK;//圆信息
	double dx1 = 0,dy1 = 0,dx2 = 0,dy2 = 0,dx3 = 0,dy3 = 0;Color de;int dcolor=0;de.fill = 0;de.color = BLACK;//三角形信息
	double sx1 = 0,sy1 = 0,sx2 = 0,sy2 = 0;Color sq;int sxs,sys,scolor=0;sq.fill = 0;sq.color = BLACK;//矩形信息
	
	for ( ; is_run(); delay_fps(60))
	{
		mouse_msg msg = {0};
		mousepos(&msg.x,&msg.y);
		cleardevice();
		while (mousemsg()){msg = getmouse();}
		if(menu==0){//主菜单
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40 )
			{
				line(30,75,120,75);
				if(msg.is_left() && msg.is_down()){menu = 1;}
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 120 && msg.y >= 80 )
			{
				line(30,115,120,115);
				if(msg.is_left() && msg.is_down()){menu = 2;}
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 160 && msg.y >= 120)
			{
				line(30,155,120,155);
				if(msg.is_left() && msg.is_down()){menu = 3;}
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 200 && msg.y >= 160 )
			{
				line(30,195,120,195);
				if(msg.is_left() && msg.is_down()){menu = 4;}
			}
		    outtextxy(0,0,"欢迎来到简易画板：");
			outtextxy(0,40,"1、作画");
			outtextxy(0,80,"2、帮助");
			outtextxy(0,120,"3、设置");
			outtextxy(0,160,"4、退出");
			
		}
		if(menu==1){//菜单-作画
			outtextxy(0,0,"请选择绘制图像：");
			outtextxy(0,40,"1、圆");
			outtextxy(0,80,"2、三角形");
			outtextxy(0,120,"3、矩形");
			outtextxy(0,160,"4、自定义");
			outtextxy(0,200,"5、读取文件");
			outtextxy(0,240,"6、退出");
			if(msg.x <= 90 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40 )//菜单-作画-指向询问绘画形式memu8、再指向画圆
			{
				line(30,75,90,75);
				if(fu==0 ){
					if(msg.is_left()  && msg.is_up()){fu = 1;}
				}
				if(fu==1 || ser==1){
					if(msg.is_left()  && msg.is_down()){fu = 0;ser = 0;type = 1;menu = 8;}
				}
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 120 && msg.y >= 80 )//菜单-作画-指向询问绘画形式memu8、再指向画三角形
			{
				line(30,115,150,115);
				if(msg.is_left()  && msg.is_down()){type = 2;menu = 8;fu = 0;}
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 160 && msg.y >= 120 )//菜单-作画-指向询问绘画形式memu8、再指向画矩形
			{
				line(30,155,120,155);
				if(msg.is_left()  && msg.is_down()){type = 3;menu = 8;fu = 0;}
			}
			if(msg.x <= 150 && msg.x >= 0 && msg.y <= 200 && msg.y >= 160 )//菜单-作画-自定义
			{
				line(30,195,150,195);
				if(msg.is_left()  && msg.is_down()){menu = 12;fu = 0;}
			}
			if(msg.x <= 180 && msg.x >= 0 && msg.y <= 240 && msg.y >= 200 )//菜单-作画-读取文件
			{
				line(30,235,180,235);
				if(msg.is_left()  && msg.is_down())
				{
					menu = 13;fu=0;
				}
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 280 && msg.y >= 240 )//菜单-作画-返回菜单
			{
				line(30,275,120,275);
				if(msg.is_left()  && msg.is_down()){menu = 0;fu = 0;ser = 0;}
			}
		}
		
		if(menu==2){//菜单-帮助
			if(msg.x <= 570 && msg.x >= 0 && msg.y <= 40 && msg.y >= 0 )
			{
				line(30,35,540,35);
			}
			if(msg.x <= 840 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40 )
			{
				line(30,75,840,75);
			}
			if(msg.x <= 810 && msg.x >= 0 && msg.y <= 160 && msg.y >= 80 )
			{
				line(30,115,780,115);
				line(30,155,660,155);
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 200 && msg.y >= 160 )
			{
				line(30,195,120,195);
				if(msg.is_left()){menu = 0;}
			}
			outtextxy(0,0,"1、本程序采用鼠标结构，请以鼠标操作；");
			outtextxy(0,40,"2、绘图将有两种形式:①默认图形②自定义绘图(可保存信息)；");
			outtextxy(0,80,"3、自定义绘图时请按住鼠标左键，拖动鼠标，得出相应图形");
			outtextxy(30,120,"（绘三角形时只需按下鼠标左键确认三个顶点）；");
			outtextxy(0,160,"4、退出");
		}
		if(menu==3){//菜单-设置
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 40 && msg.y >= 0 )
			{
				line(30,35,170,35);
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40 )
			{
				line(30,75,120,75);
				if(msg.is_left()){menu = 0;}
			}
			outtextxy(0,0,"1、暂不支持");
			outtextxy(0,40,"2、退出");
		}
		if(menu==4){//菜单-退出程序
			break;
		}
		if(menu==5){//画默认圆
			Circle *circle1 = new Circle();
			draw(circle1);
			ser = 1;
			outtextxy(0,0,"退出");
			if(msg.x <= 60 && msg.x >= 0 && msg.y <= 40 && msg.y >= 0 )
			{
				line(0,35,60,35);
				if(msg.is_left() && msg.is_down()){
					delete circle1;
					menu = 1;
				}
			}
		}
		if(menu==6){//画默认三角形
		    Delta *delta=new Delta();
			draw(delta);
			outtextxy(0,0,"退出");
			if(msg.x <= 60 && msg.x >= 0 && msg.y <= 40 && msg.y >= 0 )
			{
				line(0,35,60,35);
				if(msg.is_left() && msg.is_down()){
					delete delta;
					menu = 1;
				}
			}
		}
		if(menu==7){//画默认矩形
			Squareness *squ = new Squareness();
			draw(squ);
			outtextxy(0,0,"退出");
			if(msg.x <= 60 && msg.x >= 0 && msg.y <= 40 && msg.y >= 0 )
			{
				line(0,35,60,35);
				if(msg.is_left() && msg.is_down()){
					delete squ;
					menu = 1;
				}
			}
		}
		
		if(menu==8){//菜单-作画-询问绘画形式
			outtextxy(0,0,"请选择绘画形式：");
		    outtextxy(0,40,"1、默认图画");
			outtextxy(0,80,"2、自定义作画");
			outtextxy(0,120,"3、退出");
			if(msg.x <= 180 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40 )//菜单-作画-询问绘画形式-默认图画
			{
				line(30,75,180,75);
				if(fu==0){
					if(msg.is_left()  && msg.is_up()){fu = 1;}
				}
				if(fu==1){
					if(msg.is_left()  && msg.is_down()){
						fu = 0;
						if(type==1){menu = 5;}
						if(type==2){menu = 6;}
						if(type==3){menu = 7;}
					}
				}
			}
			if(msg.x <= 210 && msg.x >= 0 && msg.y <= 120 && msg.y >= 80 )//菜单-作画-询问绘画形式-自定义作画
			{
				line(30,115,210,115);
				if(fu==0){
					if(msg.is_left()  && msg.is_up()){fu = 1;}
				}
				if(fu==1){
				    if(msg.is_left()  && msg.is_down()){
						fu = 0;
					    if(type==1){menu = 9;}
						if(type==2){menu = 10;}
						if(type==3){menu = 11;}
				    }
				}
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 160 && msg.y >= 120 )//菜单-作画-询问绘画形式-返回作画
			{
				line(30,155,120,155);
				if(fu==0){
					if(msg.is_left()  && msg.is_up()){fu = 1;}
				}
				if(fu==1){
					if(msg.is_left()  && msg.is_down()){ser = 1;fu = 0;menu = 1;}
				}
			}
		}
		if(menu==9){//自定义画圆
			outtextxy(0,0,"按住鼠标左键,拖动鼠标得出图形");
			outtextxy(0,40,"1、清屏重画");
			outtextxy(0,80,"2、退出");
			setfillcolor(BLACK);
			bar(0,116,90,155);
			outtextxy(15,120,"黑色");
			rectangle(0,155,90,195);
			outtextxy(15,160,"白色");
			setfillcolor(RED);
			bar(0,195,90,235);
			outtextxy(15,200,"红色");
			setfillcolor(BLUE);
			bar(0,235,90,275);
			outtextxy(15,240,"蓝色");
			setfillcolor(YELLOW);
			bar(0,275,90,315);
			outtextxy(15,280,"黄色");
			rectangle(0,315,92,355);
			outtextxy(0,320,"不填充");
			outtextxy(0,360,"请选择");
			outtextxy(0,400,"以上颜");
		    outtextxy(0,440,"色填充");
			if(fu==0&&serv==0){if(msg.is_left()  && msg.is_up()){serv = 1;}}
			if(fu==0&&serv==1){if(msg.is_down()){cx = msg.x;cy = msg.y;fu = 1;}}
			if(fu==1){
			    cleardevice();
			    mousepos(&cxc,&cyc);
				cr = sqrt((cxc-cx)*(cxc-cx) + (cyc-cy)*(cyc-cy));
				Circle *cia = new Circle(cx,cy,cr,0,BLACK);
				draw(cia);
				delete cia;
				outtextxy(0,0,"按住鼠标左键,拖动鼠标得出图形");
			    outtextxy(0,40,"1、清屏重画");
			    outtextxy(0,80,"2、退出");
				setfillcolor(BLACK);
			    bar(0,116,90,155);
			    outtextxy(15,120,"黑色");
			    rectangle(0,155,90,195);
		    	outtextxy(15,160,"白色");
		    	setfillcolor(RED);
		    	bar(0,195,90,235);
		    	outtextxy(15,200,"红色");
		    	setfillcolor(BLUE);
		    	bar(0,235,90,275);
		    	outtextxy(15,240,"蓝色");
		    	setfillcolor(YELLOW);
			    bar(0,275,90,315);
			    outtextxy(15,280,"黄色");
				rectangle(0,315,92,355);
				outtextxy(0,320,"不填充");
			    outtextxy(0,360,"请选择");
			    outtextxy(0,400,"以上颜");
		    	outtextxy(0,440,"色填充");
				if(msg.is_up()){fu=0;serv=2;}
			}
			Circle *cib = new Circle(cx,cy,cr,ci.fill,ci.color);
			if(serv==2){draw(cib);}
			if (msg.x <= 180 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40)
			{
				line(30,75,180,75);
				if(msg.is_left() && msg.is_down()){
				fu = 0;
				serv = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 155 && msg.y >= 115)
			{
				line(20,148,70,148);
				if(msg.is_left() && msg.is_down()){
				ccolor = 1;
				ci.color = BLACK;
				ci.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 195 && msg.y >= 155)
			{
				line(20,188,70,188);
				if(msg.is_left() && msg.is_down()){
				ccolor = 2;
				ci.color = WHITE;
				ci.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 235 && msg.y >= 195)
			{
				line(20,228,70,228);
				if(msg.is_left() && msg.is_down()){
				ccolor = 3;
				ci.color = RED;
				ci.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 275 && msg.y >= 235)
			{
				line(20,268,70,268);
				if(msg.is_left() && msg.is_down()){
				ccolor = 4;
				ci.color = BLUE;
				ci.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 315 && msg.y >= 275)
			{
				line(20,308,70,308);
				if(msg.is_left() && msg.is_down()){
				ccolor = 5;
				ci.color = YELLOW;
				ci.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 355 && msg.y >= 315)
			{
				line(5,348,85,348);
				if(msg.is_left() && msg.is_down()){
				ccolor = 0;
				ci.color = BLACK;
				ci.fill = 0;
				}
			}
			if(serv == 1 || serv == 2){
			    if(msg.x <= 120 && msg.x >= 0 && msg.y <= 120 && msg.y >= 80 )
			    {
				    line(30,114,120,114);
				    if(msg.is_left() && msg.is_down()){
					menu=1;
					serv=0;
					ser=1;
					write.open("message2.txt",ios::app);
					write<<"1"<<endl;
					write<<cx<<" "<<cy<<" "<<cr<<endl;
					write<<ccolor<<" "<<ci.fill<<endl;
					write.close();
					cx = cy = cr = 0;
					delete cib;
				    }
			     }
			}
		}
		if(menu==10){//自定义画三角形
			outtextxy(0,0,"按下鼠标左键，确认三个顶点");
			outtextxy(0,40,"1、清屏重画");
			outtextxy(0,80,"2、退出");
			setfillcolor(BLACK);
			bar(0,116,90,155);
			outtextxy(15,120,"黑色");
			rectangle(0,155,90,195);
			outtextxy(15,160,"白色");
			setfillcolor(RED);
			bar(0,195,90,235);
			outtextxy(15,200,"红色");
			setfillcolor(BLUE);
			bar(0,235,90,275);
			outtextxy(15,240,"蓝色");
			setfillcolor(YELLOW);
			bar(0,275,90,315);
			outtextxy(15,280,"黄色");
			rectangle(0,315,92,355);
			outtextxy(0,320,"不填充");
			outtextxy(0,360,"请选择");
			outtextxy(0,400,"以上颜");
		    outtextxy(0,440,"色填充");
			setfillcolor(BLACK);
		    fillellipse(dx1,dy1,2,2);
			fillellipse(dx2,dy2,2,2);
			fillellipse(dx3,dy3,2,2);
			if(fu==0&&serv==0){if(msg.is_left()  && msg.is_up()){serv=1;}}
			if(fu==0&&serv==1){if(msg.is_down()){dx1 = msg.x;dy1 = msg.y;fu = 1;}}
			if(fu==1&&serv==1){if(msg.is_up()){serv=2;}}
			if(fu==1&&serv==2){if(msg.is_down()){dx2 = msg.x;dy2 = msg.y;serv = 3;}}
			if(fu==1&&serv==3){if(msg.is_up()){serv = 4;}}
			if(fu==1&&serv==4){if(msg.is_down()){dx3 = msg.x;dy3 = msg.y;serv = 5;}}
			Delta *delta = new Delta(dx1,dy1,dx2,dy2,dx3,dy3,de.fill,de.color);
			if(serv==5){draw(delta);}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 155 && msg.y >= 115)
			{
				line(20,148,70,148);
				if(msg.is_left() && msg.is_down()){
				dcolor = 1;
				de.color = BLACK;
				de.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 195 && msg.y >= 155)
			{
				line(20,188,70,188);
				if(msg.is_left() && msg.is_down()){
				dcolor = 2;
				de.color = WHITE;
				de.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 235 && msg.y >= 195)
			{
				line(20,228,70,228);
				if(msg.is_left() && msg.is_down()){
				dcolor = 3;
				de.color = RED;
				de.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 275 && msg.y >= 235)
			{
				line(20,268,70,268);
				if(msg.is_left() && msg.is_down()){
				dcolor = 4;
				de.color = BLUE;
				de.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 315 && msg.y >= 275)
			{
				line(20,308,70,308);
				if(msg.is_left() && msg.is_down()){
				dcolor = 5;
				de.color = YELLOW;
				de.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 355 && msg.y >= 315)
			{
				line(5,348,85,348);
				if(msg.is_left() && msg.is_down()){
				dcolor = 0;
				de.color = BLACK;
				de.fill = 0;
				}
			}
			if (msg.x <= 180 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40)
			{
				line(30,75,180,75);
				if(msg.is_left() && msg.is_down())
				{
				    dx1 = dy1 = dx2 = dy2 = dx3 = dy3 = 0;
				    fu = 0;
				    serv = 1;
				}
			}
			if(serv == 1 || serv == 2 || serv == 3 || serv == 4 || serv == 5){
			    if(msg.x <= 120 && msg.x >= 0 && msg.y <= 120 && msg.y >= 80 )
			    {
				    line(30,115,120,115);
				    if(msg.is_left() && msg.is_down()){
					menu = 1;
					serv = 0;
					ser = 1;
					write.open("message2.txt",ios::app);
					write<<"2"<<endl;
					write<<dx1<<" "<<dy1<<" "<<dx2<<" "<<dy2<<" "<<dx3<<" "<<dy3<<endl;
					write<<dcolor<<" "<<de.fill<<endl;
					write.close();
					dx1 = dy1 = dx2 = dy2 = dx3 = dy3 = 0;
					delete delta;
				    }
			     }
			}
		}
		if(menu==11){//自定义画矩形
			outtextxy(0,0,"按住鼠标左键,拖动鼠标得出图形");
			outtextxy(0,40,"1、清屏重画");
			outtextxy(0,80,"2、退出");
			setfillcolor(BLACK);
			bar(0,116,90,155);
			outtextxy(15,120,"黑色");
			rectangle(0,155,90,195);
		    outtextxy(15,160,"白色");
		    setfillcolor(RED);
		    bar(0,195,90,235);
		    outtextxy(15,200,"红色");
		    setfillcolor(BLUE);
		    bar(0,235,90,275);
		    outtextxy(15,240,"蓝色");
		    setfillcolor(YELLOW);
			bar(0,275,90,315);
			outtextxy(15,280,"黄色");
			rectangle(0,315,92,355);
			outtextxy(0,320,"不填充");
			outtextxy(0,360,"请选择");
			outtextxy(0,400,"以上颜");
		    outtextxy(0,440,"色填充");
			if(fu==0&&serv==0){if(msg.is_left()  && msg.is_up()){serv = 1;}}
			if(fu==0&&serv==1){if(msg.is_down()){sx1 = msg.x;sy1 = msg.y;fu = 1;}}
			if(fu==1){
			    cleardevice();
			    mousepos(&sxs,&sys);
				sx2 = sxs;
				sy2 = sys;
				Squareness *squa = new Squareness(sx1,sy1,sx2,sy2,0,BLACK);
				draw(squa);
				delete squa;
				outtextxy(0,0,"按住鼠标左键,拖动鼠标得出图形");
			    outtextxy(0,40,"1、清屏重画");
			    outtextxy(0,80,"2、退出");
				setfillcolor(BLACK);
			    bar(0,116,90,155);
			    outtextxy(15,120,"黑色");
			    rectangle(0,155,90,195);
		    	outtextxy(15,160,"白色");
		    	setfillcolor(RED);
		    	bar(0,195,90,235);
		    	outtextxy(15,200,"红色");
		    	setfillcolor(BLUE);
		    	bar(0,235,90,275);
		    	outtextxy(15,240,"蓝色");
		    	setfillcolor(YELLOW);
			    bar(0,275,90,315);
			    outtextxy(15,280,"黄色");
				rectangle(0,315,92,355);
				outtextxy(0,320,"不填充");
			    outtextxy(0,360,"请选择");
			    outtextxy(0,400,"以上颜");
		    	outtextxy(0,440,"色填充");
				if(msg.is_up()){fu=0;serv=2;}
			}
			Squareness *squb = new Squareness(sx1,sy1,sx2,sy2,sq.fill,sq.color);
			if(serv==2){draw(squb);}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 155 && msg.y >= 115)
			{
				line(20,148,70,148);
				if(msg.is_left() && msg.is_down()){
				scolor = 1;
				sq.color = BLACK;
				sq.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 195 && msg.y >= 155)
			{
				line(20,188,70,188);
				if(msg.is_left() && msg.is_down()){
				scolor = 2;
				sq.color = WHITE;
				sq.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 235 && msg.y >= 195)
			{
				line(20,228,70,228);
				if(msg.is_left() && msg.is_down()){
				scolor = 3;
				sq.color = RED;
				sq.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 275 && msg.y >= 235)
			{
				line(20,268,70,268);
				if(msg.is_left() && msg.is_down()){
				scolor = 4;
				sq.color = BLUE;
				sq.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 315 && msg.y >= 275)
			{
				line(20,308,70,308);
				if(msg.is_left() && msg.is_down()){
				scolor = 5;
				sq.color = YELLOW;
				sq.fill = 1;
				}
			}
			if (msg.x <= 90 && msg.x >= 0 && msg.y <= 355 && msg.y >= 315)
			{
				line(5,348,85,348);
				if(msg.is_left() && msg.is_down()){
				scolor = 0;
				sq.color = BLACK;
				sq.fill = 0;
				}
			}
			if (msg.x <= 180 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40)
			{
				line(30,75,180,75);
				if(msg.is_left() && msg.is_down()){
				    fu = 0;
				    serv = 1;
				}
			}
			if(serv == 1 || serv == 2){
			    if(msg.x <= 120 && msg.x >= 0 && msg.y <= 120 && msg.y >= 80 )
			    {
				    line(30,115,120,115);
				    if(msg.is_left() && msg.is_down()){
					menu=1;
					serv=0;
					ser=1;
					write.open("message2.txt",ios::app);
					write<<"3"<<endl;
					write<<sx1<<" "<<sy1<<" "<<sx2<<" "<<sy2<<endl;
					write<<scolor<<" "<<sq.fill<<endl;
					write.close();
					sx1 = sx2 = sy1 = sy2 = 0;
					delete squb;
				    }
			     }
			}
		}
		if(menu == 12){
			menu = drawany();
			setfont(30,15,"楷体");
		}
		if(menu == 13)//菜单-作画-读取文件
		{
			ifstream read;
			read.open("message.txt");
			if(read.fail())//菜单-作画-读取文件-查看文件是否存在
			{
				cleardevice();
				outtextxy(0,0,"文件不存在");
				delay_ms(2000);
				menu = 1;
			}
			else{
				while(!read.eof()){
					read >> type;
					if(type > 3 || type <=0)
					{
						cleardevice();
						outtextxy(0,0,"图形类型信息错误，无法读取");
						delay_ms(2000);
						type = 0;
						menu = 1;
					}
					else
					{
						if(type==1)//菜单-作画-读取文件-读取圆信息
						{
							read>>cx>>cy>>cr;
							if(cx == 0 || cy == 0){
								cleardevice();
								outtextxy(0,0,"圆心坐标读取错误，默认为（100,100）");
								delay_ms(2000);
								cx=cy=100;
							}
							if(cr == 0){
								cleardevice();
								outtextxy(0,0,"圆半径读取错误，默认为50");
								delay_ms(2000);
								cr = 50;
							}
							read>>ccolor>>ci.fill;
							if(ccolor > 5 || ccolor <= 0){
								cleardevice();
								outtextxy(0,0,"圆填充颜色读取错误，默认为BLACK");
								delay_ms(2000);
								ci.color = BLACK;
							}
							else{
								if(ccolor==1)ci.color=BLACK;
								if(ccolor==2)ci.color=WHITE;
								if(ccolor==3)ci.color=RED;
								if(ccolor==4)ci.color=BLUE;
								if(ccolor==5)ci.color=YELLOW;
							}
							if(ci.fill > 1 || ci.fill < 0){
								cleardevice();
								outtextxy(0,0,"圆是否填充读取错误，默认为否");
								delay_ms(2000);
								ci.fill=0;
							}
						}
						if(type==2)//菜单-作画-读取文件-读取三角形信息
						{
							read>>dx1>>dy1>>dx2>>dy2>>dx3>>dy3;
							if(dx1 == 0 || dy1 == 0){
								cleardevice();
								outtextxy(0,0,"三角形第一顶点坐标读取错误，默认为（500,100）");
								delay_ms(2000);
								dx1 = 500;
								dy1 = 100;
							}
							if(dx2 == 0 || dy2 == 0){
								cleardevice();
								outtextxy(0,0,"三角形第二顶点坐标读取错误，默认为（600,200）");
								delay_ms(2000);
								dx2 = 600;
								dy2 = 200;
							}
							if(dx3 == 0 || dy3 == 0){
								cleardevice();
								outtextxy(0,0,"三角形第三顶点坐标读取错误，默认为（400,200）");
								delay_ms(2000);
								dx3 = 400;
								dy3 = 200;
							}
							read>>dcolor>>de.fill;
							if(dcolor > 5 || dcolor <=0){
								cleardevice();
								outtextxy(0,0,"三角形填充颜色读取错误，默认为BLACK");
								delay_ms(2000);
								de.color = BLACK;
							}
							else{
								if(dcolor==1)de.color=BLACK;
								if(dcolor==2)de.color=WHITE;
								if(dcolor==3)de.color=RED;
								if(dcolor==4)de.color=BLUE;
								if(dcolor==5)de.color=YELLOW;
							}
							if(de.fill > 1 || de.fill < 0){
								cleardevice();
								outtextxy(0,0,"三角形是否填充读取错误，默认为否");
								delay_ms(2000);
								de.fill=0;
							}
						}
						if(type==3)//菜单-作画-读取文件-读取矩形信息
						{
							read>>sx1>>sy1>>sx2>>sy2;
							if(sx1 == 0 || sy1 == 0){
								cleardevice();
								outtextxy(0,0,"矩形第一顶点坐标读取错误，默认为（300,300）");
								delay_ms(2000);
								sx1=sy1=300;
							}
							if(sx2 == 0 || sy2 == 0){
								cleardevice();
								outtextxy(0,0,"矩形第二顶点坐标读取错误，默认为（500,700）");
								delay_ms(2000);
								sx2=500;
								sy2=700;
							}
							read>>scolor>>sq.fill;
							if(scolor > 5 || scolor <= 0){
								cleardevice();
								outtextxy(0,0,"矩形填充颜色读取错误，默认为BLACK");
								delay_ms(2000);
								sq.color = BLACK;
							}
							else{
								if(scolor==1)sq.color=BLACK;
								if(scolor==2)sq.color=WHITE;
								if(scolor==3)sq.color=RED;
								if(scolor==4)sq.color=BLUE;
								if(scolor==5)sq.color=YELLOW;
							}
							if(sq.fill > 1 || sq.fill < 0){
								cleardevice();
								outtextxy(0,0,"矩形是否填充读取错误，默认为否");
								delay_ms(2000);
								sq.fill=0;
							}
						}
					}
				}
			}
			read.close();
			menu=14;
		}
		if(menu == 14)//菜单-作画-读取文件-绘出图形
		{
			Circle *ciacleb = new Circle(cx,cy,cr,ci.fill,ci.color);
			draw(ciacleb);
			Delta *deltaa = new Delta(dx1,dy1,dx2,dy2,dx3,dy3,de.fill,de.color);
			draw(deltaa);
			Squareness *squaa = new Squareness(sx1,sy1,sx2,sy2,sq.fill,sq.color);
			draw(squaa);
			outtextxy(0,0,"退出");
			if(msg.x <= 60 && msg.x >= 0 && msg.y <= 40 && msg.y >= 0 )
			{
				line(0,35,60,35);
				if(msg.is_left()){
					menu = 1;
					ser=1;
					delete ciacleb;
					delete deltaa;
					delete squaa;
					cx = cy = cr = 0;ci.color = BLACK;ci.fill = 0;
					dx1= dy1 = dx2 = dy2 = dx3 = dy3 = 0;de.color = BLACK;de.fill = 0;
					sx1= sy1 = sx2 = sy2 = 0;sq.color = BLACK;sq.fill = 0;
				} 
			}
			
		}
}

	 return 0;	
}