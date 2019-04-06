#include "Starcatalogue.h"
#include <QtWidgets/QApplication>
#include "RepoAstro.h"
#include "Controller.h"
#include "AstroWindow.h"

using namespace std;

void tests()
{
	Controller ctrl;

	ctrl.addStar("Steluta", "Calea Mare", 200, 123);
	assert(ctrl.getStarsRepo().getStars()[0].getLuminosity() == 200);
	assert(ctrl.getStarsRepo().getStars()[0].getConstellation().compare("Calea Mare") == 0);
	ctrl.addStar("Puiu", "Gainusa", 22222, 441);
	assert(ctrl.getStarsRepo().getStars()[1].getLuminosity() == 22222);
	assert(ctrl.getStarsRepo().getStars()[0].getConstellation().compare("Calea Mare") == 0);

	ctrl.addStar("Shiny", "Milky Way", 213, 11111);
	ctrl.addStar("Shiny", "Another way", 2123123, 11111);

	//only by temperature
	vector<Star> starsByTAndN = ctrl.getByNameAndTemp("", 123);
	assert(starsByTAndN[0].getName().compare("Steluta") == 0);

	//only by name
	starsByTAndN = ctrl.getByNameAndTemp("Puiu", 0);
	assert(starsByTAndN[0].getName().compare("Puiu") == 0);

	//by name and temperature
	starsByTAndN = ctrl.getByNameAndTemp("Shiny", 11111);
	assert(starsByTAndN.size() == 2);
	assert(starsByTAndN[0].getName().compare("Shiny") == 0);
}

int main(int argc, char *argv[])
{
	tests();
	QApplication a(argc, argv);
	RepoAstro astros{ "astronomers.txt" };
	Controller ctrl{ "stars.txt" };
	vector<Astronomer>& as =astros.getAstros();

	StarsModelView* model= new StarsModelView(ctrl.getStarsRepo(), NULL);

	for (int i = 0; i < as.size(); i++)
	{
		AstroWindow* w = new AstroWindow(model, as[i], ctrl);
		w->show();
	}

	return a.exec();
}
