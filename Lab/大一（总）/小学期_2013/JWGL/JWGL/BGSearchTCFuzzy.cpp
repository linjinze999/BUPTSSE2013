// BGSearchTCFuzzy.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "BGSearchTCFuzzy.h"
#include "afxdialogex.h"


// CBGSearchTCFuzzy 对话框

IMPLEMENT_DYNAMIC(CBGSearchTCFuzzy, CDialogEx)

CBGSearchTCFuzzy::CBGSearchTCFuzzy(CWnd* pParent /*=NULL*/)
	: CDialogEx(CBGSearchTCFuzzy::IDD, pParent)
{

}

CBGSearchTCFuzzy::~CBGSearchTCFuzzy()
{
}

void CBGSearchTCFuzzy::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_LIST1, m_ctllistBGSearchTCfUZZY);
}


BEGIN_MESSAGE_MAP(CBGSearchTCFuzzy, CDialogEx)
END_MESSAGE_MAP()


// CBGSearchTCFuzzy 消息处理程序


BOOL CBGSearchTCFuzzy::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// TODO:  在此添加额外的初始化
	CRect rect;    
    // 获取编程语言列表视图控件的位置和大小    
     m_ctllistBGSearchTCfUZZY.GetClientRect(&rect);    
   
    // 为列表视图控件添加全行选中和栅格风格    
     m_ctllistBGSearchTCfUZZY.SetExtendedStyle(m_ctllistBGSearchTCfUZZY.GetExtendedStyle() | LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES);    
    
     // 为列表视图控件添加三列    
     m_ctllistBGSearchTCfUZZY.InsertColumn(0, _T("名字"), LVCFMT_CENTER, rect.Width()/5, 0);    
     m_ctllistBGSearchTCfUZZY.InsertColumn(1, _T("账号"), LVCFMT_CENTER, rect.Width()/5, 1);    
     m_ctllistBGSearchTCfUZZY.InsertColumn(2, _T("密码"), LVCFMT_CENTER, rect.Width()/5, 2);    
	 m_ctllistBGSearchTCfUZZY.InsertColumn(3, _T("性别"), LVCFMT_CENTER, rect.Width()/5, 3);  
	 m_ctllistBGSearchTCfUZZY.InsertColumn(4, _T("电话"), LVCFMT_CENTER, rect.Width()/5, 4);  
     // 在列表视图控件中插入列表项，并设置列表子项文本 
	 for(int i = 0;i<200;i++){
		 m_ctllistBGSearchTCfUZZY.InsertItem(i, searchtcfuzzy[i].getname());    
         m_ctllistBGSearchTCfUZZY.SetItemText(i, 1, searchtcfuzzy[i].getid());    
         m_ctllistBGSearchTCfUZZY.SetItemText(i, 2, searchtcfuzzy[i].getpassword());    
	     m_ctllistBGSearchTCfUZZY.SetItemText(i, 3, searchtcfuzzy[i].getsex()); 
	     m_ctllistBGSearchTCfUZZY.SetItemText(i, 4,searchtcfuzzy[i].gettelephone()); 
	     if(_T("") == searchtcfuzzy[i].getid()){break;}
	 }

	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}
