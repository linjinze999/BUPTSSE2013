// StudentPage4.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "StudentPage4.h"
#include "afxdialogex.h"
#include "ClassCourseAndMessage.h"


// StudentPage4 对话框

IMPLEMENT_DYNAMIC(StudentPage4, CPropertyPage)

StudentPage4::StudentPage4()
	: CPropertyPage(StudentPage4::IDD)
	, m_SDMessageToTC(_T(""))
	, m_SDMessageCourse(_T(""))
{

}

StudentPage4::~StudentPage4()
{
}

void StudentPage4::DoDataExchange(CDataExchange* pDX)
{
	CPropertyPage::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_LIST1, m_SDMessageTC);
	DDX_Text(pDX, IDC_EDIT2, m_SDMessageToTC);
	DDX_CBString(pDX, IDC_COMBO1, m_SDMessageCourse);
	DDX_Control(pDX, IDC_COMBO1, m_SDMessageCourseName);
}


BEGIN_MESSAGE_MAP(StudentPage4, CPropertyPage)
	ON_BN_CLICKED(IDC_BUTTON1, &StudentPage4::OnBnClickedButton1)
	ON_WM_PAINT()
END_MESSAGE_MAP()


// StudentPage4 消息处理程序


BOOL StudentPage4::OnInitDialog()
{
	CPropertyPage::OnInitDialog();

	// TODO:  在此添加额外的初始化
	CRect rect;
	m_SDMessageTC.GetClientRect(&rect);
	m_SDMessageTC.SetExtendedStyle(m_SDMessageTC.GetExtendedStyle() | LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES);
	m_SDMessageTC.InsertColumn(0, _T("课程名称："), LVCFMT_CENTER, rect.Width()/2, 0);
	m_SDMessageTC.InsertColumn(1, _T("留言："), LVCFMT_CENTER, rect.Width()/2, 1);
	CString sdcoursename[20];
	CourseAndMessage Message;
	Message.getyourcourse(sdcoursename,sdname);
	for(int i = 0;i<20;i++){
		if(_T("") == sdcoursename[i]){break;}
		else{
		    m_SDMessageTC.InsertItem(i, sdcoursename[i]);
			m_SDMessageTC.SetItemText(i, 1,Message.GetScoreMessage(sdcoursename[i]));
		}
	}
	for(int i = 0;i<20;i++){
	    if(_T("") ==sdcoursename[i]){break;}
		else{
		    m_SDMessageCourseName.AddString(sdcoursename[i]);
		}
	}

	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}


void StudentPage4::OnBnClickedButton1()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(_T("") == m_SDMessageToTC){
		MessageBox(_T("请输入内容！"));
	}
	else{
		if(_T("") == m_SDMessageCourse){
		    MessageBox(_T("请选择课程！"));
		}
		else{
		    CourseAndMessage cam;
	        cam.StudentAddMessage(m_SDMessageCourse,m_SDMessageToTC);
	        MessageBox(_T("发送成功"));
		}
	}
	
}


void StudentPage4::OnPaint()
{
	CPaintDC dc(this); // device context for painting
	// TODO: 在此处添加消息处理程序代码
	CRect   rect;   
    GetClientRect(&rect);   
    CDC   dcMem;   
    dcMem.CreateCompatibleDC(&dc);   
    CBitmap   bmpBackground;   
    bmpBackground.LoadBitmap(IDB_BITMAP1);   
    BITMAP   bitmap;   
    bmpBackground.GetBitmap(&bitmap);   
    CBitmap   *pbmpOld=dcMem.SelectObject(&bmpBackground);   
    dc.StretchBlt(0,0,rect.Width(),rect.Height(),&dcMem,0,0,   
    bitmap.bmWidth,bitmap.bmHeight,SRCCOPY); 
	// 不为绘图消息调用 CPropertyPage::OnPaint()
}
