#include <stdio.h>
#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>
using namespace std;
int main() {
    int N, M, K, T; 
    cin >> N >> M >> K >> T;
    vector<vector<pair<int, int> > > studs(N);
    vector<int> iq(N);
    for(int i = 0; i<N; i++) 
        cin >> iq[i]; 
    vector<vector<pair<int,int> > > apps(T + 2);
    for(int i = 0; i<K; i++) {
        int P, Q, R;
        cin >> P >> Q >> R;
        if(Q == M)
            apps[R].push_back(make_pair(P-1, Q-1));
        else
            apps[R+1].push_back(make_pair(P-1, Q-1));

    }
    vector<vector<long double> > pp(M-1);
    for(int i = 0; i<M-1; i++) {
        pp[i].push_back(0.0);
    }
    for(int t = 1; t<=T; t++)
        for(int i = 0; i<M-1; i++) {
            long double p;
            cin >> p;
            pp[i].push_back(pp[i][t-1] + p);
        }
    long double max = 0;
    int maxid = 1;
    for(int t = 1; t<=T; t++) {
        bool found = false;
        for(auto p: apps[t]) {
            studs[p.first].push_back(make_pair(p.second, t));
            if(p.second == M-1) found = true;
        }
        if(!found) continue;
        int app = 0;
        long double se = 0.0;
        for(int i = 0; i<N; i++) {
            bool aM = false;
            long double pi = 1.0;
            for(auto u: studs[i]) {
                if(u.first == M-1) {
                    aM = true;
                    app +=1;
                } else {
                    pi = pi*(1 - (pp[u.first][t-1] - pp[u.first][u.second-2]));
                }
            }
            if(aM) {
                se += iq[i]*pi;
            }
        }
        if(app > 0) {
            //double estim = se/pe;
            if(max<se) {
                max = se;
                maxid = t;
            }

        }
    }
    cout.precision(9);
    cout << max << " " << maxid << endl;

}
