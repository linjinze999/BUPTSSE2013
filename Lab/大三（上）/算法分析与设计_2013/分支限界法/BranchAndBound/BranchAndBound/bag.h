#include<string>
struct node//树节点信息
{
	std::string choose;//利用字符串以01来表示物品选择，如“0100”
	int vector;        //结点体积
	int value;         //结点价值
	int up_bound;      //结点上界
	int deep;          //结点深度
};
void node_copy(node *node1,node node2);//复制node
void node_extend(node *node_left,node *node_right,node extend_node,int *goods_value,int *goods_vector,int *goods_per_value,int bag_vector,int goods_number);//扩展结点的左右结点(含上界计算)
void InsertSort(int a[], int n);//插入排序，并保留序号
void getBagResult(int *goods_value,int *goods_vector,int goods_number,int bag_vector,int *result);//获取最佳方案
void setGoodsVV(int *goods_value,int *goods_vector,int goods_number,int bag_vector);//随机生成价值和体积

