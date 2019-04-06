#include "Set.h"
#include <fstream>

using namespace std;

Set::Set(){}

Set::Set(const int cap, std::string file)
{
	list = DLLA(cap); fileName = file;
	ifstream f(file);
	string brand;
	char *ds;
	if (f.is_open())
	{
		while (getline(f, brand))
		{
			DLLANode node{ brand, 0, 0 };
			add(node);
		}
		f.close();
	}
}

Set::~Set(){}

void Set::add(DLLANode & e)
{
	if(list.size==0 || find(e)==false)
	{
		if (list.firstEmpty == 0)
		{
			e.next = list.tail;
			e.prev = -1;
			list.nodes.add(e);
			list.firstEmpty = 1;
		}
		else
		{
			e.next = list.tail + 1;
			e.prev = list.tail - 1;
			list.nodes.add(e);
			list.firstEmpty = ++list.tail;
		}
		list.size++;
	}
}

void Set::remove(DLLANode & e)
{
	if (find(e))
	{
		int prevNode = -1;
		DLLAIterator it{ list };
		while (it.valid() && list.nodes[it.currentElement] != e)
		{
			prevNode = it.currentElement;
			it.next();
		}
		if (it.currentElement != -1)
		{
			if (it.currentElement == list.head)
			{
				list.head = list.nodes[it.currentElement].next;
				list.nodes[list.nodes[it.currentElement].next].prev = -1;
			}
			else if (it.currentElement == list.tail - 1)
			{
				list.tail--;
				list.nodes[prevNode].next = list.tail;
			}
			else
			{
				list.nodes[prevNode].next = list.nodes[it.currentElement].next;
				list.nodes[list.nodes[it.currentElement].next].prev = prevNode;
			}
			list.nodes[it.currentElement].info = "deleted";
			list.size--;
			//list.nodes = list.nodes - list.nodes[it.currentElement];
		}
	}
}

bool Set::find(DLLANode & e)
{
	DLLAIterator it{ list };
	while (it.valid())
	{
		if(list.nodes[it.currentElement].info.compare("deleted")!=0)
			if (list.nodes[it.currentElement] == e)
				return true;
		it.next();
	}
	return false;
}

void Set::destroy()
{
	//list.nodes.~DynamicVector();
}

Set Set::reunion(Set s1, Set s2)
{
	DLLAIterator it1{ s1.list }, it2{ s2.list };
	Set resultSet{ s1.list.cap + s2.list.cap };
	while (it1.valid())
	{
		resultSet.add(s1.list.nodes[it1.currentElement]);
		it1.next();
	}
	while (it2.valid())
	{
		if (resultSet.find(s2.list.nodes[it2.currentElement]) == false)
			resultSet.add(s2.list.nodes[it2.currentElement]);
		it2.next();
	}
	return resultSet;
}

Set Set::intersection(Set s1, Set s2)
{
	DLLAIterator it1{ s1.list }, it2{ s2.list };
	Set resultSet{ s1.list.cap };
	while (it1.valid())
	{
		if(s2.find(s1.list.nodes[it1.currentElement]))
			resultSet.add(s1.list.nodes[it1.currentElement]);
		it1.next();
	}
	return resultSet;
}

Set Set::difference(Set s1, Set s2)
{
	DLLAIterator it1{ s1.list }, it2{ s2.list };
	Set resultSet{ s1.list.cap };
	while (it1.valid())
	{
		if (s2.find(s1.list.nodes[it1.currentElement]) == false)
			resultSet.add(s1.list.nodes[it1.currentElement]);
		it1.next();
	}
	return resultSet;
}
