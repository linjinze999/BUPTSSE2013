#include <iostream>
#include <time.h>
#include<stdlib.h>
using namespace std;  
#define KB 256
#define STEP 64*1024*1024
void main(){
	clock_t begin,end;
	long data=0;
	long *arrays;
	int ArraySize=0;
	for(int i=0;i<15;i++){//difference array size(equal to cache size)------>will get difference time
		ArraySize=KB*pow((double)2,i);//cache size
		arrays=(long*)(malloc(sizeof(long)*ArraySize));//create array
		for(int a=0;a<(KB*pow((double)2,i));a++)//access
			arrays[a]=0;
		begin=clock();//start time
		for(int a=0;a<STEP;a++)//get every cache line 
			data=arrays[a*16%ArraySize];
		end=clock();//end time
		free(arrays);//free array
		cout<<ArraySize/KB<<"KB running time:"<<end-begin<<endl;
	}
	cin.get();
}