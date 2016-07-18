// BGSeeStudentInformation.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "BGSeeStudentInformation.h"
#include "afxdialogex.h"


// CBGSeeStudentInformation 对话框

IMPLEMENT_DYNAMIC(CBGSeeStudentInformation, CDialogEx)

CBGSeeStudentInformation::CBGSeeStudentInformation(CWnd* pParent /*=NULL*/)
	: CDialogEx(CBGSeeStudentInformation::IDD, pParent)
	, m_BGSearchExactID(_T(""))
	, m_BGSearchExactPassword(_T(""))
	, m_BGSearchExactName(_T(""))
	, m_BGSearchExactSex(_T(""))
	, m_BGSearchExactTelephone(_T(""))
	, m_BGSearchExactMajor(_T(""))
	, m_BGSearchExactNoclass(_T(""))
{

}

CBGSeeStudentInformation::~CBGSeeStudentInformation()
{
}

void CBGSeeStudentInformation::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_BGSearchExactID);
	DDX_Text(pDX, IDC_EDIT2, m_BGSearchExactPassword);
	DDX_Text(pDX, IDC_EDIT3, m_BGSearchExactName);
	DDX_Text(pDX, IDC_EDIT4, m_BGSearchExactSex);
	DDX_Text(pDX, IDC_EDIT6, m_BGSearchExactTelephone);
	DDX_Text(pDX, IDC_EDIT5, m_BGSearchExactMajor);
	DDX_Text(pDX, IDC_EDIT7, m_BGSearchExactNoclass);
}


BEGIN_MESSAGE_MAP(CBGSeeStudentInformation, CDialogEx)
END_MESSAGE_MAP()


// CBGSeeStudentInformation 消息处理程序
