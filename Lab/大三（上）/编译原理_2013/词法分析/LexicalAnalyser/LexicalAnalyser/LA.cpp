#include<iostream>
#include<fstream>
#include<string>
#include<vector>
#include"LA.h"
using namespace std;

//关键字
char *KeyWord[]={"int","bool","char","long","double","include","void","return","for","do","switch","while","case","break",
	"if","else","goto","new","true","false","default","continue"};
//运算符
char *Operatornum[]={"+","-","*","/","%","++","+=","--","-=","^","*="};
//比较符
char *Comparison[]={">","<",">=","<=","==","!=","<>"};
//分界符
char *Interpunction[]={",",";","(",")","[","]","{","}"};
//逻辑运算符
char *Logical[]={"&&","&","||","|"};
//注释
char *Note[]={"//","/*","*/"};

//字符数、单词数、行数
int CharNum,WordNum,LineNum;

//符号表
vector<char*> variate;

bool LexicalAnalyser(char *FileName)
{
	ifstream f;
	f.open(FileName);/*打开文件*/
	if(f.is_open())/*该文件存在*/
	{
		CharNum=0;
		WordNum=0;
		LineNum=0;
		char *line=new char[1024];
		variate.clear();
		
		cout<<endl<<"*******************记号流*******************"<<endl;
		while(!f.eof())/*读取至文件末尾*/
		{
			f.getline(line,1024);/*每次读取一行*/
			LineNum+=1;/*行数+1*/
			Lexical(line);/*词法分析*/
		}
		f.close();/*关闭文件*/
		cout<<"*******************符号表*******************"<<endl;
		for(int VNo=0;VNo<variate.size();VNo++)
			cout<<VNo<<" "<<variate[VNo]<<endl;
		cout<<"********************统计********************"<<endl;
		cout<<"该文件拥有："<<LineNum<<"行、"<<WordNum<<"单词、"<<CharNum<<"字符"<<endl<<endl;
		return true;
	}
	else/*该文件不存在*/
		return false;
}

void Lexical(char *line)/*词法分析*/
{
	int count=0;
	
	while(line[count]!='\0')
			{
				//忽略空格和\t
				while(line[count]==' '||line[count]=='	')
					count++;
				//空格忽略后仍有字符待分析
				if(line[count]!='\0')
				{
					//判定是否为关键字或变量
					if((line[count]>='a'&&line[count]<='z')||
						(line[count]>='A'&&line[count]<='Z')||
						line[count]=='_')
					{
						int wordnum=0;
						char *word=new char;
						//得到单词
						while((line[count]>='a'&&line[count]<='z')||
						(line[count]>='A'&&line[count]<='Z')||
						(line[count]>='0'&&line[count]<='9')||
						line[count]=='_')
						{
							realloc(word,(wordnum+1)*sizeof(char));
							word[wordnum]=line[count];
							wordnum++;
							count++;
							CharNum+=1;
						}
						word[wordnum]=NULL;
						//关键字判定
						bool isKW=false;
						int KWNo=0;
						while(KeyWord[KWNo])
						{
							//是关键字
							if(strcmp(KeyWord[KWNo],word)==0)
							{
								cout<<"1 关键字 "<<word<<endl;
								isKW=true;
								break;
							}
							KWNo++;
						}
						//不是关键字，即为变量
						if(!isKW){
							bool variate_is_exist=false;
							for(int VNo=0;VNo<variate.size();VNo++)//字符表中是否已有该数据
								if(strcmp(variate[VNo],word)==0)
								{
									variate_is_exist=true;
									break;
								}
							if(!variate_is_exist)
								variate.push_back(word);//输入到字符表中
							cout<<"2 标识符 "<<word<<endl;
						}
						WordNum+=1;
					}
					//判定是否为数值
					else if(line[count]>='0'&&line[count]<='9')
					{
						char *word=new char;
						int wordnum=0;
						while((line[count]>='0'&&line[count]<='9')||line[count]=='.')
						{
							realloc(word,(wordnum+1)*sizeof(char));
							word[wordnum]=line[count];
							wordnum++;
							count++;
							CharNum+=1;
						}
						word[wordnum]=NULL;
						cout<<"3 常数值 "<<word<<endl;
						WordNum+=1;
					}
					//其他情况（各种符号）
					else
					{
						switch(line[count])
						{
						case '+':
							//++
							if(line[count+1]=='+')
							{
								cout<<"4 运算符 ++"<<endl;
								count+=2;
								CharNum+=2;
								WordNum+=1;
							}
							//+=
							else if(line[count+1]=='=')
							{
								cout<<"4 运算符 +="<<endl;
								count+=2;
								CharNum+=2;
								WordNum+=1;
							}
							//+
							else
							{
								cout<<"4 运算符 +"<<endl;
								count+=1;
								CharNum+=1;
								WordNum+=1;
							}
							break;
						case '-':
							//--
							if(line[count+1]=='-')
							{
								cout<<"4 运算符 --"<<endl;
								count+=2;
								CharNum+=2;
								WordNum+=1;
							}
							//-=
							else if(line[count+1]=='=')
							{
								cout<<"4 运算符 -="<<endl;
								count+=2;
								CharNum+=2;
								WordNum+=1;
							}
							//-
							else
							{
								cout<<"4 运算符 -"<<endl;
								count+=1;
								CharNum+=1;
								WordNum+=1;
							}
							break;
						case '=':
							//==
							if(line[count+1]=='=')
							{
								cout<<"5 比较符 =="<<endl;
								count+=2;
								CharNum+=2;
								WordNum+=1;
							}
							//=
							else
							{
								cout<<"6 赋值符 ="<<endl;
								count+=1;
								CharNum+=1;
								WordNum+=1;
							}
							break;
						case '!':
							//!=
							if(line[count+1]=='=')
							{
								cout<<"5 比较符 !="<<endl;
								count+=2;
								CharNum+=2;
								WordNum+=1;
							}
							//!
							else
							{
								cout<<"4 运算符 !"<<endl;
								count+=1;
								CharNum+=1;
								WordNum+=1;
							}
							break;
						case '>':
							//>=
							if(line[count+1]=='=')
							{
								cout<<"5 比较符 >="<<endl;
								count+=2;
								CharNum+=2;
								WordNum+=1;
							}
							//>
							else
							{
								cout<<"5 比较符 >"<<endl;
								count+=1;
								CharNum+=1;
								WordNum+=1;
							}
							break;
						case '<':
							//<=
							if(line[count+1]=='=')
							{
								cout<<"5 比较符 <="<<endl;
								count+=2;
								CharNum+=2;
								WordNum+=1;
							}
							//<>
							else if(line[count+1]=='>')
							{
								cout<<"5 比较符 <>"<<endl;
								count+=2;
								CharNum+=2;
								WordNum+=1;
							}
							//<
							else
							{
								cout<<"5 比较符 <"<<endl;
								count+=1;
								CharNum+=1;
								WordNum+=1;
							}
							break;
						case '*'://*=
							if(line[count+1]=='=')
							{
								cout<<"4 运算符 *="<<endl;
								count+=2;
								CharNum+=2;
								WordNum+=1;
							}
							//*
							else
							{
								cout<<"4 运算符 *"<<endl;
								count+=1;
								CharNum+=1;
								WordNum+=1;
							}
							break;
						case '%'://%
							cout<<"4 运算符 %"<<endl;
							count+=1;
							CharNum+=1;
							WordNum+=1;
							break;
						case '^'://^
							cout<<"4 运算符 ^"<<endl;
							count+=1;
							CharNum+=1;
							WordNum+=1;
							break;
						case '|':
							//||
							if(line[count+1]=='|')
							{
								cout<<"7 逻辑符 ||"<<endl;
								count+=2;
								CharNum+=2;
								WordNum+=1;
							}
							//|
							else
							{
								cout<<"7 逻辑符 |"<<endl;
								count+=1;
								CharNum+=1;
								WordNum+=1;
							}
							break;
						case '&':
							//&&
							if(line[count+1]=='&')
							{
								cout<<"7 逻辑符 &&"<<endl;
								count+=2;
								CharNum+=2;
								WordNum+=1;
							}
							//&
							else
							{
								cout<<"7 逻辑符 &"<<endl;
								count+=1;
								CharNum+=1;
								WordNum+=1;
							}
							break;
						case '/':
							//"//"
							if(line[count+1]=='/')
							{
								char *word=new char;
								int wordnum=0;
								while(line[count]!='\0')
								{
									realloc(word,(wordnum+1)*sizeof(char));
									word[wordnum]=line[count];
									count++;
									wordnum++;
								}
								word[wordnum]=NULL;
								cout<<"8 注释符 "<<word<<endl;
							}
							//"/**/"
							else if(line[count+1]=='*')
							{
								char *word=new char;
								int wordnum=0;
								realloc(word,(wordnum+1)*sizeof(char));
								word[wordnum]=line[count];
								count++;wordnum++;
								realloc(word,(wordnum+1)*sizeof(char));
								word[wordnum]=line[count];
								count++;wordnum++;
								while(line[count]!='\0')
								{
									if(line[count]=='*'&&line[count+1]=='/')
									{
										realloc(word,(wordnum+1)*sizeof(char));
										word[wordnum]=line[count];
										count++;wordnum=0;
										realloc(word,(wordnum+1)*sizeof(char));
										word[wordnum]=line[count];
										count++;wordnum=0;
										break;
									}
									realloc(word,(wordnum+1)*sizeof(char));
									word[wordnum]=line[count];
									count++;wordnum=0;
								}
								word[wordnum]=NULL;
								cout<<"8 注释符 "<<word<<endl;
							}
							//"/"
							else
							{
								cout<<"4 运算符 /"<<endl;
								count+=1;
								CharNum+=1;
								WordNum+=1;
							}
							break;
						default://. ; , \  @ ~ ` $ # ( ) [ ] { }
							cout<<"9 分界符 "<<line[count]<<endl;
							count+=1;
							CharNum+=1;
							break;
						}
					}
				}
			}
}