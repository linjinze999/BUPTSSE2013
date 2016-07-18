#include"mapcolor.h"
#include<iostream>
#include<vector>
#include<time.h>
bool MapColor(int number,int *map,int *result,int count,int color,int max_color)//着色图
{
	bool can=false;
	for(int n=0;n<number;n++)
		result[n]=-1;
	for(int i=0;i<max_color;i++){//遍历所有颜色
		for(int j=0;j<number;j++){//遍历所有顶点
			if(result[j]==-1){//顶点未着色
				can=true;
				for(int n=0;n<number;n++)//检查是否冲突
					if(map[j*number+n]==1){
						if(result[n]==i){
							can=false;
							break;
						}
					}
				if(can)
					result[j]=i;
			}
		}
	}
	for(int n=0;n<number;n++)
		if(result[n]==-1)
			return false;
	return true;
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