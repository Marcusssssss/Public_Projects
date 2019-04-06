#include "RepoStars.h"

using namespace std;

RepoStars::RepoStars(std::string f)
{
	fileName = f;
	ifstream ff(f);
	Star s;
	while (ff >> s)
		stars.push_back(s);
	ff.close();
}

RepoStars::RepoStars()
{
}


RepoStars::~RepoStars()
{
}

std::vector<Star> RepoStars::getByName(string name)
{
	vector<Star> byName;
	for (auto& s : stars)
		if(s.getName().compare(name)==0)
			byName.push_back(s);
	return byName;
}

std::vector<Star> RepoStars::getByNameAndTemp(std::string name, int temp)
{
	vector<Star> byName;
	if(name!="")
		byName = getByName(name);
	else
		byName = stars;
	if (temp != 0)
	{
		vector<Star> byTemp;
		for (auto& s : byName)
			if (s.getTemperature() == temp)
				byTemp.push_back(s);
		return byTemp;
	}
	return byName;
}
