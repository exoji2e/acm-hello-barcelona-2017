n = int(raw_input())
z = list(map(int, raw_input().split()))
p = [0]*n
i = 1
lasti = 0
while i<n:
    if z[i] != 0:
        if i + z[i] > lasti:
            for j in range(lasti - i, z[i]):
                if i + j > n - 1: break
                p[i+j] = max(j+1, p[i+j])
                lasti = i + j
    else:
        lasti = max(i, lasti)
    i += 1
print(' '.join(map(str, p)))
