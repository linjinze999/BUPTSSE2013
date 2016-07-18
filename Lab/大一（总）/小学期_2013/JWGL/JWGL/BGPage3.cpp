// BGPage3.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "BGPage3.h"
#include "afxdialogex.h"
#include "ClassStudent.h"
#include "ClassTeacher.h"


// BGPage3 对话框
int deletetype = 0;

IMPLEMENT_DYNAMIC(BGPage3, CPropertyPage)

BGPage3::BGPage3()
	: CPropertyPage(BGPage3::IDD)
	, m_BGDeleteTCID(_T(""))
	, m_BGDeleteUserBegin(0)
	, m_BGDeleteUserEnd(0)
{

}

BGPage3::~BGPage3()
{
}

void BGPage3::DoDataExchange(CDataExchange* pDX)
{
	CPropertyPage::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_BGDeleteTCID);
	DDX_Text(pDX, IDC_EDIT2, m_BGDeleteUserBegin);
	DDX_Text(pDX, IDC_EDIT3, m_BGDeleteUserEnd);
}


BEGIN_MESSAGE_MAP(BGPage3, CPropertyPage)
	ON_BN_CLICKED(IDC_RADIO1, &BGPage3::OnBnClickedRadio1)
	ON_EN_CHANGE(IDC_EDIT2, &BGPage3::OnEnChangeEdit2)
	ON_EN_CHANGE(IDC_EDIT3, &BGPage3::OnEnChangeEdit3)
	ON_BN_CLICKED(IDC_RADIO2, &BGPage3::OnBnClickedRadio2)
	ON_BN_CLICKED(IDC_BUTTON1, &BGPage3::OnBnClickedButton1)
	ON_BN_CLICKED(IDC_BUTTON2, &BGPage3::OnBnClickedButton2)
	ON_WM_PAINT()
END_MESSAGE_MAP()


// BGPage3 消息处理程序


void BGPage3::OnBnClickedRadio1()
{
	// TODO: 在此添加控件通知处理程序代码
	deletetype = 1;
}


void BGPage3::OnEnChangeEdit2()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CPropertyPage::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码
}


void BGPage3::OnEnChangeEdit3()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CPropertyPage::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码
}


void BGPage3::OnBnClickedRadio2()
{
	// TODO: 在此添加控件通知处理程序代码
	deletetype = 2;
}


void BGPage3::OnBnClickedButton1()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	teacher dlt;
	if(deletetype == 1){
		bool dele;
		dele = dlt.deleteteacher(m_BGDeleteTCID);
		if(dele){
		    MessageBox(_T("删除成功！"));
		}
		else{
		    MessageBox(_T("查无此人！"));
		}
	}
	else{
		if(deletetype == 2){
		    CString user;
			if(0 == m_BGDeleteUserBegin || 0 == m_BGDeleteUserEnd){
			    MessageBox(_T("请输入起始或终止值！"));
			}
			else{
				if( m_BGDeleteUserBegin>m_BGDeleteUserEnd){
				     MessageBox(_T("错误：起始值比终止值大！"));
				}
				else{
					for(long i = m_BGDeleteUserBegin;i <= m_BGDeleteUserEnd;i++){
						user.Format(_T("%ld"),i);
						dlt.deleteteacher(user);
					}
					MessageBox(_T("删除成功！"));
				}
			}
		}
		else{
		    MessageBox(_T("请选择删除类型！"));
		}
	}
}


void BGPage3::OnBnClickedButton2()
{
	// TODO: 在此添加控件通知处理程序代码UpdateData(true);
	UpdateData(true);
	student dlt;
	if(deletetype == 1){
		bool dele;
		dele = dlt.deletestudent(m_BGDeleteTCID);
		if(dele){
		    MessageBox(_T("删除成功！"));
		}
		else{
		    MessageBox(_T("查无此人！"));
		}
	}
	else{
		if(deletetype == 2){
		    CString user;
			if(0 == m_BGDeleteUserBegin || 0 == m_BGDeleteUserEnd){
			    MessageBox(_T("请输入起始或终止值！"));
			}
			else{
				if( m_BGDeleteUserBegin>m_BGDeleteUserEnd){
				     MessageBox(_T("错误：起始值比终止值大！"));
				}
				else{
					for(long i = m_BGDeleteUserBegin;i <= m_BGDeleteUserEnd;i++){
						user.Format(_T("%ld"),i);
						dlt.deletestudent(user);
					}
					MessageBox(_T("删除成功！"));
				}
			}
		}
		else{
		    MessageBox(_T("请选择删除类型！"));
		}
	}

}


void BGPage3::OnPaint()
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
