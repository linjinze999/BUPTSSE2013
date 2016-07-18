#include<iostream>
#include<iomanip>
#include"greed_01bag.h"
#include"greed_AddGas.h"
#include"DP_SelectAD.h"
#include"DP_CarRun.h"
using namespace std;

void main()
{
	int menu=0;
	while(true){
		system("cls");
		cout<<"********** 贪心法、动态规划法 **********"<<endl;
		cout<<"****  1、贪心法：0/1背包问题        ****"<<endl;
		cout<<"****  2、贪心法:汽车加油问题        ****"<<endl;
		cout<<"****  3、动态规划法：广告牌选取     ****"<<endl;
		cout<<"****  4、动态规划法：汽车加油行驶   ****"<<endl;
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
			{
				/*|------------------动态规划法：广告牌选取问题-------------------|*/
				char way='0';
				int AD_number=0;
				system("cls");
				cout<<"|--------------- 动态规划法 ----------------|"<<endl;
				cout<<"************** 广告牌选取问题 **************"<<endl;//电路格数
				cout<<"请输入广告牌数量：";
				cin>>AD_number;

				int max_value=0;
				vector<int> *max_path = new vector<int>;
				int *AD_position = new int[AD_number+1];
				int *AD_price = new int[AD_number+1];
				AD_position[0] = 0;
				AD_price[0] = 0;
				int last_position=0;

				cout<<endl;
				cout<<"*************** 数据添加方式 ***************"<<endl;
				cout<<"****  1、随机生成；                     ****"<<endl;
				cout<<"****  2、手动输入。                     ****"<<endl;
				cout<<"********************************************"<<endl;
				cout<<"请选择：";
				cin>>way;
				if(way=='1'){
					setPV(AD_position,AD_price,AD_number);
				}
				else{
					for(int i=1;i<=AD_number;i++)
					{
						cout<<"请输入第"<<i<<"广告牌位置：";
						cin>>AD_position[i];
						while(AD_position[i]<=last_position){
							cout<<"Error:新广告牌位置应该在上一个广告牌之后！请输入大于"<<last_position<<"的值。"<<endl;
							cout<<"请输入第"<<i<<"广告牌位置：";
							cin>>AD_position[i];
						}
						last_position = AD_position[i];
						cout<<"请输入第"<<i<<"广告牌利益：";
						cin>>AD_price[i];
					}
				}

				cout<<endl<<"******************* 数据 *******************"<<endl;
				cout<<"广告牌位置：";
				for(int i=1;i<=AD_number;i++)
					cout<<setw(2)<<AD_position[i]<<" ";
				cout<<endl<<"广告牌利益：";
				for(int i=1;i<=AD_number;i++)
					cout<<setw(2)<<AD_price[i]<<" ";

				cout<<endl<<endl<<"******************* 结果 *******************"<<endl;
				SelectAD(AD_number,AD_position,AD_price,&max_value,max_path);
				cout<<"广告牌选择：";
				for(unsigned int k=0;k<(*max_path).size();k++)
					cout<<setw(2)<<(*max_path)[k]<<" ";
				cout<<endl<<"广告牌位置：";
				for(unsigned int k=0;k<(*max_path).size();k++)
					cout<<setw(2)<<AD_position[(*max_path)[k]]<<" ";
				cout<<endl<<"广告牌利益：";
				for(unsigned int k=0;k<(*max_path).size();k++)
					cout<<setw(2)<<AD_price[(*max_path)[k]]<<" ";
				cout<<endl<<"获得总收益："<<max_value<<endl;

				cout<<endl;
				system("pause");
			}
			
			break;
		case 4:
			{/*|------------------动态规划法：汽车加油行驶问题-------------------|*/
				char way = '0';
				int check_number = 0;
				int ride_route = 0;
				int cost_addgas = 0;
				int cost_back = 0;
				int cost_addstation = 0;
				system("cls");
				cout<<"|--------------- 动态规划法 ----------------|"<<endl;
				cout<<"************** 汽车加油行驶问题 **************"<<endl;//电路格数
				cout<<"请输入方形网格长度：";
				cin>>check_number;
				while(check_number<2 || check_number>100)
				{
					cout<<"错误：请输入2~100的值。";
					cout<<"请输入方形网格长度：";
					cin>>check_number;
				}
				cout<<"请输入满油汽车最多能行驶的路程：";
				cin>>ride_route;
				while(check_number<2 || check_number>10)
				{
					cout<<"错误：请输入2~10的值。";
					cout<<"请输入满油汽车最多能行驶的路程：";
					cin>>ride_route;
				}
				cout<<"请输入加油费：";
				cin>>cost_addgas;
				cout<<"请输入汽车返回所需要缴纳的费用：";
				cin>>cost_back;
				cout<<"请输入增加油库的费用：";
				cin>>cost_addstation;

				int CarRunResult = 0;
				int *station = new int[check_number*check_number];
				setStation(station,check_number);
				cout<<endl<<"******************** 数据 ********************"<<endl;
				cout<<"网格长度："<<check_number<<endl;
				cout<<"单次行驶最大路程："<<ride_route<<endl;
				cout<<"加油费："<<cost_addgas<<endl;
				cout<<"汽车回驶费用："<<cost_back<<endl;
				cout<<"增设油库费用："<<cost_addstation<<endl;
				cout<<"**************** 油库分布情况 ****************"<<endl;
				for(int i=0;i<check_number;i++)
				{
					for(int j=0;j<check_number;j++)
						cout<<station[i*check_number+j]<<" ";
					cout<<endl;
				}
				cout<<endl<<endl<<"******************** 结果 ********************"<<endl;
				CarRunResult=getCarRunResult(check_number,ride_route,cost_addgas,cost_back,cost_addstation,station);
				cout<<CarRunResult<<endl;

				cout<<endl;
				system("pause");
			}
			break;
		default:
			break;
		}
	}
}