#include<vector>
#include<iostream>
#include<math.h>
#include"wiring.h"

void getWiring(int circuit_number,int *circuit_start,int *circuit_end,std::vector<int> *result)//得到电路布线
{
	int bound=abs(circuit_end[0]-circuit_start[0])+abs(circuit_end[1]-circuit_start[1]);//函数限界值

	wiring_node *root=new wiring_node;//开始结点
	(*root).x=circuit_start[0];
	(*root).y=circuit_start[1];
	(*root).deep=0;
	(*root).bound=bound;

	std::vector<wiring_node> PT;//PT表
	PT.push_back(*root);

	while(PT.size()>0){
		wiring_node *extend_node=new wiring_node;	//取PT表中第一个结点
		wiring_node_copy(extend_node,PT[0]);		//复制结点
		PT.erase(PT.begin());						//删除结点

		std::vector<wiring_node> *son_node=new std::vector<wiring_node>;
		if(wiring_node_extend(*extend_node,bound,circuit_end,son_node)){//扩展结点
			for(unsigned int i=0;i<(*son_node).size();i++){
				PT.push_back((*son_node)[i]);
			}
		}
		else{//得到一个结果
			(*result).push_back(circuit_start[0]);
			(*result).push_back(circuit_start[1]);
			for(unsigned int i=0;i<(*extend_node).path.size();i++)
				(*result).push_back((*extend_node).path[i]);
			(*result).push_back(-1);
		}

	}
};

bool wiring_node_extend(wiring_node extend_node,int bound,int *circuit_end,std::vector<wiring_node> *son_node){//扩展结点
	if(extend_node.x==circuit_end[0] && extend_node.y==circuit_end[1])//叶节点
		return false;
	else//扩展结点
	{
		wiring_node *son_node1=new wiring_node;//x+1
		wiring_node_copy(son_node1,extend_node);
		(*son_node1).deep+=1;
		(*son_node1).x+=1;
		(*son_node1).bound=(*son_node1).deep+abs((*son_node1).y-circuit_end[1])+abs((*son_node1).x-circuit_end[0]);
		if((*son_node1).bound==bound){
			(*son_node1).path.push_back((*son_node1).x);
			(*son_node1).path.push_back((*son_node1).y);
			(*son_node).push_back(*son_node1);
		}

		wiring_node *son_node2=new wiring_node;//x-1
		wiring_node_copy(son_node2,extend_node);
		(*son_node2).deep+=1;
		(*son_node2).x-=1;
		(*son_node2).bound=(*son_node2).deep+abs((*son_node2).y-circuit_end[1])+abs((*son_node2).x-circuit_end[0]);
		if((*son_node2).bound==bound){
			(*son_node2).path.push_back((*son_node2).x);
			(*son_node2).path.push_back((*son_node2).y);
			(*son_node).push_back(*son_node2);
		}

		wiring_node *son_node3=new wiring_node;//y+1
		wiring_node_copy(son_node3,extend_node);
		(*son_node3).deep+=1;
		(*son_node3).y+=1;
		(*son_node3).bound=(*son_node3).deep+abs((*son_node3).y-circuit_end[1])+abs((*son_node3).x-circuit_end[0]);
		if((*son_node3).bound==bound){
			(*son_node3).path.push_back((*son_node3).x);
			(*son_node3).path.push_back((*son_node3).y);
			(*son_node).push_back(*son_node3);
		}

		wiring_node *son_node4=new wiring_node;//y-1
		wiring_node_copy(son_node4,extend_node);
		(*son_node4).deep+=1;
		(*son_node4).y-=1;
		(*son_node4).bound=(*son_node4).deep+abs((*son_node4).y-circuit_end[1])+abs((*son_node4).x-circuit_end[0]);
		if((*son_node4).bound==bound){
			(*son_node4).path.push_back((*son_node4).x);
			(*son_node4).path.push_back((*son_node4).y);
			(*son_node).push_back(*son_node4);
		}
		return true;
	}
};

void wiring_node_copy(wiring_node *node1,wiring_node node2)//结点复制
{
	(*node1).bound=node2.bound;
	(*node1).deep=node2.deep;
	(*node1).x=node2.x;
	(*node1).y=node2.y;
	for(unsigned int i=0;i<node2.path.size();i++)
		(*node1).path.push_back(node2.path[i]);
};