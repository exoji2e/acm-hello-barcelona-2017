#include <stdio.h>
#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>
#include <cmath>
#include <string.h>
using namespace std;
int mzfun(string t, int n){
    vector<int> z;
    z.push_back(0);
    int m = 0;
    int l = 0, r = 0;
    for(int i = 1; i<n; i++) {
        if (i < r) {
            z.push_back(min(z[i-l], r-i+1));
        } else {
            z.push_back(0);
        } 
        while (z[i] + i < n and t[i+z[i]] == t[z[i]]) {
            z[i]++;
        }
        
        if (i + z[i] - 1>r) {
            l = i;
            r = i + z[i] -1;
        }
        m = max(m, z[i]);
    }
    return m;
}
int main() {
    string s; 
    cin >> s;
    string t = "";
    int n = s.length();
    long long sum = 0;
    for(int i = 0; i<n; i++) {
        t = s[n-i-1] + t;
        int nn = i + 1;
        int m = mzfun(t, nn);
        sum += (nn-m)*(nn+m+1)/2;
    }
    cout << sum << endl;
}

