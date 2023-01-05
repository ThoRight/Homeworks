#include "PFArray.h"

namespace MuratArray
{
    template<typename T>
    PFArray<T>::PFArray() :capacity(50), used(0){
        a = shared_ptr<T[]>(new T[50]);
    }

    template<typename T>
    PFArray<T>::PFArray(int size) :capacity(size), used(0){
        a = shared_ptr<T[]>(new T[size]);
    }

    template<typename T>
    PFArray<T>::PFArray(const PFArray<T>& pfaObject)
        :capacity(pfaObject.getCapacity( )), used(pfaObject.getNumberUsed( )){
        
        a = shared_ptr<T[]>(new T[capacity]);
        for(int i=0;i<used;++i)
            a[i] = pfaObject.a[i];
    }
    template<typename T>
    void PFArray<T>::addElement(const T& element){
        int i;
        try {
            if(used >= capacity){
                throw out_of_range("Capacity of array has been reached");
            }
            a[used] = element;
            ++used;
        }
        catch (out_of_range& e){
            cerr << e.what() << '\n';
            shared_ptr<T[]> temp;
            temp = shared_ptr<T[]>(new T[capacity+10]);
            capacity+=10;
            for(i=0;i<used;++i){
                temp[i] = a[i];
            }
            cout << "Capacity has been increased by 10.\n";
            temp[used] = element;
            a = nullptr;
            a = temp;
            ++used;
        }
    }
   
    template<typename T>
    bool PFArray<T>::full() const{
        return (capacity == used);
    }

    template<typename T>
    int PFArray<T>::getCapacity()const{
        return capacity;
    }

    template<typename T>
    int PFArray<T>::getNumberUsed() const{
        return used;
    }


    template<typename T>
    T& PFArray<T>::operator[](int index){
        if (index >= used){
            throw out_of_range("Illegal index in PFArray"); 
        }
        return a[index];
    }

    template<typename T>
    PFArray<T>& PFArray<T>::operator=(const PFArray<T>& rightSide){
        if (capacity != rightSide.capacity){
            a = shared_ptr<T[]>(new T[rightSide.capacity]);
        }

        capacity = rightSide.capacity;
        used = rightSide.used;
        for (int i = 0; i < used; i++)
            a[i] = rightSide.a[i];

        return *this;
    }
    
    template<typename T>
    PFArray<T>& PFArray<T>::operator=(const PFArray&& source){
        int i;
        if (this != &source){    // checking self-assignment
            a = source.a;
            source.a = nullptr;
            used = source.used;
            capacity = source.capacity;
        }
        return *this;
    }

    template<typename T>
    bool PFArray<T>::empty()const{
        return used==0;
    }

    template<typename T>
    PFArray<T>::PFArray( PFArray&& source){
        int i;
        if(capacity < source.capacity()){
            a=nullptr;
            shared_ptr<T[]> temp;
            temp = shared_ptr<T[]>(new T[source.capacity()]);
            a = temp;
            capacity = source.capacity;
            used = source.used;
            temp = nullptr;
        }
        else{    
            a = source.a;
            used = source.used;
        }
        source.a = nullptr;
        source.used = 0;
        source.capacity = 0;
    }

    template<typename T>
    void PFArray<T>::erase(Iterator position){
        int flag=0;
        Iterator b(this->begin());
        Iterator end(this->end());
        --end;
        for(auto it=this->begin();it!=this->end();++it){  // Try to find positon of element that wanted to be erased
            if(it==position){
                flag=1;
                b=it;
            }
        }
        if(flag==1){ // to erase the element, shifting left rest of them after that element. 
            for(auto it=b;it!=end;++it){
                *it = *(it+1);
            }
        --used;
        }
        else
            cout << "Given iterator and PFArray's iterators doesn't match. So element couldn't be erased.\n";
    }

    template<typename T>
    void PFArray<T>::clear(){
        a = nullptr;
        shared_ptr<T[]> temp = shared_ptr<T[]>(new T[1]);
        a = temp;
        used = 0;
        capacity = 1;
    }

    template<typename T>
    typename PFArray<T>::Iterator PFArray<T>::begin()const{
        return PFArray<T>::Iterator(&a[0]);
    }

    template<typename T>
    typename PFArray<T>::Iterator PFArray<T>::end()const{
        return PFArray<T>::Iterator(&a[used]);
    }

    template<typename T>
    const typename PFArray<T>::Iterator PFArray<T>::cbegin()const{
        return PFArray<T>::Iterator(&a[0]);
    }

    template<typename T>
    const typename PFArray<T>::Iterator PFArray<T>::cend()const{
        return PFArray<T>::Iterator(&a[used]);
    }
    
    template<typename T>
    int PFArray<T>::size()const{
        int count=0;
        for(auto p=this->begin();p!=this->end();++p){ // instead of using return 'used', i use this because it confirms begin and end functions are working.
            ++count;
        }
        return count;
    }

    template<typename T>
    PFArray<T>::Iterator::Iterator(T* newptr){
        ptr = newptr;
    }

    template<typename T>
    typename PFArray<T>::Iterator PFArray<T>::Iterator::operator+(ptrdiff_t k){
        return Iterator(this->ptr + k);
    }

    template<typename T>
    typename PFArray<T>::Iterator& PFArray<T>::Iterator::operator++(){
        ++(this->ptr);
        return *this;
    }

    template<typename T>
    typename PFArray<T>::Iterator PFArray<T>::Iterator::operator++(int){
        return Iterator(ptr+1);
    }


    template<typename T>
    typename PFArray<T>::Iterator& PFArray<T>::Iterator::operator--(){
        --(this->ptr);
        return *this;
    }

    template<typename T>
    typename PFArray<T>::Iterator& PFArray<T>::Iterator::operator--(int){
        return Iterator(ptr-1);
    }


    template<typename T>
    T& PFArray<T>::Iterator::operator*()const{
        return *(this->ptr);
    }

    template<typename T>
    T* PFArray<T>::Iterator::operator->()const{
        return this->ptr;
    }

    template<typename T>
    typename PFArray<T>::Iterator PFArray<T>::Iterator::operator-(ptrdiff_t k){
        return Iterator(this->ptr - k);
    }

    template<typename T>
    ptrdiff_t PFArray<T>::Iterator::operator-(const Iterator& oth)const{
        return ptr - oth.ptr;
    }

    template<typename T>
    T& PFArray<T>::Iterator::operator[](ptrdiff_t k){
        return *(ptr + k);
    }

    template<typename T>
    typename PFArray<T>::Iterator& PFArray<T>::Iterator::operator=(const Iterator& oth){
        if(oth.ptr == this->ptr)
            return *this;
        ptr = oth.ptr;
        return *this;
    }

    template <typename T>
    bool PFArray<T>::Iterator::operator==(const Iterator& iter)const{
        return this->ptr == iter.ptr;
    }

    template <typename T>
    bool PFArray<T>::Iterator::operator!=(const Iterator& iter)const{
        return this->ptr != iter.ptr;
    }

    template <typename T>
    bool PFArray<T>::Iterator::operator<(const Iterator& iter)const{
        return this->ptr < iter.ptr;
    }

    template <typename T>
    bool PFArray<T>::Iterator::operator>(const Iterator& iter)const{
        return this->ptr > iter.ptr;
    }

    template <typename T>
    bool PFArray<T>::Iterator::operator<=(const Iterator& iter)const{
        return this->ptr <= iter.ptr;
    }

    template <typename T>
    bool PFArray<T>::Iterator::operator>=(const Iterator& iter)const{
        return this->ptr < iter.ptr;
    }

    template<typename T>
    typename PFArray<T>::Iterator& PFArray<T>::Iterator::operator+=(int k)const{
        ptr += k;
        return *this;
    }

    template<typename T>
    typename PFArray<T>::Iterator& PFArray<T>::Iterator::operator-=(int k)const{
        ptr -= k;
        return *this;
    }


    template <typename T>
    T* PFArray<T>::Iterator::getptr()const{
        return ptr;
    }



}// MuratArray