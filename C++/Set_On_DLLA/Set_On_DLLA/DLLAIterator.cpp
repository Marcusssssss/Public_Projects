#include "DLLAIterator.h"

DLLAIterator::DLLAIterator(){}

DLLAIterator::~DLLAIterator(){}

void DLLAIterator::next()
{
	currentElement = list.nodes[currentElement].next;
}

void DLLAIterator::previous()
{
	currentElement = list.nodes[currentElement].prev;
}

bool DLLAIterator::valid()
{
	if (currentElement == -1 || currentElement>=list.tail)
		return false;
	else
		return true;
}
