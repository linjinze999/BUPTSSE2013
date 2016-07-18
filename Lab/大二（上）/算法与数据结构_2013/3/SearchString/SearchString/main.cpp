#include <iostream>
#include<string>
#include<conio.h>
using namespace std;

void  search( string  S, string T, int &place,int &times )//普通匹配
{   
	int i =0;
	int j = 0;
	times = 0;
	while ( i < (int)S.length()  &&  j < (int)T.length() ) 	 
	{  
		if ( S[i] == T[j] ) {
			++i ;
			++j ;
		} 	
		else {
			i = i - j + 1;
			j = 0;
		}      	
		times ++;//循环次数
	}
	if (  j >= (int)T.length() ) //匹配位置
		place = i-T.length()+1;
	else 
		place = -1;
}
void  Get_next( string T, int  next[] ){  //KMP中子串字符对应的next
	int j = 0; 
	int k = -1; 
	next[0]=-1;
	while ( j < (int)T.length() ){  
		if (k == -1 ||  T[j] == T[k] ){
			++j ;
			++k ;
			next[j]=k;	   
    	}
	    else{
			k = next[k]; 
		}

	}
}
void  search_KMP( string  S, string T, int &place,int &times){ //KMP匹配
	int i = 0; 
	int j = 0;
	times = 0;
	int next[100];
	Get_next(T,next);//得到子串的next
	while ( i < (int)S.length()  &&  j < (int)T.length() )
	{ 
		if ( j == -1 || S[i] == T[j] )   { ++i ;  ++j ;  }
		else  j = next[ j ];  
		times++;//循环次数
	}
	if (  j >= (int)T.length () )  place =  i-T.length()+1;  //匹配位置  
	else place = -1;
}  

void main(){
	int place,times;
	string s,t;
	while(true){
		cout<<"请输入主串：";
		cin>>s;
		cout<<"请输入子串：";
		cin>>t;
		search(s,t,place,times);//普通匹配
		cout<<"普通匹配："<<endl;
		if(place == -1){
			cout<<"不匹配，循环次数："<<times<<endl;
		}
		else{
			cout<<"匹配，匹配位置为："<<place<<"，循环次数为："<<times<<endl;
		}
		search_KMP(s,t,place,times);
		cout<<"KMP匹配："<<endl;//KMP匹配
		if(place == -1){
			cout<<"不匹配，循环次数："<<times<<endl;
		}
		else{
			cout<<"匹配，匹配位置为："<<place<<"，循环次数为："<<times<<endl;
		}
		cout<<"按任意键继续>>>>>>>>>>>>>>>>";
		_getch();
		cout<<endl<<endl;
	}
}

