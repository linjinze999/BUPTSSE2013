// BGAddStudentUser.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "BGAddStudentUser.h"
#include "afxdialogex.h"
#include "ClassStudent.h"


// CBGAddStudentUser 对话框

IMPLEMENT_DYNAMIC(CBGAddStudentUser, CDialogEx)

CBGAddStudentUser::CBGAddStudentUser(CWnd* pParent /*=NULL*/)
	: CDialogEx(CBGAddStudentUser::IDD, pParent)
	, m_AddSDID(_T(""))
	, m_AddSDPassword(_T(""))
	, m_AddSDName(_T(""))
	, m_AddSDSex(_T(""))
	, m_AddSDTelephone(_T(""))
	, m_AddSDMajor(_T(""))
	, m_AddSDNoclasses(_T(""))
{

}

CBGAddStudentUser::~CBGAddStudentUser()
{
}

void CBGAddStudentUser::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_AddSDID);
	DDX_Text(pDX, IDC_EDIT2, m_AddSDPassword);
	DDX_Text(pDX, IDC_EDIT3, m_AddSDName);
	DDX_CBString(pDX, IDC_COMBO1, m_AddSDSex);
	DDX_Text(pDX, IDC_EDIT5, m_AddSDTelephone);
	DDX_CBString(pDX, IDC_COMBO2, m_AddSDMajor);
	DDX_CBString(pDX, IDC_COMBO3, m_AddSDNoclasses);
}


BEGIN_MESSAGE_MAP(CBGAddStudentUser, CDialogEx)
	ON_BN_CLICKED(IDOK, &CBGAddStudentUser::OnBnClickedOk)
	ON_BN_CLICKED(IDC_BUTTON1, &CBGAddStudentUser::OnBnClickedButton1)
	ON_EN_CHANGE(IDC_EDIT2, &CBGAddStudentUser::OnEnChangeEdit2)
	ON_CBN_SELCHANGE(IDC_COMBO3, &CBGAddStudentUser::OnCbnSelchangeCombo3)
END_MESSAGE_MAP()


// CBGAddStudentUser 消息处理程序


void CBGAddStudentUser::OnBnClickedOk()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(TRUE);
	if(m_AddSDID == _T("")){ 
		MessageBox(_T("请输入账号！"));
	}
	else{
		if(m_AddSDPassword == _T("")){
			MessageBox(_T("请输入密码！"));
		}
		else{
			if(m_AddSDName == _T("")){
			    MessageBox(_T("请输入名字！"));
			}
			else{
				if(m_AddSDSex == _T("")){
			        MessageBox(_T("请选择性别！"));
			    }
				else{
					if(m_AddSDTelephone == _T("")){
					     MessageBox(_T("请输入电话！"));
					}
					else{
						if(m_AddSDMajor == _T("")){
							MessageBox(_T("请选择专业！"));
						}
						else{
							if(m_AddSDMajor == _T("")){
							    MessageBox(_T("请选择班级！"));
						    }
							else{
	                            student no1;
	                            no1.setinformation(m_AddSDID,m_AddSDPassword,m_AddSDName,m_AddSDTelephone,m_AddSDSex,m_AddSDMajor,m_AddSDNoclasses);
	                            no1.out();
						        MessageBox(_T("增加用户成功！"));
						        CDialogEx::OnOK();
							}
						}
					}
				}
			}
	    }
	}
	UpdateData(FALSE);
	
}


void CBGAddStudentUser::OnBnClickedButton1()
{
	// TODO: 在此添加控件通知处理程序代码
	CBGAddStudentUser::OnBnClickedOk();
	CBGAddStudentUser sdu;
	sdu.DoModal();
}


void CBGAddStudentUser::OnEnChangeEdit2()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CDialogEx::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码
}


void CBGAddStudentUser::OnCbnSelchangeCombo3()
{
	// TODO: 在此添加控件通知处理程序代码
}
