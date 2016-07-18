#include<vector>
#include<string>
using namespace std;
bool parser(vector<string> input);//算术表达式语法分析器
bool ParseList(vector<string> *stack,string FirstWord,string *output,vector<vector<string>> M);//据分析表操作栈
void setParseList(vector<vector<string>> *M);//分析表生成
int getNonTerminalPosition(char a);//获取非终结符在分析表中的位置
int getTerminalPosition(string a);//获取终结符在分析表中的位置
bool Mystrchr(string s,char c);//字符串s中是否含有字符c