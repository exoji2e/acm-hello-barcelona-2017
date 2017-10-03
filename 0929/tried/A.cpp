#include <stdio.h>
#include<iostream>
#include <algorithm>
#include <math.h>
#include <vector>
using namespace std;
int main() {
    int T;
    scanf("%d", &T);
    for(int t = 0; t<T; t++) {
        int N; 
        int M; 
        scanf("%d %d", &N, &M);
        vector<pair<int,int> > ptsx;
        vector<pair<int,int> > ptsy;
        int a, b;
        for(int i = 0; i<N; i++) {
            cin >> a >> b;
            ptsx.push_back(make_pair(a,b));
            ptsy.push_back(make_pair(b,a));
        }
        sort(ptsx.begin(), ptsx.end());
        sort(ptsy.begin(), ptsy.end());
        int sum = 0;
        for(int rec = 0; rec<M; rec++) {
            int l, r, b, t, x, y;
            scanf("%d %d %d %d", &l, &r, &b, &t);
            std::vector<pair<int, int> >::iterator lox, hix, loy, hiy;
            lox = lower_bound(ptsx.begin(), ptsx.end(), make_pair(l, b));
            hix = upper_bound(ptsx.begin(), ptsx.end(), make_pair(r, t));
            loy = lower_bound(ptsy.begin(), ptsy.end(), make_pair(b, l));
            hiy = upper_bound(ptsy.begin(), ptsy.end(), make_pair(t, r));
            int lx = hix - lox, ly = hiy - loy;
            if(lx == N) {
                sum += ly;
            } else if(ly == N) {
                sum += lx;
            }else if (hix - lox < hiy - loy) {
                for(int i = lox - ptsx.begin(); i<hix - ptsx.begin(); i++) {
                    x = ptsx[i].first;
                    y = ptsx[i].second;
                    if(x >= l && x <= r && y >= b && y <= t)
                        ++sum;
                }
            }else {
                for(int i = loy - ptsy.begin(); i<hiy - ptsy.begin(); i++) {
                    y = ptsy[i].first;
                    x = ptsy[i].second;
                    if(x >= l && x <= r && y >= b && y <= t)
                        ++sum;
                }
            }
        }
        cout << sum << endl;
    }
}
