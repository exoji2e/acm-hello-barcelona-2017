import math
def dist(p, q):
    return math.hypot(p[0]-q[0], p[1] - q[1])
def pts2line(p, q):
    return (q[1] - p[1], -q[0] + p[0], -p[0]*q[1] + p[1]*q[0])

def overlap(A, B, C, D):
    if A[0] == B[0]:
        return __overlap(A[1], B[1], C[1], D[1])
    else:
        return __overlap(A[0], B[0], C[0], D[0])

def __overlap(x1, x2, x3, x4):
    x1, x2 = (min(x1,x2), max(x1, x2))
    x3, x4 = (min(x3,x4), max(x3, x4))
    return x2 >= x3 and x1 <= x4

def p(P):
    print(str(P[0]) + ' ' + str(P[1]))

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

def SegSeg(A, B, C, D):
    eqa = A == B
    eqc = C == D
    if eqa and eqc:
        if A == C:
            p(A)
            return True
        return False
    if eqc:
        eqa, A, B, C, D = (eqc, C, D, A, B)
    if eqa:
        l = pts2line(C, D)
        if l[0]*A[0] + l[1]*A[1] + l[2] == 0 and inside(A[0], C[0], D[0]) and inside(A[1], C[1], D[1]):
            p(A)
            return True
        return False

    A, B = tuple(sorted([A,B]))
    C, D = tuple(sorted([C,D]))
    l1 = pts2line(A, B)
    l2 = pts2line(C, D)
    if l1[0]*l2[1] == l1[1]*l2[0]:
        if l1[0]*l2[2] == l1[2]*l2[0]:
            if overlap(A, B, C, D):
                if B == C:
                    p(B)
                    return True
                if D == A:
                    p(A)
                    return True
                s = sorted([A,B,C,D])
                p(s[1])
                p(s[2])
                return True
            else:
                return False
        else:
            return False
    ix, iy = inters(l1, l2)
    if inside(ix, A[0], B[0]) and inside(iy, A[1], B[1]) and inside(ix, C[0], D[0]) and inside(iy, C[1], D[1]):
        p((ix, iy))
        return True
    return False

A = tuple(map(int, input().split()))
B = tuple(map(int, input().split()))
C = tuple(map(int, input().split()))
D = tuple(map(int, input().split()))
if not SegSeg(A, B, C, D):
    print('Empty')





