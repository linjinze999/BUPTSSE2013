#include<iostream>
#include"point.h"
#include <stdio.h>
using namespace std;

int Point::number=0;
Point::Point(){Point::number++;};
Point::~Point(){Point::number--;};