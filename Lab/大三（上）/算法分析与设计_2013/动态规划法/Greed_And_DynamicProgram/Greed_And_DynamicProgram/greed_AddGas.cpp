#include<iostream>
#include<time.h>
#include"greed_AddGas.h"
bool AddGasResult(int max_distance,int station_number,int *distance,int *result_station,int& result_count)//汽车加油
{
	for(int i=0;i<station_number;i++)
		if(max_distance<distance[i])
			return false;
	result_count = 0;
	for(int i=0;i<station_number;i++)
		result_station[i]=0;

	int temp = max_distance;
	for(int i=0;i<station_number-1;i++)
	{
		if(temp >= distance[i+1])
			temp -= distance[i+1];
		else
		{
			temp = max_distance;
			result_station[i]=1;
			result_count++;
		}
	}
	return true;
}

void setDistance(int *distance,int station_number,int max_distance)//随机生成路程
{
	srand((int)time(0));
	int r=rand()%max_distance;
	distance[0] = 0;
	for(int i=1;i<station_number;i++)
		{
			if(r==0)
				r=1;
			distance[i]=r;
			r=(r*157)%max_distance;
		}
};