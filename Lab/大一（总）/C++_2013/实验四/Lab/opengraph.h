#pragma once
#include<iostream>
#include <graphics.h>
#include <stdio.h>
using namespace std;

class Opengraph{
public:
	Opengraph(Opengraph &);
	Opengraph(){
	initgraph(900,800);
	int x1 = 330,x2=430,x3=330,x4=430,a=0;
	setcolor(EGERGB(0, 0xFF, 0));
	setfillcolor(EGERGB(0, 0, 0xFF));
	for ( ; is_run(); delay_fps(60) )
	{
		x1 = x1 + 8;
		x2 = x2 - 8;
		x3 = x3 + 8;
		x4 = x4 - 8;
		cleardevice();
		setbkcolor(WHITE);
	    setfont(50,25,"楷体");
	    setcolor(BLACK);
	    outtextxy(0,0,"欢迎进入");
	    setcolor(RED);
	    setfont(100,50,"楷体");
	    outtextxy(x1,150,"简");
	    setcolor(BLUE);
	    outtextxy(x2,250,"易");
	    setcolor(GREEN);
	    outtextxy(x3,350,"画");
	    setcolor(MAGENTA);
	    outtextxy(x4,450,"板");
	    setfont(40,20,"迷你简硬笔行书");
	    setcolor(BLACK);
	    outtextxy(610,660,"制作人：林锦泽");
		if(a==0){delay_ms(2000);a++;}
		if(x1>=850){break;}
	    }
	    cleardevice();
		
	     }
	static int getNumber(){return number;}
private:
	static int number;
    };