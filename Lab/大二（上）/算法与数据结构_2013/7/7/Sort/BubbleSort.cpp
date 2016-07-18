#include"BubbleSort.h"

void bubble_sort(SqList &L,int &compare,int &move){ // (ÆðÅÝÅÅÐò)
	int i,j;
	Snode t;
	bool change;
	compare=move=0;
	for(i=L.length-1,change=true;i>1&&change;--i)
	{
		change=false;
		for(j=0;j<i;++j){
			compare++;
			if(L.R[j].key>L.R[j+1].key)
			{
				t=L.R[j];
				L.R[j]=L.R[j+1];
				L.R[j+1]=t;
				move+=3;
				change=true;
			}
		}
	}
};