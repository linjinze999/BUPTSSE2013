#include"QuitSort.h"

int Partition (SqList &L, int low, int high,int &compare,int &move) {
	L.R[0] = L.R[low]; 
	move++;
	int pivotkey = L.R[low].key;
	while (low<high) {
		compare++;
		while(low<high && L.R[high].key>=pivotkey){
			-- high; 
			compare++;
		}
		L.R[low] = L.R[high];
		move++;
		while (low<high && L.R[low].key<=pivotkey){ 
			++ low;
			compare++;
		}
		L.R[high] = L.R[low];
		move++;
	}
	L.R[low] = L.R[0];
	move++;
	return low;
};   

void QSort (SqList &L,  int s,  int  t ,int &compare,int &move) {// ¿ìËÙÅÅÐò
	if (s < t) {
		int pivotloc = Partition(L, s, t, compare, move);
		QSort(L, s, pivotloc-1, compare, move);
		QSort(L, pivotloc+1, t, compare, move);
	}
};