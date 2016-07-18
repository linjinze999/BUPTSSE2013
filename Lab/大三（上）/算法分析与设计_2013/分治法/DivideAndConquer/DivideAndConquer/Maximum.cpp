#include<iostream>
#include<time.h>
using namespace std;

int Maximun(int data[], int length, int& left, int& right)//最大子串和问题
{
    int i, cur_left, cur_right;
    int cur_max, max;
    cur_max = max = left = right = cur_left = cur_right = 0;
    for(i = 0; i < length; ++i)
    {
        cur_max += data[i];
        if(cur_max > 0)
        {
            cur_right = i;
            if(max < cur_max)
            {
                max = cur_max;
                left = cur_left;
                right = cur_right;
            }
        }
        else  
        {
            cur_max = 0;
            cur_left = cur_right = i + 1;  
        }
    }
    return max;
};

void setString(int data[],int data_length)//随机生成带正负的整数
{
	srand((int)time(0));
	int r=rand()%100;
	int t = rand()%67;
	for(int i=0;i<data_length;i++)
		{
			if(t>33)
				data[i] = 0-r;
			else
				data[i]=r;
			if(r==0)
				r=1;
			r=(r*157)%100;
			if(t==0)
				t=1;
			t=(t*143)%67;
		}
};