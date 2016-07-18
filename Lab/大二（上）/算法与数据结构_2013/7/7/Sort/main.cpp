#include<iostream>
#include"BInsertSort.h"
#include"BubbleSort.h"
#include"HeapSort.h"
#include"InsertSort.h"
#include"MergeSort.h"
#include"QuitSort.h"
#include"SelectSort.h"
#include"ShellSort.h"
#include"List.h"
using namespace std;

void main(){
	SqList L1,L2;
	int data=12345;
	setList(L1,data);
	L2=L1;
	int choose;
	int compare,move;
	compare=move=0;
	while(true){
		cout<<"*********************************"<<endl;
		cout<<"*\t1、打印原始数据；\t*"<<endl;
		cout<<"*\t2、八种排序算法比较；\t*"<<endl;
		cout<<"*\t3、重新生成数据；\t*"<<endl;
		cout<<"*********************************"<<endl;
		cout<<"请选择：";
		cin>>choose;
		switch(choose){
		case 1:
			Print(L1);
			cout<<endl<<endl;
			break;
		case 2:
			cout<<"1、直接插入排序："<<endl;
			InsertSort(L1,compare,move);
			Print(L1);
			cout<<endl<<"比较次数："<<compare<<endl<<"移动次数："<<move<<endl<<endl;
			L1=L2;
			compare=move=0;

			cout<<"2、折半插入排序："<<endl;
			BInsertSort(L1,compare,move);
			Print(L1);
			cout<<endl<<"比较次数："<<compare<<endl<<"移动次数："<<move<<endl<<endl;
			L1=L2;
			compare=move=0;

			cout<<"3、希尔排序："<<endl;
			int a[1];
			a[0]=5;
			ShellSort(L1,a,1,compare,move);
			Print(L1);
			cout<<endl<<"比较次数："<<compare<<endl<<"移动次数："<<move<<endl<<endl;
			L1=L2;
			compare=move=0;

			cout<<"4、起泡排序："<<endl;
			bubble_sort(L1,compare,move);
			Print(L1);
			cout<<endl<<"比较次数："<<compare<<endl<<"移动次数："<<move<<endl<<endl;
			L1=L2;
			compare=move=0;

			cout<<"5、快速排序："<<endl;
			QSort(L1,1,L1.length,compare,move);
			Print(L1);
			cout<<endl<<"比较次数："<<compare<<endl<<"移动次数："<<move<<endl<<endl;
			L1=L2;
			compare=move=0;

			cout<<"6、简单选择排序："<<endl;
			SelectSort(L1,compare,move);
			Print(L1);
			cout<<endl<<"比较次数："<<compare<<endl<<"移动次数："<<move<<endl<<endl;
			L1=L2;
			compare=move=0;

			cout<<"7、堆排序："<<endl;
			HeapSort(L1,compare,move);
			Print(L1);
			cout<<endl<<"比较次数："<<compare<<endl<<"移动次数："<<move<<endl<<endl;
			L1=L2;
			compare=move=0;

			cout<<"8、归并排序："<<endl;
			MergeSort(L1,compare,move);
			Print(L1);
			cout<<endl<<"比较次数："<<compare<<endl<<"移动次数："<<move<<endl<<endl;
			L1=L2;
			compare=move=0;

			break;
		case 3:
			setList(L1,data);
			L2=L1;
			cout<<"生成成功！"<<"新数据如下："<<endl;
			Print(L1);
			cout<<endl<<endl;
			break;
		default:
			break;
		}
	}
}



  