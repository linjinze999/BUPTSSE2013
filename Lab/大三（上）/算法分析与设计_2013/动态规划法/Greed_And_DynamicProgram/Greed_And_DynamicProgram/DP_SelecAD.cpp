#include<time.h>
#include<iostream>
#include"DP_SelectAD.h"
void SelectAD(int AD_number,int *AD_position,int *AD_price,int *max_value,vector<int> *max_path)
{
	vector<int> E;
	vector<int> OPT;
	vector< vector<int> > path;
	E.push_back(0);
	OPT.push_back(0);
	for(int j=0;j<=AD_number;j++)
	{
		vector<int> *v = new vector<int>;
		path.push_back(*v);
	}
	
	for(int i=1;i<=AD_number;i++)
	{
		E.push_back(getE(i,AD_position));
		OPT.push_back(getOPT(i,AD_position,AD_price,path,OPT,E[i]));
	}

	int max=0;
	int max_position=0;
	for(int i=1;i<=AD_number;i++)
	{
		if(max<OPT[i])
		{
			max = OPT[i];
			max_position = i;
		}
	}

	(*max_value) = max;
	(*max_path) = path[max_position];
};

int getE(int no,int *AD_position)
{
	for(int i = no-1;i>0;i--)
	{
		if(AD_position[no]>(AD_position[i]+5))
			return i;
	}
	return 0;
};

int getOPT(int no,int *AD_position,int *AD_price,vector< vector<int> > &path,vector<int> OPT,int E)
{

	if((AD_price[no] + OPT[E]) > OPT[no-1])
	{
		vector<int> v = path[E];
		v.push_back(no);
		path[no] = v;
		return (AD_price[no]+OPT[E]);
	}
	else{
		path[no] = path[no-1];
		return OPT[no-1];
	}
}

void setPV(int *AD_position,int *AD_price,int AD_number)//
{
	int *ADP = new int[AD_number];
	srand((int)time(0));
	int r=rand()%(AD_number*5);
	int p=rand()%17;
	
	for(int i=1;i<=AD_number;i++)
	{
		if(r==0)
			r=1;
		if(p==0)
			p=1;
		AD_price[i] = p;
		ADP[i-1] = r;
		r = (r*163)%(AD_number*5);
		p = (p*157)%17;
	}
	InsertSort0(ADP,AD_number);
	for(int i=1;i<=AD_number;i++)
		AD_position[i]=ADP[i-1];
};

void InsertSort0(int *a, int n)//²åÈëÅÅÐò
{
    for(int i= 1; i<n; i++){
        if(a[i] < a[i-1]){
            int j= i-1;
            int x = a[i];
            a[i] = a[i-1];
            while((j > -1) && (x < a[j])){
				a[j+1] = a[j];
                j=j-1;
            }
            a[j+1] = x;
        }
    }
};