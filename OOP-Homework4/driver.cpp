#include "DayofYearSet.h"
#include <string>

int main(){
    bool check;
    MuratSet::DayofYearSet::DayOfYear d1;
    MuratSet::DayofYearSet::DayOfYear d2(5,5);
    
    MuratSet::DayofYearSet s1 = {{1,1}, {2,2}, {2,2}, {3,3}, {9,29}, {1,1}, {9,9}};
    MuratSet::DayofYearSet s2 = {{2,2}, {3,15}, {3,15}, {4,20}, {9,9}, {5,5}, {7,17}};
    MuratSet::DayofYearSet s3;
    MuratSet::DayofYearSet s4 = {{9,9}, {2,2}, {9,29}, {1,1}, {3,3}};
    MuratSet::DayofYearSet s5 = s4;
    
    cout << "DayofYearSet::DayOfYear d1(default constructor)" << endl;
    cout << "DayofYearSet::DayOfYear d2(5,5)" << endl;
    cout << "operator '<<' for d1: ";
    cout << d1 << endl;
    cout << "operator '<<' for d2: ";
    cout << d2 << endl;

    cout <<"\n";

    cout << "DayofYearSet s1 = {{1,1}, {2,2}, {2,2}, {3,3}, {9,29}, {1,1}, {9,9}}" << endl;
    cout << "DayofYearSet s2 = {{2,2}, {3,15}, {3,15}, {4,20}, {9,9}, {5,5}, {7,17}}" << endl;
    cout << "DayofYearSet s3 (default constructor)" << endl;
    cout << "DayofYearSet s4 = {{9,9}, {2,2}, {9,29}, {1,1}, {3,3}}" << endl;
    cout << "DayofYearSet s5 = s4 (copy constructor)\n" << endl;
    cout << "operator '<<' for s1: ";
    cout << s1 << endl;
    cout << "operator '<<' for s2: ";
    cout << s2 << endl;
    cout << "operator '<<' for s3: ";
    cout << s3 << endl;
    cout << "operator '<<' for s4: ";
    cout << s4 << endl;
    cout << "operator '<<' for s5: ";
    cout << s5 << endl;

    cout << "\nSize of s1: ";
    cout << s1.size();
    cout << "\nSize of s2: ";
    cout << s2.size();
    cout << "\nSize of s3: ";
    cout << s3.size();
    cout << "\nSize of s4: ";
    cout << s4.size();
    cout << "\nSize of s5: ";
    cout << s5.size() << endl;


    cout << "\ns1==s2 : ";
    check = s1==s2;
    cout << check;
    cout << "\ns1==s4 : ";
    check = s1==s4;
    cout << check;
    cout << "\ns1!=s2 : ";
    check = s1!=s2;
    cout << check;
    cout << "\ns1!=s4 : ";
    check = s1!=s4;
    cout << check;

    string next;
    cout << "\nPress enter to next page\n";
    getline(cin,next);
    system("clear");
    cout << "s1: " << s1 << "\ns2: " << s2 << "\ns3: " << s3 << "\ns4: " << s4 << endl;

    cout << "\nRemoving 'March 15' from s2..(remove function)\n";
    s2.remove(MuratSet::DayofYearSet::DayOfYear(3,15));
    cout << "New s2: " << s2;

    cout << "\nRemoving 'April 20' from s2..(remove function)\n";
    s2.remove(MuratSet::DayofYearSet::DayOfYear(4,20));
    cout << "New s2: " << s2 << endl;
    
    cout << "\nbinary operator+ (adding elements)  s1 = s1 + September 29(already has)\n";
    s1 = s1 + MuratSet::DayofYearSet::DayOfYear(9,29);
    cout << "s1 + September 29 = " << s1;

    cout << "\nbinary operator+ (adding elements)  s3 = s3 + 13 November\n";
    s3 = s3 + MuratSet::DayofYearSet::DayOfYear(11,13);
    cout << "s3 + 13 November = " << s3;

    cout << "\nPress enter to next page\n";
    getline(cin,next);
    system("clear");

    cout << "s1: " << s1 << "\ns2: " << s2 << "\ns3: " << s3 << "\ns4: " << s4 << endl;
    
    cout << "\nbinary operator+ (union set) s1 = s1 + s2\n";
    s1 = s1 + s2;
    cout << "new s1: " << s1 << endl;

    cout << "\nbinary operator+ (union set) s4 = s4 + s3\n";
    s4 = s4 + s3;
    cout << "new s4: " << s4 << endl;

    cout << "\nbinary operator- (returns difference set)\n";
    cout << "s1-s2 = " << s1-s2 << endl;

    cout << "\nbinary operator- (returns difference set)\n";
    cout << "s3-s4 = " << s3-s4 << endl;

    cout << "\nPress enter to next page\n";
    getline(cin,next);
    system("clear");

    cout << "s1: " << s1 << "\ns2: " << s2 << "\ns3: " << s3 << "\ns4: " << s4 << endl;

    cout << "\nbinary operator- (removes an element from the set)\n";
    cout << "s1 = s1 - September 9\n";
    s1 = s1 - MuratSet::DayofYearSet::DayOfYear(9,9);
    cout << "new s1: " << s1 << endl;

    cout << "\nbinary operator- (removes an element from the set)\n";
    cout << "s2 = s2 - May 5\n";
    s2 = s2 - MuratSet::DayofYearSet::DayOfYear(5,5);
    cout << "new s2: " << s2 << endl;

    cout << "\nbinary operator- (removes an element from the set)\n";
    cout << "s2 = s2 - March 15(doesn't exist)\n";
    s2 = s2 - MuratSet::DayofYearSet::DayOfYear(3,15);
    cout << "new s2: " << s2 << endl;

    cout << "\nPress enter to next page\n";
    getline(cin,next);
    system("clear");

    cout << "s1: " << s1 << "\ns2: " << s2 << "\ns3: " << s3 << "\ns4: " << s4 << endl;
    
    cout << "\nbinary operator^ (intersection set)\n";
    MuratSet::DayofYearSet tempSet = s1^s2;
    cout << "s1^s2 = " << tempSet << endl;
    tempSet = s2^s3;
    cout << "s2^s3 = " << tempSet << endl;
    tempSet = s1^s4;
    cout << "s1^s4 = " << tempSet << endl;

    cout << "\nbinary operator[]\n";
    cout << "s1[1] = " << s1[1] << endl;
    cout << "s3[0] = " << s3[0] << endl;
    cout << "s4[2] = " << s4[2] << endl;

    cout << "\nPress enter to next page\n";
    getline(cin,next);
    system("clear");

    cout << "s1: " << s1 << "\ns2: " << s2 << "\ns3: " << s3 << "\ns4: " << s4 << endl;

    cout << "\nunary operator!(complement of set)\n";
    cout << "\nPress enter to see complement of s1\n";
    getline(cin,next);
    cout << "!s1 = " << !s1 << endl;

    cout << "\nPress enter to next page\n";
    getline(cin,next);
    system("clear");

    cout << "s1: " << s1 << "\ns2: " << s2 << "\ns3: " << s3 << "\ns4: " << s4 << endl;

    cout << "\nunary operator!(complement of set)\n";
    cout << "\nPress enter to see complement of s4\n";
    getline(cin,next);
    cout << "!s4 = " << !s4 << endl;


}