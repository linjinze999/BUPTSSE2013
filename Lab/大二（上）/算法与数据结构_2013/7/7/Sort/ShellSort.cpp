#include"ShellSort.h"


void ShellSort(SqList &L,int dlta[],int t,int &compare,int &move){// Ï£¶ûÅÅĞò
	int k;
	compare=move=0;
	for(k=0;k<t;++k)
	{
		ShellInsert(L,dlta[k],compare,move); 
	}
};

void ShellInsert(SqList &L,int dk,int &compare,int &move){ // Ï£¶û²åÈëÅÅĞò
	int i,j;
	for(i=dk+1;i<=L.length;++i){
		compare++;
		if (L.R[i].key<L.R[i-dk].key){
			L.R[0]=L.R[i];
			move++;
			for(j=i-dk;j>0&&(L.R[0].key<L.R[j].key);j-=dk){
				L.R[j+dk]=L.R[j];
				compare++;
				move++;
			}
			L.R[j+dk]=L.R[0];
			move++;
		}
	}
};
