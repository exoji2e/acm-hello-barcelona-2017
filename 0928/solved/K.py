a, b = input().split()
cnt = 0
i = 0
while i < len(a):
    if b == a[i:i+len(b)]:
        i+=len(b) - 1
    i += 1
    cnt += 1
print(cnt)

