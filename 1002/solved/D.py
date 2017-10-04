import math
def inters(l1, l2):
    a1,b1,c1 = l1
    a2,b2,c2 = l2
    cp = a1*b2 - a2*b1 
    if cp != 0:
        return ((b1*c2 - b2*c1)/cp, (a2*c1 - a1*c2)/cp)
    else:
        return False
def Tp(a, b, c, A, B):
    d = (b + c - a)/(2*c)
    return(A[0] + (B[0] - A[0])*d, A[1] + (B[1] - A[1])*d)

def Tps(P1, P2, P3):
    a = dist(P2, P3)
    b = dist(P1, P3)
    c = dist(P1, P2)
    return (Tp(b, c, a, P2, P3),
            Tp(a, c, b, P1, P3),
            Tp(a, b, c, P1, P2))
    
def line(Q, dx, dy):
    return (-dx, -dy, dy*Q[1] + dx*Q[0])
def dist(p, q):
    return math.hypot(p[0]-q[0], p[1] - q[1])


x1, y1, x2, y2, x3, y3 = map(int, input().split())
P1 = (x1, y1)
P2 = (x2, y2)
P3 = (x3, y3)

Q1, Q2, Q3 = Tps(P1, P2, P3)
l1 = line(Q1, P3[0]-P2[0], P3[1] - P2[1])
l2 = line(Q2, P1[0]-P3[0], P1[1] - P3[1])

i = inters(l1, l2)
print(i[0], i[1], dist(i, Q1))

