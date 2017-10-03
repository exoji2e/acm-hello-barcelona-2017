T = int(input())
for _ in range(T):
    N, L = map(int, input().split())
    L = L*1000
    ext = [tuple(map(int, input().split())) for _ in range(N)]
    ext.sort(key=lambda x: -x[0])
    t = 0
    speed = 0
    dist = 0
    i = 0
    for f in ext:
        dl = L-dist
        tt = f[1]/1000000
        dv = f[0]*tt
        dd = (2*speed + dv)*tt/2
        if dl > dd:
            dist += dd
            speed += dv
            t += tt
        else:
            b = (speed/f[0])
            dt = -b + (b*b + 2*dl/f[0])**(0.5)
            t += dt
            dist = L
        if dist >= L:
            break
    if dist<L:
        t += (L-dist)/speed
    print(t)


