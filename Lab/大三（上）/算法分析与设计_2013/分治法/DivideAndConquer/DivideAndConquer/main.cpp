#include<iostream>
#include<math.h>
#include<iomanip>
#include"CoverChessboard.h"
#include"ElementSelector.h"
#include"Maximun.h"
using namespace std;

void main()
{
	int menu=0;
	while(true){
		system("cls");
		cout<<"**************** 分治法 ****************"<<endl;
		cout<<"****  1、元素选择                   ****"<<endl;
		cout<<"****  2、最大子段和问题             ****"<<endl;
		cout<<"****  3、棋盘覆盖问题               ****"<<endl;
		cout<<"****************************************"<<endl;
		cout<<"请选择：";
		cin>>menu;
		switch(menu){
		case 1:
			{/*|------------------元素选择-------------------|*/
				char way = '0';
				int element_number = 0;	//元素数量
				int rank = 0;			//查找排名

				system("cls");
				cout<<"***************** 元素选择 *****************"<<endl;
				cout<<"请输入元素总数：";
				cin>>element_number;
				cout<<"请输入查找排名：";
				cin>>rank;
				while(rank>element_number)
				{
					cout<<"Error：值过大。"<<endl;
					cout<<"请输入查找排名：";
					cin>>rank;
				}
				int *data = new int[element_number];	//元素数据
				int *data2 = new int[element_number];
				int result_value = 0;				//查找值
				int result_no = 0;					//查找位置

				cout<<endl;
				cout<<"**************** 数据添加方式 **************"<<endl;
				cout<<"****  1、随机生成；                     ****"<<endl;
				cout<<"****  2、手动输入。                     ****"<<endl;
				cout<<"********************************************"<<endl;
				cout<<"请选择：";
				cin>>way;

				if(way=='1')//随机生成数据
					setData(data,element_number);
				else        //手动输入数据
					for(int i=0;i<element_number;i++)
					{
						cout<<"请输入第"<<i+1<<"个数据：";
						cin>>data[i];
					}
				cout<<endl<<"******************  数据  ******************"<<endl;		//显示元素数据
				for(int i=0;i<element_number;i++)
					cout<<data[i]<<"  ";
				cout<<endl;

				ElementSelect(data,data2,element_number,rank,&result_value,&result_no);

				cout<<endl<<"***************** 排序数据 *****************"<<endl;
				for(int i=0;i<element_number;i++)
					cout<<data2[i]<<"  ";
				cout<<endl;

				cout<<endl<<"****************** 结果 ********************"<<endl;//输出结果
				cout<<"排名第"<<rank<<"的元素值为："<<result_value<<endl;
				cout<<"排名第"<<rank<<"的元素在原数组中的位置为："<<result_no<<endl;

				cout<<endl;
				system("pause");
			}
			break;
		case 2:
			{/*|------------------最大子段和问题-------------------|*/
				char way='0';
				int data_length;//数组长度

				system("cls");
				cout<<"************** 最大子段和问题 **************"<<endl;
				cout<<"请输入数组长度：";
				cin>>data_length;

				int *data = new int[data_length];	//数组数据
				int left = 0;						//子段开始位置
				int right = 0;						//子段结束位置

				cout<<endl;
				cout<<"**************** 数据添加方式 **************"<<endl;
				cout<<"****  1、随机生成；                     ****"<<endl;
				cout<<"****  2、手动输入。                     ****"<<endl;
				cout<<"********************************************"<<endl;
				cout<<"请选择：";
				cin>>way;
				if(way=='1')//随机生成数据
					setString(data,data_length);
				else		//手动输入数据
					for(int i=0;i<data_length;i++)
					{
						cout<<"请输入第"<<i+1<<"个数据：";
						cin>>data[i];
					}

				cout<<endl<<endl;
				cout<<"******************* 数据 *******************"<<endl;	//输出数组数据
				for(int i=0;i<data_length;i++)
					cout<<data[i]<<" ";
				cout<<endl;

				int sum = Maximun(data,data_length,left,right);				//得到结果
				cout<<endl<<"******************* 结果 *******************"<<endl;	//输出结果
				cout<<"最大子段和为："<<sum<<endl;
				cout<<"此子段区间：["<<left+1<<","<<right+1<<"]"<<endl;
				cout<<"此子段数据：";
				for(int n = left; n <= right;n++)
					cout<<data[n]<<" ";

				cout<<endl<<endl;
				system("pause");
			}
			break;
		case 3:
			{/*|------------------棋盘覆盖问题-------------------|*/
				char way='0';
				int chess_k = 0;//2^k的k值
				system("cls");
				cout<<"*************** 棋盘覆盖问题 ***************"<<endl;
				cout<<"棋盘格数为2^k，请输入k值：";
				cin>>chess_k;

				int chess_length = pow((double)2,chess_k);		//棋盘长度
				int *chess = new int[chess_length*chess_length];//棋盘
				int point_x=0;									//特殊点x坐标
				int point_y=0;									//特殊点y坐标
				initChess(chess,chess_length);					//初始化棋盘

				cout<<endl;
				cout<<"************** 特殊点坐标输入 **************"<<endl;
				cout<<"****  1、随机生成；                     ****"<<endl;
				cout<<"****  2、手动输入。                     ****"<<endl;
				cout<<"********************************************"<<endl;
				cout<<"请选择：";
				cin>>way;
				if(way=='1')//随机生成特殊点
					setPoint(chess,chess_length,point_x,point_y);
				else		//手动输入特殊点
				{
					cout<<"请输入X坐标：";
					cin>>point_x;
					while(point_x>chess_length){
						cout<<"Error:值过大。"<<endl;
						cout<<"请输入X坐标：";
						cin>>point_x;
					}
					cout<<"请输入Y坐标：";
					cin>>point_y;
					while(point_y>chess_length){
						cout<<"Error:值过大。"<<endl;
						cout<<"请输入Y坐标：";
						cin>>point_y;
					}
					chess[(point_x)*chess_length + (point_y)]=0;
				}

				ChessBoard(chess,chess_length,0,0,point_x,point_y,chess_length);	//得到结果
				cout<<endl<<"******************** 棋盘 ******************"<<endl;	//输出结果
				for(int i=0;i<chess_length;i++)
				{
					for(int j=0;j<chess_length;j++)
						cout<<setw(3)<<chess[i*chess_length+j];
					cout<<endl;
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