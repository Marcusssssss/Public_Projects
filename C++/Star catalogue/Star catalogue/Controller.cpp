#include "Controller.h"

using namespace std;

Controller::Controller(std::string file)
{
	RepoStars repo{ file };
	repoStars = repo;
}

Controller::Controller()
{
}


Controller::~Controller()
{
}

void Controller::addStar(std::string n, std::string c, int l, int t)
{
	repoStars.addStar(Star{ n, c, l, t });
}

std::vector<Star> Controller::getByName(string name)
{
	return repoStars.getByName(name);
}

std::vector<Star> Controller::getByNameAndTemp(std::string name, int temp)
{
	return repoStars.getByNameAndTemp(name, temp);
}

void Controller::saveToFile()
{
	ofstream ff(repoStars.getFile());
	for (auto s : repoStars.getStars())
		ff << s;
}
