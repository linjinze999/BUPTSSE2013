#include<iostream>
#include<time.h>
using namespace std;

void Merge(int *a, int p, int q, int r) //将排好序的[p,q],[q,r]归并在一起
{
    int n1 = q-p+1;
    int n2 = r-q;
    int *L = new int[n1];
    int *R = new int[n2];
    int i, j, k;
    
    for (i=0; i<n1; i++){
        L[i] = a[p+i];
    }
    for (j=0; j<n2; j++){
        R[j] = a[q+j+1];
    }

    for (i=0, j=0, k=p; k<=r; k++)
    {
        if (L[i]>=R[j])
        {
            a[k] = L[i];
            i++;
        }else{
            a[k] = R[j];
            j++;
        }
    }
  
    delete []L;
    delete []R;  
};
  
void MergeSort(int *a, int p, int r)//归并排序
{  
    if (p<r)
    {
        int q = (p+r)/2;
        MergeSort(a, p, q);
        MergeSort(a, q+1, r);
        Merge(a, p, q, r);
    }  
};


void ElementSelect(int data[],int data2[],int data_length,int rank, int *result_value, int *result_no)//元素选择
{
	for(int i=0;i<data_length;i++){
		data2[i] = data[i];
	}
	MergeSort(data2,0,data_length-1);
	(*result_value)=data2[rank-1];
	for(int j=0;j<data_length;j++)
		if(data2[rank-1]==data[j])
		{
			(*result_no)=j+1;
			break;
		}
};


void setData(int data[],int data_length)//随机生成数据
{
	srand((int)time(0));
	int r=rand()%100;
	for(int i=0;i<data_length;i++)
		{
			if(r==0)
				r=1;
			data[i]=r;
			r=(r*163)%100;
		}
};