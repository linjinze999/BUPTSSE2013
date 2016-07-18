#include<time.h>
#include<iostream>
using namespace std;

int tile=1;

void ChessBoard(int chess[],int chess_length,int tr, int tc, int dr, int dc, int size)//棋盘覆盖问题
{ 
	if(size == 1)  
    {
        return; 
    }  
    int t = tile++;//L型骨牌编号
    int s = size/2;//分割棋盘  
  
    //覆盖左上角子棋盘  
    if(dr<tr+s && dc<tc+s)//特殊方格在此棋盘中  
    {  
        ChessBoard(chess,chess_length,tr,tc,dr,dc,s);  
    }  
    else//特殊方格不在此棋盘中
    {
        //用编号为t的骨牌覆盖右下角  
		chess[(tr+s-1)*chess_length+(tc+s-1)]=t;
        //覆盖其余方格  
        ChessBoard(chess,chess_length,tr,tc,tr+s-1,tc+s-1,s);  
    }  
  
    //覆盖右上角子棋盘  
    if(dr<tr+s && dc>=tc+s)//特殊方格在此棋盘中  
    {  
        ChessBoard(chess,chess_length,tr,tc+s,dr,dc,s);  
    }  
    else//特殊方格不在此棋盘中  
    {  
        //用编号为t的骨牌覆盖左下角
		chess[(tr+s-1)*chess_length+(tc+s)]=t;
        //覆盖其余方格  
        ChessBoard(chess,chess_length,tr,tc+s,tr+s-1,tc+s,s);  
    }  
  
    //覆盖左下角子棋盘  
    if(dr>=tr+s && dc<tc+s)//特殊方格在此棋盘中  
    {  
        ChessBoard(chess,chess_length,tr+s,tc,dr,dc,s);  
    }  
    else//特殊方格不在此棋盘中  
    {  
        //用编号为t的骨牌覆盖右上角  
		chess[(tr+s)*chess_length+(tc+s-1)]=t;
        //覆盖其余方格  
        ChessBoard(chess,chess_length,tr+s,tc,tr+s,tc+s-1,s);  
    }  
  
    //覆盖右下角子棋盘  
    if(dr>=tr+s && dc>=tc+s)//特殊方格在此棋盘中  
    {  
        ChessBoard(chess,chess_length,tr+s,tc+s,dr,dc,s);  
    }  
    else//特殊方格不在此棋盘中  
    {  
        //用编号为t的骨牌覆盖左上角  
		chess[(tr+s)*chess_length+(tc+s)]=t;
        //覆盖其余方格  
        ChessBoard(chess,chess_length,tr+s,tc+s,tr+s,tc+s,s);  
    }
};

void setPoint(int chess[],int chess_length,int &point_x,int &point_y)//随机生成特殊点
{
	srand((int)time(0));
	int r=rand() % 100;
	r = (r*157) % chess_length;
	int t = (r * 143) % chess_length;
	chess[(r)*chess_length + t] = 0;
	point_x = r;
	point_y = t;
};

void initChess(int chess[],int chess_length)//初始化棋盘
{
	memset(chess,-1,chess_length*chess_length);
}