#include "DLLA.h"

DLLA::DLLA(){}

DLLA::~DLLA(){}

DLLA::DLLA(const int c)
{
	cap = c;
	nodes = DynamicVector<DLLANode>(cap);
	head = 0;  tail = 1;
	firstEmpty= 0;
	size = 0;
}