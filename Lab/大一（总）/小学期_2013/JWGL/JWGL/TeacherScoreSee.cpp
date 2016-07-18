// TeacherScoreSee.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "TeacherScoreSee.h"
#include "afxdialogex.h"
#include "ClassCourseAndMessage.h"


// CTeacherScoreSee 对话框
int bujige=0,jige =0,ads;

IMPLEMENT_DYNAMIC(CTeacherScoreSee, CDialogEx)

CTeacherScoreSee::CTeacherScoreSee(CWnd* pParent /*=NULL*/)
	: CDialogEx(CTeacherScoreSee::IDD, pParent)
{

}

CTeacherScoreSee::~CTeacherScoreSee()
{
}

void CTeacherScoreSee::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_LIST1, m_TCScoreSeeCtllist);
}


BEGIN_MESSAGE_MAP(CTeacherScoreSee, CDialogEx)
	ON_BN_CLICKED(IDC_BUTTON1, &CTeacherScoreSee::OnBnClickedButton1)
END_MESSAGE_MAP()


// CTeacherScoreSee 消息处理程序


BOOL CTeacherScoreSee::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// TODO:  在此添加额外的初始化
	CRect rect;
	m_TCScoreSeeCtllist.GetClientRect(&rect);
	m_TCScoreSeeCtllist.SetExtendedStyle(m_TCScoreSeeCtllist.GetExtendedStyle() | LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES);  
	m_TCScoreSeeCtllist.InsertColumn(0, _T("学生姓名："), LVCFMT_CENTER, rect.Width()/2, 0);
	m_TCScoreSeeCtllist.InsertColumn(1, _T("成绩："), LVCFMT_CENTER, rect.Width()/2, 1);
	CourseAndMessage sdl;
	CString sdnm[200],a;
	sdl.GetTheCourseStudent(sdnm,classname);
	for(int i = 0;i<20;i++){
		if(_T("") == sdnm[i]){
		    break;
		}
		else{
			a=sdl.getsdscore(classname,sdnm[i]);
		    m_TCScoreSeeCtllist.InsertItem(i,sdnm[i]);
			m_TCScoreSeeCtllist.SetItemText(i, 1,a);   
			ads = _ttoi(a);
			if(ads>=60){jige++;}
			else{bujige++;}
		}
	}
	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}


void CTeacherScoreSee::OnBnClickedButton1()
{
	// TODO: 在此添加控件通知处理程序代码
	CString statistics;
	statistics.Format(_T("总人数：%d；及格人数：%d；不及格人数：%d"),bujige+jige,jige,bujige);
	MessageBox(statistics);
}
