#pragma once
#include "Set.h"
#include <iostream>

class UI
{
public:
	UI();
	~UI();
	int printMenu();
	std::string choose(std::string j, std::string str);
	void solvingTheProblem();
	void printSet(Set s);
	void run();
};

