// TeacherMessageForStudentWindow.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "TeacherMessageForStudentWindow.h"
#include "afxdialogex.h"
#include "ClassTeacher.h"
#include "ClassCourseAndMessage.h"


// TeacherMessageForStudentWindow 对话框

IMPLEMENT_DYNAMIC(TeacherMessageForStudentWindow, CDialogEx)

TeacherMessageForStudentWindow::TeacherMessageForStudentWindow(CWnd* pParent /*=NULL*/)
	: CDialogEx(TeacherMessageForStudentWindow::IDD, pParent)
	, m_TCMessageForSD(_T(""))
	, m_TCMessageClassName(_T(""))
	, m_TCMessageMessage(_T(""))
{

}

TeacherMessageForStudentWindow::~TeacherMessageForStudentWindow()
{
}

void TeacherMessageForStudentWindow::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT2, m_TCMessageForSD);
	DDX_CBString(pDX, IDC_COMBO1, m_TCMessageClassName);
	DDX_Text(pDX, IDC_EDIT1, m_TCMessageMessage);
	DDX_Control(pDX, IDC_COMBO1, m_TCMessageCourseName);
}


BEGIN_MESSAGE_MAP(TeacherMessageForStudentWindow, CDialogEx)
	ON_BN_CLICKED(IDOK, &TeacherMessageForStudentWindow::OnBnClickedOk)
END_MESSAGE_MAP()


// TeacherMessageForStudentWindow 消息处理程序


void TeacherMessageForStudentWindow::OnBnClickedOk()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	teacher modifymessage;
	if(_T("")==m_TCMessageClassName){
	    MessageBox(_T("请选择课程！"));
	}
	else{
		if(m_TCMessageMessage == _T("")){
			modifymessage.TCMessageForStudent(m_TCMessageClassName,_T("0"));
			MessageBox(_T("留言为空，默认清空留言！"));
			CDialogEx::OnOK();
		}
		else{
			modifymessage.TCMessageForStudent(m_TCMessageClassName,m_TCMessageMessage);
			MessageBox(_T("留言成功！"));
			CDialogEx::OnOK();
		}
	}
}


BOOL TeacherMessageForStudentWindow::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// TODO:  在此添加额外的初始化
	CourseAndMessage gettccoursename;
	CString CourseName[20];
	gettccoursename.getyourcourse(CourseName,m_TCMessageForSD);
	for(int i = 0;i<20;i++){
		if(_T("") == CourseName[i]){break;}
		else{
			m_TCMessageCourseName.AddString(CourseName[i]);
		}
	}

	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}
