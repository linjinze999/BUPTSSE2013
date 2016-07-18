#include<iostream>
#include<stdlib.h>
#include<math.h>
#include<vector>
#include"bag.h"
#include"MapColor.h"
using namespace std;


void main()
{
	int menu=0;
	int NUMBER=0;//背包物品数量
	int VECTOR=0;//背包最大体积
	int map_number=0;//地图顶点数量
	int map_max_color=0;//地图色彩数量
	while(true){
		system("cls");
		cout<<"***************  回溯法 **************"<<endl;
		cout<<"****  1、0/1背包问题              ****"<<endl;
		cout<<"****  2、图着色问题               ****"<<endl;
		cout<<"**************************************"<<endl;
		cout<<"请选择：";
		cin>>menu;
		switch(menu)
		{
		case 1:/*|---------------------0/1背包问题----------------------|*/
			{
				system("cls");
				cout<<"*****************0/1背包问题****************"<<endl;
				cout<<"请输入物品总数：";
				cin>>NUMBER;
				cout<<"请输入背包体积：";
				cin>>VECTOR;

				char way='0';
				cout<<endl;
				cout<<"*****************数据添加方式***************"<<endl;
				cout<<"****  1、随机生成；                     ****"<<endl;
				cout<<"****  2、手动输入。                     ****"<<endl;
				cout<<"********************************************"<<endl;
				cout<<"请选择：";
				cin>>way;
				
				int **allbag=new int *[NUMBER+2];//记录所有情况
				int *bag=new int[NUMBER+2];//记录结果
				int *vector=new int[NUMBER];//记录各物品体积
				int *value=new int[NUMBER];//记录各物品价值
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

				for(int n=0;n<pow((double)2,NUMBER);n++)//即将遍历
				{
					int dtb=n;
					for(int i=NUMBER-1;i>=0;i--)
					{
						goods[i]=dtb % 2;
						dtb=dtb / 2;
					}
					allbag[count]=(int*)malloc((NUMBER+2)*sizeof(int));
					getVV(allbag,value,vector,count,goods,NUMBER);
					if(allbag[count][0]>VECTOR)//回溯
					{
						int where1=NUMBER-1;
						while(where1>=0 && !goods[where1])
							where1--;
						while(where1>=0 && goods[where1])
							where1--;
						if(where1>=0)
						{
							n=0;
							for(int m=0;m<where1;m++)
								if(goods[m])
									n+=pow((double)2,(NUMBER-1-m));
							n+=pow((double)2,(NUMBER-1-where1));
							n=n-1;
						}
						else
						{
							count++;
							break;
						}
					}
					count++;	
				}
				system("cls");
				cout<<"***************  0/1背包问题  **************"<<endl;//输出所有物品、背包内容
				cout<<"**  物品价值：";
				for(int i=0;i<NUMBER;i++)
					cout<<value[i]<<"  ";
				cout<<endl<<"**  物品体积：";
				for(int i=0;i<NUMBER;i++)
					cout<<vector[i]<<"   ";
				cout<<endl<<"**  背包体积："<<VECTOR;
				result(allbag,bag,count,VECTOR,NUMBER);//得到最终结果
				cout<<endl<<"********************************************"<<endl;//输出结果
				cout<<"**  最高价值："<<bag[1]<<endl<<"**  此时体积："<<bag[0]<<endl;
				cout<<"**  方案：";
				for(int i=0;i<NUMBER;i++)
					cout<<bag[i+2]<<" ";
				cout<<"（1为选中，0为不选）"<<endl;
				cout<<"********************************************"<<endl;
				cout<<endl;
				system("pause");
			}
			break;
		case 2:
			{/*|---------------------图着色问题----------------------|*/
				system("cls");
				cout<<"*****************图着色问题****************"<<endl;
				cout<<"请输入地图顶点数：";
				cin>>map_number;
				cout<<"请输入颜色数量：";
				cin>>map_max_color;

				char way='0';
				cout<<endl;
				cout<<"*****************数据添加方式***************"<<endl;
				cout<<"****  1、随机生成；                     ****"<<endl;
				cout<<"****  2、手动输入。                     ****"<<endl;
				cout<<"********************************************"<<endl;
				cout<<"请选择：";
				cin>>way;

				int *Map=new int[map_number*map_number];
				int *result=new int[map_number];
				memset(result,0,map_number);

				if(way=='1')//随机生成数据
					setMap(map_number,Map);
				else{//手动输入数据
					int connect;
					for(int i=0;i<map_number;i++)
						for(int n=0;n<map_number;n++){
							if(i==n)
								Map[i*map_number+n]=0;
							else if(n>i){
								cout<<"请输入第"<<i+1<<"个顶点与第"<<n+1<<"个顶点的连接情况：";
								cin>>connect;
								Map[i*map_number+n]=connect;
								Map[n*map_number+i]=connect;
							}
						}
				}
				cout<<endl<<"*********************地图*******************"<<endl;
				for(int i=0;i<map_number;i++){//显示地图
					cout<<"顶点"<<i+1<<" ";
					for(int n=0;n<map_number;n++){
						cout<<Map[i*map_number+n]<<" ";
					}
					cout<<endl;
				}
				cout<<endl<<"*********************结果*******************"<<endl;
				if(MapColor(map_number,Map,result,0,1,map_max_color)){//成功结果
					cout<<"着色结果：";
					for(int n=0;n<map_number;n++)
						cout<<result[n]<<" ";
				}
				else//失败结果
					cout<<"Error:"<<map_max_color<<"个色彩无法着色该地图。"<<endl;
				cout<<endl<<endl;
				system("pause");
			}
			break;
		default:
			break;
		}
	}
	
}