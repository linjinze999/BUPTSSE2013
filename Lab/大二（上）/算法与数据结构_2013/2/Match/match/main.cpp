#include<iostream>	
#include<stack> 
#include<string>
#include<conio.h>
using namespace std; 
bool isbrace(string s){//判断字符串是否全由括号组成
	for(int i = 0;i<s.length();i++){
		if(s[i] == '(' || s[i] == ')' || s[i] == '[' || s[i] == ']' || s[i] == '{' || s[i] == '}');
		else return 0;
	}
	return 1;
}
bool ismatch(string s){//判断s的括号是否匹配
	stack<char> st;
	st.empty();
	for(int i = 0;i<s.length();i++){ 
		if(s[i] == '('|| s[i] == '[' || s[i] == '{') st.push(s[i]); //遇左括号入栈
		else { 
			if(s[i] == ']') {
				//遇右括号看是否与栈顶括号匹配
				if(!st.empty()){
					//右括号遇到栈空时直接返回false
					if(st.top() != '[') { 
						return 0; 
					} 
					else st.pop(); 
				}
				else return 0;
			} 
			if(s[i] == ')') {
				if(!st.empty()){
					if(st.top()!='(') {
						return 0;
					} 
					else st.pop(); 
				}
				else return 0;
			} 
			if(s[i] == '}') {
				if(!st.empty()){
					if(st.top()!='{') {
						return 0;
					} 
					else st.pop(); 
				}
				else return 0;
			} 
		} 
	}
	if(st.empty()) return 1;
	else return 0;
}
void main() { 
	string S;
	while(true){
		cout<<"请输入要匹配的括号：";
		cin>>S; 
		if(isbrace(S)){
			//字符串全由括号组成
			if(ismatch(S)) 
				//字符串括号匹配正确
				cout<<"以上括号匹配！"<<endl;
			else 
				//字符串括号匹配错误
				cout<<"以上括号不匹配！"<<endl;
		} 	 
		else{
			//字符串不全由括号组成
			cout<<"以上字符串非法！"<<endl; 
		}
		cout<<"\n按任意键继续>>>>>>>>>>>>>>>>>>>"; 
		_getch(); 
		cout<<endl<<endl; 
	}
}
