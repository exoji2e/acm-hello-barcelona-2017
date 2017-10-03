def inside(x, y, coords):
    c = 0
    for xi, yi in coords:
        if xi <= x and yi<= y:
            c += 1
    return c

n = int(input())
coords = []
sz = 1000000
cnt = 0
for i in range(n):
    lo = -sz
    hi = sz + 1
    while lo < hi:
        mid = (lo + hi) >> 1
        cnt += 1
        if cnt == 50*n:
            raise ValueError('RIP')
        print('?', mid, sz)
        c = int(input())
        if c < n - i:
            lo = mid + 1
        else:
            hi = mid
    x = lo
    lo = -sz
    hi = sz + 1
    while hi - lo > 1:
        mid = (lo + hi) >> 1
        cnt += 1
        if cnt == 50*n:
            raise ValueError('RIP')
        print('?', x, mid)
        c = int(input())
        if c < n - i:
            lo = mid + 1
        else:
            hi = mid
    coords.append(str(x) + ' ' +str(lo))
s = '! ' + ' '.join(coords)
print(s)


