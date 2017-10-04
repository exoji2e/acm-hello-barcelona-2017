def zfun(t):
    z = [0]*len(t)
    n = len(t)
    l, r = (0,0)
    for i in range(1,len(t)):
        if i < r:
            z[i] = min(z[i-l], r-i+1)
        while z[i] + i < n and t[i+z[i]] == t[z[i]]:
            z[i] += 1
        if i + z[i] - 1 > r:
            l = i
            r = i + z[i] - 1
    return z
t = raw_input()
p = raw_input()
k = int(raw_input())
z = zfun(p + '#' + t)[len(p)+1:]
zr = (zfun(p[::-1] + '#' + t[::-1])[len(p)+1:])[::-1]

out = []
for i in range(len(t)-len(p)+1):
    if z[i] + zr[i+len(p)-1] >= len(p) - k:
        out.append(i)
print(len(out))
print(' '.join(map(lambda x: str(x + 1),sorted(out))))


