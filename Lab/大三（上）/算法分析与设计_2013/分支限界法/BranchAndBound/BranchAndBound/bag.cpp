#include"bag.h"
#include<time.h>
#include<stdlib.h>
#include<string>
#include<vector>


void node_copy(node *node1,node node2)//复制node
{
	(*node1).choose=node2.choose;
	(*node1).vector=node2.vector;
	(*node1).value=node2.value;
	(*node1).up_bound=node2.up_bound;
	(*node1).deep=node2.deep;
};

void node_extend(node *node_left,node *node_right,node extend_node,int *goods_value,int *goods_vector,int *goods_per_value,int bag_vector,int goods_number)//扩展结点的左右结点(含上界计算)
{
	//左结点
	node_copy(node_left,extend_node);
	(*node_left).choose[(*node_left).deep]='1';
	(*node_left).vector+=goods_vector[goods_per_value[(*node_left).deep*2]];
	(*node_left).value+=goods_value[goods_per_value[(*node_left).deep*2]];
	(*node_left).deep+=1;
	if((*node_left).deep<goods_number)
		(*node_left).up_bound=(*node_left).value+(bag_vector-(*node_left).vector)*goods_per_value[(*node_left).deep*2+1];
	else
		(*node_left).up_bound=(*node_left).value;
	
	//右结点
	node_copy(node_right,extend_node);
	(*node_right).choose[(*node_right).deep]='0';
	(*node_right).deep+=1;
	if((*node_right).deep<goods_number)
		(*node_right).up_bound=(*node_right).value+(bag_vector-(*node_right).vector)*goods_per_value[(*node_right).deep*2+1];
	else
		(*node_right).up_bound=(*node_right).value;
};

void InsertSort(int *a, int n)//插入排序，并保留序号
{
    for(int i= 3; i<n*2; i+=2){
        if(a[i] > a[i-2]){
            int j= i-2;
            int x = a[i];
			int no=a[i-1];
			a[i-1] = a[i-3];
            a[i] = a[i-2];
            while((j > -1) && (x > a[j])){
                a[j+2] = a[j];
				a[j+1] = a[j-1];
                j=j-2;
            }
            a[j+2] = x;
			a[j+1] = no;
        }
    }
};

void getBagResult(int *goods_value,int *goods_vector,int goods_number,int bag_vector,int *result)//获取最佳方案
{
	int *goods_per_value=new int[2*goods_number];//保存（价值/体积）值和序号，序号方便结果打印
	for(int i=0;i<goods_number;i++)
	{
		goods_per_value[i*2]=i;
		goods_per_value[i*2+1]=goods_value[i]/goods_vector[i];
	}
	InsertSort(goods_per_value,goods_number);	//排序，并保留序号

	char *result_change=new char[goods_number];	//排序被修改后，得出的方案

	std::vector<node> PT;//PT表
	node *root=new node;//根节点
	(*root).value=0;
	(*root).vector=0;
	(*root).deep=0;
	for(int i=0;i<goods_number;i++)
		(*root).choose+="2";
	(*root).up_bound=bag_vector*goods_per_value[1];
	PT.push_back(*root);

	while(PT.size()>0){
		int extend_node_max_up_bound=0;					 //PT表中最大上界值
		int extend_node_position;                        //PT表中最大上界所在位置
		for(unsigned int n=0;n < PT.size();n++)          //取上界最大的结点为扩展结点
			if(extend_node_max_up_bound<PT[n].up_bound){
				extend_node_position=n;
				extend_node_max_up_bound=PT[n].up_bound;
			}
		node *extend_node=new node;
		node_copy(extend_node,PT[extend_node_position]); //复制扩展结点
		PT.erase(PT.begin()+extend_node_position);	     //删除扩展结点

		//由扩展结点得到左右结点
		node *node_left=new node;
		node *node_right=new node;
		node_extend(node_left,node_right,*extend_node,goods_value,goods_vector,goods_per_value,bag_vector,goods_number);
		//左结点
		if((*node_left).vector<=bag_vector){				//体积符合条件
			if((*node_left).deep>=(goods_number))			//叶节点
			{
				bool success=true;							//是否最优解
				for(unsigned int n=0;n < PT.size();n++)
					if((*node_left).value<PT[n].up_bound){	//不是最优解，删掉PT表中上界小于该值的结点
						success=false;
						PT.erase(PT.begin()+n);
						n = -1;
					}
				if(success)									//最优解
				{
					for(int i=0;i<goods_number;i++)
						result_change[i]=(*node_left).choose[i];
					break;
				}
			}
			else											//不是叶节点，入PT表
				PT.push_back(*node_left);
		}
		//右结点
		if((*node_right).vector<=bag_vector){		//体积符合条件
			if((*node_right).deep>=(goods_number))	//叶节点
			{
				bool success=true;					//是否最优解
				for(unsigned int n=0;n < PT.size();n++)
					if((*node_right).value<PT[n].up_bound){//不是最优解，删掉PT表中上界小于该值的结点
						success=false;
						PT.erase(PT.begin()+n);
						n = -1;
					}
				if(success)	//最优解
				{
					for(int i=0;i<goods_number;i++)
						result_change[i]=(*node_right).choose[i];
					break;
				}
			}
			else			//不是叶节点，入PT表
				PT.push_back(*node_right);
		}
	}

	//将改序后的结果转化成改序前的结果
	int *result_no_VV=new int[goods_number];
	for(int n=0;n<goods_number;n++)
		result_no_VV[n]=0;
	for(int i=0;i<goods_number;i++)
		if(result_change[i]=='1')
			result_no_VV[goods_per_value[i*2]]=1;
	//算出最优方案的体积与价值，由result记录全部结果
	result[0]=0;
	result[1]=0;
	for(int i=0;i<goods_number;i++)
	{
		if(result_no_VV[i]==1)
		{
			result[i+2]=1;
			result[0]+=goods_vector[i];
			result[1]+=goods_value[i];
		}
		else
			result[i+2]=0;
	}

};

void setGoodsVV(int *goods_value,int *goods_vector,int goods_number,int bag_vector)//随机生成价值和体积
{
	srand((int)time(0));
	int r=rand()%100;
	for(int i=0;i<goods_number;i++)
		{
			if(r==0)
				r=59;
			goods_value[i]=r;
			r=(r*157)%bag_vector;
			if(r==0)
				r=59%bag_vector;
			goods_vector[i]=r;
			r=(r*161)%100;
		}
};
