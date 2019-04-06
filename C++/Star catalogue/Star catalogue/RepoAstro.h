#pragma once
#include "Astronomer.h"

class RepoAstro
{
private:
	std::vector<Astronomer> astros;
	std::string fileName;
public:
	RepoAstro(std::string f);
	RepoAstro();
	~RepoAstro();

	std::vector<Astronomer>& getAstros() { return astros; }
};

