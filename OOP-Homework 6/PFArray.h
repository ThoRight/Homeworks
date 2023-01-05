#ifndef PFARRAY_H_
#define PFARRAY_H_

#include <memory>
#include <iterator>
#include <iostream>
#include <algorithm>

using namespace std;


namespace MuratArray
{
    template<typename T>
    class PFArray
    {
    public:
        class Iterator{
        public:
            /*   Iterator class must be compatible with std::find, std::sort and std::for_each. To do this,
                our iterator is like random access iterator. That's why i overloaded these operators.
            */
            using iterator_category = random_access_iterator_tag;   
            using value_type = T;
            using difference_type = ptrdiff_t;
            using pointer = T*;
            using reference = T&;

            Iterator(T* newptr);
            Iterator& operator++();
            Iterator operator++(int);
            Iterator& operator--();
            Iterator& operator--(int);
            T& operator*()const;
            T* operator->()const;
            bool operator==(const Iterator& iter)const;
            bool operator!=(const Iterator& iter)const;
            bool operator<(const Iterator& iter)const;
            bool operator<=(const Iterator& iter)const;
            bool operator>(const Iterator& iter)const;
            bool operator>=(const Iterator& iter)const;
            Iterator& operator+=(int k)const;
            Iterator& operator-=(int k)const;
            Iterator& operator=(const Iterator& oth);
            Iterator operator+(ptrdiff_t  k);
            T& operator[](ptrdiff_t  k);
            Iterator operator-(ptrdiff_t  k);
            ptrdiff_t operator-(const Iterator& oth)const;
            T* getptr()const;

        private:
            T* ptr;  /* it doesn't keep data, i didn't allocate any memory space for this pointer, 
                        it just helps for operation(+,-,etc.) in adresses as iterator.
                    */
        };

        PFArray(); //Initializes with a capacity of 50.
        PFArray(int capacityValue);
        PFArray(const PFArray<T>& pfaObject); // copy constructor
        void addElement(const T& element);
        bool full()const; // Checks if array is full.
        int getCapacity()const;
        int getNumberUsed()const;
        bool empty()const;
        T& operator[](int index);
        PFArray<T>& operator=(const PFArray<T>& rightSide);

        PFArray<T>& operator=(const PFArray&& source); //move sementics
        PFArray(PFArray<T>&& source);
        
        Iterator begin()const;
        Iterator end()const;
        const Iterator cbegin()const;
        const Iterator cend()const;
        int size()const;
        void erase(Iterator position);
        void clear();

    private:
        shared_ptr<T[]> a; //for data.
        int capacity; //for the capacity of the array.
        int used; //for the number of currently using.
    };

}// MuratArray

#endif /* PFARRAY_H_ */

