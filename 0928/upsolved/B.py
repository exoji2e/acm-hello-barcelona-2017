n = int(input())
s = input()
letters = [ord('A')+x for x in range(n)]
val = [False]*(1<<n)
for i in range(1<<n):
    satis = ''
    for j in range(n):
        if (i&(1<<j)):
            exec(chr(letters[j]) + '=True')
        else:
            exec(chr(letters[j]) + '=False')
    val[i] = eval(s)
dp = [2]*int(3**n)

def dfs(v):
    d = 0
    state = 0
    fail = False
    for i in range(n):
        if v[i] == 2:
            state += (3**i)*2
            fail = True
        if v[i] == 1:
            state += (3**i)
            d += 1<<i
    if not fail:
        if n%2 == 0:
            dp[state] = 1 if val[d] else 0
        else:
            dp[state] = 0 if val[d] else 1
    if dp[state] != 2:
        return dp[state]

    win = False
    for i in range(n):
        if v[i] == 2:
            v[i] = 0
            win = win or dfs(v) == 0
            v[i] = 1
            win = win or dfs(v) == 0
            v[i] = 2
    if win:
        dp[state] = 1
        return 1
    dp[state] = 0
    return 0
v = dfs([2]*n)
print('Alice' if v == 1 else 'Bob')

