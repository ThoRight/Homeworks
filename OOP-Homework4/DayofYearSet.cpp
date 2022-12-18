#include "DayofYearSet.h"

namespace MuratSet{

void DayofYearSet::DayOfYear::set(int newMonth, int newDay){
        month = newMonth;
        day = newDay;
}


int DayofYearSet::DayOfYear::getMonthNumber()const{
    return month;
}

int DayofYearSet::DayOfYear::getDay()const{
    return day;
}


DayofYearSet::DayOfYear::DayOfYear():month(1), day(1){

}

DayofYearSet::DayOfYear::DayOfYear(int newMonth, int newDay){
    month = newMonth;
    day = newDay;
    //Checking the validty of input
    if((month ==1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day>31 || month > 12 || month < 1){
        cout << "Wrong input, month will be January, day will be 1.\n";
        month = 1;
        day = 1;
    }
    else if((month ==4 || month ==6 || month == 9 || month == 11) && day>30 || month > 12 || month < 1){
        cout << "Wrong input, month will be January, day will be 1.\n";
        month = 1;
        day = 1;
    }
    else if(month==2 && day > 29 || month > 12 || month < 1){
        cout << "Wrong input, month will be January, day will be 1.\n";
        month = 1;
        day = 1;
    }
}

void DayofYearSet::DayOfYear::operator++(){
    if((month ==1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day==31){
        day=1;
        if(month!=12) ++month;
        else month=1;
    }
    else if((month ==4 || month ==6 || month == 9 || month == 11) && day==30){
        day=1;
        ++month;
    }
    else if(month==2 && day == 29){
        day=1;
        ++month;
    }
    else ++day;
}

ostream& operator<<(ostream &out, const DayofYearSet::DayOfYear &doy){
    switch (doy.getMonthNumber()){

        case 1:
            out << "January "; break;
        case 2:
            out << "February "; break;
        case 3:
            out << "March "; break;
        case 4:
            out << "April "; break;
        case 5:
            out << "May "; break;
        case 6:
            out << "June "; break;
        case 7:
            out << "July "; break;
        case 8:
            out << "August "; break;
        case 9:
            out << "September "; break;
        case 10:
            out << "October "; break;
        case 11:
            out << "November "; break;
        case 12:
            out << "December "; break;
        default:
            out << doy.getMonthNumber() << " ";
            out << "Error in DayOfYear output.";
    }

    out << doy.getDay();

    return out;
}

bool DayofYearSet::DayOfYear::operator==(const DayOfYear &oth)const{
    return(this->day == oth.getDay() && this->month == oth.getMonthNumber());
}

DayofYearSet:: DayofYearSet(){
    set = new DayOfYear[1];
    set[0].set(1,1);
    sizeofSet = 1;
}

/*
    Constructor takes initializer_list. Firstly, eliminates the same elements in the list (to prevent writing same elements again)
by using 2 tempSet objects and assigns the elements of set to actual set object.
*/
DayofYearSet::DayofYearSet(const initializer_list<DayOfYear>& setList){
    
    int i=0,j,flag=0,count=1;
    DayofYearSet tempSet;
    decltype(tempSet) tempSet2;  //DayofYearSet tempSet2

    tempSet.setsizeofSet(0);
    
    for(auto a=setList.begin(); a!=setList.end(); ++a){
        if(tempSet.size()==0){
            tempSet[0]= *a;
            tempSet.setsizeofSet(1);
        }
        else{
            tempSet = tempSet + *a;
        }
    }
    tempSet2[0] = tempSet[0];
    for(i=0;i<tempSet.size();++i){
        for(j=i+1;j<tempSet.size();++j){
            if(tempSet[i]==tempSet[j])
                flag=1;            
        }
        if(flag!=1){
            tempSet2 = tempSet2 + tempSet[i];
            ++count;
        }
        else flag=0;
    }
    set = new DayOfYear[tempSet2.size()];
    for(i=0;i<tempSet2.size();++i){
        set[i] = tempSet2[i];
    }
    sizeofSet = tempSet2.size();


}

ostream& operator<<(ostream &out, const DayofYearSet &doySet){
    int i=0;
    out << "{";
    for(i=0;i<doySet.size();++i){
        if(i!=doySet.size()-1)
            out << doySet[i] << ", ";
        else 
            out << doySet[i];
    }
    out << "}";
    return out;
}

/*
    Firstly, checks the size of sets. If sizes are same, 
then checks if each elements of sets are same or not.
*/
bool DayofYearSet::operator==(const DayofYearSet & oth)const{
    int i,j,flag=0;
    if(sizeofSet != oth.size()) return false;
    for(i=0;i<sizeofSet;++i){
        for(j=0;j<sizeofSet;++j){
            if(set[i]==oth[j]) flag=1;
        }
        if(flag==0) return false;
        else flag=0;
    }
    return true;
}

bool DayofYearSet::operator!=(const DayofYearSet & oth)const{
    int i,j,flag=0;
    if(sizeofSet != oth.size()) return true;
    for(i=0;i<sizeofSet;++i){
        for(j=0;j<sizeofSet;++j){
            if(set[i]==oth[j]) flag=1;
        }
        if(flag==0) return true;
        else flag=0;
    }
    return false;
}

DayofYearSet::~DayofYearSet(){
    delete [] set;
    set = nullptr;
}

int DayofYearSet::size()const{
    return sizeofSet;
}

void DayofYearSet::remove(const DayOfYear & key){
    if(issame(key)){
        int i,index=0;
        --sizeofSet;
        decltype(set) temp = new DayOfYear[sizeofSet]; //DayOfYear* temp
        for(i=0;i<sizeofSet+1;++i){
            if(set[i]==key)
                ; //intentionally empty
            else{
                temp[index] = set[i];
                ++index;
            }
        }
        delete [] set;
        set = nullptr;
        set = temp;
    }
    else
        cout << "Set doesn't have what you want to remove!\n";
}

DayofYearSet DayofYearSet::operator+(const DayOfYear &oth){
    DayofYearSet tempSet;
    tempSet[0] = oth;
    tempSet = *this + tempSet;
    return tempSet;
}

DayofYearSet::DayofYearSet(const DayofYearSet &copy){
    int i;
    set = new DayOfYear[copy.size()];
    
    for(i=0;i<copy.size();++i)
        set[i] = copy[i];

    sizeofSet = copy.size();
}

bool DayofYearSet::issame(const DayOfYear & key)const{
    int i;
    for(i=0;i<sizeofSet;++i)
        if(set[i]==key) return true;
    return false;
}


DayofYearSet DayofYearSet:: operator+(DayofYearSet &oth)const{
    int i,j,newSize;
    DayofYearSet tempSet;
    tempSet = oth - (oth^*this); /* Substracting the intersection of two set from one of the sets to prevent adding same elements more than once*/
    newSize = this->size()+tempSet.size();
    decltype(set) temp = new DayOfYear[newSize]; //DayOfYear* temp
    
    for(i=0;i<this->size();++i)
        temp[i] = this->set[i];

    for(i=this->size(),j=0;i<newSize;++i,++j)
        temp[i] = tempSet[j];
    
    tempSet.setsizeofSet(newSize);

    delete [] tempSet.set;
    tempSet.set = nullptr;
    tempSet.set = temp;
    return tempSet;
}



DayofYearSet DayofYearSet:: operator-(const DayofYearSet &oth)const{
    int i,j;
    DayofYearSet tempSet;
    tempSet.setsizeofSet(0);
    for(i=0;i<this->size();++i){
        if(oth.issame(this->set[i]))
            ; //intentionally empty
        else{
            /* This if else is necessary, because default constructor of DayofYearSet initializes 1 January for set.
                Firstly, we need to change first element of the set.*/
            if(tempSet.size()==0){
                tempSet.setsizeofSet(1);
                tempSet[0] = this->set[i];
                
            }
            else{
                tempSet = tempSet + this->set[i];
            }
        }
    }
    return tempSet;
}

DayofYearSet DayofYearSet:: operator-(const DayOfYear &oth)const{
    int i;
    DayofYearSet tempSet;
    tempSet[0] = oth;
    tempSet = *this - tempSet;
    return tempSet;
}

void DayofYearSet:: setsizeofSet(int newSize){
    sizeofSet = newSize;
}

DayofYearSet& DayofYearSet:: operator=(const DayofYearSet &oth){
    if(*this == oth) return *this;
    int i;
    DayOfYear* temp = new DayOfYear[oth.size()];
    for(i=0;i<oth.size();++i)
        temp[i] = oth[i];
    
    sizeofSet = oth.size();
    delete [] set;
    set = nullptr;
    set = temp;
    return *this;
}

DayofYearSet DayofYearSet:: operator^(const DayofYearSet &oth)const{
    int i,j;
    DayofYearSet tempSet;
    tempSet.setsizeofSet(0);

    for(i=0;i<this->size();++i){
        if(oth.issame(this->set[i])){
            /* This if else is necessary, because default constructor of DayofYearSet initializes 1 January for set.
                Firstly, we need to change first element of the set.*/
            if(tempSet.size() == 0){
                tempSet.setsizeofSet(1);
                tempSet[0] = this->set[i];
            }
            else{
                tempSet = tempSet + this->set[i];
            }
        }
    }
    return tempSet;
}

DayofYearSet::DayOfYear& DayofYearSet:: operator[](const int& index)const{
    if (index >= sizeofSet && index != 0){
        cout << "index out of bound, exiting...\n";
        exit(0);
    }
    return set[index];
}

/*
I add 365 day to tempSet. Then substract the element of the object from tempSet to obtain complement of the set.  
*/
DayofYearSet DayofYearSet:: operator!()const{
    DayOfYear tempDOY(1,2); /* it starts 2 January because tempSet has default constructor which menas tempSet already has 1 January*/
    DayofYearSet tempSet;
    
    for(;;++tempDOY){
        tempSet = tempSet + tempDOY;
        if(tempDOY.getDay()==31 && tempDOY.getMonthNumber()==12)
            break;
    }
    tempSet = tempSet - *this;
    return tempSet;

}

}

