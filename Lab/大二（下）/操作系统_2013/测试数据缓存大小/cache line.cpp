#include <iostream>
#include <time.h>
#include <math.h>
using namespace std;  
  
#define MAX 64 * 1024 * 1024  

inline int getLoopTimeMs(int Stride)  //return running time
{  
    if(Stride<=0)  
        Stride=1;  
    long* pData = new long[MAX];  //create array
    memset(pData,0,MAX*sizeof(long));  
    clock_t start,finish;
    start=clock();//start time
    for (int i = 0; i < MAX; i+=Stride)  //access
        pData[i]=1;
    finish=clock();//end time
    int time =(int)(finish-start) ;//running time
    delete[] pData;  //delete array
    return time;
}  
  

int main()  
{
	int time[10]={0};
	for(int n=0;n<10;n++){//difference Stride
		time[n] = getLoopTimeMs(pow((double)2,n));
		cout<<"Stride:"<<pow((double)2,n)<<",Time:"<<time[n]<<endl;
	}
	cin.get();
	return 0;  
}