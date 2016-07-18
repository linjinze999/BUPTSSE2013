// TeacherScoreModify.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "TeacherScoreModify.h"
#include "afxdialogex.h"
#include "ClassStudent.h"
#include "ClassStudent.h"


// CTeacherScoreModify 对话框

IMPLEMENT_DYNAMIC(CTeacherScoreModify, CDialogEx)

CTeacherScoreModify::CTeacherScoreModify(CWnd* pParent /*=NULL*/)
	: CDialogEx(CTeacherScoreModify::IDD, pParent)
	, m_TCScoreModifyID(_T(""))
	, m_TCScoreModifyScore(_T(""))
	, m_TCScoreModifyOld(_T(""))
{

}

CTeacherScoreModify::~CTeacherScoreModify()
{
}

void CTeacherScoreModify::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_TCScoreModifyID);
	DDX_Text(pDX, IDC_EDIT2, m_TCScoreModifyScore);
	DDX_Text(pDX, IDC_EDIT3, m_TCScoreModifyOld);
}


BEGIN_MESSAGE_MAP(CTeacherScoreModify, CDialogEx)
	ON_BN_CLICKED(IDOK, &CTeacherScoreModify::OnBnClickedOk)
	ON_EN_CHANGE(IDC_EDIT1, &CTeacherScoreModify::OnEnChangeEdit1)
END_MESSAGE_MAP()


// CTeacherScoreModify 消息处理程序


void CTeacherScoreModify::OnBnClickedOk()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	student adl;
	bool k;
	k = adl.serch(m_TCScoreModifyID);
	if(k){
		if(_T("")==m_TCScoreModifyScore){
		    MessageBox(_T("成绩不能为空！"));
		}
		else{
			adl.setscore(classname,adl.getname(),m_TCScoreModifyScore);
			MessageBox(_T("修改成功！"));
			CDialogEx::OnOK();
		}
	}
	else{
	    MessageBox(_T("查无此人！"));
	}
}


void CTeacherScoreModify::OnEnChangeEdit1()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CDialogEx::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码
	UpdateData(true);
	student ads;
	CString a;
	bool i;
	i = ads.serch(m_TCScoreModifyID);
	a = ads.getsdscore(classname,ads.getname());
	if(i){
	    SetDlgItemText(IDC_EDIT3, a);
	}
	else{
	    SetDlgItemText(IDC_EDIT3, _T(""));
	}
}
