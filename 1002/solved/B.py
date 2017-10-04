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


A = tuple(map(int,input().split()))
B = tuple(map(int,input().split()))
C = tuple(map(int,input().split()))
D = tuple(map(int,input().split()))

# dist a - b
print(dist(A,C))
print(distSegP(C, D, A)[0])
print(distSegP(C, D, A)[1])
print(distSegP(C, D, A)[2])
print(distSegP(A, B, C)[0])
i = inters(pts2line(A, B), pts2line(C, D))
#6
if i == False or not insideS(i, A, B) or not insideS(i, C, D):
    print(min(distSegP(A,B,C)[0],distSegP(A,B,D)[0],distSegP(C,D,A)[0],distSegP(C,D,B)[0]))
else:
    print(0)
#7
if i == False or not insideS(i, A, B) or not insideH(i, C, D):
    print(min(distSegP(A,B,C)[0],distSegP(A,B,D)[0],distSegP(C,D,A)[1],distSegP(C,D,B)[1]))
else:
    print(0)
  
#8
if i == False or not insideS(i, A, B):
    print(min(distSegP(A,B,C)[0],distSegP(A,B,D)[0],distSegP(C,D,A)[2],distSegP(C,D,B)[2]))
else:
    print(0)

#9
print(distSegP(A, B, C)[1])

#10
if i == False or not insideS(i, C, D) or not insideH(i, A, B):
    print(min(distSegP(A,B,C)[1],distSegP(A,B,D)[1],distSegP(C,D,A)[0],distSegP(C,D,B)[0]))
else:
    print(0)

#11
if i == False or not insideH(i, C, D) or not insideH(i, A, B):
    print(min(distSegP(A,B,C)[1],distSegP(A,B,D)[1],distSegP(C,D,A)[1],distSegP(C,D,B)[1]))
else:
    print(0)

#12
if i == False or not insideH(i, A, B):
    print(min(distSegP(A,B,C)[1],distSegP(A,B,D)[1],distSegP(C,D,A)[2],distSegP(C,D,B)[2]))
else:
    print(0)

#13
print(distSegP(A,B,C)[2])

#14
if i == False or not insideS(i, C, D):
    print(min(distSegP(A,B,C)[2],distSegP(A,B,D)[2],distSegP(C,D,A)[0],distSegP(C,D,B)[0]))
else:
    print(0)

#15
if i == False or not insideH(i, C, D):
    print(min(distSegP(A,B,C)[2],distSegP(A,B,D)[2],distSegP(C,D,A)[1],distSegP(C,D,B)[1]))
else:
    print(0)

#16
if i == False:
    print(distSegP(A,B,C)[2])
else:
    print(0)

