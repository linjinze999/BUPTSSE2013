#include<iostream>
#include"color.h"
#include <stdio.h>
using namespace std;

int Color::number=0;
Color::Color(){Color::number++;};
Color::~Color(){Color::number--;};