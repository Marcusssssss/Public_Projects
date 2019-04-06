#pragma once
#include "DLLANode.h"
#include "DynamicVector.h"

class DLLA
{
public:
	DynamicVector<DLLANode> nodes;
	int cap, head, tail, firstEmpty;
	int size;

public:
	DLLA();
	DLLA(const int c);

	~DLLA();
};

