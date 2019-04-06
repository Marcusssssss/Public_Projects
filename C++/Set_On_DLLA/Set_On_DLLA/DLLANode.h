#pragma once
#include <string>

class DLLANode
{
public:
	std::string info;
	int next, prev;
public:
	DLLANode();
	~DLLANode();
	
	DLLANode(const std::string i, const int n, const int p) :info(i), next(n), prev(p) {}
	std::string toString() { return info + "; prev: " + std::to_string(prev) +"; next: " + std::to_string(next)+"\n"; }
	friend bool operator==(DLLANode node1, DLLANode node2);
	friend bool operator!=(DLLANode node1, DLLANode node2); 
};

