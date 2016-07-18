#include<iostream>
#include<vector>

struct wiring_node//电路节点信息
{
	int x;					//结点x坐标
	int y;					//结点y坐标
	int bound;				//结点函数值
	int deep;				//结点深度
	std::vector<int> path;	//路径
};

void getWiring(int circuit_number,int *circuit_start,int *circuit_end,std::vector<int> *result);//得到电路布线
bool wiring_node_extend(wiring_node extend_node,int bound,int *circuit_end,std::vector<wiring_node> *son_node);//扩展结点
void wiring_node_copy(wiring_node *node1,wiring_node node2);//结点复制