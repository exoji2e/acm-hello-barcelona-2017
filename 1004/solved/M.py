def boarders(s):
    b = [0]*len(s)
    for i in range(1, len(s)):
        k = b[i-1]
        while k>0 and s[k] != s[i]:
            k = b[k-1]
        if s[k] == s[i]:
            b[i] = k+1
    return b
def main():
    s = raw_input()
    b = boarders(s)
    m = 0
    n = len(s)
    dp = [-1]*n
    for i in range(n):
        if dp[i] != -1 or b[i] == 0:
            continue
        k = b[i]
        c = 1
        # Ew so ugly :( should be rec function.
        while k > 0:
            c += 1
            if dp[k-1] != -1:
                c += dp[k-1]
                k = 0
            else:
                k = b[k-1]
        dp[i] = c
        if dp[m] < dp[i]:
            m = i
        while k > 0:
            c -= 1
            if dp[k-1] != -1:
                k = 0
            else:
                dp[k-1] = c
                k = b[k-1]
    print(s[:m+1])


if __name__ == '__main__':
    main()

