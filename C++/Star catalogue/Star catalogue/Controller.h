#pragma once
#include "RepoStars.h"

class Controller
{
private:
	RepoStars repoStars;

public:
	Controller(std::string file);
	Controller();
	~Controller();
	
	RepoStars& getStarsRepo() { return repoStars; }
	/*
		Function which adds a star to the stars repository.
		In: 
			- name: n (string)
			- constellation: c(string)
			- luminosity: l(int)
			- temperature: t(int)
		Out: the vector of stars will now contain a new star with theese informations.
	*/
	void addStar(std::string n, std::string c, int l, int t);
	std::vector<Star> getByName(std::string name);

	/*
		Function which returns a vector of stars searched by name and/ or by temperature.
		If name is "", then the search will be made only on temp.
		If temp is 0, then the search will be made only on name.
		If name is not "" and temp is not 0, the search will be made on both.
		
		In: - name (string)
			- temp (int)
		Out: A vector containing stars searched as it is mentioned above.
	*/
	std::vector<Star> getByNameAndTemp(std::string name, int temp);
	void saveToFile();
};

