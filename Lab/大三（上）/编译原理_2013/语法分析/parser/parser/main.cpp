#include<iostream>
#include"LA.h"
#include"parser.h"
using namespace std;

void main()
{
	char *FileName=new char[15];
	vector<string> input;
	while(true){
		system("cls");
		cout<<"********************************************"<<endl;
		cout<<"****            语法分析器              ****"<<endl;
		cout<<"****       (针对算数表达式的语法)       ****"<<endl;
		cout<<"********************************************"<<endl;
		cout<<"请输入文件名：";
		cin>>FileName;
		while(!LexicalAnalyser(FileName,&input)){
			cout<<"Error:无法打开该文件！(检查文件名正误，或是否已添加后缀)"<<endl;
			cout<<"请重新输入文件名：";
			cin>>FileName;;
		}
		system("cls");
		if(!parser(input))
			cout<<"错误:该表达式不是算术表达式！"<<endl<<endl;
		else
			cout<<"成功:该表达式是算术表达式！"<<endl<<endl;
		system("pause");
	}
}
