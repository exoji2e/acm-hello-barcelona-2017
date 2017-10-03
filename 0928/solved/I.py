n = map(int, input())
al = list(map(int, input().split()))
alpha = list('abcdefghijklmnopqrstuvwxyz')
out = []
for a in al:
    out.append(alpha[a])
    alpha = [alpha[a]] + alpha[:a] + alpha[a+1:]

print(''.join(out))
