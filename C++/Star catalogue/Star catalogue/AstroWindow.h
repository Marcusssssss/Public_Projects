#pragma once
#include <QWidget>
#include "ui_AstroWindow.h"
#include "Controller.h"
#include "Astronomer.h"
#include "StarsModelView.h"
#include "OnlyMineModel.h"

class AstroWindow : public QWidget
{
	Q_OBJECT

public:
	AstroWindow(StarsModelView* m, Astronomer& a, Controller& c, QWidget *parent = Q_NULLPTR);
	~AstroWindow();
	void connectSlots();
	void onlyFromMine();
	void addStar();
	void searchStars();
	void save();

private:
	Ui::AstroWindow ui;
	Astronomer astro;
	Controller& ctrl;
	StarsModelView* model;
	OnlyMineModel* onlyModel;
};
