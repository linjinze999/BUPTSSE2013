#include<iostream>
#include <graphics.h>
using namespace std;
int drawany(){
	bool MouseDown = 0;
	cleardevice();
	int worda = 0 ,wordb = 0;
	for ( ; is_run(); delay_fps(5000))
	{
		mouse_msg msg = {0};
		mousepos(&msg.x,&msg.y);
		while (mousemsg())
	    {
		     msg = getmouse();
		}
		setfont(30,15,"楷体");
		outtextxy(0,0,"按住鼠标左键作画:");
		if(worda == 0){
			setfont(30,15,"楷体");
			outtextxy(0,40,"1、清屏");
		}
		if(worda == 1){
			setfont(30,15,"迷你简硬笔行书");
			outtextxy(0,40,"1、清屏");
		}
		if(wordb == 0){
			setfont(30,15,"楷体");
			outtextxy(0,80,"2、退出");
		}
		if(wordb == 1){
			setfont(30,15,"迷你简硬笔行书");
			outtextxy(0,80,"2、退出");
		}
		if(msg.is_down()) MouseDown = true;
		if(msg.is_up()) MouseDown = false;
		if(MouseDown) putpixel(msg.x, msg.y,BLACK);
		if (msg.x <= 120 && msg.x >= 0 && msg.y <= 80 && msg.y >= 40)
		{
			worda = 1;
			 if(msg.is_left() && msg.is_down())
			 {
				 cleardevice ();
			 }
		}
		else{worda = 0;}
		if (msg.x <= 120 && msg.x >= 0 && msg.y <= 120 && msg.y >= 80)
		{
			wordb = 1;
			 if(msg.is_left() && msg.is_down())
			 {
				break;
			 }
		}
		else{wordb = 0;}
	}
	return 1;
}