#pragma once
#include <vector>
#include <string>
#include <sstream>
#include <fstream>

class Astronomer
{
private:
	std::string name, constellation;

public:
	Astronomer(std::string n, std::string c) :name(n), constellation(c) {}
	Astronomer();
	~Astronomer();

	friend std::istream& operator>>(std::istream& is, Astronomer& a);
	std::string getName() { return name; }
	std::string getConstellation() { return constellation; }
};

