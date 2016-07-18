#include"BInsertSort.h"

void BInsertSort(SqList &L,int &compare,int &move){
	// ’€∞Î≤Â»Î≈≈–Ú
	int i,j,m,low,high;
	compare=move=0;
	for(i=2;i<=L.length;++i)
	{
		L.R[0]=L.R[i];
		move++;
		low=1;
		high=i-1;
		while(low<=high) { 
			m=(low+high)/2; 
			compare++;
			if (L.R[0].key<L.R[m].key)
				high=m-1; 
			else
				low=m+1; 
		}
		for(j=i-1;j>=high+1;--j){
			L.R[j+1]=L.R[j]; 
			move++;
		}
		L.R[high+1]=L.R[0];
		move++;
	}
};