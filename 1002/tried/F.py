import math
def dist(p):
    return math.hypot(p[0]-p[2], p[1] - p[3])
def dist(p, q):
    return math.hypot(p[0]-q[0], p[1] - q[1])

def pts2line(p, q):
    return (-q[1] + p[1], q[0] - p[0], p[0]*q[1] - p[1]*q[0])

def distl(l, p):
    return abs(l[0]*p[0] + l[1]*p[1] + l[2])/math.hypot(l[0], l[1])

def project(l, p):
    a, b, c = l
    return ((b*(b*p[0] - a*p[1]) - a*c)/(a*a + b*b), (a*(a*p[1] - b*p[0]) - b*c)/(a*a + b*b))

# distSeg, distHalfinf, distLine
def distSegP(P, Q, R):
    a, b, c = pts2line(P, Q)
    Rpx, Rpy = project((a,b,c), R)
    dp = min(dist(P, R), dist(Q, R))
    dl = distl((a,b,c), R)
    if inside(Rpx, P[0], Q[0]) and inside(Rpy, P[1], Q[1]):
        return (dl, dl, dl)
    if insideH((Rpx, Rpy), P, Q):
        return (dp, dl, dl)
    
    return (dp, dp, dl)

def inters(l1, l2):
    a1,b1,c1 = l1
    a2,b2,c2 = l2
    cp = a1*b2 - a2*b1 
    if cp != 0:
        return ((b1*c2 - b2*c1)/cp, (a2*c1 - a1*c2)/cp)
    else:
        return False

def inside(i, A, B):
    return (i-A)*(i-B) <= 0

def insideS(i, A, B):
    return inside(i[0], A[0], B[0]) and inside(i[1], A[1], B[1])
def insideH(i, A, B):
    return (i[0] - A[0])*(A[0] - B[0]) <= 0 and (i[1] - A[1])*(A[1] - B[1]) <= 0
def tangent(alpha, a, d, p, cx, cy):
    Tx, Ty = map(lambda x: x*d/a,(cx - p[0], cy - p[1]))
    Rx, Ry = (Tx*math.cos(alpha) - Ty*math.sin(alpha), Tx*math.sin(alpha) + Ty*math.cos(alpha))
    return (Rx + p[0], Ry + p[1])

x1, y1, x2, y2 = map(int, input().split())
xc, yc, r = map(int, input().split())
p1 = (x1,y1)
p2 = (x2,y2)
pm = (xc, yc)
if distSegP(p1,p2, (xc,yc))[0] > r:
    print(dist(p1,p2))
else:
    d1 = dist(p1,pm)
    alpha = math.asin(r/d1)
    T11 = tangent(alpha, d1, math.sqrt(d1*d1 - r*r), p1, xc, yc)
    T12 = tangent(-alpha, d1, math.sqrt(d1*d1 - r*r), p1, xc, yc)
    d2 = dist(p2,pm)
    alpha2 = math.asin(r/d2)
    T21 = tangent(alpha2, d2, math.sqrt(d2*d2 - r*r), p2, xc, yc)
    T22 = tangent(-alpha2, d2, math.sqrt(d2*d2 - r*r), p2, xc, yc)
    d = 0
    if dist(p1, pm) - r > 0.0000001:
        d+= dist(p1, T11)
        T1 = [T11, T12]
    else:
        T1 = [p1]

    if dist(p2, pm) - r > 0.0000001:
        d+= dist(p2, T21)
        T2 = [T21, T22]
    else:
        T2 = [p2]

    mina = 100
    for t1 in T1:
        for t2 in T2:
            xy = (t1[0]-xc)*(t2[0]-xc) + (t1[1]-yc)*(t2[1]-yc)
            mina = min(mina, abs(math.acos(xy/(r*r))))
    print(d + mina*r)

