#include<iostream>
#include<stack>
#include<conio.h>
#include<string>
using namespace std;

void transform(int number,int system){//将number转化为system进制数，并输出
	int e;
    stack<int> s;
	while(number){
		s.push(number%system);
		number = number / system;
	}
	while(!s.empty()){
		e = s.top();
		if(e<10)
			cout<<e;
		else
			cout<<(char)(e+'A'-10);
		s.pop();
	}
	cout<<endl;
}
bool isnumber(string s){//判断字符串S是否完全由数字组成
	for(int i = 0;i < s.length();i++){
	    if(isdigit(s[i]));
		else
			return 0;
	}
	return 1;
}
void main(){
	int number,system;
	string input;
	while(true){
	    cout<<"请输入一个十进制数：";
		cin>>input;
		if(isnumber(input)){
			//输入的十进制数正确
			number=atoi(input.c_str());
			cout<<"请输入要转换的进制数：";
			cin>>input;
			if(isnumber(input)){
				//输入的进制数正确
				system=atoi(input.c_str());;
				cout<<"“"<<number<<"”"<<"转换为"<<system<<"进制，结果为：";
				transform(number,system);//输出结果
				cout<<"按任意键继续>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
				_getch();
				cout<<endl<<endl;
			}
			else{
				//输入的十进制数错误
			    cout<<"输入有误，请重新输入！"<<endl<<endl;
			}
		}
		else{
			//输入的进制数错误
		    cout<<"输入有误，请重新输入！"<<endl<<endl;
		}
	}
}