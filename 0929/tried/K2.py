L, N = map(int, input().split())
def gcd(a, b):
    return b if a%b == 0 else gcd(b, a%b)
dp = [[-1]*(L+1) for _ in range(L+1)]
nk = [int(N**i) for i in range(L)]
for i in range(L+1):
    dp[i][i] = (1, 1)
def f(l, k):
    if dp[l][k] != -1:
        return dp[l][k]
    p, q = f(l-1, k)
    if l > 3 and k > 1:
        a = p*(p-1) + (p+q)*(q*nk[k] - p + 1)
    else: 
        a =  (p+q)*(q*nk[k] - p) + p*p
    b = q*q*nk[k]
    g = gcd(a, b)
    dp[l][k] = (a//g, b//g)
    return dp[l][k]

x = 1
y = 1
for k in range(1, L):
    a, b = f(L, k)
    x, y = (x*b + y*a, y*b)

    g = gcd(x, y)
    x, y = (x//g, y//g)
print(str(x) +'/' + str(y))
    
