#include "AstroWindow.h"
#include <qobject.h>
#include <qmessagebox.h>

using namespace std;

AstroWindow::AstroWindow(StarsModelView* m, Astronomer& a, Controller& c, QWidget *parent): model(m), astro(a), ctrl(c), QWidget(parent)
{
	ui.setupUi(this);
	this->setWindowTitle(QString::fromStdString(a.getName()));
	onlyModel = new OnlyMineModel(a, c.getStarsRepo(), NULL);
	ui.tableView->setModel(m);
	ui.tableView->resizeColumnsToContents();
	connectSlots();
}

AstroWindow::~AstroWindow()
{
}

void AstroWindow::connectSlots()
{
	connect(ui.checkBox, &QCheckBox::stateChanged, this, &AstroWindow::onlyFromMine);
	connect(ui.addPushButton, &QPushButton::clicked, this, &AstroWindow::addStar);
	connect(ui.searchNameLineEdit, &QLineEdit::textChanged, this, &AstroWindow::searchStars);
	connect(ui.searchTempLineEdit, &QLineEdit::textChanged, this, &AstroWindow::searchStars);
	connect(ui.savePushButton, &QPushButton::clicked, this, &AstroWindow::save);
}

void AstroWindow::onlyFromMine()
{
	if (ui.tableView->model() == model)
		ui.tableView->setModel(onlyModel);
	else ui.tableView->setModel(model);
}

void AstroWindow::addStar()
{
	string name = this->ui.nameLineEdit->text().toStdString();
	int lum = this->ui.luminosityLineEdit->text().toInt(), temp = ui.temperatureLineEdit->text().toInt();
	if (name == "")
		QMessageBox::information(this, "Fail", "Empty name!");
	else if(!ctrl.getByName(name).empty())
		QMessageBox::information(this, "Fail", "There is another star with the same name!");
	else
	{
		onlyModel->layoutAboutToBeChanged();
		model->layoutAboutToBeChanged();
		ctrl.addStar(name, astro.getConstellation(), lum, temp);
		QMessageBox::information(this, "Success", "The star has been added!");
		onlyModel->layoutChanged();
		model->layoutChanged();
	}
}

void AstroWindow::searchStars()
{
	ui.listWidget->clear();
	string name = ui.searchNameLineEdit->text().toStdString();
	int temp = ui.searchTempLineEdit->text().toInt();
	vector<Star> vector = ctrl.getByNameAndTemp(name, temp);
	for (auto star : vector)
		ui.listWidget->addItem(QString::fromStdString(star.toString()));
}

void AstroWindow::save()
{
	ctrl.saveToFile();
}
