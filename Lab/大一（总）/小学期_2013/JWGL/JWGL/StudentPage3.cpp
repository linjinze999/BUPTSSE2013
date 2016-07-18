// StudentPage3.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "StudentPage3.h"
#include "afxdialogex.h"
#include "ClassCourseAndMessage.h"


// StudentPage3 对话框

IMPLEMENT_DYNAMIC(StudentPage3, CPropertyPage)

StudentPage3::StudentPage3()
	: CPropertyPage(StudentPage3::IDD)
{

}

StudentPage3::~StudentPage3()
{
}

void StudentPage3::DoDataExchange(CDataExchange* pDX)
{
	CPropertyPage::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_LIST1, m_SDSeeScore);
}


BEGIN_MESSAGE_MAP(StudentPage3, CPropertyPage)
END_MESSAGE_MAP()


// StudentPage3 消息处理程序



BOOL StudentPage3::OnInitDialog()
{
	CPropertyPage::OnInitDialog();

	// TODO:  在此添加额外的初始化
	CRect rect;
	m_SDSeeScore.GetClientRect(&rect);
	m_SDSeeScore.SetExtendedStyle(m_SDSeeScore.GetExtendedStyle() | LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES);
	m_SDSeeScore.InsertColumn(0, _T("课程名称："), LVCFMT_CENTER, rect.Width()/2, 0);
	m_SDSeeScore.InsertColumn(1, _T("成绩："), LVCFMT_CENTER, rect.Width()/2, 1);
	CString sdcoursename[20];
	CourseAndMessage getsdscore;
	getsdscore.getyourcourse(sdcoursename,studentname);
	for(int i = 0;i<20;i++){
		if(_T("") == sdcoursename[i]){break;}
		else{
		    m_SDSeeScore.InsertItem(i, sdcoursename[i]);
			m_SDSeeScore.SetItemText(i, 1,getsdscore.getsdscore(sdcoursename[i],studentname));
		}
	}

	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}
