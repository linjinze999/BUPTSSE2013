// BGSearchSDFuzzy.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "BGSearchSDFuzzy.h"
#include "afxdialogex.h"
#include "ClassStudent.h"


// CBGSearchSDFuzzy 对话框
IMPLEMENT_DYNAMIC(CBGSearchSDFuzzy, CDialogEx)

CBGSearchSDFuzzy::CBGSearchSDFuzzy(CWnd* pParent /*=NULL*/)
	: CDialogEx(CBGSearchSDFuzzy::IDD, pParent)
{

}

CBGSearchSDFuzzy::~CBGSearchSDFuzzy()
{
}

void CBGSearchSDFuzzy::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_LIST2, m_ctllistBGSearchSDFuzzy);
}


BEGIN_MESSAGE_MAP(CBGSearchSDFuzzy, CDialogEx)
END_MESSAGE_MAP()


// CBGSearchSDFuzzy 消息处理程序


BOOL CBGSearchSDFuzzy::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// TODO:  在此添加额外的初始化
	CRect rect;    
    // 获取编程语言列表视图控件的位置和大小    
     m_ctllistBGSearchSDFuzzy.GetClientRect(&rect);    
   
    // 为列表视图控件添加全行选中和栅格风格    
     m_ctllistBGSearchSDFuzzy.SetExtendedStyle(m_ctllistBGSearchSDFuzzy.GetExtendedStyle() | LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES);    
    
     // 为列表视图控件添加三列    
     m_ctllistBGSearchSDFuzzy.InsertColumn(0, _T("名字"), LVCFMT_CENTER, rect.Width()/7, 0);    
     m_ctllistBGSearchSDFuzzy.InsertColumn(1, _T("账号"), LVCFMT_CENTER, rect.Width()/7, 1);    
     m_ctllistBGSearchSDFuzzy.InsertColumn(2, _T("密码"), LVCFMT_CENTER, rect.Width()/7, 2);    
	 m_ctllistBGSearchSDFuzzy.InsertColumn(3, _T("性别"), LVCFMT_CENTER, rect.Width()/7, 3);  
	 m_ctllistBGSearchSDFuzzy.InsertColumn(4, _T("电话"), LVCFMT_CENTER, rect.Width()/7, 4);  
	 m_ctllistBGSearchSDFuzzy.InsertColumn(5, _T("专业"), LVCFMT_CENTER, rect.Width()/7, 5);  
	 m_ctllistBGSearchSDFuzzy.InsertColumn(6, _T("班级"), LVCFMT_CENTER, rect.Width()/7, 6);  
     // 在列表视图控件中插入列表项，并设置列表子项文本 
	 for(int i = 0;i<200;i++){
		 m_ctllistBGSearchSDFuzzy.InsertItem(i, searchsdfuzzy[i].getname());    
         m_ctllistBGSearchSDFuzzy.SetItemText(i, 1, searchsdfuzzy[i].getid());    
         m_ctllistBGSearchSDFuzzy.SetItemText(i, 2, searchsdfuzzy[i].getpassword());    
	     m_ctllistBGSearchSDFuzzy.SetItemText(i, 3, searchsdfuzzy[i].getsex()); 
	     m_ctllistBGSearchSDFuzzy.SetItemText(i, 4,searchsdfuzzy[i].gettelephone()); 
	     m_ctllistBGSearchSDFuzzy.SetItemText(i, 5, searchsdfuzzy[i].getmajor()); 
	     m_ctllistBGSearchSDFuzzy.SetItemText(i, 6, searchsdfuzzy[i].getnoclass()); 
	 if(_T("") == searchsdfuzzy[i].getid()){break;}
	 }
	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}
