#pragma once
#include <iostream>
using namespace std;

struct Node  
{ 
	char Name[20]; 
	double x,y; 
	Node *next; 
}; 
class List{ 
private: 
	Node *head,*tail;
public: 
	List(); 
	void Found();
	void Insert(); 
	void Delete(); 
	void Search(); 
	void Change(); 
	void Nearcity();
}; 