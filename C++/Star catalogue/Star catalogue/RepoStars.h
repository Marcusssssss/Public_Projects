#pragma once
#include "Star.h"

class RepoStars
{
private:
	std::vector<Star> stars;
	std::string fileName;
public:
	RepoStars(std::string f);
	RepoStars();
	~RepoStars();
	void addStar(const Star& s) { stars.push_back(s); }
	std::vector<Star>& getStars() { return stars; }
	std::vector<Star> getByName(std::string name);
	std::vector<Star> getByNameAndTemp(std::string name, int temp);
	std::string getFile() { return fileName; }
};

