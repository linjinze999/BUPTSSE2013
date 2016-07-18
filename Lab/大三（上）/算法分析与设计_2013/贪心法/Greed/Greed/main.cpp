#include<iostream>
#include<iomanip>
#include"addgas.h"
#include"bag01.h"
#include"mapcolor.h"
using namespace std;

void main()
{
	int menu=0;
	while(true){
		system("cls");
		cout<<"**************** 贪心法 ****************"<<endl;
		cout<<"****  1、0/1背包问题                ****"<<endl;
		cout<<"****  2、汽车加油问题               ****"<<endl;
		cout<<"****  3、图着色问题                 ****"<<endl;
		cout<<"****************************************"<<endl;
		cout<<"请选择：";
		cin>>menu;
		switch(menu){
		case 1:
			{/*|------------------贪心法：背包问题-------------------|*/
				char way='0';
				int goods_number=0;//物品数量
				int bag_vector=0;  //背包体积

				system("cls");
				cout<<"|----------------- 贪心法 ------------------|"<<endl;
				cout<<"**************** 0/1背包问题 ***************"<<endl;
				cout<<"请输入物品总数：";
				cin>>goods_number;
				cout<<"请输入背包体积：";
				cin>>bag_vector;

				int *goods_vector=new int[goods_number];
				int *goods_value=new int[goods_number];
				int *result=new int[goods_number+2];

				cout<<endl;
				cout<<"**************** 数据添加方式 **************"<<endl;
				cout<<"****  1、随机生成；                     ****"<<endl;
				cout<<"****  2、手动输入。                     ****"<<endl;
				cout<<"********************************************"<<endl;
				cout<<"请选择：";
				cin>>way;

				if(way=='1')//随机生成数据
					setGoodsVV(goods_value,goods_vector,goods_number,bag_vector);
				else        //手动输入数据
					for(int i=0;i<goods_number;i++)
					{
						cout<<"请输入第"<<i+1<<"个物品体积：";
						cin>>goods_vector[i];
						cout<<"请输入第"<<i+1<<"个物品价值：";
						cin>>goods_value[i];
						
					}
				cout<<endl;
				cout<<"****************** 背包信息 ****************"<<endl;				//显示背包信息
				cout<<"**  物品价值：";
				for(int i=0;i<goods_number;i++)
					cout<<goods_value[i]<<"  ";
				cout<<endl;
				cout<<"**  物品体积：";
				for(int i=0;i<goods_number;i++)
					cout<<goods_vector[i]<<"   ";
				cout<<endl;
				cout<<"**  背包体积："<<bag_vector;
				getBagResult(goods_value,goods_vector,goods_number,bag_vector,result);	//获取最佳方案
				cout<<endl;
				cout<<"******************* 结果 *******************"<<endl;				//输出最佳方案
				cout<<"**  最高价值："<<result[1]<<endl;
				cout<<"**  此时体积："<<result[0]<<endl;
				cout<<"**  方案：";
				for(int i=0;i<goods_number;i++)
					cout<<result[i+2]<<" ";
				cout<<"（1为选中，0为不选）"<<endl;
				cout<<"********************************************"<<endl;
				cout<<endl;
				system("pause");
			}
			break;
		case 2:
			{/*|------------------贪心法：汽车加油-------------------|*/
				char way='0';
				int station_number = 0;//城市数量
				int max_distance = 0;

				system("cls");
				cout<<"|----------------- 贪心法 ------------------|"<<endl;
				cout<<"************* 汽车加油行驶问题 *************"<<endl;
				cout<<"请输入加油站个数：";
				cin>>station_number;
				cout<<"请输入满油可行驶路程：";
				cin>>max_distance;

				int *distance = new int[station_number];
				int *result_station = new int[station_number];
				int result_count = 0;

				cout<<endl;
				cout<<"*************** 数据添加方式 ***************"<<endl;
				cout<<"****  1、随机生成；                     ****"<<endl;
				cout<<"****  2、手动输入。                     ****"<<endl;
				cout<<"********************************************"<<endl;
				cout<<"请选择：";
				cin>>way;
				if(way=='1')//随机生成数据
					setDistance(distance,station_number,max_distance);
				else		//手动输入数据
				{
					distance[0] = 0;
					for(int i=1;i<station_number;i++)
					{
						cout<<"请输入第"<<i<<"个汽油站到第"<<i+1<<"的距离：";
						cin>>distance[i];
					}
				}

				cout<<endl;
				cout<<"******************* 距离 *******************"<<endl;
				for(int i=0;i<station_number;i++){  //显示地图
					cout<<distance[i]<<" ";
				}
				
				cout<<endl<<endl;
				cout<<"******************* 结果 *******************"<<endl;		//输出结果
				if(AddGasResult(max_distance,station_number,distance,result_station,result_count))
				{
					cout<<"加油方案：";
					for(int i = 0; i < station_number; i++)
						cout<<result_station[i]<<" ";
					cout<<endl;
					cout<<"最少加油次数："<<result_count<<endl;
				}
				else
					cout<<"Error：满油可行驶路程过小，无法达到终点。"<<endl;

				cout<<endl;
				system("pause");
			}
			break;
		case 3:
			{/*|--------------------贪心法：图着色问题-------------------|*/
				int map_number=0;
				int map_max_color=0;
				system("cls");
				cout<<"|----------------- 贪心法 -----------------|"<<endl;
				cout<<"**************** 图着色问题 ****************"<<endl;
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
				else{		//手动输入数据
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
						cout<<result[n]+1<<" ";
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