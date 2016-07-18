#include"marry.h"
#include<stdlib.h>
#include<time.h>
int isWomenAgree(int WhichMan,int WhichWoman,int *WomenMark[],int MenMarry[],int WomenMarry[])//匹配是否成功
{
	if(WomenMarry[WhichWoman]==-1)//女生无对象
	{
		WomenMarry[WhichWoman]=WhichMan;
		MenMarry[WhichMan]=WhichWoman;
		return -1;
	}
	else
	{
		if(WomenMark[WhichWoman][WomenMarry[WhichWoman]]>=WomenMark[WhichWoman][WhichMan])//评分低于该女生现有对象
			return -2;
		else//评分高于该女生现有对象
		{
			int lost=WomenMarry[WhichWoman];
			WomenMarry[WhichWoman]=WhichMan;
			MenMarry[WhichMan]=WhichWoman;
			MenMarry[lost]=-1;
			return lost;//返回被淘汰的人编号
		}
	}

};

int whoIsManWant(int *MenMark[],int rank,int population,int WhichMan)//获得该男子求婚对象
{
	int *MarkSort = new int[population];
	int *WomenSort = new int[population];
	for(int i=0;i<population;i++)
		MarkSort[i]=MenMark[WhichMan][i];
	for(int n=0;n<population;n++)
	{
		int max=0;
		int count;
		for(int m=n;m<population;m++)
			if(MarkSort[m]>max)
				max=MarkSort[m];
		for(int m=n;m<population;m++)
			if(MarkSort[m]==max)
			{
				WomenSort[n]=m;
				int temp=MarkSort[n];
				MarkSort[n]=MarkSort[m];
				MarkSort[m]=temp;
				break;
			}		
	}
	return WomenSort[rank];
};

void ManSearch(int *MenMark[],int *WomenMark[],int population,int MenMarry[],int WomenMarry[],int WhichMan,int ManRank[])//开始求婚
{
	int WhichWoman=whoIsManWant(MenMark,ManRank[WhichMan],population,WhichMan);
	int result=isWomenAgree(WhichMan,WhichWoman,WomenMark,MenMarry,WomenMarry);
	if(result==-2)//匹配失败，继续匹配
	{
		ManRank[WhichMan]++;
		ManSearch(MenMark,WomenMark,population,MenMarry,WomenMarry,WhichMan,ManRank);
	}
	else if(result==-1)//匹配成功，且该女生原本无对象
	{
		return;
	}
	else//匹配成功，该女生原本有对象，为淘汰的男生继续匹配
	{
		ManSearch(MenMark,WomenMark,population,MenMarry,WomenMarry,result,ManRank);
	}
};

void setMark(int *MenMark[],int *WomenMark[],int population)//随机生成好感度
{
	srand((int)time(0));
	int r=rand()%100;
	for(int i=0;i<population;i++)
		for(int n=0;n<population;n++)
		{
			MenMark[i][n]=r;
			r=(r*157)%100;
			WomenMark[i][n]=r;
			r=(r*127)%100;
		}
};

void marry(int *MenMark[],int *WomenMark[],int population,int MenMarry[],int WomenMarry[])//匹配结婚
{
	setMark(MenMark,WomenMark,population);
	int *ManRank = new int[population];
	for(int i=0;i<population;i++)
		ManRank[i]=0;
	for(int i=0;i<population;i++)
		ManSearch(MenMark,WomenMark,population,MenMarry,WomenMarry,i,ManRank);
	
}