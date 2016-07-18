// TeacherPage1.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "TeacherPage1.h"
#include "afxdialogex.h"
#include "TeacherAddCourseWindow.h"
#include "ClassCourseAndMessage.h"


// TeacherPage1 对话框

IMPLEMENT_DYNAMIC(TeacherPage1, CPropertyPage)

TeacherPage1::TeacherPage1()
	: CPropertyPage(TeacherPage1::IDD)
	, m_TCCourseName(_T(""))
{

}

TeacherPage1::~TeacherPage1()
{
}

void TeacherPage1::DoDataExchange(CDataExchange* pDX)
{
	CPropertyPage::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_TCCourseName);
	DDX_Control(pDX, IDC_LIST1, m_TCCourseClassList);
}


BEGIN_MESSAGE_MAP(TeacherPage1, CPropertyPage)
	ON_BN_CLICKED(IDC_BUTTON1, &TeacherPage1::OnBnClickedButton1)
	ON_BN_CLICKED(IDC_BUTTON2, &TeacherPage1::OnBnClickedButton2)
	ON_NOTIFY(NM_CLICK, IDC_LIST1, &TeacherPage1::OnNMClickList1)
	ON_WM_PAINT()
END_MESSAGE_MAP()


// TeacherPage1 消息处理程序


void TeacherPage1::OnBnClickedButton1()//教师增加课程
{
	// TODO: 在此添加控件通知处理程序代码
	TeacherAddCourseWindow tacw;
	tacw.addcourse.setname(m_TCCourseName);
	tacw.DoModal();
}


void TeacherPage1::OnBnClickedButton2()//教师删除课程
{
	// TODO: 在此添加控件通知处理程序代码
	CourseAndMessage deleteclass;
	if(_T("") == tcdeleteclass){
	    MessageBox(_T("请选择课程！"));
	}
	else{
		if(_T("C++（必修）") == tcdeleteclass||_T("英语（必修）") == tcdeleteclass||_T("JAVA（必修）") == tcdeleteclass){
		    MessageBox(_T("必修课无法删除！"));
		}
		else{
		    deleteclass.TCDeleteCourse(tcdeleteclass,m_TCCourseName);
			MessageBox(_T("删除成功！重新登录可刷新数据。"));
		}
	}	
}


BOOL TeacherPage1::OnInitDialog()//输出教师拥有的课程
{
	CPropertyPage::OnInitDialog();

	// TODO:  在此添加额外的初始化
	CRect rect; 
	m_TCCourseClassList.GetClientRect(&rect);
	m_TCCourseClassList.SetExtendedStyle(m_TCCourseClassList.GetExtendedStyle() | LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES);    
	m_TCCourseClassList.InsertColumn(0, _T("课程名称："), LVCFMT_CENTER, rect.Width()/1, 0);
	CourseAndMessage getclassname;
	CString classname[20];
	getclassname.getyourcourse(classname,m_TCCourseName);
	for(int i = 0;i<20;i++){
		m_TCCourseClassList.InsertItem(i, classname[i]);   
		if(_T("") == classname[i]){
		    break;
		}
	}
	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}


void TeacherPage1::OnNMClickList1(NMHDR *pNMHDR, LRESULT *pResult)//获取用户对列表的选项
{
	LPNMITEMACTIVATE pNMItemActivate = reinterpret_cast<LPNMITEMACTIVATE>(pNMHDR);
	// TODO: 在此添加控件通知处理程序代码
	*pResult = 0;
	NMLISTVIEW *pNMListView = (NMLISTVIEW*)pNMHDR;
	if (-1 != pNMListView->iItem){
	    tcdeleteclass = m_TCCourseClassList.GetItemText(pNMListView->iItem, 0);
	}
}


void TeacherPage1::OnPaint()
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
