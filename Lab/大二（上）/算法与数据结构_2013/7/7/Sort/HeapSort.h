#pragma once
#include<iostream>
#include"List.h"
using namespace std;

void HeapAdjust(SqList &H, int s, int m,int &compare,int &move);
void HeapSort( SqList &H ,int &compare,int &move);