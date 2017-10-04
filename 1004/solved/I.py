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
while s:
    z = zfun(s)
    n = len(s)
    done = False
    for i in range(n):
        if i + z[i] == n:
            print(s[:i])
            done = True
            break
    if not done:
        print(s)
    try:
        s = raw_input()
    except:
        s = ''
