import math
def main():
    n = int(input())
    pts = [tuple(map(int, input().split())) for _ in range(n)]
    if n == 1:
        print(pts[0][0], pts[0][1])
        return
    dx = pts[0][0] - pts[1][0] 
    dy = pts[0][1] - pts[1][1]
    line = True
    for i in range(2, len(pts)):
        dx2 = pts[0][0] - pts[i][0]
        dy2 = pts[0][1] - pts[i][1]
        if dx*dy2 != dx2*dy:
            line = False
    if line:
        pts.sort()
        pp = pts[(n-1)//2]
        print(pp[0], pp[1])
        return
    yi = (0.5, 0.5)
    for i in range(100):
        nx, ny, s = (0.0, 0.0, 0.0)
        for p in pts:
            d = math.hypot(yi[0] - p[0], yi[1] - p[1])
            nx += p[0]/d
            ny += p[1]/d
            s += 1/d
        yi = (nx/s, ny/s)
    print(yi[0], yi[1])

if __name__ == '__main__':
    main()
