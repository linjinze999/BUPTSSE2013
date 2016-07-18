#include<iostream>
#include<conio.h>
#include<stack>
using namespace std;

typedef struct BTnode{//二叉树结点
	char data;
	struct BTnode *lchild,*rchild;
}BTnode,*BiTree;

BiTree CreateBiTree(){//创建二叉树
	char ch;
	BiTree T;
	cin>>ch;
	if(ch=='#') T=NULL;
	else{
		T = (BiTree)malloc(sizeof(BTnode));
		T->data = ch;
		T->lchild = CreateBiTree();
		T->rchild = CreateBiTree();
	}
	return T;
}

void PreOrderTraverse(BiTree T){//递归先序遍历
	if(T){
		cout<<T->data<<' ';
		PreOrderTraverse(T->lchild);
		PreOrderTraverse(T->rchild);
	}
}

void InOrderTraverse(BiTree T){//递归中序遍历
	if(T){
		InOrderTraverse(T->lchild);
		cout<<T->data<<' ';
		InOrderTraverse(T->rchild);
	}
}

void PostOrderTraverse(BiTree T){//递归后序遍历
	if(T){
		PostOrderTraverse(T->lchild);
		PostOrderTraverse(T->rchild);
		cout<<T->data<<' ';
	}
}

void PreOrderTraverse2(BiTree T) //非递归前序遍历 
{
    stack<BiTree> s;
    while(T!=NULL||!s.empty())
    {
        while(T!=NULL)
        {
            cout<<T->data<<" ";
            s.push(T);
            T=T->lchild;
        }
        if(!s.empty())
        {
            T=s.top();
            s.pop();
            T=T->rchild;
        }
    }
}

void InOrderTraverse2(BiTree T)//非递归中序遍历
{
    stack<BiTree> s;
    while(T!=NULL||!s.empty())
    {
        while(T!=NULL)
        {
            s.push(T);
            T=T->lchild;
        }
        if(!s.empty())
        {
            T=s.top();
            cout<<T->data<<" ";
            s.pop();
            T=T->rchild;
        }
    }    
} 

void PostOrderTraverse2(BiTree T)//非递归后序遍历
{
    stack<BiTree> s;
    BiTree cur;                      //当前结点 
    BiTree pre=NULL;                 //前一次访问的结点 
    s.push(T);
    while(!s.empty())
    {
        cur=s.top();
        if((cur->lchild==NULL && cur->rchild==NULL) ||
           (pre!=NULL && (pre==cur->lchild || pre==cur->rchild)))
        {
            cout<<cur->data<<" ";  //如果当前结点没有孩子结点或者孩子节点都已被访问过 
            s.pop();
            pre=cur; 
        }
        else
        {
            if(cur->rchild!=NULL)
                s.push(cur->rchild);
            if(cur->lchild!=NULL)    
                s.push(cur->lchild);
        }
    }    
}

void PrintTree(BiTree Boot,int Layer)//按竖向树状打印的二叉树结构
{
	int i;
	if(Boot==NULL) return;
	PrintTree(Boot->rchild,Layer+1);
	for(i=0;i<Layer;i++)
		cout<<"   ";
	cout<<Boot->data<<endl;
	PrintTree(Boot->lchild,Layer+1);
}

bool isfull(BiTree T){
	if(T->lchild&&T->rchild){
		if(!isfull(T->lchild)) return 0;
		if(!isfull(T->rchild)) return 0;
		return 1;
	}
	else{
		if(T->lchild==NULL&&T->rchild==NULL) return 1;
		else return 0;
	}

}
void main(){
	BiTree T;
	int layer = 0;
	cout<<"请输入二叉树元素，“#”表示空子树：";
	T = CreateBiTree();
	cout<<"\n一、递归遍历："<<endl;
	cout<<"  1、先序遍历序列：";
	PreOrderTraverse(T);
	cout<<endl;
	cout<<"  2、中序遍历序列：";
	InOrderTraverse(T);
	cout<<endl;
	cout<<"  3、后序遍历序列：";
	PostOrderTraverse(T);
	cout<<endl<<endl;
	cout<<"二、非递归遍历："<<endl;
	cout<<"  1、先序遍历序列：";
	PreOrderTraverse2(T);
	cout<<endl;
	cout<<"  2、中序遍历序列：";
	InOrderTraverse2(T);
	cout<<endl;
	cout<<"  3、后序遍历序列：";
	PostOrderTraverse2(T);
	cout<<endl<<endl;
	cout<<"三、二叉树结构如下:"<<endl<<endl;
	PrintTree(T,layer);
	cout<<"四、是否满二叉树：";
	if(isfull(T))
		cout<<"是";
	else 
		cout<<"否";
	_getch();
}