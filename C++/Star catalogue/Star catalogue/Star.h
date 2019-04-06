#pragma once
#include <vector>
#include <string>
#include <sstream>
#include <fstream>

class Star
{
private:
	std::string name, constellation;
	int luminosity, temperature;
public:
	Star();
	~Star();
	Star(std::string n, std::string c, int l, int t) :name(n), constellation(c), luminosity(l), temperature(t) {}

	friend std::istream& operator>>(std::istream& is, Star& s);
	friend std::ostream& operator<<(std::ostream& os, const Star& s);

	std::string getName() { return name; }
	std::string getConstellation() { return constellation; }
	int getLuminosity() { return luminosity; }
	int getTemperature() { return temperature; }
	std::string toString();
};

