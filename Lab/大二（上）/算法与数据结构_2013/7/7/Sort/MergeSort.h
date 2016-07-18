#pragma once
#include<iostream>
#include"List.h"
using namespace std;

void Merge(SqList &L1,SqList &L2,int i,int m,int n,int &compare,int &move);
void MSort(SqList &L1,SqList &L2,int s, int t,int &compare,int &move);
void MergeSort(SqList &L,int &compare,int &move);