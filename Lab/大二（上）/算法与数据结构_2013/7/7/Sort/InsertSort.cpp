#include"InsertSort.h"

void InsertSort(SqList &L,int &compare,int &move){//÷±Ω”≤Â»Î≈≈–Ú
	int i,j;
	compare=move=0;
	for(i=2;i<=L.length;++i){
		compare++;
		if(L.R[i].key < L.R[i-1].key)  {
			L.R[0] =L.R[i]; 
			move++;
			for(j=i-1;L.R[0].key < L.R[j].key;--j){
				L.R[j+1]=L.R[j];
				move++;
				compare++;
			}
			L.R[j+1]=L.R[0];
			move++;
		}
	}
};