#include"MapColor.h"
#include<iostream>
#include<vector>
#include<time.h>
bool MapColor(int number,int *map,int *result,int count,int color,int max_color)//回溯法递归生成着色图
{
	for(int n=0;n<number;n++)//匹配颜色
	{
		if(map[count*number+n]==1)
			if(color==result[n]){
				color++;
				n=-1;
			}
	}
	if(color>max_color)//颜色值应当小于等于最大颜色数
		return false;
	else{
		result[count]=color;
		if(count>=number)//叶节点
			return true;
		if(MapColor(number,map,result,count+1,1,max_color))//下一节点成功
			return true;
		else
			return MapColor(number,map,result,count,color+1,max_color);//下一节点失败，回溯
	}
};


void setMap(int count,int *map)//随机生成无向图
{
	srand((int)time(0));
	int r=rand()%100;
	for(int i=0;i<count;i++)
		for(int n=0;n<count;n++){
			if(n==i)
				map[i*count+n]=0;
			else if(n>i){
				map[i*count+n]=r%2;
				map[n*count+i]=r%2;
				r=((r+1)*157)%100;
			}
		}
};