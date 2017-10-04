def boarders(s):
    b = [0]*len(s)
    for i in range(1, len(s)):
        k = b[i-1]
        while k>0 and s[k] != s[i]:
            k = b[k-1]
        if s[k] == s[i]:
            b[i] = k+1
    return b
p = raw_input()
pp = p + '#' + p
k = int(raw_input())
if k == 0 or p == '':
    print(0)
elif k == 1:
    print(1 if p =='a' else 0)
else:
    F = [[0]*(len(p)+1) for _ in range(k+1)]
    R = [[0]*(len(p)+1) for _ in range(k+1)]
    A = [[0]*2 for _ in range(len(p) + 1)]

    b = boarders(pp)

    ind = 0 if p[0] == 'a' else 1
    A[0][ind] = 1
    for i in range(1, len(p)+1):
        kk = b[len(p) + i]
        while kk>0 and pp[kk] != 'a':
            kk = b[kk -1]
        if pp[kk] == 'a':
            kk += 1
        A[i][0] = kk
    for i in range(1, len(p)+1):
        kk = b[len(p) + i]
        while kk>0 and pp[kk] != 'b':
            kk = b[kk -1]
        if pp[kk] == 'b':
            kk += 1
        A[i][1] = kk


    for i in range(len(p) + 1):
        F[0][i] = i
        F[1][i] = A[i][0]
        F[2][i] = A[i][1]

    for kk in range(3, k+1):
        for i in range(len(p) + 1):
            F[kk][i] = F[kk-1][F[kk-2][i]]
    
    for i in range(len(p)+1):
        if F[1][i] == len(p):
            R[1][i] = 1
        if F[2][i] == len(p):
            R[2][i] = 1

    MOD = 1000000009
    for kk in range(3,k+1):
        for i in range(len(p)+1):
            R[kk][i] = (R[kk-2][i]+R[kk-1][F[kk-2][i]])%MOD


    print(R[k][0])


