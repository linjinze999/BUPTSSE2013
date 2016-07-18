#include<iostream>
#include"LA.h"
using namespace std;

void main()
{
	char *FileName=new char[15];
	while(true){
		system("cls");
		cout<<"********************************************"<<endl;
		cout<<"****            词法分析器              ****"<<endl;
		cout<<"****   (附带文本字符数、单词数、行数)   ****"<<endl;
		cout<<"********************************************"<<endl;
		cout<<"请输入文件名：";
		cin>>FileName;
		while(!LexicalAnalyser(FileName)){
			cout<<"Error:无法打开该文件！(检查文件名正误，或是否已添加后缀)"<<endl;
			cout<<"请重新输入文件名：";
			cin>>FileName;;
		}
		system("pause");
	}
}
