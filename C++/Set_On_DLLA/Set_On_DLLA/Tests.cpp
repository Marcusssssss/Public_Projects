#include "Tests.h"
#include "Set.h"
#include "assert.h"

TestsOnSet::TestsOnSet(){}

TestsOnSet::~TestsOnSet(){}

void TestsOnSet::testForAddingAndRemoving()
{
	Set s{ 10 };

	//add

	DLLANode audi{ "Audi", 0, 0 }; s.add(audi);
	DLLANode bmw{ "BMW", 0, 0 }; s.add(bmw);
	DLLANode dacia{ "Dacia", 0, 0 };  s.add(dacia);
	DLLANode koenig{ "Koenigsegg", 0, 0 };  s.add(koenig);
	DLLANode lambo{ "Lamborghini", 0, 0 };  s.add(lambo);

	assert(s.list.nodes[s.list.head] == audi);
	assert(s.list.nodes[s.list.tail-1] == lambo);

	assert(s.list.nodes[s.list.head].toString() == "Audi; prev: -1; next: 1\n");
	assert(s.list.nodes[s.list.tail - 1].toString() == "Lamborghini; prev: 3; next: 5\n");
	
	//remove

	s.remove(s.list.nodes[s.list.head]);
	assert(s.list.nodes[s.list.head].prev == -1);
	s.remove(lambo);
	assert(koenig.next == s.list.tail);
	s.remove(dacia);
	assert(bmw.next = koenig.prev);

	//find
	assert(s.find(s.list.nodes[s.list.tail-1]) == true);
	assert(s.find(lambo) == false);

	//s.destroy();
}

void TestsOnSet::testForDestroy()
{
	//destroy - DOESN'T WORK
	//s.destroy();
}

void TestsOnSet::testForReuninon()
{
	Set s{ 10 }, ss{ 10 }, resultSet{20};
	DLLANode audi{ "Audi", 0, 0 }; s.add(audi);
	DLLANode bmw1{ "BMW", 0, 0 }; s.add(bmw1);
	DLLANode dacia{ "Dacia", 0, 0 };  s.add(dacia);
	DLLANode koenig{ "Koenigsegg", 0, 0 };  s.add(koenig);
	DLLANode lambo{ "Lamborghini", 0, 0 };  s.add(lambo);

	DLLANode aston{ "Aston Martin", 0, 0 }; ss.add(aston);
	DLLANode vw{ "Volkswagen", 0, 0 }; ss.add(vw);

	resultSet = Set::reunion(s, ss);
	assert(resultSet.size() == 7);

	DLLANode bmw2{ "BMW", 0, 0 }; ss.add(bmw2);
	resultSet = Set::reunion(s, ss);
	assert(resultSet.size() == 7);

	resultSet.remove(audi);
	assert(resultSet.size() == 6);

	DLLANode tesla{ "Tesla", 0, 0 }; ss.add(tesla);

	resultSet = resultSet.reunion(s, ss); //Audi is readded and Tesla added, so resultSet.size+=2
	assert(resultSet.size() == 8);
	//s.destroy();
	//ss.destroy();
	//resultSet.destroy();
}

void TestsOnSet::testForIntersection()
{
	Set s{ 10 }, ss{ 10 }, resultSet{ 20 };
	DLLANode audi{ "Audi", 0, 0 }; s.add(audi);
	DLLANode bmw1{ "BMW", 0, 0 }; s.add(bmw1);
	DLLANode mergedes{ "Mercedes", 0, 0 };  s.add(mergedes);
	DLLANode koenig{ "Koenigsegg", 0, 0 };  s.add(koenig);
	DLLANode lambo{ "Lamborghini", 0, 0 };  s.add(lambo);

	DLLANode mergedess{ "Mercedes", 0, 0 };  ss.add(mergedess);
	DLLANode koenigg{ "Koenigsegg", 0, 0 }; ss.add(koenigg);
	DLLANode aston{ "Aston Martin", 0, 0 }; ss.add(aston);
	DLLANode vw{ "Volkswagen", 0, 0 }; ss.add(vw);

	resultSet = Set::intersection(s, ss);
	assert(resultSet.size() == 2);

	DLLANode bmw2{ "BMW", 0, 0 }; ss.add(bmw2);
	resultSet = Set::intersection(s, ss);
	assert(resultSet.size() == 3);

	assert(resultSet.find(audi) == false);
	//s.destroy();
	//ss.destroy();
	//resultSet.destroy();
}

void TestsOnSet::testForDifference()
{
	Set s{ 10 }, ss{ 10 }, resultSet{ 20 };
	DLLANode audi{ "Audi", 0, 0 }; s.add(audi);
	DLLANode bmw1{ "BMW", 0, 0 }; s.add(bmw1);
	DLLANode mergedes{ "Mercedes", 0, 0 };  s.add(mergedes);
	DLLANode koenig{ "Koenigsegg", 0, 0 };  s.add(koenig);
	DLLANode lambo{ "Lamborghini", 0, 0 };  s.add(lambo);

	DLLANode mergedess{ "Mercedes", 0, 0 };  ss.add(mergedess);
	DLLANode koenigg{ "Koenigsegg", 0, 0 }; ss.add(koenigg);
	DLLANode aston{ "Aston Martin", 0, 0 }; ss.add(aston);
	DLLANode vw{ "Volkswagen", 0, 0 }; ss.add(vw);

	resultSet = Set::difference(s, ss);
	assert(resultSet.size() == 3);

	DLLANode bmw2{ "BMW", 0, 0 }; ss.add(bmw2);
	resultSet = Set::difference(s, ss);
	assert(resultSet.size() == 2);

	assert(resultSet.find(mergedes) == false);
	//s.destroy();
	//ss.destroy();
	//resultSet.destroy();
}

void TestsOnSet::testForDynamicVector()
{
	DynamicVector<int> ints(1);
	for (int i = 0; i < 20; i++) // the vector will be resized, since the initial capacity is only 10
		ints.add(i);		     // and it must be 20
	assert(ints[19] == 19);
	assert(ints[5] == 5);
	assert(ints.getSize() == 20);

	ints = ints - ints[4]; //the vector will not contain anymore 4 and, instead of 4 will be 19...
	assert(ints[4] == 19);

	DynamicVector<int> ints2;
	for (int i = 50; i < 60; i++)
		ints2.add(i);

	ints = ints2; ints2.setSize(0);
	assert(ints[1] == 51);
	assert(ints[2] == 52);
	ints = ints - ints[2];
	assert(ints[2] != 52);
	//ints.~DynamicVector();
	//ints2.~DynamicVector();
}

void TestsOnSet::testForDLLA()
{
	DLLA dlla{ 20 };
	assert(dlla.tail == 1);
	assert(dlla.head == 0);
	assert(dlla.cap == 20);
	assert(dlla.firstEmpty = -1);

	DLLANode node1{ "node1", 0, 0 };
	DLLANode node2{ "node2", 0, 0 };
	dlla.nodes.add(node1);
	dlla.nodes.add(node2);
	assert(dlla.nodes[0].info.compare("node1") == 0);
	assert(dlla.nodes[1].info.compare("node2") == 0);
	assert(dlla.cap == 20);
	//dlla.~DynamicVector();
}

void TestsOnSet::testsForDLLAIterator()
{
	Set s{ 10 };
	DLLANode audi{ "Audi", 0, 0 }; s.add(audi);
	DLLANode bmw1{ "BMW", 0, 0 }; s.add(bmw1);
	DLLANode mergedes{ "Mercedes", 0, 0 };  s.add(mergedes);
	DLLANode koenig{ "Koenigsegg", 0, 0 };  s.add(koenig);
	DLLANode lambo{ "Lamborghini", 0, 0 };  s.add(lambo);
	
	DLLAIterator it{ s.list };
	assert(it.list.nodes[it.getCurrent()] == audi);
	assert(it.valid() == true);
	it.next();
	assert(it.list.nodes[it.currentElement] == bmw1);
	it.next(); it.next(); it.next();
	assert(it.list.nodes[it.currentElement] == lambo);
	it.next();
	assert(it.valid() == false);
	it.previous();
	assert(it.valid() == true);
}
