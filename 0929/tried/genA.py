T = 5
n = 10000
m = 50000
sz = 100000
print(T)
import random
for _ in range(T):
    print(n, m)
    for _ in range(n):
        print(random.randint(0, sz), random.randint(0, sz))
    for _ in range(m):
        l, r = (random.randint(0, sz), random.randint(0, sz))
        b, t = (random.randint(0, sz), random.randint(0, sz))
        l, r = (min(l, r), max(l, r))
        b, t = (min(b, t), max(b, t))
        print(l, r, b, t)

