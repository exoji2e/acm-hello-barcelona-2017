T = int(input())
def gcd(a, b):
    return b if a%b == 0 else gcd(b, a%b)

for _ in range(T):
    r, w = map(int, input().split())
    dp = [-1]*(w+1)
    dp[0] = (1, 1)
    for i in range(1, w+1):
        a, b = (r, i+r)
        dpa, dpb = dp[i-1]
        dpa = dpb - dpa
        c, d = (i*dpa, (i+r)*dpb)
        x, y = (a*d + c*b, b*d)
        g = gcd(x, y)
        dp[i] = (x//g, y//g)
    print(str(dp[w][0]) + '/' + str(dp[w][1]))

