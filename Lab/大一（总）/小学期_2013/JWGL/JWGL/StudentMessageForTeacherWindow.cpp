// StudentMessageForTeacherWindow.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "StudentMessageForTeacherWindow.h"
#include "afxdialogex.h"
#include "ClassCourseAndMessage.h"


// StudentMessageForTeacherWindow 对话框

IMPLEMENT_DYNAMIC(StudentMessageForTeacherWindow, CDialogEx)

StudentMessageForTeacherWindow::StudentMessageForTeacherWindow(CWnd* pParent /*=NULL*/)
	: CDialogEx(StudentMessageForTeacherWindow::IDD, pParent)
{

}

StudentMessageForTeacherWindow::~StudentMessageForTeacherWindow()
{
}

void StudentMessageForTeacherWindow::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_LIST1, m_ctllistSDMessageForTC);
}


BEGIN_MESSAGE_MAP(StudentMessageForTeacherWindow, CDialogEx)
END_MESSAGE_MAP()


// StudentMessageForTeacherWindow 消息处理程序


BOOL StudentMessageForTeacherWindow::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// TODO:  在此添加额外的初始化
	CRect rect;    
    // 获取编程语言列表视图控件的位置和大小    
    m_ctllistSDMessageForTC.GetClientRect(&rect);    
   
    // 为列表视图控件添加全行选中和栅格风格    
     m_ctllistSDMessageForTC.SetExtendedStyle(m_ctllistSDMessageForTC.GetExtendedStyle() | LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES);    
    
     // 为列表视图控件添加三列    
     m_ctllistSDMessageForTC.InsertColumn(0, _T("课程名称"), LVCFMT_CENTER, rect.Width()/2, 0);    
     m_ctllistSDMessageForTC.InsertColumn(1, _T("留言信息"), LVCFMT_CENTER, rect.Width()/2, 1);       
     // 在列表视图控件中插入列表项，并设置列表子项文本  
	 CourseAndMessage tcgetmessage;
	 CString coursename[20];
	 tcgetmessage.getyourcourse(coursename,tcname);
	 int hang=0;
	 for(int a = 0;a<20;a++){
		 if(_T("") == coursename[a]){break;}
		 else{
	         CString tcmessage[200];
	         tcgetmessage.TCGetSDMessage(tcmessage,coursename[a]);
			 for(int i=0;i<200;i++){
				 if(_T("") == tcmessage[i]){break;}
				 else{
                     m_ctllistSDMessageForTC.InsertItem(hang, coursename[a]);
                     m_ctllistSDMessageForTC.SetItemText(hang, 1,tcmessage[i]);
					 hang++;
				 }
			 }
		 }
	 }

	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}
