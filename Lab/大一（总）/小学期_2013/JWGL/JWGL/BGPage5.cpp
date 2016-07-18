// BGPage5.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "BGPage5.h"
#include "afxdialogex.h"
#include "BGStat.h"


// BGPage5 对话框

IMPLEMENT_DYNAMIC(BGPage5, CPropertyPage)

BGPage5::BGPage5()
	: CPropertyPage(BGPage5::IDD)
	, m_BGStatType(_T(""))
{

}

BGPage5::~BGPage5()
{
}

void BGPage5::DoDataExchange(CDataExchange* pDX)
{
	CPropertyPage::DoDataExchange(pDX);
	DDX_CBString(pDX, IDC_COMBO1, m_BGStatType);
}


BEGIN_MESSAGE_MAP(BGPage5, CPropertyPage)
	ON_BN_CLICKED(IDC_BUTTON1, &BGPage5::OnBnClickedButton1)
	ON_WM_PAINT()
END_MESSAGE_MAP()


// BGPage5 消息处理程序


void BGPage5::OnBnClickedButton1()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	CBGStat bgs;
	if(_T("") == m_BGStatType){
	    MessageBox(_T("请选择统计类型！"));
	}
	else{
		if(_T("专业") == m_BGStatType){
			bgs.stattype = 1;
			bgs.DoModal();
		}
		else{
			if(_T("分段") == m_BGStatType){
			    bgs.stattype = 2;
			    bgs.DoModal();
			}
			else{
			    if(_T("性别") == m_BGStatType){
			        bgs.stattype = 3;
			        bgs.DoModal();
			    }
				else{
				     MessageBox(_T("请选择正确统计类型！"));
				}
			}
		}
	}

}


void BGPage5::OnPaint()
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
