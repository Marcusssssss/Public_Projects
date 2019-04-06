#pragma once
class TestsOnSet
{
public:
	TestsOnSet();
	~TestsOnSet();
	static void testForAddingAndRemoving();
	static void testForDestroy(); //doesn't working
	static void testForReuninon();
	static void testForIntersection(); //contains set.find(element) function
	static void testForDifference(); //contains set.find(element) function
	static void testForDynamicVector();
	static void testForDLLA();
	static void testsForDLLAIterator();
};

