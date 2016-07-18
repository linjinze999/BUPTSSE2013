// BGModifyTeacherInfoemation.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "BGModifyTeacherInfoemation.h"
#include "afxdialogex.h"


// CBGModifyTeacherInfoemation 对话框

IMPLEMENT_DYNAMIC(CBGModifyTeacherInfoemation, CDialogEx)

CBGModifyTeacherInfoemation::CBGModifyTeacherInfoemation(CWnd* pParent /*=NULL*/)
	: CDialogEx(CBGModifyTeacherInfoemation::IDD, pParent)
	, m_BGModifyTCID(_T(""))
	, m_BGModifyTCPassword(_T(""))
	, m_BGModifyTCName(_T(""))
	, m_BGModifyTCSex(_T(""))
	, m_BGModifyTCTelephone(_T(""))
{

}

CBGModifyTeacherInfoemation::~CBGModifyTeacherInfoemation()
{
}

void CBGModifyTeacherInfoemation::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_BGModifyTCID);
	DDX_Text(pDX, IDC_EDIT2, m_BGModifyTCPassword);
	DDX_Text(pDX, IDC_EDIT3, m_BGModifyTCName);
	DDX_CBString(pDX, IDC_COMBO1, m_BGModifyTCSex);
	DDX_Text(pDX, IDC_EDIT4, m_BGModifyTCTelephone);
}


BEGIN_MESSAGE_MAP(CBGModifyTeacherInfoemation, CDialogEx)
	ON_EN_CHANGE(IDC_EDIT3, &CBGModifyTeacherInfoemation::OnEnChangeEdit3)
	ON_BN_CLICKED(IDOK, &CBGModifyTeacherInfoemation::OnBnClickedOk)
END_MESSAGE_MAP()


// CBGModifyTeacherInfoemation 消息处理程序


void CBGModifyTeacherInfoemation::OnEnChangeEdit3()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CDialogEx::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码
}


void CBGModifyTeacherInfoemation::OnBnClickedOk()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	CString oldid;
	oldid = modifyinformation.getid();
	if(m_BGModifyTCID == _T("")){MessageBox(_T("账号不能为空！"));}
	else{
		if(m_BGModifyTCPassword == _T("")){MessageBox(_T("密码不能为空！"));}
		else{
			if(m_BGModifyTCName == _T("")){MessageBox(_T("名字不能为空！"));}
			else{
				if(m_BGModifyTCSex == _T("")){MessageBox(_T("性别不能为空！"));}
				else{
					if(m_BGModifyTCTelephone == _T("")){MessageBox(_T("电话不能为空！"));}
					else{
						modifyinformation.modifyinformation(oldid,m_BGModifyTCID,m_BGModifyTCPassword,m_BGModifyTCName,m_BGModifyTCSex,m_BGModifyTCTelephone);
						MessageBox(_T("修改成功！"));
						CDialogEx::OnOK();
					}
				}
			}
		}
	}
}
