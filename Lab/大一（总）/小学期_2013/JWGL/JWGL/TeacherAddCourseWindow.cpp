// TeacherAddCourseWindow.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "TeacherAddCourseWindow.h"
#include "afxdialogex.h"


// TeacherAddCourseWindow 对话框

IMPLEMENT_DYNAMIC(TeacherAddCourseWindow, CDialogEx)

TeacherAddCourseWindow::TeacherAddCourseWindow(CWnd* pParent /*=NULL*/)
	: CDialogEx(TeacherAddCourseWindow::IDD, pParent)
	, m_TCAddCourseClassname(_T(""))
	, m_TCAddCourseClassMessage(_T(""))
{

}

TeacherAddCourseWindow::~TeacherAddCourseWindow()
{
}

void TeacherAddCourseWindow::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_TCAddCourseClassname);
	DDX_Text(pDX, IDC_EDIT2, m_TCAddCourseClassMessage);
}


BEGIN_MESSAGE_MAP(TeacherAddCourseWindow, CDialogEx)
	ON_EN_CHANGE(IDC_EDIT2, &TeacherAddCourseWindow::OnEnChangeEdit2)
	ON_BN_CLICKED(IDOK, &TeacherAddCourseWindow::OnBnClickedOk)
END_MESSAGE_MAP()


// TeacherAddCourseWindow 消息处理程序


void TeacherAddCourseWindow::OnEnChangeEdit2()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CDialogEx::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码
}


void TeacherAddCourseWindow::OnBnClickedOk()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(_T("") == m_TCAddCourseClassname){
	    MessageBox(_T("课程名字不能为空！"));
	}
	else{
		addcourse.TCAddCourse(m_TCAddCourseClassname,m_TCAddCourseClassMessage,addcourse.name);
		MessageBox(_T("增加成功！重新登录将刷新数据！"));
		CDialogEx::OnOK();
	}
}
