#pragma once
#include "DLLAIterator.h"
#include "DLLA.h"

class Set
{
public:
	DLLA list;
	std::string fileName;
public:
	Set();
	Set(const int cap) { list = DLLA(cap); }
	Set(const int cap, std::string file);
	~Set();
	void add(DLLANode & element);
	void remove(DLLANode & element);
	bool find(DLLANode &element);
	int size() { return this->list.size; }
	void destroy();
	static Set reunion(Set s1, Set s2);
	static Set intersection(Set s1, Set s2);
	static Set difference(Set s1, Set s2);
};

