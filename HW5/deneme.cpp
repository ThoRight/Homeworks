#include <iostream>
using namespace std;
int main(){
    char* a[5];
    a[0] = new char;
    a[0][10] = 'a';
    cout << a[0][10];
}