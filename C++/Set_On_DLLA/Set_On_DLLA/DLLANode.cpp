#include "DLLANode.h"

DLLANode::DLLANode(){}

DLLANode::~DLLANode(){}

bool operator==(DLLANode node1, DLLANode node2)
{
	return node1.info.compare(node2.info)==0;
}

bool operator!=(DLLANode node1, DLLANode node2)
{
	if (operator==(node1, node2) == true)
		return false;
	else return true;
}
