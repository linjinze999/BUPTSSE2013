// SDModifySDPassword.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "SDModifySDPassword.h"
#include "afxdialogex.h"
#include "ClassStudent.h"
#include "StudentPage1.h"

// CSDModifySDPassword 对话框

IMPLEMENT_DYNAMIC(CSDModifySDPassword, CDialogEx)

CSDModifySDPassword::CSDModifySDPassword(CWnd* pParent /*=NULL*/)
	: CDialogEx(CSDModifySDPassword::IDD, pParent)
	, m_SDIFMTOld(_T(""))
	, m_SDIFMTNew1(_T(""))
	, m_SDIFMTNew2(_T(""))
{

}

CSDModifySDPassword::~CSDModifySDPassword()
{
}

void CSDModifySDPassword::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_SDIFMTOld);
	DDX_Text(pDX, IDC_EDIT2, m_SDIFMTNew1);
	DDX_Text(pDX, IDC_EDIT3, m_SDIFMTNew2);
}


BEGIN_MESSAGE_MAP(CSDModifySDPassword, CDialogEx)
	ON_BN_CLICKED(IDOK, &CSDModifySDPassword::OnBnClickedOk)
END_MESSAGE_MAP()


// CSDModifySDPassword 消息处理程序


void CSDModifySDPassword::OnBnClickedOk()//学生修改密码
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(m_SDIFMTNew1 == m_SDIFMTNew2){
		bool text;
		text = modifypw.modifyinformation(modifypw.getid(),modifypw.getid(),m_SDIFMTNew2,modifypw.getname(),modifypw.getsex(),modifypw.gettelephone(),modifypw.getmajor(),modifypw.getnoclass());
	    if(text){
		    MessageBox(_T("修改成功！"));
		    CDialogEx::OnOK();
		}
		else{
			MessageBox(_T("修改出错！"));
		}
	}
	else{
	    MessageBox(_T("前后密码不一致！"));
	}
}
