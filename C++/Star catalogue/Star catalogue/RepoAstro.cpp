#include "RepoAstro.h"

using namespace std;

RepoAstro::RepoAstro(std::string f)
{
	fileName = f;
	ifstream ff(f);
	Astronomer a;
	while (ff >> a)
		astros.push_back(a);
	ff.close();
}

RepoAstro::RepoAstro()
{
}


RepoAstro::~RepoAstro()
{
}
