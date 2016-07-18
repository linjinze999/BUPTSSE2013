// StudentPage2.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "StudentPage2.h"
#include "afxdialogex.h"
#include "ClassCourseAndMessage.h"


// StudentPage2 对话框

IMPLEMENT_DYNAMIC(StudentPage2, CPropertyPage)

StudentPage2::StudentPage2()
	: CPropertyPage(StudentPage2::IDD)
{

}

StudentPage2::~StudentPage2()
{
}

void StudentPage2::DoDataExchange(CDataExchange* pDX)
{
	CPropertyPage::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_LIST2, m_SDDropCourse);
	DDX_Control(pDX, IDC_LIST1, m_SDChooseCourse);
}


BEGIN_MESSAGE_MAP(StudentPage2, CPropertyPage)
	ON_BN_CLICKED(IDC_BUTTON1, &StudentPage2::OnBnClickedButton1)
	ON_BN_CLICKED(IDC_BUTTON2, &StudentPage2::OnBnClickedButton2)
	ON_NOTIFY(NM_CLICK, IDC_LIST1, &StudentPage2::OnNMClickList1)
	ON_NOTIFY(NM_CLICK, IDC_LIST2, &StudentPage2::OnNMClickList2)
	ON_WM_PAINT()
END_MESSAGE_MAP()


// StudentPage2 消息处理程序


void StudentPage2::OnBnClickedButton1()//学生选课
{
	// TODO: 在此添加控件通知处理程序代码
	CourseAndMessage chooseclass;
	if(_T("") == chooseclassname){
	    MessageBox(_T("请选择课程！"));
	}
	else{
		CString a[20];
		int c=0;
		chooseclass.getyourcourse(a,sdname);
		for(int b=0;b<20;b++){
			if(_T("") == a[b]){break;}
			else{
				if(chooseclassname == a[b]){
					c = 1;
					break;
				}
			}
		}
		if(c == 1){
		    MessageBox(_T("您已经拥有该课程！"));
		}
		else{
	        chooseclass.SDChooseCourse(chooseclassname,sdname);
		    MessageBox(_T("选课成功！重新登录可刷新数据"));
		}
	}
}


void StudentPage2::OnBnClickedButton2()//学生退课
{
	// TODO: 在此添加控件通知处理程序代码
	CourseAndMessage dropclass;
	if(_T("") == dropclassname){
	    MessageBox(_T("请选择课程！"));
	}
	else{
		if(_T("C++（必修）") == dropclassname || _T("英语（必修）") == dropclassname || _T("JAVA（必修）") == dropclassname){
		    MessageBox(_T("您选择的是必修课，无法退课！"));
		}
		else{
		    dropclass.SDDeleteCourse(dropclassname,sdname);
		    MessageBox(_T("退课成功！重新登录可刷新数据。"));
		}
	}
}


BOOL StudentPage2::OnInitDialog()
{
	CPropertyPage::OnInitDialog();

	// TODO:  在此添加额外的初始化
	CRect rect; 
	m_SDChooseCourse.GetClientRect(&rect);
	m_SDChooseCourse.SetExtendedStyle(m_SDChooseCourse.GetExtendedStyle() | LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES);    
	m_SDChooseCourse.InsertColumn(0, _T("课程名称："), LVCFMT_CENTER, rect.Width()/1, 0);
	CourseAndMessage getallclassname;
	CString classname[50];
	getallclassname.getallcourse(classname);
	for(int i = 0;i<20;i++){ 
		if(_T("") == classname[i]){
		    break;
		}
		else{
		    m_SDChooseCourse.InsertItem(i, classname[i]);
		}
	}
	CRect rrect;
	m_SDDropCourse.GetClientRect(&rrect);
	m_SDDropCourse.SetExtendedStyle(m_SDDropCourse.GetExtendedStyle() | LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES);    
	m_SDDropCourse.InsertColumn(0, _T("课程名称："), LVCFMT_CENTER, rrect.Width()/1, 0);
	CourseAndMessage getsdclassname;
	CString sdclassname[50];
	getsdclassname.getyourcourse(sdclassname,sdname);
	for(int i = 0;i<20;i++){
		if(_T("") == sdclassname[i]){
		    break;
		}
		else{
		    m_SDDropCourse.InsertItem(i, sdclassname[i]);
		}
	}

	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}


void StudentPage2::OnNMClickList1(NMHDR *pNMHDR, LRESULT *pResult)//获取学生选课名字
{
	LPNMITEMACTIVATE pNMItemActivate = reinterpret_cast<LPNMITEMACTIVATE>(pNMHDR);
	// TODO: 在此添加控件通知处理程序代码
	*pResult = 0;
	NMLISTVIEW *pNMListView = (NMLISTVIEW*)pNMHDR;
	if (-1 != pNMListView->iItem){
	    chooseclassname = m_SDChooseCourse.GetItemText(pNMListView->iItem, 0);
	}
}


void StudentPage2::OnNMClickList2(NMHDR *pNMHDR, LRESULT *pResult)//获取学生退课名字
{
	LPNMITEMACTIVATE pNMItemActivate = reinterpret_cast<LPNMITEMACTIVATE>(pNMHDR);
	// TODO: 在此添加控件通知处理程序代码
	*pResult = 0;
	NMLISTVIEW *pNMListView = (NMLISTVIEW*)pNMHDR;
	if (-1 != pNMListView->iItem){
	    dropclassname = m_SDDropCourse.GetItemText(pNMListView->iItem, 0);
	}
}


void StudentPage2::OnPaint()
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
