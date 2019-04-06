#pragma once
#include "DLLA.h"

class DLLAIterator
{
public:
	DLLA list;
	int currentElement;

public:
	DLLAIterator();
	DLLAIterator(DLLA &dlla) : list(dlla), currentElement(dlla.head) {}
	
	~DLLAIterator();
	void next();
	void previous();
	bool valid();
	int getCurrent() { return currentElement; }
};

