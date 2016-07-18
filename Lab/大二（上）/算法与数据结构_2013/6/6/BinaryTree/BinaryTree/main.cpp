#include<iostream>
using namespace std;

typedef  struct BiTNode{
    int data;
    struct BiTNode *lchild, *rchild;
} BiTNode, *BiTree;

int SearchNode(BiTree T,int key, int deep){//查找结点
	if (T==NULL){ 
		return 0; 
	}
	else {
		if (key==T->data){  
			return deep; 
		}
		else {
			if (key<T->data) 
				return SearchNode(T->lchild, key,deep+1);
			else  
				return SearchNode(T->rchild, key,deep+1);
		}
	}
}

bool InsertNode(BiTree &T,int key){//插入结点
	if(SearchNode(T,key,1)==0){
		if(T==NULL){
			T=(BiTree)malloc(sizeof(BiTNode));
			T->lchild=NULL;
			T->rchild=NULL;
			T->data=key;
			return 1;
		}
		else{
			if(key>T->data)
				return InsertNode(T->rchild,key);
			else
				return InsertNode(T->lchild,key);
		}
	}
	else{
		return 0;
	}
}

void PrintTree(BiTree Boot,int Layer){//打印凹入表
	int i;
	if(Boot==NULL) return;
	PrintTree(Boot->rchild,Layer+1);
	for(i=0;i<Layer;i++)
		cout<<"   ";
	cout<<Boot->data<<endl;
	PrintTree(Boot->lchild,Layer+1);
}

void CreateBiTree(BiTree &T){
	int number;
	int key;
	cout<<"请输入结点数：";
	cin>>number;
	for(int n=1;n<=number;n++){
		cout<<"请输入第"<<n<<"个结点的值:";
		cin>>key;
		if(!InsertNode(T,key)){
			cout<<"数据重复，请重新输入"<<endl;
			n--;
			continue;
		}
	}
	cout<<"创建成功！"<<endl;
	cout<<"凹入表如下："<<endl;
	PrintTree(T,0);
}

bool DeleteNode(BiTree &T,int key){//删除结点
	if(SearchNode(T,key,1)==0)
		return 0;
	else{
		if(T->data==key){
			BiTree s,q;
			if(T->rchild==NULL){//右子树为空
				q=T; T=T->lchild; free(q);
			}
			else if(T->lchild==NULL){//左子树为空
				q=T; T=T->rchild; free(q);
			}
			else{//左右子树不为空
				q=T; s=T->lchild;
				while(s->rchild){
					q=s;
					s=s->rchild;
				}
				T->data=s->data;
				if(q!=T)
					q->rchild=s->lchild; 
				else
					q->lchild=s->lchild;
				free(s);
			}
			return 1;
		}
		else if(key>T->data)
			return DeleteNode(T->rchild,key);
		else
			return DeleteNode(T->lchild,key);
	}
}

void InOrderTraverse(BiTree T){//中序遍历
	if(T){
		InOrderTraverse(T->lchild);
		cout<<T->data<<' ';
		InOrderTraverse(T->rchild);
	}
}

void main(){
	int menu=0;
	BiTree T=NULL;
	int key=0;
	int deep=0;
	bool t;
	while(true){
		cout<<"***********************"<<endl;
		cout<<"       二叉排序树      "<<endl;
		cout<<"***********************"<<endl;
		cout<<"1、创建二叉排序树；"<<endl;
		cout<<"2、遍历二叉排序树；"<<endl;
		cout<<"3、查找结点；"<<endl;
		cout<<"4、插入结点；"<<endl;
		cout<<"5、删除结点；"<<endl;
		cout<<"6、打印凹入表；"<<endl;
		cout<<"***********************"<<endl;
		cout<<"请选择：";
		cin>>menu;
		switch(menu){
		case 1:
			CreateBiTree(T);
			cout<<endl;
			break;
		case 2:
			cout<<"中序遍历结果：";
			InOrderTraverse(T);
			cout<<endl;
			cout<<endl;
			break;
		case 3:
			cout<<"请输入查找的结点值：";
			cin>>key;
			deep=SearchNode(T,key,1);
			if(deep==0)
				cout<<"不存在该结点！"<<endl;
			else
				cout<<"该结点的深度为："<<deep<<endl;
			cout<<endl;
			break;
		case 4:
			cout<<"请输入插入的结点值：";
			cin>>key;
			t=InsertNode(T,key);
			if(t)
				cout<<"插入成功！"<<endl;
			else
				cout<<"数据重复！插入失败！"<<endl;
			cout<<endl;
			break;
		case 5:
			cout<<"请输入要删除的结点值：";
			cin>>key;
			t=DeleteNode(T,key);
			if(t)
				cout<<"删除成功！"<<endl;
			else
				cout<<"删除失败！未找到该值！"<<endl;
			cout<<endl;
			break;
		case 6:
			if(T==NULL)
				cout<<"打印失败！未创建二叉排序树！"<<endl;
			else{
				cout<<"凹入表如下："<<endl;
				PrintTree(T,0);
			}
			cout<<endl;
			break;
		default:
			break;
		}
	}
}