/********************************************************************************
** Form generated from reading UI file 'Starcatalogue.ui'
**
** Created by: Qt User Interface Compiler version 5.11.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_STARCATALOGUE_H
#define UI_STARCATALOGUE_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_StarcatalogueClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *StarcatalogueClass)
    {
        if (StarcatalogueClass->objectName().isEmpty())
            StarcatalogueClass->setObjectName(QStringLiteral("StarcatalogueClass"));
        StarcatalogueClass->resize(600, 400);
        menuBar = new QMenuBar(StarcatalogueClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        StarcatalogueClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(StarcatalogueClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        StarcatalogueClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(StarcatalogueClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        StarcatalogueClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(StarcatalogueClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        StarcatalogueClass->setStatusBar(statusBar);

        retranslateUi(StarcatalogueClass);

        QMetaObject::connectSlotsByName(StarcatalogueClass);
    } // setupUi

    void retranslateUi(QMainWindow *StarcatalogueClass)
    {
        StarcatalogueClass->setWindowTitle(QApplication::translate("StarcatalogueClass", "Starcatalogue", nullptr));
    } // retranslateUi

};

namespace Ui {
    class StarcatalogueClass: public Ui_StarcatalogueClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_STARCATALOGUE_H
