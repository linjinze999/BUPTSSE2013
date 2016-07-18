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
	int fu = 0,ser = 0,serv = 0,fillc = 0,coprt = 0;//鼠标辅助
	int threed[8];threed[0]=107;threed[1]=85;threed[2]=107;threed[3]=107;threed[4]=117;threed[5]=96;threed[6]=107;threed[7]=85;//“填充”旁边的辅助三角形
	int threedd[8];threedd[0]=107;threedd[1]=125;threedd[2]=107;threedd[3]=147;threedd[4]=117;threedd[5]=136;threedd[6]=107;threedd[7]=125;//“符号”旁边的辅助三角形
	int type;//默认图形类型指标
	double cx = 0,cy = 0,cr = 0;Color ci;int cxc,cyc,cdr,ccolor=0;ci.fill = 0;ci.color = BLACK;//圆信息
	double dx1 = 0,dy1 = 0,dx2 = 0,dy2 = 0,dx3 = 0,dy3 = 0;Color de;int dcolor=0;de.fill = 0;de.color = BLACK;//三角形信息
	double sx1 = 0,sy1 = 0,sx2 = 0,sy2 = 0;Color sq;int sxs,sys,scolor=0;sq.fill = 0;sq.color = BLACK;//矩形信息
	Circle *cib[50];int sizenum = 0;Delta *delta[50];Squareness *squb[50];//画多个图形
	
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
				if(msg.is_left() && msg.is_down()){menu = 4;}
			}
		    outtextxy(0,0,"欢迎来到简易画板：");
			outtextxy(0,40,"1、作画");
			outtextxy(0,80,"2、帮助");
			outtextxy(0,120,"3、退出");
			
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
			if(msg.x <= 810 && msg.x >= 0 && msg.y <= 240 && msg.y >= 160 )
			{
				line(30,195,880,195);
				line(30,235,880,235);
			}
			if(msg.x <= 120 && msg.x >= 0 && msg.y <= 280 && msg.y >= 240 )
			{
				line(30,275,120,275);
				if(msg.is_left()){menu = 0;}
			}
			outtextxy(0,0,"1、本程序采用鼠标结构，请以鼠标操作。");
			outtextxy(0,40,"2、绘图将有两种形式:①默认图形②自定义绘图(可保存信息)。");
			outtextxy(0,80,"3、自定义绘图时请按住鼠标左键，拖动鼠标，得出相应图形");
			outtextxy(30,120,"（绘三角形时只需按下鼠标左键确认三个顶点）。");
			outtextxy(0,160,"4、若画出2个及以上的图形，可在“符号”选项中比较第一与第二");
			outtextxy(45,200,"图形，或得出第一图形顶点坐标，或令第二图形等于第一图形。");
			outtextxy(0,240,"5、退出");
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
			outtextxy(0,0,"按住鼠标左键,拖动鼠标得出图形(出错请选“1”重置)");
			outtextxy(0,40,"1、清屏重画");
			outtextxy(0,80,"2、填充");
			outtextxy(0,120,"3、符号");
			outtextxy(0,160,"4、退出");
			drawpoly(4,threed);
			drawpoly(4,threedd);
			if(sizenum >= 1){
				for(int sizeb = 0;sizeb < sizenum;sizeb++){
				draw(cib[sizeb]);
				}
			}
			if(fu==0&&serv==0){if(msg.is_left()  && msg.is_up()){serv = 1;}}
			if(fu==0&&serv==1){if(msg.is_down()){cx = msg.x;cy = msg.y;fu = 1;}}
			if(fu==1){
			    cleardevice();
				if(sizenum >= 1){
					for(int sizea = 0;sizea < sizenum;sizea++){
					    draw(cib[sizea]);
					}
				}
			    mousepos(&cxc,&cyc);
				cr = sqrt((cxc-cx)*(cxc-cx) + (cyc-cy)*(cyc-cy));
				Circle *cia = new Circle(cx,cy,cr,0,BLACK);
				draw(cia);
				delete cia;
				outtextxy(0,0,"按住鼠标左键,拖动鼠标得出图形(出错请选“1”重置)");
			    outtextxy(0,40,"1、清屏重画");
			    outtextxy(0,80,"2、填充");
				outtextxy(0,120,"3、符号");
			    outtextxy(0,160,"4、退出");
			    drawpoly(4,threed);
			    drawpoly(4,threedd);
				if(msg.is_up()){
					fu=0;
					serv=2;
					cib[sizenum] = new Circle(cx,cy,cr,ci.fill,ci.color);
					sizenum++;
				}
			}
			if(serv==2){
				fu = 0;
				serv = 1;
			}
			if (msg.x <= 180 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40)
			{//清屏
				line(30,75,180,75);
				if(msg.is_left() && msg.is_down()){
				fu = 0;
				serv = 1;
				sizenum = 0;
				}
			}
			if (msg.x <= 120 && msg.x >= 0 && msg.y <= 120 && msg.y >= 80)
			{//填充
				line(30,115,120,115);
				setfillcolor(BLACK);
	            fillpoly(3,threed);
				setfillcolor(BLACK);
				bar(122,80,212,120);
				outtextxy(135,85,"黑色");
				rectangle(122,120,212,160);
		    	outtextxy(135,125,"白色");
		    	setfillcolor(RED);
		    	bar(122,160,212,200);
		    	outtextxy(135,165,"红色");
		    	setfillcolor(BLUE);
		    	bar(122,200,212,240);
		    	outtextxy(135,205,"蓝色");
		    	setfillcolor(YELLOW);
				bar(122,240,212,280);
				outtextxy(135,245,"黄色");
				rectangle(119,280,213,320);
				outtextxy(121,285,"不填充");
				fillc = 1;
			}
			else
			{
				if(fillc==1){
				    if(msg.x <= 210 && msg.x >= 120 && msg.y <= 320 && msg.y >= 80)
					{
						fu=serv=0;
						setfillcolor(BLACK);
	                    fillpoly(3,threed);
						setfillcolor(BLACK);
						bar(122,80,212,120);
						outtextxy(135,85,"黑色");
						rectangle(122,120,212,160);
		    			outtextxy(135,125,"白色");
		    			setfillcolor(RED);
		    			bar(122,160,212,200);
		    			outtextxy(135,165,"红色");
		    			setfillcolor(BLUE);
		    			bar(122,200,212,240);
		    			outtextxy(135,205,"蓝色");
		    			setfillcolor(YELLOW);
						bar(122,240,212,280);
						outtextxy(135,245,"黄色");
						rectangle(119,280,213,320);
						outtextxy(121,285,"不填充");
						if(sizenum >= 1){
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 120 && msg.y >= 80)
						{
							line(140,113,190,113);
							if(msg.is_left() && msg.is_down()){
							cib[sizenum-1]->colornum = 1;
							cib[sizenum-1]->setCircle(BLACK,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 160 && msg.y >= 120)
						{
							line(140,153,190,153);
							if(msg.is_left() && msg.is_down()){
							cib[sizenum-1]->colornum = 2;
							cib[sizenum-1]->setCircle(WHITE,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 200 && msg.y >= 160)
						{
							line(140,193,190,193);
							if(msg.is_left() && msg.is_down()){
							cib[sizenum-1]->colornum = 3;
							cib[sizenum-1]->setCircle(RED,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 240 && msg.y >= 200)
						{
							line(140,233,190,233);
							if(msg.is_left() && msg.is_down()){
							cib[sizenum-1]->colornum = 4;
							cib[sizenum-1]->setCircle(BLUE,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 280 && msg.y >= 240)
						{
							line(140,273,190,273);
							if(msg.is_left() && msg.is_down()){
							cib[sizenum-1]->colornum = 5;
							cib[sizenum-1]->setCircle(YELLOW,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 320 && msg.y >= 280)
						{
							line(125,313,205,313);
							if(msg.is_left() && msg.is_down()){
							cib[sizenum-1]->colornum = 0;
							cib[sizenum-1]->setCircle(BLACK,0);
							}
						}
					    }
					}
					else{fillc = 0;fu = 0;serv = 1;}
				}
			}
			if (msg.x <= 120 && msg.x >= 0 && msg.y <= 160 && msg.y >= 120)
			{//符号
				line(30,155,120,155);
				setfillcolor(BLACK);
	            fillpoly(3,threedd);
				if(sizenum > 1){
				rectangle(122,120,172,160);
				outtextxy(135,125,"<");
				rectangle(122,160,172,200);
		    	outtextxy(135,165,">");
		    	rectangle(122,200,172,240);
		    	outtextxy(135,205,"<=");
		    	rectangle(122,240,172,280);
		    	outtextxy(135,245,">=");
				rectangle(122,280,172,320);
				outtextxy(135,285,"==");
				rectangle(122,320,172,360);
				outtextxy(135,325,"=");
				rectangle(122,360,172,400);
				outtextxy(124,365,"[0]");
				}
				else{
				    rectangle(122,120,332,160);
				    outtextxy(128,125,"画图数量少于2");
				}
				coprt = 1;
			}
			else
			{
				if(coprt==1){
				    if(msg.x <= 210 && msg.x >= 120 && msg.y <= 400 && msg.y >= 120)
					{
						setfillcolor(BLACK);
	                    fillpoly(3,threedd);
						if(sizenum > 1){
						rectangle(122,120,172,160);
						outtextxy(135,125,"<");
						rectangle(122,160,172,200);
		    			outtextxy(135,165,">");
		    			rectangle(122,200,172,240);
		    			outtextxy(135,205,"<=");
		    			rectangle(122,240,172,280);
		    			outtextxy(135,245,">=");
						rectangle(122,280,172,320);
						outtextxy(135,285,"==");
						rectangle(122,320,172,360);
						outtextxy(135,325,"=");
						rectangle(122,360,172,400);
						outtextxy(124,365,"[0]");
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 160 && msg.y >= 120)
						{
							line(135,155,155,155);
							rectangle(172,120,252,160);
							if(cib[0]<cib[1]){outtextxy(175,125,"true");}
							else{outtextxy(175,125,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 200 && msg.y >= 160)
						{
							line(135,195,155,195);
							rectangle(172,160,252,200);
							if(cib[0]>cib[1]){outtextxy(175,165,"true");}
							else{outtextxy(175,165,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 240 && msg.y >= 200)
						{
							line(135,235,155,235);
							rectangle(172,200,252,240);
							if(cib[0]<=cib[1]){outtextxy(175,205,"true");}
							else{outtextxy(175,205,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 280 && msg.y >= 240)
						{
							line(135,275,155,275);
							rectangle(172,240,252,280);
							if(cib[0]>=cib[1]){outtextxy(175,245,"true");}
							else{outtextxy(175,245,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 320 && msg.y >= 280)
						{
							line(135,315,155,315);
							rectangle(172,280,252,320);
							if(cib[0]==cib[1]){outtextxy(175,285,"true");}
							else{outtextxy(175,285,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 360 && msg.y >= 320)
						{
							line(135,355,155,355);
							if(msg.is_left() && msg.is_down()){
							rectangle(172,320,252,360);
							cib[1] = cib[0];
							}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 400 && msg.y >= 360)
						{
							Point caxy;
							int caxyx,caxyy;
							caxy = (*cib[0])[0];
							caxyx=caxy.x;
							caxyy=caxy.y;
							line(135,395,155,395);
							rectangle(172,360,312,400);
							xyprintf(175,365,"(%3d,%3d)",caxyx,caxyy);
						}

					    }
						else{
							rectangle(122,120,332,160);
						    outtextxy(128,125,"画图数量少于2");
						}
					}
					else{coprt = 0;}
				}
			}
			if(serv == 1 || serv == 2){//退出
			    if(msg.x <= 120 && msg.x >= 0 && msg.y <= 200 && msg.y >= 160 )
			    {
				    line(30,194,120,194);
				    if(msg.is_left() && msg.is_down()){
					menu=1;
					serv=0;
					ser=1;
					write.open("message2.txt",ios::app);
					if(sizenum >=1){
					    for(int csize = 0;csize < sizenum;csize++){
					    write<<"1"<<endl;
						write<<cib[csize]->centre.x<<" "<<cib[csize]->centre.y<<" "<<cib[csize]->getRadius()<<endl;
						write<<cib[csize]->colornum<<" "<<cib[csize]->getCirfill()<<endl;
					    }
					}
					write.close();
					cx = cy = cr = 0;
					for(int deletecib = 0;deletecib < sizenum;deletecib++)
					{
						delete cib[deletecib];
					}
					sizenum = 0;
				    }
			     }
			}
		}
		if(menu==10){//自定义画三角形
			outtextxy(0,0,"按下鼠标左键，确认三个顶点");
			outtextxy(0,40,"1、清屏重画");
			outtextxy(0,80,"2、填充");
			outtextxy(0,120,"3、符号");
			outtextxy(0,160,"4、退出");
			drawpoly(4,threed);
			drawpoly(4,threedd);
			setfillcolor(BLACK);
		    fillellipse(dx1,dy1,2,2);
			fillellipse(dx2,dy2,2,2);
			fillellipse(dx3,dy3,2,2);
			if(sizenum >= 1){
				for(int sizede = 0;sizede < sizenum;sizede++){
				    draw(delta[sizede]);
				}
			}
			if(fu==0&&serv==0){if(msg.is_left()  && msg.is_up()){serv=1;}}
			if(fu==0&&serv==1){if(msg.is_down()){dx1 = msg.x;dy1 = msg.y;fu = 1;}}
			if(fu==1&&serv==1){if(msg.is_up()){serv=2;}}
			if(fu==1&&serv==2){if(msg.is_down()){dx2 = msg.x;dy2 = msg.y;serv = 3;}}
			if(fu==1&&serv==3){if(msg.is_up()){serv = 4;}}
			if(fu==1&&serv==4){if(msg.is_down()){dx3 = msg.x;dy3 = msg.y;serv = 5;}}
			if(serv==5){
				delta[sizenum] = new Delta(dx1,dy1,dx2,dy2,dx3,dy3,de.fill,de.color);
				sizenum++;
				dx1 = dy1 = dx2 = dy2 = dx3 = dy3 = 0;
				fu = 0;
				serv = 1;
			}
			if (msg.x <= 120 && msg.x >= 0 && msg.y <= 120 && msg.y >= 80)
			{//填充
				line(30,115,120,115);
				setfillcolor(BLACK);
	            fillpoly(3,threed);
				setfillcolor(BLACK);
				bar(122,80,212,120);
				outtextxy(135,85,"黑色");
				rectangle(122,120,212,160);
		    	outtextxy(135,125,"白色");
		    	setfillcolor(RED);
		    	bar(122,160,212,200);
		    	outtextxy(135,165,"红色");
		    	setfillcolor(BLUE);
		    	bar(122,200,212,240);
		    	outtextxy(135,205,"蓝色");
		    	setfillcolor(YELLOW);
				bar(122,240,212,280);
				outtextxy(135,245,"黄色");
				rectangle(119,280,213,320);
				outtextxy(121,285,"不填充");
				fillc = 1;
			}
			else
			{
				if(fillc==1){
				    if(msg.x <= 210 && msg.x >= 120 && msg.y <= 320 && msg.y >= 80)
					{
						fu = serv = 0;
						setfillcolor(BLACK);
	                    fillpoly(3,threed);
						setfillcolor(BLACK);
						bar(122,80,212,120);
						outtextxy(135,85,"黑色");
						rectangle(122,120,212,160);
		    			outtextxy(135,125,"白色");
		    			setfillcolor(RED);
		    			bar(122,160,212,200);
		    			outtextxy(135,165,"红色");
		    			setfillcolor(BLUE);
		    			bar(122,200,212,240);
		    			outtextxy(135,205,"蓝色");
		    			setfillcolor(YELLOW);
						bar(122,240,212,280);
						outtextxy(135,245,"黄色");
						rectangle(119,280,213,320);
						outtextxy(121,285,"不填充");
						if(sizenum>=1){
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 120 && msg.y >= 80)
						{
							line(140,113,190,113);
							if(msg.is_left() && msg.is_down()){
								delta[sizenum-1]->colornum = 1;
								delta[sizenum-1]->setDelta(BLACK,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 160 && msg.y >= 120)
						{
							line(140,153,190,153);
							if(msg.is_left() && msg.is_down()){
							    delta[sizenum-1]->colornum = 2;
								delta[sizenum-1]->setDelta(WHITE,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 200 && msg.y >= 160)
						{
							line(140,193,190,193);
							if(msg.is_left() && msg.is_down()){
							    delta[sizenum-1]->colornum = 3;
								delta[sizenum-1]->setDelta(RED,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 240 && msg.y >= 200)
						{
							line(140,233,190,233);
							if(msg.is_left() && msg.is_down()){
							    delta[sizenum-1]->colornum = 4;
								delta[sizenum-1]->setDelta(BLUE,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 280 && msg.y >= 240)
						{
							line(140,273,190,273);
							if(msg.is_left() && msg.is_down()){
							    delta[sizenum-1]->colornum = 5;
								delta[sizenum-1]->setDelta(YELLOW,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 320 && msg.y >= 280)
						{
							line(125,313,205,313);
							if(msg.is_left() && msg.is_down()){
							    delta[sizenum-1]->colornum = 0;
								delta[sizenum-1]->setDelta(BLACK,0);
							}
						}
						}
					}
					else{fillc = 0;fu= 0;serv =1;}
				}
			}
			if (msg.x <= 120 && msg.x >= 0 && msg.y <= 160 && msg.y >= 120)
			{//符号
				line(30,155,120,155);
				setfillcolor(BLACK);
	            fillpoly(3,threedd);
				if(sizenum > 1){
				rectangle(122,120,172,160);
				outtextxy(135,125,"<");
				rectangle(122,160,172,200);
		    	outtextxy(135,165,">");
		    	rectangle(122,200,172,240);
		    	outtextxy(135,205,"<=");
		    	rectangle(122,240,172,280);
		    	outtextxy(135,245,">=");
				rectangle(122,280,172,320);
				outtextxy(135,285,"==");
				rectangle(122,320,172,360);
				outtextxy(135,325,"=");
				rectangle(122,360,172,400);
				outtextxy(130,365,"[]");
				}
				else{
				    rectangle(122,120,332,160);
				    outtextxy(128,125,"画图数量少于2");
				}
				coprt = 1;
			}
			else
			{
				if(coprt==1){
				    if(msg.x <= 210 && msg.x >= 120 && msg.y <= 400 && msg.y >= 120)
					{
						setfillcolor(BLACK);
	                    fillpoly(3,threedd);
						if(sizenum > 1){
						rectangle(122,120,172,160);
						outtextxy(135,125,"<");
						rectangle(122,160,172,200);
		    			outtextxy(135,165,">");
		    			rectangle(122,200,172,240);
		    			outtextxy(135,205,"<=");
		    			rectangle(122,240,172,280);
		    			outtextxy(135,245,">=");
						rectangle(122,280,172,320);
						outtextxy(135,285,"==");
						rectangle(122,320,172,360);
						outtextxy(135,325,"=");
						rectangle(122,360,172,400);
						outtextxy(130,365,"[]");
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 160 && msg.y >= 120)
						{
							line(135,155,155,155);
							rectangle(172,120,252,160);
							if(delta[0]<delta[1]){outtextxy(175,125,"true");}
							else{outtextxy(175,125,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 200 && msg.y >= 160)
						{
							line(135,195,155,195);
							rectangle(172,160,252,200);
							if(delta[0]>delta[1]){outtextxy(175,165,"true");}
							else{outtextxy(175,165,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 240 && msg.y >= 200)
						{
							line(135,235,155,235);
							rectangle(172,200,252,240);
							if(delta[0]<=delta[1]){outtextxy(175,205,"true");}
							else{outtextxy(175,205,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 280 && msg.y >= 240)
						{
							line(135,275,155,275);
							rectangle(172,240,252,280);
							if(delta[0]>=delta[1]){outtextxy(175,245,"true");}
							else{outtextxy(175,245,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 320 && msg.y >= 280)
						{
							line(135,315,155,315);
							rectangle(172,280,252,320);
							if(delta[0]==delta[1]){outtextxy(175,285,"true");}
							else{outtextxy(175,285,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 360 && msg.y >= 320)
						{
							line(135,355,155,355);
							if(msg.is_left() && msg.is_down()){
							rectangle(172,320,252,360);
							delta[1] = delta[0];
							}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 400 && msg.y >= 360)
						{
							Point dexy;
							int dexyx1,dexyy1,dexyx2,dexyy2,dexyx3,dexyy3;
							dexy = (*delta[0])[0];
							dexyx1=dexy.x;
							dexyy1=dexy.y;
							dexy = (*delta[0])[1];
							dexyx2=dexy.x;
							dexyy2=dexy.y;
							dexy = (*delta[0])[2];
							dexyx3=dexy.x;
							dexyy3=dexy.y;
							line(135,395,155,395);
							rectangle(172,360,362,400);
							rectangle(172,400,362,440);
							rectangle(172,440,362,480);
							xyprintf(175,365,"[0](%3d,%3d)",dexyx1,dexyy1);
							xyprintf(175,405,"[1](%3d,%3d)",dexyx2,dexyy2);
							xyprintf(175,445,"[2](%3d,%3d)",dexyx3,dexyy3);
						}

					    }
						else{
							rectangle(122,120,332,160);
						    outtextxy(128,125,"画图数量少于2");
						}
					}
					else{coprt = 0;}
				}
			}
			if (msg.x <= 180 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40)
			{//清屏
				line(30,75,180,75);
				if(msg.is_left() && msg.is_down())
				{
				    dx1 = dy1 = dx2 = dy2 = dx3 = dy3 = 0;
				    fu = 0;
				    serv = 1;
					sizenum = 0;
				}
			}
			if(serv == 1 || serv == 2 || serv == 3 || serv == 4 || serv == 5){//退出
			    if(msg.x <= 120 && msg.x >= 0 && msg.y <= 200 && msg.y >= 160 )
			    {
				    line(30,195,120,195);
				    if(msg.is_left() && msg.is_down()){
					menu = 1;
					serv = 0;
					ser = 1;
					write.open("message2.txt",ios::app);
					if(sizenum >= 1){
						for(int dsize = 0;dsize < sizenum;dsize++){
							write<<"2"<<endl;
							write<<delta[dsize]->getP1x()<<" "<<delta[dsize]->getP1y()<<" "<<delta[dsize]->getP2x()<<" "<<delta[dsize]->getP2y()<<" "<<delta[dsize]->getP3x()<<" "<<delta[dsize]->getP3y()<<endl;
							write<<delta[dsize]->colornum<<" "<<delta[dsize]->getFill()<<endl;
						}
					}
					write.close();
					dx1 = dy1 = dx2 = dy2 = dx3 = dy3 = 0;
					for(int deletedelta = 0;deletedelta < sizenum;deletedelta++){
					    delete delta[deletedelta];
					}
					sizenum = 0;
				    }
			     }
			}
		}
		if(menu==11){//自定义画矩形
			outtextxy(0,0,"按住鼠标左键,拖动鼠标得出图形(出错请选“1”重置)");
			outtextxy(0,40,"1、清屏重画");
			outtextxy(0,80,"2、填充");
			outtextxy(0,120,"3、符号");
			outtextxy(0,160,"4、退出");
			drawpoly(4,threed);
			drawpoly(4,threedd);
			if(sizenum >= 1){
				for(int sizesqu = 0;sizesqu < sizenum;sizesqu++){
				    draw(squb[sizesqu]);
				}
			}
			if(fu==0&&serv==0){if(msg.is_left()  && msg.is_up()){serv = 1;}}
			if(fu==0&&serv==1){if(msg.is_down()){sx1 = msg.x;sy1 = msg.y;fu = 1;}}
			if(fu==1){
			    cleardevice();
				if(sizenum >= 1){
				    for(int sizesqua = 0;sizesqua < sizenum;sizesqua++){
				    draw(squb[sizesqua]);
				    }
			    }
			    mousepos(&sxs,&sys);
				sx2 = sxs;
				sy2 = sys;
				Squareness *squa = new Squareness(sx1,sy1,sx2,sy2,0,BLACK);
				draw(squa);
				delete squa;
				outtextxy(0,0,"按住鼠标左键,拖动鼠标得出图形(出错请选“1”重置)");
			    outtextxy(0,40,"1、清屏重画");
			    outtextxy(0,80,"2、填充");
			    outtextxy(0,120,"3、符号");
			    outtextxy(0,160,"4、退出");
			    drawpoly(4,threed);
			    drawpoly(4,threedd);
				if(msg.is_up()){
					fu=0;
					serv=2;
					squb[sizenum] = new Squareness(sx1,sy1,sx2,sy2,sq.fill,sq.color);
					sizenum++;
				}
			}
			
			if(serv==2){fu = 0;serv = 1;}
			if (msg.x <= 120 && msg.x >= 0 && msg.y <= 120 && msg.y >= 80)
			{//填充
				line(30,115,120,115);
				setfillcolor(BLACK);
	            fillpoly(3,threed);
				setfillcolor(BLACK);
				bar(122,80,212,120);
				outtextxy(135,85,"黑色");
				rectangle(122,120,212,160);
		    	outtextxy(135,125,"白色");
		    	setfillcolor(RED);
		    	bar(122,160,212,200);
		    	outtextxy(135,165,"红色");
		    	setfillcolor(BLUE);
		    	bar(122,200,212,240);
		    	outtextxy(135,205,"蓝色");
		    	setfillcolor(YELLOW);
				bar(122,240,212,280);
				outtextxy(135,245,"黄色");
				rectangle(119,280,213,320);
				outtextxy(121,285,"不填充");
				fillc = 1;
			}
			else
			{
				if(fillc==1){
				    if(msg.x <= 210 && msg.x >= 120 && msg.y <= 320 && msg.y >= 80)
					{
						fu = serv = 0;
						setfillcolor(BLACK);
	                    fillpoly(3,threed);
						setfillcolor(BLACK);
						bar(122,80,212,120);
						outtextxy(135,85,"黑色");
						rectangle(122,120,212,160);
		    			outtextxy(135,125,"白色");
		    			setfillcolor(RED);
		    			bar(122,160,212,200);
		    			outtextxy(135,165,"红色");
		    			setfillcolor(BLUE);
		    			bar(122,200,212,240);
		    			outtextxy(135,205,"蓝色");
		    			setfillcolor(YELLOW);
						bar(122,240,212,280);
						outtextxy(135,245,"黄色");
						rectangle(119,280,213,320);
						outtextxy(121,285,"不填充");
						if(sizenum >=1){
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 120 && msg.y >= 80)
						{
							line(140,113,190,113);
							if(msg.is_left() && msg.is_down()){
								squb[sizenum-1]->colornum = 1;
								squb[sizenum-1]->setSquareness(BLACK,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 160 && msg.y >= 120)
						{
							line(140,153,190,153);
							if(msg.is_left() && msg.is_down()){
								squb[sizenum-1]->colornum = 2;
								squb[sizenum-1]->setSquareness(WHITE,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 200 && msg.y >= 160)
						{
							line(140,193,190,193);
							if(msg.is_left() && msg.is_down()){
								squb[sizenum-1]->colornum = 3;
								squb[sizenum-1]->setSquareness(RED,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 240 && msg.y >= 200)
						{
							line(140,233,190,233);
							if(msg.is_left() && msg.is_down()){
								squb[sizenum-1]->colornum = 4;
								squb[sizenum-1]->setSquareness(BLUE,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 280 && msg.y >= 240)
						{
							line(140,273,190,273);
							if(msg.is_left() && msg.is_down()){
								squb[sizenum-1]->colornum = 5;
								squb[sizenum-1]->setSquareness(YELLOW,1);
							}
						}
						if (msg.x <= 212 && msg.x >= 122 && msg.y <= 320 && msg.y >= 280)
						{
							line(125,313,205,313);
							if(msg.is_left() && msg.is_down()){
								squb[sizenum-1]->colornum = 0;
								squb[sizenum-1]->setSquareness(BLACK,0);
							}
						}
						}
					}
					else{fillc = 0;fu = 0;serv = 1;}
				}
			}
			if (msg.x <= 120 && msg.x >= 0 && msg.y <= 160 && msg.y >= 120)
			{//符号
				line(30,155,120,155);
				setfillcolor(BLACK);
	            fillpoly(3,threedd);
				if(sizenum > 1){
				rectangle(122,120,172,160);
				outtextxy(135,125,"<");
				rectangle(122,160,172,200);
		    	outtextxy(135,165,">");
		    	rectangle(122,200,172,240);
		    	outtextxy(135,205,"<=");
		    	rectangle(122,240,172,280);
		    	outtextxy(135,245,">=");
				rectangle(122,280,172,320);
				outtextxy(135,285,"==");
				rectangle(122,320,172,360);
				outtextxy(135,325,"=");
				rectangle(122,360,172,400);
				outtextxy(130,365,"[]");
				}
				else{
				    rectangle(122,120,332,160);
				    outtextxy(128,125,"画图数量少于2");
				}
				coprt = 1;
			}
			else
			{
				if(coprt==1){
				    if(msg.x <= 210 && msg.x >= 120 && msg.y <= 400 && msg.y >= 120)
					{
						setfillcolor(BLACK);
	                    fillpoly(3,threedd);
						if(sizenum > 1){
						rectangle(122,120,172,160);
						outtextxy(135,125,"<");
						rectangle(122,160,172,200);
		    			outtextxy(135,165,">");
		    			rectangle(122,200,172,240);
		    			outtextxy(135,205,"<=");
		    			rectangle(122,240,172,280);
		    			outtextxy(135,245,">=");
						rectangle(122,280,172,320);
						outtextxy(135,285,"==");
						rectangle(122,320,172,360);
						outtextxy(135,325,"=");
						rectangle(122,360,172,400);
						outtextxy(130,365,"[]");
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 160 && msg.y >= 120)
						{
							line(135,155,155,155);
							rectangle(172,120,252,160);
							if(squb[0]<squb[1]){outtextxy(175,125,"true");}
							else{outtextxy(175,125,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 200 && msg.y >= 160)
						{
							line(135,195,155,195);
							rectangle(172,160,252,200);
							if(squb[0]>squb[1]){outtextxy(175,165,"true");}
							else{outtextxy(175,165,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 240 && msg.y >= 200)
						{
							line(135,235,155,235);
							rectangle(172,200,252,240);
							if(squb[0]<=squb[1]){outtextxy(175,205,"true");}
							else{outtextxy(175,205,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 280 && msg.y >= 240)
						{
							line(135,275,155,275);
							rectangle(172,240,252,280);
							if(squb[0]>=squb[1]){outtextxy(175,245,"true");}
							else{outtextxy(175,245,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 320 && msg.y >= 280)
						{
							line(135,315,155,315);
							rectangle(172,280,252,320);
							if(squb[0]==squb[1]){outtextxy(175,285,"true");}
							else{outtextxy(175,285,"false");}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 360 && msg.y >= 320)
						{
							line(135,355,155,355);
							if(msg.is_left() && msg.is_down()){
							rectangle(172,320,252,360);
							squb[1] = squb[0];
							}
						}
						if (msg.x <= 172 && msg.x >= 122 && msg.y <= 400 && msg.y >= 360)
						{
							Point sqxy;
							int sqxyx1,sqxyy1,sqxyx2,sqxyy2;
							sqxy = (*squb[0])[0];
							sqxyx1 = sqxy.x;
							sqxyy1 = sqxy.y;
							sqxy = (*squb[0])[1];
							sqxyx2 = sqxy.x;
							sqxyy2 = sqxy.y;
							line(135,395,155,395);
							rectangle(172,360,362,400);
							rectangle(172,400,362,440);
							xyprintf(175,365,"[0](%3d,%3d)",sqxyx1,sqxyy1);
							xyprintf(175,405,"[1](%3d,%3d)",sqxyx2,sqxyy2);
						}

					    }
						else{
							rectangle(122,120,332,160);
						    outtextxy(128,125,"画图数量少于2");
						}
					}
					else{coprt = 0;}
				}
			}
			if (msg.x <= 180 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40)
			{//清屏
				line(30,75,180,75);
				if(msg.is_left() && msg.is_down()){
				    fu = 0;
				    serv = 1;
					sizenum = 0;
				}
			}
			if(serv == 1 || serv == 2){//退出
			    if(msg.x <= 120 && msg.x >= 0 && msg.y <= 200 && msg.y >= 160 )
			    {
				    line(30,195,120,195);
				    if(msg.is_left() && msg.is_down()){
					menu=1;
					serv=0;
					ser=1;
					write.open("message2.txt",ios::app);
					if(sizenum >= 1){
						for(int ssize = 0;ssize < sizenum;ssize++){
						    write<<"3"<<endl;
							write<<squb[ssize]->getTopleftx()<<" "<<squb[ssize]->getToplefty()<<" "<<squb[ssize]->getRightdownx()<<" "<<squb[ssize]->getRightdowny()<<endl;
							write<<squb[ssize]->colornum<<" "<<squb[ssize]->getSqufill()<<endl;
						}
					}
					write.close();
					sx1 = sx2 = sy1 = sy2 = 0;
					for(int deletesqub = 0;deletesqub < sizenum;deletesqub++){
					    delete squb[deletesqub];
					}
					sizenum = 0;
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