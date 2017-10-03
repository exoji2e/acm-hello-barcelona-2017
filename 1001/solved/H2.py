def outside(x, y, coords):
    c = 0
    for xi, yi in coords:
        if xi > x or yi > y:
            c += 1
    return c
n = int(input())
sz = 1000000
xs = [-sz]
for i in range(1,n+1):
    lo = xs[-1]
    hi = sz + 1
    while lo < hi:
        mid = (lo + hi) >> 1
        print('?', mid, sz)
        c = int(input())
        if c < i:
            lo = mid + 1
        else:
            hi = mid
    xs.append(lo)
coords = []
out = '!'
for i in range(1, n+1):
    x = xs[i]
    lo = -sz
    hi = sz
    while lo < hi:
        mid = (lo + hi) >> 1
        print('?', x, mid)
        c = int(input())
        if c < i - outside(x, mid, coords):
            lo = mid + 1
        else:
            hi = mid
    coords.append((x, lo))
    out += ' ' + str(x) + ' ' + str(lo)

print(out)
