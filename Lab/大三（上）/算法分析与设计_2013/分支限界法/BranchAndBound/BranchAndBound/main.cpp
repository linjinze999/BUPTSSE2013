#include<iostream>
#include"bag.h"
#include"travel_city.h"
#include"wiring.h"
using namespace std;

void main()
{
	int menu=0;
	while(true){
		system("cls");
		cout<<"************** 分支限界法 **************"<<endl;
		cout<<"****  1、0/1背包问题                ****"<<endl;
		cout<<"****  2、旅行商售货员问题           ****"<<endl;
		cout<<"****  3、电路布线问题               ****"<<endl;
		cout<<"****************************************"<<endl;
		cout<<"请选择：";
		cin>>menu;
		switch(menu){
		case 1:
			{/*|------------------0/1背包问题-------------------|*/
				char way='0';
				int goods_number=0;//物品数量
				int bag_vector=0;  //背包体积

				system("cls");
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

				system("cls");
				cout<<"***************  0/1背包问题  **************"<<endl;				//显示背包信息
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
				cout<<"********************************************"<<endl;				//输出最佳方案
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
			{/*|------------------旅行商售货员问题-------------------|*/
				char way='0';
				int city_number=0;//城市数量
				int choose=0;

				system("cls");
				cout<<"************* 旅行商售货员问题 *************"<<endl;
				cout<<"请输入城市个数：";
				cin>>city_number;

				int *city_cost=new int[city_number*city_number];//城市间路程（费用）
				int *city_travel_result=new int[city_number];   //记录最终结果

				cout<<endl;
				cout<<"*************** 数据添加方式 ***************"<<endl;
				cout<<"****  1、随机生成；                     ****"<<endl;
				cout<<"****  2、手动输入。                     ****"<<endl;
				cout<<"********************************************"<<endl;
				cout<<"请选择：";
				cin>>way;
				if(way=='1')//随机生成数据
					setCost(city_number,city_cost);
				else		//手动输入数据
				{      
					int cost;
					for(int i=0;i<city_number;i++)
						for(int n=i;n<city_number;n++)
						{
							if(i==n)
								city_cost[i*city_number+n]=100;
							else
							{
								cout<<"请输入第"<<i+1<<"个城市与第"<<n+1<<"个城市的路程（费用）：";
								cin>>cost;
								if(cost<100)
								{
									city_cost[i*city_number+n]=cost;
									city_cost[n*city_number+i]=cost;
								}
								else{
									cout<<"Error:路程（费用）不宜超过一百，请重新输入。"<<endl;
									n--;
								}
							}
						}
				}

				cout<<endl;
				cout<<"******************* 地图 *******************"<<endl;
				for(int i=0;i<city_number;i++){  //显示地图
					cout<<"城市"<<i+1<<" ";
					for(int n=0;n<city_number;n++){
						if(city_cost[i*city_number+n]==100)
							cout<<"◎ ";
						else
							cout<<city_cost[i*city_number+n]<<" ";
					}
					cout<<endl;
				}

				
				cout<<endl;
				cout<<"***************** 城市起点 *****************"<<endl;//选择起点城市
				cout<<"请选择起点城市(1~"<<city_number<<")：";
				cin>>choose;
				while(choose>city_number){
					cout<<"Error:起点城市编号过大，请输入1~"<<city_number<<"。"<<endl;;
					cout<<"请选择起点城市(1~"<<city_number<<")：";
					cin>>choose;
				}

				getTravelResult(city_number,city_cost,city_travel_result,choose);//得到路线
				cout<<endl;
				cout<<"******************* 结果 *******************"<<endl;		//输出结果
				cout<<"路径：";
				for(int i=0;i<city_number;i++)
					cout<<city_travel_result[i]<<" ";
				cout<<endl<<endl;
				system("pause");
			}
			break;
		case 3:
			{/*|------------------电路布线问题-------------------|*/
				int circuit_number=0;
				system("cls");
				cout<<"************** 电路布线问题 **************"<<endl;//电路格数
				cout<<"请输入电路格数：";
				cin>>circuit_number;

				cout<<endl;
				cout<<"*************** 起止坐标值 ***************"<<endl;//起止坐标值
				int circuit_start[2];
				int circuit_end[2];
				cout<<"请输入出发点X坐标：";
				cin>>circuit_start[0];
				while(circuit_start[0]>=circuit_number){
					cout<<"Error:值过大。"<<endl;
					cout<<"请输入出发点X坐标：";
					cin>>circuit_start[0];
				}
				cout<<"请输入出发点Y坐标：";
				cin>>circuit_start[1];
				while(circuit_start[1]>=circuit_number){
					cout<<"Error:值过大。"<<endl;
					cout<<"请输入出发点Y坐标：";
					cin>>circuit_start[1];
				}
				cout<<"请输入终点X坐标：";
				cin>>circuit_end[0];
				while(circuit_end[0]>=circuit_number){
					cout<<"Error:值过大。"<<endl;
					cout<<"请输入终点X坐标：";
					cin>>circuit_end[0];
				}
				cout<<"请输入终点Y坐标：";
				cin>>circuit_end[1];
				while(circuit_end[1]>=circuit_number){
					cout<<"Error:值过大。"<<endl;
					cout<<"请输入终点Y坐标：";
					cin>>circuit_end[1];
				}

				std::vector<int> *result=new std::vector<int>;
				getWiring(circuit_number,circuit_start,circuit_end,result);//得到电路布线
				cout<<endl;
				cout<<"******************** 路径 ******************"<<endl;//输出结果
				cout<<"路径：";
				for(unsigned int i=0;i<(*result).size();i+=2)
				{
					if((*result)[i] == -1)
					{
						if(i!=((*result).size()-1))
						{
							cout<<endl<<"路径：";
							i-=1;
						}
						else
							i-=1;
					}
					else
						cout<<"["<<(*result)[i]<<","<<(*result)[i+1]<<"] ";
				}
				cout<<endl<<endl;
				system("pause");
			}
			break;
		default:
			break;
		}
	}
}