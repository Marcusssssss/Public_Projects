#include "Astronomer.h"

using namespace std;

Astronomer::Astronomer()
{
}


Astronomer::~Astronomer()
{
}

std::istream & operator>>(std::istream & is, Astronomer & a)
{
	string line, tok;
	getline(is, line);
	stringstream ss(line);
	vector<string> res;
	while (getline(ss, tok, '|'))
		res.push_back(tok);
	if (res.size() != 2)
		return is;
	a.name = res[0];
	a.constellation = res[1];
	return is;
}
