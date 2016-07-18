#include <string.h>
#include <stdlib.h>
#include<iostream>
using namespace std;

#define HASH_LEN 50 //哈希表的长度 
#define M 47 //除数
#define NAME_NO 30 //人名的个数 

typedef struct NAME 
{
	char *py; //名字的拼音
	int k; //拼音所对应的整数
}NAME;
NAME NameList[HASH_LEN]; 

typedef struct hterm //哈希表
{
	char *py; //名字的拼音
	int k; //拼音所对应的整数
	int si; //查找长度
}HASH;
HASH HashList[HASH_LEN]; 


void InitNameList()//名字初始化 
{
	NameList[0].py="wanghao";
	NameList[1].py="baoboyang";
	NameList[2].py="linjinze";
	NameList[3].py="lixueping";
	NameList[4].py="xiaopingguo";
	NameList[5].py="dujiaoshou";
	NameList[6].py="liuyifei";
	NameList[7].py="zhangxinyu";
	NameList[8].py="zhuyida";
	NameList[9].py="zhangxueyuan";
	NameList[10].py="jiangteng";
	NameList[11].py="wanghaotian";
	NameList[12].py="gaojiawei";
	NameList[13].py="chensixuan";
	NameList[14].py="guojianmin";
	NameList[15].py="zhaizexi";
	NameList[16].py="zhaojunyong";
	NameList[17].py="kuangzhenyu";
	NameList[18].py="xuxiao";
	NameList[19].py="caosuo";
	NameList[20].py="liangshengming";
	NameList[21].py="keziqin";
	NameList[22].py="zhangxi";
	NameList[23].py="zhouxin";
	NameList[24].py="chenyuanwei";
	NameList[25].py="lizijian";
	NameList[26].py="panhaoting";
	NameList[27].py="xueyanxing";
	NameList[28].py="yuxuanang";
	NameList[29].py="yansuo";

	char *f;
	int r,s0;

	for (int i=0;i<NAME_NO;i++)
	{
		s0=0;
		f=NameList[i].py;

		for (r=0;*(f+r) != NULL;r++) //方法:将字符串的各个字符所对应的ASCII码相加，所得的整数做为哈希表的关键字
			s0=*(f+r)+s0;

		NameList[i].k=s0;
	} 
}

void CreateHashList()//建立哈希表 
{
	for (int i=0; i<50; i ++)
	{
		HashList[i].py="";
		HashList[i].k=0;
		HashList[i].si=0;
	}
	for (int i=0; i < NAME_NO ; i++)
	{
		int sum=0;
		int adr=(NameList[i].k) % M; //哈希函数
		int d=adr;
		if(HashList[adr].si==0) //不冲突
		{
			HashList[adr].k=NameList[i].k;
			HashList[adr].py=NameList[i].py;
			HashList[adr].si=1;
		}
		else //冲突 
		{
			do{
				d++; 
				sum=sum+1;
			}while (HashList[d].k!=0);

			HashList[d].k=NameList[i].k;
			HashList[d].py=NameList[i].py;
			HashList[d].si=sum+1;
		}
	}
}


void FindList()//查找 
{ 
	cout<<"请输入姓名的拼音:";
	char name[20]={0}; 
	cin>>name;

	int s0=0;
	for (int r=0;r<20;r++) //求关键字
		s0+=name[r]; 

	int sum=1;
	int adr=s0 % M; //使用哈希函数
	int d=adr;

	if(HashList[adr].k==s0) 
		cout<<"姓名:"<<HashList[d].py<<"  关键字:"<<s0<<"  查找长度为: 1"<<endl;
	else if (HashList[adr].k==0) 
		cout<<"无该记录!";
	else
	{
		int g=0;
		do
		{
			d++; 
			sum=sum+1;
			if (HashList[d].k==0)
			{
				cout<<"无记录!"<<endl;
				g=1; 
			}
			if (HashList[d].k==s0)
			{ 
				cout<<"姓名:"<<HashList[d].py<<"  关键字:"<<s0<<"  查找长度为: "<<sum<<endl;
				g=1; 
			}
		}while(g==0); 
	} 
}


void Display()//显示哈希表 
{
	cout<<"\n地址\t关键字\t\t搜索长度\tH(key)\t\t拼音 \n";

	for(int i=0; i<50; i++)
	{
		cout<<i;
		cout<<"\t"<<HashList[i].k;
		cout<<"\t\t"<<HashList[i].si;
		cout<<"\t\t"<<(HashList[i].k)%M;
		cout<<"\t\t"<<HashList[i].py;
		cout<<endl;
	}

	float average=0;
	for (int i=0;i <50;i ++)
		if(HashList[i].si!=0)
			average+=HashList[i].si;
	average=average/NAME_NO;
	cout<<"平均查找长度:ASL("<<NAME_NO<<")="<<average<<" \n\n";
}


void main(){
	InitNameList(); 
	CreateHashList (); 
	int i=0;
	while(true)
	{
		cout<<"\n------------------------哈希表的建立和查找----------------------"<<endl;
		cout<<" 1. 显示哈希表\n";
		cout<<" 2. 查找\n";
		cout<<" 3. 退出\n";
		cout<<"请选择：";
		cin>>i;
		switch(i){
		case 1:
			Display(); 
			break;
		case 2:
			FindList();
			break;
		case 3:
			goto H;
			break;
		default:
			break;
		}
		cout<<endl<<endl;
	}
H:;
}