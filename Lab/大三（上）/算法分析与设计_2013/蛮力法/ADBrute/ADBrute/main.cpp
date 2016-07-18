#include<iostream>
#include<stdlib.h>
#include<math.h>
#include"bag.h"
#include"marry.h"

using namespace std;


void main()
{
	int menu=0;
	int POPULATION=0;//婚姻匹配对数
	int NUMBER=0;//0/1背包物品数量
	int VECTOR=0;//0/1背包最大体积
	while(true){
		system("cls");
		cout<<"*****************蛮力法***************"<<endl;
		cout<<"**** 1、0/1背包问题；             ****"<<endl;
		cout<<"**** 2、婚姻匹配算法。            ****"<<endl;
		cout<<"**************************************"<<endl;
		cout<<"请选择方案：";
		cin>>menu;
		switch(menu)
		{
		case 1:/*-------------------0/1背包问题-------------------*/
			{
				char way='0';
				system("cls");
				cout<<"*****************0/1背包问题****************"<<endl;
				cout<<"请输入物品总数：";
				cin>>NUMBER;
				cout<<"请输入背包体积：";
				cin>>VECTOR;

				cout<<endl<<"*****************数据添加方式***************"<<endl;
				cout<<"****  1、随机生成；                     ****"<<endl;
				cout<<"****  2、手动输入。                     ****"<<endl;
				cout<<"********************************************"<<endl;
				cout<<"请选择：";
				cin>>way;
				
				int **allbag=new int*[NUMBER+2];//记录所有情况
				int *bag=new int[NUMBER+2];//记录结果
				int *vector=new int[NUMBER];//记录物品体积
				int *value=new int[NUMBER];//记录物品价值
				int count=0;
				int *goods=new int[NUMBER];//方案

				if(way=='1')//随机生成数据
					setVV(value,vector,NUMBER,VECTOR);
				else//手动输入数据
					for(int i=0;i<NUMBER;i++)
					{
						cout<<"请输入第"<<i+1<<"个物品价值：";
						cin>>value[i];
						cout<<"请输入第"<<i+1<<"个物品体积：";
						cin>>vector[i];
					}

				system("cls");
				for(int n=0;n<pow((double)2,NUMBER);n++)//所有情况
				{
					int dtb=n;
					for(int i=0;i<NUMBER;i++)
					{
						goods[i]=dtb % 2;
						dtb=dtb / 2;
					}
					allbag[count]=(int*)malloc((NUMBER+2)*sizeof(int));
					getVV(allbag,value,vector,count,goods,NUMBER);
					count++;	
				}
				cout<<"***************  0/1背包问题  **************"<<endl;
				cout<<"**  物品价值：";
				for(int i=0;i<NUMBER;i++)
					cout<<value[i]<<"  ";
				cout<<endl<<"**  物品体积：";
				for(int i=0;i<NUMBER;i++)
					cout<<vector[i]<<"   ";
				cout<<endl<<"**  背包体积："<<VECTOR;
				result(allbag,bag,count,VECTOR,NUMBER);//最终结果
				cout<<endl<<"********************************************"<<endl;
				cout<<"**  最高价值："<<bag[1]<<endl<<"**  此时体积："<<bag[0]<<endl;
				cout<<"**  方案：";
				for(int i=0;i<NUMBER;i++)
					cout<<bag[i+2]<<" ";
				cout<<"（1为选中，0为不选）"<<endl;
				cout<<"********************************************"<<endl;
				cout<<endl;
				system("pause");
				break;
			}
		case 2:/*-------------------婚姻匹配问题-------------------*/
			{
				system("cls");
				cout<<"**************婚姻匹配算法************"<<endl;
				cout<<"请输入匹配对数：";
				cin>>POPULATION;
				int **MenMark=new int*[POPULATION];//记录男生对女生的印象分	
				int **WomenMark=new int*[POPULATION];//记录女生对男生的印象分
				for(int n=0;n<POPULATION;n++)
				{
					MenMark[n]=(int*)malloc(POPULATION*sizeof(int));
					WomenMark[n]=(int*)malloc(POPULATION*sizeof(int));
				}
				int *MenMarry=new int[POPULATION];//记录男生的对象
				int *WomenMarry=new int[POPULATION];//记录女生的对象
				for(int i=0;i<POPULATION;i++)
				{
					MenMarry[i]=-1;
					WomenMarry[i]=-1;
				}

				marry(MenMark,WomenMark,POPULATION,MenMarry,WomenMarry);//得到结果
				cout<<endl<<"************男生对女生评价************"<<endl<<"      ";
				for(int i=0;i<POPULATION;i++)
					cout<<"女生"<<i+1<<" ";
				cout<<endl;
				for(int i=0;i<POPULATION;i++)
				{
					cout<<"男生"<<i+1<<" ";
					for(int n=0;n<POPULATION;n++)
					{
						cout<<MenMark[i][n]<<"    ";
						if(MenMark[i][n]<10)
							cout<<" ";
					}
					cout<<endl;
				}
				cout<<endl<<"************女生对男生评价************"<<endl<<"      ";
				for(int i=0;i<POPULATION;i++)
					cout<<"男生"<<i+1<<" ";
				cout<<endl;
				for(int i=0;i<POPULATION;i++)
				{
					cout<<"女生"<<i+1<<" ";
					for(int n=0;n<POPULATION;n++)
					{
						cout<<WomenMark[i][n]<<"    ";
						if(WomenMark[i][n]<10)
							cout<<" ";
					}
					cout<<endl;
				}
				cout<<endl<<"****************男生匹配**************"<<endl;
				cout<<"****     男生编号    女生编号     ****"<<endl;
				for(int i=0;i<POPULATION;i++)
					cout<<"****     "<<i+1<<"           "<<MenMarry[i]+1<<"            ****"<<endl;

				cout<<endl<<"****************女生匹配**************"<<endl;
				cout<<"****     女生编号    男生编号     ****"<<endl;
				for(int i=0;i<POPULATION;i++)
					cout<<"****     "<<i+1<<"           "<<WomenMarry[i]+1<<"            ****"<<endl;
				cout<<endl;
				system("pause");
				break;
			}
		default:
			break;
		}
	}
}