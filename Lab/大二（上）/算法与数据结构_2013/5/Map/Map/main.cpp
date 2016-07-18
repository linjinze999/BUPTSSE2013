#include<iostream>
#include<queue>
#include<conio.h>
using namespace std;
#define MAX_VEX_NUM 30

typedef struct ArcNode{//边指向的下一个结点的下标及下一条边的指针
	int adjvex;
	struct ArcNode *nextarc;
}ArcNode;

typedef struct VNode{//顶点数据及第一条边的指针
	char data;
	bool visited;
	ArcNode *FirstArc;
}VNode,AdjList[MAX_VEX_NUM];

struct ALGraph{ //顶点数及边数
	AdjList vertices;
	int vexnum;
	int arcnum;
}; 

int LocateVex(ALGraph G,char v){//寻找V的下标
	int i=0;
	while(i<G.vexnum && v!=G.vertices[i].data)
		i++;
	if(i<G.vexnum)
		return i;
	else
		return -1;
}

void CreateMap(ALGraph &G){//创建图
	cout<<"***开始创建邻接表***"<<endl;
	cout<<"请输入图的顶点数:";
	cin>>G.vexnum;
	cout<<"请输入图的边数：";
	cin>>G.arcnum;
	char v1;//起始点
	char v2;//终止点
	int v1locate;//起始点的下标
	int v2locate;//终止点的下标
	char data;//顶点信息
	bool redo=0;//顶点信息是否重复
	ArcNode *p,*q,*end;
	cout<<"\n***开始输入图的信息***"<<endl;
	cout<<"1、输入顶点信息:"<<endl;
	for(int i=0;i<G.vexnum;i++){
		cout<<"第"<<i+1<<"个顶点：";
		cin>>data;
		for(int i=0;i<G.vexnum;i++){//顶点信息是否重复
			if(data==G.vertices[i].data){
				redo=1;
				break;
			}
		}
		if(!redo){//顶点信息正确
			G.vertices[i].data=data;
			G.vertices[i].FirstArc=NULL;
			G.vertices[i].visited=0;
		}
		else{//顶点信息重复
			cout<<"信息重复，请重新输入！"<<endl;
			redo=0;
			i--;
			continue;
		}
		
	}
	cout<<"2、输入边信息："<<endl;
	for(int k=1;k<=G.arcnum;k++){
		cout<<"输入第"<<k<<"条边的起始点:";
		cin>>v1;
		cout<<"输入第"<<k<<"条边的终止点:";
		cin>>v2;
		v1locate=LocateVex(G,v1);
		v2locate=LocateVex(G,v2);
		if(v1locate!=-1&&v2locate!=-1){//起始和终止点存在
		    p=new ArcNode;
		    p->adjvex=v2locate;
			p->nextarc=NULL;
			if(G.vertices[v1locate].FirstArc==NULL){
				G.vertices[v1locate].FirstArc=p;
			}
			else{
				for(end=G.vertices[v1locate].FirstArc;end->nextarc;end=end->nextarc);
				end->nextarc=p;
			}
			q=new ArcNode;
		    q->adjvex=v1locate;
		    q->nextarc=NULL;
			if(G.vertices[v2locate].FirstArc==NULL){
				G.vertices[v2locate].FirstArc=q;
			}
			else{
				for(end=G.vertices[v2locate].FirstArc;end->nextarc;end=end->nextarc);
				end->nextarc=q;
			}
		}
		else{//起始点或终止点不存在
		    cout<<"输入有误！请重新输入！"<<endl;
			k--;
			continue;
		}
	}
	cout<<"创建成功！"<<endl;
	cout<<"该无向图的邻接表如下:"<<endl;
	for(int i=0;i<G.vexnum;i++){
		cout<<"G->vertices["<<i<<"] = "<<G.vertices[i].data<<" : ";
		for(ArcNode *p=G.vertices[i].FirstArc;p;p=p->nextarc){
			cout<<p->adjvex<<" ";
		}
		cout<<endl;
	}
}

void DFS(ALGraph &G,int Place){//深度遍历某顶点开始的所有顶点
	G.vertices[Place].visited=1;
	cout<<G.vertices[Place].data<<" ";
	for(ArcNode *p=G.vertices[Place].FirstArc;p;p=p->nextarc){
		if(!G.vertices[p->adjvex].visited)
			DFS(G,p->adjvex);
	}
}
void DFStraverse(ALGraph G,int Place){//深度遍历，防止从某顶点开始遍历后有未遍历到的顶点
	DFS(G,Place);
	for(int i=0;i<G.vexnum;i++){
		if(!G.vertices[i].visited){
			DFS(G,i);
		}
	}
}

void BFS(ALGraph &G,int Place){//广度遍历某顶点开始的所有顶点
	int front;
	queue<int> ARC;
	G.vertices[Place].visited=1;
	cout<<G.vertices[Place].data<<" ";
	ARC.push(Place);
	while(!ARC.empty()){
		front=ARC.front();
		ARC.pop();
		for(ArcNode *p=G.vertices[front].FirstArc;p;p=p->nextarc){
			if(!G.vertices[p->adjvex].visited){
				G.vertices[p->adjvex].visited=1;
				cout<<G.vertices[p->adjvex].data<<" ";
				ARC.push(p->adjvex);
			}
		}
	}
}

void BFStraverse(ALGraph G,int Place){//广度遍历，防止从某顶点开始遍历后有未遍历到的顶点
	BFS(G,Place);
	for(int i=0;i<G.vexnum;i++){
		if(!G.vertices[i].visited){
			BFS(G,i);
		}
	}
}

void DFSside(ALGraph &G,int Place){//深度遍历某顶点开始的所有边
	G.vertices[Place].visited=1;
	for(ArcNode *p=G.vertices[Place].FirstArc;p;p=p->nextarc){
		if(!G.vertices[p->adjvex].visited)
			cout<<"("<<G.vertices[Place].data<<","<<G.vertices[p->adjvex].data<<") ";
		if(!G.vertices[p->adjvex].visited)
			DFSside(G,p->adjvex);
	}
}

void DFSsidetraverse(ALGraph G,int Place){//深度遍历边，防止从某顶点开始遍历后有未遍历到的顶点
	DFSside(G,Place);
	for(int i=0;i<G.vexnum;i++){
		if(!G.vertices[i].visited){
			DFSside(G,i);
		}
	}
}



void BFSside(ALGraph &G,int Place){//广度遍历某顶点开始的所有顶点
	int front;
	queue<int> ARC;
	G.vertices[Place].visited=1;
	ARC.push(Place);
	while(!ARC.empty()){
		front=ARC.front();
		ARC.pop();
		for(ArcNode *p=G.vertices[front].FirstArc;p;p=p->nextarc){
			if(!G.vertices[p->adjvex].visited){
				G.vertices[p->adjvex].visited=1;
				cout<<"("<<G.vertices[front].data<<","<<G.vertices[p->adjvex].data<<") ";
				ARC.push(p->adjvex);
			}
		}
	}
}

void BFSsidetraverse(ALGraph G,int Place){//广度遍历边，防止从某顶点开始遍历后有未遍历到的顶点
	BFSside(G,Place);
	for(int i=0;i<G.vexnum;i++){
		if(!G.vertices[i].visited){
			BFSside(G,i);
		}
	}
}

void main(){
	int FirstPlace;
	char FirstNodeData;
	ALGraph G;
	CreateMap(G);
	cout<<endl;
H:  cout<<"***开始遍历***"<<endl;
	cout<<"请输入开始遍历的顶点：";
	cin>>FirstNodeData;
	FirstPlace=LocateVex(G,FirstNodeData);
	if(FirstPlace!=-1){
	    cout<<"1、深度遍历顶点：";
	    DFStraverse(G,FirstPlace);
	    cout<<endl;
		cout<<"   深度遍历边：";
		DFSsidetraverse(G,FirstPlace);
	    cout<<endl;
	    cout<<"2、广度遍历顶点：";
	    BFStraverse(G,FirstPlace);
	    cout<<endl;
		cout<<"   广度遍历边：";
	    BFSsidetraverse(G,FirstPlace);
	    cout<<endl;
	    _getch();
	}
	else{//开始遍历的顶点不存在
		cout<<"输入有误！请重新输入！"<<endl;
	    goto H;
	}
}