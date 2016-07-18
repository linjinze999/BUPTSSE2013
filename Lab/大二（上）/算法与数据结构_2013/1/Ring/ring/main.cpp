#include <iostream>
#include <conio.h>
using namespace std;

struct Node{
    int n;//密码
	int m;//序号
	Node *next;
};

void main(){
	Node *head = new Node;
	head->next = NULL;
	Node *tail;
	tail = head;
	Node *de;
	de = head;
	int population;
	int m;
H:  cout<<"请输入总人数：";
	cin>>population;
	if(population < 1 || population > 30){
		cout<<"输入有误！请重新输入！\n"<<endl;
		goto H;
	}
	else{
		for(int i = 1;i <= population;i++){//为每个人赋上密码值
			Node *p = new Node;
			p->m = i;
		    cout<<"请输入第"<<i<<"个人的密码：";
			cin>>p->n;
			tail->next = p;
			tail = tail->next;
		}
		head = head->next;//使堆首尾相连
		tail->next = head;
		free(de);//释放最开始的空节点
		cout<<"输入完成！\n"<<endl;
		cout<<"请输入起始出列的m值：";
		cin>>m;
		cout<<"出列顺序为：";
		Node *d;
		do{//输出序号
			d = head;
			for(int n = 1;n < m;n++){
				tail = d;
				d = d->next;
			}
			cout<<d->m<<"、";
			m = d->n;
			head = d->next;
			tail->next = head;
			free(d);
			population--;
		}while(population > 0);
	}
	cout<<"\n\n程序结束，按任意键退出";
	_getch();
}