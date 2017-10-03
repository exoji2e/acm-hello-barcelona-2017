#include <stdio.h>
#include<iostream>
#include <algorithm>
#include <math.h>
#include <vector>
using namespace std;
int main() {
    int N; 
    cin >> N;
    vector<long long> p;

    int pi;
    p.push_back(0);
    for(int i = 0; i<N; i++) {
         cin >> pi;
         p.push_back(pi);
    }
    long long a;
    long long b;
    cin >> a;
    cin >> b;
    long long d[N+1]; 
    d[0] = 0LL;
    for(int i = 1; i<=N; i++) {
        d[i] = (i*i + i*a + b)*p[i] + d[i-1];
        for(long long j = i-1; j > 0; j--) {
            long long prod = (j*j + j*a + b)*p[i];
            d[j] = max(d[j], prod + d[j-1]);
        }
    }
    for(int i = 1; i<=N; i++)
        cout << d[i] << " ";
    cout << endl;
}
