#include<time.h>
#include<iostream>
#include"DP_CarRun.h"

int getCarRunResult(int check_number,int ride_route,int cost_addgas,int cost_back,int cost_addstation,int *station)
{
	int gas_state = ride_route+1;
	int noDrived = 100000;
	int *Drive = new int[check_number * check_number * (gas_state+1)];
	for(int i=0;i<(check_number * check_number * (gas_state+1));i++)
		Drive[i]=noDrived;
	for(int i=0;i < gas_state;i++)
    {
        Drive[i]=0;
    }
	int r=1;
	int i,j,p,q,min;
	while(r>0)
	{
		r=0;
		for(i=0;i<check_number;i++)
		{
			for(j=0;j<check_number;j++)
			{
				if(i!=0 || j!=0)
				{
					for(p=0;p<gas_state;p++)
					{
						min=noDrived;
						if(i!=0)
						{
							if(Drive[(i-1)*check_number*(gas_state+1) + j*(gas_state+1) + p+1] < min)
								min = Drive[(i-1)*check_number*(gas_state+1) + j*(gas_state+1) + p+1];
						}
						if(j!=0)
						{
							if(Drive[i*check_number*(gas_state+1) + (j-1)*(gas_state+1) + p+1] < min)
								min = Drive[i*check_number*(gas_state+1) + (j-1)*(gas_state+1) + p+1];
						}
						if(i!=check_number-1)
						{
							if(Drive[(i+1)*check_number*(gas_state+1) + j*(gas_state+1) + p+1] + cost_back< min)
							{
								min = Drive[(i+1)*check_number*(gas_state+1) + j*(gas_state+1) + p+1] + cost_back;
							}
						}
						if(j!=check_number-1)
						{
							if(Drive[i*check_number*(gas_state+1) + (j+1)*(gas_state+1) + p+1] + cost_back< min)
							{
								min = Drive[i*check_number*(gas_state+1) + (j+1)*(gas_state+1) + p+1] + cost_back;
							}
						}
						if(Drive[i*check_number*(gas_state+1) + j*(gas_state+1) + p] > min + station[i*check_number+j]*cost_addgas)
                        {	
                            r++;
                        }
						Drive[i*check_number*(gas_state+1) + j*(gas_state+1) + p]=min;
						if(station[i*check_number+j]==1)
                        {
                            Drive[i*check_number*(gas_state+1) + j*(gas_state+1) + p]+=cost_addgas;
                            for(q=1;q<gas_state;q++)
                                Drive[i*check_number*(gas_state+1) + j*(gas_state+1) + q]=Drive[i*check_number*(gas_state+1) + j*(gas_state+1) + p];
                            break;
                        }
                        else if(Drive[i*check_number*(gas_state+1) + j*(gas_state+1) + p]==noDrived)
                        {
							Drive[i*check_number*(gas_state+1) + j*(gas_state+1) + p] = Drive[i*check_number*(gas_state+1) + j*(gas_state+1)] + cost_addstation + cost_addgas;
                            for(q=p+1;q<gas_state;q++)
                            {
								Drive[i*check_number*(gas_state+1) + j*(gas_state+1) + q] = Drive[i*check_number*(gas_state+1) + j*(gas_state+1) + p];
                            }
                            break;
                        }
						
					}//forp
				}//if0,0
			}// for j
		}//for i
	}//while

	return Drive[(check_number-1)*check_number*(gas_state+1) + (check_number-1)*(gas_state+1)];

}


void setStation(int *station,int check_number)
{
	for(int i=0;i<check_number*check_number;i++)
		station[i]=0;
	
	srand((int)time(0));
	int number = rand()%(check_number*check_number);
	//int number = check_number;
	int *station_position = new int[number];
	int r=rand()%(check_number*check_number);
	for(int j=0;j<number;j++)
	{
		if(r==0)
			r=check_number;
		station_position[j]=r;
		r=(r*157*(check_number+1))%(check_number*check_number);
	}
	for(int n = 0; n < number; n++)
		station[station_position[n]]=1;
};

