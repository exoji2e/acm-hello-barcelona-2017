def trim(s):
    return ''.join(filter(lambda x: x!='(' and x!=')' and x!=',', list(s)))
N = int(input())
t = ['']
for i in range(N-1):
    tt = list(map(int, map(trim, input().split())))
    t.append((tt[0:3], tt[3:6], tt[6:9]))

M = int(input())
for _ in range(M):
    if N == 1:
        print('yes')
        continue
    l = list(map(int, input().split()))
    ln = l[0]
    l = [2]*15 + l[1:] + [2]*15
    head = 15
    state = 1
    for _ in range(10):
        trans = t[state][l[head]]
        state = trans[0]
        l[head] = trans[2]
        head += trans[1]
        if state == N:
            break
    if state == N:
        print('yes')
    else:
        print('no')

    





