import math
def dist(p):
    return math.hypot(p[0]-p[2], p[1] - p[3])
def vec(p):
    return (p[2]-p[0], p[3] - p[1])
def dot(p, q):
    return p[0]*q[0] + p[1]*q[1]
def cross(p, q):
    return p[0]*q[1] - p[1]*q[0]
p = tuple(map(int, input().split()))
q = tuple(map(int, input().split()))
print(dist(p), dist(q))
pv = vec(p)
qv = vec(q)
print(pv[0] + qv[0], pv[1] + qv[1])
print(dot(pv, qv), cross(pv, qv))
print(abs(cross(pv, qv)/2))
