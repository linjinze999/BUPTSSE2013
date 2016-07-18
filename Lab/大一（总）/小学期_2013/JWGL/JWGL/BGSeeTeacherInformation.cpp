// BGSeeTeacherInformation.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "BGSeeTeacherInformation.h"
#include "afxdialogex.h"


// CBGSeeTeacherInformation 对话框

IMPLEMENT_DYNAMIC(CBGSeeTeacherInformation, CDialogEx)

CBGSeeTeacherInformation::CBGSeeTeacherInformation(CWnd* pParent /*=NULL*/)
	: CDialogEx(CBGSeeTeacherInformation::IDD, pParent)
	, m_BGSearchTeacherExactID(_T(""))
	, m_BGSearchTeacherExactPassword(_T(""))
	, m_BGSearchTeacherExactName(_T(""))
	, m_BGSearchTeacherExactSex(_T(""))
	, m_BGSearchTeacherExactTelephone(_T(""))
{

}

CBGSeeTeacherInformation::~CBGSeeTeacherInformation()
{
}

void CBGSeeTeacherInformation::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_BGSearchTeacherExactID);
	DDX_Text(pDX, IDC_EDIT2, m_BGSearchTeacherExactPassword);
	DDX_Text(pDX, IDC_EDIT3, m_BGSearchTeacherExactName);
	DDX_Text(pDX, IDC_EDIT4, m_BGSearchTeacherExactSex);
	DDX_Text(pDX, IDC_EDIT5, m_BGSearchTeacherExactTelephone);
}


BEGIN_MESSAGE_MAP(CBGSeeTeacherInformation, CDialogEx)
	ON_EN_CHANGE(IDC_EDIT4, &CBGSeeTeacherInformation::OnEnChangeEdit4)
END_MESSAGE_MAP()


// CBGSeeTeacherInformation 消息处理程序


void CBGSeeTeacherInformation::OnEnChangeEdit4()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CDialogEx::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码
}
