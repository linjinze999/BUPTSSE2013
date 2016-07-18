#include<iostream>
#include<vector>
using namespace std;
void SelectAD(int AD_number,int *AD_position,int *AD_price,int *max_value,vector<int> *max_path);
int getE(int no,int *AD_position);
int getOPT(int no,int *AD_position,int *AD_price,vector< vector<int> > &path,vector<int> OPT,int E);
void setPV(int *AD_position,int *AD_price,int AD_number);//
void InsertSort0(int *a, int n);//≤Â»Î≈≈–Ú