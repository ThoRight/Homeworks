#ifndef _DayofYearSet_H_
#define _DayofYearSet_H_

#include <iostream>

using namespace std;

namespace MuratSet{

class DayofYearSet{
public:
    
    class DayOfYear{
    public:
	    DayOfYear();
	    DayOfYear(int newMonth, int newDay);
        void set(int newMonth, int newDay);

 
        int getMonthNumber()const; //Returns 1 for January, 2 for February, etc.
        int getDay()const;

        bool operator==(const DayOfYear &oth)const;
        void operator++();
        friend ostream & operator<<(ostream &out, const DayOfYear &doy);

    private:
        int month;
        int day;
    };
    
    DayofYearSet();
    int size()const;

    friend ostream & operator << (ostream &out, const DayofYearSet &doySet);
    DayofYearSet(const initializer_list<DayOfYear>& setList);
    ~DayofYearSet();
    DayofYearSet(const DayofYearSet & copy);
    bool operator ==(const DayofYearSet & oth)const;
    bool operator !=(const DayofYearSet & oth)const;
    DayofYearSet operator +(const DayOfYear &oth);
    void remove(const DayOfYear & key);
    DayofYearSet operator+(DayofYearSet &oth)const;
    DayofYearSet operator-(const DayofYearSet &oth)const;
    DayofYearSet operator-(const DayOfYear &oth)const;
    DayofYearSet& operator=(const DayofYearSet &oth);
    DayofYearSet operator^(const DayofYearSet &oth)const;
    DayOfYear& operator[](const int& index)const;
    DayofYearSet operator!()const;
    void setsizeofSet(int newSize);

private:
    DayOfYear *set;
    int sizeofSet;
    bool issame(const DayOfYear & key)const;

};

}
#endif /* _DayofYearSet_H_ */