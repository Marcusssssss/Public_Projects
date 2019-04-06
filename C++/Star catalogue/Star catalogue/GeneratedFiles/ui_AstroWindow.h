/********************************************************************************
** Form generated from reading UI file 'AstroWindow.ui'
**
** Created by: Qt User Interface Compiler version 5.11.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_ASTROWINDOW_H
#define UI_ASTROWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QCheckBox>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QTableView>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_AstroWindow
{
public:
    QTableView *tableView;
    QCheckBox *checkBox;
    QLabel *label;
    QLineEdit *nameLineEdit;
    QLineEdit *luminosityLineEdit;
    QLineEdit *temperatureLineEdit;
    QLabel *label_2;
    QLabel *label_3;
    QLabel *label_4;
    QPushButton *addPushButton;
    QLabel *label_5;
    QLabel *label_6;
    QLineEdit *searchNameLineEdit;
    QLineEdit *searchTempLineEdit;
    QLabel *label_7;
    QListWidget *listWidget;
    QPushButton *savePushButton;
    QLabel *label_8;

    void setupUi(QWidget *AstroWindow)
    {
        if (AstroWindow->objectName().isEmpty())
            AstroWindow->setObjectName(QStringLiteral("AstroWindow"));
        AstroWindow->resize(885, 723);
        tableView = new QTableView(AstroWindow);
        tableView->setObjectName(QStringLiteral("tableView"));
        tableView->setGeometry(QRect(20, 30, 571, 431));
        checkBox = new QCheckBox(AstroWindow);
        checkBox->setObjectName(QStringLiteral("checkBox"));
        checkBox->setGeometry(QRect(620, 40, 221, 20));
        label = new QLabel(AstroWindow);
        label->setObjectName(QStringLiteral("label"));
        label->setGeometry(QRect(620, 90, 161, 41));
        QFont font;
        font.setPointSize(14);
        font.setBold(true);
        font.setWeight(75);
        label->setFont(font);
        nameLineEdit = new QLineEdit(AstroWindow);
        nameLineEdit->setObjectName(QStringLiteral("nameLineEdit"));
        nameLineEdit->setGeometry(QRect(730, 150, 113, 22));
        luminosityLineEdit = new QLineEdit(AstroWindow);
        luminosityLineEdit->setObjectName(QStringLiteral("luminosityLineEdit"));
        luminosityLineEdit->setGeometry(QRect(730, 190, 113, 22));
        temperatureLineEdit = new QLineEdit(AstroWindow);
        temperatureLineEdit->setObjectName(QStringLiteral("temperatureLineEdit"));
        temperatureLineEdit->setGeometry(QRect(730, 230, 113, 22));
        label_2 = new QLabel(AstroWindow);
        label_2->setObjectName(QStringLiteral("label_2"));
        label_2->setGeometry(QRect(620, 150, 55, 16));
        label_3 = new QLabel(AstroWindow);
        label_3->setObjectName(QStringLiteral("label_3"));
        label_3->setGeometry(QRect(620, 190, 71, 16));
        label_4 = new QLabel(AstroWindow);
        label_4->setObjectName(QStringLiteral("label_4"));
        label_4->setGeometry(QRect(620, 230, 81, 16));
        addPushButton = new QPushButton(AstroWindow);
        addPushButton->setObjectName(QStringLiteral("addPushButton"));
        addPushButton->setGeometry(QRect(750, 270, 93, 28));
        label_5 = new QLabel(AstroWindow);
        label_5->setObjectName(QStringLiteral("label_5"));
        label_5->setGeometry(QRect(630, 490, 161, 41));
        label_5->setFont(font);
        label_6 = new QLabel(AstroWindow);
        label_6->setObjectName(QStringLiteral("label_6"));
        label_6->setGeometry(QRect(630, 590, 81, 16));
        searchNameLineEdit = new QLineEdit(AstroWindow);
        searchNameLineEdit->setObjectName(QStringLiteral("searchNameLineEdit"));
        searchNameLineEdit->setGeometry(QRect(740, 550, 113, 22));
        searchTempLineEdit = new QLineEdit(AstroWindow);
        searchTempLineEdit->setObjectName(QStringLiteral("searchTempLineEdit"));
        searchTempLineEdit->setGeometry(QRect(740, 590, 113, 22));
        label_7 = new QLabel(AstroWindow);
        label_7->setObjectName(QStringLiteral("label_7"));
        label_7->setGeometry(QRect(630, 550, 55, 16));
        listWidget = new QListWidget(AstroWindow);
        listWidget->setObjectName(QStringLiteral("listWidget"));
        listWidget->setGeometry(QRect(20, 490, 571, 192));
        savePushButton = new QPushButton(AstroWindow);
        savePushButton->setObjectName(QStringLiteral("savePushButton"));
        savePushButton->setGeometry(QRect(780, 680, 93, 28));
        label_8 = new QLabel(AstroWindow);
        label_8->setObjectName(QStringLiteral("label_8"));
        label_8->setGeometry(QRect(610, 670, 161, 41));
        label_8->setFont(font);

        retranslateUi(AstroWindow);

        QMetaObject::connectSlotsByName(AstroWindow);
    } // setupUi

    void retranslateUi(QWidget *AstroWindow)
    {
        AstroWindow->setWindowTitle(QApplication::translate("AstroWindow", "AstroWindow", nullptr));
        checkBox->setText(QApplication::translate("AstroWindow", "Only Stars from my constellation", nullptr));
        label->setText(QApplication::translate("AstroWindow", "Add star", nullptr));
        label_2->setText(QApplication::translate("AstroWindow", "Name", nullptr));
        label_3->setText(QApplication::translate("AstroWindow", "Luminosity", nullptr));
        label_4->setText(QApplication::translate("AstroWindow", "Temperature", nullptr));
        addPushButton->setText(QApplication::translate("AstroWindow", "Add star", nullptr));
        label_5->setText(QApplication::translate("AstroWindow", "Search star", nullptr));
        label_6->setText(QApplication::translate("AstroWindow", "Temperature", nullptr));
        label_7->setText(QApplication::translate("AstroWindow", "Name", nullptr));
        savePushButton->setText(QApplication::translate("AstroWindow", "Save", nullptr));
        label_8->setText(QApplication::translate("AstroWindow", "Save to file", nullptr));
    } // retranslateUi

};

namespace Ui {
    class AstroWindow: public Ui_AstroWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_ASTROWINDOW_H
