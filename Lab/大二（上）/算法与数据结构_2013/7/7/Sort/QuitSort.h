#pragma once
#include<iostream>
#include"List.h"
using namespace std;

int Partition (SqList &L, int low, int high,int &compare,int &move);
void QSort (SqList &L,  int s,  int  t ,int &compare,int &move) ;