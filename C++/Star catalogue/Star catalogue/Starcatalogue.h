#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_Starcatalogue.h"

class Starcatalogue : public QMainWindow
{
	Q_OBJECT

public:
	Starcatalogue(QWidget *parent = Q_NULLPTR);

private:
	Ui::StarcatalogueClass ui;
};
