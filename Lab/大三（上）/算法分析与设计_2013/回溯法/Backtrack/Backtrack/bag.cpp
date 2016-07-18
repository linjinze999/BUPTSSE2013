#include"bag.h"
#include<time.h>
#include<stdlib.h>
void getVV(int *AllBag[],int value[],int vector[],int no,int goods[],int GoodsLength)//记录每一种情况
{
	int myvalue=0;
	int myvector=0;
	for(int i=0;i<GoodsLength;i++)
	if(goods[i]>0)
	{
		myvalue+=value[i];
		myvector+=vector[i];

	}
	AllBag[no][0]=myvector;
	AllBag[no][1]=myvalue;
	for(int n=0;n<GoodsLength;n++)
		AllBag[no][n+2]=goods[n];
};

void result(int *AllBag[],int bag[],int no,int MaxVector,int number)//比较得出结果
{
	int maxvalue=0;
	for(int i=0;i<no;i++)
		if(AllBag[i][0]<=MaxVector && AllBag[i][1]>maxvalue)
		{
			for(int n=0;n<number+2;n++)
				bag[n]=AllBag[i][n];
			maxvalue=bag[1];
		}
			
};

void setVV(int *value,int *vector,int number,int max_vector)//随机生成价值和体积
{
	srand((int)time(0));
	int r=rand()%100;
	for(int i=0;i<number;i++)
		{
			value[i]=r;
			r=((r+1)*157)%max_vector;
			vector[i]=r;
			r=((r+1)*161)%97;
		}
};
