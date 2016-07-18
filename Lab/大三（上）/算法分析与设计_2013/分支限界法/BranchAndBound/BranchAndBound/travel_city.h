#include<string>
#include<vector>
struct city_node//树节点信息
{
	std::string choose;//利用字符串来表示路程，如“1324”
	int down_bound;    //结点下界
	int deep;          //结点深度
};


void getTravelResult(int city_number,int *city_cost,int *city_travel_result,int choose);//得到最优路径
void node_copy(city_node *node1,city_node node2);//复制结点
void getTwoLineMin(int *city_cost,int lineNo,int city_number,int *min1,int *min2);//得到一行中除本身外两个最小的值
void getOneLineMin(int *city_cost,int lineNo,int city_number,int *min,std::string travel,int node_deep);//得到一行中除本身和被选中值外最小的值
bool getNodeExtend(city_node extend_node,std::vector<city_node> *node_form,int city_number,int *city_cost);//扩展结点
void setCost(int city_number,int *city_cost);//随机生成城市间的路程（费用）