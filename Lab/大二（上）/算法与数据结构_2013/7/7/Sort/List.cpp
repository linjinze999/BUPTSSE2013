#include"List.h"

void setList(SqList &L,int &num){//Î±Ëæ»ú
	L.length=100;
	for(int n=0;n<=100;n++){
		L.R[n].otherinfo=0;
		L.R[n].key=num%344;
		num=513*num%343;
	}
};

void Print(SqList L){
	for(int n=1;n<L.length;n++){
		cout<<L.R[n].key<<" ";
	}
};