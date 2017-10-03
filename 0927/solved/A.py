n, m, k = map(int, input().split())
ai = list(map(int, input().split()))
tot = 0.0
deg = [0]*n
for _ in range(m):
    a, b = map(int, input().split())
    deg[a-1] += 1
    deg[b-1] += 1

for i in range(n):
    tot += ai[i]*deg[i]/(2*m)

print(tot*k)
