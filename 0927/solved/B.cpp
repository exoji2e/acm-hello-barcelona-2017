#include <stdio.h>
#include<iostream>
#include <algorithm>
#include <math.h>
using namespace std;
int abs(int a) { return a>=0?a:-a; }
int dist(int x1, int y1, int x2, int y2) {
    return abs(x1 - x2) + abs(y1 - y2);
}
int main() {
    int N; 
    scanf("%d", &N);
    int x[N];
    int y[N];
    for(int i = 0; i<N; i++) {
        scanf("%d %d", &x[i], &y[i]);
    }
    int d[N];
    for(int i = 0; i<N; i++) d[i] = dist(x[0], y[0], x[i], y[i]);
    int sum = 0;
    for(int t = 1; t<N; t++) {
        int min = 1000000;
        int minid = -1;
        for(int i = 0; i<N; i++) if(d[i] != 0 && d[i] < min) {
            min = d[i];
            minid = i;
        }
        sum += min;
        for(int i = 0; i<N; i++) {
            int dd = dist(x[minid], y[minid], x[i], y[i]);
            if (dd < d[i]) d[i] = dd;
        }
    }
    cout << sum*2 << endl;
}
