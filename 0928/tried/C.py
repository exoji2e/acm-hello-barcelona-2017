n = int(input())
c = [list(map(int, input().split())) for _ in range(n)]
c.sort(key=lambda x: x[2])

import math
def line(c1, c2):
    dx = c2[0] - c1[0]
    dy = c2[1] - c1[1]
    t = c1[2]/(c1[2] + c2[2])
    x = c1[0] + t*dx
    y = c1[1] + t*dy
    return (-dx, -dy, dy*y + x*dx)
def p2line(p1, p2):
    dx = p1[0] - p2[0]
    dy = p1[1] - p2[1]
    return (-dx, -dy, dy*p1[1]+ dx*p1[0])
def circ(c1, c2):
    dx = c2[0] - c1[0]
    dy = c2[1] - c1[1]
    t = c1[2]/(c1[2] + c2[2])
    x = c1[0] + t*dx
    y = c1[1] + t*dy
    x2 = c2[0] + dx*c2[2]/(c1[2]-c2[2])
    y2 = c2[1] + dy*c2[2]/(c1[2]-c2[2])
    xm = (x + x2)/2
    ym = (y + y2)/2
    return (xm, ym, math.hypot(x-xm, y-ym))

def dist(line, p):
    return abs(line[0]*p[0] + line[1]*p[1] + line[2])/math.hypot(line[0], line[1])
def tangent(alpha, a, d, p, cx, cy):
    Tx, Ty = map(lambda x: x*d/a,(cx - p[0], cy - p[1]))
    Rx, Ry = (Tx*math.cos(alpha) - Ty*math.sin(alpha), Tx*math.sin(alpha) + Ty*math.cos(alpha))
    return p2line(p, (Rx + p[0], Ry + p[1]))


if n == 2:
    print('YES')
    if c[0][2] == c[1][2]:
        print((c[0][0] + c[1][0])/2, (c[0][1] + c[1][1])/2)
    else:
        x, y, r = circ(c[0],c[1])
        print(x, y - r)
else:
    rds = set()
    for x in c:
        rds.add(x[2])
    pairsc = [(i, j) for i in range(n) for j in range(n) if c[i][2]<c[j][2]]
    pairsl = [(i, j) for i in range(n) for j in range(n) if i < j and c[i][2]==c[j][2]]
    pts = []
    if len(pairsl) >= 2:
        # lines
        a1,b1,c1 = line(c[pairsl[0][0]],c[pairsl[0][1]])
        a2,b2,c2 = line(c[pairsl[1][0]],c[pairsl[1][1]])
        cp = a1*b2 - a2*b1 
        if cp != 0:
            pts.append(((b1*c2 - b2*c1)/cp, (a2*c1 - a1*c2)/cp))
    else:
        # circs
        x1, y1, r1 = circ(c[pairsc[0][0]], c[pairsc[0][1]])
        x2, y2, r2 = circ(c[pairsc[1][0]], c[pairsc[1][1]])
        dx = x2 - x1
        dy = y2 - y1
        if math.hypot(dx, dy) < r1 + r2:
            lo = math.atan(dx/dy) if dy != 0 else 0.0
            hi = lo + math.pi/2
            for i in range(60):
                mid = (lo + hi)/2
                x = x1 + r1*math.cos(mid)
                y = y1 + r1*math.sin(mid)
                if r2 < math.hypot(x-x2, y-y2):
                    hi = mid
                else:
                    lo = mid
            pts.append((x,y))
            lo = math.atan(dx/dy) if dy != 0 else 0.0
            hi = lo - math.pi/2
            for i in range(60):
                mid = (lo + hi)/2
                x = x1 + r1*math.cos(mid)
                y = y1 + r1*math.sin(mid)
                if r2 < math.hypot(x-x2, y-y2):
                    hi = mid
                else:
                    lo = mid
            pts.append((x,y))
        elif abs(math.hypot(dx, dy) - r1 - r2) < 0.000001:
            pts.append((x1 + dx*r1/(r1+r2), y1 + dy*r1/(r1+r2)))
    printed = False
    for p in pts:
        tangents = []
        ok = True
        rat = math.hypot(c[0][0]-p[0], c[0][1] - p[1])/c[0][2]
        for ci in c:
            a = math.hypot(ci[0]-p[0], ci[1] - p[1])
            rat2 = a/ci[2]
            alpha = math.asin(1/rat2)
            d = math.sqrt(a*a - ci[2]*ci[2])
            tangents.append(tangent(alpha, a, d, p, ci[0], ci[1]))
            tangents.append(tangent(-alpha, a, d, p, ci[0], ci[1]))
            if abs(rat - rat2) > 0.000001:
                ok = False
        for ci in c:
            for t in tangents:
                if dist(t, (ci[0], ci[1])) - ci[2] < -0.000001:
                    ok = False
        if ok and not printed:
            print('YES')
            print(p[0], p[1])
            printed = True
    if not printed:
        print('NO')
    

