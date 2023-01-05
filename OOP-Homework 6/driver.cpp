#include "PFArray.cpp"


int main(){
    cout << "---- Testing PFArray class functions ----\n";
    MuratArray::PFArray<int> pfarr1;
    cout << "\npfarr1(int) has been created with no parameter constructor\n";
    MuratArray::PFArray<char> pfarr2(5);
    cout << "pfarr2(char) has been created with 5 capacity\n";
    MuratArray::PFArray<int> pfarr3=pfarr1;
    cout << "pfarr3(int) has been created with copy constructor(pfarr3=pfarr1)\n";
    cout << "\nAdding these elements to pfarr1: 5,7,8,4,2,29,14,10,6,4,7 (with this order)\n";
    pfarr1.addElement(5);
    pfarr1.addElement(7);
    pfarr1.addElement(8);
    pfarr1.addElement(4);
    pfarr1.addElement(2);
    pfarr1.addElement(29);
    pfarr1.addElement(14);
    pfarr1.addElement(10);
    pfarr1.addElement(6);
    pfarr1.addElement(4);
    pfarr1.addElement(7);
    cout << "\nPrinting pfarr1 by using ranged based for loop\n";
    cout << "pfarr1: ";
    for(auto it: pfarr1)
        cout << it << " ";
    cout << endl;
    cout << "\nSorting pfarr1 by using std::sort....\n";
    sort(pfarr1.begin(),pfarr1.end());
    cout << "\nAfter sorting, printing pfarr1 by using begin and end...\n";
    cout << "pfarr1: ";
    for(auto it=pfarr1.begin();it!=pfarr1.end();++it)
        cout << *it << " ";
    cout << endl;

    cout << "\nAdding these elements to pfarr2: 'M','U','R','A','T','A','R','R','A','Y' (with this order, capacity must be increased)\n";
    pfarr2.addElement('M');
    pfarr2.addElement('U');
    pfarr2.addElement('R');
    pfarr2.addElement('A');
    pfarr2.addElement('T');
    pfarr2.addElement('A');
    pfarr2.addElement('R');
    pfarr2.addElement('R');
    pfarr2.addElement('A');
    pfarr2.addElement('Y');

    cout << "\nPrinting pfarr2 by using cbegin and cend...\n";
    cout << "pfarr2: ";
    for(auto it=pfarr2.cbegin();it!=pfarr2.cend();++it)
        cout << *it << " ";
    cout << endl;
    cout << "\nChecking whether pfarr1 is empty by using empty function...\n";
    if(pfarr1.empty())
        cout << "Yes it's empty\n";
    else
        cout << "No it is not empty\n";

    cout << "\nChecking whether pfarr2 is empty by using empty function...\n";
    if(pfarr2.empty())
        cout << "Yes it's empty\n";
    else
        cout << "No it is not empty\n";

    cout << "\nChecking whether pfarr3 is empty by using empty function...\n";
    if(pfarr3.empty())
        cout << "Yes it's empty\n";
    else
        cout << "No it is not empty\n";

    cout << "\nSize of pfarr1 by using size function(it gives number of element currently using): ";
    cout << pfarr1.size() << endl;

    cout << "\nSize of pfarr2 by using size function(it gives number of element currently using): ";
    cout << pfarr2.size() << endl;

    cout << "\nSize of pfarr3 by using size function(it gives number of element currently using): ";
    cout << pfarr3.size() << endl;

    cout << "\nFinding element 'T' in pfarr2 by using std::find...\n";
    char c='T';
    MuratArray::PFArray<char>::Iterator it = find(pfarr2.begin(), pfarr2.end(), c);
    if (it != pfarr2.end()){
        cout << "Element " << c <<" has been found at position : " ;
        cout << it - pfarr2.begin() << " (starts from zero) \n" ;
    }
    else
        cout << "Element couldnt be found.\n";

    cout << "\nFinding element 15(it doesn't exist) in pfarr1 by using std::find...\n";
    int i = 15;
    MuratArray::PFArray<int>::Iterator it2 = find(pfarr1.begin(), pfarr1.end(), i);
    if (it2 != pfarr1.end()){
        cout << "Element " << i <<" has been found at position : " ;
        cout << it2 - pfarr1.begin() << " (starts from zero) \n" ;
    }
    else
        cout << "Element couldnt be found.\n";

    cout << "\nMultiplying each element of pfarr1 by 2 and printing by using std::for_each...\n";
    cout << "new pfarr1: ";
    for_each(pfarr1.begin(), pfarr1.end(), [](int& x) { x *= 2; cout << x << " ";});
    cout << endl;

    cout << "\nPrinting pfarr2 by using std::for_each...\n";
    cout << "pfarr2: ";
    for_each(pfarr2.begin(), pfarr2.end(), [](char &c) { cout << c << " ";});
    cout << endl;

    cout << "\nErasing fourth element pointed by the iterator(which is 10) in pfarr1 by using erase...\n";
    MuratArray::PFArray<int>::Iterator iter = pfarr1.begin() + 3;
    pfarr1.erase(iter);
    cout << "After erasing new pfarr1: ";
    for_each(pfarr1.begin(), pfarr1.end(), [](int& x) { cout << x << " ";});
    cout << endl;

    cout << "\nTrying to erase element in pfarr2 with improper iterator by using erase...\n";
    MuratArray::PFArray<char>::Iterator iter2 = pfarr2.begin() + 15;
    pfarr2.erase(iter2);

    cout << "\nClearing pfarr1 by using clear function...\n";
    pfarr1.clear();
    cout << "After clearing, checking whether pfarr1 is empty by using empty function...\n";
    if(pfarr1.empty())
        cout << "Yes it's empty\n";
    else
        cout << "No it is not empty\n";


    
}