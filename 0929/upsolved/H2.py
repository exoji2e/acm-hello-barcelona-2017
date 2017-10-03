T = int(raw_input())
out = []
for _ in range(T):
    N = int(raw_input())
    x = raw_input()
    y = raw_input()
    z = raw_input()
    k, m, n, o, f = (0,0,0,0,0)
    for i in range(N):#zip(x,y,z):
        a = x[i]
        b = y[i]
        c = z[i]
        if a == b and a == c:
            o += 1
        elif a == b:
            k += 1
        elif a == c:
            n += 1
        elif b == c:
            m += 1
        else:
            f += 1
    d = min(k, m, n)
    res = o + 2*d
    used = o + 3*d
    k -= d
    n -= d
    m -= d
    MAX = max(k, m, n)
    if MAX*2 < N - used:
        res += MAX
        used += MAX*2
        if k == MAX:
            k = 0
        elif n == MAX:
            n = 0
        else:
            m = 0
        rm = max(k,n,m)
        f -= MAX - rm
        if f//2 < rm:
            res += f//2
        else:
            res += (f + rm)//3
    else:
        res += (N - used)//2
        used = N
    out.append(res)
    #print(res)
print('\n'.join(map(str,out)))
            
