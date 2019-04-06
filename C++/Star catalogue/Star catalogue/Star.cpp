#include "Star.h"

using namespace std;

Star::Star()
{
}


Star::~Star()
{
}

std::string Star::toString()
{
	return name + " | " + constellation + " | " + to_string(luminosity) + " | " + to_string(temperature);
}

std::istream & operator>>(std::istream & is, Star & s)
{
	string line, tok;
	getline(is, line);
	stringstream ss(line);
	vector<string> res;
	while (getline(ss, tok, '|'))
		res.push_back(tok);
	if (res.size() != 4)
		return is;
	s.name = res[0];
	s.constellation = res[1];
	s.luminosity = stoi(res[2]);
	s.temperature = stoi(res[3]);
	return is;
}

std::ostream & operator<<(std::ostream & os, const Star &s)
{
	os << s.name << '|' << s.constellation << '|' << to_string(s.luminosity) << '|' << to_string(s.temperature) << "\n";
	return os;
}
