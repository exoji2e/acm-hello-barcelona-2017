def zfun(t):
    z = [0]*len(t)
    n = len(t)
    l, r = (0,0)
    for i in range(1,n):
        if i < r:
            z[i] = min(z[i-l], r-i+1)
        while z[i] + i < n and t[i+z[i]] == t[z[i]]:
            z[i] += 1
        if i + z[i] - 1 > r:
            l = i
            r = i + z[i] - 1
    return z

s = raw_input()
t = ''
tot = 0
ns = len(s)
for i in range(ns):
    t = s[ns-i-1] + t
    m = max(zfun(t))
    n = len(t)
    tot += (n-m)*(n+m+1)//2
print(tot)
