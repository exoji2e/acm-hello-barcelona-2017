T = int(input())
def gcd(a, b):
    return b if a%b == 0 else gcd(b, a%b)
mem = {}
for _ in range(T):
    n, m = map(int, input().split())
    n, m = (n, m) if n<=m else (m, n)
    
    t = (n,m)
    if t in mem:
        print(mem[t])
        continue

    pts = [(x, y) for y in range(m) for x in range(n)]
    tot = 0
    for p1 in pts:
        for p2 in pts:
            if p1 == p2:
                break
            dx = p1[0] - p2[0]
            dy = p1[1] - p2[1]
            g = 1 if dx == 0 or dy == 0 else gcd(abs(dx), abs(dy))
            dx = dx//g
            dy = dy//g
            if dx == 0:
                tot += n*m - n
                continue
            if dy == 0:
                tot += n*m - m
                continue
            rml = p1[1]//dy + 1
            rmr = (m-1 - p1[1])//dy
            if dx < 0:
                rml = min(rml, -(n-1 - p1[0])//dx + 1)
                rmr = min(rmr, -(p1[0])//dx)
            else:
                rmr = min(rmr, (n-1 - p1[0])//dx)
                rml = min(rml, (p1[0])//dx + 1)
            add = n*m - rmr - rml
            tot += add

    tot = tot//3
    mem[(n,m)] = tot
    print(tot)
