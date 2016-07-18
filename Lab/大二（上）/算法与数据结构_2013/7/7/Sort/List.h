#pragma once
#include<iostream>
using namespace std;

typedef  struct {
	int  key;        //表示排序关键字
	int   otherinfo; //排序记录中的其他数据项
}  Snode;  

typedef  struct { 
	Snode R[101]; //存放待排序全体记录
	int  length;  //排序记录个数
} SqList; 

void setList(SqList &L,int &num);

void Print(SqList L);
