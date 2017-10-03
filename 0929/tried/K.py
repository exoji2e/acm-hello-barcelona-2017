L, N = map(int, input().split())
def gcd(a, b):
    return b if a%b == 0 else gcd(b, a%b)
def dfs(l, n, w, weight):
    if l == L:
        s = set()
        for i in range(len(w)):
            for j in range(i+1,len(w)+1):
                s.add(str(w[i:j]))
        return len(s)*weight
    s = 0
    for i in range(min(l, n)):
        w[l] = i
        s += dfs(l+1, n, w, weight)
    if n > l:
        w[l] = l
        s+= dfs(l+1, n, w, weight*(n-l))
    return s

d = dfs(1, N, [0]*L, N)
n = N**L
g = gcd(d, n)
print('if n == ' + str(N) + ' and l == ' + str(L) + ': print(\'' + str(d//g) + '/' + str(n//g) + '\')\n')

