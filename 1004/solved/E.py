def boarders(s):
    b = [0]*len(s)
    for i in range(1, len(s)):
        k = b[i-1]
        while k>0 and s[k] != s[i]:
            k = b[k-1]
        if s[k] == s[i]:
            b[i] = k+1
    return b
s = raw_input()
t = s + s[::-1]
l = boarders(t)[-1]
print(l if l<=len(s) else len(s))
