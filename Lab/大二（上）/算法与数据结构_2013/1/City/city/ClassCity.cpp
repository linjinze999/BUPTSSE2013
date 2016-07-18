#include <iostream>
#include <string>
#include <math.h>
#include "ClassCity.h"
using namespace std;

List::List(){
    head = new Node;
	head->next = NULL;
	tail = head;
};
void List::Insert(){//增加城市
	int exist = 0;
	Node *q;
    Node *p = new Node; 
	cout<<"请输入城市名称:"; 
	cin>>p->Name ; 
	for(q = head->next;q != NULL;q = q->next){//判断是否已存在该城市名
	    if(!strcmp(q->Name,p->Name)) 
		{ 
			exist = 1;
			break; 
		}
	}
	if(exist == 1){//已存在的城市名无效
	    cout<<"此城市名已存在，增加失败。"<<endl;
	}
	else{//城市名为新则继续读入坐标
		cout<<"请输入城市X坐标:";
		cin>>p->x ; 
		cout<<"请输入城市Y坐标:";
		cin>>p->y ;
		p->next = NULL;
		if(head->next == NULL){
			head->next = p;
		}
		tail->next = p;
		tail = tail->next;
		cout<<"增加成功！"<<endl;
	}
};
void List::Delete(){//删除城市
	Node *p,*t;
	int exist = 0;
	char name[20];    
	cout<<"请输入你要删除的城市的名称:"; 
	cin>>name ;
	for(p = head;p->next != NULL;p = p->next){//找到城市则删除
	    if(!strcmp(p->next->Name,name)) 
		{ 
			t = p->next; 
			p->next = t->next; 
			delete(t); 
			cout<<"删除成功!"<<endl;
			exist = 1;
			break; 
		}
	}
	if (exist == 0){//找不到城市则给出提示
	    cout<<"查不到此城市。"<<endl;
	}
};
void List::Search(){//查询城市
    Node *p;
	int exist = 0;
	char name[20];    
	cout<<"请输入你要查询的城市的名称:"; 
	cin>>name ;
	for(p = head->next;p != NULL;p = p->next){//找到城市则给出城市坐标
	    if(!strcmp(p->Name,name)) 
		{ 
			cout<<"“"<<p->Name<<"”"<<"的坐标为("<<p->x<<','<<p->y<<')'<<endl;
			exist = 1;
			break; 
		}
	}
	if (exist == 0){//找不到城市则给出提示
	    cout<<"查不到此城市。"<<endl;
	}
};
void List::Change(){//更新城市信息
	Node *p;
	int exist = 0;
	char name[20];    
	cout<<"请给出你要修改的城市的名称:"; 
	cin>>name ;
	for(p = head->next;p != NULL;p = p->next){//找到城市则改变该城市信息
	    if(!strcmp(p->Name,name)) 
		{ 
			cout<<"请输入新的城市名称：";
			cin>>p->Name ; 
	        cout<<"请输入新的城市X坐标:";
	        cin>>p->x ; 
	        cout<<"请输入新的城市Y坐标:";
	        cin>>p->y ;
			cout<<"修改完成！"<<endl;
			exist = 1;
			break; 
		}
	}
	if (exist == 0){//找不到城市则给出提示
	    cout<<"查不到此城市。"<<endl;
	}
};
void List::Nearcity(){//查询某点附近的城市
	char name[20]; 
	Node *p; 
	double x,y;
	int NearCityExist = 0;
	double SD; 
	double RD; 
	cout<<"请输入中心点的x坐标:"; 
	cin>>x; 
	cout<<"请输入中心点的y坐标:"; 
	cin>>y; 
	cout<<"请输入查询距离 :"; 
    cin>>SD; 
	for(p = head->next; p != NULL; p = p->next){//给出符合条件的附近城市
		RD = sqrt(((*p).x-x)*((*p).x-x)+((*p).y-y)*((*p).y-y)); 
		if(SD >= RD){
			cout<<p->Name<<":("<<p->x<<','<<p->y<<") 、 ";
			NearCityExist = 1;
		}
	}
	if(NearCityExist == 0){//若没有符合条件的附近城市则给出提示
	cout<<"不存在符合条件的城市。"<<endl;
	}
	else{
	    cout<<endl;
	}
};