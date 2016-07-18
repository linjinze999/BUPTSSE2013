#include"MergeSort.h"

void Merge(SqList &L1,SqList &L2,int i,int m,int n,int &compare,int &move){
	/* 将有序的SR[i..m]和SR[m+1..n]归并为有序的TR[i..n]  */
	int j,k,l;
	for(j=m+1,k=i;i<=m&&j<=n;++k) {
		compare++;
		if (L1.R[i].key<L1.R[j].key){
			L2.R[k]=L1.R[i++];
			move++;
		}
		else{
			move++;
			L2.R[k]=L1.R[j++];
		}
	}
	if(i<=m)
		for(l=0;l<=m-i;l++){
			move++;
			L2.R[k+l]=L1.R[i+l];
		}
		if(j<=n)
			for(l=0;l<=n-j;l++){
				move++;
				L2.R[k+l]=L1.R[j+l];
			}
};

void MSort(SqList &L1,SqList &L2,int s, int t,int &compare,int &move){
	/* 将SR[s..t]归并排序为TR1[s..t] */
	int m;
	SqList TR2={0};
	if(s==t){
		move++;
		L2.R[s]=L1.R[s];
	}
	else{
		m=(s+t)/2; /* 将SR[s..t]平分为SR[s..m]和SR[m+1..t] */
		MSort(L1,TR2,s,m,compare,move); /* 递归地将SR[s..m]归并为有序的TR2[s..m] */
		MSort(L1,TR2,m+1,t,compare,move); /* 递归地将SR[m+1..t]归并为有序的TR2[m+1..t] */
		Merge(TR2,L2,s,m,t,compare,move); /* 将TR2[s..m]和TR2[m+1..t]归并到TR1[s..t] */
	}
};

void MergeSort(SqList &L,int &compare,int &move){
	//归并排序
	compare=move=0;
	MSort(L,L,1,L.length,compare,move);
};